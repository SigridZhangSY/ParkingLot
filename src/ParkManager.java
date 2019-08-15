import exceptions.CarNotFoundException;
import exceptions.NoPrkingSpaceException;

import java.util.List;

import static java.util.Arrays.asList;

public class ParkManager {
    List<ParkingLot> parkingLots;

    public ParkManager(ParkingLot... parkingLots) {
        this.parkingLots = asList(parkingLots);
    }

    public Ticket park(Car car) {
        ParkingLot chosenParkingLot = parkingLots.stream()
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(NoPrkingSpaceException::new);
        return chosenParkingLot.park(car);
    }

    public Car pick(Ticket ticket) {
        for (ParkingLot parkingLot : this.parkingLots) {
            try {
                Car car = parkingLot.pick(ticket);
                return car;
            }catch (CarNotFoundException ignored) {
            }
        }

        throw new CarNotFoundException();
    }
}
