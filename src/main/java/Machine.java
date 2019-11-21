import java.util.ArrayList;

public class Machine {


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



    public static Machine getInstance(){
        return machine;
    }










}
