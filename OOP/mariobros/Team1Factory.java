// import java.util.ArrayList;


public class Team1Factory extends CharacterFactory {

    @Override
    public Character getCharacter(String name){
        if(name.equalsIgnoreCase("MARIO")){
            return new Mario();
        }
            
        else if(name.equalsIgnoreCase("PRINCESS PEACH")){
            return new PrincessPeach();
        }
        
        return null;       
    }


}