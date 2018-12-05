package messages;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement (name = "responseEnvelope")
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({ PlayerIdentifier.class})
public class ResponseEnvelope<T> {

	@XmlElement(name="exceptionMessage")
	private final String exceptionMessage;
	
	@XmlElement(name="exceptionName")
	private final String exceptionName;
	
	@XmlElement(name="state")
	private final ResponseState state;
	
	@XmlElement(name="data")
	private final T data;
	
	public ResponseEnvelope()
	{
		state = ResponseState.OK;
		this.data = null;

		exceptionMessage = "";
		exceptionName = "";
	}
	
	public ResponseEnvelope(T data)
	{
		state = ResponseState.OK;
		this.data = data;

		exceptionMessage = "";
		exceptionName = "";
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public String getExceptionName() {
		return exceptionName;
	}

	public ResponseState getState() {
		return state;
	}

	public T getData() {
		return data;
	}
}
