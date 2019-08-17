import parking.exceptions.CarDuplicatedException;
import parking.exceptions.CarNotFoundException;
import org.junit.Test;
import parking.Car;
import parking.ParkingLot;
import parking.Ticket;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class ParkingLotTest {

    private ParkingLot parkingLot = new ParkingLot(5);

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

    @Test
    public void should_return_zero_when_park_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Car());

        assertEquals(parkingLot.getEmptyCount(), 0);
    }

    @Test
    public void should_return_capacity_when_there_is_no_car_in_park_lot() {
        ParkingLot parkingLot = new ParkingLot(1);

        assertEquals(parkingLot.getEmptyCount(), 1);
    }

    @Test
    public void should_return_right_number_when_check_empty_count() {
        int capacity = 5;
        ParkingLot parkingLot = new ParkingLot(capacity);
        parkingLot.park(new Car());

        assertEquals(parkingLot.getEmptyCount(), capacity - 1);
    }

    @Test
    public void should_return_empty_rate_as_one_when_there_is_no_parked_car() {
        int capacity = 10;
        ParkingLot parkingLot = new ParkingLot(capacity);

        assertEquals(0, Float.compare(parkingLot.getEmptyRate(), 1));
    }

    @Test
    public void should_return_empty_rate_as_zero_when_there_parking_lot_is_full() {
        int capacity = 10;
        ParkingLot parkingLot = new ParkingLot(capacity);
        IntStream.range(0, 10).forEach((number) -> parkingLot.park(new Car()));

        assertEquals(0, Float.compare(parkingLot.getEmptyRate(), 0));
    }

    @Test
    public void should_return_empty_rate_correctly_when_park_lot_is_neither_full_or_empty() {
        int capacity = 10;
        ParkingLot parkingLot = new ParkingLot(capacity);
        IntStream.range(0, 3).forEach((number) -> parkingLot.park(new Car()));

        float emptyRate = parkingLot.getEmptyRate();
        assertEquals(0, Float.compare(emptyRate, (float)0.7));
    }
}
