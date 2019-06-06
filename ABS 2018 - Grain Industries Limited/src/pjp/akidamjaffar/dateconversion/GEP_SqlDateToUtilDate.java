package pjp.akidamjaffar.dateconversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class GEP_SqlDateToUtilDate {

	public static Date convertSqlToUtilDate(String userDateObj) {
		final DateFormat dateFormatObj = new SimpleDateFormat("yyyy-MM-dd");
		Date sqlDateObj = null;

		try {
			sqlDateObj = dateFormatObj.parse(userDateObj);
		} catch (final ParseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), new GEP_SqlDateToUtilDate().getClass().getName(),
					JOptionPane.ERROR_MESSAGE);
		}

		final Date utilDateObj = new Date(sqlDateObj.getTime());

		return utilDateObj;
	}
}
