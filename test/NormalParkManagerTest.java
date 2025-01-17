import parking.exceptions.CarNotFoundException;
import parking.exceptions.NoPrkingSpaceException;
import org.junit.Test;
import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.manager.ParkManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static parking.manager.ParkingLotSelectorFactory.getNormalParkSelector;

public class NormalParkManagerTest {
    @Test
    public void should_park_a_car_when_there_is_only_one_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(10);
        ParkManager parkManager = new ParkManager(getNormalParkSelector(), parkingLot);
        Car car = new Car();

        Ticket ticket = parkManager.park(car);

        assertNotNull(ticket);

    }

    @Test(expected = NoPrkingSpaceException.class)
    public void should_not_park_a_car_when_the_only_parking_lot_is_full() {
        ParkingLot fullParkingLot = new ParkingLot(1);
        fullParkingLot.park(new Car());
        ParkManager parkManager = new ParkManager(getNormalParkSelector(), fullParkingLot);

        parkManager.park(new Car());
    }

    @Test
    public void should_park_car_in_first_lot_when_both_lots_are_not_full() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);

        ParkManager parkManager = new ParkManager(getNormalParkSelector(), firstLot, secondLot);
        Car car = new Car();
        Ticket ticket = parkManager.park(car);

        assertEquals(firstLot.pick(ticket), car);

    }

    @Test
    public void should_park_in_second_lot_when_first_lot_is_full() {
        ParkingLot firstLot = new ParkingLot(1);
        firstLot.park(new Car());
        ParkingLot secondLot = new ParkingLot(1);

        ParkManager parkManager = new ParkManager(getNormalParkSelector(), firstLot, secondLot);
        Car car = new Car();
        Ticket ticket = parkManager.park(car);

        assertEquals(secondLot.pick(ticket), car);
    }

    @Test
    public void should_manager_pick_car_when_park_car_in_first_lot() {
        ParkingLot firstLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = firstLot.park(car);
        ParkManager parkManager = new ParkManager(getNormalParkSelector(), firstLot);

        Car pickedCar = parkManager.pick(ticket);

        assertEquals(car, pickedCar);
    }

    @Test
    public void should_manager_pick_car_when_park_car_in_second_lot() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);
        firstLot.park(new Car());
        Car car = new Car();
        Ticket ticket = secondLot.park(car);
        ParkManager parkManager = new ParkManager(getNormalParkSelector(), firstLot, secondLot);

        Car pickedCar = parkManager.pick(ticket);

        assertEquals(car, pickedCar);
    }

    @Test(expected = CarNotFoundException.class)
    public void should_throw_exception_when_pick_not_parked_car() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);
        ParkManager parkManager = new ParkManager(getNormalParkSelector(), firstLot, secondLot);

        parkManager.pick(new Ticket());
    }
}
