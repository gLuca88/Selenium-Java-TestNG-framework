package gianluca.com.configurazionereport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtils {//console e file log

	private static final Logger logger = LogManager.getLogger(LoggerUtils.class);

	public static void info(String msg) {
		logger.info(msg);
	}

	public static void error(String msg) {
		logger.error(msg);
	}

	public static void warn(String msg) {
		logger.warn(msg);
	}
}
