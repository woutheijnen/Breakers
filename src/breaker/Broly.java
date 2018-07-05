package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *@author les breakers
 * Super-ennemi, nécessitant plusieurs impacts pour être tué
 */
public class Broly extends Ennemi {
	
	private int vie=25;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Joueur joueur;
        private GestionMap gestion;

        public Broly(Game g, int x, int y,GestionMap gm){
            super(g,"broly",x,y);            
            joueur = gm.getPlayer();
            gestion = gm;
        }
        
        /**
	 * gére la collision avec les autres objects
	 */
	public void effect(Objet o) {
             if(o.isFriend())
            {
                vie --;
                Random alea = new Random();
                int aleatoire = alea.nextInt(200);
                joueur.changeScore(aleatoire);
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
	public void move(long dt) {
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(5000);
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
            if(aleatoire>=40 && aleatoire <150){
               this.tirer();
            }
            if(aleatoire>=150 && aleatoire <160){
                tirerSp();
            }
            if(aleatoire>=301 && aleatoire <=310){
                tireSp2();
            }
            if(aleatoire>=161 && aleatoire <=300){
                tirer2();
            }
	}

        /**
	*@return la vie de l'ennemi
	*/
	public int getVie() {
		return this.vie;
	}


        /*
         * sortie audio de l'ennemi
         */
        public void crier(){
            Audio a = new Audio("brolyTouche");
            a.start();
        }
        /*
         * enléve l'objet et aumente le score
         */
        private void meurt(){
            Random alea = new Random();
            int aleatoire = alea.nextInt(500);
            joueur.changeScore(aleatoire);
            gestion.setExplosionPossible(false);
            if(!gestion.getPlayer().estEnVie()){
                gestion.getPlayer().restitution();
            }
            if(gestion.getPlayer2() != null){
                if(!gestion.getPlayer2().estEnVie()){
                    gestion.getPlayer2().restitution();
                }
            }
            game().remove(this);
        }

       /**
        * ajoute une jauge
        * @param g
        * @throws Exception 
        */
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            
            int w = vie*6;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-50,getTop()-5,w,4);
        }
        /*
         * gére les projectiles1
         */
    @Override
    protected void tirer() {
        BouleDeDragon bouleDeDragon = new BouleDeDragon(this, joueur.getMiddleX(), joueur.getMiddleY(), gestion);
        game().add(bouleDeDragon);
    }
        /*
         * gére les projectiles2
         */
    protected void tirer2() {
        Random alea = new Random();
        int aleaX = alea.nextInt(game().getWidth()/2);
        int aleaY = alea.nextInt(game().getHeight()+80)-160;
        BouleDeDragon2 bouleDeDragon = new BouleDeDragon2(this, aleaX, aleaY, gestion);
        game().add(bouleDeDragon);
    }
        /*
         * gére les projectiles spéciales1
         */
    protected void tirerSp(){
        
    }
        /*
         * gére les projectiles spéciales2
         */
    protected void tireSp2(){
        
    }
    
        /*
         * gére les déplacements à l'horizontale
         */
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