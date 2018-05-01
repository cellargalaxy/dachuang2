package bean;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class House {
	private final String landlordId;//data.fangdong
	private final String id;//_id
	private final String houseId;//_id
	private final String link;//url
	
	/**
	 * 下面几个应该是int或者string会好点，这些都是评分数据，5分制的
	 **/
	private final double neatHygiene;//整洁卫生,data.otherComment.score.detail[0].value
	private final double matchingDescription;//描述相符,data.otherComment.score.detail[1].value
	private final String trafficLocation;//交通位置,data.otherComment.score.detail[2].value
	private final double safetyLevel;//安全程度,data.otherComment.score.detail[3].value
	private final double valueForMoney;//性价比,data.otherComment.score.detail[4].value
	private final double total;//总评,data.otherComment.score.total
	
	
	private final double dayTop;//每晚的价格,data.dayTop
	private final double area;//面积,data.area
	
	
	private final String personalDescription;//个性描述,data.detailIntro[0].info
	private final String internalSituation;//内部情况,data.detailIntro[1].info
	private final String trafficCondition;//交通情况,data.detailIntro[2].info
	private final String surroundingSituation;//周边情况,data.detailIntro[3].info
	
	/**
	 * 下面这些其实是根据字符串切割判断的，布尔型或者0/1
	 **/
	//配套设施 data.detailIntro[4].info
	private final int hotShower;//热水淋浴
	private final int wirelessNetwork;//无线网络
	private final int airConditioning;//空调
	private final int tv;//电视
	private final int elevator;//电梯
	private final int accessControlSystem;//门禁系统
	private final int washingMachine;//洗衣机
	private final int refrigerator;//冰箱
	private final int drinkingWaterEquipment;//饮水设备
	private final int heating;//暖气
	private final int flipFlop;//拖鞋
	private final int handPaper;//手纸
	private final int bath;//浴液
	private final int shampoo;//洗发水
	private final int soap;//香皂
	private final int allowToCook;//允许做饭
	private final int smokingAllowed;//允许吸烟
	private final int allowingParties;//允许聚会
	private final int parkingSpace;//停车位
	private final int bathtub;//浴缸
	private final int wiredNetwork;//有线网络
	private final int dentalEquipment;//牙具
	private final int towel;//毛巾
	private final int allowPets;//允许带宠物
	
	/**
	 * 有些跟上面一样但是，字符串的处理更加复杂
	 **/
	//入住须知,data.detailIntro[5].info
	private final int bathroom;//卫生间：独立卫生间
	private final int minimumStayDays;//最少入住天数：1天
	private final int maximumNumberOfDays;//最多入住天数：无限制
	private final int whetherToReceiveOutsiders;//是否接待境外人士：不接待
	private final int replacementOfSheets;//被单更换：每客一换
	
	
	private final int population;//入住人数,data.population 【"宜住4人"】
	
	/**
	 * 这两个其实是家具，填写他们的数量，sofa指的是沙发床
	 * 同样的，要处理字符串
	 * 【"双人床：1.8m宽 × 2m长 ×1张
	 
	 双人床：1.5m宽 × 2m长 ×1张"】
	 */
	//家具,data.furniture
	private final int furniture1Bed;
	private final int furniture2Sofa;
	
	
	private final int numberOfPhotos;//照片数量,data.photos.length 【photos是一个数组】
	private final String address;//data.address
	private final String title;//data.title
	private final double longitude;//data.longitude
	private final double latitude;//data.latitude
	/**
	 * 省和市要从address中分割
	 * 【北京市朝阳区沿海赛洛城】
	 */
	private final String city;//data.address
	private final String province;//data.address
	private final Date fetchTime;//fetchTime
	
	private final Comment[] comments;
	
	public House(String landlordId, String id, String houseId, String link, double neatHygiene, double matchingDescription, String trafficLocation, double safetyLevel, double valueForMoney, double total, double dayTop, double area, String personalDescription, String internalSituation, String trafficCondition, String surroundingSituation, int hotShower, int wirelessNetwork, int airConditioning, int tv, int elevator, int accessControlSystem, int washingMachine, int refrigerator, int drinkingWaterEquipment, int heating, int flipFlop, int handPaper, int bath, int shampoo, int soap, int allowToCook, int smokingAllowed, int allowingParties, int parkingSpace, int bathtub, int wiredNetwork, int dentalEquipment, int towel, int allowPets, int bathroom, int minimumStayDays, int maximumNumberOfDays, int whetherToReceiveOutsiders, int replacementOfSheets, int population, int furniture1Bed, int furniture2Sofa, int numberOfPhotos, String address, String title, double longitude, double latitude, String city, String province, Date fetchTime, Comment[] comments) {
		this.landlordId = landlordId;
		this.id = id;
		this.houseId = houseId;
		this.link = link;
		this.neatHygiene = neatHygiene;
		this.matchingDescription = matchingDescription;
		this.trafficLocation = trafficLocation;
		this.safetyLevel = safetyLevel;
		this.valueForMoney = valueForMoney;
		this.total = total;
		this.dayTop = dayTop;
		this.area = area;
		this.personalDescription = personalDescription;
		this.internalSituation = internalSituation;
		this.trafficCondition = trafficCondition;
		this.surroundingSituation = surroundingSituation;
		this.hotShower = hotShower;
		this.wirelessNetwork = wirelessNetwork;
		this.airConditioning = airConditioning;
		this.tv = tv;
		this.elevator = elevator;
		this.accessControlSystem = accessControlSystem;
		this.washingMachine = washingMachine;
		this.refrigerator = refrigerator;
		this.drinkingWaterEquipment = drinkingWaterEquipment;
		this.heating = heating;
		this.flipFlop = flipFlop;
		this.handPaper = handPaper;
		this.bath = bath;
		this.shampoo = shampoo;
		this.soap = soap;
		this.allowToCook = allowToCook;
		this.smokingAllowed = smokingAllowed;
		this.allowingParties = allowingParties;
		this.parkingSpace = parkingSpace;
		this.bathtub = bathtub;
		this.wiredNetwork = wiredNetwork;
		this.dentalEquipment = dentalEquipment;
		this.towel = towel;
		this.allowPets = allowPets;
		this.bathroom = bathroom;
		this.minimumStayDays = minimumStayDays;
		this.maximumNumberOfDays = maximumNumberOfDays;
		this.whetherToReceiveOutsiders = whetherToReceiveOutsiders;
		this.replacementOfSheets = replacementOfSheets;
		this.population = population;
		this.furniture1Bed = furniture1Bed;
		this.furniture2Sofa = furniture2Sofa;
		this.numberOfPhotos = numberOfPhotos;
		this.address = address;
		this.title = title;
		this.longitude = longitude;
		this.latitude = latitude;
		this.city = city;
		this.province = province;
		this.fetchTime = fetchTime;
		this.comments = comments;
	}
	
	public String getLandlordId() {
		return landlordId;
	}
	
	public String getId() {
		return id;
	}
	
	public String getHouseId() {
		return houseId;
	}
	
	public String getLink() {
		return link;
	}
	
	public double getNeatHygiene() {
		return neatHygiene;
	}
	
	public double getMatchingDescription() {
		return matchingDescription;
	}
	
	public String getTrafficLocation() {
		return trafficLocation;
	}
	
	public double getSafetyLevel() {
		return safetyLevel;
	}
	
	public double getValueForMoney() {
		return valueForMoney;
	}
	
	public double getTotal() {
		return total;
	}
	
	public double getDayTop() {
		return dayTop;
	}
	
	public double getArea() {
		return area;
	}
	
	public String getPersonalDescription() {
		return personalDescription;
	}
	
	public String getInternalSituation() {
		return internalSituation;
	}
	
	public String getTrafficCondition() {
		return trafficCondition;
	}
	
	public String getSurroundingSituation() {
		return surroundingSituation;
	}
	
	public int getHotShower() {
		return hotShower;
	}
	
	public int getWirelessNetwork() {
		return wirelessNetwork;
	}
	
	public int getAirConditioning() {
		return airConditioning;
	}
	
	public int getTv() {
		return tv;
	}
	
	public int getElevator() {
		return elevator;
	}
	
	public int getAccessControlSystem() {
		return accessControlSystem;
	}
	
	public int getWashingMachine() {
		return washingMachine;
	}
	
	public int getRefrigerator() {
		return refrigerator;
	}
	
	public int getDrinkingWaterEquipment() {
		return drinkingWaterEquipment;
	}
	
	public int getHeating() {
		return heating;
	}
	
	public int getFlipFlop() {
		return flipFlop;
	}
	
	public int getHandPaper() {
		return handPaper;
	}
	
	public int getBath() {
		return bath;
	}
	
	public int getShampoo() {
		return shampoo;
	}
	
	public int getSoap() {
		return soap;
	}
	
	public int getAllowToCook() {
		return allowToCook;
	}
	
	public int getSmokingAllowed() {
		return smokingAllowed;
	}
	
	public int getAllowingParties() {
		return allowingParties;
	}
	
	public int getParkingSpace() {
		return parkingSpace;
	}
	
	public int getBathtub() {
		return bathtub;
	}
	
	public int getWiredNetwork() {
		return wiredNetwork;
	}
	
	public int getDentalEquipment() {
		return dentalEquipment;
	}
	
	public int getTowel() {
		return towel;
	}
	
	public int getAllowPets() {
		return allowPets;
	}
	
	public int getBathroom() {
		return bathroom;
	}
	
	public int getMinimumStayDays() {
		return minimumStayDays;
	}
	
	public int getMaximumNumberOfDays() {
		return maximumNumberOfDays;
	}
	
	public int getWhetherToReceiveOutsiders() {
		return whetherToReceiveOutsiders;
	}
	
	public int getReplacementOfSheets() {
		return replacementOfSheets;
	}
	
	public int getPopulation() {
		return population;
	}
	
	public int getFurniture1Bed() {
		return furniture1Bed;
	}
	
	public int getFurniture2Sofa() {
		return furniture2Sofa;
	}
	
	public int getNumberOfPhotos() {
		return numberOfPhotos;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getProvince() {
		return province;
	}
	
	public Date getFetchTime() {
		return fetchTime;
	}
	
	public Comment[] getComments() {
		return comments;
	}
	
	@Override
	public String toString() {
		return "House{" +
				"landlordId='" + landlordId + '\'' +
				", id='" + id + '\'' +
				", houseId='" + houseId + '\'' +
				", link='" + link + '\'' +
				", neatHygiene=" + neatHygiene +
				", matchingDescription=" + matchingDescription +
				", trafficLocation='" + trafficLocation + '\'' +
				", safetyLevel=" + safetyLevel +
				", valueForMoney=" + valueForMoney +
				", total=" + total +
				", dayTop=" + dayTop +
				", area=" + area +
				", personalDescription='" + personalDescription + '\'' +
				", internalSituation='" + internalSituation + '\'' +
				", trafficCondition='" + trafficCondition + '\'' +
				", surroundingSituation='" + surroundingSituation + '\'' +
				", hotShower=" + hotShower +
				", wirelessNetwork=" + wirelessNetwork +
				", airConditioning=" + airConditioning +
				", tv=" + tv +
				", elevator=" + elevator +
				", accessControlSystem=" + accessControlSystem +
				", washingMachine=" + washingMachine +
				", refrigerator=" + refrigerator +
				", drinkingWaterEquipment=" + drinkingWaterEquipment +
				", heating=" + heating +
				", flipFlop=" + flipFlop +
				", handPaper=" + handPaper +
				", bath=" + bath +
				", shampoo=" + shampoo +
				", soap=" + soap +
				", allowToCook=" + allowToCook +
				", smokingAllowed=" + smokingAllowed +
				", allowingParties=" + allowingParties +
				", parkingSpace=" + parkingSpace +
				", bathtub=" + bathtub +
				", wiredNetwork=" + wiredNetwork +
				", dentalEquipment=" + dentalEquipment +
				", towel=" + towel +
				", allowPets=" + allowPets +
				", bathroom=" + bathroom +
				", minimumStayDays=" + minimumStayDays +
				", maximumNumberOfDays=" + maximumNumberOfDays +
				", whetherToReceiveOutsiders=" + whetherToReceiveOutsiders +
				", replacementOfSheets=" + replacementOfSheets +
				", population=" + population +
				", furniture1Bed=" + furniture1Bed +
				", furniture2Sofa=" + furniture2Sofa +
				", numberOfPhotos=" + numberOfPhotos +
				", address='" + address + '\'' +
				", title='" + title + '\'' +
				", longitude=" + longitude +
				", latitude=" + latitude +
				", city='" + city + '\'' +
				", province='" + province + '\'' +
				", fetchTime=" + fetchTime +
				", comments=" + Arrays.toString(comments) +
				'}';
	}
}