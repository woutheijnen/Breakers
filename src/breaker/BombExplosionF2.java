package breaker;

import iut.Objet;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class BombExplosionF2 extends Ennemi {
    private GestionMap ge;
        
        public BombExplosionF2(Ennemi source, GestionMap gestion){
            super(source.game(),"BombExplosionF2",source.getLeft(),source.getTop());
            ge = gestion;
        }
	
	public void effect(Objet o) {

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
		return true;
	}

        
	/**
	 * Déplace l'objet
	 * @param dt le temps écoulé en millisecondes depuis le précédent déplacement
	 */
	public void move(long dt) {
            if(!ge.isExplosionPossible()){
                    game().remove(this);
                }
            
                Random alea = new Random();
                int aleatoire;

                aleatoire = alea.nextInt(2000);
                if(aleatoire>=40 && aleatoire <150){
                    BombExplosionF3 exp3 = new BombExplosionF3(this, ge);
                    game().add(exp3);
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
                return false;
	}

    @Override
    public int getVie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}