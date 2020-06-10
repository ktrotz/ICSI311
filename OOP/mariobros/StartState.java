public class StartState implements State {
    public void doAction(Context context, int balloons, String name){
        System.out.println("Players are in start state");
        System.out.println("Players given " + balloons + " balloons");
        context.setState(this);
    }

    public String toString(){
        return "Players Ready";
    }
}