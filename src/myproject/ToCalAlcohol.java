package myproject;

public class ToCalAlcohol {
	
	double CalculatedValue;
	
	public void CalAlInBlood(String WhDr, String Se, int Wei, int Gla, int Per) {
		
		if(WhDr == "เบียร์") {
			
			if(Se == "ชาย") {
				CalculatedValue = ((Gla * 16.5)/(Wei*0.68*10)*1000);
			}
			else {
				CalculatedValue = ((Gla * 16.5)/(Wei*0.55*10)*1000);
			}
			
		}else if(WhDr == "สุรา") {
			
			if(Se == "ชาย") {
				CalculatedValue = ((Gla * 18)/(Wei*0.68*10)*1000);
			}
			else {
				CalculatedValue = ((Gla * 18)/(Wei*0.55*10)*1000);
			}
			
		}else {
			
			if(Se == "ชาย") {
				CalculatedValue = ((Gla * 19.5)/(Wei*0.68*10)*1000);
			}
			else {
				CalculatedValue = ((Gla * 19.5)/(Wei*0.55*10)*1000);
			}
			
		}
		
	}
	
	public double GetCalResult() {
		return CalculatedValue;
	}

}
