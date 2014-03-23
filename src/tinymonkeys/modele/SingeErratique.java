package tinymonkeys.modele;

import java.util.ArrayList;
import java.util.Random;

import utils.Constantes;


/**
 * Classe du singe erratique.
 * 
 * @version 1.0
 * @author Guillaume
 *
 */

public class SingeErratique extends Singe
{
	/**
	 * Constructeur de la classe SingeErratique. 
	 * 
	 * @param x la coordonnee en abscisse du singe.
	 * @param y la coordonnee en ordonnee du singe.
	 * @param ile l'ile sur laquelle vit le singe.
	 */
	public SingeErratique(int x, int y, Ile ile)
	{
		super(x, y, ile);
	}
	
	/**
	 * Deplacement aleatoire du singe erratique.
	 */
	public void deplacerSinge()
	{
		final Random random = new Random();
		int dir = 0; // nombre aléatoire pour décider du déplacement
		
		 // Cette méthode renvoit la liste des déplacements possibles
		ArrayList<Integer> listeDir = this.monkeyIsland.deplacementsPossibles(getX(), getY());
		if(!listeDir.isEmpty()){		
			//On choisit un déplacement au hasard parmis celle-ci
			dir = random.nextInt(listeDir.size());
			//On traite le déplacement = nouvelle position
			switch(listeDir.get(dir)){
				case Constantes.BAS : //bas
					this.setPosition(getX(), getY()-1);
					break;
				case Constantes.GAUCHE : //gauche
					this.setPosition(getX()-1, getY());
					break;
				case Constantes.HAUT : //haut
					this.setPosition(getX(), getY()+1);
					break;
				default : //droite
					this.setPosition(getX()+1, getY());
			}
			if(coordonneesEgales(monkeyIsland.getPirate().getX(), monkeyIsland.getPirate().getY())){
				for (PirateEcouteur piratEcouteur : this.monkeyIsland.getPirate().getPirateEcouteurs().getListeners(PirateEcouteur.class)) {
					piratEcouteur.mortPirate(0);
				}
			}
			//Evolutivité: Si un jour les singes s'interessent aux trésors
	//		if(coordonneesEgales(monkeyIsland.getTresor().getX(), monkeyIsland.getTresor()getY()){
	//		}
		}
	}
	
}
