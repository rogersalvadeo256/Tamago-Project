package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GlobalSettings {

    private boolean tamagoMode = true;
    private boolean firstTime = true;

    public boolean isTamagoMode() {
        return tamagoMode;
    }

    @XmlAttribute(name = "method")
    public void setTamagoMode(boolean tamagoMode) {
        this.tamagoMode = tamagoMode;
    }

    public boolean isFirstTime() {
        return firstTime;
    }

    @XmlElement
    public void setFirstTime(boolean firstTime) {
        this.firstTime = firstTime;
    }
}
