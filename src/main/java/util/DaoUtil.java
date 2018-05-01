package util;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cellargalaxy on 18-4-22.
 */
public class DaoUtil {
	public static final DB DB;
	
	static {
		ServerAddress serverAddress = new ServerAddress("119.23.150.92", 27017);
		List<ServerAddress> serverAddresses = new ArrayList<ServerAddress>();
		serverAddresses.add(serverAddress);
		
		MongoCredential credential = MongoCredential.createScramSha1Credential("spider", "spider", "spider123".toCharArray());
		List<MongoCredential> mongoCredentials = new ArrayList<MongoCredential>();
		mongoCredentials.add(credential);
		
		MongoClient mongoClient = new MongoClient(serverAddresses, mongoCredentials);
		
		DB = mongoClient.getDB("spider");
	}
}
