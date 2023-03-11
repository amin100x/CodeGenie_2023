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
                        return ThisSlot;
                    }

                } else if (Category.equals("NR")) {
                    if (!ReservedSlotsForSeniorCiti.containsKey(ThisSlot) && !ReservedSlots.contains(ThisSlot)) {
                        Car car = new Car(CarNo, CheckInTime, Category);
                        car.ParkingSlotNo = s;
                        car.ParkingFlor = f;
                        ReservedSlots.add(ThisSlot);
                        Cars.add(car);
                        return ThisSlot;
                    }
                }
            }
        }
        return "PARKING FULL";
    }

    public int CheckOut(String CarNo, String CheckOutTime) {

        int charge = 0;
        for (Car car : Cars) {
            if (car.CarNum.equals(CarNo)) {
                int durationInHour = FindDurationInHour(car.CheckInTime, CheckOutTime);
                charge = CalculateCharge((int) durationInHour);
                car.Charge = charge;
                car.CheckOutTime = CheckOutTime;
                AllCars.add(car);

                String ThisSlot = (char) ('A' + car.ParkingFlor) + "-" + car.ParkingSlotNo;
                if (!ReservedSlots.isEmpty() && ReservedSlots.contains(ThisSlot)) {
                    ReservedSlots.remove(ThisSlot);
                } else if (!ReservedSlotsForSeniorCiti.isEmpty() && !ReservedSlotsForSeniorCiti.get(ThisSlot)) {
                    ReservedSlotsForSeniorCiti.put(ThisSlot, true);
                }
                Cars.remove(car);
                return charge;
            }
        }
        return -1;
    }

    public int FindDurationInHour(String in, String out) {
        String cOut = "";
        if (out.substring(5).equals("pm")) {
            int hour;
            if (out.substring(0, 2).equals("12")) {
                hour = Integer.parseInt(out.substring(0, 2));
            } else {
                hour = Integer.parseInt(out.substring(0, 2)) + 12;
            }
            cOut += hour + out.substring(2, 5);
        } else if (out.substring(5).equals("am")) {
            cOut += out.substring(0, 5);
        }

        String Cin = "";
        if (in.substring(5).equals("pm")) {
            int hour;
            if (in.substring(0, 2).equals("12")) {
                hour = Integer.parseInt(in.substring(0, 2));
            } else {
                hour = Integer.parseInt(in.substring(0, 2)) + 12;
            }
            Cin += hour + in.substring(2, 5);
        } else if (in.substring(5).equals("am")) {
            Cin += in.substring(0, 5);
        }

        int durationInHour = (Integer.parseInt(cOut.substring(0, 2))
                - Integer.parseInt(Cin.substring(0, 2)))
                + (Integer.parseInt(cOut.substring(3, 5))
                        - Integer.parseInt(Cin.substring(3, 5))) / 60;
        return durationInHour;
    }

    public int CalculateCharge(int h) {
        if (h <= 2)
            return 50;
        else if (h >= 2 && h <= 4)
            return 80;
        else
            return 100;
    }

    String TimeIn24HourFormat(String time) {
        String ans = "";
        if (time.substring(5).equals("pm")) {
            int hour;
            if (time.substring(0, 2).equals("12")) {
                hour = Integer.parseInt(time.substring(0, 2));
            } else {
                hour = Integer.parseInt(time.substring(0, 2)) + 12;
            }
            ans += hour + time.substring(2, 5);
        } else if (time.substring(5).equals("am")) {
            ans += time.substring(0, 5);
        }
        return ans;
    }

    void GenerateReport() {

        AllCars.sort(Comparator.comparing((Car car) -> car.ParkingFlor).thenComparing((Car car) -> car.ParkingSlotNo)
                .thenComparing((Car car) -> TimeIn24HourFormat(car.CheckInTime)));
        System.out.println("PARKING SLOT, CAR NO, CHECK IN TIME, CHECK OUT TIME, CHARGES, CATEGORY");

        for (Car c : AllCars) {
            String slot = (char) ('A' + c.ParkingFlor) + "-" + c.ParkingSlotNo;
            System.out.println(slot + " , " + c.CarNum + " , " + c.CheckInTime + " , " + c.CheckOutTime + " , "
                    + c.Charge + " , " + c.Category);
        }
    }

    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);

        int NumOfFloor = Integer.parseInt(sc.nextLine());
        int NumOfSlotInEachFloors = Integer.parseInt(sc.nextLine());
        String ReservedForSenior = sc.nextLine();

        ParkingManagementSystem pr = new ParkingManagementSystem(NumOfFloor, NumOfSlotInEachFloors, ReservedForSenior);
        while (true) {
            String inputCar = sc.nextLine();
            String[] arr = inputCar.split(" ");

            if (arr.length == 4) {
                System.out.println(pr.CheckIn(arr[1], arr[2], arr[3]));
            } else if (arr.length == 3) {
                System.out.println(pr.CheckOut(arr[1], arr[2]));
            } else if (inputCar.equals("GENERATE REPORT")) {
                pr.GenerateReport();
            } else {
                System.out.println("Invalid Details...");
                sc.close();
                break;
            }

        }
    }
}
