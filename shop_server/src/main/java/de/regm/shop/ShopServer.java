package de.regm.shop;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Endpoint;

import de.regm.shop.products.ProductsPort;

/**
 * Example for usage of the Endpoint API following the contract-first
 * approach. To do so, the WSDL has to be provided in the meta data map
 * and port/service have to be specified as well.
 * 
 * @author Gunnar Morling
 *
 */
public class ShopServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		URL wsdlResource = ShopServer.class
				.getResource("/WEB-INF/wsdl/products.wsdl");

		List<Source> metadata = new ArrayList<Source>();

		Source source = new StreamSource(wsdlResource.openStream());
		source.setSystemId(wsdlResource.toExternalForm());
		metadata.add(source);

		Endpoint endpoint = Endpoint.create(new ProductsPort());
		endpoint.setMetadata(metadata);
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put(Endpoint.WSDL_PORT, new QName(
				"http://www.regm.de/shop/products",
				"ProductsPort"));
		properties.put(Endpoint.WSDL_SERVICE, new QName(
				"http://www.regm.de/shop/products",
				"ProductsService"));
		endpoint.setProperties(properties);

		endpoint.publish("http://localhost:8080/shop_server/Products");

		System.out
				.println("Published service at http://localhost:8080/shop_server/Products");

	}

}
