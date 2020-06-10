

//Interface
public interface Character { //game character
    String name();
    String team();
    void setBalloons();
    void popBalloon(String name);
    int balloonsLeft();
}