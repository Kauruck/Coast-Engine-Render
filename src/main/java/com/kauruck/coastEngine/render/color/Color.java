package com.kauruck.coastEngine.render.color;

public class Color {

    private float red;
    private float green;
    private float blue;
    private float alpha;

    public Color(float red, float green, float blue, float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float[] forGL(int size){
        float[] out = new float[size * 4];
        for(int i = 0; i < size * 4; i += 4){
            out[i] = red;
            out[i + 1] = green;
            out[i + 2] = blue;
            out[i + 3] = alpha;
        }

        return out;
    }
}
