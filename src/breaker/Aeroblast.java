package breaker;

import iut.Objet;
import iut.ObjetTouchable;

/**
 * Projectile lancé , par l'ennemi pour tuer le joueur.
 */
public class Aeroblast extends ObjetTouchable {
    private final double vitesse=0.15;
    private int oldXCord;
    private int oldYCord;
    
        /**
         * Constructeur de la classe Aeroblast
         * @param source 
         * @param aAttaquer 
         */
        public Aeroblast(ObjetTouchable source, Ennemi aAttaquer){
            super(source.game(),"aeroblast",source.getMiddleX(),source.getBottom());
            oldXCord = aAttaquer.getMiddleX();
            oldYCord = aAttaquer.getMiddleY();
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
	public void effect(Objet o) {
		if(o.isEnnemy()) // objet "coté joueur"
                {
                    game().remove(this);
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
                if(oldXCord<getMiddleX() && oldYCord<getMiddleY()){
                        moveX(dt*-vitesse);
                        moveY(dt*-vitesse);
                }
                if(oldXCord>getMiddleX() && oldYCord<getMiddleY()){
                        moveX(dt*vitesse);
                        moveY(dt*-vitesse);
                }
                if(oldXCord<getMiddleX() && oldYCord>getMiddleY()){
                        moveX(dt*-vitesse);
                        moveY(dt*vitesse);
                }
                if(oldXCord>getMiddleX() && oldYCord>getMiddleY()){
                        moveX(dt*vitesse);
                        moveY(dt*vitesse);
                }
                if(oldXCord>=getMiddleX()-10 && oldXCord<=getMiddleX()+10 && oldYCord<getMiddleY()){
                        moveY(dt*-vitesse);
                }
                if(oldXCord>=getMiddleX()-10 && oldXCord<=getMiddleX()+10 && oldYCord>getMiddleY()){
                        moveY(dt*vitesse);
                }
                if(oldXCord<getMiddleX() && oldYCord >= getMiddleY()-10 && oldYCord <= getMiddleY()+10){
                        moveX(dt*-vitesse);
                }
                if(oldXCord>getMiddleX() && oldYCord >= getMiddleY()-10 && oldYCord <= getMiddleY()+10){
                        moveX(dt*vitesse);
                }
                if(estSorti() || (oldXCord>=getMiddleX()-10 && oldXCord<=getMiddleX()+10 && oldYCord >= getMiddleY()-10 && oldYCord <= getMiddleY()+10)){
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
	 * @return true si le tir a quitté l'écran (par le bas)
	 */
	private boolean estSorti() {
		return getTop()>game().height() || getLeft()<0 || getTop()<0 || getRight()>game().getWidth();
	}

        public int getVie() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
}