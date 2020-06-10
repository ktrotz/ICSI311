

public class Team2Factory extends CharacterFactory {
    
    @Override
    public Character getCharacter(String name){
        if (name.equalsIgnoreCase("LUIGI")){
            return new Luigi();
        }
        else if (name.equalsIgnoreCase("PRINCESS DAISY")){
            return new PrincessDaisy();
        }
        return null;
    }
}