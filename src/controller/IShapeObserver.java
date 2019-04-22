package controller;

import view.gui.ShapeConfig;

public interface IShapeObserver
{
    void update();
    ShapeConfig getShapeConfig();
}
