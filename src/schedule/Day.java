/**
 * Sleep schedule visualizer.
 *
 * @author Seth Gorrin
 * @since 2022-05-29
 */

package schedule;

import java.util.ArrayList;

/**
 * A visualization of a calendar day.
 * For a given hour, true is for sleeping, false is for awake.
 */
public class Day {
  public static final int HOURS = 24;

  private final ArrayList<Boolean> hours;
  private final DayName name;

  private int sleep;
  private int wake;

  /**
   * Constructor, set up an empty day.
   */
  public Day(DayName name) {
    this.name = name;
    hours = new ArrayList<>();
    sleep = 0;
    wake = 0;

    for (int i = 0; i < HOURS; i++) {
      hours.add(false);
    }
  }

  /**
   * Copy a day with the sleep hours shifted.
   *
   * @param day   the original schedule.Day
   * @param shift the amount to shift
   */
  public Day(Day day, int shift) {
    name = DayName.next(day.getName());
    hours = new ArrayList<>(day.hours);
    sleep = day.sleep;
    wake = day.wake;

    shiftSleep(shift);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append(String.format("%9s", name.name())).append("  \n");

    for (int i = 0; i < HOURS; i++) {
      builder.append(String.format("%02d", i)).append(": ");
      builder.append(hours.get(i) ? "#####  \n" : "·····  \n");
    }

    return builder.toString();
  }

  /**
   * Reset the day.
   */
  private void reset() {
    for (int i = 0; i < HOURS; i++) {
      hours.set(i, false);
    }
  }

  /**
   * Set the sleep hours for the day.
   *
   * @param sleep the first hour of sleep
   * @param wake   the first hour awake after sleeping
   */
  public void setSleepHours(int sleep, int wake) {
    setSleep(sleep);
    setWake(wake);

    update();
  }

  /**
   * Update the arraylist with the current hours.
   */
  public void update() {
    reset();
    if (sleep < wake) {
      for (int i = sleep; i < wake; i++) {
        hours.set(i, true);
      }
    } else {
      for (int i = 0; i < wake; i++) {
        hours.set(i, true);
      }
      for (int i = sleep; i < HOURS; i++) {
        hours.set(i, true);
      }
    }
  }

  /**
   * Shift sleep hours a given amount and direction.
   *
   * @param hours how many hours to shift (must be fewer than the hours in a day)
   */
  public void shiftSleep(int hours) {
    assert Math.abs(hours) < HOURS;

    sleep = (sleep + hours) % HOURS;
    wake = (wake + hours) % HOURS;

    if (sleep < 0) {
      sleep += HOURS;
    }
    if (wake < 0) {
      wake += 24;
    }

    setSleepHours(sleep, wake);
  }

  /**
   * Get the value of a given hour.
   *
   * @param h the hour
   * @return  the value of the hour
   */
  public boolean hour(int h) {
    assert h >= 0 && h < HOURS;

    return hours.get(h);
  }

  public DayName getName() {
    return name;
  }

  protected void setSleep(int sleep) {
    assert sleep < HOURS && sleep >= 0;
    this.sleep = sleep;
  }

  protected void setWake(int wake) {
    assert wake < HOURS && wake >= 0;
    this.wake = wake;
  }

  protected int getWake() {
    return wake;
  }
}
