package h10;

import h10.abilities.Flies;
import h10.animals.Animal;
import org.tudalgo.algoutils.student.annotation.StudentImplementationRequired;

// TODO: H10.2.2
public class AirEnclosure<A extends Animal & Flies> implements Enclosure<A>{

    @StudentImplementationRequired("H10.2.2")
    // TODO: H10.2.2
    final StackOfObjects<A> animals = new StackOfObjects<>();

    @StudentImplementationRequired("H10.2.2")
    // @Override
    public StackOfObjects<A> getAnimals() {
        // TODO: H10.2.2
        return animals;
    }

    @StudentImplementationRequired("H10.2.2")
    // @Override
    public void feed() {
        // TODO: H10.2.2
        for (int i = 0; i<animals.size(); i++) {
            if (animals.get(i).isHungry()) {
                if (animals.get(i).getFeedingLocation() != animals.get(i).getCurrentLocation()) {
                    animals.get(i).flyTo(animals.get(i).getFeedingLocation());
                }
                animals.get(i).eat();
                }
            }
        }
}
