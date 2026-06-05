package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MonsterState {

    private String name;
    private int hp;
    private int food;
    private int water;
    private int bath;
    private int age;
    private int weight;
    private int discipline;
    private int poop;
    private String type;
    private String species;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    @XmlElement
    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getFood() {
        return food;
    }

    @XmlElement
    public void setFood(int food) {
        this.food = food;
    }

    public int getWater() {
        return water;
    }

    @XmlElement
    public void setWater(int water) {
        this.water = water;
    }

    public int getBath() {
        return bath;
    }

    @XmlElement
    public void setBath(int bath) {
        this.bath = bath;
    }

    public int getAge() {
        return age;
    }

    @XmlElement
    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    @XmlElement
    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDiscipline() {
        return discipline;
    }

    @XmlElement
    public void setDiscipline(int discipline) {
        this.discipline = discipline;
    }

    public int getPoop() {
        return poop;
    }

    @XmlElement
    public void setPoop(int poop) {
        this.poop = poop;
    }

    public String getType() {
        return type;
    }

    @XmlElement
    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    @XmlAttribute
    public void setSpecies(String species) {
        this.species = species;
    }
}
