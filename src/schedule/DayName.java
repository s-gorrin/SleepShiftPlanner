/**
 * Sleep schedule visualizer.
 *
 * @author Seth Gorrin
 * @since 2022-05-29
 */

package schedule;

/**
 * The names of the days.
 */
public enum DayName {
  Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday;

  /**
   * Get the next day of the week.
   *
   * @param name  the current day
   * @return      the day following the current day
   */
  public static DayName next(DayName name) {
    int nextIndex = (name.ordinal() + 1) % 7;

    return DayName.values()[nextIndex];
  }
}
