package parking.manager;

import parking.ParkingLot;

import java.util.List;

public interface ParkingLotSelector {
    ParkingLot selectParkingLot(List<ParkingLot> parkingLots);
}
