package edu.uga.miage.m1.polygons.gui.commands;

public class CommandInvoker {
    private Command command;

    public CommandInvoker(Command c){
        this.command=c;
    }

    public void executeCommand(){
        this.command.execute();
    }
}
