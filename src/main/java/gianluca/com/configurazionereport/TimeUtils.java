package gianluca.com.configurazionereport;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	public static String getTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
	}
}
