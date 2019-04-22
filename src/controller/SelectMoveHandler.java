package controller;

import view.gui.ShapeConfig;

import java.awt.*;

public class SelectMoveHandler
{
    ObserverHandler observerHandler = ObserverHandler.getInstance();

    private static volatile SelectMoveHandler instance = null;

    private SelectMoveHandler() { }

    public static SelectMoveHandler getInstance() {
        if (instance == null) {
            synchronized(SelectMoveHandler.class) {
                if (instance == null) {
                    instance = new SelectMoveHandler();
                }
            }
        }
        return instance;
    }

    public void selectShape(Point startCoords, Point endCoords)
    {
        observerHandler.getSelectionList().clear();
        int selectionX = ((int) startCoords.getX());
        int selectionY = ((int) startCoords.getY());
        int selectionHeight = (int) ((int) endCoords.getY() - startCoords.getY());
        int selectionWidth = (int) ((int) endCoords.getX() - startCoords.getX());

        ShapeConfig currentShape;

        for (IShapeObserver s : observerHandler.getMasterList())
        {
            currentShape = s.getShapeConfig();

            if (currentShape.getStartX() < selectionX + selectionWidth
                    && currentShape.getStartX() + currentShape.getWidth() >
                    selectionX && currentShape.getStartY() < selectionY + selectionHeight
                    && currentShape.getHeight() + currentShape.getStartY() > selectionY)
            {
                observerHandler.getSelectionList().add(s);
            }
        }
    }

    public void moveShape(Point startCoords, Point endCoords)
    {
        ShapeConfig currentShape;

        int moveWidth = (int) (endCoords.getX() - startCoords.getX());
        int moveHeight = (int) (endCoords.getY() - startCoords.getY());

        for (IShapeObserver s : observerHandler.getSelectionList())
        {
            currentShape = s.getShapeConfig();

            Point newStartCoords = new Point((currentShape.getStartX()) + moveWidth,
                    currentShape.getStartY() + moveHeight);
            Point newEndCoords = new Point((currentShape.getWidth() + (int) newStartCoords.getX()),
                    currentShape.getHeight() + (int) newStartCoords.getY());

            currentShape.setStartCoords(newStartCoords);
            currentShape.setEndCoords(newEndCoords);
        }

        // Repaint()
        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();
        observerHandler.notifySelectionObservers();
    }
}
