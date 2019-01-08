package Main;

import MessagesBase.HalfMap;
import MessagesBase.UniqueGameIdentifier;

public class Data
{
    private UniqueGameIdentifier gameStateId;

    private String player1;
    
    private String player2;

    private HalfMap map;

    public UniqueGameIdentifier getGameStateId ()
    {
        return gameStateId;
    }

    public void setGameStateId (UniqueGameIdentifier gameIdentifier)
    {
        this.gameStateId = gameIdentifier;
    }

    public String getPlayer1 ()
    {
        return player1;
    }

    public void setPlayer1 (String string)
    {
        this.player1 = string;
    }
    
    public String getPlayer2 ()
    {
        return player2;
    }

    public void setPlayer2 (String string)
    {
        this.player2 = string;
    }

    public HalfMap getMap ()
    {
        return map;
    }

    public void setMap (HalfMap halfMap)
    {
        this.map = halfMap;
    }

    @Override
    public String toString()
    {
        return "[gameStateId = "+gameStateId+", player1 = "+player1+", player2 = "+player2+", map = "+map+"]";
    }
}