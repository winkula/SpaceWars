package spacewars.game.model.planets;

import spacewars.game.model.GameElement;
import spacewars.gamelib.geometrics.Vector;

@SuppressWarnings("serial")
public abstract class Planet extends GameElement
{
    public Planet(Vector position, int sizeRadius, int viewRadius)
    {
        super(position, sizeRadius, viewRadius);
    }
}
