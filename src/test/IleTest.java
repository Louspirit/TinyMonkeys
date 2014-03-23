package test;

import java.util.ArrayList;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import tinymonkeys.modele.Ile;
import tinymonkeys.modele.SingeErratique;
import utils.Constantes;

/**
 * Classe de test visant à tester les méthodes de l'ile des singes.
 * @author Guillaume
 *
 */
public class IleTest extends junit.framework.TestCase
{
	/**
	 * Singe erratique.
	 * On le bouchonne pour tester les méthodes de l'ile utilisant des singe erratiques.
	 */
	private SingeErratique singeMock;
	
	/**
	 * l'île que l'on désire testé.
	 */
	private Ile monkeyIsland;
	
	/**
	 * Set up.
	 */
	@Before
	public void setUp()
	{
		final int tailleCarte = 20;
		this.monkeyIsland = new Ile();
		int[][] carte = new int[tailleCarte][tailleCarte];
		// On initialise la carte en TERRE
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = Constantes.TERRE;
			}
		}
		this.monkeyIsland.creationCarte(carte);
		this.singeMock = EasyMock.createMock(SingeErratique.class);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().addElement(this.singeMock);
	}
	
	/**
	 * Test ile : singe est présent (sur une case).
	 * On utilise un singe mocké
	 */
	@Test
	public void testSingeEstPresentMock()
	{

		//Méthode coordonneesEgales() du singeErratique
		EasyMock.expect(
				this.singeMock.coordonneesEgales(0, 0)
				).andReturn(
						true
						);
		// on l'appelle 1 fois
		EasyMock.expectLastCall().times(1);
		
		//Méthode coordonneesEgales() du singeErratique
				EasyMock.expect(
						this.singeMock.coordonneesEgales(1, 1)
						).andReturn(
								false
								);
		// on l'appelle 1 fois
		EasyMock.expectLastCall().times(1);
				
		//Chargement du mock du client
		EasyMock.replay(this.singeMock);
		
		//Execution de la méthode à tester
		boolean testPresent = this.monkeyIsland.singeEstPresent(0, 0); // Position du singe mocké
		boolean testAbsent = this.monkeyIsland.singeEstPresent(1, 1); // Rien ici

		//Assertions
		//Verification que l'île détecte que le singe est bien présent
		assertTrue(Constantes.ERREUR_DETECTION_SINGE+Constantes.X+0+Constantes.VIRGULE+Constantes.Y+0, testPresent );
		//Verification que l'île détecte que la case ne contient pas de singe
		assertFalse(Constantes.ERREUR_DETECTION_SINGE+Constantes.X+1+Constantes.VIRGULE+Constantes.Y+1, testAbsent );
		//Vérification du comportement du Mock
		EasyMock.verify(this.singeMock);
	}	
	
	/**
	 * Test ile : singe est présent (sur une case).
	 * Sans bouchonnage
	 */
	@Test
	public void testSingeEstPresent()
	{
		final int x = 3;
		final int y = 3;
		SingeErratique singe = new SingeErratique(x,y,this.monkeyIsland);  
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().remove(this.singeMock);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe);
		
		//Execution de la méthode à tester
		boolean testPresent = this.monkeyIsland.singeEstPresent(x, y); // on vient d'y ajouter un singe
		boolean testAbsent = this.monkeyIsland.singeEstPresent(1, 1); // Il n'y a rien

		//Assertions
		//Verification que l'île détecte que le singe est bien présent
		assertTrue(Constantes.ERREUR_DETECTION_SINGE+Constantes.X+0+Constantes.VIRGULE+Constantes.Y+0, testPresent );
		//Verification que l'île détecte que la case ne contient pas de singe
		assertFalse(Constantes.ERREUR_DETECTION_SINGE+Constantes.X+1+Constantes.VIRGULE+Constantes.Y+1, testAbsent );
	}	
	
	/**
	 * Test ile : deplacementsPossible : pas d'obstacles.
	 * Transmet la liste des déplacements possibles autour d'une case en fonction des obstacles environnants
	 */
	@Test
	public void test4DeplacementsPossibles()
	{
		//On supprime tous les singes existants
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().clear();
		//Coordonnées de la case à tester (centre de la map)
		final int x = 10; 
		final int y = 10;
		
		//Execution de la méthode à tester
		ArrayList<Integer> liste = this.monkeyIsland.deplacementsPossibles(x, y);
		
		// Pas d'obstacle : les 4 directions sont autorisées  
		ArrayList<Integer> listeDeplacementTheorique = new ArrayList<Integer>();
		listeDeplacementTheorique.add(Constantes.GAUCHE);
		listeDeplacementTheorique.add(Constantes.DROITE);
		listeDeplacementTheorique.add(Constantes.HAUT);
		listeDeplacementTheorique.add(Constantes.BAS);
		
		boolean test = listeDeplacementTheorique.equals(liste);
		
		//Assertions
		//Verification que les posibilités retournées sont correctes
		assertTrue(Constantes.ERREUR_DETECTION_DEPLACEMENTS_P+Constantes.LISTE_DEPLACEMENTS+liste.toString()+Constantes.VIRGULE+Constantes.LISTE_DEPLACEMENTS_THEORIQUES+listeDeplacementTheorique.toString(), test );
	}
	
	/**
	 * Test ile : deplacementsPossible : de l'eau en dessous.
	 * Transmet la liste des déplacements possibles autour d'une case en fonction des obstacles environnants
	 */
	@Test
	public void test3DeplacementsPossibles()
	{
		//On supprime tous les singes existants
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().clear();
		
		final int tailleMap = 3;
		final int dessousX = 1;
		final int dessousY = 0;
		// On crée une carte en TERRE
		int[][] carte = new int[tailleMap][tailleMap]; 
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = Constantes.TERRE;
			}
		}
		//, avec de la MER en dessous de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//Coordonnées de la case à tester (centre de la map)
		final int x = 1; 
		final int y = 1;
		
		//Execution de la méthode à tester
		ArrayList<Integer> liste = this.monkeyIsland.deplacementsPossibles(x, y);
		
		// Pas d'obstacle : les 4 directions sont autorisées  
		ArrayList<Integer> listeDeplacementTheorique = new ArrayList<Integer>();
		listeDeplacementTheorique.add(Constantes.GAUCHE);
		listeDeplacementTheorique.add(Constantes.DROITE);
		listeDeplacementTheorique.add(Constantes.HAUT);
		
		boolean test = listeDeplacementTheorique.equals(liste);
		
		//Assertions
		//Verification que les posibilités retournées sont correctes
		assertTrue(Constantes.ERREUR_DETECTION_DEPLACEMENTS_P+Constantes.LISTE_DEPLACEMENTS+liste.toString()+Constantes.VIRGULE+Constantes.LISTE_DEPLACEMENTS_THEORIQUES+listeDeplacementTheorique.toString(), test );
	}
	
	/**
	 * Test ile : deplacementsPossible : de l'eau en dessous et à gauche.
	 * Transmet la liste des déplacements possibles autour d'une case en fonction des obstacles environnants
	 */
	@Test
	public void test2DeplacementsPossibles()
	{
		//On supprime tous les singes existants
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().clear();
		
		final int tailleMap = 3;
		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		// On crée une carte en TERRE
		int[][] carte = new int[tailleMap][tailleMap]; 
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = Constantes.TERRE;
			}
		}
		//, avec de la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//, avec de la MER en dessous de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//Coordonnées de la case à tester (centre de la map)
		final int x = 1; 
		final int y = 1;
		
		//Execution de la méthode à tester
		ArrayList<Integer> liste = this.monkeyIsland.deplacementsPossibles(x, y);
		
		// Pas d'obstacle : les 4 directions sont autorisées  
		ArrayList<Integer> listeDeplacementTheorique = new ArrayList<Integer>();
		listeDeplacementTheorique.add(Constantes.DROITE);
		listeDeplacementTheorique.add(Constantes.HAUT);
		
		boolean test = listeDeplacementTheorique.equals(liste);
		
		//Assertions
		//Verification que les posibilités retournées sont correctes
		assertTrue(Constantes.ERREUR_DETECTION_DEPLACEMENTS_P+Constantes.LISTE_DEPLACEMENTS+liste.toString()+Constantes.VIRGULE+Constantes.LISTE_DEPLACEMENTS_THEORIQUES+listeDeplacementTheorique.toString(), test );
	}
	
	/**
	 * Test ile : deplacementsPossible : de l'eau en dessous et à gauche, et un singe en haut.
	 * Transmet la liste des déplacements possibles autour d'une case en fonction des obstacles environnants
	 */
	@Test
	public void test1DeplacementPossible()
	{
		//On supprime tous les singes existants
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().clear();
		
		final int tailleMap = 3;
		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		final int hautX = 1;
		final int hautY = 2;
		
		//On ajoute un singe voisin
		SingeErratique singe = new SingeErratique(hautX, hautY, this.monkeyIsland);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe);
		
		// On crée une carte en TERRE
		int[][] carte = new int[tailleMap][tailleMap]; 
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = Constantes.TERRE;
			}
		}
		//, avec de la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//, avec de la MER en dessous de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//Coordonnées de la case à tester (centre de la map)
		final int x = 1; 
		final int y = 1;
		
		//Execution de la méthode à tester
		ArrayList<Integer> liste = this.monkeyIsland.deplacementsPossibles(x, y);
		
		// Pas d'obstacle : les 4 directions sont autorisées  
		ArrayList<Integer> listeDeplacementTheorique = new ArrayList<Integer>();
		listeDeplacementTheorique.add(Constantes.DROITE);
		
		boolean test = listeDeplacementTheorique.equals(liste);
		
		//Assertions
		//Verification que les posibilités retournées sont correctes
		assertTrue(Constantes.ERREUR_DETECTION_DEPLACEMENTS_P+Constantes.LISTE_DEPLACEMENTS+liste.toString()+Constantes.VIRGULE+Constantes.LISTE_DEPLACEMENTS_THEORIQUES+listeDeplacementTheorique.toString(), test );
	}
	

	/**
	 * Test ile : deplacementsPossible : de l'eau en dessous et à gauche, et un singe en haut et à droite.
	 * Transmet la liste des déplacements possibles autour d'une case en fonction des obstacles environnants
	 */
	@Test
	public void testAucunDeplacementPossible()
	{
		//On supprime tous les singes existants
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().clear();
		
		final int tailleMap = 3;
		final int dessousX = 1;
		final int dessousY = 0;
		final int gaucheX = 0;
		final int gaucheY = 1;
		final int hautX = 1;
		final int hautY = 2;
		final int droiteX = 2;
		final int droiteY = 1;
		
		//On ajoute les singes voisins
		SingeErratique singe = new SingeErratique(hautX, hautY, this.monkeyIsland);
		SingeErratique singe2 = new SingeErratique(droiteX,droiteY, this.monkeyIsland);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe);
		this.monkeyIsland.getSingesErratiques().getSingesErratiques().add(singe2);
		
		// On crée une carte en TERRE
		int[][] carte = new int[tailleMap][tailleMap]; 
		for (int i = 0; i < carte.length; i++) {
			for (int j = 0; j < carte[i].length; j++) {
				carte[i][j] = Constantes.TERRE;
			}
		}
		//, avec de la MER en dessous de la case à tester
		carte[gaucheX][gaucheY] = Constantes.MER;
		//, avec de la MER en dessous de la case à tester
		carte[dessousX][dessousY] = Constantes.MER;
		//Mise à jour de la carte de l'île		
		this.monkeyIsland.setCarte(carte);
		
		//Coordonnées de la case à tester (centre de la map)
		final int x = 1; 
		final int y = 1;
		
		//Execution de la méthode à tester
		ArrayList<Integer> liste = this.monkeyIsland.deplacementsPossibles(x, y);
		
		// Pas d'obstacle : les 4 directions sont autorisées  
		ArrayList<Integer> listeDeplacementTheorique = new ArrayList<Integer>();
		
		boolean test = listeDeplacementTheorique.equals(liste);
		
		//Assertions
		//Verification que les posibilités retournées sont correctes
		assertTrue(Constantes.ERREUR_DETECTION_DEPLACEMENTS_P+Constantes.LISTE_DEPLACEMENTS+liste.toString()+Constantes.VIRGULE+Constantes.LISTE_DEPLACEMENTS_THEORIQUES+listeDeplacementTheorique.toString(), test );
	}
	
}
