/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breaker;

import iut.Objet;
import java.util.Random;

/**
 * représente une attaque lancée contre l'ennemi
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class BouleEnergie extends Ennemi {
    private final double vitesse=0.15;
    private int oldXCord;
    private int oldYCord;
    
    public BouleEnergie(Ennemi source, GestionMap ges){
            super(source.game(),"bouleEnergie",source.getLeft(),source.getMiddleY());
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

    @Override
    protected void tirer() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void crier() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getVie() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    /**
     * L'objet est retiré de l'écran après collision
     */
    public void effect(Objet o) {
        if(o.isFriend()) // objet "coté joueur"
                {
                    game().remove(this);
                }
    }

    @Override
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
   /**
    * 
    * @return false si l'objet est un "ennemi" du joueur
    */
    public boolean isEnnemy() {
		return true;
	}
    /**
     * 
     * @return true si l'objet est un "ami" du joueur
     */
    public boolean isFriend() {
		return false;
	}
    /**
     * 
     * @return true si l'objet est sorti de l'écran
     */
    private boolean estSorti() {
		return getTop()>game().height() || getLeft()<0 || getTop()<0 || getRight()>game().getWidth();
	}
    
}
