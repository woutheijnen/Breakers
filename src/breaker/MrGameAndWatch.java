package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *@author les breakers
 * Super-ennemi, nécessitant plusieurs impacts pour être tué
 */
public class MrGameAndWatch extends Ennemi {
	
	private int vie;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Joueur joueur;
        private GestionMap gestion;
        private boolean clone;

        public MrGameAndWatch(Game g, int x, int y,GestionMap gm, int pv, boolean isClone){
            super(g,"gameAndWatch",x,y);            
            joueur = gm.getPlayer();
            gestion = gm;
            vie = pv;
            clone = isClone;
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
	public void
                move(long dt) {
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
            if(aleatoire>=40 && aleatoire <150){
                this.tirer();
            }
            if(aleatoire>=150 && aleatoire <200){
                tirer2();
            }
            if(aleatoire==200){
                tireSp();
            }
            if(aleatoire==201){
                seMultiplier();
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
            Audio a = new Audio("gamemal");
            a.start();
        }
        /*
         * enléve l'objet et aumente le score
         */
        private void meurt(){
            if(!clone){
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
            }
            game().remove(this);
        }

        @Override
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie*5;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-50,getTop()-5,w,4);
        }
        /*
         * gére les projectiles1
         */
    @Override
    protected void tirer() {
        Audio t = new Audio("gametire");
        t.start();
        Random alea = new Random();
        int aleatoire = alea.nextInt(4);
        aleatoire ++;
        String s = "pancake"+aleatoire;
        Pancake  p = new Pancake(this, 0, 0, s, gestion, 0);
        game().add(p);
    }
        /*
         * gére les projectiles2
         */
    protected void tirer2() {
        Audio t = new Audio("gametire");
        t.start();
        Random alea = new Random();
        int aleaX = alea.nextInt(game().getWidth()/2);
        int aleaY = alea.nextInt(game().getHeight()+80)-160;
        int aleatoire = alea.nextInt(4);
        aleatoire ++;
        String s = "pancake"+aleatoire;
        Pancake  p = new Pancake(this, aleaX, aleaY, s, gestion, 1);
        game().add(p);
    }
         /*
         * gére les projectiles spéciales
         */
    protected void tireSp(){
        Audio t = new Audio("gamesp");
        t.start();
        for(int i=0; i<10; i++){
            Random alea = new Random();
            int aleaX = alea.nextInt(game().getWidth()/2);
            int aleaY = alea.nextInt(game().getHeight()+80)-160;
            int aleatoire = alea.nextInt(4);
            aleatoire ++;
            String s = "pancake"+aleatoire;
            Pancake  p = new Pancake(this, aleaX, aleaY, s, gestion , 1);
            game().add(p);
        }
    }
        /*
         * crée un nouveau MrGameAndWatch
         */
    protected void seMultiplier(){
        Audio a = new Audio("gamemulti");
        a.start();

        MrGameAndWatch s = new MrGameAndWatch(game(), this.getMiddleX(), this.getMiddleY(), gestion, vie/5, true);
        game().add(s);
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