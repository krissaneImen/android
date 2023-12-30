package com.example.speciset;

public class spec {
    String name ;
    int nbr;
    public spec(String n , int nb){
        this.name=n;
        this.nbr=nb;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNbr() {
        return nbr;
    }
    public void setNbr(int nbr) {
        this.nbr = nbr;
    }
    @Override
    public String toString() {
        return name ;
    }
}
