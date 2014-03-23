package tinymonkeys.modele;

import java.util.EventListener;

/**
 * Ecouteur pour l'ile.
 * @version 1.0
 * @author Guillaume
 *
 */
public interface IleEcouteur extends  EventListener
{
	/**
	 * Création de l'affichage de laa carte.
	 * 
	 * @param carte la carte a dessiner.
	 */
	public void creationCarte(int[][] carte);
	
	/**
	 * Modifie la carte affichee.
	 * @param carte la nouvelle carte.
	 */
	public void changementCarte(int [][] carte);
	
	/**
	 * Creation de l'image du singe erratique identifie aux coordonnees indiquees.
	 * @param id l'identifiant du singe erratique.
	 * @param x coordonnee en abscisse du singe erratique.
	 * @param y coordonnee en ordonnee du singe erratique.
	 */
	public void creationSingeErratique(int id, int x, int y);
	
	/**
	 * Deplacement de l'image du singe erratique identifie aux coordonnees indiquees.
	 * @param id l'identifiant du singe erratique.
	 * @param x coordonnee en abscisse du singe erratique.
	 * @param y coordonnee en ordonnee du singe erratique.
	 */
	public void deplacementSingeErratique(int id, int x, int y);
	
	/**
	 * Creation de l'image du tresor aux coordonnees indiquees.
	 * @param x coordonnee en abscisse du tresor.
	 * @param y coordonnee en ordonnee du tresor.
	 */
	public void creationTresor(int x, int y);
	
	/**
	 * Suppression de l'image du tresor.  
	 */ 
	public void suppressionTresor();

	
}
