package breaker;

import iut.Audio;
import iut.Objet;
import iut.ObjetTouchable;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class CarapaceR extends Ennemi {
    private final double vitesse=0.15;
    private final ObjetTouchable joueur;
    private Audio r;
    private int i = 0;
    
        public CarapaceR(Ennemi source, GestionMap ges){
            super(source.game(),"carapaceR",source.getMiddleX(),source.getBottom());
            Random alea = new Random();
            int aleatoire = alea.nextInt(2);
            if(aleatoire == 0 && ges.getPlayer().estEnVie()){
                joueur = ges.getPlayer();
            }else{
                if(ges.isMultiPlayer() && ges.getPlayer2().estEnVie()){
                    joueur = ges.getPlayer2();
                }else{
                    joueur = ges.getPlayer();
                }
            }
            r = new Audio("carapaceR");
            r.start();
        }
        
	
        /**
         * effet d'une collision entre l'objet et le paramètre
         * @param o objet "coté joueur"
         */
	public void effect(Objet o) {
		if(o.isFriend()) 
                {
                    r.stop();
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
                if(joueur == null){
                    game().remove(this);
                }
                if(joueur.getMiddleX()<getMiddleX() && joueur.getMiddleY()<getMiddleY()){
                        moveX(dt*-vitesse);
                        moveY(dt*-vitesse);
                }
                if(joueur.getMiddleX()>getMiddleX() && joueur.getMiddleY()<getMiddleY()){
                        moveX(dt*vitesse);
                        moveY(dt*-vitesse);
                }
                if(joueur.getMiddleX()<getMiddleX() && joueur.getMiddleY()>getMiddleY()){
                        moveX(dt*-vitesse);
                        moveY(dt*vitesse);
                }
                if(joueur.getMiddleX()>getMiddleX() && joueur.getMiddleY()>getMiddleY()){
                        moveX(dt*vitesse);
                        moveY(dt*vitesse);
                }
                if(joueur.getMiddleX()==getMiddleX() && joueur.getMiddleY()<getMiddleY()){
                        moveY(dt*-vitesse);
                }
                if(joueur.getMiddleX()==getMiddleX() && joueur.getMiddleY()>getMiddleY()){
                        moveY(dt*vitesse);
                }
                if(joueur.getMiddleX()<getMiddleX() && joueur.getMiddleY()==getMiddleY()){
                        moveX(dt*-vitesse);
                }
                if(joueur.getMiddleX()>getMiddleX() && joueur.getMiddleY()==getMiddleY()){
                        moveX(dt*vitesse);
                }
                if(estSorti() || (joueur.getMiddleX()==getMiddleX() && joueur.getMiddleY() == getMiddleY())){
                    r.stop();
                    game().remove(this);
                }
                
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