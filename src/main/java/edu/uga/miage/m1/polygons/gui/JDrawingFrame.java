package edu.uga.miage.m1.polygons.gui;
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */


import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.List;

import javax.swing.*;

import edu.uga.miage.m1.polygons.gui.commands.GoBackCommand;
import edu.uga.miage.m1.polygons.gui.shapes.*;


/**
 * This class represents the main application class, which is a JFrame subclass
 * that manages a toolbar of shapes and a drawing canvas.
 * 
 * @author <a href="mailto:christophe.saint-marcel@univ-grenoble-alpes.fr">Christophe</a>
 *
 */
public class JDrawingFrame extends JFrame
    implements MouseListener, MouseMotionListener
{
    private transient ArrayList<SimpleShape> listOfShapes = new ArrayList<>();

    public enum Shapes {SQUARE, TRIANGLE, CIRCLE, CUBE}
    private static final long serialVersionUID = 1L;
    private JToolBar mToolbar;
    private Shapes mSelected;
    private JPanel mPanel;
    private JLabel mLabel;
    private transient SimpleShape movingShape;
    private transient List<ArrayList<SimpleShape>> history = new LinkedList<>();
    private Point startPosition;
    private transient ActionListener mReusableActionListener = new ShapeActionListener();
    //our new action listener for the export button
    private transient ActionListener mExportActionListener = new ExportActionListener();
    public final transient GoBackCommand goBackCommand = new GoBackCommand(this);
    //our 2D arraylist to store the shapes and their coordinates
    private transient ArrayList<String[]> mShapeList = new ArrayList<>();
    /**
     * Tracks buttons to manage the background.
     */
    private EnumMap<Shapes, JButton> mButtons = new EnumMap<>(Shapes.class);
    /**
     * Default constructor that populates the main window.
     * @param frameName 
    **/
    public JDrawingFrame(String frameName)
    {
        super(frameName);
        // Instantiates components
        mToolbar = new JToolBar("Toolbar");
        mPanel = new JPanel();
        mPanel.setBackground(Color.WHITE);
        mPanel.setLayout(null);
        mPanel.setPreferredSize(new Dimension(1000, 1000));
        mPanel.addMouseListener(this);
        mPanel.addMouseMotionListener(this);
        mLabel = new JLabel(" ", SwingConstants.LEFT);



        // Fills the panel
        setLayout(new BorderLayout());
        add(mToolbar, BorderLayout.NORTH);
        add(mPanel, BorderLayout.CENTER);
        add(mLabel, BorderLayout.SOUTH);
        
        // Add shapes in the menu
        addShape(Shapes.SQUARE, new ImageIcon(getClass().getResource("images/square.png")));
        addShape(Shapes.TRIANGLE, new ImageIcon(getClass().getResource("images/triangle.png")));
        addShape(Shapes.CIRCLE, new ImageIcon(getClass().getResource("images/circle.png")));
        addShape(Shapes.CUBE, new ImageIcon(getClass().getResource("images/underc.png")));

        // add button
        addButtonExport("activeexport", new ImageIcon(getClass().getResource("images/underc.png")));


        // add key listener to the frame
         new ActionHandler(this);
    }


    /**
     * Injects an available <tt>SimpleShape</tt> into the drawing frame.
     * @param icon The icon associated with the injected <tt>SimpleShape</tt>.
    **/
    public void addShape(Shapes shape, ImageIcon icon)
    {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        mButtons.put(shape, button);
        button.setActionCommand(shape.toString());
        button.addActionListener(mReusableActionListener);

        if (mSelected == null)
        {
            button.doClick();
        }

        mToolbar.add(button);
        mToolbar.validate();
        repaint();
    }



    //add a button to export the shapes in xml or json in the toolbar
    //using template of the function addShape()
    private void addButtonExport(String name, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setBorderPainted(false);
        button.setActionCommand(name);
        //attach the action listener to the button
        button.addActionListener(mExportActionListener);

        //add the button to the toolbar
        mToolbar.add(button);
        mToolbar.validate();
        repaint();
    }


    //methods that format the shape list to xml or json depending on user choice
    //iterate on the shape list and add the shape to the export string
    public void exportShape() {

        String fileType = "";
        String export = "";
        boolean validtype = false;
        // open a dialog box to choose the type of export (xml or json)
        fileType = javax.swing.JOptionPane.showInputDialog("Choisissez le type d'export ( 'xml' ou  'json' ?)");

        //iterate while the user doesn't choose a valid type
        while (!validtype){
            //case where the user chose to export in json (equalsIgnoreCase compare two strings without taking care of the case)
            if( "json".equalsIgnoreCase(fileType) ){

                export = exportJson(mShapeList);
                validtype = true;
            }
            //case where the user chose to export in xml (equalsIgnoreCase compare two strings without taking care of the case)
            else if("xml".equalsIgnoreCase(fileType)){
                export = exportXml(mShapeList);
                validtype = true;
            }else {
                javax.swing.JOptionPane.showConfirmDialog(null, "Vous n'avez pas saisi un type valide, veuillez recommencer");
                exportShape();
            }
        }


        // ask user for a file name
        String fileName = javax.swing.JOptionPane.showInputDialog("nom du fichier a enregistrer?");

        // open file explorer to choose where to save the file
        javax.swing.JFileChooser fileExplorer = new javax.swing.JFileChooser();
        fileExplorer.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        fileExplorer.showSaveDialog(null);

        File selectedFile = fileExplorer.getSelectedFile();

        // create the file to save
        File file = new File(selectedFile.getParent(), fileName + "." + fileType);



        try( java.io.PrintWriter output = new java.io.PrintWriter(file)) {
            output.print(export);
        } catch (Exception e) {
            //laissé sans traitement pour l'instant
        }

        javax.swing.JOptionPane.showMessageDialog(null, "fichier creee");

    }



    /**
     * Use the factory to abstract shape creation
     * Implements method for the <tt>MouseListener</tt> interface to
     * draw the selected shape into the drawing canvas.
     * @param evt The associated mouse event.
    **/

    public void mouseClicked(MouseEvent evt)
    {
        addHistoryList(listOfShapes);
        if (mPanel.contains(evt.getX(), evt.getY()))
        {
            drawShapes(evt.getX(), evt.getY());
        }
    }

    public void drawShapes( int x , int y){
        SimpleShape tempShape = null;
        Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
        switch(mSelected) {
            case CIRCLE:
                tempShape = new Circle(x,y);
                tempShape.draw(g2);
                //if the shape is a circle, add a circle with x and y coord to the shape list
                listOfShapes.add(tempShape);
                break;
            case TRIANGLE:
                tempShape = new Triangle(x, y);
                tempShape.draw(g2);
                //if the shape is a triangle, add a triangle with x and y coord to the shape list
                listOfShapes.add(tempShape);
                break;
            case SQUARE:
                tempShape = new Square(x, y);
                tempShape.draw(g2);
                //if the shape is a square, add a square with x and y coord to the shape list
                listOfShapes.add(tempShape);
                break;
            case CUBE :
                tempShape = new Cube(100,x,y);
                tempShape.draw(g2);
                listOfShapes.add(tempShape);
                break;
            default:
                break;


        }
    }

    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
    **/
    public void mouseEntered(MouseEvent evt){
                    //  laissé vide intentionnellement.
    }
    /**
     * Implements an empty method for the <tt>MouseListener</tt> interface.
     * @param evt The associated mouse event.
    **/
    public void mouseExited(MouseEvent evt)
    {
        mLabel.setText(" ");
        mLabel.repaint();
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to initiate
     * shape dragging.
     * @param evt The associated mouse event.
    **/

    public void mousePressed(MouseEvent evt)
    {
        for(SimpleShape simpleShape : listOfShapes ){
            if(simpleShape.contains(evt.getX(),evt.getY())){
                SimpleShape shapeCopy = null;
                switch (simpleShape.getType()){
                    case "circle":
                         shapeCopy =  new Circle(evt.getX(), evt.getY());
                         break;
                    case "square":
                        shapeCopy =  new Square(evt.getX(), evt.getY());
                        break;
                    case "triangle":
                        shapeCopy =  new Triangle(evt.getX(), evt.getY());
                        break;
                    case "cube":
                        shapeCopy =  new Cube(50,evt.getX(), evt.getY());
                        break;
                    default:
                        break;
                }
                List<SimpleShape> listCopy = new ArrayList<>(listOfShapes);
                listCopy.remove(simpleShape);
                listCopy.add(shapeCopy);
                addHistoryList(listCopy);
                movingShape = simpleShape;
                startPosition = evt.getPoint();
            }
        }
    }

    /**
     * Implements method for the <tt>MouseListener</tt> interface to complete
     * shape dragging.
     * @param evt The associated mouse event.
    **/
    public void mouseReleased(MouseEvent evt)
    {
        this.movingShape = null;
    }

    /**
     * Implements method for the <tt>MouseMotionListener</tt> interface to
     * move a dragged shape.
     * @param evt The associated mouse event.
    **/
    public void mouseDragged(MouseEvent evt)
    {
        if(this.movingShape != null){
            int diffX = evt.getX() - this.startPosition.x;
            int diffY = evt.getY() - this.startPosition.y;
            movingShape.move(diffX,diffY);
            redrawShapes();
            startPosition = evt.getPoint();
        }
    }
    public void appendShapeToList(String shapeName, int x, int y) {

        String[] shapeTab = {shapeName, Integer.toString(x), Integer.toString(y)};

        this.mShapeList.add(shapeTab);
    }

    public void instantiateListOfShapesText(){
        mShapeList.clear();
        if(!listOfShapes.isEmpty()){
            for(SimpleShape s : listOfShapes){
                appendShapeToList(s.getType(),s.getX(),s.getY());
            }
        }

    }
    /**
     * Implements an empty method for the <tt>MouseMotionListener</tt>
     * interface.
     * @param evt The associated mouse event.
    **/
    public void mouseMoved(MouseEvent evt)
    {
        modifyLabel(evt);
    }

private void modifyLabel(MouseEvent evt) {
        mLabel.setText("(" + evt.getX() + "," + evt.getY() + ")");
    }

    /**
     * Simple action listener for shape tool bar buttons that sets
     * the drawing frame's currently selected shape when receiving
     * an action event
    **/
    private class ShapeActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            // ItÃ¨re sur tous les boutons
            Iterator<Shapes> keys = mButtons.keySet().iterator();
            while (keys.hasNext()) {
                Shapes shape = keys.next();
                JButton btn = mButtons.get(shape);
                if (evt.getActionCommand().equals(shape.toString())) {
                    btn.setBorderPainted(true);
                    mSelected = shape;
                } else {
                    btn.setBorderPainted(false);
                }
                btn.repaint();
            }
        }
    }

    //change the given shape list into a json format
    public String exportJson(List<String[]> mShapeList){
        StringBuilder exportBuilder = new StringBuilder();
        exportBuilder.append("{\n\"shapes\":  [\n");
        instantiateListOfShapesText();

        for (int i = 0; i < mShapeList.size(); i++) {
            String[] iterateShape = mShapeList.get(i);
            exportBuilder.append(" {\n \"type\": \"")
                .append(iterateShape[0])
                .append("\",\n \"x\": ")
                .append(iterateShape[1])
                .append(",\n\"y\": ")
                .append(iterateShape[2])
                .append("\n}");
    
            // Vérifiez si c'est la dernière forme pour ajouter une nouvelle ligne ou non
            if (i < mShapeList.size() - 1) {
                exportBuilder.append(",\n");
            }
        }
    
        exportBuilder.append("\n  ]\n}");
    
        return exportBuilder.toString();
    }

    //change the given shape list into a xml format
    private String exportXml(List<String[]> mShapeList) {
        StringBuilder exportBuilder = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<root>\n<shapes>\n");
        instantiateListOfShapesText();

        for (int i = 0; i < mShapeList.size(); i++) {
            String[] tab = mShapeList.get(i);
    
            exportBuilder.append("\t<shape>\n")
                         .append("\t\t<type>").append(tab[0]).append("</type>\n")
                         .append("\t\t<x>").append(tab[1]).append("</x>\n")
                         .append("\t\t<y>").append(tab[2]).append("</y>\n")
                         .append("\t</shape>");
    
            if (i < mShapeList.size() - 1) {
                exportBuilder.append("\n");
            }
        }
    
        exportBuilder.append("\n</shapes>\n</root>");
    
        return exportBuilder.toString();
    }

    public void goBack() {
        if(!history.isEmpty()){
            int last = this.history.size() - 1;
            this.listOfShapes = this.history.get(last);
            this.history.remove(last);
            redrawShapes();
        }
    }
    public void redrawShapes() {
        Graphics2D g2 = (Graphics2D) mPanel.getGraphics();
        g2.setColor(Color.WHITE); // Clear the panel by painting it white
        g2.fillRect(0, 0, mPanel.getWidth(), mPanel.getHeight());

        // Redraw the remaining shapes from the listOfShapes
        for (SimpleShape shape : listOfShapes) {
            shape.draw(g2);
        }
    }
    private class ExportActionListener implements ActionListener {

        public void actionPerformed(ActionEvent evt) {

            if(evt.getActionCommand().equals("activeexport")) {
                exportShape();            }
        }
    }

    public void addHistoryList(List<SimpleShape> shapes) {
        ArrayList<SimpleShape> newShapes = new ArrayList<>(shapes);
        history.add(newShapes);
    }
    public List<String[]> getShapeList() {
        return this.mShapeList;
    }
    public List<SimpleShape> getListOfShapes() {
        return this.listOfShapes;
    }
    public void setSquareButton(JButton squareButton) {
        this.mButtons.put(Shapes.SQUARE, squareButton);
    }

    public void setTriangleButton(JButton triangleButton) {
        this.mButtons.put(Shapes.TRIANGLE, triangleButton);
    }

    public void setCircleButton(JButton circleButton) {
        this.mButtons.put(Shapes.CIRCLE, circleButton);
    }

    public Shapes getSelectedShape() {
        return mSelected;
    }

    public Map<Shapes, JButton> getButtons() {
        return mButtons;
    }
    public void setSelectedShape(Shapes selectedShape) {
        this.mSelected = selectedShape;
    }

}