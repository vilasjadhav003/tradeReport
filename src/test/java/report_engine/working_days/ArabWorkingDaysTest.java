package report_engine.working_days;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ArabWorkingDaysTest {
    private IWorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = ArabWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2018, 9, 2);

        assertEquals(aSunday, workingDays.findFirstWorkingDate(aSunday));
    }

    @Test
    public void testFindFirstWorkingDate_Thursday() throws Exception {
        final LocalDate aThursday = LocalDate.of(2018, 9, 4);

        assertEquals(aThursday, workingDays.findFirstWorkingDate(aThursday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2018, 9, 7);

        
        assertEquals(LocalDate.of(2018, 9, 7), workingDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2018, 9, 8);

        
        assertEquals(LocalDate.of(2018, 9, 8), workingDays.findFirstWorkingDate(aSaturday));
    }

}