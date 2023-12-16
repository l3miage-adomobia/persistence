package persistence;
import edu.uga.miage.m1.polygons.gui.shapes.Circle;
import edu.uga.miage.m1.polygons.gui.shapes.CompositeShape;
import edu.uga.miage.m1.polygons.gui.shapes.Square;
import edu.uga.miage.m1.polygons.gui.shapes.Triangle;
import edu.uga.miage.m1.polygons.gui.persistence.XMLVisitor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class XMLVisitorTest {

    private XMLVisitor xmlVisitor;

    @Before
    public void setUp() {
        xmlVisitor = new XMLVisitor();
    }

    @Test
    public void testVisitCircle() {
        Circle circle = new Circle(10, 20);
        circle.accept(xmlVisitor);

        String expectedXml = "<shape><type>circle</type><x>-15</x><y>-5</y></shape>";
        assertEquals(expectedXml, xmlVisitor.getRepresentation());
    }

    @Test
    public void testVisitSquare() {
        Square square = new Square(30, 40);
        square.accept(xmlVisitor);

        String expectedXml = "<shape><type>square</type><x>5</x><y>15</y></shape>";
        assertEquals(expectedXml, xmlVisitor.getRepresentation());
    }

    @Test
    public void testVisitTriangle() {
        Triangle triangle = new Triangle(50, 60);
        triangle.accept(xmlVisitor);

        String expectedXml = "<shape><type>triangle</type><x>25</x><y>35</y></shape>";
        assertEquals(expectedXml, xmlVisitor.getRepresentation());
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

        compositeShape.accept(xmlVisitor);

        String expectedXml = "<shape><type>composite</type><shapes>" +
                "<shape><type>circle</type><x>-15</x><y>-5</y></shape>" +
                "<shape><type>square</type><x>5</x><y>15</y></shape>" +
                "<shape><type>triangle</type><x>25</x><y>35</y></shape>" +
                "</shapes></shape>";

        assertEquals(expectedXml, xmlVisitor.getRepresentation());
    }
}
