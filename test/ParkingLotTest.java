import exceptions.CarDuplicatedException;
import exceptions.CarNotFoundException;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private ParkingLot parkingLot = new ParkingLot();

    @Test
    public void should_return_ticket_when_park_a_car() {
        Car car = new Car();

        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }


    @Test
    public void should_return_specified_car_when_pick_car_by_ticket() {
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);

        Car pickedCar = parkingLot.pick(ticket);

        assertEquals(car, pickedCar);
    }

    @Test(expected = CarNotFoundException.class)
    public void should_throw_car_not_found_exception_when_ticket_has_not_matched_car(){
        parkingLot.pick(new Ticket());
    }

    @Test(expected = CarDuplicatedException.class)
    public void should_throw_car_duplicate_exception_when_park_a_car_which_is_parked() {
        Car car = new Car();
        parkingLot.park(car);

        parkingLot.park(car);

    }

    @Test(expected = CarNotFoundException.class)
    public void should_throw_car_not_found_exception_when_repick_car() {
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.pick(ticket);

        parkingLot.pick(ticket);
    }

    @Test
    public void should_return_false_when_check_full_with_not_full_parking_lot() {
        ParkingLot fullParkingLot = new ParkingLot(1);

        assertFalse(fullParkingLot.isFull());
    }

    @Test
    public void should_park_lot_be_full_after_park_a_car_in_park_lot_which_capacity_is_one() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        assertTrue(parkingLot.isFull());
    }

    @Test
    public void should_park_lot_be_not_full_after_pick_a_car_from_park_lot_which_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        Ticket ticket = parkingLot.park(new Car());
        assertTrue(parkingLot.isFull());

        parkingLot.pick(ticket);

        assertFalse(parkingLot.isFull());
    }
}
