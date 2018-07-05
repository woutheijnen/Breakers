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
public class Bro extends Ennemi {
	/**
	 * Représente la vie de Bro(ennemi). Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
        private Joueur joueur;
        private GestionMap gestion;

        public Bro(Game g, int x, int y,GestionMap gm){
            super(g,"bro",x,y);
            joueur = gm.getPlayer();
            gestion = gm;
        }
        
	
        /**
         * effet d'une collision entre l'objet et le paramètre
         * @param o représente l'objet qui rentre en collision avec Bro
         */
	public void effect(Objet o) {
             if(o.isFriend())
            {
                    this.meurt();
                
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
            if(aleatoire>=40 && aleatoire <100){
                this.tirer();
            }
	}
        /**
         * 
         * @return 1 (fonction non utilisée)
         */
	public int getVie() {
		return 1;
	}


        public void crier(){
            
        }
        /**
         * retire l'ennemi du jeu quand il meurt
         */
        private void meurt(){
            game().remove(this);
        }
        
    @Override
    /**
     * ajoute les tire de l'ennemi qui sont représentés par le "marteau"
     */
    protected void tirer() {
        Marteau m = new Marteau(this, gestion);
        game().add(m);
    }
        /**
         * gère les déplacements de Bro horizontalement
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