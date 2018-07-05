package breaker;

import iut.Objet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Indique visuellement le temps
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */

public class Temps extends Objet {
        public Temps(Joueur j) {
            super(j.game(),"",0,0);
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
	 * gére la collision avec les autres objects
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
	* Affiche le score sur l'écran
	*/
        public void draw(Graphics g){
            g.setColor(Color.white);
            String w = "Temps: "+j.getTemps();
            g.setColor(Color.WHITE);
            Font f = new Font("Courier",Font.BOLD,20);
            g.setFont(f);
            g.drawString(w, 750, 44);
        }
}