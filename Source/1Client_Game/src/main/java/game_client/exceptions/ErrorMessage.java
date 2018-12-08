package game_client.exceptions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class ErrorMessage {

    @XmlElement
    private Date date;
    @XmlElement
    private String errorDescription;

    public ErrorMessage() {
    }

    public ErrorMessage(Date date, String errorDescription) {
        this.date = date;
        this.errorDescription = errorDescription;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
