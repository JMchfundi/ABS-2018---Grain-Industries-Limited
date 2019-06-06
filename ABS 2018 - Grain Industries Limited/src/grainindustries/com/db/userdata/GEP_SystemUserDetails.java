package grainindustries.com.db.userdata;

import java.util.Date;

import grainindustries.com.db.models.GEP_User_Obj;
import pjp.akidamjaffar.crypto.GEP_Encoder;
import pjp.akidamjaffar.dateconversion.GEP_UtilDateToSqlDate;

public class GEP_SystemUserDetails {

	public static GEP_User_Obj getUserDataEntries() {
		final GEP_User_Obj userData = new GEP_User_Obj();

		userData.setId(GEP_Encoder.encodeData(OnSystemUser.GetUserAutho.USERNAME));
		userData.setName(GEP_Encoder.encodeData("Abs System Default"));
		userData.setWord(GEP_Encoder.encodeData("Password@2906"));
		userData.setLocation(GEP_Encoder.encodeData("Abs System Default"));
		userData.setCreator(GEP_Encoder.encodeData("Abs System Default"));
		userData.setActive_mult(GEP_Encoder.encodeData("0"));
		userData.setLast_log(GEP_UtilDateToSqlDate.convertUtilToSqlDate(new Date().toString()));
		userData.setUser_authencity(GEP_Encoder.encodeData(OnSystemUser.GetUserAutho.ADMIN));

		return userData;
	}

	private GEP_SystemUserDetails() {

	}
}
