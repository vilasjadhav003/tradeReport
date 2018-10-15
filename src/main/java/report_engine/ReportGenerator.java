package report_engine;


import report_engine.TradeSettlementDateCalculator;
import report_engine.TradeSettlementCalculator;

import report_engine.instruction.Instruction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class ReportGenerator implements IReportGenerator {
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public String generateInstructionsReport(Set<Instruction> instructions) {
        // first calculate the correct settlement dates
        TradeSettlementDateCalculator.calculateSettlementDates(instructions);

        // Build the report string
        return generateDailyOutgoingRanking(instructions,
                generateDailyIncomingRanking(instructions,
                generateDailyIncomingAmount(instructions,
                generateDailyOutgoingAmount(instructions, stringBuilder))))
            .toString();
    }

    private static StringBuilder generateDailyOutgoingAmount(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                TradeSettlementCalculator.calculateDailyOutgoingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingAmount(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, BigDecimal> dailyOutgoingAmount =
                TradeSettlementCalculator.calculateDailyIncomingAmount(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Amount          \n")
                .append("----------------------------------------\n")
                .append("      Date       |    Trade Amount      \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingAmount.keySet()) {
            stringBuilder
                    .append(date).append("       |      ").append(dailyOutgoingAmount.get(date)).append("\n");
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyOutgoingRanking(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Rank>> dailyOutgoingRanking =
                TradeSettlementCalculator.calculateDailyOutgoingRanking(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Outgoing Daily Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyOutgoingRanking.keySet()) {
            for (Rank rank : dailyOutgoingRanking.get(date)) {
                stringBuilder
                    .append(date).append("   |      ")
                    .append(rank.getRank()).append("     |    ")
                    .append(rank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }

    private static StringBuilder generateDailyIncomingRanking(Set<Instruction> instructions, StringBuilder stringBuilder) {
        final Map<LocalDate, LinkedList<Rank>> dailyIncomingRanking =
                TradeSettlementCalculator.calculateDailyIncomingRanking(instructions);

        stringBuilder
                .append("\n----------------------------------------\n")
                .append("         Incoming Daily Ranking          \n")
                .append("----------------------------------------\n")
                .append("     Date    |     Rank   |   Entity     \n")
                .append("----------------------------------------\n");

        for (LocalDate date : dailyIncomingRanking.keySet()) {
            for (Rank rank : dailyIncomingRanking.get(date)) {
                stringBuilder
                        .append(date).append("   |      ")
                        .append(rank.getRank()).append("     |    ")
                        .append(rank.getEntity()).append("\n");
            }
        }

        return stringBuilder;
    }
}

