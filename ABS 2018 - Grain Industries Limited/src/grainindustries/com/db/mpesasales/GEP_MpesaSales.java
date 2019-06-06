package grainindustries.com.db.mpesasales;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.JOptionPane;

import pjp.akidamjaffar.crypto.GEP_Decoder;

@Entity
public class GEP_MpesaSales {
	public static class GEP_MpesaSalesList {
		private static List<GEP_MpesaSales> listGEP_MpesaSales;

		public static List<GEP_MpesaSales> createList(ResultSet resultSetObj) {

			listGEP_MpesaSales = new LinkedList<>();

			try {
				while (resultSetObj.next()) {
					final GEP_MpesaSales dataObj = new GEP_MpesaSales();

					dataObj.setSalesDate((Date) resultSetObj.getObject("salesDate"));
					dataObj.setSalesStatus(resultSetObj.getObject("salesStatus").toString());
					dataObj.setSalesPayement(Double.parseDouble(resultSetObj.getObject("salesPayement").toString()));
					dataObj.setSalesDeleted(Integer.parseInt(resultSetObj.getObject("salesDeleted").toString()));
					dataObj.setSalesTill(GEP_Decoder.decodeData(resultSetObj.getObject("salesTill").toString()));
					dataObj.setCustPhone(GEP_Decoder.decodeData(resultSetObj.getObject("custPhone").toString()));
					dataObj.setSalesRef(GEP_Decoder.decodeData(resultSetObj.getObject("salesRef").toString()));
					dataObj.setSalesName(GEP_Decoder.decodeData(resultSetObj.getObject("salesName").toString()));
					dataObj.setSalesLocation(
							GEP_Decoder.decodeData(resultSetObj.getObject("salesLocation").toString()));

					listGEP_MpesaSales.add(dataObj);
				}
			} catch (final SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_MpesaSales().getClass().getName(),
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			return listGEP_MpesaSales;
		}
	}

	public interface TableName {
		String TABLENAME = new GEP_MpesaSales().getClass().getSimpleName().toUpperCase();
	}

	private Date salesDate;
	private String salesTill;
	private String custPhone;
	private String salesRef;
	private String salesStatus;
	private String salesLocation;
	private String salesName;

	private double salesPayement;

	private int salesDeleted;

	public String getCustPhone() {
		return custPhone;
	}

	public Date getSalesDate() {
		return salesDate;
	}

	public int getSalesDeleted() {
		return salesDeleted;
	}

	public String getSalesLocation() {
		return salesLocation;
	}

	public String getSalesName() {
		return salesName;
	}

	public double getSalesPayement() {
		return salesPayement;
	}

	@Id
	public String getSalesRef() {
		return salesRef;
	}

	public String getSalesStatus() {
		return salesStatus;
	}

	public String getSalesTill() {
		return salesTill;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public void setSalesDate(Date salesDate) {
		this.salesDate = salesDate;
	}

	public void setSalesDeleted(int salesDeleted) {
		this.salesDeleted = salesDeleted;
	}

	public void setSalesLocation(String salesLocation) {
		this.salesLocation = salesLocation;
	}

	public void setSalesName(String salesName) {
		this.salesName = salesName;
	}

	public void setSalesPayement(Double salesPayement) {
		this.salesPayement = salesPayement;
	}

	public void setSalesRef(String salesRef) {
		this.salesRef = salesRef;
	}

	public void setSalesStatus(String salesStatus) {
		this.salesStatus = salesStatus;
	}

	public void setSalesTill(String salesTill) {
		this.salesTill = salesTill;
	}
}