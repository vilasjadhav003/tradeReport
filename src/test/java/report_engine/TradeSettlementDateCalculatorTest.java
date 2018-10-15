package report_engine;

import report_engine.instruction.Instruction;
import report_engine.instruction.PriceDetails;
import report_engine.instruction.TradeAction;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TradeSettlementDateCalculatorTest {
    @Test
    public void calculateSettlementDate_default_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 9, 7); // Its a Friday

        final Instruction demoInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 5),
                initialSettlementDate,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        TradeSettlementDateCalculator.calculateSettlementDate(demoInstruction);

        // should be the same
        assertEquals(initialSettlementDate, demoInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_default_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 9, 9); // Its a Sunday

        final Instruction demoInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 10),
                initialSettlementDate,
                new PriceDetails(
                        Currency.getInstance("SGD"),
                        BigDecimal.valueOf(1),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        TradeSettlementDateCalculator.calculateSettlementDate(demoInstruction);

       
        assertEquals(LocalDate.of(2018, 9, 10), demoInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Friday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 9, 7); // Its a Friday

        final Instruction demoInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 10),
                initialSettlementDate,
                new PriceDetails(
                        Currency.getInstance("AED"), // Its Arabia (AED)
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        TradeSettlementDateCalculator.calculateSettlementDate(demoInstruction);

        
        assertEquals(LocalDate.of(2018, 9, 9), demoInstruction.getSettlementDate());
    }

    @Test
    public void calculateSettlementDate_arabia_Sunday() throws Exception {
        final LocalDate initialSettlementDate = LocalDate.of(2018, 9, 9); // Its a Sunday

        final Instruction demoInstruction = new Instruction(
                "E1",
                TradeAction.BUY,
                LocalDate.of(2018, 9, 10),
                initialSettlementDate,
                new PriceDetails(
                        Currency.getInstance("SAR"), // Its Arabia (SAR)
                        BigDecimal.valueOf(0.50),
                        200,
                        BigDecimal.valueOf(100.25)));

        // calculate new settlement day
        TradeSettlementDateCalculator.calculateSettlementDate(demoInstruction);

        // should be the same
        assertEquals(initialSettlementDate, demoInstruction.getSettlementDate());
    }
}