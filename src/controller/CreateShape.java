package controller;

import controller.*;
import model.ShapeType;
import model.persistence.ApplicationState;
import model.persistence.ColorAdapter;
import view.gui.ShapeConfig;

import java.awt.*;

public class CreateShape implements ICommand, IUndoable
{
    private Point startCoords;
    private Point endCoords;
    private ApplicationState appState;
    private IShapeObserver ishape;
    IObserverHandler observerHandler = ObserverHandler.getInstance();
    ColorAdapter cAdapter = new ColorAdapter();

    public CreateShape(Point startCoords, Point endCoords, ApplicationState appState)
    {
        this.startCoords = startCoords;
        this.endCoords = endCoords;
        this.appState = appState;
    }

    // run method should create the data object for the shape and add it to shapeList

    @Override
    public void run()
    {
        ShapeConfig dataObject = dumpData(startCoords, endCoords);

        if (appState.getActiveShapeType().equals(ShapeType.ELLIPSE))
        {
            ishape = IShapeFactory.createCircle(dataObject);
        }
        else if (appState.getActiveShapeType().equals(ShapeType.RECTANGLE))
        {
            ishape = IShapeFactory.createRectangle(dataObject);
        }
        else // appState.equals(Triangle)
        {
            ishape = IShapeFactory.createTriangle(dataObject);
        }

        observerHandler.registerObserver(ishape);
        CommandHistory.add(this);
    }

    public ShapeConfig dumpData(Point startCoords, Point endCoords)
    {
        return new ShapeConfig(startCoords, endCoords,
                cAdapter.convert(appState.getActivePrimaryColor()),
                cAdapter.convert(appState.getActiveSecondaryColor()),
                appState.getActiveShapeShadingType(), appState.getActiveShapeType());
    }

    @Override
    public void undo() {
        observerHandler.undoRedoObserver(observerHandler.getMasterList(),
                observerHandler.getUndoableList());
    }

    @Override
    public void redo() {
        observerHandler.undoRedoObserver(observerHandler.getUndoableList(),
                observerHandler.getMasterList());
    }
}
