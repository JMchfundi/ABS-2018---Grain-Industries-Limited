package grainindustries.com.db.models;

public class GEP_PhoneTillReff {
	private final String[] paymentDetails;

	public GEP_PhoneTillReff(String paymentDetails) {
		// TODO Auto-generated constructor stub
		this.paymentDetails = paymentDetails.split(" ");
	}

	public String getMpsRefference() {

		return paymentDetails[3].substring(0, 10);
	}

	public String getPhoneNumber() {

		return paymentDetails[1].replaceFirst("254", "0");
	}

	public String getTillNumber() {

		return paymentDetails[2].replace("BuyGoods", "");
	}
}