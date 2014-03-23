package tinymonkeys.modele;

import javax.swing.event.EventListenerList;

import utils.Constantes;

/**
 * Classe d'un pirate. 
 * 
 * @version 1.0
 * @author Guillaume
 *
 */
public class Pirate 
{

	/**
	 * Le chemin vers l'image du pirate.
	 */
	private String avatar;
	
	/**
	 * Coordonnee en abscisse du pirate (indice dans la carte).
	 */
	private int x;
	
	/**
	 * Coordonnee en ordonnee du pirate (indice dans la carte).
	 */
	private int y;
	
	/**
	 * Ile aux singes.
	 */
	private Ile monkeyIsland;
	
	/**
	 * Récolte des trésors.
	 */
	private int butin; // Evolutivité : créé si l'on désire affiché le score du joueur par exemple
	
	/**
	 * Liste des écouteurs sur le pirate.
	 */
	final private EventListenerList pirateEcouteurs;
	
	/**
	 * Constructeur du pirate sans position ni nom renseignes mais avec l'ile associee.
	 * 
	 * @param ile l'ile contenant tous les elements de celle-ci.
	 */
	public Pirate(Ile ile)
	{
		this.monkeyIsland = ile;
		this.pirateEcouteurs = new EventListenerList();
	}
	
	/**
	 * Constructeur du pirate avec le nom renseigne.
	 * 
	 * @param ile l'ile contenant tous les elements de celle-ci.
	 * @param avatar le lien vers l'avatar du pirate.
	 */
	public Pirate(Ile ile, String avatar)
	{
		this.monkeyIsland = ile;
		this.avatar = avatar;
		this.pirateEcouteurs = new EventListenerList();
	}
		
	/**
	 * Accesseur en lecture de la position en abscisse du pirate.
	 * @return la coordonnee en abscisse.
	 */
	public int getX()
	{
		return this.x;
	}
	
	
	/**
	 * Accesseur en lecture de la position en ordonnee du pirate.
	 * @return la coordonnee en ordonnee.
	 */
	public int getY()
	{
		return this.y;
	}
	
	
	/**
	 * Place le pirate a sa position initiale.
	 * 
	 * @param x la coordonnee initiale en abscisse.
	 * @param y la coordonnee initiale en ordonnee.
	 */
	public void positionInitiale(int x, int y)
	{	
		this.x = x;
		this.y = y;

		for (PirateEcouteur piratecouteur : this.pirateEcouteurs.getListeners(PirateEcouteur.class)) {
			piratecouteur.ajoutPirate(0, x, y, this.avatar);
		}		
	}
	
	/**
	 * Methode deplacant le pirate selon la direction demandee.
	 * Si le deplacement indique positionne le pirate sur un singe, le pirate meurt.
	 * Si le deplacement indique positionne le pirate sur le tresor, le tresor est detruit.
	 * 
	 * @param dx la direction en abscisse (comprise entre -1 et 1).
	 * @param dy la direction en ordonnee (comprise entre -1 et 1).
	 */
	public void demandeDeplacement(int dx, int dy)
	{
		// Nouvelles coordonnées en fonction des directions d
		this.x = this.x + dx;
		this.y = this.y + dy;
		
		for (PirateEcouteur piratecouteur : this.pirateEcouteurs.getListeners(PirateEcouteur.class)) {
			//Traitement trésor
			if(this.monkeyIsland.getTresor().coordonneesEgales(this.x, this.y)){
				this.butin++;
				this.monkeyIsland.suppressionTresor();
				this.monkeyIsland.creationTresor();
			}
			//Traitement déplacement pirate
			if(this.monkeyIsland.singeEstPresent(this.x, this.y)){
				piratecouteur.deplacementPirate(0, this.x, this.y);
				//Marcher sur un singe entraine la mort
				piratecouteur.mortPirate(0);
				break;
			}else if(Constantes.MER != this.monkeyIsland.valeurCarte(this.x, this.y)){
				piratecouteur.deplacementPirate(0, this.x, this.y);
			}else{
				piratecouteur.deplacementPirate(0, this.x, this.y);
				piratecouteur.mortPirate(0);
			}
			//Libération clavier pour pouvoir se déplacer
			piratecouteur.liberationClavier();
		}
	}
	
	
	/**
	 * Accesseur en lecture de l'avatar.
	 * 
	 * @return le chemin vers l'image.
	 */
	public String getAvatar()
	{
		return this.avatar;
	}
	
	/**
	 * Accesseur en ecriture de l'avatar du pirate.
	 * 
	 * @param avatar l'avatar du pirate.
	 */
	public void setAvatar(String avatar)
	{
		this.avatar = avatar;
	}
		
	
	/**
	 * Enregistre dans la liste des ecouteurs de pirate l'ecouteur passe en parametre.
	 * 
	 * @param ecouteur ecouteur du pirate.
	 */
	public void enregistreEcPirate(PirateEcouteur ecouteur)
	{
		this.pirateEcouteurs.add(PirateEcouteur.class, ecouteur);
	}

	/**
	 * Retourne la liste des écouteurs du pirate.
	 * @return the pirateEcouteurs
	 */
	public EventListenerList getPirateEcouteurs() 
	{
		return this.pirateEcouteurs;
	}
}
