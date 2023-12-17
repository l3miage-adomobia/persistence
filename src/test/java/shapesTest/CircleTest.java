
package shapesTest;


import com.example.shapes.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class CircleTest {

    @Test
    public void testGetX() {
        // Crée un objet Circle
        Circle circle = new Circle(50, 50);

        // Vérifie la valeur de X
        assertEquals(25, circle.getX());
    }

    @Test
    public void testGetY() {
        // Crée un objet Circle
        Circle circle = new Circle(50, 50);

        // Vérifie la valeur de Y
        assertEquals(25, circle.getY());
    }
    @Test
    public void testMove() {
        // Crée un objet Circle
        Circle circle = new Circle(50, 50);

        // Déplace le cercle de (dx, dy)
        int dx = 10;
        int dy = 20;
        circle.move(dx, dy);

        // Vérifie les nouvelles valeurs de X et Y après le déplacement
        assertEquals(25 + dx, circle.getX());
        assertEquals(25 + dy, circle.getY());
    }
    @Test
    public void testSetmX() {
        // Crée un objet Circle
        Circle circle = new Circle(50, 50);

        // Modifie la valeur de mX
        int newX = 30;
        circle.setmX(newX);

        // Vérifie la nouvelle valeur de X
        assertEquals(newX, circle.getX());
    }

    @Test
    public void testSetmY() {
        // Crée un objet Circle
        Circle circle = new Circle(50, 50);

        // Modifie la valeur de mY
        int newY = 40;
        circle.setmY(newY);

        // Vérifie la nouvelle valeur de Y
        assertEquals(newY, circle.getY());
    }

    @Test
    public void testGetType() {
        // Crée un objet Circle
        Circle circle = new Circle(50, 50);

        // Vérifie le type de la forme
        assertEquals("circle", circle.getType());
    }
}


