/**
 *
 */
package grainindustries.com.db.driversdata;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.JOptionPane;

import pjp.akidamjaffar.crypto.GEP_Decoder;

/**
 * @author root
 *
 */
@Entity
public class GEP_Receive_Sales {
	public static class GEP_DriversList {
		private static List<GEP_Receive_Sales> listGEP_Drivers;

		public static List<GEP_Receive_Sales> createList(ResultSet resultSetObj) {

			listGEP_Drivers = new LinkedList<>();

			try {
				while (resultSetObj.next()) {
					final GEP_Receive_Sales dataObj = new GEP_Receive_Sales();

					dataObj.setDriversLocation(
							GEP_Decoder.decodeData(resultSetObj.getObject("driversLocation").toString()));
					dataObj.setDriversDeletion(Integer.parseInt(resultSetObj.getObject("driversDeletion").toString()));
					dataObj.setDuePayment(Double.parseDouble(resultSetObj.getObject("duePayment").toString()));
					dataObj.setTransactionDetails(resultSetObj.getObject("transactionDetails").toString());
					dataObj.setAcknowledgementDate((Date) resultSetObj.getObject("salesDate"));

					listGEP_Drivers.add(dataObj);
				}
			} catch (final SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_Receive_Sales().getClass().getName(),
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			return listGEP_Drivers;
		}
	}

	public interface TableName {
		String TABLENAME = new GEP_Receive_Sales().getClass().getSimpleName().toUpperCase();
	}

	private String recordNO;
	private String driversLocation;
	private int driversDeletion;
	private double duePayment;

	private String transactionDetails;

	private Date salesDate;

	@Column(name = "salesDate")
	public Date getAcknowledgementDate() {
		return salesDate;
	}

	public int getDriversDeletion() {
		return driversDeletion;
	}

	public String getDriversLocation() {
		return driversLocation;
	}

	public double getDuePayment() {
		return duePayment;
	}

	@Id
	public String getRecordNO() {
		return recordNO;
	}

	public String getTransactionDetails() {
		return transactionDetails;
	}

	public void setAcknowledgementDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public void setDriversDeletion(int driversDeletion) {
		this.driversDeletion = driversDeletion;
	}

	public void setDriversLocation(String driversLocation) {
		this.driversLocation = driversLocation;
	}

	public void setDuePayment(double duePayment) {
		this.duePayment = duePayment;
	}

	public void setRecordNO(String recordNO) {
		this.recordNO = recordNO;
	}

	public void setTransactionDetails(String transactionDetails) {
		this.transactionDetails = transactionDetails;
	}
}
