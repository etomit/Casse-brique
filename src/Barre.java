import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Barre extends Sprite {
    private int vitesse;

    public Barre(int x, int y, int largeur, int hauteur) {
        super(x, y, largeur, hauteur);
        this.vitesse = 10;
    }
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    public void deplacerGauche() {
        int newX = x - vitesse;
        if (newX >= 0) {
            setX(newX);
        }
    }

    public void deplacerDroite() {
        int newX = x + vitesse;
        if (newX <= 500 - getWidth()) {
            setX(newX);
        }
    }

    public void deplacer() {
        // Ajoute ici le code pour déplacer la barre selon la logique souhaitée
        // Par exemple, utiliser deplacerGauche() et deplacerDroite() en fonction des commandes
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void gererDeplacement(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT) {
            deplacerGauche();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            deplacerDroite();
        }
    }

    public void arreterDeplacement(int keyCode) {
        // Ajoute ici le code pour arrêter le déplacement si nécessaire
    }
}
