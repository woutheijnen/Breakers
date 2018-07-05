package breaker;

import iut.Objet;
import iut.ObjetTouchable;
import java.util.Random;

/**
 *@author les breakers
 * Projectile lancé par l'ennemi pour tuer le joueur.
 */
public class AutoEclair extends Ennemi {
    private final double vitesse=0.07;
    private final ObjetTouchable joueur;
    private GestionMap ge;
    
        public AutoEclair(Ennemi source, GestionMap ges){
            super(source.game(),"eclair",source.getMiddleX(),source.getBottom());
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
            ge = ges;
        }
        
	/**
	 * gére la collision avec les autres objects
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
                    explosion();
                }
	}

        /*
         * affiche une explosion et enléve l'objet
         */
        public void explosion(){
            EclairExplosion exp1 = new EclairExplosion(this, ge);
            game().add(exp1);
            game().remove(this);
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

        @Override
        protected void tirer() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        protected void crier() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
}