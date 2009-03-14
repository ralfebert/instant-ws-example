package de.javamagazin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import de.regm.shop.products.ProductsPortType;
import de.regm.shop.products.ProductsService;
import de.regm.shop.products.types.FindProductsRequest;
import de.regm.shop.products.types.FindProductsResponse;
import de.regm.shop.products.types.GetProductByIdRequest;
import de.regm.shop.products.types.GetProductByIdResponse;

	public class ServiceTest {
	
		@Test
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
