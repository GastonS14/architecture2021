package JUnit;

import com.integrador4.entity.Product;
import com.integrador4.repository.ProductRepository;
import com.integrador4.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	private static final int PRODUCT_ID = 1;
	private static final Product DEFAULT_PRODUCT = new Product("banana", 50.0, 3);
	private static final Optional<Product> DEFAULT_OPTIONAL_PRODUCT = Optional.of(DEFAULT_PRODUCT);

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	public void givenProductWhenFindByIdIsCalledThenReturnProperlyProduct() {

		Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(DEFAULT_OPTIONAL_PRODUCT);

		Optional<Product> actual = productService.getById(PRODUCT_ID);
		assertEquals(DEFAULT_OPTIONAL_PRODUCT, actual);
	}

	@Test
	public void givenAProductsWhenFindAllIsCalledThenReturnAllTheProducts() {
		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product("banana", 51.0, 31));
		products.add(new Product("martillo", 144.5, 13));
		products.add(new Product("televisor", 500.0, 3));

		Mockito.when(productRepository.findAll()).thenReturn(products);

		Iterable<Product> actual = productService.getAll();
		assertEquals(products, actual);
	}

	@Test
	public void givenProductWhenDeleteIsCalledThenReturnProperlyDeletedProduct() {

		Mockito.when(productRepository.findById(PRODUCT_ID)).thenReturn(DEFAULT_OPTIONAL_PRODUCT);
		Mockito.doNothing().when(productRepository).delete(DEFAULT_PRODUCT);

		Optional<Product> actual = productService.delete(PRODUCT_ID);

		assertEquals(DEFAULT_OPTIONAL_PRODUCT, actual);
	}
}
