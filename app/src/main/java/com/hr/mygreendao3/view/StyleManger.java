package com.hr.mygreendao3.view;

/**
 * Created by 吕 on 2017/11/23.
 */

public class StyleManger {
    public final static String THREE = "three";
    public final static String FOUR = "four";
    public static BaseModel  getBaseModel(String style){
        BaseModel baseModel = null;
        switch (style){
            case THREE:
                baseModel = new ThreeStyle();
                break;
            case FOUR:
                baseModel = new FourStyle();
                break;
        }
        return baseModel;
    }
}
