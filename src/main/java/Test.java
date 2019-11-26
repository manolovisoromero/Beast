import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Query;

import java.util.List;

import Entities.*;
import org.hibernate.criterion.Restrictions;


public class Test {

    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Add new Employee object
        Employee emp = new Employee();
        emp.setEmail("demo-User@mail.com");
        emp.setFirstName("demo");
        emp.setLastName("User");
        emp.setEmployeeId(1);

        session.save(emp);

//        usergame userGame = new usergame();
//        usergameID usergameid = new usergameID(3,3);
//        userGame.setUsergameid(usergameid);
//        userGame.setWin(false);
//
//        session.save(userGame);

//        User user1 = new User("test","tettt");
//
//
//        session.save(user1);




        // Get employees
        Query query = session.createQuery("from Employee");
        List<Employee> employees = query.list();

        for(Employee employee: employees){
            System.out.println(employee);
        }

        Criteria crit = session.createCriteria(usergame.class);
        crit.add(Restrictions.eq("usergameid.gameID",1));
        List<usergame> results = crit.list();
        for( usergame userGame1: results){
            System.out.println("usergame: "+userGame1);
        }

        // Get Employee

        Employee employe;
        employe = (Employee) session.get(Employee.class, 1) ;
        System.out.println("employe: "+ employe.getEmployeeId());

        usergame usergame1;
        usergameID usergameID1 = new usergameID(1,1);
        usergame1 = (usergame) session.get(usergame.class, usergameID1);


        // Update
        employe.setFirstName("Sjaakkk");
        session.update(employe);

        Employee employe1;
        employe1 = (Employee) session.get(Employee.class, 1) ;
        System.out.println("employe1: "+ employe1.getFirstName());





        session.getTransaction().commit();
        HibernateUtil.shutdown();



    }
}
