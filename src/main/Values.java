package main;

import com.google.gson.annotations.SerializedName;

public class Values {
    @SerializedName("default")
    private float default_value;
    private float min;
    private float max;
    public Values(float default_value, float min, float max){
        this.default_value=default_value;
        this.min=min;
        this.max=max;
    }

    public void setDefault_value(float default_value) {
        this.default_value = default_value;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getDefault_value() {
        return default_value;
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }
}
