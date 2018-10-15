package report_engine.working_days;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class DefaultWorkingDaysTest {

    private IWorkingDays workingDays;
    @Before
    public void setUp() throws Exception {
        workingDays = DefaultWorkingDays.getInstance();
    }

    @Test
    public void testFindFirstWorkingDate_Monday() throws Exception {
        final LocalDate aMonday = LocalDate.of(2018, 9, 3);

        // should return the same, since Monday is a working by default
        assertEquals(aMonday, workingDays.findFirstWorkingDate(aMonday));
    }

    @Test
    public void testFindFirstWorkingDate_Friday() throws Exception {
        final LocalDate aFriday = LocalDate.of(2018, 9, 7);

        // should return the same, since Friday is a working by default
        assertEquals(aFriday, workingDays.findFirstWorkingDate(aFriday));
    }

    @Test
    public void testFindFirstWorkingDate_Saturday() throws Exception {
        final LocalDate aSaturday = LocalDate.of(2018, 9, 8);

       
        assertEquals(LocalDate.of(2018, 9, 8), workingDays.findFirstWorkingDate(aSaturday));
    }

    @Test
    public void testFindFirstWorkingDate_Sunday() throws Exception {
        final LocalDate aSunday = LocalDate.of(2018, 9, 9);

        
        assertEquals(LocalDate.of(2018, 9, 9), workingDays.findFirstWorkingDate(aSunday));
    }
}