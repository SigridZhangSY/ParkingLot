import parking.exceptions.CarNotFoundException;
import parking.exceptions.NoPrkingSpaceException;
import org.junit.Test;
import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.manager.ParkManager;
import parking.manager.ParkingLotSelectorFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static parking.manager.ParkingLotSelectorFactory.getEmptyPreferSelector;

public class EmptyPreferManagerTest {
    @Test
    public void should_park_car_in_first_lot_when_first_has_more_empty_space() {
        ParkingLot firstLot = new ParkingLot(2);
        ParkingLot secondLot = new ParkingLot(2);
        secondLot.park(new Car());
        ParkManager manager = new ParkManager(getEmptyPreferSelector(), firstLot, secondLot);
        Car car = new Car();

        Ticket ticket = manager.park(car);

        assertThat(firstLot.pick(ticket), is(car));
    }

    @Test
    public void should_park_car_in_second_when_second_has_more_empty_space() {
        ParkingLot firstLot = new ParkingLot(2);
        ParkingLot secondLot = new ParkingLot(2);
        firstLot.park(new Car());
        ParkManager manager = new ParkManager(getEmptyPreferSelector(), firstLot, secondLot);
        Car car = new Car();

        Ticket ticket = manager.park(car);

        assertThat(secondLot.pick(ticket), is(car));
    }

    @Test
    public void should_park_in_first_when_two_lots_have_same_empty_space() {
        ParkingLot firstLot = new ParkingLot(2);
        ParkingLot secondLot = new ParkingLot(2);
        ParkManager manager = new ParkManager(getEmptyPreferSelector(), firstLot, secondLot);
        Car car = new Car();

        Ticket ticket = manager.park(car);

        assertThat(firstLot.pick(ticket), is(car));
    }

    @Test(expected = NoPrkingSpaceException.class)
    public void should_throw_no_parking_space_exception_when_both_lots_have_no_parking_space() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);
        firstLot.park(new Car());
        secondLot.park(new Car());
        ParkManager manager = new ParkManager(getEmptyPreferSelector(), firstLot, secondLot);
        Car car = new Car();

        manager.park(car);
    }

    @Test
    public void should_pick_a_car_from_first_lot() {
        ParkingLot firstLot = new ParkingLot(2);
        ParkingLot secondLot = new ParkingLot(2);
        Car car = new Car();
        Ticket ticket =firstLot.park(car);
        ParkManager manager = new ParkManager(getEmptyPreferSelector(), firstLot, secondLot);

        Car picked = manager.pick(ticket);

        assertThat(picked, is(car));
    }

    @Test
    public void should_pick_a_car_from_second_lot() {
        ParkingLot firstLot = new ParkingLot(2);
        ParkingLot secondLot = new ParkingLot(2);
        Car car = new Car();
        Ticket ticket =secondLot.park(car);
        ParkManager manager = new ParkManager(getEmptyPreferSelector(), firstLot, secondLot);

        Car picked = manager.pick(ticket);

        assertThat(picked, is(car));
    }

    @Test(expected = CarNotFoundException.class)
    public void should_throw_exception_when_pick_not_parked_car() {
        ParkingLot firstLot = new ParkingLot(1);
        ParkingLot secondLot = new ParkingLot(1);
        ParkManager parkManager = new ParkManager(getEmptyPreferSelector(), firstLot, secondLot);

        parkManager.pick(new Ticket());
    }
}