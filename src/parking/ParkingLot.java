package parking;

import parking.exceptions.CarDuplicatedException;
import parking.exceptions.CarNotFoundException;
import parking.exceptions.NoPrkingSpaceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ParkingLot {

    private int capacity;
    private Map<Ticket, Car> carMap;

    public ParkingLot(int capacity) {
        if (capacity <= 0){
            throw new IllegalArgumentException();
        }
        this.carMap = new HashMap<>();
        this.capacity = capacity;
    }


    public Ticket park(Car car) throws NoPrkingSpaceException, CarDuplicatedException{
        if (this.isFull()) {
            throw new NoPrkingSpaceException();
        }
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

    public boolean isFull() {
        return this.capacity == this.carMap.size();
    }

    public int getEmptyCount() {
        return this.capacity - this.carMap.size();
    }

    public float getEmptyRate() {
        return ((float) this.getEmptyCount()) / this.capacity;
    }
}
