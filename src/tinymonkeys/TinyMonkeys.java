package tinymonkeys;

import tinymonkeys.controleur.Controleur;

/**
 * Classe principale de TinyMonkeys.
 * 
 * @version 1.0
 * @author Guillaume
 *
 */
final public class TinyMonkeys 
{

	/**
	 * Constructeur privÃ© de TinyMonkeys.
	 * 
	 * Ce constructeur privÃ© assure la non-instanciation de TinyMonkeys dans un programme.
	 * (TinyMonkeys est la classe principale du programme TinyMonkeys)
	 */
	private TinyMonkeys() 
	{
		// Constructeur privÃ© pour assurer la non-instanciation de TinyMonkeys.
		
	}
	
	/**
	 * Main du programme.
	 * 
	 * @param args arguments.
	 */
	public static void main(String[] args)
	{
		final Controleur controleur = new Controleur();
		controleur.lanceEvolutionsPersonnages();
	}
	
}
