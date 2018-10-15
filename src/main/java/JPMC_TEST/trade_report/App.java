package JPMC_TEST.trade_report;

import java.util.Set;

import JPMC_TEST.trade_report.utils.DemoInstructionsGenerator;
import report_engine.IReportGenerator;
import report_engine.instruction.Instruction;
import report_engine.ReportGenerator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 final Set<Instruction> instructions = DemoInstructionsGenerator.getDemoInstructions();
         final IReportGenerator reportGenerator = new ReportGenerator();

         System.out.println(reportGenerator.generateInstructionsReport(instructions));
    }
}
