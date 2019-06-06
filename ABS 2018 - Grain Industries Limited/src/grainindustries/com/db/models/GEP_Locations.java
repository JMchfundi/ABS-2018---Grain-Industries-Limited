package grainindustries.com.db.models;

import java.util.Vector;

public class GEP_Locations {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Vector<String> readLocations() {

		final Vector<String> vectorObj = new Vector<>();

		vectorObj.add("Mombasa");
		vectorObj.add("Nairobi");
		vectorObj.add("Mtwapa");
		vectorObj.add("Ukunda");

		return vectorObj;
	}
}