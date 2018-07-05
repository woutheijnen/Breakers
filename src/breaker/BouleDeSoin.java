package breaker;

import iut.Objet;
import iut.ObjetTouchable;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 */
public class BouleDeSoin extends Ennemi {
    private final double vitesse=0.05;
    private ObjetTouchable joueur;
    private GestionMap gestion;
    
        /**
         * Constructeur de la classe BouleDeSoin
         * @param source
         * @param ges 
         */
        public BouleDeSoin(ObjetTouchable source, GestionMap ges){
            super(source.game(),"bouleDeSoin",source.getMiddleX(),source.getBottom());
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
            gestion = ges;
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
	public void effect(Objet o) {
		if(o.isEnnemy()) // objet "coté joueur"
                {
                    game().remove(this);
                }
                if(o == gestion.getPlayer()){
                    gestion.getPlayer().changeVie(10);
                    game().remove(this);
                }
                if(o == gestion.getPlayer2() && o != null){
                    gestion.getPlayer2().changeVie(10);
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

    @Override
    public int getVie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}