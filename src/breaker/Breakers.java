package breaker;

import iut.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Représente le jeu "Breakers".
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 */
public class Breakers extends Game {
    private Joueur player;
    
        public Breakers()
        {
            super(1920,980,"Breakers");
            
	}
        
	
        /**
	 * crée tous les objets du jeu. Appelé en début de partie.
         * 
	 */
	public void createObjects() 
        {
                Herbe h = new Herbe(this, 0, 0);
                add(h);
              
                // creation du joueur
                player = new Joueur(this);
                add (player);

                // commande pour creer l'interaction avec le clavier pour jouer le player
                addKeyInteractiveObject(player);

                // creation de la jauge de vie du joueur
                JaugeJoueur vie = new JaugeJoueur(player);
                add (vie);
                
                Score score = new Score(player);
                add (score);
                
                Temps temps = new Temps(player);
                add (temps);
                
                GestionMap start = new GestionMap(this, player);
                add(start);
                
                player.setGestionMap(start);
        }
        
	/**
	 * Dessine le fond d'écran
	 * @param g la surface d'affichage
         * 
	 */
	public void drawBackground(Graphics g) {
     
            g.setColor(Color.gray);
            g.fillRect(0,0,getWidth(),getHeight()); 
                
	}

	/**
	 * Action à exécuter lorsque le jeu est perdu
         * 
	 */
	public void perdu() {
         
            Font f = new Font("Courier",Font.BOLD,50);
            Graphics g = this.getGraphics();
            
            g.setFont(f);
            g.setColor(Color.red);
            g.drawString("GAME OVER", 400, 400);
            g.drawString("Votre Score : "+player.getScore(), 340, 500);
            try {
                Thread.sleep(2000000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Breakers.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
        
        
        

	/**
	 * Action à exécuter lorsque le jeu est gagné
         * 
	 */
	public void gagne() {
        Graphics g=this.getGraphics();
            ecrire(g,"Felicitation vous avez gagné!!!",400,400);
            g.drawString("Votre Score : "+player.getScore(), 340, 500);
	}
        
        private void ecrire(Graphics g,String s,int x,int y){
            
            Font font = new Font("Courier", Font.BOLD, 20);
            g.setFont(font);
            g.setColor(Color.green);
            g.drawString(s, x, y);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Breakers.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
}
