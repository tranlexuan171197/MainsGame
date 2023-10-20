package commons;


import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
//import org.apache.logging.log4j.spi.LoggingEvent;
import org.testng.Reporter;

import java.io.Serializable;

public class TestNGAppender extends AbstractAppender {
	private Layout<? extends Serializable> layout;
	protected TestNGAppender(String name, Layout<? extends Serializable> layout){
		super(name, null, layout, false);
		this.layout = layout;
	}
	public void append(LogEvent event){
		String formattedEvent = new String(layout.toByteArray(event));
		Reporter.log(formattedEvent+"<br>");
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
