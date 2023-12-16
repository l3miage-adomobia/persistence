package edu.uga.miage.m1.polygons.gui;

import edu.uga.miage.m1.polygons.gui.commands.CommandInvoker;
import edu.uga.miage.m1.polygons.gui.commands.GoBackCommand;

import java.awt.event.*;

public class ActionHandler implements KeyListener, ActionListener {
    private JDrawingFrame jdrawingFrame;  // R�f�rence � la fen�tre principale, si n�cessaire.

    public ActionHandler(JDrawingFrame drawingFrame) {
        this.jdrawingFrame = drawingFrame;
        drawingFrame.addKeyListener(this); // Ajoutez le KeyListener � la frame.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used 
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z && (e.getModifiersEx() & InputEvent.CTRL_DOWN_MASK) != 0) {
           CommandInvoker commandInvoker = new CommandInvoker(new GoBackCommand(jdrawingFrame));
            commandInvoker.executeCommand();
        }
        if (e.getKeyCode() == KeyEvent.VK_E) {
            CommandInvoker commandInvoker = new CommandInvoker(new GoBackCommand(jdrawingFrame));
            commandInvoker.executeCommand();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
                // Not used 

    }

    @Override
    public void actionPerformed(ActionEvent e) {
                // Not used 

    }
}
