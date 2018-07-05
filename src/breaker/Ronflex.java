package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import iut.ObjetTouchable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
  * classe correspondant à un personnage qui peut aider le joueur
  */
public class Ronflex extends ObjetTouchable {
	/**
	 * Représente la vie de Ronflex. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=160;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private GestionMap gestion;
        private int xp = 0;

        public Ronflex(Game g, int x, int y, GestionMap actualistion){
            super(g,"ronflex",x,y);   
            gestion = actualistion;
            this.crier();
            gestion.setPokeBallActive(true);
            gestion.getPlayer().changeScore(189);
        }
        
        /*@author heijnenw*/
	public void effect(Objet o) {
             if(o.isEnnemy())
            {
                vie --;
                this.crier();
                if(vie<=0){
                    this.meurt();
                }
            }
	}
        
	/**
	 * Deplacement du perso en fonction d'une variable alÃ©atoire (contrÃ´le de l'IA)	 	 
	 * @param dt le temps Ã©coulÃ© en millisecondes depuis le prÃ©cÃ©dent dÃ©placement
	 */
	public void move(long dt) {
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(2000);
            if(aleatoire>=40 && aleatoire <50){
                this.tirer();
            }
            
            xp += dt;
            if(xp >= 50000){
                vie += 80;
                if(vie>=160){
                    vie = 160;
                }
                xp -= 50000;
            }
	}

	public int getVie() {
		return this.vie;
	}


        public void crier(){
            Audio a = new Audio("ronflex");
            a.start();
        }
        
        private void meurt(){
            this.crier();
            gestion.setPokeBallActive(false);
            game().remove(this);
        }
        
        /**
         * dessine la jauge de vie du personnage
         * @param g
         * @throws Exception 
         */
        @Override
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie*1;
            int v = xp/10000;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-60,getTop()-5,w,4);
        }
        /**
         * créer l'attaque du perso
         */
        protected void tirer() {
            Ronfle ronfle = new Ronfle(this, gestion.getCurrentBoss());
            game().add(ronfle);
        }


        @Override
        public boolean isFriend() {
            return true;
        }

        @Override
        public boolean isEnnemy() {
            return false;
        }
}