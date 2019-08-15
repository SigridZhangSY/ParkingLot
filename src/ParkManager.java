public class ParkManager {
    ParkingLot parkingLot;

    public ParkManager(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        return parkingLot.park(car);
    }
}
