package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Super-ennemi, nécessitant plusieurs impacts pour être tué
 * @author Islem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */
public class Mario extends Ennemi {
	/**
	 * Représente la vie du chien. Diminue à chaque impact. Une valeur de 0 entraîne la mort.
	 */
	private int vie=25;
        private double sensX = 1.5;
        private double sensY = 0.5;
        private GestionMap gestion;

        public Mario(Game g, int x, int y,GestionMap gm){
            super(g,"Mario",x,y);
            gestion = gm;
        }
        
	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
       
	public void effect(Objet o) {
             if(o.isFriend())
            {
                vie --;
                this.crier();
                Random alea = new Random();
                int aleatoire = alea.nextInt(200);
                gestion.getPlayer().changeScore(aleatoire);
                if(vie<=0){
                    this.meurt();
                }
            }
	}
        
	/**
	 * Déplacement l'énnemi en fonction d'une variable aléatoire (contrôle de l'IA)
	 * @param dt le temps écoulé en millisecondes depuis le précédent déplacement
	 */
        
	public void move(long dt) {                
            Random alea = new Random();
            int aleatoire;
            
            aleatoire = alea.nextInt(4000);
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
            if(aleatoire>=150 && aleatoire <157){
                tirerCaraV();
            }
            if(aleatoire>=157 && aleatoire<160){
                tirerCaraR();
            }
            if(aleatoire==160){
                spawnBro();
            }
            if(aleatoire==161){
                spawnStand();
            }
	}

	public int getVie() {
		return this.vie;
	}


        public void crier(){
            Audio a = new Audio("marioMal");
            a.start();
        }
        
        private void meurt(){
            Random alea = new Random();
            int aleatoire = alea.nextInt(200);
            gestion.getPlayer().changeScore(aleatoire);
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

        /**
	 * Action: déssine la jauge de vie de l'ennemi
	 * @param g de type Graphics
	 */
        public void draw(Graphics g) throws Exception
        {
            super.draw(g);
            // ajoute une jauge
            int w = vie*6;
            g.setColor(Color.RED);
            g.fillRect(getLeft()-50,getTop()-5,w,4);
        }
        
        /**
	 *créer l'attaque de mario: une boule de feu
	 */
        protected void tirer() {
            Audio bdf = new Audio("marioTire");
            bdf.start();
            BouleDeFeu boule = new BouleDeFeu(this, gestion);
            game().add(boule);
        }
        /**
	 *créer l'attaque de mario: une carapace verte
	 */
        protected void tirerCaraV(){
            CarapaceV cara = new CarapaceV(this);
            game().add(cara);
        }
        /**
	 *créer l'attaque de mario: une carapace rouge
	 */
        protected void tirerCaraR(){
            CarapaceR cara = new CarapaceR(this, gestion);
            game().add(cara);
        }
        /**
	 *créer l'attaque de mario: créer un ennemi "bro"
	 */
        protected void spawnBro(){
            Audio bro = new Audio("bro");
            bro.start();
            int futureYCord = 0;
            if(this.getMiddleY()>=game().getHeight()){
                futureYCord = this.getMiddleY()+160;
            }else{
                futureYCord = this.getMiddleY()-160;
            }
            Bro br = new Bro(game(), this.getMiddleX(), futureYCord, gestion);
            game().add(br);
        }
        /**
	 *créer l'attaque de mario: créer un canon
	 */
        protected void spawnStand(){
            Audio st = new Audio("bill");
            st.start();
            int futureYCord = 0;
            if(this.getMiddleY()>=game().getHeight()){
                futureYCord = this.getMiddleY()+160;
            }else{
                futureYCord = this.getMiddleY()-160;
            }
            BulletStand bs = new BulletStand(game(), this.getMiddleX(), futureYCord, gestion);
            game().add(bs);
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