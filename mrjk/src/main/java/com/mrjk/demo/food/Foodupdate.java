package com.mrjk.demo.food;

import com.google.gson.annotations.SerializedName;

public class Foodupdate {


    @SerializedName("success")
    private boolean _$Success173; // FIXME check this code
    private String message;

    public boolean is_$Success173() {
        return _$Success173;
    }

    public void set_$Success173(boolean _$Success173) {
        this._$Success173 = _$Success173;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
