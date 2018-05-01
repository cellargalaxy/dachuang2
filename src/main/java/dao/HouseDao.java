package dao;

import bean.House;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public interface HouseDao {
	House insertHouse(House house);
	
	boolean deleteHouse(House house);
	
	House selectHouse(House house);
	
	House[] selectHouses(int off,int len);
	
	House[] selectAllHouse();
	
	House updateHouse(House house);
}
