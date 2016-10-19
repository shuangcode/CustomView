package com.daniel.custom.design.fourprinciple.LOD;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daniel.xiao on 2016/10/19.
 * 中介
 */
public class Mediator {

    List<Room> mRooms = new ArrayList<>();

    public Mediator(){
        for (int i =0; i< 5; i++){
            mRooms.add(new Room(14+1, (14+1)*150));
        }
    }

    public Room rentOut( float price ,float area){
        for(Room room : mRooms){
            if(isSuitable(price, area, room)){
                return room;
            }
        }
        return null;
    }

    public List<Room> getAllRooms(){
        return mRooms;
    }

    private boolean isSuitable(float roomPrice, float roomArea, Room room) {
        return Math.abs(room.price - roomPrice) < Tenant.diffPrice && Math.abs(room.area - roomArea) < Tenant.diffArea ;
    }
}
