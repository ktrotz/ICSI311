

public class StopState implements State { //when player dies
    
    public void doAction(Context context, int balloons, String name){
        if(balloons == 0){
            System.out.println(name + " is dead.");
            context.setState(this);
        }
    }

    public String toString(){
        return "Game Over";
    }
}