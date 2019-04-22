package controller;

import view.gui.PaintCanvas;

import java.awt.*;
import java.util.LinkedList;

public class ObserverHandler implements IObserverHandler
{
    private LinkedList<IShapeObserver> masterList = new LinkedList<IShapeObserver>();
    private LinkedList<IShapeObserver> selectionList = new LinkedList<IShapeObserver>();
    private LinkedList<IShapeObserver> undoableList = new LinkedList<IShapeObserver>();
    private LinkedList<IShapeObserver> redoableList = new LinkedList<IShapeObserver>();
    PaintCanvas paintCanvas = PaintCanvas.getInstance();
    private Boolean flag = false;

    private static volatile ObserverHandler instance = null;

    private ObserverHandler() { }

    public static ObserverHandler getInstance() {
        if (instance == null) {
            synchronized(ObserverHandler.class) {
                if (instance == null) {
                    instance = new ObserverHandler();
                }
            }
        }
        return instance;
    }

    public void registerObserver(IShapeObserver observer)
    {
        masterList.add(observer);

        notifyMasterObservers();
    }

    public void undoRedoObserver(LinkedList removeFrom, LinkedList addTo)
    {
        IShapeObserver result = (IShapeObserver) removeFrom.peekLast();
        if (result != null)
        {
            IShapeObserver shape = (IShapeObserver) removeFrom.removeLast();
            addTo.add(shape);
            setFlag(true);
            notifyMasterObservers();
            notifySelectionObservers();
        }
    }

    public void setFlag(Boolean choice)
    {
        flag = choice;
    }

    public void notifyMasterObservers()
    {
        Graphics2D g2 = paintCanvas.getGraphics2D();

        if (flag)
            g2.clearRect(0, 0, 1200, 800);

        for (IShapeObserver observer : masterList)
        {
            observer.update();
        }
        flag = false;
    }

    public void notifySelectionObservers()
    {
        Graphics2D g2 = paintCanvas.getGraphics2D();

        if (flag)
            g2.clearRect(0, 0, 1200, 800);

        for (IShapeObserver observer : selectionList)
        {
            observer.update();
        }
        flag = false;
    }

    public LinkedList<IShapeObserver> getMasterList()
    {
        return masterList;
    }

    public LinkedList<IShapeObserver> getSelectionList()
    {
        return selectionList;
    }

    public LinkedList<IShapeObserver> getUndoableList()
    {
        return undoableList;
    }

    public LinkedList<IShapeObserver> getRedoableList()
    {
        return redoableList;
    }
}
