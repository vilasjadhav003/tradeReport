package JPMC_TEST.trade_report.utils;

import report_engine.instruction.Instruction;
import report_engine.instruction.PriceDetails;
import report_engine.instruction.TradeAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

public class DemoInstructionsGenerator {
	public static Set<Instruction> getDemoInstructions() {
		return new HashSet<>(Arrays.asList(

				new Instruction("foo", TradeAction.BUY, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 20),
						new PriceDetails(Currency.getInstance("SGD"), BigDecimal.valueOf(0.50), 200,
								BigDecimal.valueOf(100.25))),

				new Instruction("bar", TradeAction.BUY, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 19),
						new PriceDetails(Currency.getInstance("AED"), BigDecimal.valueOf(0.22), 450,
								BigDecimal.valueOf(150.5))),

				new Instruction("E9", TradeAction.SELL, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 18),
						new PriceDetails(Currency.getInstance("SAR"), BigDecimal.valueOf(0.27), 150,
								BigDecimal.valueOf(400.8))),

				new Instruction("E4", TradeAction.SELL, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new PriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.94), 50,
								BigDecimal.valueOf(500.6))),

				new Instruction("E5", TradeAction.BUY, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new PriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.94), 20,
								BigDecimal.valueOf(40.6))),

				new Instruction("E6", TradeAction.BUY, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new PriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.94), 20,
								BigDecimal.valueOf(40.6))),

				new Instruction("E7", TradeAction.SELL, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new PriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.94), 1000,
								BigDecimal.valueOf(160.6))),

				new Instruction("E8", TradeAction.SELL, LocalDate.of(2018, 9, 10), LocalDate.of(2018, 9, 21),
						new PriceDetails(Currency.getInstance("EUR"), BigDecimal.valueOf(0.94), 120,
								BigDecimal.valueOf(500.6)))));
	}
}
