package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Super-ennemi, nécessitant plusieurs impacts pour être tué
 */
public class ZombiePlante extends Ennemi {
	/**
	 * Représente la vie du chien. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=3;
        private double sensX = 1.5;
        private double sensY = 0.5;

        public ZombiePlante(Game g, int x, int y){
            super(g,"Zombie",x,y);
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
        /*@author heijnenw*/
	public void effect(Objet o) {
             if(o.isFriend())
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
	public void move(long dt) {
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(4000);
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
            if(aleatoire>=40 && aleatoire <60){
                this.tirer();
            }
	}

	public int getVie() {
		return this.vie;
	}


        public void crier(){
            Audio a = new Audio("zombie");
            a.start();
        }
        
        private void meurt(){
            game().remove(this);
        }

        @Override
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie*10;
            g.setColor(Color.RED);
            g.fillRect(getLeft(),getTop()-2,w,4);
        }
    @Override
    protected void tirer() {
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(80);
            if(aleatoire>=0 && aleatoire <10){
                TirEnnemiD tirEND = new TirEnnemiD(this);
                game().add(tirEND);
            }
            if(aleatoire>=10 && aleatoire <20){
                TirEnnemiS tirEND = new TirEnnemiS(this);
                game().add(tirEND);
            }
            if(aleatoire>=20 && aleatoire <30){
                TirEnnemiQ tirEND = new TirEnnemiQ(this);
                game().add(tirEND);
            }
            if(aleatoire>=30 && aleatoire <40){
                TirEnnemiZ tirEND = new TirEnnemiZ(this);
                game().add(tirEND);
            }
            if(aleatoire>=40 && aleatoire <50){
                TirEnnemiZQ tirEND = new TirEnnemiZQ(this);
                game().add(tirEND);
            }
            if(aleatoire>=50 && aleatoire <60){
                TirEnnemiZD tirEND = new TirEnnemiZD(this);
                game().add(tirEND);
            }
            if(aleatoire>=60 && aleatoire <70){
                TirEnnemiSQ tirEND = new TirEnnemiSQ(this);
                game().add(tirEND);
            }
            if(aleatoire>=70 && aleatoire <80){
                TirEnnemiSD tirEND = new TirEnnemiSD(this);
                game().add(tirEND);
            }
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