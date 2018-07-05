package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * @author les breakers (Heijnen, Zouhairi, Chakrina, Yahiaoui, Gouvernet)
 * Le Boss final, nécessitant plusieurs impacts pour être tué
 */
public class BossFinal extends Ennemi {
	/**
	 * Représente la vie du boss. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=150;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private Joueur joueur;
        private GestionMap ge;

        public BossFinal(Game g, int x, int y,GestionMap ges){
            super(g,"Boss",x-100,y-200);
            joueur = ges.getPlayer();
            ge = ges;
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
        
	public void effect(Objet o) {
             if(o.isFriend())
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
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(1000);
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
                this.tirerSp();
            }
            if(aleatoire>=157 && aleatoire <=160){
                this.tireSp2();
            }
            if(aleatoire==161){
                this.tireSp3();
            }
            if(aleatoire==162){
                this.tireSp4();
            }
            if(aleatoire>=161 && aleatoire <= 200){
                this.tireSp5();
            }
            if(aleatoire>=161 && aleatoire <= 200){
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
            Audio a = new Audio("Boss");
            a.start();
        }
        
        private void meurt(){
            Audio a = new Audio("boss_meurt");
            a.start();
            Random alea = new Random();
            int aleatoire = alea.nextInt(5000);
            joueur.changeScore(aleatoire);
            game().remove(this);
        }

        @Override
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-30,getTop()-5,w,4);
        }
        
    @Override
    protected void tirer() {
        TirEnnemiD tirED = new TirEnnemiD(this);
        TirEnnemiS tirES = new TirEnnemiS(this);
        TirEnnemiQ tirEQ = new TirEnnemiQ(this);
        TirEnnemiZ tirEZ = new TirEnnemiZ(this);
        TirEnnemiZD tirEZD = new TirEnnemiZD(this);
        TirEnnemiZQ tirEZQ = new TirEnnemiZQ(this);
        TirEnnemiSD tirESD = new TirEnnemiSD(this);
        TirEnnemiSQ tirESQ = new TirEnnemiSQ(this);
        game().add(tirED);
        game().add(tirES);
        game().add(tirEQ);
        game().add(tirEZD);
        game().add(tirEZQ);
        game().add(tirESD);
        game().add(tirESQ);
        game().add(tirEZ);
    }
    
    protected void tirer2() {
        AutoEclair eclair = new AutoEclair(this, ge);
        game().add(eclair);
    }
    
    protected void tirerSp(){
        EnergieBomb bomb = new EnergieBomb(this, joueur, ge);
        game().add(bomb);
    }
    
    protected void tireSp2(){
        ZombiePlante s = new ZombiePlante(game(), game().getWidth()/2, game().getHeight()/2);
        game().add(s);
    }
    
    protected void tireSp3(){
        SuperEclair s = new SuperEclair(this, ge);
        game().add(s);
    }
    
    protected void tireSp4(){
        CarapaceV s = new CarapaceV(this);
        game().add(s);
    }
    
    protected void tireSp5(){
        Bullet s = new Bullet(this);
        game().add(s);
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
            
            if(futurLeft>=game().width()/2 && futurRight<game().width()-160){
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
            
            if(futurDown>=160 && futurUp<game().height()-160)
                this.moveY(dx);                            
        }
}