# SleepShiftPlanner
### A tool to plan when to be asleep for a week of sleep schedule fixing.  

## Explanation
Delated Sleep Phase Syndrome causes a sleep schedule that is several hours
later than average, but still requires a "normal" amount of sleep.
The easiest way to get on a more diurnal schedule is to go to bed later each
"night" until you go around the clock and get back to the desired schedule.
Typically this is done with a shift of three hours per night for a week.

## How to use
Execution begins in the main. You can indicate which day to start on,
expected sleep hours on the first day, and how many hours to shift by
each day. For best results, start sleep on the first day at or after midnight.

The program will calculate how many hours of sleep per night and generate
a schedule to show approximate sleep hours each day of the shift week.

Optionally, the schedule can be written to a file with the `ToFile` class.
