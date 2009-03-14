package de.regm.shop.products;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import javax.jws.WebService;

import com.sun.xml.ws.developer.SchemaValidation;

import de.regm.shop.products.types.FindProductsRequest;
import de.regm.shop.products.types.FindProductsResponse;
import de.regm.shop.products.types.GetProductByIdRequest;
import de.regm.shop.products.types.GetProductByIdResponse;
import de.regm.shop.products.types.Product;

@WebService(endpointInterface = "de.regm.shop.products.ProductsPortType")
@SchemaValidation
public class ProductsPort implements ProductsPortType {

	private Map<Integer, Product> sampleProducts = new TreeMap<Integer, Product>();

	public ProductsPort() {

		Product product = new Product();
		product.setId(1);
		product.setName("Jeans-Hose");
		product.setPrice(new BigDecimal("89.99"));
		product.setSize("L");
		sampleProducts.put(product.getId(), product);

		product = new Product();
		product.setId(2);
		product.setName("Stretch-Hose");
		product.setPrice(new BigDecimal("49.99"));
		product.setSize("M");
		sampleProducts.put(product.getId(), product);
	}

	public FindProductsResponse findProducts(FindProductsRequest request) {

		FindProductsResponse response = new FindProductsResponse();
		response.getProducts().addAll(sampleProducts.values());
		return response;
	}

	public GetProductByIdResponse getProductById(GetProductByIdRequest request) {

		GetProductByIdResponse response = new GetProductByIdResponse();
		response.setProduct(sampleProducts.get(request.getId()));
		return response;
	}

}
