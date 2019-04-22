package view.gui;

import controller.CreateShape;
import controller.ICommand;
import controller.MoveShape;
import controller.SelectShape;
import model.StartAndEndPointMode;
import model.persistence.ApplicationState;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ClickHandler extends MouseInputAdapter
{
    private PaintCanvas panel;
    private ApplicationState appState;
    private Point startCoords, endCoords;
    private ICommand command;

    public ClickHandler(PaintCanvas panel, ApplicationState appState)
    {
        this.panel = panel;
        this.appState = appState;
    }

    public void mousePressed(MouseEvent e)
    {
        startCoords = new Point(e.getX(), e.getY());
        endCoords = startCoords;
    }

    public void mouseReleased(MouseEvent e)
    {
        endCoords = new Point(e.getX(), e.getY());

        if (appState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.DRAW))
        {
            command = new CreateShape(startCoords, endCoords, appState);
        }
        else if (appState.getActiveStartAndEndPointMode().equals(StartAndEndPointMode.SELECT))
        {
            command = new SelectShape(startCoords, endCoords);
        }
        else // Mode set to MOVE
        {
            command = new MoveShape(startCoords, endCoords);
        }

        command.run();

        startCoords = null;
        endCoords = null;
    }

    public void mouseDragged(MouseEvent e) {
        endCoords = new Point(e.getX(), e.getY());
    }
}