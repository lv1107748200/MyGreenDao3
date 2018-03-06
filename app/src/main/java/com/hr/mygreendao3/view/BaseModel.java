package com.hr.mygreendao3.view;

/**
 * Created by 吕 on 2017/11/23.
 */

public abstract class BaseModel{

    private int hNum;
    private int lineNum;

    public void sethNum(int hNum) {
        this.hNum = hNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    public int getBetween() {
        return 10;
    }

    public int getBagNum(){
        return 12;
    }

    public int getHNum(int length) {
        setLineAndHnum(length);
        return hNum;
    }


    public int getLineNum(int length) {
        setLineAndHnum(length);
        return lineNum;
    }

    public boolean getIsOnclick() {
        return true;
    }
    //几行几列
    public void setLineAndHnum(int length){
        if (length <= 4) {
            hNum = 1;
            lineNum = 4;
        } else if (length <= 8) {
            hNum = 2;
            lineNum = 4;
        } else {
            hNum = 3;
            lineNum = 4;
        }
    }

}
