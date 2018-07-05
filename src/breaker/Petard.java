package breaker;

import iut.Objet;
import iut.ObjetTouchable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *@author les breakers 
 * Projectile lancé par l'ennemi pour tuer le joueur.
 */
public class Petard extends Ennemi {
    private final double vitesse=0.15;
    private int oldXCord;
    private int oldYCord;
    private int vie = 3;
    private GestionMap ge;
    
        public Petard(Ennemi source, int x, int y, GestionMap gestion){
            super(source.game(),"petard",source.getMiddleX(),source.getBottom());
            Random alea = new Random();
            int aleatoire = alea.nextInt(2);
            if(aleatoire == 0 && gestion.getPlayer().estEnVie()){
                oldXCord = gestion.getPlayer().getMiddleX();
                oldYCord = gestion.getPlayer().getMiddleY();
            }else{
                if(gestion.isMultiPlayer() && gestion.getPlayer2().estEnVie()){
                    oldXCord = gestion.getPlayer2().getMiddleX();
                    oldYCord = gestion.getPlayer2().getMiddleY();
                }else{
                    oldXCord = gestion.getPlayer().getMiddleX();
                    oldYCord = gestion.getPlayer().getMiddleY();
                }
            }
            ge = gestion;
        }
        
	/**
	 * gére la collision avec les autres objects
	 */
	public void effect(Objet o) {
		if(o.isFriend()) // objet "coté joueur"
                {
                    vie --;
                    if(vie <=0){
                        explosion();
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
                    explosion();
                }
	}

        public void explosion(){
            BombExplosionF1 exp1 = new BombExplosionF1(this, ge);
            game().add(exp1);
            
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