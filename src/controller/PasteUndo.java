package controller;

public class PasteUndo
{
    private ObserverHandler observerHandler = ObserverHandler.getInstance();

    public PasteUndo() { }

    public void undoShapePaste()
    {
        IShapeObserver shape = observerHandler.getMasterList().removeLast();
        observerHandler.getRedoableList().add(shape);

        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();
    }
}
