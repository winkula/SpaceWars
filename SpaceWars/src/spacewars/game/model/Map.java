package spacewars.game.model;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import spacewars.game.MapFactory;
import spacewars.gamelib.IRenderable;
import spacewars.gamelib.Screen;

public class Map implements IRenderable
{
    private int                 width;
    private int                 height;
    private List<MineralPlanet> mineralPlanets;
    private List<Point>         homePlanetPositions;
    private List<Star>          stars;
    
    public Map(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.mineralPlanets = new LinkedList<MineralPlanet>();
        this.homePlanetPositions = new LinkedList<Point>();
        this.stars = new LinkedList<Star>();
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public void addMineralPlanet(MineralPlanet planet)
    {
        assert planet != null;
        mineralPlanets.add(planet);
    }
    
    public void addHomePlanetPosition(Point position)
    {
        assert position != null;
        homePlanetPositions.add(position);
    }
    
    public Point getHomePlanetPosition(int index)
    {
        assert index >= 0 && index < homePlanetPositions.size();
        return homePlanetPositions.get(index);
    }
    
    public void addStar(Star star)
    {
        assert star != null;
        stars.add(star);
    }
    
    public List<MineralPlanet> getMineralPlanets()
    {
        return mineralPlanets;
    }
    
    @Override
    public void render(Graphics2D g)
    {
        // render map bounds
        g.setColor(Color.RED);
        g.drawRect(Screen.getInstance().getViewport().getOriginPosition().x, Screen.getInstance().getViewport().getOriginPosition().y, width, height);
        
        for (Star star : stars)
        {
            final int viewportx = Screen.getInstance().getViewport().getOriginPosition().x;
            final int viewporty = Screen.getInstance().getViewport().getOriginPosition().y;
            final int screenw = Screen.getInstance().getSize().width;
            final int screenh = Screen.getInstance().getSize().height;
            
            final int DEEP_DELTA = 2;
            final int DEEP_FACTOR = 1;
            final double FACTOR = 0.5;
            final int SIZE = (int) ((MapFactory.NUMBER_OF_LAYERS - star.getLayer()) * FACTOR);
            
            int x = (viewportx / (1 + DEEP_DELTA + star.getLayer() * DEEP_FACTOR) + star.getPosititon().x - SIZE / 2) % screenw;
            int y = (viewporty / (1 + DEEP_DELTA + star.getLayer() * DEEP_FACTOR) + star.getPosititon().y - SIZE / 2) % screenh;
            if (x < 0) x += screenw;
            if (y < 0) y += screenh;
            
            Composite original = g.getComposite();
            g.setColor(Color.WHITE);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            g.fillOval(x, y, SIZE, SIZE);
            g.setComposite(original);
        }
        
        for (MineralPlanet planet : mineralPlanets)
        {
            planet.render(g);
        }
        
        for (Point position : homePlanetPositions)
        {
            final int SIZE = 26;
            final int x = Screen.getInstance().getViewport().getOriginPosition().x + position.x - SIZE / 2;
            final int y = Screen.getInstance().getViewport().getOriginPosition().y + position.y - SIZE / 2;
            
            Color color = Color.BLUE;
            g.setColor(color);
            g.fillOval(x, y, SIZE, SIZE);
        }
    }
}