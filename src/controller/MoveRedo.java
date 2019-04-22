package controller;

import view.gui.ShapeConfig;

import java.awt.*;
import java.util.LinkedList;

public class MoveRedo
{
    private Point startCoords;
    private Point endCoords;
    private int changeInX;
    private int changeInY;
    private LinkedList<Integer> pointXredo = new LinkedList<Integer>();
    private LinkedList<Integer> pointYredo = new LinkedList<Integer>();
    ObserverHandler observerHandler = ObserverHandler.getInstance();

    public MoveRedo(Point startCoords, Point endCoords)
    {
        this.startCoords = startCoords;
        this.endCoords = endCoords;
        this.changeInX = endCoords.x - startCoords.x;
        this.changeInY = endCoords.y - startCoords.y;

        pointXredo.add(changeInX);
        pointYredo.add(changeInY);
    }

    public void redoShapeMove()
    {
        ShapeConfig currentShape;

        for (IShapeObserver s : observerHandler.getSelectionList())
        {
            currentShape = s.getShapeConfig();

            Point newStartCoords = new Point((currentShape.getStartX()) + this.changeInX,
                    currentShape.getStartY() + this.changeInY);
            Point newEndCoords = new Point((currentShape.getEndX() + this.changeInX),
                    currentShape.getEndY() + this.changeInY);

            currentShape.setStartCoords(newStartCoords);
            currentShape.setEndCoords(newEndCoords);
        }
        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();
        observerHandler.notifySelectionObservers();
    }

    public LinkedList<Integer> getPointXredo() { return pointXredo; }

    public LinkedList<Integer> getPointYredo()
    {
        return pointYredo;
    }
}
