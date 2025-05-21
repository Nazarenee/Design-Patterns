package devices;

import base.BaseDevice;
import processing.core.PApplet;

public class Fan extends BaseDevice {
    private int xPosition;
    private int yPosition;
    private int radius;
    private float rotation = 0;
    private boolean isSpinning = false;
    private int bladeCount = 4;
    private int color;
    private String name;

    public Fan(String name, int xPosition, int yPosition) {
        super(name);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.radius = 40;
        this.color = 0xFFAAAAAA;
    }

    @Override
    public void turnOn() {
        System.out.println(getName() + " is spinning!");
        isSpinning = true;
    }

    @Override
    public void turnOff() {
        System.out.println(getName() + " is stopping....");
        isSpinning = false;
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

    public float getRotation() {
        return rotation;
    }

    public void updateRotation() {
        if (isSpinning) {
            rotation += 0.1f;
            if (rotation > PApplet.TWO_PI) {
                rotation -= PApplet.TWO_PI;
            }
        }
    }

    public boolean isSpinning() {
        return isSpinning;
    }

    public int getBladeCount() {
        return bladeCount;
    }

    public int getColor() {
        return color;
    }
}
