/**
 * (c) Akida M Jafar, PJP
 */
package grainindustries.com.db.models;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.JTable;
import javax.swing.JTextField;

import grainindustries.com.db.userdata.OnSystemUser;
import pjp.akidamjaffar.config.GEP_Unique_Box;
import pjp.akidamjaffar.crypto.GEP_Decoder;
import pjp.akidamjaffar.crypto.GEP_Encoder;

/**
 * @author Jei Mchfundi
 *
 */
@Entity
public class GEP_Transction_Obj {
	public static class GEP_Transction_ObjJtable {

		private static GEP_Transction_Obj transction_Obj;

		public static GEP_Transction_Obj sgGEP_Transction_ObjFJ(JTable table, JTextField infoTextField, int row) {

			transction_Obj = new GEP_Transction_Obj();

			transction_Obj.setId(GEP_Encoder.encodeData(GEP_Unique_Box.getUniqueIndex(infoTextField,
					GEP_Transction_Obj.Unique_Box.UNIQUE_BOX, GEP_Transction_Obj.TableName.TABLENAME)));
			transction_Obj.setRef(GEP_Encoder.encodeData(table.getModel().getValueAt(row, 1).toString()));
			transction_Obj.setPayto(GEP_Encoder.encodeData(table.getModel().getValueAt(row, 2).toString()));
			transction_Obj.setMerchant(GEP_Encoder.encodeData(table.getModel().getValueAt(row, 3).toString()));
			transction_Obj.setAmount(GEP_Encoder.encodeData(table.getModel().getValueAt(row, 5).toString()));
			transction_Obj.setDate("");
			transction_Obj.setDescription(GEP_Encoder.encodeData(table.getModel().getValueAt(row, 4).toString()));
			transction_Obj.setTransby(GEP_Encoder.encodeData(OnSystemUser.getUserName().toString()));

			return transction_Obj;
		}
	}

	public static class GEP_Transction_ObjList {
		private static List<GEP_Transction_Obj> listObj;

		public static List<GEP_Transction_Obj> createList(ResultSet resultSetObj, JTextField infoTextField) {

			listObj = new LinkedList<>();

			try {
				while (resultSetObj.next()) {
					final GEP_Transction_Obj dataObj = new GEP_Transction_Obj();

					dataObj.setId(GEP_Decoder.decodeData(resultSetObj.getObject("id").toString()));
					dataObj.setRef(GEP_Decoder.decodeData(resultSetObj.getObject("ref").toString()));
					dataObj.setPayto(GEP_Decoder.decodeData(resultSetObj.getObject("payto").toString()));
					dataObj.setMerchant(GEP_Decoder.decodeData(resultSetObj.getObject("merchant").toString()));
					dataObj.setAmount(GEP_Decoder.decodeData(resultSetObj.getObject("amount").toString()));
					dataObj.setDate(GEP_Decoder.decodeData(resultSetObj.getObject("date").toString()));
					dataObj.setDescription(GEP_Decoder.decodeData(resultSetObj.getObject("description").toString()));
					dataObj.setTransby(GEP_Decoder.decodeData(resultSetObj.getObject("transby").toString()));

					listObj.add(dataObj);
				}
			} catch (final SQLException e) {
				infoTextField.setText(e.getMessage() + " " + new GEP_Transction_Obj().getClass().getSimpleName());
				infoTextField.setForeground(Color.RED);
			}

			return listObj;
		}
	}

	public interface TableName {
		String TABLENAME = new GEP_Transction_Obj().getClass().getSimpleName().toUpperCase();
	}

	public interface Unique_Box {
		String UNIQUE_BOX = "abs_trans_2030.";
	}

	private String id;
	private String ref;
	private String payto;
	private String merchant;

	private String amount;

	private String date;

	private String description;

	private String transby;

	@Column(nullable = false)
	public String getAmount() {
		return amount;
	}

	public String getDate() {
		return date;
	}

	public String getDescription() {
		return description;
	}

	@Id
	public String getId() {
		return id;
	}

	public String getMerchant() {
		return merchant;
	}

	public String getPayto() {
		return payto;
	}

	@Column(unique = true)
	public String getRef() {
		return ref;
	}

	public String getTransby() {
		return transby;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public void setPayto(String payto) {
		this.payto = payto;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setTransby(String transby) {
		this.transby = transby;
	}

}
