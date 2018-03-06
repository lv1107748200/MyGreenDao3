package com.hr.mygreendao3.view;

/**
 * Created by 吕 on 2017/11/23.
 */

public class FourStyle extends BaseModel {

    @Override
    public int getBetween() {
        return 15;
    }

    @Override
    public boolean getIsOnclick() {
        return super.getIsOnclick();
    }

    @Override
    public void setLineAndHnum(int length) {
        if (length <= 4) {
            sethNum(1);
            setLineNum(4);
        } else if (length <= 8) {
            sethNum(2);
            setLineNum(4);
        } else {
            sethNum(3);
            setLineNum(4);
        }
    }
}
