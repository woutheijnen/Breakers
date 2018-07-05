package breaker;

import iut.Objet;
import iut.ObjetTouchable;

/**
 * Projectile lancée par l'ennemi pour tuer le joueur.
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class TirEnnemiZQ extends ObjetTouchable {
    private final double vitesse=0.1;
    
        public TirEnnemiZQ(Ennemi source){
            super(source.game(),"TirEnnemiZD",source.getMiddleX(),source.getMiddleY());
        }
	/**
	 * gére la collision avec les autres objects
	 */
	public void effect(Objet o) {
		if(o.isFriend()) // objet "coté joueur"
                {
                    game().remove(this);
                }
	}

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

        
	/**
	 * Déplace l'objet
	 * @param dt le temps écoulé en millisecondes depuis le précédent déplacement
	 */
	public void move(long dt) {
		moveX(dt*vitesse);
                moveY(dt*-vitesse);
                if(estSorti()){
                    game().remove(this);
                }
	}

	/**
	 * indique si le tir est "à l'intérieur" de l'écran ou non
	 * @return true si le tir a quitté l'écran (par le bas)
	 */
	private boolean estSorti() {
		return getTop()>game().height() || getLeft()<0 || getTop()<0 || getRight()>game().getWidth();
	}
}