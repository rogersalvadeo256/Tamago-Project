package tamago;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Tamago {
	
	private double time;
	private String name;
	private int warmth, happines;

	public double getTime() {
		return time;
	}

	@XmlElement
	public void setTime(double time) {
		this.time = time;
	}

	public int getWarmth() {
		return warmth;
	}

	@XmlElement
	public void setWarmth(int warmth) {
		this.warmth = warmth;
	}

	public int getHappines() {
		return happines;
	}

	@XmlElement
	public void setHappines(int happines) {
		this.happines = happines;
	}

	public String getName() {
		return name;
	}

	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
	
	

}
