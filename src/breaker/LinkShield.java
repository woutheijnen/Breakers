package breaker;

import iut.Objet;

/**
 *@author les breakers
 * Projectile lancé par l'ennemi pour tuer le joueur.
 */
public class LinkShield extends Ennemi {
    private final double vitesse=0.1;
    private final Ennemi link;
    private int vie = 5;
    
        public LinkShield(Ennemi source){
            super(source.game(),"shield",source.getMiddleX(),source.getBottom());
            link = source;
        }
        
	/**
	 * gére la collision avec les autres objects
	 */
	public void effect(Objet o) {
		if(o.isFriend()) // objet "coté joueur"
                {
                    vie --;
                    if(vie <=0){
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
                if(link == null){
                    game().remove(this);
                }
                if(link.getMiddleX()-80<getMiddleX() && link.getMiddleY()<getMiddleY()){
                        moveX(dt*-vitesse);
                        moveY(dt*-vitesse);
                }
                if(link.getMiddleX()-80>getMiddleX() && link.getMiddleY()<getMiddleY()){
                        moveX(dt*vitesse);
                        moveY(dt*-vitesse);
                }
                if(link.getMiddleX()-80<getMiddleX() && link.getMiddleY()>getMiddleY()){
                        moveX(dt*-vitesse);
                        moveY(dt*vitesse);
                }
                if(link.getMiddleX()-80>getMiddleX() && link.getMiddleY()>getMiddleY()){
                        moveX(dt*vitesse);
                        moveY(dt*vitesse);
                }
                if(link.getMiddleX()-80==getMiddleX() && link.getMiddleY()<getMiddleY()){
                        moveY(dt*-vitesse);
                }
                if(link.getMiddleX()-80==getMiddleX() && link.getMiddleY()>getMiddleY()){
                        moveY(dt*vitesse);
                }
                if(link.getMiddleX()-80<getMiddleX() && link.getMiddleY()==getMiddleY()){
                        moveX(dt*-vitesse);
                }
                if(link.getMiddleX()-80>getMiddleX() && link.getMiddleY()==getMiddleY()){
                        moveX(dt*vitesse);
                }
	}

        /*
         * affiche une explosion et enléve l'objet
         */
        
	/**
	 * indique si le tir est "à l'intérieur" de l'écran ou non
	 * @return true si le tir a quitté l'écran (par le bas)
	 */

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