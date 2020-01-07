package logic;

import org.hibernate.Session;
import org.hibernate.Query;

import java.util.ArrayList;

import entities.*;


public class Test {

    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();

        //Machine.getInstance().updateNote(2,"updated 2");
        System.out.println(Machine.getInstance().getUnplayedGame(1));



        // Add new logic.Employee object
//        logic.Employee emp = new logic.Employee();
//        emp.setEmail("demo-User@mail.com");
//        emp.setFirstName("demo");
//        emp.setLastName("User");
//        emp.setEmployeeId(1);
//
//        session.save(emp);


//        entities.Note note = new Note();
//        note.setContent("hallo 9");
//        note.setUserID(1);
//        session.save(note);


        boolean [][] gamer = new boolean[][]{
                new boolean[]{true,true,false,false,false},
                new boolean[]{true,true,false,true,true},
                new boolean[]{false,true,false,true,false},
                new boolean[]{true,false,true,false,false},
                new boolean[]{true,false,false,false,true}
        };


//        Query query = session.createQuery("from Usergame");
//        List<Usergame> usergames = query.list();
//
//
//        Query query1 = session.createQuery("from Game");
//        List<Game> games = query1.list();
//
//        int indexer = 0;
//        for(Usergame Usergame: usergames){
//            if(Usergame.getUsergameid().getUserID() == 3){
//
//                for(Game game: games){
//                    if(game.getGameXY().getGameID() != Usergame.getUsergameid().getGameID()){
//                        indexer = game.getGameXY().getGameID();
//                    }
//                }
//            }
//        }
//
//        for(Game game: games){
//            if(game.getGameXY().getGameID() == indexer){
//                System.out.println(game.getValue());
//            }
//        }


//         Query queryy = session.createQuery("SELECT usergameid.gameID FROM Usergame  WHERE usergameid.userID =:cid ");
//        queryy.setParameter("cid", 1);
//        ArrayList<Usergame> usergames1 =  (ArrayList<Usergame>) queryy.list();
//        System.out.println(usergames1);



//        for(int x = 0;x < gamer.length; x++){
//            for(int y = 0; y < gamer[x].length; y++){
//                GameXY gameXY = new GameXY(1,x,y);
//                Game game = new Game(gameXY,gamer[x][y]);
//                session.save(game);
//            }
//        }
//

//
////        Usergame userGame = new Usergame();
////        usergameID usergameid = new usergameID(3,3);
////        userGame.setUsergameid(usergameid);
////        userGame.setWin(false);
////
////        session.save(userGame);
//
////        User user1 = new User("test","tettt");
////
////
////        session.save(user1);
//
//
//
//
//        // Get employees
//        Query query = session.createQuery("from logic.Employee");
//        List<logic.Employee> employees = query.list();
//
//        for(logic.Employee employee: employees){
//            System.out.println(employee);
//        }
//
//        Criteria crit = session.createCriteria(Usergame.class);
//        crit.add(Restrictions.eq("usergameid.gameID",1));
//        List<Usergame> results = crit.list();
//        for( Usergame userGame1: results){
//            System.out.println("Usergame: "+userGame1);
//        }
//
//        // Get logic.Employee
//
//        logic.Employee employe;
//        employe = (logic.Employee) session.get(logic.Employee.class, 1) ;
//        System.out.println("employe: "+ employe.getEmployeeId());
//
//        Usergame usergame1;
//        usergameID usergameID1 = new usergameID(1,1);
//        usergame1 = (Usergame) session.get(Usergame.class, usergameID1);
//
//
//        // Update
//        employe.setFirstName("Sjaakkk");
//        session.update(employe);
//
//        logic.Employee employe1;
//        employe1 = (logic.Employee) session.get(logic.Employee.class, 1) ;
//        System.out.println("employe1: "+ employe1.getFirstName());
//



//
//        session.getTransaction().commit();
//        HibernateUtil.shutdown();



    }
}
