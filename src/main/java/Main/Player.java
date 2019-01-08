package Main;

public class Player
{
    private String lastName;

    private String studentID;

    private String state;

    private String collectedTreasure;

    private String uniquePlayerID;

    private String firstName;

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getStudentID ()
    {
        return studentID;
    }

    public void setStudentID (String studentID)
    {
        this.studentID = studentID;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getCollectedTreasure ()
    {
        return collectedTreasure;
    }

    public void setCollectedTreasure (String collectedTreasure)
    {
        this.collectedTreasure = collectedTreasure;
    }

    public String getUniquePlayerID ()
    {
        return uniquePlayerID;
    }

    public void setUniquePlayerID (String uniquePlayerID)
    {
        this.uniquePlayerID = uniquePlayerID;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [lastName = "+lastName+", studentID = "+studentID+", state = "+state+", collectedTreasure = "+collectedTreasure+", uniquePlayerID = "+uniquePlayerID+", firstName = "+firstName+"]";
    }
}