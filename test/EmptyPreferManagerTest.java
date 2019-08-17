import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EmptyPreferManagerTest {
    @Test
    public void should_park_car_in_first_lot_when_first_has_mor_empty_space() {
        ParkingLot firstLot = new ParkingLot(2);
        ParkingLot secondLot = new ParkingLot(2);
        secondLot.park(new Car());
        EmptyPreferManager manager = new EmptyPreferManager(firstLot, secondLot);
        Car car = new Car();

        Ticket ticket = manager.park(car);

        assertThat(firstLot.pick(ticket), is(car));
    }
}