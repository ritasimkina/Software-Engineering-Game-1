package Main;

public class Map
{
    private MapNodes mapNodes;

    public MapNodes getMapNodes ()
    {
        return mapNodes;
    }

    public void setMapNodes (MapNodes mapNodes)
    {
        this.mapNodes = mapNodes;
    }

    @Override
    public String toString()
    {
        return "[mapNodes = "+mapNodes+"]";
    }
}