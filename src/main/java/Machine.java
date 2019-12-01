import java.util.ArrayList;

import REST_calls.ChangePassRequest;
import REST_calls.RegisterRequest;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Query;

import java.util.List;

import Entities.*;



public class Machine {


    Gson gson = new Gson();



    public ArrayList<Person> persons = new ArrayList<>();
    private static Machine machine = new Machine();
    private Machine(){}

    public static Machine getInstance(){
        return machine;
    }


    public String registerUser(RegisterRequest registerRequest){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User");
        List<User> Users = query.list();

        for(User userr : Users){
            if(registerRequest.getUsername() == userr.getUsername()){
                return "Username already taken";
            }
        }

        User userr = new User(registerRequest.getUsername(),registerRequest.getPassword());
        session.save(userr);
        session.close();
        HibernateUtil.shutdown();

        return "Register succesful";
    }

    public String changePassword(ChangePassRequest changePassRequest){

        try{
            //Open
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();


            //code
            User user;

            user = (User) session.get(User.class, changePassRequest.getUserID()) ;
            user.setPassword(changePassRequest.getPassword());
            session.update(user);

            //Close
            session.getTransaction().commit();
            session.close();
            HibernateUtil.shutdown();

            return "Password change success.";

        }catch(Exception e){
            System.out.println(e);
        }



        return " Password change failed.";
    }


    public String getGame(){

        boolean [][] game = new boolean[][]{
                new boolean[]{true,true,false,false,false},
                new boolean[]{true,true,false,true,true},
                new boolean[]{false,true,false,true,false},
                new boolean[]{true,false,true,false,false},
                new boolean[]{true,false,false,false,true}
        };

        String json = gson.toJson(game);
        return json;

    }




















}
