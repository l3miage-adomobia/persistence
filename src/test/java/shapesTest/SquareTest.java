
package shapesTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import edu.uga.miage.m1.polygons.gui.shapes.Square;

public class SquareTest {


    @Test
    public void testGetX() {
        // Cr�e un objet Square
        Square square = new Square(50, 50);

        // V�rifie la valeur de X
        assertEquals(25, square.getX());
    }

    @Test
    public void testGetY() {
        // Cr�e un objet Square
        Square square = new Square(50, 50);

        // V�rifie la valeur de Y
        assertEquals(25, square.getY());
    }
    @Test
    public void testMove() {
        // Cr�e un objet Square
        Square square = new Square(50, 50);

        // D�place le carr� de (dx, dy)
        int dx = 10;
        int dy = 20;
        square.move(dx, dy);

        // V�rifie les nouvelles valeurs de X et Y apr�s le d�placement
        assertEquals(25 + dx, square.getX());
        assertEquals(25 + dy, square.getY());
    }

    @Test
    public void testSetmX() {
        // Cr�e un objet Square
        Square square = new Square(50, 50);

        // Modifie la valeur de mX
        int newX = 30;
        square.setmX(newX);

        // V�rifie la nouvelle valeur de X
        assertEquals(newX, square.getX());
    }

    @Test
    public void testSetmY() {
        // Cr�e un objet Square
        Square square = new Square(50, 50);

        // Modifie la valeur de mY
        int newY = 40;
        square.setmY(newY);

        // V�rifie la nouvelle valeur de Y
        assertEquals(newY, square.getY());
    }

    @Test
    public void testGetType() {
        // Cr�e un objet Square
        Square square = new Square(50, 50);

        // V�rifie le type de la forme
        assertEquals("square", square.getType());
    }
}

