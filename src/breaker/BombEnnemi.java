package breaker;

import iut.Audio;
import iut.Objet;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class BombEnnemi extends Ennemi {
    private final double vitesse=0.15;
    private int vie = 3;
    private int oldXCord;
    private int oldYCord;
    private GestionMap ge;
    
    public BombEnnemi(Ennemi source, int x, int y, GestionMap gestion, int mode){
            super(source.game(),"BombEnnemi",source.getMiddleX(),source.getMiddleY());
            if(mode == 0){
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
            }else{
                oldXCord = x;
                oldYCord = y;
            }
            ge = gestion;
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
         * @param o objet "coté joueur"
	 */
	public void effect(Objet o) {
		if(o.isFriend())  
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
        /**
        * ajoute et puis retire le projectile du jeu
        */
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