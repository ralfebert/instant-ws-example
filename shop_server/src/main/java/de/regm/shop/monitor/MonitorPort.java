package de.regm.shop.monitor;

import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jws.WebService;

import com.sun.xml.ws.developer.SchemaValidation;

import de.regm.shop.monitor.MonitorPortType;
import de.regm.shop.monitor.types.GetServiceCallsCountRequest;
import de.regm.shop.monitor.types.GetServiceCallsCountResponse;
import de.regm.shop.monitor.types.GetServiceCallsCountResponse.RequestLogs;
import de.regm.shop.RequestCountHandler;

@WebService(endpointInterface = "de.regm.shop.monitor.MonitorPortType")
@SchemaValidation
public class MonitorPort implements MonitorPortType {

	public GetServiceCallsCountResponse getServiceCallsCount(GetServiceCallsCountRequest body) {

		GetServiceCallsCountResponse theValue = new GetServiceCallsCountResponse();

		RequestLogs requestLogs = new RequestLogs();

		for (Entry<String, AtomicInteger> oneEntry : RequestCountHandler.getRequestCounts()
			.entrySet()) {
			de.regm.shop.monitor.types.RequestLog requestLog = new de.regm.shop.monitor.types.RequestLog();
			requestLog.setRequestName(oneEntry.getKey());
			requestLog.setCallCount(oneEntry.getValue().get());
			requestLogs.getRequestLog().add(requestLog);
		}

		theValue.setRequestLogs(requestLogs);
		return theValue;
	}

}