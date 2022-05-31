/**
 * Sleep schedule visualizer.
 *
 * @author Seth Gorrin
 * @since 2022-05-30
 */

package schedule;

import java.util.ArrayList;

/**
 * A week of days.
 */
public class Week {
  public static final int DAYS = 7;

  private final ArrayList<Day> days;
  private final DayName first;

  /**
   * Constructor for a week of Days.
   */
  public Week(DayName first) {
    days = new ArrayList<>();

    this.first = first;
    Day firstDay = new Day(first);
    days.add(firstDay);

    for (int i = 1; i < DAYS; i++) {
      days.add(new Day(days.get(i - 1), 0));
    }
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    for (Day day : days) {
      builder.append(String.format("%9s", day.getName().name())).append("  ");
    }
    builder.replace(builder.length() - 2, builder.length(), "\n");

    for (int i = 0; i < Day.HOURS; i++) {
      for (Day day : days) {
        builder.append(String.format("%02d", i)).append(": ");
        builder.append(day.hour(i) ? "#####  " : "·····  ");
      }
      builder.replace(builder.length() - 2, builder.length(), "\n");
    }

    return builder.toString();
  }

  /**
   * Get the key information for the schedule.
   *
   * @return  the key
   */
  public static String key() {
    return "Awake: ·····\n"
        + "Asleep: #####\n"
        + "The Asleep designation indicates sleeping for the full hour indicated.\n"
        + "For example, 00: ##### means sleeping from midnight to 1am.";
  }

  /**
   * Fill a full week with sleeps, appropriately shifted.
   *
   * @param sleepPerNight hours of sleep per night
   * @param shift         amount later each bedtime is than the previous
   */
  public void setWeek(int sleepPerNight, int shift) {
    int hoursAwake = (Day.HOURS + shift) - sleepPerNight;
    int sleepHour = days.get(0).getWake() + hoursAwake;

    while (sleepHour < Day.HOURS * DAYS) {
      days.get(whichDay(sleepHour)).setSleep(sleepHour % Day.HOURS);
      sleepHour += sleepPerNight;
      days.get(whichDay(sleepHour)).setWake(sleepHour % Day.HOURS);
      sleepHour += hoursAwake;
    }

    for (Day day : days) {
      if (!day.getName().equals(first)) {
        day.update();
      }
    }
  }

  /**
   * Calculate which day of a week a given hour belongs to.
   *
   * @param weekHour  the hour of the week
   * @return          the day, with Sunday == 0
   */
  private int whichDay(int weekHour) {
    weekHour %= Day.HOURS * DAYS;

    int i = 0;

    while (weekHour > Day.HOURS) {
      weekHour -= Day.HOURS;
      i++;
    }

    return i;
  }

  private int sleepPerNight(int sleep, int wake) {
    if (sleep < wake) {
      return wake - sleep;
    }

    return wake + (Day.HOURS - sleep);
  }

  /**
   * Set the sleep time for Sunday.
   *
   * @param sleep time to go to sleep
   * @param wake  time to wake up
   */
  public void setFirstDay(int sleep, int wake, int shift) {
    days.get(0).setSleepHours(sleep, wake);
    setWeek(sleepPerNight(sleep, wake), shift);
  }
}
