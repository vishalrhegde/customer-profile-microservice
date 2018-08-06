package au.com.vishal.customerprofilemicroservice.controller.util;

import java.util.Arrays;
import java.util.List;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.filter.AbstractMatcherFilter;
import ch.qos.logback.core.spi.FilterReply;

public class LogLevelFilter extends AbstractMatcherFilter<Object>{

	@Override
	public FilterReply decide(Object evt) {
		if(!isStarted()){
			return FilterReply.NEUTRAL;
		}
		LoggingEvent  event = (LoggingEvent)evt;
		List<Level> eventsToKeep = Arrays.asList(Level.TRACE, Level.DEBUG, Level.INFO);
        if (eventsToKeep.contains(event.getLevel())){
            return FilterReply.NEUTRAL;
        }
        else{
            return FilterReply.DENY;
        }
	}
	

}
