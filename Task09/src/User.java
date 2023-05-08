public class User {

    private String name;

    private int id;

    private int age;

    public User(int age, String name) {
        this.name = name;
        this.age = age;
    }

    public void setAge(int value) {
        age = value;
    }

    public int getAge() {
        return age;
    }

    public void setName(String value) {
        name = value;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int value){
        id = value;
    }

    @Override
    public String toString() {
        return String.format("%d %s %d",id,name,age);
    }
}
