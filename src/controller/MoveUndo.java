package controller;

import view.gui.ShapeConfig;

import java.awt.*;
import java.util.LinkedList;

public class MoveUndo
{
    private Point startCoords;
    private Point endCoords;
    private int changeInX;
    private int changeInY;
    private LinkedList<Integer> pointXundo = new LinkedList<Integer>();
    private LinkedList<Integer> pointYundo = new LinkedList<Integer>();
    ObserverHandler observerHandler = ObserverHandler.getInstance();

    public MoveUndo(Point startCoords, Point endCoords)
    {
        this.startCoords = startCoords;
        this.endCoords = endCoords;
        this.changeInX = endCoords.x - startCoords.x;
        this.changeInY = endCoords.y - startCoords.y;

        pointXundo.add(changeInX);
        pointYundo.add(changeInY);
    }

    public void undoShapeMove()
    {
        ShapeConfig currentShape;

        for (IShapeObserver s : observerHandler.getSelectionList())
        {
            currentShape = s.getShapeConfig();

            Point newStartCoords = new Point((currentShape.getStartX()) - this.changeInX,
                    currentShape.getStartY() - this.changeInY);
            Point newEndCoords = new Point((currentShape.getEndX() - this.changeInX),
                    currentShape.getEndY() - this.changeInY);

            currentShape.setStartCoords(newStartCoords);
            currentShape.setEndCoords(newEndCoords);
        }
        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();
        observerHandler.notifySelectionObservers();
    }

    public LinkedList<Integer> getPointXundo()
    {
        return pointXundo;
    }

    public LinkedList<Integer> getPointYundo()
    {
        return pointYundo;
    }
}
