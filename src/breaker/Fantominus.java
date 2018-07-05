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
public class Fantominus extends ObjetTouchable {
	/**
	 * Représente la vie du chien. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=30;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Ennemi e;
        private GestionMap gestion;
        private int xp = 0;

        /**
         * Constructeur de la classe Fantominus
         * @param g
         * @param x
         * @param y
         * @param actualistion 
         */
        public Fantominus(Game g, int x, int y, GestionMap actualistion){
            super(g,"fantominus",x,y);   
            gestion = actualistion;
            this.crier();
            gestion.setPokeBallActive(true);
            gestion.getPlayer().changeScore(62);
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
        /*@author heijnenw*/
	public void
        move(long dt) {
            Ennemi temp = gestion.getCurrentBoss();
            if(temp!=null){
                e = temp;
            }
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(16000);
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
                Spectrum spectrum = new Spectrum(game(), this.getLeft(), this.getTop(), gestion);
                game().add(spectrum);
                aleatoire = alea.nextInt(200);
                gestion.getPlayer().changeScore(aleatoire);
                game().remove(this);
            }
	}

        /**
         * @return la vie de Fantominus
         */
	public int getVie() {
		return this.vie;
	}

        /**
         * crie de Fantominus
         */
        public void crier(){
            Audio a = new Audio("fantominus");
            a.start();
        }
        
        /**
         * procedure quand Fantominus meurt
         */
        private void meurt(){
            this.crier();
            gestion.setPokeBallActive(false);
            game().remove(this);
        }

        /**
         * dessine la jauge de vie de Fantominus
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
            g.fillRect(getLeft()+10,getTop()-5,w,4);
        }
    
    /**
     * tire un objet
     */
    protected void tirer() {
        BouleOmbre boule = new BouleOmbre(this, e);
        game().add(boule);
    }
    

    /**
     * @return true si l'objet est un "ami" du joueur
     */
    public boolean isFriend() {
        return true;
    }

    /**
     * @return false si l'ojet est un "ennemi" du joueur
     */
    public boolean isEnnemy() {
        return false;
    }
    
        /**
         * gère les deplacements horizontaux
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
         * gère les deplacements verticaux
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