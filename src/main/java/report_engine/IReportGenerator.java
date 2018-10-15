package report_engine;

import report_engine.instruction.Instruction;

import java.util.Set;

public interface IReportGenerator {
    String generateInstructionsReport(Set<Instruction> instructions);
}
