package breaker;

import iut.Audio;
import iut.Objet;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class CarapaceV extends Ennemi {
    private double sensX=0;
    private double sensY=0;
    private int vie=5;
    
        public CarapaceV(Ennemi source){
            super(source.game(),"carapaceV",source.getLeft(),source.getMiddleY());
        }
        
	/**
         * effet d'une collision entre l'objet et le paramètre
         * @param o objet "coté joueur"
         */
	public void effect(Objet o) {
		if(o.isFriend()) 
                {
                    vie --;
                    if(vie<=0){
                        game().remove(this);
                    }
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
            if(sensX==0){
                Random alea = new Random();
                int aleatoire;

                aleatoire = alea.nextInt(6);
                sensX = aleatoire-3;
                }
            
            if(sensY==0){
                Random alea = new Random();
                int aleatoire;

                aleatoire = alea.nextInt(6);
                sensY = aleatoire-3;
                }
                
                moveY(dt*sensY/6);
                moveX(dt*sensX/6);
                if((getBottom() <60) || (getTop() > game().height()-60)){
                    sensY = -1* sensY;
                    vie --;
                    Audio bump = new Audio("bump");
                    bump.start();
                }
                if((getLeft() <60) || (getRight() >game().width()-60)){
                    sensX = -1* sensX;
                    vie --;
                    Audio bump = new Audio("bump");
                    bump.start();
                }
                if(vie<=0){
                game().remove(this);
            }
	}

        public void explosion(){
            
        }
        
        public void crier(){
            
        }
        
        public void tirer(){
            
        }
        
	/**
	 * indique si le tir est "à l'intérieur" de l'écran ou non
	 * @return true si le tir a quitté l'écran 
	 */
	private boolean estSorti() {
		return getTop()>game().height() || getLeft()<0 || getTop()<0 || getRight()>game().getWidth();
	}

    @Override
    public int getVie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}