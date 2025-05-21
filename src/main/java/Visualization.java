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

    int moodyX, moodyY;

    Command livingTurnOnCommand;
    Command livingTurnOffCommand;
    Command kitchenTurnOnCommand;
    Command kitchenTurnOffCommand;
    Command bathroomTurnOnCommand;
    Command bathroomTurnOffCommand;
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
        surface.setTitle("Room + Light Visualization");

        livingRoom = new Room("Living Room");
        kitchen = new Room("Kitchen");
        bathroom = new Room("Bathroom");

        Light livingMain = new Light("Main Light", 255, 255, 200, 80, 120);
        Light livingAccent = new Light("Accent Light", 100, 200, 255, 200, 170);
        Light kitchenLight = new Light("Kitchen Light", 255, 255, 255, 120, 120);
        Light bathroomLight = new Light("Bathroom Light", 0, 0, 255, 140, 120);

        livingRoom.addDevice(livingMain);
        livingRoom.addDevice(livingAccent);
        kitchen.addDevice(kitchenLight);
        bathroom.addDevice(bathroomLight);

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

        moodyX = 360;
        moodyY = 800;

        livingTurnOnCommand = new TurnOnAllCommand(livingRoom);
        livingTurnOffCommand = new TurnOffAllCommand(livingRoom);
        kitchenTurnOnCommand = new TurnOnAllCommand(kitchen);
        kitchenTurnOffCommand = new TurnOffAllCommand(kitchen);
        bathroomTurnOnCommand = new TurnOnAllCommand(bathroom);
        bathroomTurnOffCommand = new TurnOffAllCommand(bathroom);

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

        drawButton(livingOnX, livingOnY, btnWidth, btnHeight, "Turn ON All");
        drawButton(livingOffX, livingOffY, btnWidth, btnHeight, "Turn OFF All");

        drawButton(kitchenOnX, kitchenOnY, btnWidth, btnHeight, "Turn ON All");
        drawButton(kitchenOffX, kitchenOffY, btnWidth, btnHeight, "Turn OFF All");

        drawButton(bathroomOnX, bathroomOnY, btnWidth, btnHeight, "Turn ON All");
        drawButton(bathroomOffX, bathroomOffY, btnWidth, btnHeight, "Turn OFF All");

        drawButton(moodyX, moodyY, btnWidth + 40, btnHeight, "Moody Mode");
    }

    private void drawRoomWithShadow(int x, int y, int w, int h, String roomName) {
        fill(0, 60);
        rect(x + 8, y + 8, w, h, 20);

        fill(180);
        rect(x, y, w, h, 20);

        fill(20);
        text(roomName, x + w / 2f, y + 20);
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
        } else if (isMouseOverButton(moodyX, moodyY, btnWidth + 40, btnHeight)) {
            moodyModeCommand.execute();
        }
    }

    private boolean isMouseOverButton(int x, int y, int w, int h) {
        return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
    }
}
