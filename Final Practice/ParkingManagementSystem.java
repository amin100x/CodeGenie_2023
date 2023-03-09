import java.text.ParseException;
import java.util.*;

class Car {
    String CarNum;
    String CheckInTime;
    String CheckOutTime;
    String Category;
    int Charge;
    int ParkingSlotNo;
    int ParkingFlor;

    Car(String CarNo, String checkInTime, String Cate) {
        this.CarNum = CarNo;
        this.CheckInTime = checkInTime;
        this.Category = Cate;
    }
}

public class ParkingManagementSystem {

    int NumOfFloor;
    int NumOfSlotInEachFloors;
    Map<String, Boolean> ReservedSlotsForSeniorCiti;
    Set<String> ReservedSlots;

    List<Car> Cars;
    // for keep record of all the cars for generate REPORT
    List<Car> AllCars;

    ParkingManagementSystem(int NumOfFloor, int NumOfSlotInEachFloors, String ReservedForSenior) {
        this.NumOfFloor = NumOfFloor;
        this.NumOfSlotInEachFloors = NumOfSlotInEachFloors;
        this.ReservedSlotsForSeniorCiti = new HashMap<>();
        for (String str : ReservedForSenior.split(" ")) {
            ReservedSlotsForSeniorCiti.put(str, true);
        }
        this.ReservedSlots = new HashSet<>();
        this.Cars = new ArrayList<>();
        this.AllCars = new ArrayList<>();
    }

    public String CheckIn(String CarNo, String CheckInTime, String Category) {

        for (int f = 0; f < NumOfFloor; f++) {
            for (int s = 1; s <= NumOfSlotInEachFloors; s++) {
                String ThisSlot = (char) ('A' + f) + "-" + s;
                if (Category.equals("R")) {
                    if (!ReservedSlotsForSeniorCiti.isEmpty() && ReservedSlotsForSeniorCiti.containsKey(ThisSlot)
                            && ReservedSlotsForSeniorCiti.get(ThisSlot)) {
                        Car car = new Car(CarNo, CheckInTime, Category);
                        car.ParkingSlotNo = s;
                        car.ParkingFlor = f;
                        ReservedSlotsForSeniorCiti.put(ThisSlot, false);
                        Cars.add(car);
                        // AllCars.add(car);
                        return ThisSlot;
                    }

                } else if (Category.equals("NR")) {
                    if (!ReservedSlotsForSeniorCiti.containsKey(ThisSlot) && !ReservedSlots.contains(ThisSlot)) {
                        Car car = new Car(CarNo, CheckInTime, Category);
                        car.ParkingSlotNo = s;
                        car.ParkingFlor = f;
                        ReservedSlots.add(ThisSlot);
                        Cars.add(car);
                        // AllCars.add(car);
                        return ThisSlot;
                    }
                }
            }
        }
        return "PARKING FULL";
    }

    public void CheckOut(String CarNo, String CheckOutTime) {
        String cOut = "";
        if (CheckOutTime.substring(5).equals("pm")) {
            int hour = Integer.parseInt(CheckOutTime.substring(0, 2)) + 12;
            cOut = hour + CheckOutTime.substring(3, 5);
        } else {
            cOut += CheckOutTime.substring(0, 5);
        }
        for (Car car : Cars) {
            if (car.CarNum.equals(CarNo)) {
                String Cin = "";
                if (car.CheckInTime.substring(5).equals("pm")) {
                    int hour = Integer.parseInt(car.CheckInTime.substring(0, 2)) + 12;
                    Cin = hour + car.CheckInTime.substring(3, 5);
                } else {
                    Cin += car.CheckInTime.substring(0, 5);
                }

                int durationInHour = (Integer.parseInt(CheckOutTime.substring(0, 2))
                        - Integer.parseInt(car.CheckInTime.substring(0, 2)))
                        + (Integer.parseInt(CheckOutTime.substring(3, 5))
                                - Integer.parseInt(car.CheckInTime.substring(3, 5))) / 60;

                car.Charge = CalculateCharge((int) durationInHour);
                car.CheckOutTime = CheckOutTime;
                AllCars.add(car);

                String ThisSlot = (char) ('A' + car.ParkingFlor) + "-" + car.ParkingSlotNo;
                if (!ReservedSlots.isEmpty() && ReservedSlots.contains(ThisSlot)) {
                    ReservedSlots.remove(ThisSlot);
                } else if (!ReservedSlotsForSeniorCiti.isEmpty() && !ReservedSlotsForSeniorCiti.get(ThisSlot)) {
                    ReservedSlotsForSeniorCiti.put(ThisSlot, true);
                }
                Cars.remove(car);
                return;
            }
        }
        return;
    }

    public int CalculateCharge(int h) {
        if (h <= 2)
            return 50;
        else if (h >= 2 && h <= 4)
            return 80;
        else
            return 100;
    }

    void GenerateReport() {

        AllCars.sort(Comparator.comparing((Car car) -> car.ParkingFlor).thenComparing((Car car) -> car.ParkingSlotNo)
                .thenComparing((Car car) -> car.CheckInTime));
        System.out.println("PARKING SLOT, CAR NO, CHECK IN TIME, CHECK OUT TIME, CHARGES, CATEGORY");

        for (Car c : AllCars) {
            String slot = (char) ('A' + c.ParkingFlor) + "-" + c.ParkingSlotNo;
            System.out.println(slot + " , " + c.CarNum + " , " + c.CheckInTime + " , " + c.CheckOutTime + " , "
                    + c.Charge + " , " + c.Category);
        }
    }

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        ParkingManagementSystem pr = new ParkingManagementSystem(5, 10, "A-1 A-10 B-2 B-5 C-1 C-8 D-2 D-4 E-5 E-10");

        while (true) {
            // System.out.print("Enter Car Details:- ");
            String inputCar = sc.nextLine();
            String[] arr = inputCar.split(" ");

            if (arr.length == 4) {
                System.out.println(pr.CheckIn(arr[1], arr[2], arr[3]));
            } else if (arr.length == 3) {
                pr.CheckOut(arr[1], arr[2]);
            } else if (inputCar.equals("GENERATE REPORT")) {
                pr.GenerateReport();
            } else {
                break;
            }

        }
    }
}