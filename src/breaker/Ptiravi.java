package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import iut.ObjetTouchable;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Super-ennemi, nécessitant plusieurs impacts pour être tué
 */
public class Ptiravi extends ObjetTouchable {
	/**
	 * Représente la vie du chien. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=100;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private GestionMap gestion;
        private int xp = 0;

        public Ptiravi(Game g, int x, int y, GestionMap actualistion){
            super(g,"ptiravi",x,y);   
            gestion = actualistion;
            this.crier();
            gestion.setPokeBallActive(true);
            gestion.getPlayer().changeScore(110);
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
	 * Déplace l'objet
	 * @param dt le temps écoulé en millisecondes depuis le précédent déplacement
	 */
        /*@author heijnenw*/
	public void
        move(long dt) {
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(32000);
            if(aleatoire>=0 && aleatoire <10){
                this.déplaceX(+1);
            }
            if(aleatoire>=10 && aleatoire <20){
                this.déplaceX(-1);
            }
            if(aleatoire>=20 && aleatoire <30){
                this.déplaceY(1);
            }
            if(aleatoire>=30 && aleatoire <40){
                this.déplaceY(-1);
            }
            if(aleatoire>=40 && aleatoire <50){
                this.tirer();
            }
            
            xp += dt;
            if(xp >= 25000){
                Leveinard leveinard = new Leveinard(game(), this.getLeft(), this.getTop(), gestion);
                game().add(leveinard);
                aleatoire = alea.nextInt(400);
                gestion.getPlayer().changeScore(aleatoire);
                game().remove(this);
            }
	}

	public int getVie() {
		return this.vie;
	}


        public void crier(){
            Audio a = new Audio("ptiravi");
            a.start();
        }
        
        private void meurt(){
            this.crier();
            gestion.setPokeBallActive(false);
            game().remove(this);
        }

        @Override
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie*1;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-10,getTop()-5,w,4);
        }
        
    protected void tirer() {
        BouleDeSoin soin = new BouleDeSoin(this, gestion);
        game().add(soin);
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
        return false;
    }
    
        private void déplaceX(int sens)
        {
            final int nbPixels = 40; 
            int dx = sens*nbPixels;
            int futurLeft = getLeft()+dx;
            int futurRight = getRight()+dx;
            
            if(futurLeft>=game().width()/2+80 && futurRight<game().width()-80){
                this.moveX(dx);
            }
        }
        /*
         * gére les déplacements à la verticale
         */
        private void déplaceY(int sens)
        {
            final int nbPixels = 40; 
            int dx = sens*nbPixels;
            int futurUp = getTop()+dx;
            int futurDown = getBottom()+dx;
            
            if(futurDown>=80 && futurUp<game().height()-80)
                this.moveY(dx);                            
        }
}