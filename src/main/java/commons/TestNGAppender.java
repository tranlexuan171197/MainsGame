package commons;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class TestNGAppender extends AppenderSkeleton {
	protected void append(LoggingEvent event){
		Reporter.log(getLayout().format(event)+"<br>");
	}
	public void close(){
		Reporter.log("Loggin appender is closed.");
	}
	public boolean requiresLayout(){
		return true;
	}

	public void info(String logmessage){
		Reporter.log(logmessage);
	}
}
