package devices;

import base.BaseDevice;

public class Light extends BaseDevice {

    private int red;
    private int green;
    private int blue;
    private int originalRed;
    private int originalGreen;
    private int originalBlue;

    private boolean isOn = false;
    private int xPosition;
    private int yPosition;
    private int radius = 30;

    public Light(String name) {
        super(name);
    }

    public Light(String name, int r, int g, int b, int x, int y) {
        super(name);
        this.originalRed = r;
        this.originalGreen = g;
        this.originalBlue = b;
        setColor(0,0,0);
        this.xPosition = x;
        this.yPosition = y;
    }



    @Override
    public void turnOn() {
        isOn= true;
        setColor(originalRed, originalGreen, originalBlue);
        System.out.println(getName() + " light is now on!");
    }

    @Override
    public void turnOff() {
        isOn = false;
        setColor(0,0,0);
        System.out.println(getName() + " light is now off!");
    }


    public boolean isOn() {
        return isOn;
    }


    public void setColor(int r, int g, int b) {
        this.red = constrain(r, 0, 255);
        this.green = constrain(g, 0, 255);
        this.blue = constrain(b, 0, 255);

    }

    public void setPosition(int x, int y) {
        this.xPosition = x;
        this.yPosition = y;
    }



    public void setRadius(int radius) {
        this.radius = Math.max(10, radius);
    }

    public int getRed() {
        return red;
    }
    public int getGreen() {
        return green;
    }
    public int getBlue() {
        return blue ;
    }
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }
    public int getRadius() {
        return radius;
    }


    private int constrain(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    @Override
    public String toString() {
        return getName() + " [" + (isOn ? "ON" : "OFF") +
                ", RGB(" + originalRed + "," + originalGreen + "," + originalBlue + ")]";
    }
}
