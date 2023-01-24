package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		Vehicle model = new Vehicle(sc.nextLine());
		
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
		
		System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);
		
		CarRental carRental = new CarRental(start, finish, model);
		
		System.out.print("Entre com o preço da hora: ");
		double priceHours = sc.nextDouble();
		
		System.out.print("Entre com o preço por dia: ");
		double priceDays = sc.nextDouble();
		
		RentalService service = new RentalService(priceHours, priceDays, new BrazilTaxService());
		service.processInvoice(carRental);
		
		System.out.println("FATURA: ");
		System.out.println("Pagamento Básico: " + String.format("%.2f", carRental.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", carRental.getInvoice().getTax()));
		System.out.println("Pagamento Total: " + String.format("%.2f", carRental.getInvoice().totalPayment()));
		
		
		sc.close();

	}

}
