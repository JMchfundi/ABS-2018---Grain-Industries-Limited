package grainindustries.com.db.models;

public class GEP_VanSN_Loc {
	private final String[] systemDetails;

	public GEP_VanSN_Loc(String systemDetails) {
		// TODO Auto-generated constructor stub
		this.systemDetails = systemDetails.split(":");
	}

	public String getVanLoc() {
		return systemDetails[2];
	}

	public String getVanSN() {
		return systemDetails[1].replaceFirst("Route Name", "");
	}
}