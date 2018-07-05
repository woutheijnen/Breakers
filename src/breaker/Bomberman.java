package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Super-ennemi, nécessitant plusieurs impacts pour être tué
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class Bomberman extends Ennemi {
	/**
	 * Représente la vie du chien. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=40;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Joueur joueur;
        private GestionMap gestion;

        public Bomberman(Game g, int x, int y,GestionMap gm){
            super(g,"bomberman",x,y);            
            joueur = gm.getPlayer();
            gestion = gm;
        }
        
       /**
	 * Action : effet d'une collision entre l'objet et le paramètre
         * @param o objet "coté joueur"
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
            
            aleatoire = alea.nextInt(2000);
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
            if(aleatoire>=50 && aleatoire <60){
                this.tirer2();
            }
	}
        /**
         * 
         * @return la vie de l'ennemi
         */
	public int getVie() {
		return this.vie;
	}


        public void crier(){
            Audio a = new Audio("bombermanmal");
            a.start();
        }
        /**
         * retire l'ennemi du jeu
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

        @Override
        /**
         *ajoute une jauge
         */
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            
            int w = vie*4;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-50,getTop()-5,w,4);
        }
        
    @Override
    /**
     * ajoute les tires de l'ennemi dans le jeu
     */
    protected void tirer() {
        Audio bm = new Audio("bombermantire");
        bm.start();
        BombEnnemi bmb = new BombEnnemi(this, joueur.getMiddleX(), joueur.getMiddleY(), gestion, 0);
        game().add(bmb);
    }
    
    protected void tirer2() {
        Audio bm = new Audio("bombermantire");
        bm.start();
        Random alea = new Random();
        int aleaX = alea.nextInt(game().getWidth()/2);
        int aleaY = alea.nextInt(game().getHeight()+80)-160;
        BombEnnemi bmb = new BombEnnemi(this, aleaX, aleaY, gestion, 1);
        game().add(bmb);
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