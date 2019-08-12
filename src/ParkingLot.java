import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    public ParkingLot() {
        this.carMap = new HashMap<>();
    }

    private Map<Ticket, Car> carMap;


    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        this.carMap.put(ticket, car);
        return ticket;
    }

    public Car pick(Ticket ticket) {
        return this.carMap.get(ticket);
    }
}
