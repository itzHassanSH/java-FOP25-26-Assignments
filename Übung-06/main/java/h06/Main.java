package h06;

import org.tudalgo.algoutils.student.annotation.DoNotTouch;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        // TODO H6.6 Spielwiese
        FuelPoweredCar car = new FuelPoweredCar(FuelType.GASOLINE, 0);
        car.fillUp(50);
        System.out.println(car.getFillingLevel());
        car.turnOn();
        System.out.println(car.letMeMove(100 * (int) Math.pow(10, 3))); // check if casting makes a difference

        DriveableLawnMower lawnMower = new DriveableLawnMower(FuelType.DIESEL, 5);
        lawnMower.spinBlade();
        System.out.println(lawnMower.isBladeSpinning());
        System.out.println(lawnMower.letMeMove(500));
        System.out.println("Old: 5l, New: " + lawnMower.getFillingLevel());
        lawnMower.turnOn();
        System.out.println(lawnMower.letMeMove(20 * (int) Math.pow(10, 3)));
        System.out.println("New Filling level: " + lawnMower.getFillingLevel());

        System.out.println("Tax should be: 6.88, It has been calculated to: " + TaxCalculator.calculateTax(car, 20));
        System.out.println("Tax should be: 0.96, It has been calculated to: " + TaxCalculator.calculateTax(lawnMower, 3));

        lawnMower = new HybridLawnMower(FuelType.DIESEL, 6);
        System.out.println("Tax should be: 0.864, It has been calculated to: " + TaxCalculator.calculateTax(lawnMower, 3));

        Chainsaw chainsaw = new Chainsaw();
        while (!chainsaw.isMotorRunning()) {
            chainsaw.attemptStart();
        }
        chainsaw.addWood(new Wood(2.0, 5.0));
        chainsaw.addWood(new Wood(2.0, 5.0));
        chainsaw.sawWood(10);
        System.out.println("Number of wood should've decreased from 2 to 1 - value: " + chainsaw.getRemainingWood());

        Smartphone phone = new Smartphone(exampleTokenDirectory(), exampleMessageReceiver());
        phone.use(6);

    }

    /**
     * @return an example TokenDirectory that maps the placeholders used in {@link Main#exampleMessageReceiver}
     * to example values
     */
    @DoNotTouch
    private static TokenDictionary exampleTokenDirectory() {
        TokenDictionary dictionary = new TokenDictionary();
        dictionary.putToken("name", "Max Mustermann");
        dictionary.putToken("date", "26/06/2025");
        dictionary.putToken("animal", "Hund");
        dictionary.putToken("university", "TU Darmstadt");

        return dictionary;
    }

    /**
     * @return an example MessageReceiver that provides messages containing common placeholders such as name and date
     */
    @DoNotTouch
    private static MessageReceiver exampleMessageReceiver() {
        MessageReceiver receiver = new MessageReceiver();
        receiver.addMessage("Hello <name>, how are you doing");
        receiver.addMessage("<date> was a wild day");
        receiver.addMessage("Ich habe das an der <university> gelernt");

        return receiver;
    }
}
