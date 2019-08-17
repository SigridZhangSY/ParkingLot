package parking.manager;

import parking.ParkingLot;

import java.util.Comparator;

public class EmptyPreferManager extends ParkManager {

    public EmptyPreferManager(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    protected ParkingLot selectParkingLot() {
        return this.parkingLots.stream().max(Comparator.comparing(ParkingLot::getEmptyCount))
                .get();
    }
}
