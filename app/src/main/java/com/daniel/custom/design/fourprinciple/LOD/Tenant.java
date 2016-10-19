package com.daniel.custom.design.fourprinciple.LOD;

import com.daniel.custom.utils.CLog;

/**
 * Created by daniel.xiao on 2016/10/19.
 * 租户
 */
public class Tenant {

    private static final String TAG = "Tenant";
    public float roomArea;
    public float roomPrice;
    public static final float diffPrice = 100.0001f;
    public static final float diffArea = 0.00001f;

    public void rentRoom(Mediator mediator){
        CLog.d(TAG, "租到房了" + mediator.rentOut(roomPrice, roomArea));
    }


}
