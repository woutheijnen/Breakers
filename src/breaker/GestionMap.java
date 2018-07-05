/**
 * C'est la classe qui permet de créer tout les objets du jeu et de gérer les niveaux du jeu
 *
 */

package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import java.util.Random;

/**
 *
 * @author Islem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */
public class GestionMap extends Ennemi{
        private Audio a;
        private int niveau=-2;
        private Joueur player;
        private Joueur2 player2;
        private boolean nivCree=false;
        private Ennemi b=null;
        private Menu0 menu0;
        private Menu1 menu;
        private Menu2 menu2;
        private Menu3 menu3;
        private boolean pokeballActive = false;
        private boolean startPokeballCounter = false;
        private int pokeballCounter = 0;
        private int explosionCounter = 0;
        private boolean startExplosionCounter = false;
        private int spCounter = 0;
        private int mode;
        private boolean multiplayer = false;
        private int i = 1;
        private boolean explosionPossible = true;
       /**         
         * @param g est le jeu 
         * @param j est le joueur n'ayant pas de sprite 
         */
    public GestionMap(Game g,Joueur j){
            super(g,"null",0,0);
            player = j;
    }
    /**
     *(méthode non utilisé)
     */
    public void crier(){
        
    }
    
    public void tirer(){
        
    }
    
    public void effect(Objet o) {
        
    }
    /**         * 
      * @param dt réel correspondant à la distance de déplacement
      */
    public void move(long dt) {
            /**
                 * Si nous commençons le jeu, nous créons le menu principale 
                 * du jeu
                 */
            if(niveau==-2 && !nivCree){
                if(a!=null){
                    a.stop();
                }
                a = new Audio("menu");
                a.start();
                menu0 = new Menu0(game(), 0, 0);
                game().add(menu0);
                nivCree= true;
            }
            
            if(niveau==-1 && !nivCree){
                menu = new Menu1(game(), 0, 0);
                game().add(menu);
                nivCree= true;
            }
            
            
        //################################################################        
            /**
                 * Le joueur a choisit de jouer au premier mode de jeu (mode aventure)
                 */
            if(niveau==0 && !nivCree && mode == 1){
                menu2 = new Menu2(game(), 0, 0);
                game().add(menu2);
                nivCree= true;
            }
            /**
                 * Le joueur a choisit de jouer au deuxieme mode de jeu (mode survie)
                 * sinon il choisit le mode n°3 (mode 2 joueurs)
                 */
            if(niveau==0 && !nivCree && mode == 2){
                String s;
                if(!multiplayer){
                    s = "menu4";
                }else{
                    s = "menu3";
                }
                menu3 = new Menu3(game(),s);
                game().add(menu3);
                nivCree= true;
            }
            
            
        //################################################################            
             /**
              * création du niveau 1 du premier mode 
              */
            if(niveau==1 && !nivCree && mode == 1){
                 /** Crée les monstres
                  */
                if(a!=null){
                    a.stop();
                }
                /**
                     * création du son de l'ennemi
                     */
                Audio ns = new Audio("newstart");
                ns.start();
                a = new Audio("finalsong");
                a.start();

                b = new Pikachu(game(), game().getWidth()-120, game().getHeight()/2, this);
                game().add(b);
                
                nivCree= true;
            }
            
            /**
                 * création du niveau 1 du deuxième mode 
                 */
            if(niveau==1 && !nivCree && mode == 2){
                 // Crée les monstres
                if(a!=null){
                    a.stop();
                }
                Audio ns = new Audio("newstart");
                ns.start();
                a = new Audio("finalsong");
                a.start();
                
                if(multiplayer){
                    player2 = new Joueur2(game(), this);
                    game().add(player2);
                    JaugeJoueur2 jauge2 = new JaugeJoueur2(player2);
                    game().add(jauge2);
                }
                
                for(int a = 0; a < i; a++){
                    Random alea = new Random();
                    int aleatoire = alea.nextInt(8);
                    int XCord = alea.nextInt(game().getWidth()/2-160)+game().getWidth()/2;
                    int YCord = alea.nextInt(game().getHeight()-160);
                    /**
                         * (de la ligne 164 à 187)
                         * 
                         * si variable aléatoire = 0 alors nous créons un ennemi (pikachu) 
                         * sinon si aléatoire=1 alors nous créons un ennemi mario
                         * sinon si aléatoire=2 alors nous créons un ennemi Samus
                         * sinon si aléatoire=3 alors nous créons un ennemi MrGameAndWatch
                         * sinon si aléatoire=4 alors nous créons un ennemi Bomberman
                         * sinon si aléatoire=5 alors nous créons un ennemi Cartman

                         */
                    if(aleatoire == 0){
                        b = new Pikachu(game(), XCord, YCord, this);
                        game().add(b);
                    }
                    if(aleatoire == 1){
                        b = new Mario(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 2){
                        b = new Samus(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 3){
                        b = new MrGameAndWatch(game(), XCord, YCord, this, 30, false);
                        game().add(b);
                        }
                    if(aleatoire == 4){
                        b = new Bomberman(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 5){
                        b = new Cartman(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 6){
                        b = new Broly(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 7){
                        b = new Link(game(), XCord, YCord, this);
                        game().add(b);
                        }
                }
                nivCree = true;
                i ++;
            }
            
            
    
                
            
            
        //################################################################            
            /**
             * 
             *(de la ligne 227 à 323)
             * Si le joueur joue au niveau 2 (mode 1)
             * création d'un ennemi mario
             * 
             * /**
             * Si le joueur joue au niveau 3 
             * création d'un ennemi Samus
             * 
             * /**
             * Si le joueur joue au niveau 4 
             * création d'un ennemi MrGameAndWatch
             * 
             * 
             * Si le joueur joue au niveau 5
             * création d'un ennemi Bomberman
             * 
             * Si le joueur joue au niveau 6 
             * création d'un ennemi Cartman
             * 
             * Si le joueur joue au niveau 9 
             * création du BossFinal
             * 
             * Si le joueur joue à un niveau supérieur ou égal à 10
             * le joueur finit le jeu
             *                 
             */
            if(niveau==2 && !nivCree){
                 // Crée les monstres
                b = new Mario(game(), game().getWidth()-120, game().getHeight()/2, this);
                game().add(b);
                
                nivCree= true;
            }
            
        //################################################################            
            
            if(niveau==3 && !nivCree){
                 // Crée les monstres
                b = new Samus(game(), game().getWidth()-120, game().getHeight()/2, this);
                game().add(b);
                
                nivCree= true;
            }
            
        //################################################################  
            
            if(niveau==4 && !nivCree){
                 // Crée les monstres
                b = new MrGameAndWatch(game(), game().getWidth()-120, game().getHeight()/2, this, 30, false);
                game().add(b);
                
                nivCree= true;
            }
            
        //################################################################ 
            
            if(niveau==5 && !nivCree){
                 // Crée les monstres
                b = new Bomberman(game(), game().getWidth()-120, game().getHeight()/2, this);
                game().add(b);
                
                nivCree= true;
            }
            
        //################################################################ 
            
            if(niveau==6 && !nivCree){
                 // Crée les monstres
                b = new Cartman(game(), game().getWidth()-120, game().getHeight()/2, this);
                game().add(b);
                
                nivCree= true;
            }
            
        //################################################################ 
            
            if(niveau==7 && !nivCree){
                 // Crée les monstres
                b = new Broly(game(), game().getWidth()-120, game().getHeight()/2, this);
                game().add(b);
                
                nivCree= true;
            }
            
        //################################################################ 
            
            if(niveau==8 && !nivCree){
                 // Crée les monstres
                b = new Link(game(), game().getWidth()-120, game().getHeight()/2, this);
                game().add(b);
                
                nivCree= true;
            }
            
        //################################################################ 
            
            if(niveau==9 && !nivCree){
                 // Crée les monstres
                b = new BossFinal(game(), game().getWidth()-120, game().getHeight()/2, this);
                game().add(b);
                
                nivCree= true;
            }
            
            if(niveau>=10){
                game().remove(this);
            }
        //################################################################ 
            /**
             *(de la ligne 331 à 357)
             * Si le joueur est au delà du niveau 1
             * nous créons des bonus qui aiderons le joueur:santé et alliés 
             * (pokéball qui permet d'appeler un pokémon pour lui venir en aide)
             */
            if(niveau >= 1){
                Random alea;
                alea = new Random();
                int ijk = 1;
                if(mode==1){
                    ijk=3;
                }
                int aleatoire  = alea.nextInt(6000*ijk/i);
                if(aleatoire == 0 || 1 == aleatoire || aleatoire == 6){
                    int xb = alea.nextInt((game().getWidth()-240)/2);
                    int yb = alea.nextInt(game().getHeight()-240);
                    BonusPv bonus = new BonusPv(this, xb+80, yb+80, this);
                    game().add(bonus);
                }
                if(aleatoire == 2 || aleatoire == 3){
                    int xb = alea.nextInt((game().getWidth()-240)/3);
                    int yb = alea.nextInt(game().getHeight()-240);
                    Pokeball pokeball = new Pokeball(this, xb+80, yb+80, this);
                    game().add(pokeball);
                }
                if(aleatoire == 4 || aleatoire == 5 || aleatoire == 7){
                    int xb = alea.nextInt((game().getWidth()-240)/3);
                    int yb = alea.nextInt(game().getHeight()-240);
                    PowerUp pu = new PowerUp(this, xb+80, yb+80, this);
                    game().add(pu);
                }
            }
            
            if(startPokeballCounter){
                pokeballCounter += dt;
                if(pokeballCounter >= 200){
                    startPokeballCounter = false;
                    pokeballCounter = 0;
                    pokeballActive = false;
                }
            }else{
                pokeballCounter = 0;
            }
            
            if(startExplosionCounter){
                explosionCounter += dt;
                if(explosionCounter >= 200){
                    startExplosionCounter = false;
                    explosionCounter = 0;
                    explosionPossible = true;
                }
            }else{
                explosionCounter = 0;
            }
            
            spCounter += dt;
            if(spCounter >= 30000 && niveau>=1){
               spCounter = 0;
               if(player.estEnVie()){
                   player.setSp(1);
               }
               if(player2 != null){
                    if(player2.estEnVie()){
                        player2.setSp(1);
                    }
               }
            }
            
            if(b!=null && mode == 1){
                if(b.getVie()<=0){
                    niveau ++;
                    nivCree = false;
                    b=null;
                }
            }
                
            if(mode == 2 && b != null){
                if(b.getVie() <=0){
                for(int a = 0; a < i; a++){
                    Random alea = new Random();
                    int aleatoire = alea.nextInt(8);
                    int XCord = alea.nextInt(game().getWidth()/2-120)+game().getWidth()/2;
                    int YCord = alea.nextInt(game().getHeight()-160);
                    if(aleatoire == 0){
                        b = new Pikachu(game(), XCord, YCord, this);
                        game().add(b);
                    }
                    if(aleatoire == 1){
                        b = new Mario(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 2){
                        b = new Samus(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 3){
                        b = new MrGameAndWatch(game(), XCord, YCord, this, 30, false);
                        game().add(b);
                        }
                    if(aleatoire == 4){
                        b = new Bomberman(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 5){
                        b = new Cartman(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 6){
                        b = new Broly(game(), XCord, YCord, this);
                        game().add(b);
                        }
                    if(aleatoire == 7){
                        b = new Link(game(), XCord, YCord, this);
                        game().add(b);
                        }
                }
                i++;
                }
            }
    }
    
    public Joueur getPlayer(){
        return player;
    }

    @Override
    public int getVie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Ennemi getCurrentBoss(){
        return b;
    }
    
    public boolean isPokeballActive(){
        return pokeballActive;
    }
    
    public void setPokeBallActive(boolean val){
        pokeballActive = val;
        if(val==true){
            startPokeballCounter = true;
        }
    }
    
    public int getMode(){
        return mode;
    }
    
    public void setMode(int val, boolean multi){
        mode = val;
        multiplayer = multi;
    }
    
    public Joueur2 getPlayer2(){
        return player2;
    }
    
    public int getNiveau(){
        return niveau;
    }
    
    public void passerAuNiveauSuivant(){
        if(niveau == -2){
            game().remove(menu0);
        }
        if(niveau == -1){
            game().remove(menu);
        }
        if(niveau == 0){
            game().remove(menu2);
            game().remove(menu3);
        }
        niveau ++;
        nivCree = false;
    }
    
    public void passerAuNiveauPrecedent(){
        if(niveau == 0){
            game().remove(menu2);
            game().remove(menu3);
        }
        niveau --;
        nivCree = false;
    }
    
    public boolean isExplosionPossible(){
        return explosionPossible;
    }
    
    public void setExplosionPossible(boolean val){
        explosionPossible = val;
        if(val == false){
            startExplosionCounter = true;
        }
    }
    
    public boolean isMultiPlayer(){
        return multiplayer;
    }
}
