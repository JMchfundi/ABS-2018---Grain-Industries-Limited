package pjp.akidamjaffar.dateconversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class GEP_UtilDateToSqlDate {

	public static java.sql.Date convertUtilToSqlDate(String userDateObj) {
		final DateFormat dateFormatObj = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
		Date utilDateObj = null;

		try {
			utilDateObj = dateFormatObj.parse(userDateObj);
		} catch (final ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_UtilDateToSqlDate().getClass().getName(),
					JOptionPane.ERROR_MESSAGE);
		}

		final java.sql.Date sqlDateObj = new java.sql.Date(utilDateObj.getTime());

		return sqlDateObj;
	}
}
