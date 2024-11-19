package myproject;

public class CalBmiBmr {

    double BMIsum, BMRsum ;

    public double calBMI(float UserTall, float UserWeight) {
        BMIsum = UserWeight / ((UserTall / 100) * (UserTall / 100));
        return BMIsum;
    }

    public double calBMR(String UserGender, int UserOld, float UserTall, float UserWeight) {
        if (UserGender.equals("male")) {
            BMRsum = 66 + (13.7 * UserWeight) + (5 * UserTall) - (6.8 * UserOld);
        } else {
            BMRsum = 665 + (9.6 * UserWeight) + (1.8 * UserTall) - (4.7 * UserOld);
        }
        return BMRsum;
    }

    public double getBMI() {
        return BMIsum;
    }

    public double getBMR() {
        return BMRsum;
    }

   
}
