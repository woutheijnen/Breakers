package breaker;

import iut.Objet;
import iut.ObjetTouchable;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class EclairExplosion extends Ennemi {
        private int dmg=3;
        private GestionMap gestion;
    
        public EclairExplosion(Ennemi source, GestionMap ges){
            super(source.game(),"eclairexp",source.getMiddleX()-50,source.getMiddleY()-0);
            gestion = ges;
        }
	/**
	 * effet d'une collision entre l'objet et le paramètre
         * @param o objet "coté joueur" 
	 */
	public void effect(Objet o) {
            dmg --;
            if(dmg==0){
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
		return true;
	}

        
	/**
	 * Déplace l'objet
	 * @param dt le temps écoulé en millisecondes depuis le précédent déplacement
	 */
	public void move(long dt) {
            if(!gestion.isExplosionPossible()){
                game().remove(this);
            }
            
                Random alea = new Random();
                int aleatoire;

                aleatoire = alea.nextInt(20000);
                if(aleatoire>=40 && aleatoire <50){
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