package breaker;

import iut.Audio;
import iut.Objet;
import iut.ObjetTouchable;
import java.util.Random;

/**
 * Object bonus permettant au joueur de récupérer de la santé
 * @author Islem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */
public class BonusPv extends ObjetTouchable {
    private Joueur j;
    private Joueur2 j2;
    private int pv;
    /**
    * 
    * @param source est une variable locale de type Ennemi auquel la bombe va être utilisé
    * @param x est l'entier correspondant à la coordonnée des abscisses 
    * @param y est l'entier correspondant à la coordonnée des ordonnées
    * @param gestion est un object de type gestionMap
    */
    public BonusPv(Ennemi source, int x, int y, GestionMap gestion){
        super(source.game(),"bonusPv",x ,y);
        j = gestion.getPlayer();
        j2 = gestion.getPlayer2();
        pv = 5;
    }

    /**
     * Action : effet d'une collision entre l'objet et le paramêtre 
     * si l'objet est un joueur alors sa santé augmente et son score également 
     * sinon pour un ennemi le bonus s'enlève
     */
    public void effect(Objet o) {
        if(o == j){
            j.changeVie(20);
            game().remove(this);
            Random alea = new Random();
            int aleatoire = alea.nextInt(50);
            j.changeScore(aleatoire);
            Audio bpv = new Audio("bonusPv");
            bpv.start();
        }

        if(o == j2 && o != null){
            j2.changeVie(10);
            game().remove(this);
            Random alea = new Random();
            int aleatoire = alea.nextInt(50);
            j.changeScore(aleatoire);
            Audio bpv = new Audio("bonusPv");
            bpv.start();
        }

        if(o.isEnnemy()){
            pv --;
            if(pv<=0){
                game().remove(this);
            }
        }
    }

    /**
        * @return true si l'objet est un "ami" du joueur
        */
    public boolean isFriend() {
            return false;
    }

    /**
        * @return false si l'objet est un "ennemi" du joueur
        */
    public boolean isEnnemy() {
            return false;
    }


    /**
        * Déplace l'objet
        * @param dt le temps écoulé en millisecondes depuis le précédent déplacement
        */
    public void move(long dt) {
        if(dt>=60000){
            game().remove(this);
        }
        if(estSorti()){
            game().remove(this);
        }
    }

    public void explosion(){

    }

    public void crier(){

    }

    public void tirer(){

    }

    /**
        * indique si le tir est "à l'intérieur" de l'écran ou non
        * @return true si le tir a quitté l'écran (par le bas)
        */
    private boolean estSorti() {
            return getTop()>game().height()-80 || getLeft()<80 || getTop()<80 || getRight()>game().getWidth()-80;
    }
        
}