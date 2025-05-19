public class Light implements Device{
    @Override
    public void turnOn() {
        System.out.println("The light is on!");
    }

    @Override
    public void turnOff() {
        System.out.println("The light is off!");
    }
}
