package randonStuff;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GlobalStuff {

	private String method;
	private boolean firstTime;

	public String getMethod() {	return method;}

	@XmlAttribute
	public void setMethod(String method) { this.method = method;}

	public boolean isFirstTime() {return firstTime;	}

	@XmlElement
	public void setFirstTime(boolean firstTime) {this.firstTime = firstTime;}

	
	
}
