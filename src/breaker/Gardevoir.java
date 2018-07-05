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
public class Gardevoir extends ObjetTouchable {
	/**
	 * Représente la vie du chien. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=70;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Ennemi e;
        private GestionMap gestion;
        private int xp = 0;

        /**
         * Constructeur de la classe Gardevoir
         * @param g
         * @param x
         * @param y
         * @param actualistion 
         */
        public Gardevoir(Game g, int x, int y, GestionMap actualistion){
            super(g,"gardevoir",x,y);   
            gestion = actualistion;
            this.crier();
            gestion.setPokeBallActive(true);
            gestion.getPlayer().changeScore(233);
        }
        
        /**
         * Action : effet d'une collision entre l'objet et le paramètre
         * @param o 
         */
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
	public void
        move(long dt) {
            Ennemi temp = gestion.getCurrentBoss();
            if(temp!=null){
                e = temp;
            }
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(1333);
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
            if(aleatoire>=40 && aleatoire <46){
                this.tirer();
            }
            if(aleatoire>=46 && aleatoire <49){
                this.tirerSp();
            }
            if(aleatoire==49){
                this.tirerSp2();
            }
            
            xp += dt;
            if(xp >= 30000){
                vie += 35;
                if(vie > 70){
                    vie = 70;
                }
                xp -= 30000;
            }
	}

        /**
         * @return la vie Gardevoir
         */
	public int getVie() {
		return this.vie;
	}

        /**
         * crie de Gardevoir
         */
        public void crier(){
            Audio a = new Audio("gardevoir");
            a.start();
        }
        
        /**
         * procedure quand Gardevoir meurt
         */
        private void meurt(){
            this.crier();
            gestion.setPokeBallActive(false);
            game().remove(this);
        }

        /**
         * dessine la jauge de vie de Gardevoir
         * @param g
         * @throws Exception 
         */
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie*1;
            int v = xp/10000;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-20,getTop()-5,w,4);
        }
    
    /**
     * tire un objet
     */
    protected void tirer() {
        BoulePsy boule = new BoulePsy(this, e);
        game().add(boule);
    }
    
    /**
     * tire special 1
     */
    protected void tirerSp() {
        Barriere barriere = new Barriere(this, gestion);
        game().add(barriere);
    }
    
    /**
     * tire special 2
     */
    protected void tirerSp2() {
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
    
        /**
         * gère les déplacements horizontaux
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
        
        /**
         * gère les deplacement verticaux
         * @param sens 
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