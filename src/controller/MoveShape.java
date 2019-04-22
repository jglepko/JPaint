package controller;

import controller.ICommand;
import controller.IUndoable;
import controller.SelectMoveHandler;

import java.awt.*;

public class MoveShape implements ICommand, IUndoable
{
    private Point startCoords;
    private Point endCoords;
    private MoveUndo moveUndo;
    private MoveRedo moveRedo;

    public MoveShape(Point startCoords, Point endCoords)
    {
        this.startCoords = startCoords;
        this.endCoords = endCoords;
        moveUndo = new MoveUndo(startCoords, endCoords);
        moveRedo = new MoveRedo(startCoords, endCoords);
    }

    @Override
    public void run()
    {
        SelectMoveHandler selectMoveHandler = SelectMoveHandler.getInstance();
        selectMoveHandler.moveShape(startCoords, endCoords);

        CommandHistory.add(this);
    }

    @Override
    public void undo()
    {
        int pointX = this.moveUndo.getPointXundo().removeLast();
        this.moveRedo.getPointXredo().add(pointX);
        int pointY = this.moveUndo.getPointYundo().removeLast();
        this.moveRedo.getPointYredo().add(pointY);
        this.moveUndo.undoShapeMove();
    }

    @Override
    public void redo()
    {
        int pointX = this.moveRedo.getPointXredo().removeLast();
        this.moveUndo.getPointXundo().add(pointX);
        int pointY = this.moveRedo.getPointYredo().removeLast();
        this.moveUndo.getPointYundo().add(pointY);
        this.moveRedo.redoShapeMove();
    }
}
