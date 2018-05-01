package dao;

import bean.Landlord;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * Created by cellargalaxy on 18-4-23.
 */
public abstract class AbstractLandlordDao implements LandlordDao {
	public static final String idName = "_id";
	
	public Landlord insertLandlord(Landlord landlord) {
		return null;
	}
	
	public boolean deleteLandlord(Landlord landlord) {
		return false;
	}
	
	public Landlord selectLandlord(Landlord landlord) {
		if (landlord == null) {
			return null;
		}
		DBObject dbObject = createConditionDBObject(landlord);
		dbObject = getDBCollection().findOne(dbObject);
		return dbObjectToLandlord(dbObject);
	}
	
	public abstract DBObject createConditionDBObject(Landlord landlord);
	
	public Landlord[] selectLandlords(int off, int len) {
		DBCursor dbCursor = getDBCollection().find().limit(len).skip(off);
		Landlord[] landlords = new Landlord[dbCursor.size()];
		int i = 0;
		for (DBObject dbObject : dbCursor.toArray()) {
			landlords[i] = dbObjectToLandlord(dbObject);
			i++;
		}
		return landlords;
	}
	
	public Landlord[] selectAllLandlord() {
		DBCursor dbCursor = getDBCollection().find();
		Landlord[] landlords = new Landlord[dbCursor.size()];
		int i = 0;
		for (DBObject dbObject : dbCursor.toArray()) {
			landlords[i] = dbObjectToLandlord(dbObject);
			i++;
		}
		return landlords;
	}
	
	public Landlord updateLandlord(Landlord landlord) {
		return null;
	}
	
	abstract Landlord dbObjectToLandlord(DBObject dbObject);
	
	abstract DBCollection getDBCollection();
}
