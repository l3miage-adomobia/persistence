package shapesTest;

import static org.junit.Assert.assertEquals;

import com.example.shapes.*;
import org.junit.Test;

public class TriangleTest {

    @Test
    public void testGetX() {
        // Crée un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // Vérifie la valeur de X
        assertEquals(25, triangle.getX());
    }

    @Test
    public void testGetY() {
        // Crée un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // Vérifie la valeur de Y
        assertEquals(25, triangle.getY());
    }
    @Test
    public void testMove() {
        // Crée un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // Déplace le triangle de (dx, dy)
        int dx = 10;
        int dy = 20;
        triangle.move(dx, dy);

        // Vérifie les nouvelles valeurs de X et Y après le déplacement
        assertEquals(25 + dx, triangle.getX());
        assertEquals(25 + dy, triangle.getY());
    }

    @Test
    public void testSetmX() {
        // Crée un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // Modifie la valeur de mX
        int newX = 30;
        triangle.setmX(newX);

        // Vérifie la nouvelle valeur de X
        assertEquals(newX, triangle.getX());
    }

    @Test
    public void testSetmY() {
        // Crée un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // Modifie la valeur de mY
        int newY = 40;
        triangle.setmY(newY);

        // Vérifie la nouvelle valeur de Y
        assertEquals(newY, triangle.getY());
    }

    @Test
    public void testGetType() {
        // Crée un objet Triangle
        Triangle triangle = new Triangle(50, 50);

        // Vérifie le type de la forme
        assertEquals("triangle", triangle.getType());
    }
}

