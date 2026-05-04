package factoryPattern;

public abstract class CarFactory {
    //Hàm abstract (có tên hàm/ chưa có phần thân)
    //Những class mà kế thừa nó sẽ cần implement lại
    //Khung để cho các class khác follow theo đúng structure/ business
    public abstract void viewCar();

    public abstract void driveCar();

    public abstract void bookCar();
}