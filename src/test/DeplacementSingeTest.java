package test;
import org.junit.Before;
import org.junit.Test;

import tinymonkeys.modele.Ile;
import tinymonkeys.modele.SingeErratique;
import utils.Constantes;

/**
 * Classe de test visant à tester le déplacement des singes erratiques.
 * @author Guillaume
 *
 */
public class DeplacementSingeTest extends junit.framework.TestCase
{
	
	/**
	 * singe ératique.
	 */
	private SingeErratique singe;
	
	/**
	 * l'île aux singes.
	 * Non bouchonnée
	 */
	private Ile monkeyIsland;
	
	/**
	 * Compteur pour savoir combien de fois le singe s'est déplacé sur la case en dessous.
	 */
	private int compteurBas = 0;
	
	/**
	 * Compteur pour savoir combien de fois le singe s'est déplacé sur la case en haut.
	 */
	private int compteurHaut = 0;
	
	/**
	 * Compteur pour savoir combien de fois le singe s'est déplacé sur la case de gauche.
	 */
	private int compteurGauche = 0;
	
	/**
	 * Compteur pour savoir combien de fois le singe s'est déplacé sur la case en dessous.
	 */
	private int compteurDroite = 0;
		
	/**
	 * Set up.
	 */
	@Before
	public void setUp()
	{
		final int tailleCarte = 3;		
		this.monkeyIsland = new Ile();
		int[][] carte = new int[tailleCarte][tailleCarte];
		// On initialise la carte en TERRE
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = Constantes.TERRE;
			}
		}
		this.monkeyIsland.creationCarte(carte);
		//Création Pirate
		this.monkeyIsland.ajoutPirate("Jack");
		this.monkeyIsland.getPirate().positionInitiale(0, 0);// Le pirate est créé en dehors du voisinage du singe
		
		//Création Singe à tester
		this.singe = new SingeErratique(1,1,this.monkeyIsland);	
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(this.singe);		
	}
	
	/**
	 * Test déplacement singe : aucun obstacle.
	 */
	@Test
	public void test4CheminsPossibles()
	{		
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
		
		//Execution de la méthode à tester
		this.singe.deplacerSinge();
				
		int positionXFinale = this.singe.getX();
		int positionYFinale = this.singe.getY();
		
		boolean testX = positionXInit+1 == positionXFinale || positionXInit-1 == positionXFinale;
		boolean testY =	positionYInit+1 == positionYFinale || positionYInit-1 == positionYFinale;
		boolean test = testX ^ testY; // Deplacement mais pas en diagonale
		
		//Assertions
		//Verification que les coordonnees ont changées
		assertTrue(Constantes.ERREUR_DEPLACEMENT+Constantes.X+positionXInit+Constantes.FLECHE+positionXFinale+Constantes.VIRGULE+Constantes.Y+positionYInit+Constantes.FLECHE+positionYFinale, test );
	}
	
	/**
	 * Test déplacement singe : un obstacle en dessous (la mer).
	 */
	@Test
	public void test3CheminsPossibles()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
		
		//Ajout des obstacles
		final int dessousX = 1;
		final int dessousY = 0;
		
		//Ajout des obstacles
		// On récupère la carte de l'île
		int[][] carte = this.monkeyIsland.getCarte();
		//On ajoute la MER en dessous de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//Execution de la méthode à tester
		this.singe.deplacerSinge();
				
		int positionXFinale = this.singe.getX();
		int positionYFinale = this.singe.getY();	
		
		boolean testX = positionXInit+1 == positionXFinale || positionXInit-1 == positionXFinale;
		boolean testY =	positionYInit+1 == positionYFinale || positionYInit-1 == positionYFinale;
		boolean testDepl = testX ^ testY; // Deplacement mais pas en diagonale
		
		boolean testInterdit = positionYFinale == positionYInit-1 ;
		
		//Assertions
		//Verification que les coordonnees ont changées
		assertTrue(Constantes.ERREUR_DEPLACEMENT+Constantes.X+positionXInit+Constantes.FLECHE+positionXFinale+Constantes.VIRGULE+Constantes.Y+positionYInit+Constantes.FLECHE+positionYFinale, testDepl );
		assertFalse(Constantes.DEPLACEMENT_INTERDIT+Constantes.X+positionXInit+Constantes.FLECHE+positionXFinale+Constantes.Y+positionYInit+Constantes.FLECHE+positionYFinale, testInterdit);
	}
	
	/**
	 * Test déplacement singe : un obstacle en dessous et un à gauche.
	 */
	@Test
	public void test2CheminsPossibles()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
		
		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		
		//Ajout des obstacles
		// On récupère la carte de l'île
		int[][] carte = this.monkeyIsland.getCarte();
		//On ajoute la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//On ajoute la MER à gauche de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//Execution de la méthode à tester
		this.singe.deplacerSinge();
				
		int positionXFinale = this.singe.getX();
		int positionYFinale = this.singe.getY();	
		
		boolean testX = positionXInit+1 == positionXFinale || positionXInit-1 == positionXFinale;
		boolean testY =	positionYInit+1 == positionYFinale || positionYInit-1 == positionYFinale;
		boolean testDepl = testX ^ testY; // Deplacement mais pas en diagonale
		
		boolean testInterdit = positionYFinale == positionYInit-1 || positionXFinale == positionXInit-1 ;
		
		//Assertions
		//Verification que les coordonnees ont changées
		assertTrue(Constantes.ERREUR_DEPLACEMENT+Constantes.X+positionXInit+Constantes.FLECHE+positionXFinale+Constantes.VIRGULE+Constantes.Y+positionYInit+Constantes.FLECHE+positionYFinale, testDepl );
		assertFalse(Constantes.DEPLACEMENT_INTERDIT+Constantes.X+positionXInit+Constantes.FLECHE+positionXFinale+Constantes.VIRGULE+Constantes.Y+positionYInit+Constantes.FLECHE+positionYFinale, testInterdit);
	}
	
	/**
	 * Test déplacement singe : un obstacle en dessous et un à gauche et un en haut.
	 */
	@Test
	public void test1CheminPossible()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
	
		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		final int hautX = 1;
		final int hautY = 2;
		
		//Ajout des obstacles
		// On récupère la carte de l'île
		int[][] carte = this.monkeyIsland.getCarte();
		//On ajoute la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//On ajoute la MER à gauche de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//On ajoute un singe au dessus
		SingeErratique singe = new SingeErratique(hautX, hautY, this.monkeyIsland);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe);
		
		//Execution de la méthode à tester
		this.singe.deplacerSinge();
				
		int positionXFinale = this.singe.getX();
		int positionYFinale = this.singe.getY();	
		
		boolean testX = positionXInit+1 == positionXFinale || positionXInit-1 == positionXFinale;
		boolean testY =	positionYInit+1 == positionYFinale || positionYInit-1 == positionYFinale;
		boolean testDepl = testX ^ testY; // Deplacement mais pas en diagonale
		
		boolean testInterdit = positionYFinale == positionYInit-1 || positionXFinale == positionXInit-1 || positionYFinale == positionYInit+1;
		
		//Assertions
		//Verification que les coordonnees ont changées
		assertTrue(Constantes.ERREUR_DEPLACEMENT+Constantes.X+positionXInit+Constantes.FLECHE+positionXFinale+Constantes.VIRGULE+Constantes.Y+positionYInit+Constantes.FLECHE+positionYFinale, testDepl );
		assertFalse(Constantes.DEPLACEMENT_INTERDIT+Constantes.X+positionXInit+Constantes.FLECHE+positionXFinale+Constantes.VIRGULE+Constantes.Y+positionYInit+Constantes.FLECHE+positionYFinale, testInterdit);
	}
	
	/**
	 * Test déplacement singe : un obstacle tout autour du singe.
	 */
	@Test
	public void testAucunCheminPossible()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();

		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		final int hautX = 1;
		final int hautY = 2;
		final int droiteX = 2;
		final int droiteY = 1;
		
		//Ajout des obstacles
		// On récupère la carte de l'île
		int[][] carte = this.monkeyIsland.getCarte();
		//On ajoute la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//On ajoute la MER à gauche de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//On ajoute un singe au dessus et à droite
		SingeErratique singe = new SingeErratique(hautX, hautY, this.monkeyIsland);
		SingeErratique singe2 = new SingeErratique(droiteX,droiteY, this.monkeyIsland);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe2);

		//Execution de la méthode à tester
		this.singe.deplacerSinge();
				
		int positionXFinale = this.singe.getX();
		int positionYFinale = this.singe.getY();	
		
		boolean testX = positionXInit+1 == positionXFinale || positionXInit-1 == positionXFinale;
		boolean testY =	positionYInit+1 == positionYFinale || positionYInit-1 == positionYFinale;
		boolean testDepl = testX || testY; // Deplacement quelconque n'est pas toléré
		
		boolean testInterdit = positionYFinale == positionYInit-1 || positionXFinale == positionXInit-1 || positionYFinale == positionYInit+1 || positionXFinale == positionXInit+1;
		boolean test = testInterdit || testDepl;
		
		//Assertions
		//Verification que les coordonnees n'ont pas changées
		assertFalse(Constantes.DEPLACEMENT_INTERDIT+Constantes.X+positionXInit+Constantes.FLECHE+positionXFinale+Constantes.VIRGULE+Constantes.Y+positionYInit+Constantes.FLECHE+positionYFinale, test);
	}
	
	// TEST EQUIPROBABILITE --- Autant de chance d'aller dans chaque direction lorsque celle-ci est disponible 
	
	/**
	 * Test équiprobabilité déplacement singe : aucun obstacle.
	 */
	@Test
	public void testEquiProb4CheminsPossibles()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
		this.compteurBas = 0;
		this.compteurDroite = 0;
		this.compteurGauche = 0;
		this.compteurHaut = 0;		
		
		final int nbRepetitionAction = 10000;
		//On répète le déplacement pour évaluer l'équiprobabilité
		for (int i = 0; i < nbRepetitionAction; i++) {
			// On repositionne le singe au centre de la map (case de test)
			this.monkeyIsland.getSingesErratiques().getSingesErratiques().firstElement().setPosition(1, 1);
			
			//Execution de la méthode à tester
			this.singe.deplacerSinge();
					
			int positionXFinale = this.singe.getX();
			int positionYFinale = this.singe.getY();	
			
			if(positionXFinale == positionXInit+1){
				this.compteurDroite++;
			}else if(positionXFinale == positionXInit-1){
				this.compteurGauche++;
			}else if(positionYFinale == positionYInit-1){
				this.compteurBas++;
			}else if(positionYFinale == positionYInit+1){
				this.compteurHaut++;
			}
		}

		// 2% de marge d'erreur
		final int limiteBasse = 2300;
		final int limiteHaute = 2700;
		
		//4 déplacements : 25% de chance pour chaque =~ 2500 mouvements
		boolean testBas = limiteBasse<this.compteurBas && this.compteurBas<limiteHaute;  
		boolean testHaut = limiteBasse<this.compteurHaut && this.compteurHaut<limiteHaute;
		boolean testGauche = limiteBasse<this.compteurGauche && this.compteurGauche<limiteHaute;  
		boolean testDroite = limiteBasse<this.compteurDroite && this.compteurDroite<limiteHaute;
		boolean test = testBas && testHaut && testGauche & testDroite;
		
		//Assertions
		// Vérification qu'il y a bien autant de chance de se déplacer dans les cases disponibles
		assertTrue(Constantes.DEPLACEMENT_NON_EQUIPROBABLE+Constantes.COMPTEUR_BAS+this.compteurBas+Constantes.COMPTEUR_HAUT+this.compteurHaut+Constantes.COMPTEUR_GAUCHE+this.compteurGauche+Constantes.COMPTEUR_DROITE+this.compteurDroite, test );
	}
	
	/**
	 * Test équiprobabilité déplacement singe : un obstacle en dessous.
	 */
	@Test
	public void testEquiProb3CheminsPossibles()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
		this.compteurBas = 0;
		this.compteurDroite = 0;
		this.compteurGauche = 0;
		this.compteurHaut = 0;		
		
		//Ajout des obstacles
		final int dessousX = 1;
		final int dessousY = 0;
		// On récupère la carte de l'île
		int[][] carte = this.monkeyIsland.getCarte();
		//On ajoute la MER en dessous de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		final int nbRepetitionAction = 10000;
		//On répète le déplacement pour évaluer l'équiprobabilité
		for (int i = 0; i < nbRepetitionAction; i++) {		
			// On repositionne le singe au centre de la map (case de test)
			this.monkeyIsland.getSingesErratiques().getSingesErratiques().firstElement().setPosition(1, 1);
			
			//Execution de la méthode à tester
			this.singe.deplacerSinge();
					
			int positionXFinale = this.singe.getX();
			int positionYFinale = this.singe.getY();	
			
			if(positionXFinale == positionXInit+1){
				this.compteurDroite++;
			}else if(positionXFinale == positionXInit-1){
				this.compteurGauche++;
			}else if(positionYFinale == positionYInit-1){
				this.compteurBas++;
			}else if(positionYFinale == positionYInit+1){
				this.compteurHaut++;
			}
		}
		
		// 2% de marge d'erreur
		final int limiteBasse = 3100;
		final int limiteHaute = 3500;
		
		//3 déplacements : 33% de chance pour chaque =~ 3300 mouvements
		boolean testBas = this.compteurBas == 0 ;  
		boolean testHaut = limiteBasse<this.compteurHaut && this.compteurHaut<limiteHaute;
		boolean testGauche = limiteBasse<this.compteurGauche && this.compteurGauche<limiteHaute;  
		boolean testDroite = limiteBasse<this.compteurDroite && this.compteurDroite<limiteHaute;
		boolean test = testBas && testHaut && testGauche & testDroite;
		
		//Assertions
		// Vérification qu'il y a bien autant de chance de se déplacer dans les cases disponibles
		assertTrue(Constantes.DEPLACEMENT_NON_EQUIPROBABLE+Constantes.COMPTEUR_BAS+this.compteurBas+Constantes.COMPTEUR_HAUT+this.compteurHaut+Constantes.COMPTEUR_GAUCHE+this.compteurGauche+Constantes.COMPTEUR_DROITE+this.compteurDroite, test );	
	}
	
	/**
	 * Test équiprobabilité déplacement singe : un obstacle en dessous et à gauche .
	 */
	@Test
	public void testEquiProb2CheminsPossibles()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
		this.compteurBas = 0;
		this.compteurDroite = 0;
		this.compteurGauche = 0;
		this.compteurHaut = 0;		
		
		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		
		//Ajout des obstacles
		// On récupère la carte de l'île
		int[][] carte = this.monkeyIsland.getCarte();
		//On ajoute la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//On ajoute la MER à gauche de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		final int nbRepetitionAction = 10000;
		//On répète le déplacement pour évaluer l'équiprobabilité
		for (int i = 0; i < nbRepetitionAction; i++) {		
			// On repositionne le singe au centre de la map (case de test)
			this.monkeyIsland.getSingesErratiques().getSingesErratiques().firstElement().setPosition(1, 1);
			
			//Execution de la méthode à tester
			this.singe.deplacerSinge();
					
			int positionXFinale = this.singe.getX();
			int positionYFinale = this.singe.getY();	
			
			if(positionXFinale == positionXInit+1){
				this.compteurDroite++;
			}else if(positionXFinale == positionXInit-1){
				this.compteurGauche++;
			}else if(positionYFinale == positionYInit-1){
				this.compteurBas++;
			}else if(positionYFinale == positionYInit+1){
				this.compteurHaut++;
			}
		}
		
		// 2% de marge d'erreur
		final int limiteBasse = 4800;
		final int limiteHaute = 5200;
		
		//2 déplacements : 50% de chance pour chaque =~ 5000 mouvements
		boolean testBas = this.compteurBas == 0 ;  
		boolean testHaut = limiteBasse<this.compteurHaut && this.compteurHaut<limiteHaute;
		boolean testGauche = this.compteurGauche == 0;  
		boolean testDroite = limiteBasse<this.compteurDroite && this.compteurDroite<limiteHaute;
		boolean test = testBas && testHaut && testGauche & testDroite;
		
		//Assertions
		// Vérification qu'il y a bien autant de chance de se déplacer dans les cases disponibles
		assertTrue(Constantes.DEPLACEMENT_NON_EQUIPROBABLE+Constantes.COMPTEUR_BAS+this.compteurBas+Constantes.COMPTEUR_HAUT+this.compteurHaut+Constantes.COMPTEUR_GAUCHE+this.compteurGauche+Constantes.COMPTEUR_DROITE+this.compteurDroite, test );
	}
	
	/**
	 * Test équiprobabilité déplacement singe : un obstacle en dessous et à gauche et en haut.
	 */
	@Test
	public void testEquiProb1CheminsPossibles()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
		this.compteurBas = 0;
		this.compteurDroite = 0;
		this.compteurGauche = 0;
		this.compteurHaut = 0;		
		
		final int nbRepetitionAction = 10000;

		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		final int hautX = 1;
		final int hautY = 2;
		
		//Ajout des obstacles
		// On récupère la carte de l'île
		int[][] carte = this.monkeyIsland.getCarte();
		//On ajoute la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//On ajoute la MER à gauche de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//On ajoute un singe au dessus
		SingeErratique singe = new SingeErratique(hautX, hautY, this.monkeyIsland);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe);
		
		//On répète le déplacement pour évaluer l'équiprobabilité
		for (int i = 0; i < nbRepetitionAction; i++) {
			// On repositionne le singe au centre de la map (case de test)
			this.monkeyIsland.getSingesErratiques().getSingesErratiques().firstElement().setPosition(1, 1);
			
			//Execution de la méthode à tester
			this.singe.deplacerSinge();
					
			int positionXFinale = this.singe.getX();
			int positionYFinale = this.singe.getY();	
			
			if(positionXFinale == positionXInit+1){
				this.compteurDroite++;
			}else if(positionXFinale == positionXInit-1){
				this.compteurGauche++;
			}else if(positionYFinale == positionYInit-1){
				this.compteurBas++;
			}else if(positionYFinale == positionYInit+1){
				this.compteurHaut++;
			}
		}
		
		// 0% de marge d'erreur
		//1 déplacement : 100% de chance pour chaque = 10000 mouvements
		boolean testBas = this.compteurBas == 0 ;  
		boolean testHaut = this.compteurHaut == 0;
		boolean testGauche = this.compteurGauche == 0;  
		boolean testDroite = this.compteurDroite == nbRepetitionAction;
		boolean test = testBas && testHaut && testGauche & testDroite;
		
		//Assertions
		// Vérification qu'il y a bien autant de chance de se déplacer dans les cases disponibles
		assertTrue(Constantes.DEPLACEMENT_NON_EQUIPROBABLE+Constantes.COMPTEUR_BAS+this.compteurBas+Constantes.COMPTEUR_HAUT+this.compteurHaut+Constantes.COMPTEUR_GAUCHE+this.compteurGauche+Constantes.COMPTEUR_DROITE+this.compteurDroite, test );
	}
	
	/**
	 * Test équiprobabilité déplacement singe : des obstacles tout autour du singe.
	 */
	@Test
	public void testEquiProbAucunCheminPossible()
	{
		int positionXInit = this.singe.getX();
		int positionYInit  = this.singe.getY();
		this.compteurBas = 0;
		this.compteurDroite = 0;
		this.compteurGauche = 0;
		this.compteurHaut = 0;		
		
		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		final int hautX = 1;
		final int hautY = 2;
		final int droiteX = 2;
		final int droiteY = 1;
		
		//Ajout des obstacles
		// On récupère la carte de l'île
		int[][] carte = this.monkeyIsland.getCarte();
		//On ajoute la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//On ajoute la MER à gauche de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//On ajoute un singe au dessus et à droite
		SingeErratique singe = new SingeErratique(hautX, hautY, this.monkeyIsland);
		SingeErratique singe2 = new SingeErratique(droiteX,droiteY, this.monkeyIsland);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe2);
		
		final int nbRepetitionAction = 10000;
		//On répète le déplacement pour évaluer l'équiprobabilité
		for (int i = 0; i < nbRepetitionAction; i++) {
			// On repositionne le singe au centre de la map (case de test)
			this.monkeyIsland.getSingesErratiques().getSingesErratiques().firstElement().setPosition(1, 1);
			
			//Execution de la méthode à tester
			this.singe.deplacerSinge();
					
			int positionXFinale = this.singe.getX();
			int positionYFinale = this.singe.getY();	
			
			if(positionXFinale == positionXInit+1){
				this.compteurDroite++;
			}else if(positionXFinale == positionXInit-1){
				this.compteurGauche++;
			}else if(positionYFinale == positionYInit-1){
				this.compteurBas++;
			}else if(positionYFinale == positionYInit+1){
				this.compteurHaut++;
			}
		}
		
		// 0% de marge d'erreur
		//aucun déplacement : 0% de chance pour chaque = 0 mouvement
		boolean testBas = this.compteurBas == 0 ;  
		boolean testHaut = this.compteurHaut == 0;
		boolean testGauche = this.compteurGauche == 0;  
		boolean testDroite = this.compteurDroite == 0;
		boolean test = testBas && testHaut && testGauche & testDroite;
		
		//Assertions
		// Vérification qu'il y a bien autant de chance de se déplacer dans les cases disponibles
		assertTrue(Constantes.DEPLACEMENT_NON_EQUIPROBABLE+Constantes.COMPTEUR_BAS+this.compteurBas+Constantes.COMPTEUR_HAUT+this.compteurHaut+Constantes.COMPTEUR_GAUCHE+this.compteurGauche+Constantes.COMPTEUR_DROITE+this.compteurDroite, test );	
	}
}
