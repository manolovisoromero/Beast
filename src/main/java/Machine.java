import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class Machine {

    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("Beast");


    public ArrayList<Person> persons = new ArrayList<>();

    private static Machine machine = new Machine();

    private Machine(){

        System.out.println("hallo");
        Person persoon1 = new Person("Sjaak", 20,1);
        Person persoon2 = new Person("Greta", 17,2);
        Person persoon3 = new Person("Mauro", 6,3);

        persons.add(persoon1);
        persons.add(persoon2);
        persons.add(persoon3);
    }

    public  void addUser(String username, String password){
        System.out.println("hallo");

        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            User user = new User(username,password);
            em.persist(user);
            et.commit();
        }
        catch(Exception ex){
            if(et != null){
                et.rollback();
            }
            ex.printStackTrace();
        }
        finally{
            em.close();
        }


        ENTITY_MANAGER_FACTORY.close();

    }



    public static Machine getInstance(){
        return machine;
    }












}
