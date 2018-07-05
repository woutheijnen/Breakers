package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Projectile lancé par l'ennemi pour tuer le joueur.
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class BulletStand extends Ennemi {
	
        private int vie = 10;
        private Joueur joueur;
        private GestionMap gestion;

        public BulletStand(Game g, int x, int y,GestionMap gm){
            super(g,"bulletStand",x,y);
            joueur = gm.getPlayer();
            gestion = gm;
        }
        
	/**
         * représente les effet de la collision
         * @param o représente un objet du coté joueur
         */
	public void effect(Objet o) {
             if(o.isFriend())
            {
                vie --;
                if(vie <=0){
                    this.meurt();
                }
                
            }
	}
        
	/**
	 * Déplace l'objet
	 * @param dt le temps écoulé en millisecondes depuis le précédent déplacement
	 */
	public void
                move(long dt) {
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(16000);
            if(aleatoire>=40 && aleatoire <100){
                this.tirer();
            }
	}
       /**
        * 
        * @param g qui représente la jauge 
        * @throws Exception 
        */         
       public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie*10;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-10,getTop()-5,w,4);
        }
       /**
        * 
        * @return 1 (fonction non utilisée)
        */
	public int getVie() {
		return 1;
	}


        public void crier(){
            
        }
        /**
         * on retire l'objet du jeu lorsqu'il est mort
         */
        private void meurt(){
            game().remove(this);
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
        
    @Override
    /**
     * ajoute les tirs dans le jeu
     */
    protected void tirer() {
        BulletBill b = new BulletBill(this);
        game().add(b);
        Audio bu = new Audio("bill");
        bu.start();
    }
}