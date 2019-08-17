package parking.manager;

import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.exceptions.CarNotFoundException;
import parking.exceptions.NoPrkingSpaceException;

import java.util.List;

import static java.util.Arrays.asList;

public class ParkManager {
    private List<ParkingLot> parkingLots;
    private ParkingLotSelector parkingLotSelector;

    public ParkManager(ParkingLotSelector parkingLotSelector, ParkingLot... parkingLots) {
        this.parkingLots = asList(parkingLots);
        this.parkingLotSelector = parkingLotSelector;
    }


    public Car pick(Ticket ticket) {
        for (ParkingLot parkingLot : this.parkingLots) {
            try {
                Car car = parkingLot.pick(ticket);
                return car;
            } catch (CarNotFoundException ignored) {
            }
        }

        throw new CarNotFoundException();
    }

    public Ticket park(Car car) {
        ParkingLot selectedParkingLot = parkingLotSelector.select(this.parkingLots);
        if (selectedParkingLot.isFull()) {
            throw new NoPrkingSpaceException();
        }
        return selectedParkingLot.park(car);
    }
}
