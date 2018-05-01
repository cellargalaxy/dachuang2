package bean;

import java.util.Date;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class Landlord {
	private final String id;
	private final String LandlordId;
	private final String name;
	private final String avatar;
	private final String link;
	private final Date fetchTime;
	private final int mobileAuthentication;//手机认证?
	private final int verified;//实名认证
	private final int authentication;//身份认证?
	private final int sesameCredit;//芝麻信用
	private final double praiseRate;//好评度
	private final double responseRate;//回复率
	private final double acceptanceRate;//接单率
	
	public Landlord(String id, String landlordId, String name, String avatar, String link, Date fetchTime, int mobileAuthentication, int verified, int authentication, int sesameCredit, double praiseRate, double responseRate, double acceptanceRate) {
		this.id = id;
		LandlordId = landlordId;
		this.name = name;
		this.avatar = avatar;
		this.link = link;
		this.fetchTime = fetchTime;
		this.mobileAuthentication = mobileAuthentication;
		this.verified = verified;
		this.authentication = authentication;
		this.sesameCredit = sesameCredit;
		this.praiseRate = praiseRate;
		this.responseRate = responseRate;
		this.acceptanceRate = acceptanceRate;
	}
	
	public String getId() {
		return id;
	}
	
	public String getLandlordId() {
		return LandlordId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public String getLink() {
		return link;
	}
	
	public Date getFetchTime() {
		return fetchTime;
	}
	
	public int getMobileAuthentication() {
		return mobileAuthentication;
	}
	
	public int getVerified() {
		return verified;
	}
	
	public int getAuthentication() {
		return authentication;
	}
	
	public int getSesameCredit() {
		return sesameCredit;
	}
	
	public double getPraiseRate() {
		return praiseRate;
	}
	
	public double getResponseRate() {
		return responseRate;
	}
	
	public double getAcceptanceRate() {
		return acceptanceRate;
	}
	
	@Override
	public String toString() {
		return "Landlord{" +
				"id='" + id + '\'' +
				", LandlordId='" + LandlordId + '\'' +
				", name='" + name + '\'' +
				", avatar='" + avatar + '\'' +
				", link='" + link + '\'' +
				", fetchTime=" + fetchTime +
				", mobileAuthentication=" + mobileAuthentication +
				", verified=" + verified +
				", authentication=" + authentication +
				", sesameCredit=" + sesameCredit +
				", praiseRate=" + praiseRate +
				", responseRate=" + responseRate +
				", acceptanceRate=" + acceptanceRate +
				'}';
	}
}
