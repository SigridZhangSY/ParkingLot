import parking.exceptions.CarNotFoundException;
import parking.exceptions.NoPrkingSpaceException;
import org.junit.Test;
import parking.Car;
import parking.manager.NormalParkManager;
import parking.ParkingLot;
import parking.Ticket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class NormalParkManagerTest {
    @Test
    public void should_park_a_car_when_there_is_only_one_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(10);
        NormalParkManager parkManager = new NormalParkManager(parkingLot);
        Car car = new Car();

        Ticket ticket = parkManager.park(car);

        assertNotNull(ticket);

    }

    @Test(expected = NoPrkingSpaceException.class)
    public void should_not_park_a_car_when_the_only_parking_lot_is_full() {
        ParkingLot fullParkingLot = new ParkingLot(0);
        NormalParkManager parkManager = new NormalParkManager(fullParkingLot);

        parkManager.park(new Car());
    }

    @Test
    public void should_park_car_in_first_lot_when_both_lots_are_not_full() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);

        NormalParkManager parkManager = new NormalParkManager(firstLot, secondLot);
        Car car = new Car();
        Ticket ticket = parkManager.park(car);

        assertEquals(firstLot.pick(ticket), car);

    }

    @Test
    public void should_park_in_second_lot_when_first_lot_is_full() {
        ParkingLot firstLot = new ParkingLot(1);
        firstLot.park(new Car());
        ParkingLot secondLot = new ParkingLot(1);

        NormalParkManager parkManager = new NormalParkManager(firstLot, secondLot);
        Car car = new Car();
        Ticket ticket = parkManager.park(car);

        assertEquals(secondLot.pick(ticket), car);
    }

    @Test
    public void should_manager_pick_car_when_park_car_in_first_lot() {
        ParkingLot firstLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = firstLot.park(car);
        NormalParkManager parkManager = new NormalParkManager(firstLot);

        Car pickedCar = parkManager.pick(ticket);

        assertEquals(car, pickedCar);
    }

    @Test
    public void should_manager_pick_car_when_park_car_in_second_lot() {
        ParkingLot firstLot = new ParkingLot(0);
        ParkingLot secondLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = secondLot.park(car);
        NormalParkManager parkManager = new NormalParkManager(firstLot, secondLot);

        Car pickedCar = parkManager.pick(ticket);

        assertEquals(car, pickedCar);
    }

    @Test(expected = CarNotFoundException.class)
    public void should_throw_exception_when_pick_not_parked_car() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);
        NormalParkManager parkManager = new NormalParkManager(firstLot, secondLot);

        parkManager.pick(new Ticket());
    }
}
