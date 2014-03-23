package tinymonkeys.modele;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.EventListenerList;

import utils.Constantes;

/**
 * Classe Ile. 
 * 
 * @version 1.0
 * @author Guillaume
 *
 */
public class Ile 
{	
	/**
	 * La carte de l'ile : une matrice indiquant si chaque case est de type mer (0) ou terre (1).
	 */
	private int[][] carte;
	
	/**
	 * Les singes erratiques.
	 */
	private BandeDeSingesErratiques erratiques;
	
	/**
	 * Le tresor.
	 */
	private Tresor tresor;
	
	/**
	 * Le pirate du joueur.
	 */
	private Pirate pirate;
	
	/**
	 * Liste des écouteurs sur l'ile.
	 */
	final private EventListenerList ileEcouteurs;
	
	/**
	 * Constructeur de la classe Ile. 
	 */
	public Ile()
	{
		this.carte = null;
		this.erratiques = new BandeDeSingesErratiques(this);
		this.tresor = null;
		this.pirate = new Pirate(this);
		this.ileEcouteurs = new EventListenerList();
	}
	
	
	/**
	 * Indique la largeur de la carte en nombre de cases.
	 * 
	 * @return la largeur de la carte.
	 */
	public int getLargeurCarte()
	{
		return this.carte.length;
	}
	
	/**
	 * Indique la longueur de la carte en nombre de cases.
	 * 
	 * @return la longueur de la carte.
	 */
	public int getLongueurCarte()
	{
		return this.carte[0].length;
	}
	
	/**
	 * Permet l'acces en lecture a la valeur de la carte aux coordonnees indiquees.
	 * 
	 * @param x la coordonnee en abscisse.
	 * @param y la coordonnee en ordonnee.
	 * @return la valeur de la case de la carte aux coordonnees indiquees.
	 */
	public int valeurCarte(int x, int y)
	{
		return this.carte[x][y];
	}
	
	/**
	 * Creation de la carte.
	 * 
	 * @param carte la matrice terre-mer. 
	 */
	public void creationCarte(int[][] carte)
	{
		int[][] copy = new int [carte.length][];
		for (int row = 0 ; row < carte.length ; row ++){
			copy[row] = new int[carte[row].length];
			System.arraycopy(carte[row], 0, copy[row], 0, carte[row].length);
		}	
		this.carte = copy;
		for (IleEcouteur ilesecouteur : this.ileEcouteurs.getListeners(IleEcouteur.class)) {
			ilesecouteur.creationCarte(copy);
		}

	}
	
	/**
	 * Mise à jour de la carte.
	 * 
	 * @param carte la matrice terre-mer. 
	 */
	public void setCarte(int[][] carte)
	{
		this.carte = carte;
		for (IleEcouteur ilesecouteur : this.ileEcouteurs.getListeners(IleEcouteur.class)) {
			ilesecouteur.changementCarte(carte);
		}	

	}
	
	/**
	 * Accesseur en lecture du pirate de l'ile.
	 * 
	 * @return le pirate.
	 */
	public Pirate getPirate()
	{
		return this.pirate;
	}
	
	/**
	 * Creation du pirate sur l'ile.
	 * 
	 * @param avatar le lien vers l'image du pirate.
	 */
	public void ajoutPirate(String avatar)
	{
		this.pirate.setAvatar(avatar); //Definition de l'avatar
		final Random random = new Random();
		int abcisse = 0;
		int ordonnees = 0;

		boolean place = false;
		while(!place){
			abcisse = random.nextInt(this.carte.length);
			ordonnees = random.nextInt(this.carte.length);
			if((Constantes.TERRE == this.valeurCarte(abcisse,ordonnees) && !singeEstPresent(abcisse,ordonnees))){
					place = true;					
			}
		}		
		this.pirate.positionInitiale(abcisse, ordonnees); // Définition des coordonnées
	}
	/**
	 * Vérfie si un singe est présent sur la case aux coordonnées précisées.
	 * @param abcisse l'abcisse 
	 * @param ordonnees l'ordonne
	 * @return si un singe est présent
	 */
	public boolean singeEstPresent(int abcisse, int ordonnees) 
	{
		boolean estPresent = false;
		for (SingeErratique singe : this.erratiques.getSingesErratiques()) {
			if(singe.coordonneesEgales(abcisse, ordonnees)){
				estPresent = true;
			}
		}
		return estPresent;
	}

	/**
	 * Méthode pour connaître les cases adjacentes libres.
	 * @param abcisse l'abcisse
	 * @param ordonnee l'ordonnée
	 * @return la liste des déplacements possibles
	 */
	public ArrayList<Integer> deplacementsPossibles(int abcisse, int ordonnee)
	{
		ArrayList<Integer> listeDeplacements = new ArrayList<Integer>();
		//Traitement gauche
		if(Constantes.TERRE == this.valeurCarte(abcisse-1, ordonnee) && !this.singeEstPresent(abcisse-1, ordonnee)){
			listeDeplacements.add(Constantes.GAUCHE);
		}
		//Traitement droite
		if(Constantes.TERRE == this.valeurCarte(abcisse+1, ordonnee) && !this.singeEstPresent(abcisse+1, ordonnee)){
			listeDeplacements.add(Constantes.DROITE);
		}
		//Traitement haut
		if(Constantes.TERRE == this.valeurCarte(abcisse, ordonnee+1) && !this.singeEstPresent(abcisse, ordonnee+1)){
			listeDeplacements.add(Constantes.HAUT);
		}
		//Traitement bas
		if(Constantes.TERRE == this.valeurCarte(abcisse, ordonnee-1) && !this.singeEstPresent(abcisse, ordonnee-1)){
			listeDeplacements.add(Constantes.BAS);
		}
		return listeDeplacements;
	}	
	

	/**
	 * Methode permettant de faire la demande de deplacement du pirate. 
	 * Cette methode fait suite a un appui sur une fleche directionnelle du clavier.
	 * 
	 * @param dx la direction en abscisse.
	 * @param dy la direction en ordonnee.
	 */
	public void demandeDeplacementPirate(int dx, int dy)
	{
		this.pirate.demandeDeplacement(dx, dy);
	}
	

	
	/**
	 * Accesseur en lecture de la bande de singes erratiques.
	 * 
	 * @return la bande de singes erratiques.
	 */
	public BandeDeSingesErratiques getSingesErratiques()
	{
		return this.erratiques;
	}
	
	/**
	 * Ajout du nombre indique de singes erratiques dans la liste des singes erratiques.
	 * 
	 * @param nombreS le nombre de singes erratiques a ajouter.
	 */
	public void ajoutSingesErratiques(int nombreS)
	{
		this.erratiques.ajoutSingesErratiques(nombreS);
	}
	
	
	/**
	 * Accesseur en lecture du tresor.
	 * 
	 * @return le tresor.
	 */
	public Tresor getTresor()
	{
		return this.tresor;
	}
	
	/**
	 * Creation du tresor a une position aleatoire.
	 */
	public void creationTresor()
	{		
		final Random random = new Random();
		int abcisse = 0;
		int ordonnees = 0;

		boolean place = false;
		while(!place){
			abcisse = random.nextInt(this.carte.length);
			ordonnees = random.nextInt(this.carte.length);
			if(0 != this.valeurCarte(abcisse,ordonnees)){
				place = true;
			}
		}		
		
		this.tresor = new Tresor(abcisse, ordonnees);
		for (IleEcouteur ilesecouteur : this.ileEcouteurs.getListeners(IleEcouteur.class)) {
			ilesecouteur.creationTresor(abcisse, ordonnees);
		}		
	}
	
	
	/**
	 * Suppression du tresor.  
	 */ 
	public void suppressionTresor()
	{		
		final Random random = new Random();
		int abcisse = 0;
		int ordonnees = 0;

		boolean place = false;
		while(!place){
			abcisse = random.nextInt(this.carte.length);
			ordonnees = random.nextInt(this.carte.length);
			if(0 != this.valeurCarte(abcisse,ordonnees)){
				place = true;
			}
		}		
		
		this.tresor = new Tresor(abcisse, ordonnees);
		for (IleEcouteur ilesecouteur : this.ileEcouteurs.getListeners(IleEcouteur.class)) {
			ilesecouteur.suppressionTresor();
		}
	}
	

	/**
	 * Enregistre dans la liste des ecouteurs de l'ile l'ecouteur passe en parametre.
	 * @param ecouteur ecouteur de l'ile.
	 */
	public void enregistreEcIle(IleEcouteur ecouteur)
	{
		this.ileEcouteurs.add(IleEcouteur.class, ecouteur);
	}


	/**
	 * Retourne les ecouteurs du pirate.
	 * @return the ileEcouteurs
	 */
	public EventListenerList getIleEcouteurs()
	{
		return this.ileEcouteurs;
	}


	/**
	 * Retourne la matrice[][] de la carte terre/mer de l'île.
	 * @return the carte
	 */
	public int[][] getCarte() 
	{
		return this.carte;
	}
	
}
