import java.awt.Color;
import java.awt.Graphics;

public class Balle extends Sprite {
    private double angle;
    private double vitesse;
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Balle(int x, int y, int largeur, int hauteur) {
        super(x, y, largeur, hauteur);
        this.angle = Math.toRadians(45); // Angle initial (45 degrés)
        this.vitesse = 5;
    }

    public void deplacer() {
        int newX = (int) (x + vitesse * Math.cos(angle));
        int newY = (int) (y + vitesse * Math.sin(angle));

        setX(newX);
        setY(newY);

        // Vérifier et inverser la direction si la balle atteint les bords
        if (newX <= 0 || newX >= 500 - getWidth()) {
            angle = Math.PI - angle;
        }

        if (newY <= 0 || newY >= 500 - getHeight()) {
            angle = -angle;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(x, y, width, height);
    }

    public void inverserAngle() {
        angle = -angle;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    public int getXSpeed() {
        return (int) (vitesse * Math.cos(angle));
    }

    public int getYSpeed() {
        return (int) (vitesse * Math.sin(angle));
    }

    public boolean collisionAvecBarre(Barre barre) {
        return x + width >= barre.getX() &&
                x <= barre.getX() + barre.getWidth() &&
                y + height >= barre.getY() &&
                y <= barre.getY() + barre.getHeight();
    }

    public boolean collisionAvecBrique(Brique brique) {
        return x + width >= brique.getX() &&
                x <= brique.getX() + brique.getWidth() &&
                y + height >= brique.getY() &&
                y <= brique.getY() + brique.getHeight();
    }
}
