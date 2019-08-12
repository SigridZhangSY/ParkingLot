import exceptions.CarDuplicatedException;
import exceptions.CarNotFoundException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParkingLotTest {

    @Test
    public void should_return_ticket_when_park_a_car() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();

        Ticket ticket = parkingLot.park(car);

        assertNotNull(ticket);
    }


    @Test
    public void should_return_specified_car_when_pick_car_by_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);

        Car pickedCar = parkingLot.pick(ticket);

        assertEquals(car, pickedCar);
    }

    @Test(expected = CarNotFoundException.class)
    public void should_throw_car_not_found_exception_when_ticket_has_not_matched_car(){
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.pick(new Ticket());
    }

    @Test(expected = CarDuplicatedException.class)
    public void should_throw_car_duplicate_exception_when_park_a_car_which_is_parked() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        parkingLot.park(car);

        parkingLot.park(car);

    }
}
