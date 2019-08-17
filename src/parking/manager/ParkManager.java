package parking.manager;

import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.exceptions.CarNotFoundException;

import java.util.List;

import static java.util.Arrays.asList;

public abstract class ParkManager {
    List<ParkingLot> parkingLots;

    public ParkManager(ParkingLot... parkingLots) {
        this.parkingLots = asList(parkingLots);
    }

    public abstract Ticket park(Car car);

    public Car pick(Ticket ticket) {
        for (ParkingLot parkingLot : this.parkingLots) {
            try {
                Car car = parkingLot.pick(ticket);
                return car;
            }catch (CarNotFoundException ignored) {
            }
        }

        throw new CarNotFoundException();
    }
}
