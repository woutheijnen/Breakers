package breaker;

import iut.Objet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Indique visuellement la vie restant au joueur
 * @authorIslem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */

public class JaugeJoueur extends Objet {
        public JaugeJoueur(Joueur j) {
            super(j.game(),"",10,10);
            this.j = j;
        }
	/**
	 * lie le joueur avec sa jauge pour permettre la mise à jour
	 */
	private final Joueur j;

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
         * Dessine la jauge du joueur
         */
        public void draw(Graphics g){
            int w = j.getVie();
            g.setColor(Color.RED);
            g.fillRect(getLeft()+2,getTop()+2,w*2,10);
        }
}