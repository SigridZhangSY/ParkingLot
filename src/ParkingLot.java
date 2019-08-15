import exceptions.CarDuplicatedException;
import exceptions.CarNotFoundException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ParkingLot {

    public ParkingLot() {
        this.carMap = new HashMap<>();
    }

    private Map<Ticket, Car> carMap;


    public Ticket park(Car car) {
        Optional<Car> existedCar = carMap.values().stream().filter(car::equals).findFirst();
        if (existedCar.isPresent()) {
            throw new CarDuplicatedException();
        }
        Ticket ticket = new Ticket();
        this.carMap.put(ticket, car);
        return ticket;
    }

    public Car pick(Ticket ticket) throws CarNotFoundException {
        Car car = this.carMap.get(ticket);
        if (Objects.isNull(car)) {
            throw new CarNotFoundException();
        }
        carMap.remove(ticket);
        return car;
    }
}
