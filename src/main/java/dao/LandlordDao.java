package dao;

import bean.Landlord;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public interface LandlordDao {
	Landlord insertLandlord(Landlord landlord);
	
	boolean deleteLandlord(Landlord landlord);
	
	Landlord selectLandlord(Landlord landlord);
	
	Landlord[] selectLandlords(int off, int len);
	
	Landlord[] selectAllLandlord();
	
	Landlord updateLandlord(Landlord landlord);
}
