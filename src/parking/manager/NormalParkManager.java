package parking.manager;

import parking.Car;
import parking.ParkingLot;
import parking.Ticket;
import parking.exceptions.NoPrkingSpaceException;

public class NormalParkManager extends ParkManager {

    public NormalParkManager(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        ParkingLot chosenParkingLot = parkingLots.stream()
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(NoPrkingSpaceException::new);
        return chosenParkingLot.park(car);
    }

}
