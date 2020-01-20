package logic;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import REST_calls.ChangePassRequest;
import REST_calls.PostUsergame;
import REST_calls.RegisterRequest;
import REST_calls.ReturnMsg;
import com.google.gson.Gson;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Query;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import entities.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import javax.ws.rs.core.Response;


public class Machine {


    Gson gson = new Gson();
    GameHandler gameHandler = GameHandler.getInstance();

    private static Machine machine = new Machine();
    private Machine(){}

    public static Machine getInstance(){
        return machine;
    }


    public String registerUser(RegisterRequest registerRequest) throws NoSuchAlgorithmException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("from User");
        List<User> Users = query.list();

        for(User userr : Users){
            if(registerRequest.getUsername() == userr.getUsername()){
                return "Username already taken";
            }
        }

        User userr = new User(registerRequest.getUsername(),passwordHasher(registerRequest.getPassword()));
        session.save(userr);
        session.getTransaction().commit();
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

    public Response loginUser(String username, String password){
        String hashedPass = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            hashedPass = passwordHasher(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        Query query = session.createQuery("FROM User WHERE username =:username");
        query.setParameter("username", username);
        ArrayList<User> users = (ArrayList<User>) query.list();

        try{
            User user = users.get(0);
            System.out.println("WACHTWOORDEN"+user.getPassword()+ hashedPass);
            if(user.getPassword().equals(hashedPass)){
                String token = generateToken();
                user.setToken(token);
                session.update(user);
                ReturnMsg returnMsg = new ReturnMsg();
                returnMsg.setToken(user.getToken());
                returnMsg.setUserID(user.getUserID());
                return Response.status(202)
                        .entity(gson.toJson(returnMsg))
                        .header("Access-Control-Allow-Origin", "*")
                        .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                        .allow("OPTIONS")
                        .build();
            }
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();

        }catch(Exception e){
            System.out.println(e.getMessage());
            return Response.status(400)
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }finally{
            session.close();
            HibernateUtil.shutdown();

        }

    }

    public String generateToken(){
        return "123123213";
    }



    private User getUser(String username){
        return null;

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


            boolean [][] data = new boolean[5][5];

            for(Game gamer: persongames){
                data[gamer.getGameXY().getX()][gamer.getGameXY().getY()] = gamer.getValue();
            }


            GameField gameField = gameHandler.createLabels(data);
            gameField.setGameID(game.getGameXY().getGameID());


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

    public ArrayList<Game> postGame(boolean [][] gamelist){

        ArrayList<Game> games = new ArrayList<>();
        if(arrayRightsize(gamelist)){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            int id = getUniqueId(session);
            for(int i = 0;i<gamelist.length;i++){
                for(int j = 0;j<gamelist[i].length;j++){
                    Game game = new Game();
                    GameXY gameXY = new GameXY();
                    gameXY.setX(i);
                    gameXY.setY(j);
                    gameXY.setGameID(id);
                    game.setGameXY(gameXY);
                    game.setValue(gamelist[i][j]);
                    games.add(game);
                    session.save(game);
                }
            }
            session.getTransaction().commit();
            session.close();
            HibernateUtil.shutdown();
            return games;
        }
        return null;
    }

    public String deleteGame(int gameID){


        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            if(!getUniqueIds(session).contains(gameID)){
                return "GameID not found";
            }
            Query query = session.createQuery("from Game WHERE gameXY.gameID=:gameid");
            query.setParameter("gameid", gameID);
            List<Game> games =  query.list();
            for(Game game:games){
                session.delete(game);
            }
            session.getTransaction().commit();
            session.close();

            return "Succesfully deleted";
        }
        catch(Exception e){
            return "Something went wrong";
        }finally{
            HibernateUtil.shutdown();
        }




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

            if(maximumNotesReached(session,userID)){
                throw new Exception();
            }




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


    /*
    CRUD for usergame
     */

    public Response winCheck(PostUsergame postUsergame) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            Usergame usergame = new Usergame();
            usergameID usergameID = new usergameID();
            usergameID.setGameID(postUsergame.getGameID());
            usergameID.setUserID(postUsergame.getUserID());
            usergame.setUsergameid(usergameID);
            System.out.println(Arrays.deepToString(postUsergame.getGameField()));
            System.out.println(Arrays.deepToString(postUsergame.getPlayerInput()));


            if (checkArrayEqual(postUsergame.getPlayerInput(),postUsergame.getGameField()) ) {
                usergame.setWin(true);
            }else{
                usergame.setWin(false);
            }
            session.save(usergame);
            session.getTransaction().commit();
            return Response.status(202)
                    .entity(gson.toJson(usergame))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }catch(Exception e) {
            e.printStackTrace();
            ReturnMsg returnMsg = new ReturnMsg();
            returnMsg.setMessage(e.getMessage());
            return Response.status(400)
                    .entity(gson.toJson(returnMsg))
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                    .allow("OPTIONS")
                    .build();
        }finally{
            session.close();
            HibernateUtil.shutdown();
        }
    }


    /*
    Functional methods
     */

    private int getUniqueId(Session session){
        List<Integer> uniqueIds = getUniqueIds(session);
        System.out.println(uniqueIds);
        int uniqueId = 1;
        boolean found = false;
        while(!found ) {
            if(uniqueIds.contains(uniqueId)){
                uniqueId++;
            }else{
                found = true;
            }
        }
        System.out.println(uniqueId);
        return uniqueId;
    }

    private List<Integer> getUniqueIds(Session session){
        Query query = session.createQuery("SELECT DISTINCT gameXY.gameID from Game");
        List<Integer> uniqueIds = query.list();
        return uniqueIds;
    }

    private boolean arrayRightsize(boolean[][] game){
        if(game.length == 5){
            for(int i = 0;i<game.length;i++){
                if(game[i].length ==5){
                    return true;
                }
            }
        }
        return false;

    }

    private String passwordHasher(String password) throws NoSuchAlgorithmException {
        String hashedPassword;
        MessageDigest md = MessageDigest.getInstance("MD5");

        md.update(password.getBytes());

        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i< bytes.length;i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        hashedPassword = sb.toString();

        return hashedPassword;
    }




    private boolean checkArrayEqual(boolean[][] array1, boolean[][] array2){
        boolean equal = true;
        for(int i = 0;i<array1.length;i++){
            for(int j = 0;j<array1[0].length;j++){
                if(array1[i][j] != array2[i][j]){
                    equal = false;
                }
            }
        }

        return equal;
    }


    private boolean maximumNotesReached(Session session, int userID){
        Query query = session.createQuery("SELECT COUNT(*) FROM entities.Note  WHERE userID =:userid ");
        query.setParameter("userid", userID);
        Long count = (Long)query.uniqueResult();
        System.out.println(count);
        return count >= 5;
    }




















}
