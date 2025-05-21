import base.Device;
import command.*;
import devices.Fan;
import devices.Light;
import devices.Room;
import processing.core.PApplet;

public class Visualization extends PApplet {

    Room livingRoom;
    Room kitchen;
    Room bathroom;

    int btnWidth = 120;
    int btnHeight = 30;

    int livingOnX, livingOnY;
    int livingOffX, livingOffY;

    int kitchenOnX, kitchenOnY;
    int kitchenOffX, kitchenOffY;

    int bathroomOnX, bathroomOnY;
    int bathroomOffX, bathroomOffY;

    int livingFanOnX, livingFanOnY;
    int livingFanOffX, livingFanOffY;

    int kitchenFanOnX, kitchenFanOnY;
    int kitchenFanOffX, kitchenFanOffY;

    int moodyX, moodyY;

    Command livingTurnOnCommand;
    Command livingTurnOffCommand;
    Command kitchenTurnOnCommand;
    Command kitchenTurnOffCommand;
    Command bathroomTurnOnCommand;
    Command bathroomTurnOffCommand;
    Command livingFanOnCommand, livingFanOffCommand;
    Command kitchenFanOnCommand, kitchenFanOffCommand;
    MacroCommand moodyModeCommand;

    public static void main(String[] args) {
        PApplet.main("Visualization");
    }

    @Override
    public void settings() {
        size(900, 900);
    }

    @Override
    public void setup() {
        surface.setTitle("Room + Light + Fan Visualization");

        livingRoom = new Room("Living Room");
        kitchen = new Room("Kitchen");
        bathroom = new Room("Bathroom");

        Light livingMain = new Light("Main Light", 255, 255, 200, 80, 120);
        Light livingAccent = new Light("Accent Light", 100, 200, 255, 200, 220);
        Light kitchenLight = new Light("Kitchen Light", 255, 255, 255, 120, 120);
        Light bathroomLight = new Light("Bathroom Light", 0, 0, 255, 140, 120);

        livingRoom.addDevice(livingMain);
        livingRoom.addDevice(livingAccent);
        kitchen.addDevice(kitchenLight);
        bathroom.addDevice(bathroomLight);

        Fan livingFan = new Fan("Ceiling Fan", 200, 100);
        Fan kitchenFan = new Fan("Kitchen Fan", 180, 200);

        livingRoom.addDevice(livingFan);
        kitchen.addDevice(kitchenFan);

        livingMain.turnOn();
        kitchenLight.turnOn();
        bathroomLight.turnOn();

        noStroke();
        textAlign(CENTER, CENTER);
        textSize(16);
        smooth();

        livingOnX = 40 + 50;
        livingOnY = 50 + 320;
        livingOffX = 40 + 180;
        livingOffY = livingOnY;

        kitchenOnX = 470 + 50;
        kitchenOnY = 50 + 320;
        kitchenOffX = 470 + 180;
        kitchenOffY = kitchenOnY;

        bathroomOnX = 250 + 50;
        bathroomOnY = 450 + 250;
        bathroomOffX = 250 + 180;
        bathroomOffY = bathroomOnY;

        livingFanOnX = 40 + 50;
        livingFanOnY = 50 + 370;
        livingFanOffX = 40 + 180;
        livingFanOffY = livingFanOnY;

        kitchenFanOnX = 470 + 50;
        kitchenFanOnY = 50 + 370;
        kitchenFanOffX = 470 + 180;
        kitchenFanOffY = kitchenFanOnY;

        moodyX = 360;
        moodyY = 800;

        livingTurnOnCommand = new TurnOnAllCommand(livingRoom);
        livingTurnOffCommand = new TurnOffAllCommand(livingRoom);
        kitchenTurnOnCommand = new TurnOnAllCommand(kitchen);
        kitchenTurnOffCommand = new TurnOffAllCommand(kitchen);
        bathroomTurnOnCommand = new TurnOnAllCommand(bathroom);
        bathroomTurnOffCommand = new TurnOffAllCommand(bathroom);

        for (Device d : livingRoom.getDevices()) {
            if (d instanceof Fan) {
                livingFanOnCommand = new TurnOnCommand(d);
                livingFanOffCommand = new TurnOffCommand(d);
            }
        }

        for (Device d : kitchen.getDevices()) {
            if (d instanceof Fan) {
                kitchenFanOnCommand = new TurnOnCommand(d);
                kitchenFanOffCommand = new TurnOffCommand(d);
            }
        }

        moodyModeCommand = new MacroCommand("Moody Mode");

        for (Device d : kitchen.getDevices()) {
            moodyModeCommand.addCommand(new TurnOnCommand(d));
        }
        for (Device d : bathroom.getDevices()) {
            moodyModeCommand.addCommand(new TurnOffCommand(d));
        }

        for (Device d : livingRoom.getDevices()) {
            if (d.getName().equals("Main Light")) {
                moodyModeCommand.addCommand(new TurnOffCommand(d));
            } else if (d.getName().equals("Accent Light")) {
                moodyModeCommand.addCommand(new TurnOnCommand(d));
            } else if (d instanceof Fan) {
                moodyModeCommand.addCommand(new TurnOnCommand(d));
            }
        }

        for (Device d : kitchen.getDevices()) {
            if (d instanceof Fan) {
                moodyModeCommand.addCommand(new TurnOffCommand(d));
            }
        }
    }

    @Override
    public void draw() {
        background(50);

        drawRoomWithShadow(40, 50, 350, 350, livingRoom.getName());
        drawRoomWithShadow(470, 50, 350, 350, kitchen.getName());
        drawRoomWithShadow(250, 450, 350, 300, bathroom.getName());

        visualizeRoom(livingRoom, 40, 50);
        visualizeRoom(kitchen, 470, 50);
        visualizeRoom(bathroom, 250, 450);

        for (Room room : new Room[]{livingRoom, kitchen, bathroom}) {
            int offsetX = 40;
            int offsetY = 50;

            if (room.getName().equals("Kitchen")) {
                offsetX = 470;
                offsetY = 50;
            } else if (room.getName().equals("Bathroom")) {
                offsetX = 250;
                offsetY = 450;
            }

            for (Device device : room.getDevices()) {
                if (device instanceof Fan) {
                    visualizeFan((Fan) device, offsetX, offsetY);
                }
            }
        }

        drawButton(livingOnX, livingOnY, btnWidth, btnHeight, "Turn ON All");
        drawButton(livingOffX, livingOffY, btnWidth, btnHeight, "Turn OFF All");

        drawButton(kitchenOnX, kitchenOnY, btnWidth, btnHeight, "Turn ON All");
        drawButton(kitchenOffX, kitchenOffY, btnWidth, btnHeight, "Turn OFF All");

        drawButton(bathroomOnX, bathroomOnY, btnWidth, btnHeight, "Turn ON All");
        drawButton(bathroomOffX, bathroomOffY, btnWidth, btnHeight, "Turn OFF All");

        drawButton(livingFanOnX, livingFanOnY, btnWidth, btnHeight, "Fan ON");
        drawButton(livingFanOffX, livingFanOffY, btnWidth, btnHeight, "Fan OFF");

        drawButton(kitchenFanOnX, kitchenFanOnY, btnWidth, btnHeight, "Fan ON");
        drawButton(kitchenFanOffX, kitchenFanOffY, btnWidth, btnHeight, "Fan OFF");

        drawButton(moodyX, moodyY, btnWidth + 40, btnHeight, "Moody Mode");
    }

    private void drawRoomWithShadow(int x, int y, int w, int h, String roomName) {
        fill(0, 60);
        rect(x + 8, y + 8, w, h, 20);

        fill(220, 220, 210);
        rect(x, y, w, h, 20);

        stroke(120, 100, 80);
        strokeWeight(3);
        noFill();
        rect(x + 5, y + 5, w - 10, h - 10, 15);
        noStroke();

        if (roomName.equals("Living Room")) {
            fill(150, 170, 190);
            rect(x + 30, y + h - 80, 160, 50, 10);

            fill(120, 140, 170);
            rect(x + 30, y + h - 110, 160, 30, 5);

            fill(120, 90, 60);
            rect(x + 70, y + h - 150, 80, 50, 5);

            fill(100, 80, 60);
            rect(x + w - 100, y + h - 70, 70, 40, 5);

            fill(20, 20, 30);
            rect(x + w - 90, y + h - 120, 50, 40, 3);

        } else if (roomName.equals("Kitchen")) {
            fill(180, 180, 180);
            rect(x + 20, y + 60, w - 40, 60, 5);

            rect(x + w - 80, y + 120, 60, 180, 5);

            fill(200, 200, 200);
            ellipse(x + w/2f, y + 90, 40, 30);

            fill(50, 50, 50);
            rect(x + w - 150, y + 70, 50, 40, 5);

            fill(240, 240, 240);
            rect(x + 30, y + 150, 60, 120, 5);

        } else if (roomName.equals("Bathroom")) {
            fill(230, 230, 230);
            rect(x + 40, y + 70, 80, 50, 5);
            fill(200, 200, 200);
            ellipse(x + 80, y + 95, 40, 30);

            fill(180, 200, 220);
            rect(x + 40, y + 30, 80, 40, 3);

            fill(240, 240, 240);
            rect(x + 150, y + 50, 120, 80, 10);

            fill(250, 250, 250);
            rect(x + 60, y + 150, 40, 60, 5);
        }

        strokeWeight(1);

        fill(60, 80, 120, 200);
        rect(x + 10, y + 10, w - 20, 30, 10);

        fill(255);
        textSize(16);
        text(roomName, x + w / 2f, y + 25);
    }

    void visualizeRoom(Room room, int offsetX, int offsetY) {
        for (Device device : room.getDevices()) {
            if (device instanceof Light) {
                Light light = (Light) device;

                int x = offsetX + light.getXPosition();
                int y = offsetY + light.getYPosition();
                int r = light.getRadius();

                fill(light.getRed(), light.getGreen(), light.getBlue());
                stroke(255);
                ellipse(x, y, r, r);
                fill(255);
                textSize(12);
                text(light.getName(), x - r / 2, y - r);
            }
        }
    }

    void visualizeFan(Fan fan, int offsetX, int offsetY) {
        int x = offsetX + fan.getXPosition();
        int y = offsetY + fan.getYPosition();
        int r = fan.getRadius();


        fill(80);
        ellipse(x, y, r * 0.6f, r * 0.6f);

        pushMatrix();
        translate(x, y);
        rotate(fan.getRotation());

        fill(fan.getColor());
        stroke(60);
        strokeWeight(1);

        for (int i = 0; i < fan.getBladeCount(); i++) {
            pushMatrix();
            rotate(i * TWO_PI / fan.getBladeCount());
            ellipse(r * 0.4f, 0, r * 0.8f, r * 0.25f);
            popMatrix();
        }

        popMatrix();

        fill(200);
        noStroke();
        ellipse(x, y, r * 0.3f, r * 0.3f);

        fill(255);
        textSize(12);
        text(fan.getName(), x, y + r + 15);

        fan.updateRotation();
    }

    private void drawButton(int x, int y, int w, int h, String label) {
        fill(100);
        rect(x, y, w, h, 10);
        fill(220);
        textSize(14);
        text(label, x + w / 2f, y + h / 2f);
    }

    @Override
    public void mousePressed() {
        if (isMouseOverButton(livingOnX, livingOnY, btnWidth, btnHeight)) {
            livingTurnOnCommand.execute();
        } else if (isMouseOverButton(livingOffX, livingOffY, btnWidth, btnHeight)) {
            livingTurnOffCommand.execute();
        } else if (isMouseOverButton(kitchenOnX, kitchenOnY, btnWidth, btnHeight)) {
            kitchenTurnOnCommand.execute();
        } else if (isMouseOverButton(kitchenOffX, kitchenOffY, btnWidth, btnHeight)) {
            kitchenTurnOffCommand.execute();
        } else if (isMouseOverButton(bathroomOnX, bathroomOnY, btnWidth, btnHeight)) {
            bathroomTurnOnCommand.execute();
        } else if (isMouseOverButton(bathroomOffX, bathroomOffY, btnWidth, btnHeight)) {
            bathroomTurnOffCommand.execute();
        } else if (isMouseOverButton(livingFanOnX, livingFanOnY, btnWidth, btnHeight)) {
            livingFanOnCommand.execute();
        } else if (isMouseOverButton(livingFanOffX, livingFanOffY, btnWidth, btnHeight)) {
            livingFanOffCommand.execute();
        } else if (isMouseOverButton(kitchenFanOnX, kitchenFanOnY, btnWidth, btnHeight)) {
            kitchenFanOnCommand.execute();
        } else if (isMouseOverButton(kitchenFanOffX, kitchenFanOffY, btnWidth, btnHeight)) {
            kitchenFanOffCommand.execute();
        } else if (isMouseOverButton(moodyX, moodyY, btnWidth + 40, btnHeight)) {
            moodyModeCommand.execute();
        }
    }

    private boolean isMouseOverButton(int x, int y, int w, int h) {
        return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
    }
}