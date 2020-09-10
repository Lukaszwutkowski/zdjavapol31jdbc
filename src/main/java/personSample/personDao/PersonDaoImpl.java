package personSample.personDao;

import personSample.model.Person;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class PersonDaoImpl implements PersonDao {
    private static final String URL = "jdbc:mysql://localhost:3306/sda_library?useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection connection;

    private void createConnection() {
        Properties prop = new Properties();
        prop.put("user", "root");
        prop.put("password", "RootPassword95");
        try {
            connection = DriverManager.getConnection(URL, prop);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public void createTable() {
        createConnection();

        //  String query = "create table person(person_id int, first_name varchar(55), last_name varchar(55), birth_date date)";
        String add1 = "insert into person(person_id, first_name, last_name, birth_date) values(1, 'Pawel', 'Nowak', '2020-09-07')";
        try {
            Statement statement = connection.createStatement();
            //   statement.executeUpdate(query);
            statement.executeUpdate(add1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<>();
        createConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from person";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date").toLocalDate()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection();
        return persons;
    }

    @Override
    public Person getById(int id) {
        createConnection();
        String query = "select * from person where person_id = ?";
        Person person = new Person(-1, "", "", LocalDate.now());
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                person = new Person(
                        resultSet.getInt("person_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection();
        return person;
    }

    @Override
    public boolean addPerson(Person person) {
        // TODO poprawic ta metode tak zeby nie wymagala person_id
        createConnection();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String add = String.format("insert into person(first_name, last_name, birth_date) values('%s', '%s', '%s')",
                person.getFirstName(), person.getLastName(), person.getBirthDate().format(formatter));
        int result = 0;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(add);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        closeConnection();
        return result > 0;
    }

    @Override
    public boolean updatePerson(int personId, Person updatePerson) {
        createConnection();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String update = String.format("update person set first_name = '%s', last_name = '%s', birth_date = '%s' where person_id = " + personId + ";",
        updatePerson.getFirstName(), updatePerson.getLastName(), updatePerson.getBirthDate().format(formatter));
        int result = 0;
        try (Statement statement = connection.createStatement()) {
            result = statement.executeUpdate(update);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection();
        return result > 0;
    }

    @Override
    public boolean deletePerson(int personId) {
        createConnection();
        String delete = String.format("delete from person where person_id = " + personId + ";");
        int result = 0;
        try (Statement statement = connection.createStatement()) {
            result = statement.executeUpdate(delete);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection();
        return result > 0;
    }

    @Override
    public List<Person> getByFirstName(String firstName) {
        return getAll()
                .stream()
                .filter(x -> x.getFirstName().toLowerCase().contains(firstName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getByLastName(String lastName) {
        return getAll()
                .stream()
                .filter(x -> x.getLastName().toLowerCase().contains(lastName.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> getByAgeBetween(int minAge, int maxAge) {
        List<Person> persons = new ArrayList<>();
        createConnection();
        String query = "select * from person where timestampdiff(YEAR, birth_date, NOW()) BETWEEN " + minAge  + " and " + maxAge;
        try ( Statement statement = connection.createStatement()){
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                persons.add(new Person(
                        rs.getInt("person_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date").toLocalDate()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        closeConnection();
        return persons;
    }
}
