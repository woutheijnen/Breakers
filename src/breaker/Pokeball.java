package breaker;

import iut.Audio;
import iut.Objet;
import iut.ObjetTouchable;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Objet de soutient que le joueur peut rammasser au cours du jeu qui invoque un pokémon qui aide le joueur
 * @author Islem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */
public class Pokeball extends ObjetTouchable {
    private Ennemi e;
    private Joueur j;
    private Joueur2 j2;
    private int x;
    private int y;
    private GestionMap gestion;
    private int pv;
    
    public Pokeball(Ennemi source, int xx, int yy, GestionMap actualisation){
        super(source.game(),"pokeball",xx ,yy);
        gestion = actualisation;
        j = gestion.getPlayer();
        j2 = gestion.getPlayer2();
        x = xx;
        y = yy;
        pv = 5;
    }

       /**
        * Action : effet d'une collision entre l'objet et le paramètre
        * gestion de l'IA 
        * grâce à une variable aléatoire, on détermine quelle pokemon va être contenu dans la pokeball
        */
    public void effect(Objet o) {
        if((o == j) || (o == j2 && o != null)){
            if(!gestion.isPokeballActive()){
                Random alea;
                alea = new Random();
                int aleatoire = alea.nextInt(1659);
                aleatoire ++;
                if(aleatoire >= 1 && aleatoire <= 190){
                    Fantominus fantominus = new Fantominus(game(), x, y, gestion);
                    game().add(fantominus);
                }
                if(aleatoire >= 191 && aleatoire <= 280){
                    Spectrum spectrum = new Spectrum(game(), x, y, gestion);
                    game().add(spectrum);
                }
                if(aleatoire >= 281 && aleatoire <= 325){
                    Ectoplasma ectoplasma = new Ectoplasma(game(), x, y, gestion);
                    game().add(ectoplasma);
                }
                if(aleatoire >= 326 && aleatoire <= 455){
                    Ptiravi ptiravi = new Ptiravi(game(), x, y, gestion);
                    game().add(ptiravi);
                }
                if(aleatoire >= 456 && aleatoire <= 480){
                    Leveinard leveinard = new Leveinard(game(), x, y, gestion);
                    game().add(leveinard);
                }
                if(aleatoire >= 481 && aleatoire <= 515){
                    Leuphorie leuphorie = new Leuphorie(game(), x, y, gestion);
                    game().add(leuphorie);
                }
                if(aleatoire >= 516 && aleatoire <= 770){
                    Miaouss miaouss = new Miaouss(game(), x, y, gestion);
                    game().add(miaouss);
                }
                if(aleatoire >= 771 && aleatoire <= 860){
                    Persian persian = new Persian(game(), x, y, gestion);
                    game().add(persian);
                }
                if(aleatoire >= 861 && aleatoire <= 920){
                    Electrode electrode = new Electrode(game(), x, y, gestion);
                    game().add(electrode);
                }
                if(aleatoire >= 921 && aleatoire <= 945){
                    Ronflex ronflex = new Ronflex(game(), x, y, gestion);
                    game().add(ronflex);
                }
                if(aleatoire >= 946 && aleatoire <= 948){
                    Sulfura sulfura = new Sulfura(game(), x, y, gestion);
                    game().add(sulfura);
                }
                if(aleatoire >= 949 && aleatoire <= 951){
                    Lugia lugia = new Lugia(game(), x, y, gestion);
                    game().add(lugia);
                }
                if(aleatoire >= 952 && aleatoire <= 1186){
                    Tarsal tarsal = new Tarsal(game(), x, y, gestion);
                    game().add(tarsal);
                }
                if(aleatoire >= 1187 && aleatoire <= 1306){
                    Kirlia kirlia = new Kirlia(game(), x, y, gestion);
                    game().add(kirlia);
                }
                if(aleatoire >= 1307 && aleatoire <= 1351){
                    Gardevoir gardevoir = new Gardevoir(game(), x, y, gestion);
                    game().add(gardevoir);
                }
                if(aleatoire >= 1352 && aleatoire <= 1354){
                    Terhal terhal = new Terhal(game(), x, y, gestion);
                    game().add(terhal);
                }
                if(aleatoire >= 1355 && aleatoire <= 1357){
                    Metang metang = new Metang(game(), x, y, gestion);
                    game().add(metang);
                }
                if(aleatoire >= 1358 && aleatoire <= 1360){
                    Metalosse metalosse = new Metalosse(game(), x, y, gestion);
                    game().add(metalosse);
                }
                if(aleatoire >= 1361 && aleatoire <= 1615){
                    Magicarpe magicarpe = new Magicarpe(game(), x, y, gestion);
                    game().add(magicarpe);
                }
                if(aleatoire >= 1616 && aleatoire <= 1660){
                    Leviator leviator = new Leviator(game(), x, y, gestion);
                    game().add(leviator);
                }

                aleatoire = alea.nextInt(100);
                j.changeScore(aleatoire);
            }
            game().remove(this);
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
            return true;
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
        if(dt>=10000){
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