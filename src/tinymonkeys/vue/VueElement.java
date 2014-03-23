package tinymonkeys.vue;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * Classe de la vue d'un element.
 * 
 * @version 1.0
 * @author Guillaume
 *
 */
public class VueElement extends JPanel
{

	/**
	 * Constante indiquant la difference de taille entre celle d'une case et celle d'une image.
	 * Note : pour centrer l'image sur la grille, il est necessaire d'affecter cette constante a un nombre impair. 
	 */
	protected static final int DIFFERENCE_TAILLE_CASE_IMAGE = 1;

	/**
	 * Constante indiquant le décalage de position de placement entre une image et la case la contenant. 
	 * Note : l'ajout du 1 est necessaire pour centrer l'image si DIFFERENCE_TAILLE_CASE_IMAGE est un nombre impair.
	 */
	protected static final int DIFFERENCE_PLACEMENT_CASE_IMAGE = DIFFERENCE_TAILLE_CASE_IMAGE / 2 + 1;

	/**
	 * UID auto-genere.
	 */
	private static final long serialVersionUID = -4782337326938739321L;
	
	/**
	 * Taille d'une image en nombre de pixels.
	 */
	protected int tailleImage;
	
	/**
	 * Image d'un personnage.
	 */ 
	protected transient BufferedImage imageElement;
	
	/**
	 * Taille de la case en nombre de pixels.
	 */
	protected int tailleCase;
	
	/**
	 * La coordonnee en abscisse du coin supérieur gauche de la grille. 
	 */
	protected int xGrille;
	
	/**
	 * La coordonnee en ordonnée du coin supérieur gauche de la grille. 
	 */
	protected int yGrille;
	
	/**
	 * La coordonnee en abscisse de la vue du personnage par rapport a la grille (en nombre de cases). 
	 */
	protected int x;
	
	/**
	 * La coordonnee en ordonnée de la vue du personnage par rapport a la grille (en nombre de cases). 
	 */
	protected int y;

	/**
	 * La vue d'un personnage.
	 * 
	 * @param tailleCase la taille d'une case en nombre de pixels
	 * @param xGrille l'abscissse du coin supérieur gauche de la grille ou placer l'image (en pixels). 
	 * @param yGrille l'ordonnee du coin supérieur gauche de la grille ou placer l'image (en pixels). 
	 * @param x la position en abscisse du personnage (nombre de cases).
	 * @param y la position en ordonnee du personnage (nombre de cases).
	 */
	public VueElement(int tailleCase, int xGrille, int yGrille, int x, int y)
	{
		this.setOpaque(false);
		
		this.tailleCase = tailleCase;
		this.xGrille = xGrille;
		this.yGrille = yGrille;
		this.x = x;
		this.y = y;

		this.tailleImage = this.tailleCase - DIFFERENCE_TAILLE_CASE_IMAGE;
		
		final int xImage = this.xGrille + x * this.tailleCase + DIFFERENCE_PLACEMENT_CASE_IMAGE;
		final int yImage = this.yGrille + y * this.tailleCase + DIFFERENCE_PLACEMENT_CASE_IMAGE;
		this.setBounds(xImage, yImage, this.tailleImage + 1, this.tailleImage + 1);
		
		this.imageElement = null;
	}
	
	/**
	 * Dessine l'element.
	 * 
	 * @param g graphique dans lequel dessiner.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		dessineElement(g);
	}	
	
	/**
	 * Dessine l'image de l'element. 
	 * 
	 * @param g graphique dans lequel dessiner.
	 */
	protected void dessineElement(Graphics g)
	{
		g.drawImage(this.imageElement, 0, 0, this.tailleImage, this.tailleImage, null);

	}
	
	/**
	 * Change les dimensions de l'image de l'element en fonction de la taille des cases de la grille et de sa position.
	 * 
	 * @param tailleCase taille d'une case en pixels.
	 * @param xGrille l'abscissse du coin supérieur gauche de la grille ou placer l'image (en pixels). 
	 * @param yGrille l'ordonnee du coin supérieur gauche de la grille ou placer l'image (en pixels). 
	 */
	public void setDimensions(int tailleCase, int xGrille, int yGrille)
	{
		this.tailleCase = tailleCase;
		this.xGrille = xGrille;
		this.yGrille = yGrille;

		this.tailleImage = this.tailleCase - DIFFERENCE_TAILLE_CASE_IMAGE;
		
		final int xImage = this.xGrille + this.x * this.tailleCase + DIFFERENCE_PLACEMENT_CASE_IMAGE;
		final int yImage = this.yGrille + this.y * this.tailleCase + DIFFERENCE_PLACEMENT_CASE_IMAGE;
		this.setBounds(xImage, yImage, this.tailleImage + 1, this.tailleImage + 1);
	}
	
}
