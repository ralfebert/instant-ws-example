package de.javamagazin;

import junit.framework.TestCase;
import de.regm.shop.products.ProductsService;
import de.regm.shop.products.types.FindProductsRequest;
import de.regm.shop.products.types.FindProductsResponse;
import de.regm.shop.products.types.GetProductByIdRequest;
import de.regm.shop.products.types.GetProductByIdResponse;

public class AppTest extends TestCase {

	public void testFindProducts() {
		ProductsService service = new ProductsService();
		FindProductsRequest request = new FindProductsRequest();
		request.setSearchText("test");
		request.setMaxResults(10);
		FindProductsResponse response = service.getProductsPort().findProducts(
				request);
		System.out.println(response.getProducts());
	}

	public void testGetProductById() {
		ProductsService service = new ProductsService();
		GetProductByIdRequest request = new GetProductByIdRequest();
		request.setId(1);
		GetProductByIdResponse response = service.getProductsPort()
				.getProductById(request);

		assertNotNull(response.getProduct());
		assertEquals(1, response.getProduct().getId());
		assertEquals("Jeans-Hose", response.getProduct().getName());
	}
}
