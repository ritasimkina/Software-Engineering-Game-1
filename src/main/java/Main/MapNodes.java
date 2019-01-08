package Main;

public class MapNodes
{
    private MapNode[] mapNode;

    public MapNode[] getMapNode ()
    {
        return mapNode;
    }

    public void setMapNode (MapNode[] mapNode)
    {
        this.mapNode = mapNode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [mapNode = "+mapNode+"]";
    }
}
