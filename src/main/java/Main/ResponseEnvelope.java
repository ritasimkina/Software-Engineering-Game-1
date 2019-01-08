package Main;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Margarita
 * Responses from Server(general)
 *
 */
@XmlRootElement (name = "ResponseEnvelope")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseEnvelope implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name="exceptionName")
	private String exceptionName;
	
	@XmlElement(name="exceptionMessage")
	private String exceptionMessage;
	
	@XmlElement(name="state")
	private String state;
	
	@XmlElement(name="data")
	private Data data;
	
	public ResponseEnvelope(String exceptionName, String exceptionMessage, String state) {
		super();
		this.exceptionName = exceptionName;
		this.exceptionMessage = exceptionMessage;
		this.state = state;
	}
	
	public boolean isStateOK() {
		return state != null && state.equals("OK");
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
	
	public Data getData ()
    {
        return data;
    }
}
