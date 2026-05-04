package nonFactoryPattern;

public class EndUser {
    public static void main(String[] args) {
        //Tới hãng Honda để xem và đặt xe để lái thử
        HondaHead honda = new HondaHead();
        honda.viewCar();
        honda.bookCar();
        honda.driveCar();

        FordHead ford = new FordHead();
        ford.viewCar();
        ford.bookCar();
        ford.driveCar();

        HuyndaiHead huyndai = new HuyndaiHead();
        huyndai.viewCar();
        huyndai.bookCar();
        huyndai.driveCar();
    }
}
