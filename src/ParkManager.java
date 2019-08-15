import exceptions.NoCapacityException;

public class ParkManager {
    ParkingLot parkingLot;

    public ParkManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        if (parkingLot.isFull()) {
            throw new NoCapacityException();
        }
        return parkingLot.park(car);
    }
}
