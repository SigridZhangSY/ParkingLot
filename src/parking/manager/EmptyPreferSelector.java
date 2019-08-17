package parking.manager;

import parking.ParkingLot;

import java.util.Comparator;
import java.util.List;

public class EmptyPreferSelector implements ParkingLotSelector {

    public EmptyPreferSelector() {
    }

    public ParkingLot selectParkingLot(List<ParkingLot> parkingLots) {
        return parkingLots.stream().max(Comparator.comparing(ParkingLot::getEmptyCount))
                .get();
    }
}
