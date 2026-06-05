package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TamagoState {

    private int time;
    private String name;
    private int warmth;
    private int happiness;

    public TamagoState() {
    }

    public TamagoState(TamagoState source) {
        this.time = source.time;
        this.name = source.name;
        this.warmth = source.warmth;
        this.happiness = source.happiness;
    }

    public int getTime() {
        return time;
    }

    @XmlElement
    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public int getWarmth() {
        return warmth;
    }

    @XmlElement
    public void setWarmth(int warmth) {
        this.warmth = warmth;
    }

    public int getHappiness() {
        return happiness;
    }

    @XmlElement
    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }
}
