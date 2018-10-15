package report_engine.working_days;

import java.time.LocalDate;

public interface IWorkingDays {
    LocalDate findFirstWorkingDate(LocalDate date);
}
