package game_client.messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responseEnvelope")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseEnvelope {

    @XmlElement(name = "exceptionName")
    private String exceptionName;

    @XmlElement(name = "exceptionMessage")
    private String exceptionMessage;

    @XmlElement(name = "state")
    private String state;

    public ResponseEnvelope() {
    }

    public boolean isStateOK() {
        //return state != null && state.equals("OK");
        return state != null && state.equals("Okay");
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public void setExceptionName(String exceptionName) {
        this.exceptionName = exceptionName;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "ResponseEnvelope{" +
                "exceptionMessage='" + exceptionMessage + '\'' +
                ", exceptionName='" + exceptionName + '\'' +
                ", state=" + state +
                '}';
    }
}
