package myproject;


public class CalBMR extends CalBmiBmr {
    private double tdee;

    public double calTDEE(String UserGender, int UserOld,
    		float UserTall, float UserWeigh, double activityChoices) {
    	
    	calBMR(UserGender,  UserOld,  UserTall,  UserWeigh);
        tdee = getBMR() * activityChoices;
        return tdee;
    }

    public double getTDEE() {
        return tdee;
    }
}

