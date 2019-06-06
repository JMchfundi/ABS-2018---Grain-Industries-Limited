/**
 *
 */
package grainindustries.com.db.driversdata;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.JOptionPane;

import pjp.akidamjaffar.crypto.GEP_Decoder;

/**
 * @author root
 *
 */
@Entity
public class GEP_SalesDrivers {
	public static class GEP_DriversList {
		private static List<GEP_SalesDrivers> listGEP_Drivers;

		public static List<GEP_SalesDrivers> createList(ResultSet resultSetObj) {

			listGEP_Drivers = new LinkedList<>();

			try {
				while (resultSetObj.next()) {
					final GEP_SalesDrivers dataObj = new GEP_SalesDrivers();

					dataObj.setDriversId(GEP_Decoder.decodeData(resultSetObj.getObject("driversId").toString()));
					dataObj.setDriversLocation(
							GEP_Decoder.decodeData(resultSetObj.getObject("driversLocation").toString()));
					dataObj.setDriversName(GEP_Decoder.decodeData(resultSetObj.getObject("driversName").toString()));
					dataObj.setDriversDeletion(Integer.parseInt(resultSetObj.getObject("driversDeletion").toString()));

					listGEP_Drivers.add(dataObj);
				}
			} catch (final SQLException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_SalesDrivers().getClass().getName(),
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			return listGEP_Drivers;
		}
	}

	public interface TableName {
		String TABLENAME = new GEP_SalesDrivers().getClass().getSimpleName().toUpperCase();
	}

	private static Vector<String> salesDriversTillVector;

	public static Vector<String> setGetSalesDriversTillVector(ResultSet resultSetObj) {
		List<GEP_SalesDrivers> salesDriversList;
		salesDriversTillVector = new Vector<>();

		salesDriversList = GEP_DriversList.createList(resultSetObj);

		for (final GEP_SalesDrivers gep_SalesDrivers : salesDriversList)
			try {
				salesDriversTillVector.addElement(gep_SalesDrivers.getDriversId());
			} catch (final Exception e) {
				e.printStackTrace();
			}
		return salesDriversTillVector;
	}

	private String driversId;

	private String driversName;

	private String driversLocation;

	private int driversDeletion;

	public int getDriversDeletion() {
		return driversDeletion;
	}

	@Id
	public String getDriversId() {
		return driversId;
	}

	public String getDriversLocation() {
		return driversLocation;
	}

	public String getDriversName() {
		return driversName;
	}

	public void setDriversDeletion(int driversDeletion) {
		this.driversDeletion = driversDeletion;
	}

	public void setDriversId(String driversId) {
		this.driversId = driversId;
	}

	public void setDriversLocation(String driversLocation) {
		this.driversLocation = driversLocation;
	}

	public void setDriversName(String driversName) {
		this.driversName = driversName;
	}
}
