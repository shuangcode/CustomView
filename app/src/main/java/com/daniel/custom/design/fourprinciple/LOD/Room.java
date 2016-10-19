package com.daniel.custom.design.fourprinciple.LOD;

/**
 * Created by daniel.xiao on 2016/10/19.
 */
public class Room {

    public float area;
    public float price;

    public Room(float area, float price){
        this.area = area;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room [area = "+ area +", price = "+price+"]";
    }
}
