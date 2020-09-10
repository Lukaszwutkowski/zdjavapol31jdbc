package personSample.personDao;

import personSample.model.Person;

import java.util.List;

public interface PersonDao {

    List<Person> getAll();

    Person getById(int id);

    boolean addPerson(Person person);

    boolean updatePerson(int personId, Person updatePerson);

    boolean deletePerson(int personId);


    List<Person> getByFirstName(String firstName);

    List<Person> getByLastName(String lastName);

    List<Person> getByAgeBetween(int minAge, int maxAge);


}
