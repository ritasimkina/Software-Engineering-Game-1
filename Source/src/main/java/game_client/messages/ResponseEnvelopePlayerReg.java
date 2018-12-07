package game_client.messages;

import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "responseEnvelope")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseEnvelopePlayerReg {

    @XmlElement(name = "exceptionName")
    private String exceptionName;

    @XmlElement(name = "exceptionMessage")
    private String exceptionMessage;

    @XmlElement(name = "state")
    private String state;

    @XmlElement(name = "data")
    private DataPlayerReg data;

    public ResponseEnvelopePlayerReg() {
    }

    public boolean isStateOK() {
        //return state != null && state.equals("OK");
        return state != null && state.equals("Okay");
    }

    public UUID getUniquePlayerID() {
        return data.uniquePlayerID;
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
}
