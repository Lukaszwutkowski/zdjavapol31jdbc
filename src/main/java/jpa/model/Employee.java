package jpa.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
@Table(name = "pracownicy")
@NamedQueries({
        @NamedQuery(name = Employee.BY_FIRST_NAME, query = "select e from Employee e where e.firstName = :firstName"),
        @NamedQuery(name = Employee.BY_LAST_NAME, query = "select e from Employee e where e.lastName = ?1")
})
public class Employee {
    public static final String BY_FIRST_NAME = "byFirstName";
    public static final String BY_LAST_NAME = "byLastName";
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_pracownika")
    private int employeeId;
    @Column(name = "imie")
    private String firstName;
    @Column(name = "nazwisko")
    private String lastName;
    @Column(name = "wiek")
    private int age;


    @Temporal(TemporalType.DATE)
    private Date d1;
    @Temporal(TemporalType.TIME)
    private Date t1;
    @Temporal(TemporalType.TIMESTAMP)
    private Date t2;


    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public Date getT1() {
        return t1;
    }

    public void setT1(Date t1) {
        this.t1 = t1;
    }

    public Date getT2() {
        return t2;
    }

    public void setT2(Date t2) {
        this.t2 = t2;
    }

    /*
    private java.sql.Date d1;
    private java.sql.Time t1;
    private java.sql.Timestamp t2;
     */

    @Transient
    private String firstAndLastName;

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirstAndLastName() {
        firstAndLastName = firstName + " " + lastName;
        return firstAndLastName;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
