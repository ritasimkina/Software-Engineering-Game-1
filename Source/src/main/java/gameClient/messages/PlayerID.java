package dataobjects;

import messages.GameIdentifier;
import messages.PlayerIdentifier;

public class PlayerID extends UniqueID{

	public PlayerID(PlayerIdentifier gameID)
	{
		super(gameID.getPlayerID());
	}
}