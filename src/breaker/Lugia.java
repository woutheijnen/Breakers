package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import iut.ObjetTouchable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Super-ennemi, nécessitant plusieurs impacts pour être tué
 */
public class Lugia extends ObjetTouchable {
	/**
	 * Représente la vie du chien. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=110;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Ennemi e;
        private GestionMap gestion;
        private int xp = 0;
        private boolean aeroblastActif = false;
        private int compteurAeroblast = 0;
        private int i = 0;

        public Lugia(Game g, int x, int y, GestionMap actualistion){
            super(g,"lugia",x,y);   
            gestion = actualistion;
            this.crier();
            gestion.setPokeBallActive(true);
            gestion.getPlayer().changeScore(2306);
        }
        
        /*@author heijnenw*/
	public void effect(Objet o) {
             if(o.isEnnemy())
            {
                //vie --;
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
            Ennemi temp = gestion.getCurrentBoss();
            if(temp!=null){
                e = temp;
            }
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(750);
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
                aeroblastActif = true;
            }
            
            if(aeroblastActif){
                i += dt;
                if(i>=250){
                    i -= 250;
                    Aeroblast aeroblast = new Aeroblast(this, e);
                    game().add(aeroblast);
                    compteurAeroblast ++;
                }
                if(compteurAeroblast >10){
                    aeroblastActif = false;
                    i = 0;
                    compteurAeroblast = 0;
                }
            }
            
            xp += dt;
            if(xp >= 50000){
                vie += 55;
                if(vie>=110){
                    vie = 110;
                }
                xp -= 50000;
            }
	}

	public int getVie() {
		return this.vie;
	}


        public void crier(){
            Audio a = new Audio("lugia");
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
            int v = xp/10000;
            g.setColor(Color.RED);
            g.fillRect(getLeft(),getTop()-5,w,4);
        }  

    @Override
    public boolean isFriend() {
        return true;
    }

    @Override
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