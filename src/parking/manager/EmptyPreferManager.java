package parking.manager;

import parking.Car;
import parking.ParkingLot;
import parking.Ticket;

import java.util.Comparator;

public class EmptyPreferManager extends ParkManager {

    public EmptyPreferManager(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public Ticket park(Car car) {
        return this.parkingLots.stream().max(Comparator.comparing(ParkingLot::getEmptyCount))
                .map(parkingLot -> parkingLot.park(car))
                .get();
    }
}
