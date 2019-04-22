package controller;

public class DeleteRedo
{
    private ObserverHandler observerHandler = ObserverHandler.getInstance();

    public DeleteRedo() { }

    public void redoShapeDelete()
    {
        IShapeObserver shape = observerHandler.getMasterList().removeLast();
        observerHandler.getUndoableList().add(shape);

        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();
    }

}
