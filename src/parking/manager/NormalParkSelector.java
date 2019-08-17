package parking.manager;

import parking.ParkingLot;

import java.util.Comparator;
import java.util.List;

public class NormalParkSelector implements ParkingLotSelector {

    public NormalParkSelector() {

    }

    public ParkingLot selectParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream()
                .min(Comparator.comparing(ParkingLot::isFull))
                .get();
    }

}
