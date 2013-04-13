package de.podolak.agenten.environment;

import de.podolak.agenten.utilities.Utilities;
import java.awt.Point;
import java.util.HashMap;

/**
 * The play field.
 * 
 * @version $version$
 * @author $author$
 */
public class Area {

    private Environment environment;
    private Field[][] fields;
    private HashMap<Field, Point> coordinatesByFields;
    private int dimensionX;
    private int dimensionY;

    /**
     * Creates a minimum area of size 1x1 without environment.
     */
    public Area() {
        this(null, 1, 1);
    }

    public Area(Environment environment, int dimensionX, int dimensionY) {
        this.environment = environment;
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;

        fields = new Field[dimensionX][dimensionY];
        coordinatesByFields = new HashMap<Field, Point>(dimensionX * dimensionY);

        for (int x = 0; x < dimensionX; x++) {
            for (int y = 0; y < dimensionY; y++) {
                fields[x][y] = new Field(environment);
                coordinatesByFields.put(fields[x][y], new Point(x, y));
            }
        }
    }

    public Field getField(int x, int y) {
        return fields[x][y];
    }

    public Point getLocation(Field field) {
        return coordinatesByFields.get(field);
    }

    public int getX(Field field) {
        int x = -1;

        if (coordinatesByFields.containsKey(field)) {
            x = coordinatesByFields.get(field).x;
        }

        return x;
    }

    public int getY(Field field) {
        int y = -1;

        if (coordinatesByFields.containsKey(field)) {
            y = coordinatesByFields.get(field).y;
        }

        return y;
    }

    @Override
    public String toString() {
        String indent = Utilities.getStringNTimes(" ", getClass().getSimpleName().length() + 2);
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName() + "=[");

        for (int yCounter = 0; yCounter < dimensionY; yCounter++) {
            if (yCounter > 0) {
                stringBuilder.append(indent);
            }

            stringBuilder.append("[");

            for (int xCounter = 0; xCounter < dimensionX; xCounter++) {
                stringBuilder.append("\"" + fields[xCounter][yCounter].getName() + "\",");
            }

            if (yCounter < dimensionY - 1) {
                stringBuilder.append("],\n");
            } else {
                stringBuilder.append("]");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public Field getField(Field oldField, int xOffset, int yOffset) {
        Point p = coordinatesByFields.get(oldField);

        int x = p.x + xOffset;
        int y = p.y + yOffset;

        if (x >= dimensionX) {
            x = x % dimensionX;
        }

        if (y >= dimensionY) {
            y = y % dimensionY;
        }

if (x < 0 || y < 0) {
System.out.println("x: " + x + ", y: " + y);
}

        return getField(x, y);
    }
}
