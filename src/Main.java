import schedule.DayName;
import schedule.Week;

/**
 * Main for sleep schedule planner.
 * This may eventually print a schedule or something.
 * It might be useful to also print a key:
 *    First ##### -> go to sleep at this time
 *    First ····· -> set alarm for this time
 */
public class Main {

  /**
   * Main to test and run the sleep planner.
   *
   * @param args not currently used
   */
  public static void main(String[] args) {
    Week week = new Week(DayName.Thursday);
    week.setFirstDay(6, 14, 3);
    System.out.println(Week.key());
    System.out.println(week);

    ToFile.toFile("schedule.txt", Week.key() + "\n\n" + week);
  }
}
