package tinymonkeys.modele;

import java.util.Random;
import java.util.Vector;

import javax.swing.event.EventListenerList;

import utils.Constantes;

/**
 * Classe d'une bande de singes erratiques.
 * 
 * @version 1.0
 * @author Guillaume
 *
 */
public class BandeDeSingesErratiques extends Thread
{
	/**
	 * Vecteur contenant l'ensemble des singes erratiques.
	 */
	private Vector<SingeErratique> erratiques;
	
	/**
	 * L'ile.
	 */
	private Ile monkeyIsland;
	
	/**
	 * Liste des écouteurs sur la bande de singes erratiques.
	 */
	final private EventListenerList bandeSingesEcouteurs;
	
	/**
	 * Constructeur d'une bande de singes erratiques vide.
	 * 
	 * @param ile l'ile contenant l'ensemble des elements de celle-ci.
	 */
	public BandeDeSingesErratiques(Ile ile)
	{
		super();
		this.erratiques = new Vector<SingeErratique>();
		this.monkeyIsland = ile;
		this.bandeSingesEcouteurs = new EventListenerList();
	}
	
	/**
	 * Accesseur en lecture a l'ensemble des singes erratiques.
	 * 
	 * @return le vecteur de singes erratiques.
	 */
	public Vector<SingeErratique> getSingesErratiques()
	{
		return this.erratiques;
	}

	
	/**
	 * Ajout du nombre indique de singes erratiques a des positions libres aleatoires.
	 * 
	 * @param nombreS le nombre de singes a ajouter.
	 */
	public void ajoutSingesErratiques(int nombreS)
	{
		final Random random = new Random();
		int abcisse = 0;
		int ordonnees = 0;
		for (int i = 0; i < nombreS; i++) {
			boolean place = false;
			while(!place){
				abcisse = random.nextInt(this.monkeyIsland.getLargeurCarte());
				ordonnees = random.nextInt(this.monkeyIsland.getLargeurCarte());
				if(1 == this.monkeyIsland.valeurCarte(abcisse,ordonnees)){
					place = true;
				}
			}
			this.erratiques.add(new SingeErratique(abcisse, ordonnees, this.monkeyIsland));
			for (IleEcouteur ilesecouteur : this.monkeyIsland.getIleEcouteurs().getListeners(IleEcouteur.class)) {
				ilesecouteur.creationSingeErratique(i, abcisse, ordonnees);
			}		
		}					
	}

	
	
	/**
	 * Enregistre dans la liste des ecouteurs de bande de singes l'ecouteur passe en parametre.
	 * @param ecouteur ecouteur de la bande de singes.
	 */
	public void enregistreEcBandeSinges(BandeDeSingesErratiquesEcouteur ecouteur)
	{
		this.bandeSingesEcouteurs.add(BandeDeSingesErratiquesEcouteur.class, ecouteur);
	}

	@Override
	public void run() 
	{
		while(true){
			for (int i = 0; i < this.erratiques.size(); i++) {
				this.erratiques.get(i).deplacerSinge();
				
				for (IleEcouteur ilesecouteur : this.monkeyIsland.getIleEcouteurs().getListeners(IleEcouteur.class)) {
					ilesecouteur.deplacementSingeErratique(i, this.erratiques.get(i).getX(), this.erratiques.get(i).getY());
				}	
			}
			try {
				Thread.sleep(Constantes.ATTENTE_DEPLACEMENT_SINGE);
			} 
			catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retourne la liste des écouteurs de la bande de singes erratiques.
	 * @return the bandeSingesEcouteurs
	 */
	public EventListenerList getBandeSingesEcouteurs() 
	{
		return this.bandeSingesEcouteurs;
	}

}
