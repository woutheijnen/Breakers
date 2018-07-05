package breaker;

import iut.Objet;
import iut.ObjetTouchable;
import java.util.Random;

/**
 * Représente le frame n°5 d'une explosion crée par l'ennemi
 * @author Islem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */
public class BombExplosionF5 extends Ennemi {
    private GestionMap ge;
        
        public BombExplosionF5(Ennemi source, GestionMap gestion){
            super(source.game(),"BombExplosionF5",source.getLeft(),source.getTop());
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
	 * Détermine si le frame s'enlève ou reste pendant quelques milisecondes.
	 * @param dt le temps s'écoule en millisecondes depuis le précédent déplacement
	 */
	public void move(long dt) {
            if(!ge.isExplosionPossible()){
                    game().remove(this);
                }
            
                Random alea = new Random();
                int aleatoire;

                aleatoire = alea.nextInt(2000);
                if(aleatoire>=40 && aleatoire <150){
                    game().remove(this);
            }
	}
        
        public void crier(){
            
        }
        
        public void tirer(){
            
        }

	/**
	 * Utilisé pour déterminer si l'explosion est dans l'écran (fonction pas utilisé)
	 * @return true si le tir a quittÃ© l'Ã©cran (par le bas)
	 */
	private boolean estSorti() {
                return false;
	}

    @Override
    public int getVie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}