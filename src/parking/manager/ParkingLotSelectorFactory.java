package parking.manager;

import parking.ParkingLot;

import java.util.Comparator;

public class ParkingLotSelectorFactory {
    public static ParkingLotSelector getNormalParkSelector() {
        return parkingLots -> parkingLots.stream()
                .min(Comparator.comparing(ParkingLot::isFull))
                .get();
    }

    public static ParkingLotSelector getEmptyPreferSelector() {
        return parkingLots -> parkingLots.stream().max(Comparator.comparing(ParkingLot::getEmptyCount))
                .get();
    }

    public static ParkingLotSelector getEmptyRatePreferSelector() {
        return parkingLots -> parkingLots.stream().max(Comparator.comparing(ParkingLot::getEmptyRate))
                .get();
    }
}
