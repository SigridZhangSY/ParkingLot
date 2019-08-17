package parking.manager;

import parking.ParkingLot;

import java.util.List;

@FunctionalInterface
public interface ParkingLotSelector {
    ParkingLot select(List<ParkingLot> parkingLots);
}
