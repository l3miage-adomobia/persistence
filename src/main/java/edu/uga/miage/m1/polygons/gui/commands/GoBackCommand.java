package edu.uga.miage.m1.polygons.gui.commands;

import edu.uga.miage.m1.polygons.gui.JDrawingFrame;

public class GoBackCommand implements Command{

    private JDrawingFrame jDrawingFrame;
    public GoBackCommand(JDrawingFrame jDrawingFrame){
        this.jDrawingFrame = jDrawingFrame;
    }
    @Override
    public void execute() {
        jDrawingFrame.goBack();
    }
}
