package breaker;

import iut.Game;
import iut.ObjetTouchable;

/**
 * Représente les ennemis (envahisseurs). Classe abstraite à utiliser pour factoriser le comportement
 */
public abstract class Ennemi extends ObjetTouchable {
        
        public Ennemi(Game g, String s, int x, int y){
            super(g,s,x,y);
        }
	protected abstract void tirer();

	/**
	 * joue un son quand l'ennemi est touché (cri)
	 */
	protected abstract void crier() ;
	

	/**
	 * @return true si l'objet est un "ami" du joueur
	 */
	public boolean isFriend() {
		return false;
	}

	/**
	 * @return false si l'objet est un "ennemi" du joueur
	 */
	public boolean isEnnemy() {
		return true;
	}

        public abstract int getVie();
}