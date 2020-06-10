// package abstractFactory;

public class TeamProducer {
    public static CharacterFactory getTeam(boolean team1) {
        if (team1)
            return new Team1Factory();

        else
            return new Team2Factory();
    }
}