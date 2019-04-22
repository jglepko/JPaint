package controller;

import controller.ICommand;
import controller.SelectMoveHandler;

import java.awt.*;

public class SelectShape implements ICommand
{
    private Point startCoords;
    private Point endCoords;

    public SelectShape(Point startCoords, Point endCoords)
    {
        this.startCoords = startCoords;
        this.endCoords = endCoords;
    }

    @Override
    public void run()
    {
        SelectMoveHandler selectMoveHandler = SelectMoveHandler.getInstance();
        selectMoveHandler.selectShape(startCoords, endCoords);
    }
}
