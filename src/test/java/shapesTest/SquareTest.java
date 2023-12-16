
package shapesTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import edu.uga.miage.m1.polygons.gui.shapes.Square;

public class SquareTest {


    @Test
    public void testGetX() {
        // Crée un objet Square
        Square square = new Square(50, 50);

        // Vérifie la valeur de X
        assertEquals(25, square.getX());
    }

    @Test
    public void testGetY() {
        // Crée un objet Square
        Square square = new Square(50, 50);

        // Vérifie la valeur de Y
        assertEquals(25, square.getY());
    }
    @Test
    public void testMove() {
        // Crée un objet Square
        Square square = new Square(50, 50);

        // Déplace le carré de (dx, dy)
        int dx = 10;
        int dy = 20;
        square.move(dx, dy);

        // Vérifie les nouvelles valeurs de X et Y après le déplacement
        assertEquals(25 + dx, square.getX());
        assertEquals(25 + dy, square.getY());
    }

    @Test
    public void testSetmX() {
        // Crée un objet Square
        Square square = new Square(50, 50);

        // Modifie la valeur de mX
        int newX = 30;
        square.setmX(newX);

        // Vérifie la nouvelle valeur de X
        assertEquals(newX, square.getX());
    }

    @Test
    public void testSetmY() {
        // Crée un objet Square
        Square square = new Square(50, 50);

        // Modifie la valeur de mY
        int newY = 40;
        square.setmY(newY);

        // Vérifie la nouvelle valeur de Y
        assertEquals(newY, square.getY());
    }

    @Test
    public void testGetType() {
        // Crée un objet Square
        Square square = new Square(50, 50);

        // Vérifie le type de la forme
        assertEquals("square", square.getType());
    }
}

