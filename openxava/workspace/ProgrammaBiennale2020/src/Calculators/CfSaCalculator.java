package Calculators;

import org.openxava.calculators.*; // To use ICalculator
import util.*; // To use ProgrammaBiennale Preferences

public class CfSaCalculator implements ICalculator {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object calculate() throws Exception {
		return ProgrammaBiennalePreferences.getDefaultCfSA();
    }
}