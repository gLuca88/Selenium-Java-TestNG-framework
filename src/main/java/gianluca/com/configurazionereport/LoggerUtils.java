package gianluca.com.configurazionereport;

import java.io.File;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class LoggerUtils {

	private static final Logger globalLogger = LogManager.getLogger("GLOBAL");
	private static ThreadLocal<Logger> testLogger = new ThreadLocal<>();
	private static ThreadLocal<Appender> testAppender = new ThreadLocal<>();

	public static void startTestLogging(String testName) {

		try {
			String logDir = ScreenshotUtils.getRunFolder() + "logs/";
			new File(logDir).mkdirs();

			String logPath = logDir + testName + ".log";

			// Appender per il test
			FileAppender appender = FileAppender.newBuilder().setName("Appender_" + testName).withFileName(logPath)
					.withAppend(false)
					.setLayout(PatternLayout.newBuilder().withPattern("%d [%t] %-5level: %msg%n").build()).build();

			appender.start();
			testAppender.set(appender);

			// Configurazione dinamica
			LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
			Configuration config = ctx.getConfiguration();
			config.addAppender(appender);

			LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.INFO, testName, "true",
					new AppenderRef[] {}, null, config, null);

			loggerConfig.addAppender(appender, Level.INFO, null);

			config.addLogger(testName, loggerConfig);
			ctx.updateLoggers();

			testLogger.set(LogManager.getLogger(testName));

		} catch (Exception e) {
			globalLogger.error("Errore creazione logger test", e);
		}
	}

	public static void stopTestLogging() {
		try {
			Logger logger = testLogger.get();
			Appender appender = testAppender.get();

			if (appender != null) {
				appender.stop();

				LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
				Configuration cfg = ctx.getConfiguration();

				cfg.getLoggerConfig(logger.getName()).removeAppender(appender.getName());
				cfg.removeLogger(logger.getName());
				ctx.updateLoggers();
			}
		} catch (Exception e) {
			globalLogger.error("Errore chiusura logger test", e);
		} finally {
			testLogger.remove();
			testAppender.remove();
		}
	}

	public static void info(String msg) {
		Logger logger = testLogger.get();
		if (logger != null)
			logger.info(msg);
		globalLogger.info(msg);
	}

	public static void info(String msg, Throwable t) {
		Logger logger = testLogger.get();
		if (logger != null)
			logger.info(msg, t);
		globalLogger.info(msg, t);
	}

	public static void warn(String msg) {
		Logger logger = testLogger.get();
		if (logger != null)
			logger.warn(msg);
		globalLogger.warn(msg);
	}

	public static void warn(String msg, Throwable t) {
		Logger logger = testLogger.get();
		if (logger != null)
			logger.warn(msg, t);
		globalLogger.warn(msg, t);
	}

	public static void error(String msg) {
		Logger logger = testLogger.get();
		if (logger != null)
			logger.error(msg);
		globalLogger.error(msg);
	}

	public static void error(String msg, Throwable t) {
		Logger logger = testLogger.get();
		if (logger != null)
			logger.error(msg, t);
		globalLogger.error(msg, t);
	}

	public static String getTestLogPath(String testName) {
		return ScreenshotUtils.getRunFolder() + "logs/" + testName + ".log";
	}
}
