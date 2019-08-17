package parking.manager;

import parking.ParkingLot;

import java.util.Comparator;

public class NormalParkManager extends ParkManager {

    public NormalParkManager(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingLot selectParkingLot() {
        return parkingLots.stream()
                .min(Comparator.comparing(ParkingLot::isFull))
                .get();
    }

}
