public class Person {

    public String Name;
    public int Age;
    public int Id;

    public Person(String name, int age, int id){
        this.Age = age;
        this.Name = name;
        this.Id = id;
    }


    @Override
    public String toString(){
        return this.Name +", "+ this.Age + " years old.";
    }
}
