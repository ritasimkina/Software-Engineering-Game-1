package dataobjects;

public abstract class UniqueID {

	private final String id;
	
	protected UniqueID(String id)
	{
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
