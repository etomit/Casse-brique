import java.awt.Color;
import java.awt.Graphics;

public class Brique extends Sprite {
    private int durete;
    private boolean visible;

    public Brique(int x, int y, int largeur, int hauteur, int durete) {
        super(x, y, largeur, hauteur);
        this.durete = durete;
        this.visible = true;
    }

    public boolean estIntacte() {
        return durete > 0;
    }

    public void reduireDurete() {
        if (durete > 0) {
            durete--;
        }
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void paint(Graphics g) {
        if (visible) {
            if (durete == 3) {
                g.setColor(Color.BLUE);
            } else if (durete == 2) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.GREEN);
            }

            g.fillRect(x, y, width, height);
        }
    }
}

