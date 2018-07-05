package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import iut.ObjetTouchable;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * Représente le joueur (le héro), dirigé au clavier par l'utilisateur
 * @author Islem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */
public class Joueur extends ObjetTouchable implements KeyListener {
	/**
	 * Niveau de vie du joueur (maximum : 200, minimum : 0). Une valeur de 0 entraîne la mort.
	 */
	private int vie;
        private int spleft=1;
        private int score;
        private int i = 0;
        private int sec = 0;
        private int min = 0;
        private int heures = 0;
        private int pouvArme = 1;
        private int menuNumero = 0;
        private boolean enVie = true;
        private Random r = new Random();
        
        /**
         * Lien vers le tir du joueur
         */
	private TirJoueurZ tirZ=null;
        private TirJoueurQ tirQ=null;
        private TirJoueurS tirS=null;
        private TirJoueurD tirD=null;
        private TirJoueurZQ tirZQ=null;
        private TirJoueurZD tirZD=null;
        private TirJoueurSQ tirSQ=null;
        private TirJoueurSD tirSD=null;
        private GestionMap gestion = null;
                

	/**
	 * Crée un joueur
	 * @param game Le jeu qui contient le joueur
	 */
	public Joueur(Game game) {
		super(game,"Hero",80, game.getHeight()/2);
                vie = 200;
                score = 0;
	}

	/**
	 * Modifie le niveau de vie du joueur (augmente ou diminue).
	 * La vie ne peut pas dépasser 200.
	 * Une vie de 0 (ou moins) déclenche la mort du joueur
	 * @param dv écart de vie du joueur. Positif = augmente sa vie, négatif = diminue sa vie
	 */
	protected void changeVie(int dv) {
		vie += (int) dv*(r.nextDouble()*2.0);
                if(vie<0) vie=0;
                if(vie==0) this.mourir();
	}

	/**
	 * Joue un son déclenché par le joueur lorsqu'il est touché
	 */
	protected void crier() {
	}

	/**
	 * indique au joueur que le tir a fini son travail. permet la MAJ de l'association joueur/Tir
	 */
	public void tirSorti() {
		tirZ = null;
                tirQ = null;
                tirS = null;
                tirD = null;
                tirZQ = null;
                tirZD = null;
                tirSQ = null;
                tirSD = null;
	}

	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
	public void effect(Objet o) {
            // on touche un ennemi
            if(o.isEnnemy())
            {
                changeVie(-1 * (r.nextInt(10) +1 ));
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
            if(menuNumero == -1){
                i += dt;
            }
            if(i>=1000){
                this.changeTemps(1);
                i -= 1000;
            }
            
            if(gestion.getPlayer2() != null){
                if(!gestion.getPlayer2().estEnVie() && !this.enVie){
                    game().dead();
                }
            }else{
                if(!this.enVie){
                    game().dead();
                }
            }
	}
        /**
	* Action : permet de controler le personnage avec différentes touches du claviers
	* @param e évenement dû à l'appuye du clavier
	* 
	*/
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT:
                         if(menuNumero == -1){déplaceX(-1);};
                        break;
                    case KeyEvent.VK_RIGHT:
                         if(menuNumero == -1){déplaceX(+1);};
                        break;
                    case KeyEvent.VK_UP:
                         if(menuNumero == -1){déplaceY(-1);};
                        break;
                    case KeyEvent.VK_DOWN:
                         if(menuNumero == -1){déplaceY(+1);};
                        break;
                    case KeyEvent.VK_NUMPAD8:
                         if(menuNumero == -1){tire(8);};
                        break;
                    case KeyEvent.VK_NUMPAD2:
                         if(menuNumero == -1){tire(5);};
                        break;
                    case KeyEvent.VK_NUMPAD4:
                         if(menuNumero == -1){tire(4);};
                        break;
                    case KeyEvent.VK_NUMPAD6:
                         if(menuNumero == -1){tire(6);};
                        break;
                    case KeyEvent.VK_NUMPAD9:
                         if(menuNumero == -1){tire(9);};
                        break;
                    case KeyEvent.VK_NUMPAD7:
                         if(menuNumero == -1){tire(7);};
                        break;
                    case KeyEvent.VK_NUMPAD1:
                         if(menuNumero == -1){tire(1);};
                        break;
                    case KeyEvent.VK_NUMPAD3:
                         if(menuNumero == -1){tire(3);};
                        break;
                    case KeyEvent.VK_NUMPAD0:
                         if(menuNumero == -1){tire(6);};
                         break;
                    case KeyEvent.VK_NUMPAD5:
                        if(menuNumero == -1){tireSp();};
                        break;
                    case KeyEvent.VK_V:
                        if(menuNumero == 1){
                            menuNumero = 2;
                            gestion.setMode(1, false);
                            gestion.passerAuNiveauSuivant();
                        }
                        break;
                    case KeyEvent.VK_B:
                        if(menuNumero == 1){
                           menuNumero = 2;
                            gestion.setMode(2, false);
                            gestion.passerAuNiveauSuivant();
                        }
                        break;
                    case KeyEvent.VK_N:
                        if(menuNumero == 1){
                            menuNumero = 2;
                            gestion.setMode(2, true);
                            gestion.passerAuNiveauSuivant();
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        if(menuNumero == 0){
                            menuNumero = 1;
                            gestion.passerAuNiveauSuivant();
                            }
                        if(menuNumero == 2){
                                menuNumero = -1;
                                gestion.passerAuNiveauSuivant();
                            }
                            break;
                    case KeyEvent.VK_ESCAPE:
                        if(menuNumero == 2){
                                menuNumero = 1;
                                gestion.passerAuNiveauPrecedent();
                            }
                            break;
                    case KeyEvent.VK_J:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doMove(1);
                        }
                        break;
                    case KeyEvent.VK_L:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doMove(2);
                        }
                        break;
                    case KeyEvent.VK_I:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doMove(3);
                        }
                        break;
                    case KeyEvent.VK_K:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doMove(4);
                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(6);
                        }
                        break;
                    case KeyEvent.VK_A:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(7);
                        }
                        break;
                    case KeyEvent.VK_Z:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(8);
                        }
                        break;
                    case KeyEvent.VK_E:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(9);
                        }
                        break;
                    case KeyEvent.VK_Q:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(4);
                        }
                        break;
                    case KeyEvent.VK_D:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(6);
                        }
                        break;
                    case KeyEvent.VK_W:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(1);
                        }
                        break;
                    case KeyEvent.VK_X:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(5);
                        }
                        break;
                    case KeyEvent.VK_C:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTirer(3);
                        }
                        break;
                    case KeyEvent.VK_S:
                        if(gestion.getPlayer2() != null){
                            gestion.getPlayer2().doTireSp();
                        }
                        break;    
                }
	}
        /**
	* Action : permet au jouer de lancer des attaques avec différentes touches du clavier
	* @param s entier 
	*/
        private void tire(int s)
        {
            if(enVie){
                int nbTirs=0;
                if(tirQ!=null){
                    nbTirs ++;
                }
                if(tirS!=null){
                    nbTirs ++;
                }
                if(tirD!=null){
                    nbTirs ++;
                }
                if(tirZ!=null){
                    nbTirs ++;
                }
                if(tirZQ!=null){
                    nbTirs ++;
                }
                if(tirZD!=null){
                    nbTirs ++;
                }
                if(tirSQ!=null){
                    nbTirs ++;
                }
                if(tirSD!=null){
                    nbTirs ++;
                }
                if((nbTirs <1)){
                    if(s==4 && tirQ==null){
                        tirQ = new TirJoueurQ(this);
                        game().add(tirQ);
                    }

                    if(s==5 && tirS==null){
                        tirS = new TirJoueurS(this);
                        game().add(tirS);
                    }

                    if(s==6 && tirD==null){
                        tirD = new TirJoueurD(this);
                        game().add(tirD);
                    }

                    if(s==8 && tirZ==null){
                        tirZ = new TirJoueurZ(this);
                        game().add(tirZ);
                    }

                    if(s==9 && tirZD==null){
                        tirZD = new TirJoueurZD(this);
                        game().add(tirZD);
                    }

                    if(s==7 && tirZQ==null){
                        tirZQ = new TirJoueurZQ(this);
                        game().add(tirZQ);
                    }

                    if(s==1 && tirSQ==null){
                        tirSQ = new TirJoueurSQ(this);
                        game().add(tirSQ);
                    }

                    if(s==3 && tirSD==null){
                        tirSD = new TirJoueurSD(this);
                        game().add(tirSD);
                    }
                }
            }
        }
        
        public void tireSp(){
            if(spleft>0 && enVie){
                spleft --;
                BombJoueur bom = new BombJoueur(this, gestion);
                game().add(bom);
                }
        }
        /**
	* Action : déplace le perso à l'horizontal
	*@param sens entier indiquant le sens de direction du perso
	* 
	*/
        private void déplaceX(int sens)
        {
            if(enVie){
                final int nbPixels = 40; 
                int dx = sens*nbPixels;
                int futurLeft = getLeft()+dx;
                int futurRight = getRight()+dx;

                if(futurLeft>=40 && futurRight<game().width()-40){
                    this.moveX(dx);
                }
            }
        }
        /**
	* Action : déplace le perso à la vertical
	*@param sens entier indiquant le sens de direction du perso
	* 
	*/
        private void déplaceY(int sens)
        {
            if(enVie){
                final int nbPixels = 40; 
                int dx = sens*nbPixels;
                int futurUp = getTop()+dx;
                int futurDown = getBottom()+dx;

                if(futurDown>=40 && futurUp<game().height()-40)
                    this.moveY(dx);                            
            }
        }

	public void keyReleased(KeyEvent e) {
	// RAS
	}

	public void keyTyped(KeyEvent e) {
	// RAS
	}

	public int getVie() {
		return this.vie;
	}

	public int getScore() {
		return this.score;
	}
        
        public void changeScore(int val){
            score += val;
        }
        
        public void changeTemps(int val){
            sec += val;
            if(sec>=60){
                sec -= 60;
                min ++;
            }
            if(min>=60){
                min -= 60;
                heures ++;
            }
        }
        /**
	* 
	*récupere le temps du jeu
	* @return s string qui affiche l'heure, les minutes et les secondes 
	*/
        public String getTemps(){
            String s = "";
            if(heures<10 && heures>0){
                s += "0"+Integer.toString(heures)+":";
            };
            if(heures>9){
                s += Integer.toString(heures)+":";
            }
            if(min<10 && min>0){
                s += "0"+Integer.toString(min)+":";
            };
            if(min>9){
                s += Integer.toString(min)+":";
            }
            if(min==0 && heures != 0){
                s += "00";
            }
            if(sec<10 && sec>0){
                s += "0"+Integer.toString(sec);
            };
            if(sec>9){
                s += Integer.toString(sec);
            }
            if(sec==0){
                s += "00";
            }
            return s;
        }
        
        public void setSp(int val){
            spleft += val;
        }
        
        public void setGestionMap(GestionMap ges){
            gestion = ges;
        }
        
        public int getPouvArme(){
            return pouvArme;
        }
        
        public void setPouvArme(int val){
            pouvArme += val;
        }
        
        private void mourir(){
            enVie = false;
            moveX(-2000);
        }
    
        public void restitution(){
            vie = 50;
            moveX(+2000);
            enVie = true;
            Audio ns = new Audio("newstart");
            ns.start();
        }
        
        public boolean estEnVie(){
            return enVie;
        }
}