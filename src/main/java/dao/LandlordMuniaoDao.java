package dao;

import bean.Landlord;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import util.DBObjectUtil;
import util.DaoUtil;
import util.TextUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by cellargalaxy on 18-4-23.
 */
public class LandlordMuniaoDao extends AbstractLandlordDao {
	private static final String LandlordIdName = "dataId";
	private static final String linkName = "url";
	private static final String fetchTimeName = "fetchTime";
	
	private static final String dataName = "data";
	private static final String avatarName = "avatar";
	private static final String nameName = "name";
	private static final String percentsName = "percents";//好评度,回复率,接单率
	
	private final DBCollection dbCollection = DaoUtil.DB.getCollection("muniao_fangdongs");
	
	public static void main(String[] args) {
		LandlordDao landlordDao = new LandlordMuniaoDao();
		
		Landlord landlord = new Landlord("5a283161bb20e019d0804d73", null, null, null, null, null, 0, 0, 0, 0, 0, 0, 0);
		
		landlord = landlordDao.selectLandlord(landlord);
		
		Landlord[] landlords = landlordDao.selectLandlords(0, 100);
		
		for (int i = 0; i < landlords.length; i++) {
			System.out.println(i + " " + landlords[i]);
		}
	}
	
	public DBObject createConditionDBObject(Landlord landlord) {
		DBObject dbObject = null;
		if (landlord.getId() != null) {
			dbObject = new BasicDBObject(idName, new ObjectId(landlord.getId()));
		} else if (landlord.getLandlordId() != null) {
			dbObject = new BasicDBObject(LandlordIdName, landlord.getLandlordId());
		} else if (landlord.getLink() != null) {
			dbObject = new BasicDBObject(linkName, landlord.getLink());
		}
		return dbObject;
	}
	
	final Landlord dbObjectToLandlord(DBObject dbObject) {
		if (dbObject == null) {
			return null;
		}
		String id = DBObjectUtil.objectToString(dbObject.get(idName));
		String LandlordId = DBObjectUtil.objectToString(dbObject.get(LandlordIdName));
		String link = DBObjectUtil.objectToString(dbObject.get(linkName));
		Date fetchTime = dbObject.get(fetchTimeName) != null ? (Date) dbObject.get(fetchTimeName) : null;
		String avatar = null;
		String name = null;
		double praiseRate = -1;//好评度
		double responseRate = -1;//回复率
		double acceptanceRate = -1;//接单率
		
		DBObject data = (DBObject) dbObject.get(dataName);
		if (data != null) {
			avatar = DBObjectUtil.objectToString(data.get(avatarName));
			name = DBObjectUtil.objectToString(data.get(nameName));
			
			List<String> percents = (List<String>) data.get(percentsName);
			if (percents != null) {
				String s1 = percents.get(0);
				String s2 = percents.get(1);
				String s3 = percents.get(2);
				praiseRate = TextUtil.getDoubleFromText(s1, -1);
				responseRate = TextUtil.getDoubleFromText(s2, -1);
				acceptanceRate = TextUtil.getDoubleFromText(s3, -1);
			}
		}
		return new Landlord(id, LandlordId, name, avatar, link, fetchTime, 0, 0, 0, -1, praiseRate, responseRate, acceptanceRate);
	}
	
	final DBCollection getDBCollection() {
		return dbCollection;
	}
}
