import exceptions.NoCapacityException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ParkManagerTest {
    @Test
    public void should_park_a_car_when_there_is_only_one_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(10);
        ParkManager parkManager = new ParkManager(parkingLot);
        Car car = new Car();

        Ticket ticket = parkManager.park(car);

        assertNotNull(ticket);

    }

    @Test(expected = NoCapacityException.class)
    public void should_not_park_a_car_when_the_only_parking_lot_is_full() {
        ParkingLot fullParkingLot = new ParkingLot(0);
        ParkManager parkManager = new ParkManager(fullParkingLot);

        parkManager.park(new Car());
    }
}
