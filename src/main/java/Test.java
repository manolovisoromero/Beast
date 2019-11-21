import org.hibernate.Session;

public class Test {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Add new Employee object
        Employee emp = new Employee();
        emp.setEmail("demo-user@mail.com");
        emp.setFirstName("demo");
        emp.setLastName("user");
        emp.setEmployeeId(1);

        session.save(emp);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
