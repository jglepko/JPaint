package controller;

import java.util.LinkedList;

public interface IObserverHandler
{
    void registerObserver(IShapeObserver observer);
    void undoRedoObserver(LinkedList removeFrom, LinkedList addTo);
    LinkedList<IShapeObserver> getMasterList();
    LinkedList<IShapeObserver> getSelectionList();
    LinkedList<IShapeObserver> getUndoableList();
}
