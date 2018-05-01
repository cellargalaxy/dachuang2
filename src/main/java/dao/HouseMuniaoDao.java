package dao;

import bean.Comment;
import bean.House;
import bean.Landlord;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import util.DBObjectUtil;
import util.DaoUtil;
import util.TextUtil;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cellargalaxy on 18-4-23.
 */
public class HouseMuniaoDao extends AbstractHouseDao {
	private final String houseIdName = "dataId";
	private final String linkName = "url";
	private final String fetchTimeName = "fetchTime";
	private final String dataName = "data";
	private final String trafficLocationName = "nearLine";
	private final String totalName = "overallRating";
	private final String dayTopName = "price";
	private final String personalDescriptionName = "desc";
	private final String internalSituationName = "desc";
	private final String trafficConditionName = "nearLine";
	private final String surroundingSituationName = "nearFacility";
	private final String roomYqsfName = "roomYqsf";
	private final String picsName = "pics";
	private final String addressName = "address";
	private final String titleName = "title";
	private final String longitudeName = "lng";
	private final String latitudeName = "lat";
	
	private final String commentsName = "comments";
	private final String scoresName = "scores";
	
	private final String fangdongName = "fangdong";
	private final String fangdongLinkName = "link";
	
	private final String commentRatesName = "commentRates";
	private final String nameName = "name";
	private final String valueName = "value";
	
	private final String facilityName = "facility";
	
	private final String descDetailsName = "descDetails";
	
	
	private final DBCollection dbCollection = DaoUtil.DB.getCollection("muniao_fangzis");
	private final LandlordMuniaoDao landlordMuniaoDao;
	
	public static void main(String[] args) {
		LandlordMuniaoDao landlordMuniaoDao = new LandlordMuniaoDao();
		
		HouseDao houseDao = new HouseMuniaoDao(landlordMuniaoDao);
		House[] houses = houseDao.selectHouses(0, 100);
		if (houses != null) {
			for (int i = 0; i < houses.length; i++) {
				System.out.println(i + " " + houses[i]);
			}
		}
	}
	
	public HouseMuniaoDao(LandlordMuniaoDao landlordMuniaoDao) {
		this.landlordMuniaoDao = landlordMuniaoDao;
	}
	
	House dbObjectToHouse(DBObject dbObject) {
		if (dbObject == null) {
			return null;
		}
		String id = DBObjectUtil.objectToString(dbObject.get(idName));
		String houseId = DBObjectUtil.objectToString(dbObject.get(houseIdName));
		String link = DBObjectUtil.objectToString(dbObject.get(linkName));
		Date fetchTime = dbObject.get(fetchTimeName) != null ? (Date) dbObject.get(fetchTimeName) : null;
		
		String landlordId = null;
		
		double neatHygiene = -1;
		double matchingDescription = -1;
		String trafficLocation = null;
		double safetyLevel = -1;
		double valueForMoney = -1;
		double total = -1;
		
		double dayTop = -1;
		double area = -1;
		
		String personalDescription = null;
		String internalSituation = null;
		String trafficCondition = null;
		String surroundingSituation = null;
		
		int hotShower = 0;
		int wirelessNetwork = 0;
		int airConditioning = 0;
		int tv = 0;
		int elevator = 0;
		int accessControlSystem = 0;
		int washingMachine = 0;
		int refrigerator = 0;
		int drinkingWaterEquipment = 0;
		int heating = 0;
		int flipFlop = 0;
		int handPaper = 0;
		int bath = 0;
		int shampoo = 0;
		int soap = 0;
		int allowToCook = 0;
		int smokingAllowed = 0;
		int allowingParties = 0;
		int parkingSpace = 0;
		int bathtub = 0;
		int wiredNetwork = 0;
		int dentalEquipment = 0;
		int towel = 0;
		int allowPets = 0;
		
		int bathroom = 0;
		int minimumStayDays = -1;
		int maximumNumberOfDays = Integer.MAX_VALUE;
		int whetherToReceiveOutsiders = -1;
		int replacementOfSheets = -1;
		
		int population = -1;
		
		int furniture1Bed = 0;
		int furniture2Sofa = 0;
		
		int numberOfPhotos = -1;
		String address = null;
		String title = null;
		double longitude = -1;
		double latitude = -1;
		
		Comment[] comments = null;
		
		DBObject data = (DBObject) dbObject.get(dataName);
		if (data != null) {
			DBObject fangdong = (DBObject) data.get(fangdongName);
			if (fangdong != null && fangdong.get(fangdongLinkName) != null) {
				Landlord landlord = new Landlord(null, null, null, null, fangdong.get(fangdongLinkName).toString(), null, 0, 0, 0, 0, 0, 0, 0);
				landlord = landlordMuniaoDao.selectLandlord(landlord);
				landlordId = landlord != null ? landlord.getLandlordId() : null;
			}
			
//			List<DBObject> commentsList = (List<DBObject>) data.get(commentsName);
//			if (commentsList != null) {
//				comments = new Comment[commentsList.size()];
//				int i = 0;
//				for (DBObject object : commentsList) {
//					if (object != null) {
//						String tenantId;
//						Landlord landlord;
//						Date date;
//						int january = 1 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int february = 2 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int march = 3 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int april = 4 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int may = 5 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int june = 6 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int july = 7 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int august = 8 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int september = 9 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int october = 10 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int november = 11 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int december = 12 == TextUtil.getMonthFromDate(date) ? 1 : 0;
//						int holiday;
//						double commentNeatHygiene;//整洁卫生
//						double commentMatchingDescription;//描述相符
//						String commentTrafficLocation;//交通位置
//						double commentSafetyLevel;//安全程度
//						List<Integer> scores = (List<Integer>) object.get(scoresName);
//						if (scores != null) {
//							Iterator<Integer> iterator = scores.iterator();
//							if (iterator.hasNext()) {
//							}
//							if (iterator.hasNext()) {
//								commentSafetyLevel = iterator.next();
//							}
//							if (iterator.hasNext()) {
//								commentMatchingDescription = iterator.next();
//							}
//							if (iterator.hasNext()) {
//								commentNeatHygiene = iterator.next();
//							}
//						}
//						comments[i] = null;//new Comment(tenantId, landlord, this, date, january, february, march, april, may, june, july, august, september, october, november, december, holiday, commentNeatHygiene, commentMatchingDescription, commentTrafficLocation, commentSafetyLevel);
//					}
//					i++;
//				}
//			}
			
			List<DBObject> commentRates = (List<DBObject>) data.get(commentRatesName);
			if (commentRates != null) {
				Iterator<DBObject> iterator = commentRates.iterator();
				while (iterator.hasNext()) {
					DBObject object = iterator.next();
					if (object != null) {
						if ("卫生状况".equals(object.get(nameName))) {
							neatHygiene = DBObjectUtil.objectToDouble(object.get(valueName), -1);
						} else if ("服务态度".equals(object.get(nameName))) {
							safetyLevel = DBObjectUtil.objectToDouble(object.get(valueName), -1);
						} else if ("图片吻合".equals(object.get(nameName))) {
							matchingDescription = DBObjectUtil.objectToDouble(object.get(valueName), -1);
						} else if ("设施装潢".equals(object.get(nameName))) {
							valueForMoney = DBObjectUtil.objectToDouble(object.get(valueName), -1);
						}
					}
				}
			}
			
			trafficLocation = DBObjectUtil.objectToString(data.get(trafficLocationName));
			total = DBObjectUtil.objectToDouble(data.get(totalName), -1);
			dayTop = DBObjectUtil.objectToDouble(data.get(dayTopName), -1);
			
			List<DBObject> descDetails = (List<DBObject>) data.get(descDetailsName);
			if (descDetails != null) {
				Iterator<DBObject> iterator = descDetails.iterator();
				while (iterator.hasNext()) {
					DBObject object = iterator.next();
					if (object != null) {
						if ("总床数".equals(object.get(nameName))) {
							furniture1Bed = DBObjectUtil.objectToInt(object.get(valueName), -1);
						} else if ("床型".equals(object.get(nameName))) {
							String string = DBObjectUtil.objectToString(object.get(valueName));
							if (string != null) {
								String[] strings = string.split("\n");
								if (strings != null) {
									int count = 0;
									for (String s : strings) {
										if (s.contains("沙发")) {
											count++;
										}
									}
									furniture2Sofa = count;
								}
							}
						} else if ("独立卫生间".equals(object.get(nameName))) {
							bathroom = DBObjectUtil.objectToInt(object.get(valueName), -1);
						} else if ("面积".equals(object.get(nameName))) {
							area = DBObjectUtil.objectToDouble(object.get(valueName), -1);
						} else if ("最少天数".equals(object.get(nameName))) {
							minimumStayDays = DBObjectUtil.objectToInt(object.get(valueName), -1);
						} else if ("最多天数".equals(object.get(nameName))) {
							String string = DBObjectUtil.objectToString(object.get(valueName));
							if (string != null) {
								if (string.contains("不限")) {
									maximumNumberOfDays = Integer.MAX_VALUE;
								} else {
									maximumNumberOfDays = DBObjectUtil.objectToInt(string, -1);
								}
							}
						} else if ("可住人数".equals(object.get(nameName))) {
							population = DBObjectUtil.objectToInt(object.get(valueName), -1);
						} else if ("停车位".equals(object.get(nameName))) {
							parkingSpace = object.get(valueName) != null && object.get(valueName).toString().length() > 0 ? 1 : 0;
						} else if ("接待外宾".equals(object.get(nameName)) && "接待".equals(object.get(valueName))) {
							whetherToReceiveOutsiders = 1;
						}
					}
				}
			}
			
			personalDescription = DBObjectUtil.objectToString(data.get(personalDescriptionName));
			internalSituation = DBObjectUtil.objectToString(data.get(internalSituationName));
			trafficCondition = DBObjectUtil.objectToString(data.get(trafficConditionName));
			surroundingSituation = DBObjectUtil.objectToString(data.get(surroundingSituationName));
			
			List<String> facilitiesString = (List<String>) data.get(facilityName);
			if (facilitiesString != null) {
				hotShower = facilitiesString.contains("全天热水") ? 1 : 0;
				wirelessNetwork = facilitiesString.contains("无线宽带") ? 1 : 0;
				airConditioning = facilitiesString.contains("空调") ? 1 : 0;
				tv = facilitiesString.contains("电视") || facilitiesString.contains("有线电视") ? 1 : 0;
				elevator = facilitiesString.contains("电梯") ? 1 : 0;
				accessControlSystem = facilitiesString.contains("对讲门禁") ? 1 : 0;
				washingMachine = facilitiesString.contains("洗衣机") ? 1 : 0;
				refrigerator = facilitiesString.contains("电冰箱") ? 1 : 0;
				drinkingWaterEquipment = facilitiesString.contains("热水壶") ? 1 : 0;
				heating = facilitiesString.contains("暖气") ? 1 : 0;
				flipFlop = facilitiesString.contains("拖鞋") ? 1 : 0;
				
				allowToCook = 0;//允许做饭,0
				smokingAllowed = facilitiesString.contains("允许抽烟") ? 1 : 0;
				bathtub = facilitiesString.contains("热水浴缸") ? 1 : 0;
				wiredNetwork = facilitiesString.contains("宽带上网") ? 1 : 0;
				allowPets = 0;//允许带宠物,0
				if (facilitiesString.contains("一次性用品")) {
					handPaper = 1;
					bath = 1;
					shampoo = 1;
					soap = 1;
					dentalEquipment = 1;
					towel = 1;
				}
			}
			
			String roomYqsf = DBObjectUtil.objectToString(data.get(roomYqsfName));
			if (roomYqsf != null) {
				if (roomYqsf.contains("聚会")) {
					allowingParties = 1;//允许聚会,0
				}
				if (roomYqsf.contains("被单")) {
					replacementOfSheets = 1;//被单更换
				}
			}
			
			List<String> pics = (List<String>) data.get(picsName);
			if (pics != null) {
				numberOfPhotos = pics.size();
			}
			
			address = DBObjectUtil.objectToString(data.get(addressName));
			title = DBObjectUtil.objectToString(data.get(titleName));
			longitude = DBObjectUtil.objectToDouble(data.get(longitudeName), -1);
			latitude = DBObjectUtil.objectToDouble(data.get(latitudeName), -1);
		}
		String[] strings = TextUtil.getLocationFromText(address);
		String province = strings[0];
		String city = strings[1];
		return new House(landlordId, id, houseId, link, neatHygiene, matchingDescription, trafficLocation, safetyLevel, valueForMoney, total, dayTop, area, personalDescription, internalSituation, trafficCondition, surroundingSituation, hotShower, wirelessNetwork, airConditioning, tv, elevator, accessControlSystem, washingMachine, refrigerator, drinkingWaterEquipment, heating, flipFlop, handPaper, bath, shampoo, soap, allowToCook, smokingAllowed, allowingParties, parkingSpace, bathtub, wiredNetwork, dentalEquipment, towel, allowPets, bathroom, minimumStayDays, maximumNumberOfDays, whetherToReceiveOutsiders, replacementOfSheets, population, furniture1Bed, furniture2Sofa, numberOfPhotos, address, title, longitude, latitude, city, province, fetchTime, comments);
	}
	
	DBCollection getDBCollection() {
		return dbCollection;
	}
}
