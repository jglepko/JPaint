package model.persistence;

import model.ShapeColor;
import java.util.Map;
import java.awt.Color;
import java.util.HashMap;

public class ColorAdapter
{
    private HashMap<ShapeColor, Color> colorHashMap = new HashMap<ShapeColor, Color>();
    private ShapeColor colorEnum;

    public ColorAdapter()
    {
        this.colorEnum = colorEnum;

        colorHashMap.put(ShapeColor.BLACK, Color.BLACK);
        colorHashMap.put(ShapeColor.BLUE, Color.BLUE);
        colorHashMap.put(ShapeColor.CYAN, Color.CYAN);
        colorHashMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        colorHashMap.put(ShapeColor.GRAY, Color.GRAY);
        colorHashMap.put(ShapeColor.GREEN, Color.GREEN);
        colorHashMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        colorHashMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
        colorHashMap.put(ShapeColor.ORANGE, Color.ORANGE);
        colorHashMap.put(ShapeColor.PINK, Color.PINK);
        colorHashMap.put(ShapeColor.RED, Color.RED);
        colorHashMap.put(ShapeColor.WHITE, Color.WHITE);
    }

    public Color convert(ShapeColor key)
    {
        Color colorValue = null;

        if (colorHashMap.containsKey(key))
            colorValue = colorHashMap.get(key);

        return colorValue;
    }
}
