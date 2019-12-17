package logic;

import org.hibernate.annotations.OptimisticLockType;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



@Entity
@org.hibernate.annotations.Entity(optimisticLock = OptimisticLockType.ALL, dynamicUpdate = true)
@Table(name = "employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "email") })
public class Employee implements  Serializable{



    public Employee(){
    }

    private static final long serialVersionUID = -1798070786993154676L;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer employeeId;

    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;

    @Column(name = "firstname",   nullable = false, length = 100)
    private String firstName;

    @Column(name = "lastname",  nullable = false, length = 100)
    private String lastName;

    @Override
    public String toString(){
        return employeeId +" : "+firstName+" "+lastName+" , "+ email;

    }
}
