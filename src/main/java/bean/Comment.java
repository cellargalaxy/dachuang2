package bean;

import java.util.Date;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class Comment {
	private final String tenantId;
	private final Landlord landlord;
//	private final House house;
	private final Date date;
	private final int january;
	private final int february;
	private final int march;
	private final int april;
	private final int may;
	private final int june;
	private final int july;
	private final int august;
	private final int september;
	private final int october;
	private final int november;
	private final int december;
	private final int holiday;
	private final double neatHygiene;//整洁卫生
	private final double matchingDescription;//描述相符
	private final String trafficLocation;//交通位置
	private final double safetyLevel;//安全程度
	
	public Comment(String tenantId, Landlord landlord, Date date, int january, int february, int march, int april, int may, int june, int july, int august, int september, int october, int november, int december, int holiday, double neatHygiene, double matchingDescription, String trafficLocation, double safetyLevel) {
		this.tenantId = tenantId;
		this.landlord = landlord;
		this.date = date;
		this.january = january;
		this.february = february;
		this.march = march;
		this.april = april;
		this.may = may;
		this.june = june;
		this.july = july;
		this.august = august;
		this.september = september;
		this.october = october;
		this.november = november;
		this.december = december;
		this.holiday = holiday;
		this.neatHygiene = neatHygiene;
		this.matchingDescription = matchingDescription;
		this.trafficLocation = trafficLocation;
		this.safetyLevel = safetyLevel;
	}
	
	public String getTenantId() {
		return tenantId;
	}
	
	public Landlord getLandlord() {
		return landlord;
	}
	
	public Date getDate() {
		return date;
	}
	
	public int getJanuary() {
		return january;
	}
	
	public int getFebruary() {
		return february;
	}
	
	public int getMarch() {
		return march;
	}
	
	public int getApril() {
		return april;
	}
	
	public int getMay() {
		return may;
	}
	
	public int getJune() {
		return june;
	}
	
	public int getJuly() {
		return july;
	}
	
	public int getAugust() {
		return august;
	}
	
	public int getSeptember() {
		return september;
	}
	
	public int getOctober() {
		return october;
	}
	
	public int getNovember() {
		return november;
	}
	
	public int getDecember() {
		return december;
	}
	
	public int getHoliday() {
		return holiday;
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
	
	@Override
	public String toString() {
		return "Comment{" +
				"tenantId='" + tenantId + '\'' +
				", landlord=" + landlord +
				", date=" + date +
				", january=" + january +
				", february=" + february +
				", march=" + march +
				", april=" + april +
				", may=" + may +
				", june=" + june +
				", july=" + july +
				", august=" + august +
				", september=" + september +
				", october=" + october +
				", november=" + november +
				", december=" + december +
				", holiday=" + holiday +
				", neatHygiene=" + neatHygiene +
				", matchingDescription=" + matchingDescription +
				", trafficLocation='" + trafficLocation + '\'' +
				", safetyLevel=" + safetyLevel +
				'}';
	}
}
