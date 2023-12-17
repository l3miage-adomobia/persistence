
package shapesTest;


import com.example.shapes.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CircleTest {

    @Test
    public void testGetX() {
        // Cr�e un objet Circle
        Circle circle = new Circle(50, 50);

        // V�rifie la valeur de X
        assertEquals(25, circle.getX());
    }

    @Test
    public void testGetY() {
        // Cr�e un objet Circle
        Circle circle = new Circle(50, 50);

        // V�rifie la valeur de Y
        assertEquals(25, circle.getY());
    }
    @Test
    public void testMove() {
        // Cr�e un objet Circle
        Circle circle = new Circle(50, 50);

        // D�place le cercle de (dx, dy)
        int dx = 10;
        int dy = 20;
        circle.move(dx, dy);

        // V�rifie les nouvelles valeurs de X et Y apr�s le d�placement
        assertEquals(25 + dx, circle.getX());
        assertEquals(25 + dy, circle.getY());
    }
    @Test
    public void testSetmX() {
        // Cr�e un objet Circle
        Circle circle = new Circle(50, 50);

        // Modifie la valeur de mX
        int newX = 30;
        circle.setmX(newX);

        // V�rifie la nouvelle valeur de X
        assertEquals(newX, circle.getX());
    }

    @Test
    public void testSetmY() {
        // Cr�e un objet Circle
        Circle circle = new Circle(50, 50);

        // Modifie la valeur de mY
        int newY = 40;
        circle.setmY(newY);

        // V�rifie la nouvelle valeur de Y
        assertEquals(newY, circle.getY());
    }

    @Test
    public void testGetType() {
        // Cr�e un objet Circle
        Circle circle = new Circle(50, 50);

        // V�rifie le type de la forme
        assertEquals("circle", circle.getType());
    }
}


