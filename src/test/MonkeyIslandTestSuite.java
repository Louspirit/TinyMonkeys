package test;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Classe de suite de tests unitaire pour TinyMonkey.
 *
 * @version 1.0
 * @author Guillaume
 *
 */
public class MonkeyIslandTestSuite
{

	/**
	 * Suite de tests pour TinyMonkey.
	 *
	 * @return la suite de tests.
	 *
	 * @see junit.framework.TestSuite
	 *
	 */	
	public static Test suite() 
	{
		Class<?>[] classesTest = {
				// Les classes qui seront testées.
				IleTest.class,
				DeplacementSingeMockTest.class ,		
				DeplacementSingeTest.class,
		};

		TestSuite suite = new TestSuite(classesTest);

		return suite;
	}

}

