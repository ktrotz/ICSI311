//package edu.albany.mariobros;



public class Team2Score {
	
	private static Team2Score score = null;
	protected int coins;

	private Team2Score() {
		coins = 0;
	}

	public static Team2Score getScore() {
		if(score == null) {
			score = new Team2Score();
		}

		return score;
	}

	public void addCoin(String name) {
		System.out.println(name + " collected coin");
		coins++;
		
		if(coins == 5)
			System.out.println("GAME OVER\nTeam 1 Won");
	}
	
	public void loseCoin(String bumper, String bumpee) {
		System.out.println(bumper + " bumped " + bumpee);
		System.out.println(bumpee + " lost coin");
		coins--;
	}

	public int getCoins() {
		return coins;
	}

	@Override
	public String toString() {
		return String.format("Team 2 Score: %d", coins);
	}

}

