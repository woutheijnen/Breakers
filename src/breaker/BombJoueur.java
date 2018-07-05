package breaker;

import iut.Audio;
import iut.Objet;
import iut.ObjetTouchable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 */
public class BombJoueur extends Ennemi {
    private final double vitesse=0.1;
    private int vie = 3;
    private GestionMap ge;
    
        public BombJoueur(ObjetTouchable source, GestionMap gestion){
            super(source.game(),"BombEnnemi",source.getMiddleX(),source.getMiddleY());
            ge = gestion;
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
	public void effect(Objet o) {
		if(o.isEnnemy()) // objet "coté joueur"
                {
                    vie --;
                    if(vie<=0){
                        this.explosion();
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
                    explosion();
                }
	}

        public void explosion(){
            BombExplosionF1 exp1 = new BombExplosionF1(this, ge);
            game().add(exp1);
            
            Audio e = new Audio("explosion");
            e.start();
            game().remove(this);
        }
        
        public void crier(){
            
        }
        
        public void tirer(){
            
        }
        
	/**
	 * indique si le tir est "à l'intérieur" de l'écran ou non
	 * @return true si le tir a quitté l'écran (par le bas)
	 */
	private boolean estSorti() {
		return getTop()>game().height() || getLeft()<0 || getTop()<0 || getRight()>game().getWidth();
	}
        
        @Override

    public int getVie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}