package dao;

import bean.House;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

/**
 * Created by cellargalaxy on 18-4-23.
 */
public abstract class AbstractHouseDao implements HouseDao {
	public static final String idName = "_id";
	
	public House insertHouse(House house) {
		return null;
	}
	
	public boolean deleteHouse(House house) {
		return false;
	}
	
	public House selectHouse(House house) {
		if (house == null) {
			return null;
		}
		DBObject dbObject;
		if (house.getId() != null) {
			dbObject = new BasicDBObject(idName, new ObjectId(house.getId()));
		} else {
			return null;
		}
		dbObject = getDBCollection().findOne(dbObject);
		return dbObjectToHouse(dbObject);
	}
	
	public House[] selectHouses(int off, int len) {
		DBCursor dbCursor = getDBCollection().find().limit(len).skip(off);
		House[] houses = new House[dbCursor.size()];
		int i = 0;
		for (DBObject dbObject : dbCursor.toArray()) {
			houses[i] = dbObjectToHouse(dbObject);
			i++;
		}
		return houses;
	}
	
	public House[] selectAllHouse() {
		DBCursor dbCursor = getDBCollection().find();
		House[] houses = new House[dbCursor.size()];
		int i = 0;
		for (DBObject dbObject : dbCursor.toArray()) {
			houses[i] = dbObjectToHouse(dbObject);
			i++;
		}
		return houses;
	}
	
	public House updateHouse(House house) {
		return null;
	}
	
	abstract House dbObjectToHouse(DBObject dbObject);
	
	abstract DBCollection getDBCollection();
}
