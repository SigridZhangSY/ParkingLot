package parking.manager;

import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.exceptions.CarNotFoundException;
import parking.exceptions.NoPrkingSpaceException;

import java.util.List;

import static java.util.Arrays.asList;

public abstract class ParkManager {
    List<ParkingLot> parkingLots;

    public ParkManager(ParkingLot... parkingLots) {
        this.parkingLots = asList(parkingLots);
    }


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

    public Ticket park(Car car) {
        ParkingLot selectedParkingLot = selectParkingLot();
        if(selectedParkingLot.isFull()) {
            throw new NoPrkingSpaceException();
        }
        return selectedParkingLot.park(car);
    }

    protected abstract ParkingLot selectParkingLot();
}
