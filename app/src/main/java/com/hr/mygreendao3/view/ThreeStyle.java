package com.hr.mygreendao3.view;

/**
 * Created by 吕 on 2017/11/23.
 */

public class ThreeStyle extends BaseModel {

    @Override
    public int getBetween() {
        return 10;
    }

    @Override
    public int getBagNum() {
        return 9;
    }

    @Override
    public void setLineAndHnum(int length) {
        if (length <= 3) {
            sethNum(1);
            setLineNum(3);
        } else if (length <= 6) {
            sethNum(2);
            setLineNum(3);
        } else {
            sethNum(3);
            setLineNum(3);
        }
    }
}
