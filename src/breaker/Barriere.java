package breaker;

import iut.Objet;
import iut.ObjetTouchable;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 */
public class Barriere extends ObjetTouchable {
    private final double vitesse=0.2;
    private int vie = 5;
    private ObjetTouchable j;
        
        /**
         * Constructeur de la classe Barriere
         * @param source
         * @param ges 
         */
        public Barriere(ObjetTouchable source, GestionMap ges){
            super(source.game(),"barriere",source.getMiddleX(),source.getBottom());
            Random alea = new Random();
            int aleatoire = alea.nextInt(2);
            if(aleatoire == 0 && ges.getPlayer().estEnVie()){
                j = ges.getPlayer();
            }else{
                if(ges.isMultiPlayer() && ges.getPlayer2().estEnVie()){
                    j = ges.getPlayer2();
                }else{
                    j = ges.getPlayer();
                }
            }
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
	public void effect(Objet o) {
		if(o.isEnnemy()) // objet "coté joueur"
                {
                    vie --;
                    if(vie <= 0){
                        game().remove(this);
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
                if(j.getMiddleX()+80<getMiddleX() && j.getMiddleY()<getMiddleY()){
                        moveX(dt*-vitesse);
                        moveY(dt*-vitesse);
                }
                if(j.getMiddleX()+80>getMiddleX() && j.getMiddleY()<getMiddleY()){
                        moveX(dt*vitesse);
                        moveY(dt*-vitesse);
                }
                if(j.getMiddleX()+80<getMiddleX() && j.getMiddleY()>getMiddleY()){
                        moveX(dt*-vitesse);
                        moveY(dt*vitesse);
                }
                if(j.getMiddleX()+80>getMiddleX() && j.getMiddleY()>getMiddleY()){
                        moveX(dt*vitesse);
                        moveY(dt*vitesse);
                }
                if(j.getMiddleX()+80>=getMiddleX()-10 && j.getMiddleX()<=getMiddleX()+10 && j.getMiddleY()<getMiddleY()){
                        moveY(dt*-vitesse);
                }
                if(j.getMiddleX()+80>=getMiddleX()-10 && j.getMiddleX()<=getMiddleX()+10 && j.getMiddleY()>getMiddleY()){
                        moveY(dt*vitesse);
                }
                if(j.getMiddleX()+80<getMiddleX() && j.getMiddleY() >= getMiddleY()-10 && j.getMiddleY() <= getMiddleY()+10){
                        moveX(dt*-vitesse);
                }
                if(j.getMiddleX()+80>getMiddleX() && j.getMiddleY() >= getMiddleY()-10 && j.getMiddleY() <= getMiddleY()+10){
                        moveX(dt*vitesse);
                }
                if(estSorti()){
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