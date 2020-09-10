package personSample;

import personSample.model.Person;
import personSample.personDao.PersonDao;
import personSample.personDao.PersonDaoImpl;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        PersonDao dao = new PersonDaoImpl();
        long start = System.currentTimeMillis();
        System.out.println("WYSZUKIWANIE PO IMIENIU: ");
        List<Person> person = dao.getByFirstName("paw");
        person.forEach(System.out::println);

        System.out.println("\nWYSZUKIWANIE PO NAZWISKU:");
        PersonDao dao2 = new PersonDaoImpl();
        List<Person> person2 = dao2.getByLastName("ja");
        person2.forEach(System.out::println);


        System.out.println("\nWYSZUKIWANIE WEDLUG PRZEDZIALU WIEKU:");
        PersonDao dao3 = new PersonDaoImpl();
        List<Person> person3 = dao3.getByAgeBetween(18, 30);
        person3.forEach(System.out::println);

        System.out.println("\nWYSZUKIWANIE PO ID:");
        PersonDao dao4 = new PersonDaoImpl();
        System.out.println(dao4.getById(4));


        // TODO ZMIENIANIE ISTNIEJACEGO REKORDU PO ID
        /*
        PersonDao dao5 = new PersonDaoImpl();
        Person personUpdate = new Person("Anna", "Modra", LocalDate.of(1989, 06, 12));
        dao5.updatePerson(2, personUpdate);
         */

        // TODO USUWANIE ISTNIEJACEGO REKORDU PO ID

        /*
        PersonDao dao6 = new PersonDaoImpl();
        dao6.deletePerson(5);
         */


        System.out.println("\nWYSWIETLENIE WSZYSTKICH REKORDOW TABELI DANYCH:");
        List<Person> people = dao.getAll();
        people.forEach(System.out::println);


        // TODO TWORZENIE NOWEGO REKORDU
        /*
        Person newPerson = new Person("Adam", "Lubczyk", LocalDate.of(1998, 04, 23));
        dao.addPerson(newPerson);
        */

        // TODO METODA KTORA TWORZY NOWA TABELE

        //  new PersonDaoImpl().createTable();
    }
}
