import org.junit.Test;
import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.exceptions.CarNotFoundException;
import parking.exceptions.NoPrkingSpaceException;
import parking.manager.ParkManager;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static parking.manager.ParkingLotSelectorFactory.getEmptyRatePreferSelector;

public class EmptyRatePreferManagerTest {
    @Test
    public void should_park_a_car_in_the_first_lot_when_it_has_higher_empty_rate() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot(5);
        secondParkingLot.park(new Car());
        secondParkingLot.park(new Car());
        secondParkingLot.park(new Car());
        ParkManager manager = new ParkManager(getEmptyRatePreferSelector(), firstParkingLot, secondParkingLot);

        Car car = new Car();
        Ticket ticket = manager.park(car);

        assertThat(firstParkingLot.pick(ticket), is(car));

    }

    @Test
    public void should_park_a_car_in_the_second_lot_when_it_has_higher_empty_rate() {
        ParkingLot firstParkingLot = new ParkingLot(5);
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot(2);
        secondParkingLot.park(new Car());
        ParkManager manager = new ParkManager(getEmptyRatePreferSelector(), firstParkingLot, secondParkingLot);

        Car car = new Car();
        Ticket ticket = manager.park(car);

        assertThat(secondParkingLot.pick(ticket), is(car));
    }

    @Test
    public void should_park_in_first_when_two_lots_have_same_empty_rate() {
        ParkingLot firstParkingLot = new ParkingLot(2);
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot(2);
        secondParkingLot.park(new Car());
        ParkManager manager = new ParkManager(getEmptyRatePreferSelector(), firstParkingLot, secondParkingLot);

        Car car = new Car();
        Ticket ticket = manager.park(car);

        assertThat(firstParkingLot.pick(ticket), is(car));
    }

    @Test
    public void should_throw_exception_when_both_parking_lot_are_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        firstParkingLot.park(new Car());
        ParkingLot secondParkingLot = new ParkingLot(1);
        secondParkingLot.park(new Car());
        ParkManager manager = new ParkManager(getEmptyRatePreferSelector(), firstParkingLot, secondParkingLot);

        try {
            manager.park(new Car());
        }catch (Exception e) {
            if (!(e instanceof NoPrkingSpaceException)) {
                fail("No expected exception");
            } else {
                return;
            }
        }

        fail("No expected exception");
    }

    @Test
    public void should_pick_a_car_from_first_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = firstParkingLot.park(car);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkManager manager = new ParkManager(getEmptyRatePreferSelector(), firstParkingLot, secondParkingLot);

        Car pickedCar = manager.pick(ticket);

        assertThat(pickedCar, is(car));
    }

    @Test
    public void should_pick_a_car_from_second_parking_lot() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Car car = new Car();
        Ticket ticket = secondParkingLot.park(car);
        ParkManager manager = new ParkManager(getEmptyRatePreferSelector(), firstParkingLot, secondParkingLot);

        Car pickedCar = manager.pick(ticket);

        assertThat(pickedCar, is(car));
    }

    @Test
    public void should_throw_exception_when_pick_not_parked_car() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        secondParkingLot.park(new Car());
        ParkManager manager = new ParkManager(getEmptyRatePreferSelector(), firstParkingLot, secondParkingLot);
        try {
            manager.pick(new Ticket());
        }catch (Exception e) {
            if(!(e instanceof CarNotFoundException)) {
                fail("No expected exception");
            } else {
                return;
            }
        }
        fail("No expected exception");
    }
}
