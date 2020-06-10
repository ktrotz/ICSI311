
/*<<Description>>
   Singleton Design Pattern
   */

public class Team1Score {

   private static Team1Score score = null;
   protected int coins;

   private Team1Score() {
      coins = 0;
   }

   public static Team1Score getScore() {
      if (score == null)
         score = new Team1Score();

      return score;
   }

   public void addCoin(String name) {
      System.out.println(name + " collected coin");
      coins++;

      if (coins == 5)
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
      return String.format("Team 1 Score: %d", coins);
   }
}
