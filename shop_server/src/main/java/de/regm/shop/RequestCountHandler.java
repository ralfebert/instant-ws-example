package de.regm.shop;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.ws.handler.LogicalHandler;
import javax.xml.ws.handler.LogicalMessageContext;
import javax.xml.ws.handler.MessageContext;

public class RequestCountHandler implements
		LogicalHandler<LogicalMessageContext> {

	private static ConcurrentMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<String, AtomicInteger>();

	private static void incrementCount(String requestName) {

		AtomicInteger count = requestCounts.putIfAbsent

		(requestName, new AtomicInteger());

		if (count == null)

			count = requestCounts.get(requestName);

		count.incrementAndGet();

		System.out.println(requestCounts);
	}

	public boolean handleMessage(

	LogicalMessageContext lmc) {

		try {
			boolean isOutbound = (Boolean)

			lmc.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
			// inbound handler invocation

			if (!isOutbound) {

				DOMResult requestDOM = new DOMResult();

				Transformer transformer = TransformerFactory.

				newInstance().newTransformer();
				transformer.transform(lmc.getMessage().

				getPayload(), requestDOM);

				String requestName = requestDOM.getNode().

				getFirstChild().getNodeName();

				RequestCountHandler.

				incrementCount(requestName);

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		return true;

	}

	public void close(MessageContext context) {

		// nothing to do here

	}

	public boolean handleFault

	(LogicalMessageContext context) {

		// nothing to do here

		return false;
	}

	public static ConcurrentMap<String, AtomicInteger> getRequestCounts() {
		return requestCounts;
	}

}
