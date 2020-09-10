package jpa;

import jpa.model.Employee;

import javax.persistence.*;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = factory.createEntityManager();

        TypedQuery<Employee> byFirstName = em.createNamedQuery(Employee.BY_FIRST_NAME, Employee.class);
        byFirstName.setParameter("firstName", "Adrian");
        byFirstName.getResultList().forEach(System.out::println);

        TypedQuery<Employee> byLastName = em.createNamedQuery(Employee.BY_LAST_NAME, Employee.class);
        byLastName.setParameter(1, "Jarzyna");
        byLastName.getResultList().forEach(System.out::println);

        Query query = em.createQuery("select concat(e.firstName , ' | ', e.lastName) from Employee e");
        query.getResultList().forEach(System.out::println);



      /*  Employee employee = em.find(Employee.class, 2);
        System.out.println(employee);

       */

       /* TypedQuery<Employee> tq = em.createQuery("select e from Employee e where e.firstName = :firstName", Employee.class);
        tq.setParameter("firstName", "Anna");
        tq.getResultList().forEach(System.out::println);

        */

       /* TypedQuery<Employee> tq = em.createQuery("select e from Employee e where e.firstName like 'A%'", Employee.class);
        tq.getResultList().forEach(System.out::println);

        */
//          employee.setFirstName("Marysia");
//        em.getTransaction().begin();
//        em.persist(employee);
//        em.remove(employee);
//        em.getTransaction().commit();
//        em.close();

//        Employee e = new Employee();
//        e.setFirstName("Adrian");
//        e.setLastName("Kwiatkowski");
//        e.setAge(45);
//        Employee e1 = new Employee();
//        e1.setFirstName("Anna");
//        e1.setLastName("Zielona");
//        e1.setAge(18);
//        Employee e2 = new Employee();
//        e2.setFirstName("Krzysztof");
//        e2.setLastName("Koziołek");
//        e2.setAge(44);
//        Employee e3 = new Employee();
//        e3.setFirstName("Anna");
//        e3.setLastName("Jantar");
//        e3.setAge(29);
//        em.getTransaction().begin();
//        em.persist(e);
//        em.persist(e1);
//        em.persist(e2);
//        em.persist(e3);
//        em.getTransaction().commit();
        em.close();
    }
}
