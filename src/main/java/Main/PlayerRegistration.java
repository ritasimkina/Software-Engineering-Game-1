package Main;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "playerRegistration")
@XmlAccessorType(XmlAccessType.NONE)
public final class PlayerRegistration {
  @XmlElement(name = "studentFirstName", required = true)
  @NotNull
  @Size(min = 1, max = 50)
  private final String studentFirstName;

  @XmlElement(name = "studentLastName", required = true)
  @NotNull
  @Size(min = 1, max = 50)
  private final String studentLastName;

  @XmlElement(name = "studentID", required = true)
  @NotNull
  @Size(min = 1, max = 50)
  private final String studentID;

  public PlayerRegistration() {
    studentFirstName = "";
    studentLastName = "";
    studentID = "";
  }

  public PlayerRegistration(String studentFirstName, String studentLastName, String studentID) {
    this.studentFirstName = studentFirstName;
    this.studentLastName = studentLastName;
    this.studentID = studentID;
  }

  public String getStudentFirstName() {
    return studentFirstName;
  }

  public String getStudentLastName() {
    return studentLastName;
  }

  public String getStudentID() {
    return studentID;
  }

  public boolean isRegistrationValid() {
    return studentID != null
        && studentFirstName != null
        && studentLastName != null
        && isValidName(studentFirstName)
        && isValidName(studentLastName);
  }

  private boolean isValidName(String name) {
    return name.matches(".*[a-zA-Z]+.*");
  }
}
