package com.smartconstruction.smartconstruction.api.dtos.request;

//import javax.persistence.*;
//
//@Entity
//@Table(name = "mixtures")
public class ConcreteStrengthRequest {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public static final  String [] featureNames = {"Cement (component 1)(kg in a m^3 mixture)","Stone Pebbles-Kokoto (component 2)(kg in a m^3 mixture)","Quarry Dust(component 3)(kg in a m^3 mixture)","Water  (component 4)(kg in a m^3 mixture)","Superplasticizer (component 5)(kg in a m^3 mixture)","Coarse Sand (component 6)(kg in a m^3 mixture)","Fine Sand (component 7)(kg in a m^3 mixture)","Age (day)","Concrete compressive strength(MPa, megapascals) "};


    private double cement;
    private double stonePebbles;
    private double quaryDust;
    private double water;
    private double superplasticizer;
    private double coarsesand;
    private double finesand;
    private double age;
    private double strength;

    public ConcreteStrengthRequest() {
    }

    public double[] getFeatureValues(){
        return new double[] {cement, stonePebbles,quaryDust, water, superplasticizer, coarsesand,finesand, age, strength
        };
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCement(double cement) {
        this.cement = cement;
    }

    public void setStonePebbles(double stonePebbles) {
        this.stonePebbles = stonePebbles;
    }

    public void setQuaryDust(double quaryDust) {
        this.quaryDust = quaryDust;
    }

    public void setWater(double water) {
        this.water = water;
    }

    public void setSuperplasticizer(double superplasticizer) {
        this.superplasticizer = superplasticizer;
    }

    public void setCoarsesand(double coarsesand) {
        this.coarsesand = coarsesand;
    }

    public void setFinesand(double finesand) {
        this.finesand = finesand;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public double getCement() {
        return cement;
    }

    public double getStonePebbles() {
        return stonePebbles;
    }

    public double getQuaryDust() {
        return quaryDust;
    }

    public double getWater() {
        return water;
    }

    public double getSuperplasticizer() {
        return superplasticizer;
    }

    public double getCoarsesand() {
        return coarsesand;
    }

    public double getFinesand() {
        return finesand;
    }

    public double getAge() {
        return age;
    }

    public double getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return "ConcreteStrengthRequest [cement = "+cement + ",stonePebbles = "+stonePebbles + ", quaryDust = "+ quaryDust
                + ", water = "+water + ", superplasticizer = "+superplasticizer+", coarsesand = "+coarsesand +",finesand = "+finesand+
                ", age="+age +", strength ="+strength +"]";
    }
}
