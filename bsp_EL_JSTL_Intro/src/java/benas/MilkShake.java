/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package benas;

import java.io.Serializable;

/**
 *
 * @author Manuel
 */
public class MilkShake implements Serializable{
    private String name;
    private int size;
    private double price;

    public MilkShake() {
    }

    public MilkShake(String name, int size, double price) {
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s [%d] costs € %1.2f", name , size,price);
    }
}
