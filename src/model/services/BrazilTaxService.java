package model.services;

public class BrazilTaxService implements TaxService {
	
	
	public double tax(double amount) {
		double tax;
		if (amount <= 100.0) {
			tax = amount * 0.2;
		}
		else {
			tax = amount * 0.15;
		}
		
		return tax;
	}

}
