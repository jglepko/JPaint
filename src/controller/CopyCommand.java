package controller;

import model.ShapeType;
import view.gui.ShapeConfig;

import java.awt.*;

public class CopyCommand implements ICommand
{
    private ObserverHandler observerHandler = ObserverHandler.getInstance();
    private IShapeFactory iShapeFactory = new IShapeFactory();
    private IShapeObserver dataObj = null;

    @Override
    public void run()
    {
        ShapeConfig currentShape;

        for (IShapeObserver s : observerHandler.getSelectionList())
        {
            currentShape = s.getShapeConfig();

            ShapeConfig copy = new ShapeConfig(currentShape);

            Point newStartCoords = new Point((currentShape.getStartX() - 80),
                    currentShape.getStartY());
            Point newEndCoords = new Point((currentShape.getEndX() - 80),
                    currentShape.getEndY());

            copy.setStartCoords(newStartCoords);
            copy.setEndCoords(newEndCoords);

            // Create the if else logic for factory calls

            if (copy.getShapeType().equals(ShapeType.ELLIPSE))
            {
                dataObj = IShapeFactory.createCircle(copy);
            }
            else if (copy.getShapeType().equals(ShapeType.RECTANGLE))
            {
                dataObj = IShapeFactory.createRectangle(copy);
            }
            else // ShapeType.equals(Triangle)
            {
                dataObj = IShapeFactory.createTriangle(copy);
            }

            observerHandler.getMasterList().add(dataObj);
        }
    }
}
