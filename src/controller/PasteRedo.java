package controller;

public class PasteRedo
{
    private ObserverHandler observerHandler = ObserverHandler.getInstance();

    public PasteRedo() {}

    public void redoShapePaste()
    {
        IShapeObserver shape = observerHandler.getRedoableList().removeLast();
        observerHandler.getMasterList().add(shape);

        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();
    }
}
