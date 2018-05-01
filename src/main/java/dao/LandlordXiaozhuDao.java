package dao;

import bean.Landlord;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import util.DBObjectUtil;
import util.DaoUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class LandlordXiaozhuDao extends AbstractLandlordDao {
	private static final String LandlordIdName = "dataId";
	private static final String linkName = "url";
	private static final String fetchTimeName = "fetchTime";
	
	private static final String dataName = "data";
	private static final String nameName = "name";
	private static final String avatarName = "avatar";
	private static final String sesameCreditName = "zmScore";//芝麻信用
	private static final String responseRateName = "orderAccept";//接单率
	private static final String acceptanceRateName = "onlineReply";//回复率
	
	private static final String authName = "auth";
	private static final String mobileAuthenticationName = "手机认证";
	private static final String verifiedKeyName = "实名认证";
	private static final String authenticationName = "身份认证";
	
	private final DBCollection dbCollection = DaoUtil.DB.getCollection("xiaozhu_fangdongs");
	
	public static void main(String[] args) {
		LandlordDao landlordDao = new LandlordXiaozhuDao();
		
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
		String name = null;
		String avatar = null;
		int sesameCredit = -1;
		double responseRate = -1;
		double acceptanceRate = -1;
		int mobileAuthentication = 0;
		int verified = 0;
		int authentication = 0;
		DBObject data = (DBObject) dbObject.get(dataName);
		if (data != null) {
			name = DBObjectUtil.objectToString(data.get(nameName));
			avatar = DBObjectUtil.objectToString(data.get(avatarName));
			sesameCredit = DBObjectUtil.objectToInt(data.get(sesameCreditName), -1);//芝麻信用
			responseRate = DBObjectUtil.objectToDouble(data.get(responseRateName), -1);//回复率
			acceptanceRate = DBObjectUtil.objectToDouble(data.get(acceptanceRateName), -1);//接单率
			
			List<String> auth = (List<String>) data.get(authName);
			if (auth != null) {
				mobileAuthentication = auth.contains(mobileAuthenticationName) ? 1 : 0;//手机认证?
				verified = auth.contains(verifiedKeyName) ? 1 : 0;//实名认证
				authentication = auth.contains(authenticationName) ? 1 : 0;//身份认证?
			}
		}
		return new Landlord(id, LandlordId, name, avatar, link, fetchTime, mobileAuthentication, verified, authentication, sesameCredit, -1, responseRate, acceptanceRate);
	}
	
	final DBCollection getDBCollection() {
		return dbCollection;
	}
}
