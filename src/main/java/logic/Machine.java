package logic;

import java.lang.reflect.Array;
import java.util.ArrayList;

import REST_calls.ChangePassRequest;
import REST_calls.RegisterRequest;
import com.google.gson.Gson;
import org.hibernate.Session;
import org.hibernate.Query;

import java.util.List;
import java.util.Random;

import entities.*;



public class Machine {


    Gson gson = new Gson();

    public String testtoken;



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

    public String loginUser(String username, String password){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("FROM User WHERE username =:username");
        query.setParameter("username", username);
        ArrayList<User> users = (ArrayList<User>) query.list();

        try{
            User user = users.get(0);
            if(user.getPassword().equals(password)){
                String token = generateToken();
                user.setToken(token);
                session.update(user);
                return token;
            }
            return "Credentials invalid";

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally{
            session.close();
            HibernateUtil.shutdown();

        }

    }

    public String generateToken(){
        return "123123213";
    }



    /*
    CRUD for Game
     */
    public ArrayList<Game> getGamesByUser(int userID){
        ArrayList<Game> games = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.close();
        HibernateUtil.shutdown();






        return games;
    }

    public String getUnplayedGame(int userID){
        ArrayList<Game> games;
        ArrayList<Usergame> usergames;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query queryUsergames = session.createQuery("FROM entities.Usergame  WHERE usergameid.userID =:userid ");
        queryUsergames.setParameter("userid", userID);
        usergames =  (ArrayList<Usergame>) queryUsergames.list();

        Query queryGames = session.createQuery("FROM entities.Game");
        games = (ArrayList<Game>) queryGames.list();







        Game game = findUnplayedGame(games,usergames);


        if(game != null){

            Query queryGame = session.createQuery("FROM entities.Game WHERE gameXY.gameID =:gameid");
            queryGame.setParameter("gameid", game.getGameXY().getGameID());
            ArrayList<Game> persongames = (ArrayList<Game>) queryGame.list();


            GameField gameField = new GameField();
            boolean [][] data = new boolean[5][5];

            for(Game gamer: persongames){
                data[gamer.getGameXY().getX()][gamer.getGameXY().getY()] = gamer.getValue();
            }

            gameField.setPlayfield(data);
            session.close();
            HibernateUtil.shutdown();

            return gson.toJson(gameField);
        }else{
            session.close();
            HibernateUtil.shutdown();

            return "No game found";

        }



    }

    public Game findUnplayedGame(ArrayList<Game> games, ArrayList<Usergame> usergames){
        ArrayList<Integer> gamesNr = new ArrayList<>();
        ArrayList<Integer> usergamesNr = new ArrayList<>();



        for(Game game: games){
            gamesNr.add(game.getGameXY().getGameID());
        }

        for(Usergame usergame: usergames){
            usergamesNr.add(usergame.getUsergameid().getGameID());
        }

        System.out.println("gamesnr"+gamesNr);
        System.out.println("usergamesnr"+usergamesNr);


        for(int i = 0;i<games.size();i++){
            if(!usergamesNr.contains(games.get(i).getGameXY().getGameID())){
                System.out.println("game: "+games.get(i).getGameXY().getGameID());
                return games.get(i);
            }
        }


        return null;
    }


    /*
    CRUD for Note done
     */
    public ArrayList<Note> getNotesByUser(int userID){
        ArrayList<Note> notes;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();



        Query query = session.createQuery("FROM entities.Note  WHERE userID =:userid ");
        query.setParameter("userid", userID);
        notes =  (ArrayList<Note>) query.list();

        for(Note note: notes){
            System.out.println(note.getContent());
        }

        session.close();
        HibernateUtil.shutdown();

        return notes;
    }

    public String postNote(int userID, String content){

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            entities.Note note = new Note();
            note.setContent(content);
            note.setUserID(userID);
            session.save(note);
            session.getTransaction().commit();



        }catch(Exception e){
            System.out.println("Error "+ e.getMessage());
            return "Something went wrong" + e.getMessage();
        }finally{
            HibernateUtil.shutdown();

        }



        return "Success";





    }

    public String deleteNote(int noteID){

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Note note = (Note) session.get(entities.Note.class, noteID) ;

        session.delete(note);
        session.getTransaction().commit();

    }catch(Exception e){
        System.out.println("Error "+ e);
        return "Something went wrong" + e.getMessage();
    }finally{
        HibernateUtil.shutdown();

    }



        return "Success";


    }

    public String updateNote(int noteID, String content){

        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            Note note = (Note) session.get(entities.Note.class, noteID);

            note.setContent(content);

            session.update(note);
            session.getTransaction().commit();

        }catch(Exception e){
            System.out.println("Error "+ e);
            return "Something went wrong" + e.getMessage();
        }finally{
            HibernateUtil.shutdown();

        }



        return "Success";

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
