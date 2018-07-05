/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package breaker;

import iut.Audio;
import iut.Objet;
import java.util.Random;

/**
 * représente une attaque lancée contre l'ennemi
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class EnergieBomb extends Ennemi {
    private final double vitesse=0.15;
    private int oldXCord;
    private int oldYCord;
    private int vie = 3;
    private GestionMap ge;
    
    public EnergieBomb(Ennemi source, Joueur j, GestionMap gestion){
            super(source.game(),"energieBomb",source.getLeft(),source.getMiddleY());
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
     *action a faire lors d'une collision
     * @param o objet "coté joueur"
     */
    public void effect(Objet o) {
        if(o.isFriend()) // objet "coté joueur"
                {
                    vie --;
                    if(vie<=0){
                        explosion();
                    }
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
                    explosion();
                }
    }
    /**
     * @return false si l'objet est un "ennemi" du joueur
     */
    public boolean isEnnemy() {
		return true;
	}
    /**
     * @return true si l'objet est un "ami" du joueur
     */
    public boolean isFriend() {
		return false;
	}
    /**
    * ajoute et puis retire le projectile du jeu
    */
    public void explosion() {
		BombExplosionF1 expl= new BombExplosionF1(this, ge);
                game().add(expl);
                Audio e = new Audio("explosion");
                e.start();
                game().remove(this);
	}    
    /**
     * indique si le tir est "à l'intérieur" de l'écran ou non
     * @return true si le tir a quitté l'écran 
     */
    private boolean estSorti() {
		return getTop()>game().height() || getLeft()<0 || getTop()<0 || getRight()>game().getWidth();
	}
    
}
