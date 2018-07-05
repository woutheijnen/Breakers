package breaker;

import iut.Objet;
import iut.ObjetTouchable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 */
public class Marteau extends Ennemi {
    private final double vitesse=0.1;
    private int oldXCord;
    private int oldYCord;
    
        public Marteau(Ennemi source, GestionMap ges){
            super(source.game(),"marteau",source.getMiddleX(),source.getBottom());
            Random alea = new Random();
            int aleatoire = alea.nextInt(2);
            if(aleatoire == 0 && ges.getPlayer().estEnVie()){
                oldXCord = ges.getPlayer().getMiddleX();
                oldYCord = ges.getPlayer().getMiddleY();
            }else{
                if(ges.isMultiPlayer() && ges.getPlayer2().estEnVie()){
                    oldXCord = ges.getPlayer2().getMiddleX();
                    oldYCord = ges.getPlayer2().getMiddleY();
                }else{
                    oldXCord = ges.getPlayer().getMiddleX();
                    oldYCord = ges.getPlayer().getMiddleY();
                }
            }
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
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
                if(oldXCord>=getMiddleX()-10 && oldXCord<=getMiddleX()+10 && oldYCord<getMiddleY()){
                        moveY(dt*-vitesse);
                }
                if(oldXCord>=getMiddleX()-10 && oldXCord<=getMiddleX()+10 && oldYCord>getMiddleY()){
                        moveY(dt*vitesse);
                }
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