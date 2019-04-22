package controller;

public class DeleteUndo
{
    private ObserverHandler observerHandler = ObserverHandler.getInstance();

    public DeleteUndo() { }

    public void undoShapeDelete()
    {
        IShapeObserver shape = observerHandler.getUndoableList().removeLast();
        observerHandler.getMasterList().add(shape);

        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();
    }
}
