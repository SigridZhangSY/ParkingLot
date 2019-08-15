import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertNotNull;

public class ParkManagerTest {
    @Test
    public void should_park_a_car_when_there_is_only_one_parking_lot() {
        ParkingLot parkingLot = new ParkingLot();
        ParkManager parkManager = new ParkManager(parkingLot);
        Car car = new Car();

        Ticket ticket = parkManager.park(car);

        assertNotNull(ticket);

    }
}
