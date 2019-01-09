package Main;

import Validation.NotNullOrEmpty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "uniqueGameIdentifier")
@XmlAccessorType(value = XmlAccessType.NONE)
public final class UniqueGameIdentifier {
  @XmlElement(name = "uniqueGameID", required = true)
  private final String uniqueGameID;

  public String toString() {
    return "UniqueGameIdentifier [uniqueGameID=" + this.uniqueGameID + "]";
  }

  public UniqueGameIdentifier(String uniqueGameID) {
    this.uniqueGameID = NotNullOrEmpty.check(uniqueGameID, "Game id should not be null or empty");
  }

  public static UniqueGameIdentifier of(String uniqueGameID) {
    return new UniqueGameIdentifier(uniqueGameID);
  }

  public UniqueGameIdentifier() {
    this.uniqueGameID = null;
  }

  public String getUniqueGameID() {
    return this.uniqueGameID;
  }

  public int hashCode() {
    int prime = 31;
    int result = 1;
    result = 31 * result + (this.uniqueGameID == null ? 0 : this.uniqueGameID.hashCode());
    return result;
  }

  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (this.getClass() != obj.getClass()) {
      return false;
    }
    UniqueGameIdentifier other = (UniqueGameIdentifier) obj;
    if (this.uniqueGameID == null
        ? other.uniqueGameID != null
        : !this.uniqueGameID.equals(other.uniqueGameID)) {
      return false;
    }
    return true;
  }
}
