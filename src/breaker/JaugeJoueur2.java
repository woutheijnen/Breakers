package breaker;

import iut.Objet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Indique visuellement la vie restant au joueur
 * @author HEIJNEN Wout
 */

public class JaugeJoueur2 extends Objet {
        public JaugeJoueur2(Joueur2 j) {
            super(j.game(),"",10,10);
            this.j = j;
        }
	/**
	 * lie le joueur avec sa jauge pour permettre la mise à jour
	 */
	private final Joueur2 j;

	/**
	 * Teste la collision entre deux objets
	 * @return true si la collision a eu lieu
	 */
	public boolean collision(Objet o) {
		return false;
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
		return false;
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
            
	}
        
        /**
		* Dessine la jauge 
		*/
        public void draw(Graphics g){
            int w = j.getVie();
            g.setColor(Color.RED);
            g.fillRect(2,game().getHeight()-20,w*2,10);
        }
}