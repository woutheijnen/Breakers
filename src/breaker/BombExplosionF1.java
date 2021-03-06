package breaker;

import iut.Objet;
import iut.ObjetTouchable;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class BombExplosionF1 extends Ennemi {
    private GestionMap ge;
    
        public BombExplosionF1(ObjetTouchable source, GestionMap gestion){
            super(source.game(),"BombExplosionF1",source.getLeft()-20,source.getTop()-20);
            ge = gestion;
        }
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
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
                    BombExplosionF2 exp2 = new BombExplosionF2(this, ge);
                    game().add(exp2);
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