package dao;

import bean.Comment;
import bean.House;
import bean.Landlord;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import util.DBObjectUtil;
import util.DaoUtil;
import util.TextUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by cellargalaxy on 18-4-23.
 */
public class HouseXiaozhuDao extends AbstractHouseDao {
	private final String houseIdName = "dataId";
	private final String linkName = "url";
	private final String fetchTimeName = "fetchTime";
	private final String dataName = "data";
	
	private final String dayTopName = "dayTop";
	private final String areaName = "area";
	private final String populationName = "population";
	private final String furnitureName = "furniture";
	private final String photosName = "photos";
	private final String addressName = "address";
	private final String titleName = "title";
	private final String longitudeName = "longitude";
	private final String latitudeName = "latitude";
	private final String otherCommentName = "otherComment";
	
	private final String detailIntroName = "detailIntro";
	private final String infoName = "info";
	
	private final String commentsName = "comments";
	private final String checkInTimeName = "checkInTime";
	private final String fangkeIdName = "fangkeId";
	private final DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");
	
	
	private final String scoreName = "score";
	private final String totalName = "total";
	
	private final String detailName = "detail";
	private final String valueName = "value";
	private final String nameName = "name";
	
	private final String fangdongName = "fangdong";
	private final String fangdongLinkName = "link";
	
	private final DBCollection dbCollection = DaoUtil.DB.getCollection("xiaozhu_fangzis");
	private final LandlordXiaozhuDao landlordXiaozhuDao;
	
	public HouseXiaozhuDao(LandlordXiaozhuDao landlordXiaozhuDao) {
		this.landlordXiaozhuDao = landlordXiaozhuDao;
	}
	
	public static void main(String[] args) {
		LandlordXiaozhuDao landlordXiaozhuDao = new LandlordXiaozhuDao();
		HouseXiaozhuDao houseXiaozhuDao = new HouseXiaozhuDao(landlordXiaozhuDao);
		House[] houses = houseXiaozhuDao.selectHouses(0, 1);
		for (House house : houses) {
			System.out.println(house);
		}
	}
	
	House dbObjectToHouse(DBObject dbObject) {
		if (dbObject == null) {
			return null;
		}
		String id = DBObjectUtil.objectToString(dbObject.get(idName));
		String houseId = DBObjectUtil.objectToString(dbObject.get(houseIdName));
		String link = DBObjectUtil.objectToString(dbObject.get(linkName));
		
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
			Landlord landlord = null;
			DBObject fangdong = (DBObject) data.get(fangdongName);
			if (fangdong != null && fangdong.get(fangdongLinkName) != null) {
				landlord = new Landlord(null, null, null, null, fangdong.get(fangdongLinkName).toString(), null, 0, 0, 0, 0, 0, 0, 0);
				landlord = landlordXiaozhuDao.selectLandlord(landlord);
				landlordId = landlord != null ? landlord.getLandlordId() : null;
			}
			
			DBObject otherComment = (DBObject) data.get(otherCommentName);
			if (otherComment != null) {
				List<DBObject> list = (List<DBObject>) otherComment.get(commentsName);
				if (list != null) {
					comments = new Comment[list.size()];
					int i = 0;
					for (DBObject object : list) {
						if (object != null) {
							String checkInTimeString = DBObjectUtil.objectToString(object.get(checkInTimeName));
							String fangkeId = DBObjectUtil.objectToString(object.get(fangkeIdName));
							Date date = null;
							try {
								date = dateFormat.parse(checkInTimeString);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							int january = 1 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int february = 2 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int march = 3 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int april = 4 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int may = 5 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int june = 6 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int july = 7 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int august = 8 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int september = 9 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int october = 10 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int november = 11 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int december = 12 == TextUtil.getMonthFromDate(date) ? 1 : 0;
							int holiday = TextUtil.isHoliday(date);
							double commentNeatHygiene = neatHygiene;//整洁卫生
							double commentMatchingDescription = matchingDescription;//描述相符
							String commentTrafficLocation = trafficLocation;//交通位置
							double commentSafetyLevel = safetyLevel;//安全程度
							comments[i] = new Comment(fangkeId, landlord, date, january, february, march, april, may, june, july, august, september, october, november, december, holiday, commentNeatHygiene, commentMatchingDescription, commentTrafficLocation, commentSafetyLevel);
						}
						i++;
					}
				}
				
				DBObject score = (DBObject) otherComment.get(scoreName);
				if (score != null) {
					List<DBObject> detail = (List<DBObject>) score.get(detailName);
					if (detail != null) {
						Iterator<DBObject> iterator = detail.iterator();
						while (iterator.hasNext()) {
							DBObject object = iterator.next();
							if (object != null) {
								if ("整洁卫生".equals(object.get(nameName))) {
									neatHygiene = DBObjectUtil.objectToDouble(object.get(valueName), -1);
								} else if ("描述相符".equals(object.get(nameName))) {
									matchingDescription = DBObjectUtil.objectToDouble(object.get(valueName), -1);
								} else if ("交通位置".equals(object.get(nameName))) {
									trafficLocation = DBObjectUtil.objectToString(object.get(valueName));
								} else if ("安全程度".equals(object.get(nameName))) {
									safetyLevel = DBObjectUtil.objectToDouble(object.get(valueName), -1);
								} else if ("性价比".equals(object.get(nameName))) {
									valueForMoney = DBObjectUtil.objectToDouble(object.get(valueName), -1);
								}
							}
						}
					}
					total = DBObjectUtil.objectToDouble(score.get(totalName), -1);
				}
			}
			
			dayTop = DBObjectUtil.objectToDouble(data.get(dayTopName), -1);
			area = DBObjectUtil.objectToDouble(data.get(areaName), -1);
			
			List<DBObject> detailIntro = (List<DBObject>) data.get(detailIntroName);
			if (detailIntro != null) {
				Iterator<DBObject> iterator = detailIntro.iterator();
				while (iterator.hasNext()) {
					DBObject object = iterator.next();
					if (object != null) {
						if ("个性描述".equals(object.get(nameName))) {
							String personalDescriptionString = object != null ? DBObjectUtil.objectToString(object.get(infoName)) : null;
							if (personalDescriptionString != null && personalDescriptionString.length() > 0) {
								personalDescription = personalDescriptionString;
							}
						} else if ("内部情况".equals(object.get(nameName))) {
							String internalSituationString = object != null ? DBObjectUtil.objectToString(object.get(infoName)) : null;
							if (internalSituationString != null && internalSituationString.length() > 0) {
								internalSituation = internalSituationString;
							}
						} else if ("交通情况".equals(object.get(nameName))) {
							String trafficConditionString = object != null ? DBObjectUtil.objectToString(object.get(infoName)) : null;
							if (trafficConditionString != null && trafficConditionString.length() > 0) {
								trafficCondition = trafficConditionString;
							}
						} else if ("周边情况".equals(object.get(nameName))) {
							String surroundingSituationString = object != null ? DBObjectUtil.objectToString(object.get(infoName)) : null;
							if (surroundingSituationString != null && surroundingSituationString.length() > 0) {
								surroundingSituation = surroundingSituationString;
							}
						} else if ("配套设施".equals(object.get(nameName))) {
							String facilitiesString = object != null ? DBObjectUtil.objectToString(object.get(infoName)) : null;
							if (facilitiesString != null && facilitiesString.length() > 0) {
								hotShower = facilitiesString.contains("热水淋浴") ? 1 : 0;
								wirelessNetwork = facilitiesString.contains("无线网络") ? 1 : 0;
								airConditioning = facilitiesString.contains("空调") ? 1 : 0;
								tv = facilitiesString.contains("电视") ? 1 : 0;
								elevator = facilitiesString.contains("电梯") ? 1 : 0;
								accessControlSystem = facilitiesString.contains("门禁系统") ? 1 : 0;
								washingMachine = facilitiesString.contains("洗衣机") ? 1 : 0;
								refrigerator = facilitiesString.contains("冰箱") ? 1 : 0;
								drinkingWaterEquipment = facilitiesString.contains("饮水设备") ? 1 : 0;
								heating = facilitiesString.contains("暖气") ? 1 : 0;
								flipFlop = facilitiesString.contains("拖鞋") ? 1 : 0;
								handPaper = facilitiesString.contains("手纸") ? 1 : 0;
								bath = facilitiesString.contains("浴液") ? 1 : 0;
								shampoo = facilitiesString.contains("洗发水") ? 1 : 0;
								soap = facilitiesString.contains("香皂") ? 1 : 0;
								allowToCook = facilitiesString.contains("允许做饭") ? 1 : 0;
								smokingAllowed = facilitiesString.contains("允许吸烟") ? 1 : 0;
								allowingParties = facilitiesString.contains("允许聚会") ? 1 : 0;
								parkingSpace = facilitiesString.contains("停车位") ? 1 : 0;
								bathtub = facilitiesString.contains("浴缸") ? 1 : 0;
								wiredNetwork = facilitiesString.contains("有线网络") ? 1 : 0;
								dentalEquipment = facilitiesString.contains("牙具") ? 1 : 0;
								towel = facilitiesString.contains("毛巾") ? 1 : 0;
								allowPets = facilitiesString.contains("允许带宠物") ? 1 : 0;
							}
						} else if ("入住须知".equals(object.get(nameName))) {
							String checkInInstructionsString = object != null ? DBObjectUtil.objectToString(object.get(infoName)) : null;
							if (checkInInstructionsString != null && checkInInstructionsString.length() > 0) {
								String[] strings = checkInInstructionsString.split("\n");
								if (strings != null) {
									for (String string : strings) {
										if (string != null) {
											if (string.contains("卫生间：")) {
												bathroom = string.contains("独立卫生间") ? 1 : 0;
											} else if (string.contains("最少入住天数：")) {
												minimumStayDays = TextUtil.getIntFromText(string, -1);
											} else if (string.contains("最多入住天数：")) {
												if (string.contains("无限制")) {
													maximumNumberOfDays = Integer.MAX_VALUE;
												} else {
													maximumNumberOfDays = TextUtil.getIntFromText(string, -1);
												}
											} else if (string.contains("是否接待境外人士：")) {
												whetherToReceiveOutsiders = string.contains("不接待") ? 0 : 1;
											}
											if (string.contains("被单更换：")) {
												replacementOfSheets = string.contains("每客一换") ? 1 : 0;
											}
										}
									}
								}
							}
						}
					}
				}
			}
			
			population = DBObjectUtil.objectToInt(data.get(populationName), -1);
			
			String furnitureString = DBObjectUtil.objectToString(data.get(furnitureName));
			if (furnitureString != null) {
				String[] strings = furnitureString.split("\n");
				if (strings != null) {
					for (String string : strings) {
						if (string != null && string.length() > 0) {
							if (string.contains("人床")) {
								furniture1Bed++;
							} else if (string.contains("沙发")) {
								furniture2Sofa++;
							}
						}
					}
				}
			}
			
			List<String> photos = (List<String>) data.get(photosName);
			if (photos != null) {
				numberOfPhotos = photos.size();
			}
			
			address = DBObjectUtil.objectToString(data.get(addressName));
			title = DBObjectUtil.objectToString(data.get(titleName));
			longitude = DBObjectUtil.objectToDouble(data.get(longitudeName), -1);
			latitude = DBObjectUtil.objectToDouble(data.get(latitudeName), -1);
		}
		
		String[] strings = TextUtil.getLocationFromText(address);
		String province = strings[0];
		String city = strings[1];
		Date fetchTime = (Date) dbObject.get(fetchTimeName);
		
		
		return new House(landlordId, id, houseId, link, neatHygiene, matchingDescription, trafficLocation, safetyLevel, valueForMoney, total, dayTop, area, personalDescription, internalSituation, trafficCondition, surroundingSituation, hotShower, wirelessNetwork, airConditioning, tv, elevator, accessControlSystem, washingMachine, refrigerator, drinkingWaterEquipment, heating, flipFlop, handPaper, bath, shampoo, soap, allowToCook, smokingAllowed, allowingParties, parkingSpace, bathtub, wiredNetwork, dentalEquipment, towel, allowPets, bathroom, minimumStayDays, maximumNumberOfDays, whetherToReceiveOutsiders, replacementOfSheets, population, furniture1Bed, furniture2Sofa, numberOfPhotos, address, title, longitude, latitude, city, province, fetchTime, comments);
	}
	
	DBCollection getDBCollection() {
		return dbCollection;
	}
}
