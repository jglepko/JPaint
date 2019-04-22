package controller;

public class DeleteCommand implements ICommand, IUndoable
{
    private ObserverHandler observerHandler = ObserverHandler.getInstance();
    private IShapeObserver shape = null;
    private DeleteUndo deleteUndo;
    private DeleteRedo deleteRedo;
    private int i = 0;

    public DeleteCommand()
    {
        deleteUndo = new DeleteUndo();
        deleteRedo = new DeleteRedo();
    }

    @Override
    public void run()
    {
        for (IShapeObserver s : observerHandler.getSelectionList())
        {
            for (IShapeObserver m : observerHandler.getMasterList())
            {
                if (s == m)
                {
                    shape = observerHandler.getMasterList().removeFirst();
                    observerHandler.getUndoableList().add(shape);
                }
            }
        }
        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();

        CommandHistory.add(this);
    }

    @Override
    public void undo()
    {
        this.deleteUndo.undoShapeDelete();
    }

    @Override
    public void redo()
    {
        this.deleteRedo.redoShapeDelete();
    }
}
