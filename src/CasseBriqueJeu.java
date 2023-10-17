import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CasseBriqueJeu extends JFrame {
    private Balle balle;
    private Barre barre;
    private ArrayList<Brique> briques;
    private boolean victoire = false;
    private void gestionBalleHorsLimite() {
        if (balle.getY() >= getHeight()) {
            // La balle est en bas de la fenêtre, réinitialiser sa position au-dessus de la raquette
            balle.setX(barre.getX() + barre.getWidth() / 2 - balle.getWidth() / 2);
            balle.setY(barre.getY() - balle.getHeight());
        }
    }


    public CasseBriqueJeu() {
        balle = new Balle(250, 400, 20, 20);
        barre = new Barre(200, 450, 100, 20);
        initialiserBriques();

        setTitle("Casse-Briques");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                balle.paint(g);
                barre.paint(g);
                for (Brique brique : briques) {
                    brique.paint(g);
                }

                afficherVictoire(g);
            }

        };


        panel.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                barre.gererDeplacement(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                barre.arreterDeplacement(e.getKeyCode());
            }
        });

        panel.setFocusable(true);
        add(panel);
        gestionBalleHorsLimite();


        new Thread(() -> {
            while (!victoire) {
                balle.deplacer();
                barre.deplacer();
                gestionCollisionBarre();
                gestionCollisionBrique();
                gestionBalleHorsLimite(); // Ajouter cette gestion
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Vérifier la victoire après la gestion des collisions
                if (victoire) {
                    break;
                }
            }
        }).start();
    }

    private void initialiserBriques() {
        briques = new ArrayList<>();

        int nombreDeBriques = 10;
        int nombreDeLignes = 4;
        int espaceEntreBriques = 5;
        int espaceEntreLignes = 10;
        int largeurBrique = (500 - (nombreDeBriques - 1) * espaceEntreBriques) / nombreDeBriques;
        int hauteurBrique = 15;

        // Ajouter les lignes de briques
        for (int j = 0; j < nombreDeLignes; j++) {
            for (int i = 0; i < nombreDeBriques; i++) {
                int xBrique = i * (largeurBrique + espaceEntreBriques);
                int yBrique = (nombreDeLignes - j) * (hauteurBrique + espaceEntreBriques + espaceEntreLignes); // Inverser l'ordre de dureté
                Brique brique = new Brique(xBrique, yBrique, largeurBrique, hauteurBrique, nombreDeLignes - j);
                briques.add(brique);
            }
        }
    }

    private void gestionCollisionBarre() {
        if (balle.collisionAvecBarre(barre)) {
            balle.inverserAngle();
        }
    }

    private void gestionCollisionBrique() {
        Iterator<Brique> iterator = briques.iterator();
        while (iterator.hasNext()) {
            Brique brique = iterator.next();
            if (brique.isVisible() && balle.collisionAvecBrique(brique)) {
                brique.reduireDurete();

                if (!brique.estIntacte()) {
                    iterator.remove();
                }

                balle.inverserAngle();
            }
        }

        // Vérifier si toutes les briques ont été détruites
        if (briques.isEmpty()) {
            victoire = true;
        }
    }

    private void afficherVictoire(Graphics g) {
        if (victoire) {
            g.setColor(Color.BLACK);
            g.drawString("Victoire!", 220, 250);
        }
    }

    public static void main(String[] args) {
        CasseBriqueJeu jeu = new CasseBriqueJeu();
        jeu.setVisible(true);
    }
}
