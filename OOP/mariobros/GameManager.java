// import sun.awt.image.PNGImageDecoder.Chromaticities;

//package edu.albany.mariobros;

public class GameManager {

	public static void main(String[] args) {

		// CREATE TEAMS USING ABSTRACT FACTORY DESIGN PATTERN
		// create team 1
		CharacterFactory team1 = TeamProducer.getTeam(true);

		Character mario = team1.getCharacter("MARIO");
		System.out.println("Character: " + mario.name());
		System.out.println(mario.team());
		mario.setBalloons(); // 3 balloons = 3 lives

		Character peach = team1.getCharacter("PRINCESS PEACH");
		System.out.println("Character: " + peach.name());
		System.out.println(peach.team() + "\n");
		peach.setBalloons();

		// create team 2
		CharacterFactory team2 = TeamProducer.getTeam(false);

		Character luigi = team2.getCharacter("LUIGI");
		System.out.println("Character: " + luigi.name());
		System.out.println(luigi.team());
		luigi.setBalloons();

		Character daisy = team2.getCharacter("PRINCESS DAISY");
		System.out.println("Character: " + daisy.name());
		System.out.println(daisy.team() + "\n");
		daisy.setBalloons();

		// CONTROL PLAYER STATES USING STATE DESIGN PATTERN
		// Game start
		Context context = new Context();

		StartState start = new StartState();
		start.doAction(context, mario.balloonsLeft(), null);
		System.out.println(context.getState().toString() + "\n");

		// TRACK TEAM SCORES USING SINGLETON DESIGN PATTERN
		// scores
		Team1Score team1Score = Team1Score.getScore(); // Singleton
		Team2Score team2Score = Team2Score.getScore();

		team1Score.addCoin(mario.name());

		System.out.println(team1Score.toString() + "\n");

		mario.popBalloon(luigi.name());
		mario.balloonsLeft();

		team2Score.addCoin(luigi.name());

		mario.popBalloon(daisy.name());
		mario.balloonsLeft();

		team1Score.addCoin(peach.name());
		team1Score.addCoin(peach.name());
		System.out.println(team1Score.toString() + "\n");

		team2Score.addCoin(luigi.name());
		mario.popBalloon(daisy.name());
		mario.balloonsLeft();
		System.out.println(team2Score.toString() + "\n");

		StopState stop = new StopState();
		stop.doAction(context, mario.balloonsLeft(), mario.name());

		System.out.println();
		System.out.println(team1Score.toString());
		System.out.println(team2Score.toString());
		System.out.println(context.getState().toString() + "\n");
	}
}
