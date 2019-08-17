import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

public class EmptyPreferManager {
    List<ParkingLot> parkingLots;

    public EmptyPreferManager(ParkingLot... parkingLots) {
        this.parkingLots = asList(parkingLots);
    }

    public Ticket park(Car car) {
        return this.parkingLots.stream().max(Comparator.comparing(ParkingLot::getEmptyCount))
                .map(parkingLot -> parkingLot.park(car))
                .get();
    }

    public Car pick(Ticket ticket) {
        return this.parkingLots.get(0).pick(ticket);
    }
}
