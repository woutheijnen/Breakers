package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import iut.ObjetTouchable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
  * classe correspondant à un personnage qui peut aider le joueur
  */
public class Sulfura extends ObjetTouchable {
	/**
	 * Représente la vie de Sulfura. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=90;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Ennemi e;
        private GestionMap gestion;
        private int xp = 0;
        private boolean lanceFlammeActif = false;
        private int compteurLanceFlamme = 0;
        private int i = 0;

        public Sulfura(Game g, int x, int y, GestionMap actualistion){
            super(g,"sulfura",x,y);   
            gestion = actualistion;
            this.crier();
            gestion.setPokeBallActive(true);
            gestion.getPlayer().changeScore(2261);
        }
        
        
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
	 * DÃ©placement du perso en fonction d'une variable alÃ©atoire (contrÃ´le de l'IA)	 	 
	 * @param dt le temps Ã©coulÃ© en millisecondes depuis le prÃ©cÃ©dent dÃ©placement
	 */
        
	public void move(long dt) {
            Ennemi temp = gestion.getCurrentBoss();
            if(temp!=null){
                e = temp;
            }
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(1250);
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
            if(aleatoire==50){
                lanceFlammeActif = true;
            }
            
            if(lanceFlammeActif){
                i += dt;
                if(i>=100){
                    i -= 100;
                    LanceFlamme lanceFlamme = new LanceFlamme(this, e);
                    game().add(lanceFlamme);
                    compteurLanceFlamme ++;
                }
                if(compteurLanceFlamme >8){
                    lanceFlammeActif = false;
                    i = 0;
                    compteurLanceFlamme = 0;
                }
            }
	}

	public int getVie() {
		return this.vie;
	}


        public void crier(){
            Audio a = new Audio("sulfura");
            a.start();
        }
        
        private void meurt(){
            this.crier();
            gestion.setPokeBallActive(false);
            game().remove(this);
        }
        /**
	 *déssine la jauge de vie du perso
	 */
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
        /**
        *creer l'attaque du perso
        */
        protected void tirer() {
            DanseFlamme danseflamme = new DanseFlamme(this);
            game().add(danseflamme);
        }    

        @Override
        public boolean isFriend() {
            return true;
        }

        @Override
        public boolean isEnnemy() {
            return false;
        }
        /**
	 *Action : déplace le perso à l'horizontal
	 *@param sens entier indiquant le sens de direction du perso
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