package breaker;

import iut.Objet;
import iut.ObjetTouchable;

/**
 * tir du joueur, un seul tir est présent dans le jeu à la fois
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class TirJoueurD extends ObjetTouchable {
	private final Joueur j;
        private final double vitesse=0.5;
        private int vie;

	/**
	 * Crée un tir du joueur
	 * @param joueur le joueur1 
         * @param s sens du tir
	 */
	public TirJoueurD(Joueur joueur) {
		super(joueur.game(),"TirHero",joueur.getMiddleX(),joueur.getMiddleY());
                j = joueur;                               
                vie = j.getPouvArme();
	}

	/**
	 * gére la collision avec les autres objects
	 */
	public void effect(Objet o) {
            if(o.isEnnemy())
            {
                vie --;
                if(vie<=0){
                    game().remove(this);
                    j.tirSorti();
                }
            }
	}

	/**
	 * @return true si l'objet est un "ami" du joueur
	 */
	public boolean isFriend() {
		return true;
	}

	/**
	 * @return false si l'objet est un "ennemi" du joueur
	 */
	public boolean isEnnemy() {
		return false;
	}

	/**
	 * Déplace l'objet
	 * @param dt le temps écoulé en millisecondes depuis le précédent déplacement
	 */
	public void move(long dt) {
		moveX(dt*vitesse);
                if(estSorti()){
                    game().remove(this);
                    j.tirSorti();
                }
	}

	/**
	 * indique si le tir est "à l'intérieur" de l'écran ou non
	 * @return true si le tir a quitté l'écran (par le haut)
	 */
	public boolean estSorti() {
		return getTop()>game().height()-40 || getLeft()<40 || getTop()<40 || getRight()>game().getWidth()-40;
	}
}