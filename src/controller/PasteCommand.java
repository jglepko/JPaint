package controller;

public class PasteCommand implements ICommand, IUndoable
{
    private ObserverHandler observerHandler = ObserverHandler.getInstance();
    private PasteUndo pasteUndo;
    private PasteRedo pasteRedo;

    public PasteCommand()
    {
        pasteUndo = new PasteUndo();
        pasteRedo = new PasteRedo();
    }

    @Override
    public void run()
    {
        observerHandler.setFlag(true);
        observerHandler.notifyMasterObservers();

        CommandHistory.add(this);
    }

    @Override
    public void undo()
    {
        this.pasteUndo.undoShapePaste();
    }

    @Override
    public void redo()
    {
        this.pasteRedo.redoShapePaste();
    }
}
