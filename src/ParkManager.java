import exceptions.NoCapacityException;

import java.util.List;

import static java.util.Arrays.asList;

public class ParkManager {
    List<ParkingLot> parkingLots;

    public ParkManager(ParkingLot... parkingLots) {
        this.parkingLots = asList(parkingLots);
    }

    public Ticket park(Car car) {
        ParkingLot parkingLot = parkingLots.get(0);

        if (parkingLot.isFull()) {
            throw new NoCapacityException();
        }
        return parkingLot.park(car);
    }
}
