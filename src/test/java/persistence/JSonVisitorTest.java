package persistence;
import edu.uga.miage.m1.polygons.gui.persistence.JSonVisitor;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.CompositeShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JSonVisitorTest {

    private JSonVisitor jsonVisitor;

    @Before
    public void setUp() {
        jsonVisitor = new JSonVisitor();
    }

    @Test
    public void testVisitCircle() {
        Circle circle = new Circle(10, 20);
        circle.accept(jsonVisitor);

        String expectedJson = "{ \"type\": \"circle\",\"x\":-15,\"y\":-5},";
        assertEquals(expectedJson, jsonVisitor.getRepresentation());
    }

    @Test
    public void testVisitSquare() {
        Square square = new Square(30, 40);
        square.accept(jsonVisitor);

        String expectedJson = "{\"type\": \"square\",\"x\":5,\"y\":15},";
        assertEquals(expectedJson, jsonVisitor.getRepresentation());
    }

    @Test
    public void testVisitTriangle() {
        Triangle triangle = new Triangle(50, 60);
        triangle.accept(jsonVisitor);

        String expectedJson = "{\"type\": \"triangle\",\"x\":25,\"y\":35},";
        assertEquals(expectedJson, jsonVisitor.getRepresentation());
    }
    @Test
    public void testVisitCompositeShape() {
        CompositeShape compositeShape = new CompositeShape();
        Circle circle = new Circle(10, 20);
        Square square = new Square(30, 40);
        Triangle triangle = new Triangle(50, 60);

        compositeShape.addToShape(circle);
        compositeShape.addToShape(square);
        compositeShape.addToShape(triangle);

        compositeShape.accept(jsonVisitor);

        String expectedJson = "{ \"type\": \"composite\", \"shapes\": [" +
                "{ \"type\": \"circle\",\"x\":-15,\"y\":-5},," +
                "{\"type\": \"square\",\"x\":5,\"y\":15},," +
                "{\"type\": \"triangle\",\"x\":25,\"y\":35}" +
                ",]},";
        assertEquals(expectedJson, jsonVisitor.getRepresentation());
    }
}





