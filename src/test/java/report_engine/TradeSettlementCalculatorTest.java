package report_engine;

import report_engine.instruction.Instruction;
import report_engine.instruction.PriceDetails;
import report_engine.instruction.TradeAction;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class TradeSettlementCalculatorTest {

    private static final LocalDate MONDAY    = LocalDate.of(2018, 9, 10);
    private static final LocalDate TUESDAY   = LocalDate.of(2018, 9, 11);
    private static final LocalDate WEDNESDAY = LocalDate.of(2018, 9, 12);
    private static final LocalDate SATURDAY  = LocalDate.of(2018, 9, 15);
    private static final LocalDate SUNDAY    = LocalDate.of(2018, 9, 16);

    private static Set<Instruction> getDemoSetOfInstructions() {
        final Set<Instruction> instructions = new HashSet<>();

       
        instructions.add(new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 10),
                MONDAY,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        100,
                        BigDecimal.valueOf(1))));

        instructions.add(new Instruction(
                "E2",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 10),
                MONDAY,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(1))));

        instructions.add(new Instruction(
                "E3",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 10),
                SATURDAY,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        300,
                        BigDecimal.valueOf(1))));

        instructions.add(new Instruction(
                "E4",
                TradeAction.SELL,
                LocalDate.of(2018, 9, 10),
                SUNDAY,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(1))));

        
        instructions.add(new Instruction(
                "E5",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 10),
                TUESDAY,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        400,
                        BigDecimal.valueOf(1))));

        instructions.add(new Instruction(
                "E6",
                TradeAction.SELL,
                LocalDate.of(2018, 9, 10),
                TUESDAY,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        1000,
                        BigDecimal.valueOf(1))));

        
        instructions.add(new Instruction(
                "E7",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 10),
                WEDNESDAY,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        7000,
                        BigDecimal.valueOf(1))));

        TradeSettlementDateCalculator.calculateSettlementDates(instructions);

        return instructions;
    }

    @Test
    public void testDailyIncomingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyIncomingAmount =
                TradeSettlementCalculator.calculateDailyIncomingAmount(getDemoSetOfInstructions());

        assertEquals(2, dailyIncomingAmount.size());
        assertTrue(Objects.equals(dailyIncomingAmount.get(MONDAY), BigDecimal.valueOf(200.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyIncomingAmount.get(TUESDAY), BigDecimal.valueOf(1000.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyOutgoingAmount() throws Exception {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                TradeSettlementCalculator.calculateDailyOutgoingAmount(getDemoSetOfInstructions());

        assertEquals(3, dailyOutgoingAmount.size());
        assertTrue(Objects.equals(dailyOutgoingAmount.get(MONDAY), BigDecimal.valueOf(600.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
        assertTrue(Objects.equals(dailyOutgoingAmount.get(TUESDAY), BigDecimal.valueOf(400.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
//        assertTrue(Objects.equals(dailyOutgoingAmount.get(WEDNESDAY), BigDecimal.valueOf(700.00).setScale(2, BigDecimal.ROUND_HALF_EVEN)));
    }

    @Test
    public void testDailyIncomingRanking() throws Exception {
        final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking =
                TradeSettlementCalculator.calculateDailyIncomingRanking(getDemoSetOfInstructions());

        assertEquals(2, dailyIncomingRanking.size());

        assertEquals(1, dailyIncomingRanking.get(MONDAY).size());
        assertEquals(1, dailyIncomingRanking.get(TUESDAY).size());

        assertTrue(dailyIncomingRanking.get(MONDAY).contains(new Rank(1, "E4", MONDAY)));
        assertTrue(dailyIncomingRanking.get(TUESDAY).contains(new Rank(1, "E6", TUESDAY)));

    }

    @Test
    public void testDailyOutgoingRanking() throws Exception {
        final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking =
                TradeSettlementCalculator.calculateDailyOutgoingRanking(getDemoSetOfInstructions());

        assertEquals(3, dailyOutgoingRanking.size());

        assertEquals(3, dailyOutgoingRanking.get(MONDAY).size());
        assertEquals(1, dailyOutgoingRanking.get(TUESDAY).size());
        assertEquals(1, dailyOutgoingRanking.get(WEDNESDAY).size());

        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(1, "E3", MONDAY)));
        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(2, "E2", MONDAY)));
        assertTrue(dailyOutgoingRanking.get(MONDAY).contains(new Rank(3, "E1", MONDAY)));

        assertTrue(dailyOutgoingRanking.get(TUESDAY).contains(new Rank(1, "E5", TUESDAY)));

        assertTrue(dailyOutgoingRanking.get(WEDNESDAY).contains(new Rank(1, "E7", WEDNESDAY)));
    }
}