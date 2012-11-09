package spacewars.gamelib;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import spacewars.gamelib.geometrics.Vector;

public class Viewport
{
    public final Rectangle viewport;
    
    public Viewport()
    {
        this.viewport = new Rectangle();
    }
    
    /**
     * Gets the position of the top left point of the viewport as a
     * <code>Vector</code> object.
     * 
     * @return origin position
     */
    public Point getOriginPosition()
    {
        return viewport.getLocation();
    }
    
    public Vector getOriginVector()
    {
        return new Vector(viewport.x, viewport.y);
    }
    
    public void setOriginPosition(int x, int y)
    {
        viewport.setLocation(x, y);
    }
    
    /**
     * Gets the position of the central point of the viewport as a
     * <code>Vector</code> object.
     * 
     * @return central position
     */
    public Point getCentralPosition()
    {
        return new Point(viewport.x + viewport.width / 2, viewport.y + viewport.height / 2);
    }
    
    public void setCentralPosition(int x, int y)
    {
        viewport.setLocation(viewport.width / 2 - x, viewport.height / 2 - y);
    }
    
    public Dimension getSize()
    {
        return viewport.getSize();
    }
    
    public void setSize(int width, int height)
    {
        viewport.setSize(new Dimension(width, height));
    }
}
