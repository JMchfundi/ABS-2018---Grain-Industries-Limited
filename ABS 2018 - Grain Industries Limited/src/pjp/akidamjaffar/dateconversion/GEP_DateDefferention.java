package pjp.akidamjaffar.dateconversion;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class GEP_DateDefferention {

	public static Object convertSqlToUtilDate(Date firstDateObj, Date secDateObj) {
		final long differnceValue = secDateObj.getTime() - firstDateObj.getTime();

		return TimeUnit.DAYS.convert(differnceValue, TimeUnit.MILLISECONDS);
	}
}
