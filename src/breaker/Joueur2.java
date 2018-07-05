package breaker;

import iut.Audio;
import iut.Game;
import iut.Objet;
import iut.ObjetTouchable;

/**
 * Représente le joueur (le héro), dirigé au clavier par l'utilisateur
 * @author Islem Yahiaoui, Alan Gouvernet, Mouhouni Chakrina, Wout Heijnen, Ibrahim, Zouhairi
 */
public class Joueur2 extends ObjetTouchable {
	/**
	 * Niveau de vie du joueur (maximum : 200, minimum : 0). Une valeur de 0 entraîne la mort.
	 */
        
        /**
         * Lien vers le tir du joueur
         */
	private TirJoueur2Z tir2Z=null;
        private TirJoueur2Q tir2Q=null;
        private TirJoueur2S tir2S=null;
        private TirJoueur2D tir2D=null;
        private TirJoueur2ZQ tir2ZQ=null;
        private TirJoueur2ZD tir2ZD=null;
        private TirJoueur2SQ tir2SQ=null;
        private TirJoueur2SD tir2SD=null;
        private GestionMap gestion = null;
        private int vie = 200;
        private int spleft=1;
        private int pouvArme = 1;
        private boolean enVie = true;
                

	/**
	 * Crée un joueur
	 * @param game Le jeu qui contient le joueur
	 */
	public Joueur2(Game game, GestionMap gm) {
		super(game,"Hero2",80, game.getHeight()/3);
                gestion = gm;
	}

	/**
	 * Modifie le niveau de vie du joueur (augmente ou diminue).
	 * La vie ne peut pas dépasser 200.
	 * Une vie de 0 (ou moins) déclenche la mort du joueur
	 * @param dv écart de vie du joueur. Positif = augmente sa vie, négatif = diminue sa vie
	 */
	
        protected void changeVie(int dv) {
		vie += dv;
                if(vie<0) vie=0;
                if(vie>200) vie=200;
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
		tir2Z = null;
                tir2Q = null;
                tir2S = null;
                tir2D = null;
                tir2ZQ = null;
                tir2ZD = null;
                tir2SQ = null;
                tir2SD = null;
	}

	/**
	 * Action : effet d'une collision entre l'objet et le paramètre
	 */
	public void effect(Objet o) {
            // on touche un ennemi
            if(o.isEnnemy())
            {
                changeVie(-10);
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

	}
        /**
	 * fait déplacer le personnage en fonction de l'entier move
	 */
	public void doMove(int move) {
            if(enVie){
                switch(move)
                {
                    case 1:
                         déplaceX(-1);
                        break;
                    case 2:
                         déplaceX(+1);
                        break;
                    case 3:
                         déplaceY(-1);
                        break;
                    case 4:
                         déplaceY(+1);
                        break;
                }
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
                if(tir2Q!=null){
                    nbTirs ++;
                }
                if(tir2S!=null){
                    nbTirs ++;
                }
                if(tir2D!=null){
                    nbTirs ++;
                }
                if(tir2Z!=null){
                    nbTirs ++;
                }
                if(tir2ZQ!=null){
                    nbTirs ++;
                }
                if(tir2ZD!=null){
                    nbTirs ++;
                }
                if(tir2SQ!=null){
                    nbTirs ++;
                }
                if(tir2SD!=null){
                    nbTirs ++;
                }
                if((nbTirs <1)){
                    if(s==4 && tir2Q==null){
                        tir2Q = new TirJoueur2Q(this);
                        game().add(tir2Q);
                    }

                    if(s==5 && tir2S==null){
                        tir2S = new TirJoueur2S(this);
                        game().add(tir2S);
                    }

                    if(s==6 && tir2D==null){
                        tir2D = new TirJoueur2D(this);
                        game().add(tir2D);
                    }

                    if(s==8 && tir2Z==null){
                        tir2Z = new TirJoueur2Z(this);
                        game().add(tir2Z);
                    }

                    if(s==9 && tir2ZD==null){
                        tir2ZD = new TirJoueur2ZD(this);
                        game().add(tir2ZD);
                    }

                    if(s==7 && tir2ZQ==null){
                        tir2ZQ = new TirJoueur2ZQ(this);
                        game().add(tir2ZQ);
                    }

                    if(s==1 && tir2SQ==null){
                        tir2SQ = new TirJoueur2SQ(this);
                        game().add(tir2SQ);
                    }

                    if(s==3 && tir2SD==null){
                        tir2SD = new TirJoueur2SD(this);
                        game().add(tir2SD);
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
	 *Action : déplace le perso à l'horizontal
	 *@param sens entier indiquant le sens de direction du perso
	 */
        private void déplaceX(int sens)
        {
            final int nbPixels = 40; 
            int dx = sens*nbPixels;
            int futurLeft = getLeft()+dx;
            int futurRight = getRight()+dx;
            
            if(futurLeft>=40 && futurRight<game().width()-40){
                this.moveX(dx);
            }
        }
        /**
	 *Action : déplace le perso à la vertical
	 *@param sens entier indiquant le sens de direction du perso
	 */
        private void déplaceY(int sens)
        {
            final int nbPixels = 40; 
            int dx = sens*nbPixels;
            int futurUp = getTop()+dx;
            int futurDown = getBottom()+dx;
            
            if(futurDown>=40 && futurUp<game().height()-40)
                this.moveY(dx);                            
        }


        
        
        public void setGestionMap(GestionMap ges){
            gestion = ges;
        }
    
    public void doTirer(int val){
        this.tire(val);
    }
    
    public Joueur getPlayer(){
        return gestion.getPlayer();
    }
    
    public int getVie() {
		return this.vie;
	}
    
    public int getPouvArme(){
            return pouvArme;
        }
        
    public void setPouvArme(int val){
            pouvArme += val;
        }
        
    public void setSp(int val){
            spleft += val;
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
    
    public void doTireSp(){
            tireSp();
        }
}