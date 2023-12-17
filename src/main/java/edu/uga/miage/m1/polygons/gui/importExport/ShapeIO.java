package edu.uga.miage.m1.polygons.gui.importExport;

import edu.uga.miage.m1.polygons.gui.shapes.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShapeIO {

    public void exportShapes(List<SimpleShape> shapes, String filePath) throws IOException {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Créer un document XML
            Document document = documentBuilder.newDocument();

            // Créer l'élément racine
            Element rootElement = document.createElement("shapes");
            document.appendChild(rootElement);

            // Ajouter les formes comme des éléments enfants
            for (SimpleShape shape : shapes) {
                Element shapeElement = document.createElement("shape");

                Element typeElement = document.createElement("type");
                typeElement.appendChild(document.createTextNode(shape.getType()));
                shapeElement.appendChild(typeElement);

                Element xElement = document.createElement("x");
                xElement.appendChild(document.createTextNode(String.valueOf(shape.getX())));
                shapeElement.appendChild(xElement);

                Element yElement = document.createElement("y");
                yElement.appendChild(document.createTextNode(String.valueOf(shape.getY())));
                shapeElement.appendChild(yElement);

                rootElement.appendChild(shapeElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Ajouter des propriétés pour l'indentation
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);




        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SimpleShape> importShapes(String filePath) throws IOException {
        List<SimpleShape> shapes = new ArrayList<>();

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            // Charger le document XML depuis le fichier
            Document document = documentBuilder.parse(new File(filePath));

            // Récupérer les éléments "shape"
            NodeList shapeList = document.getElementsByTagName("shape");

            // Parcourir les éléments "shape"
            for (int i = 0; i < shapeList.getLength(); i++) {
                Node shapeNode = shapeList.item(i);

                if (shapeNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element shapeElement = (Element) shapeNode;

                    // Récupérer les propriétés de la forme
                    String type = shapeElement.getElementsByTagName("type").item(0).getTextContent();
                    int x = Integer.parseInt(shapeElement.getElementsByTagName("x").item(0).getTextContent());
                    int y = Integer.parseInt(shapeElement.getElementsByTagName("y").item(0).getTextContent());

                    // Créer une instance de SimpleShape et l'ajouter à la liste
                    SimpleShape shape;
                    switch (type){
                        case "circle":
                             shape =  new Circle(x, y);
                             break;
                        case "square":
                             shape =  new Square(x, y);
                            break;
                        case "triangle":
                             shape =  new Triangle(x, y);
                            break;
                        default :
                            shape =  new Cube(50,x, y);
                            break;
                    }
                    shapes.add(shape);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return shapes;
    }

}
