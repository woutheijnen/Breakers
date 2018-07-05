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
public class Electrode extends ObjetTouchable {
	/**
	 * Représente la vie du chien. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=60;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Ennemi e;
        private GestionMap gestion;
        private int xp = 0;

        /**
         * Constructeur de la classe Electrode
         * @param g
         * @param x
         * @param y
         * @param actualistion 
         */
        public Electrode(Game g, int x, int y, GestionMap actualistion){
            super(g,"electrode",x,y);   
            gestion = actualistion;
            this.crier();
            gestion.setPokeBallActive(true);
            gestion.getPlayer().changeScore(168);
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
            
            aleatoire = alea.nextInt(1000);
            if(aleatoire>=0 && aleatoire <10){
                int vitesse = 40;
                if(e.getMiddleX()<getMiddleX() && e.getMiddleY()<getMiddleY()){
                        moveX(-vitesse);
                        moveY(-vitesse);
                }
                if(e.getMiddleX()>getMiddleX() && e.getMiddleY()<getMiddleY()){
                        moveX(vitesse);
                        moveY(-vitesse);
                }
                if(e.getMiddleX()<getMiddleX() && e.getMiddleY()>getMiddleY()){
                        moveX(-vitesse);
                        moveY(vitesse);
                }
                if(e.getMiddleX()>getMiddleX() && e.getMiddleY()>getMiddleY()){
                        moveX(vitesse);
                        moveY(vitesse);
                }
                if(e.getMiddleX()==getMiddleX() && e.getMiddleY()<getMiddleY()){
                        moveY(-vitesse);
                }
                if(e.getMiddleX()==getMiddleX() && e.getMiddleY()>getMiddleY()){
                        moveY(vitesse);
                }
                if(e.getMiddleX()<getMiddleX() && e.getMiddleY()==getMiddleY()){
                        moveX(-vitesse);
                }
                if(e.getMiddleX()>getMiddleX() && e.getMiddleY()==getMiddleY()){
                        moveX(vitesse);
                }
                if(estSorti()){
                    this.meurt();
                }
            }
	}

        /**
         * @return la vie de Electrode
         */
	public int getVie() {
		return this.vie;
	}

        /**
         * crie de Electrode
         */
        public void crier(){
            Audio a = new Audio("electrode");
            a.start();
        }
        
        /**
         * procedure quand Electrode meurt
         */
        private void meurt(){
            Audio e = new Audio("explosion");
            e.start();
            BombExplosionF1 exp1 = new BombExplosionF1(this, gestion);
            game().add(exp1);
            gestion.setPokeBallActive(false);
            game().remove(this);
        }

        /**
         * dessine la jauge de vie de Electrode
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
            g.fillRect(getLeft(),getTop()-5,w,4);
        }
        
        protected void tirer() {

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

        private boolean estSorti() {
                    return getTop()>game().height()-80 || getLeft()<80 || getTop()<80 || getRight()>game().getWidth()-80;
            }
}