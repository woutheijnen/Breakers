/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Samus extends Ennemi {
    
    private int vie=20;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Joueur joueur;
        private GestionMap gestion;
    
    public Samus (Game g, int x, int y,GestionMap gm){
        super(g,"Samus",x,y);            
        joueur = gm.getPlayer();
        gestion = gm;
    }

        /*
         * gére les projectiles1
         */
    @Override
    protected void tirer() {
        Audio tir = new Audio("samusTire");
        tir.start();
        BouleEnergie bouleEner = new BouleEnergie(this, gestion);
        game().add(bouleEner);
    }
    
        /*
         * gére les projectiles spéciales1
         */
    protected void tirerSp() {
        Audio bmb = new Audio("samusBomb");
        bmb.start();
        EnergieBomb enerBmb = new EnergieBomb(this, joueur, gestion);
        game().add(enerBmb);
    }
    /*
     * deplace l'objet a un endroit aléatoir sur la map
     */
    protected void teleport() {
        Audio tele = new Audio("samusTeleport");
        tele.start();
        Random alea = new Random();
        int newX = alea.nextInt(game().getWidth()/2-160)- (this.getMiddleX())+game().getWidth()/2+80;
        int newY = alea.nextInt(game().getHeight()/2-160)-this.getMiddleY()/2+80;
        this.moveX(newX);
        this.moveY(newY);
    }

         /*
         * sortie audio de l'ennemi
         */
    @Override
    protected void crier() {
       Audio crie = new Audio("samusMal");
       crie.start();
    }

    @Override
    public int getVie() {
        return this.vie;
    }

    @Override
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

    @Override
    public void move(long l) {
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
            if(aleatoire>=150 && aleatoire <157){
                tirerSp();
            }
            if(aleatoire>=157 && aleatoire <=180){
                teleport();
            }
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
    
    public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie*8;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-50,getTop()-5,w,4);
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
