package JUnit;

import com.integrador4.dto.RequestSale;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.Client;
import com.integrador4.entity.Product;
import com.integrador4.entity.Sale;
import com.integrador4.repository.*;
import com.integrador4.service.SaleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( MockitoExtension.class )
@ExtendWith(SpringExtension.class )
public class TestSaleService {
    static int productUnits;
    static int idClient;
    static int idProduct;
    static int initialStock;
    static ArrayList<SaleProductDto> saleProduct;
    static Sale sale;
    @MockBean SaleRepository saleRepository;
    @MockBean ProductRepository productRepository;
    @MockBean ClientRepository clientRepository;
    @MockBean RequestRepository requestRepository;
    @InjectMocks SaleService saleService;

    @BeforeAll
    static void loadData () {
        saleProduct = new ArrayList<>();
        sale = new Sale();
        productUnits = 2;
        idClient = 9;
        idProduct = 2;
        initialStock = 25;
    }


    @Test()
    @DisplayName( "adding new sale")
    public void addingSale(){
        Client client = new Client("juan","segundo");
        Product product = new Product("mayonesa",200.3,initialStock);
        sale.setClient( client );
        double amountSale = product.getPrice() * productUnits;
        sale.setAmount(amountSale);
        Mockito.when( saleRepository.save( sale )).thenReturn( sale );
        Mockito.when( clientRepository.findById(idClient)).thenReturn( Optional.of( client ));
        Mockito.when( productRepository.findById(idProduct)).thenReturn( Optional.of( product ) );
        Mockito.when( productRepository.save(product)).thenReturn(  product );
        Mockito.when( requestRepository.getPurchases(Date.valueOf(LocalDate.now()), idClient))
                .thenReturn( new ArrayList<>());
        saleProduct.add( new SaleProductDto( productUnits, idProduct ));
        RequestSale rs = new RequestSale(9, saleProduct);
        Optional<Sale> expected = saleService.save(rs);
        assertFalse( expected.isEmpty(), "Should save the purchase");
        assertEquals( expected.get().getAmount(), amountSale, "Amount should be: " + amountSale);
        assertEquals( expected.get().getClient().getName(), client.getName(), "Name should be juan");
        assertTrue( product.getStock() < initialStock );
    }

    @Test()
    @DisplayName( "adding wrong product")
    public void addingWrongProduct() {
        // to do
    }



}
