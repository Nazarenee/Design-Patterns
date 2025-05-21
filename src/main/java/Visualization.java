import processing.core.PApplet;

public class Visualization extends PApplet {

    Room livingRoom;
    Room kitchen;

    // Buttons info (position and size)
    int btnWidth = 120;
    int btnHeight = 30;

    // Button positions for Living Room
    int livingOnX, livingOnY;
    int livingOffX, livingOffY;

    // Button positions for Kitchen
    int kitchenOnX, kitchenOnY;
    int kitchenOffX, kitchenOffY;

    // Commands
    Command livingTurnOnCommand;
    Command livingTurnOffCommand;
    Command kitchenTurnOnCommand;
    Command kitchenTurnOffCommand;

    public static void main(String[] args) {
        PApplet.main("Visualization");
    }

    @Override
    public void settings() {
        size(900, 500);
    }

    @Override
    public void setup() {
        surface.setTitle("Room + Light Visualization");

        livingRoom = new Room("Living Room");
        kitchen = new Room("Kitchen");

        Light livingMain = new Light("Main Light", 255, 255, 200, 80, 120);
        Light livingAccent = new Light("Accent Light", 100, 200, 255, 200, 170);
        Light kitchenLight = new Light("Kitchen Light", 255, 255, 255, 120, 120);

        livingRoom.addDevice(livingMain);
        livingRoom.addDevice(livingAccent);
        kitchen.addDevice(kitchenLight);

        livingMain.turnOn();
        kitchenLight.turnOn();

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

        livingTurnOnCommand = new TurnOnAllCommand(livingRoom);
        livingTurnOffCommand = new TurnOffAllCommand(livingRoom);
        kitchenTurnOnCommand = new TurnOnAllCommand(kitchen);
        kitchenTurnOffCommand = new TurnOffAllCommand(kitchen);
    }

    @Override
    public void draw() {
        background(50);

        drawRoomWithShadow(40, 50, 350, 350, livingRoom.getName());
        drawRoomWithShadow(470, 50, 350, 350, kitchen.getName());

        visualizeRoom(livingRoom, 40, 50);
        visualizeRoom(kitchen, 470, 50);

        drawButton(livingOnX, livingOnY, btnWidth, btnHeight, "Turn ON All");
        drawButton(livingOffX, livingOffY, btnWidth, btnHeight, "Turn OFF All");
        drawButton(kitchenOnX, kitchenOnY, btnWidth, btnHeight, "Turn ON All");
        drawButton(kitchenOffX, kitchenOffY, btnWidth, btnHeight, "Turn OFF All");
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
                int red = light.getRed();
                int green = light.getGreen();
                int blue = light.getBlue();

                fill(red, green, blue);
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
        }
    }

    private boolean isMouseOverButton(int x, int y, int w, int h) {
        return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
    }
}
