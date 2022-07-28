import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private static final int RECEIVING_TIME = 2000;
    private static final int SELL_TIME = 1000;
    private static final int CARS = 5;
    private final List<Car> cars = new ArrayList<>();

    public void receivingCar() {
        for (int i = 0; i < CARS; i++) {
            try {
                Thread.sleep(RECEIVING_TIME);
                cars.add(new Car());
                System.out.println(Thread.currentThread().getName() + " produced one car.");
                synchronized (this) {
                    notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " went to a car dealership.");
            while (cars.size() == 0) {
                System.out.println("There are no cars!");
                wait();
            }
            Thread.sleep(SELL_TIME);
            System.out.println(Thread.currentThread().getName() + " left in a brand new Opel car.");
            cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}