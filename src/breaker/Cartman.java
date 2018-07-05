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
 * Super-ennemi, nécessitant plusieurs impacts pour être tué
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class Cartman extends Ennemi {
	/**
	 * Représente la vie de cartman. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=20;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Joueur joueur;
        private GestionMap gestion;

        public Cartman(Game g, int x, int y,GestionMap gm){
            super(g,"cartman",x,y);            
            joueur = gm.getPlayer();
            gestion = gm;
        }
        
        /**
         * effet d'une collision entre l'objet et le paramètre
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
        /*@author heijnenw*/
	public void
                move(long dt) {
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(3000);
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
	}
        /**
         * 
         * @return la vie de l'ennemi
         */
	public int getVie() {
		return this.vie;
	}

        /**
         * le son jouer lorsque l'ennemi est touché
         */
        public void crier(){
            Audio a = new Audio("cartmanmal");
            a.start();
        }
        /**
         * retire l'ennemi du jeu lorsqu'il meurt
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
         * @param g représente la jauge 
         */
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            
            int w = vie*8;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-50,getTop()-5,w,4);
        }
        
    @Override
    /**
     * ajoute les tires de l'ennemi dans le jeu
     */
    protected void tirer() {
        Audio a = new Audio("cartmantire");
        a.start();
        Bullet b = new Bullet(this);
        game().add(b);
    }
    /**
     * ajoute les tires spéciales de l'ennemi dans le jeu
     */
    protected void tirerSp(){
        Audio a = new Audio("cartmanlance");
        a.start();
        Petard pt = new Petard(this, joueur.getMiddleX(), joueur.getMiddleY(), gestion);
        game().add(pt);
    }
        
        /**
         * déplacements horizontales
         * @param sens 
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