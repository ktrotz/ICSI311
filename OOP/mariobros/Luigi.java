
import java.util.Stack;

//Concrete class
public class Luigi implements Character {

    protected Stack<Balloons<String>> balloons = new Stack<>();
    
    @Override
    public String name(){
        return "Luigi";
    }

    @Override
    public String team(){
        return "Team 2";
    }

    @Override
    public void setBalloons(){
        balloons.push(new Balloons<String>("ballon1"));
        balloons.push(new Balloons<String>("ballon2"));
        balloons.push(new Balloons<String>("ballon3"));
    }

    @Override
    public void popBalloon(String name){
        balloons.pop();
        System.out.println(name + " popped Luigi's balloon");
	}

    @Override
    public int balloonsLeft(){
        System.out.println("Luigi has " + balloons.size() + " balloons left");
        return balloons.capacity();
    }
}