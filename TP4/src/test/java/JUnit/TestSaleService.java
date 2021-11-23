package JUnit;

import com.integrador4.dto.RequestSale;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.Client;
import com.integrador4.entity.Product;
import com.integrador4.entity.Sale;
import com.integrador4.entity.SaleProduct;
import com.integrador4.repository.*;
import com.integrador4.service.SaleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( MockitoExtension.class )
public class TestSaleService {
    static int productUnits;
    static int idClient;
    static int idProduct;
    static int initialStock;
    static ArrayList<SaleProductDto> saleProduct;
    static Sale sale;
    static double productPrice;
    static double amountOfSale;
    static Client client = new Client("juan","segundo");
    static Product product;
    @Mock  SaleRepository saleRepository;
    @Mock  ProductRepository productRepository;
    @Mock  ClientRepository clientRepository;
    @Mock  RequestRepository requestRepository;
    @InjectMocks  SaleService saleService;

    @BeforeAll
    public static void loadData () {
        saleProduct = new ArrayList<>();
        sale = new Sale();
        productUnits = 2;
        productPrice = 200.3;
        idClient = 9;
        idProduct = 2;
        initialStock = 25;
        amountOfSale = productPrice * productUnits;
        product = new Product("mayonesa",productPrice,initialStock);
    }

    private Optional<Sale> getSale(){
        Mockito.lenient().when( saleRepository.save( sale )).thenReturn( sale );
        Mockito.when( clientRepository.findById(idClient)).thenReturn( Optional.of( client ));
        Mockito.when( productRepository.findById(idProduct)).thenReturn( Optional.of( product ) );
        Mockito.lenient().when( productRepository.save(product)).thenReturn(  product );
        Mockito.lenient().when( requestRepository.getPurchases(Date.valueOf(LocalDate.now()), 0))
                .thenReturn( new ArrayList<>());
        sale.setClient( client );
        sale.setDate(Date.valueOf(LocalDate.now()));
        sale.setAmount( amountOfSale );
        saleProduct.add( new SaleProductDto( productUnits, idProduct ));
        RequestSale rs = new RequestSale(idClient, saleProduct);
        return saleService.save(rs);
    }

    @Test()
    @DisplayName( "successfully sale")
    public void addingSale(){
        Optional<Sale> expected = getSale();
        assertFalse( expected.isEmpty(), "Should save the purchase");
        assertEquals( expected.get().getAmount(), amountOfSale, "Amount should be: " + amountOfSale);
        assertEquals( expected.get().getClient().getName(), client.getName(), "Name should be juan");
        assertTrue( product.getStock() < initialStock );
    }

    @Test()
    @DisplayName( "adding wrong product")
    public void addingWrongProduct() {
        productUnits += 200;
        Optional<Sale> sale = this.getSale();
        assertTrue( sale.isEmpty(), "Shouldn't save the purchase" );
    }



}
