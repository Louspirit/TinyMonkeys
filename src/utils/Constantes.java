package utils;

/**
 * Classe répertoriant des constantes.
 * @author Guillaume
 * @version 1.0
 */
public class Constantes
{
	/**
	 * représente les quatre directions possibles (haut, bas, gauche ,droite).
	 */
	public final static int NB_DIRECTION = 3;
	/**
	 * Case de type Terre.
	 */
	public final static int TERRE = 1;
	/**
	 * Element de grille Mer.
	 */
	public final static int MER = 0;
	/**
	 * Deplacement vers le bas.
	 */
	public final static int BAS = 0;
	/**
	 * Deplacement vers le gauche.
	 */
	public final static int GAUCHE = 1;
	/**
	 * Deplacement vers le haut.
	 */
	public final static int HAUT = 2;
	/**
	 * Deplacement vers le droite.
	 */
	public final static int DROITE = 3;
	/**
	 * Temps d'attente avant un déplacement de singe.
	 */
	public static final int ATTENTE_DEPLACEMENT_SINGE = 500;
	
	
	// TESTS -----------------------------------------------------------
	/**
	 * Une flèche à afficher en cas d'erreur de test.
	 */
	public final static String FLECHE = "->";
	
	/**
	 * La valeur x suivant de deux points.
	 */
	public final static String X = "x :";
	
	/**
	 * La valeur y suivant de deux points.
	 */
	public final static String Y = "y :";
	
	/**
	 * Le séparateur , .
	 */
	public final static String VIRGULE = ", ";
	
	/**
	 * La valeur compteur bas.
	 */
	public final static String COMPTEUR_BAS = "Compteur bas :";
	
	/**
	 * La valeur compteur haut.
	 */
	public final static String COMPTEUR_HAUT = "Compteur haut :";
	
	/**
	 * La valeur compteur gauche.
	 */
	public final static String COMPTEUR_GAUCHE = "Compteur gauche :";
	
	/**
	 * La valeur compteur droite.
	 */
	public final static String COMPTEUR_DROITE = "Compteur droite :";
	
	/**
	 * La liste de déplacements théoriques.
	 */
	public final static String LISTE_DEPLACEMENTS_THEORIQUES = "Liste déplacements théoriques : ";
	
	/**
	 * La liste de déplacements.
	 */
	public final static String LISTE_DEPLACEMENTS = "Liste déplacements : ";
	
	// LABELS ERROR 
	
	/**
	 * Début de phrase à afficher en cas de problème de déplacement simple.
	 */
	public final static String ERREUR_DEPLACEMENT = "Le singe ne s'est pas déplacé ou bien en diagonal, ou bien il s'est téléporté : ";
	
	/**
	 * Début de phrase à afficher en cas de problème de déplacement interdit.
	 */
	public final static String DEPLACEMENT_INTERDIT = "Le singe s'est déplacé dans un endroit interdit : ";
	
	/**
	 * Le déplacement aléatoire du singe n'est pas équiprobable.
	 */
	public final static String DEPLACEMENT_NON_EQUIPROBABLE = "Le déplacement du singe n'est pas équiprobable : ";
	
	/**
	 * L'île a mal détectée la présence ou l'absence d'un singe sur une case.
	 */
	public final static String ERREUR_DETECTION_SINGE = "L'île a mal détectée la présence ou l'absence d'un singe sur la case : ";
	
	/**
	 * Erreur de la part de l'île de detection des déplacements autour d'une case donnée.
	 */
	public final static String ERREUR_DETECTION_DEPLACEMENTS_P = "L'île a mal détectée les déplacements autorisés autour de la case. ";

}
