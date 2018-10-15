package report_engine.working_days;

import java.time.DayOfWeek;

public class ArabWorkingDays extends WorkingDays {

    private static ArabWorkingDays instance = null;

    public static ArabWorkingDays getInstance() {
        if (instance == null) {
            instance = new ArabWorkingDays();
        }
        return instance;
    }

    private ArabWorkingDays() {
        super();
    }

    @Override
    protected void setupWorkingDays() {
        this.isWorkingDayMap.put(DayOfWeek.SUNDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.MONDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.TUESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.WEDNESDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.THURSDAY, true);
        this.isWorkingDayMap.put(DayOfWeek.FRIDAY, false); // in arabia those are not working days
        this.isWorkingDayMap.put(DayOfWeek.SATURDAY, false); // in arabia those are not working days
    }
}
