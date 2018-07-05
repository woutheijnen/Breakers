/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breaker;

import iut.Game;
import iut.Objet;

/**
 * classe qui contient le menu principale du jeu
 * @author Islem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */
public class Menu1 extends Objet{
        public Menu1(Game g, int x, int y){
            super(g,"Menu1",x,y);
        }
        
	/**
	 * Test la collision entre deux objets
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
}
