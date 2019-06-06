/**
 * (c) Akida M Jafar, PJP
 */
package grainindustries.com.db.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.JTextField;

import pjp.akidamjaffar.crypto.GEP_Decoder;

/**
 * @author Jei Mchfundi
 *
 */
@Entity
public class GEP_Driver_Obj {
	public static class GEP_User_DataList {
		private static List<GEP_Driver_Obj> listObj;

		public static List<GEP_Driver_Obj> createList(ResultSet resultSetObj, JTextField infoTextField) {

			listObj = new LinkedList<>();

			try {
				while (resultSetObj.next()) {
					final GEP_Driver_Obj dataObj = new GEP_Driver_Obj();

					dataObj.setId(GEP_Decoder.decodeData(resultSetObj.getObject("id").toString()));
					dataObj.setName(GEP_Decoder.decodeData(resultSetObj.getObject("name").toString()));
					dataObj.setLocation(GEP_Decoder.decodeData(resultSetObj.getObject("location").toString()));
					dataObj.setCreator(GEP_Decoder.decodeData(resultSetObj.getObject("creator").toString()));
					dataObj.setTill(GEP_Decoder.decodeData(resultSetObj.getObject("till").toString()));
					dataObj.setsCode(GEP_Decoder.decodeData(resultSetObj.getObject("sCode").toString()));

					listObj.add(dataObj);
				}
			} catch (final SQLException e) {
				infoTextField.setText(e.getMessage() + " " + new GEP_Driver_Obj().getClass().getSimpleName());
			}

			return listObj;
		}
	}

	public static class GEP_User_DataListForUpdate {
		private static List<GEP_Driver_Obj> listObj;

		public static List<GEP_Driver_Obj> createList(ResultSet resultSetObj, JTextField infoTextField) {

			listObj = new LinkedList<>();

			try {
				while (resultSetObj.next()) {
					final GEP_Driver_Obj dataObj = new GEP_Driver_Obj();

					dataObj.setId(resultSetObj.getObject("id").toString());
					dataObj.setName(resultSetObj.getObject("name").toString());
					dataObj.setLocation(resultSetObj.getObject("location").toString());
					dataObj.setCreator(resultSetObj.getObject("creator").toString());
					dataObj.setTill(resultSetObj.getObject("till").toString());
					dataObj.setsCode(resultSetObj.getObject("sCode").toString());

					listObj.add(dataObj);
				}
			} catch (final SQLException e) {
				infoTextField.setText(e.getMessage() + " " + new GEP_Driver_Obj().getClass().getSimpleName());
			}

			return listObj;
		}
	}

	public interface TableName {
		String TABLENAME = new GEP_Driver_Obj().getClass().getSimpleName().toUpperCase();
	}

	public interface Unique_Box {
		String UNIQUE_BOX = "abs_drv_2030.";
	}

	private String id;
	private String name;

	private String location;

	private String creator;

	private String till;

	private String sCode;

	public String getCreator() {
		return creator;
	}

	@Id
	public String getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	@Column(unique = true)
	public String getsCode() {
		return sCode;
	}

	@Column(unique = true)
	public String getTill() {
		return till;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public void setTill(String till) {
		this.till = till;
	}

}
