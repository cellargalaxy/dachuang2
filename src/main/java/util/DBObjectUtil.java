package util;

/**
 * Created by cellargalaxy on 18-4-24.
 */
public class DBObjectUtil {
	public static final int objectToInt(Object object, int defaultValue) {
		if (object == null) {
			return defaultValue;
		}
		return TextUtil.getIntFromText(object.toString(), defaultValue);
	}
	
	public static final double objectToDouble(Object object, double defaultValue) {
		if (object == null) {
			return defaultValue;
		}
		return TextUtil.getDoubleFromText(object.toString(), defaultValue);
	}
	
	public static final String objectToString(Object object) {
		return object != null ? object.toString() : null;
	}
}
