package JUnit;

import com.integrador4.dto.ProductRequest;
import com.integrador4.dto.RequestSale;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.Product;
import com.integrador4.entity.Sale;
import com.integrador4.service.ProductService;
import com.integrador4.service.SaleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Optional;
import static org.testng.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest( classes = {SaleService.class,ProductService.class})
public class TestSaleService {

    private static RequestSale rs;
    private static ArrayList<SaleProductDto> saleProduct;
    @Autowired
    private static SaleService saleService;
    @Autowired
    private static ProductService productService;
    private static double amountSale;

    @BeforeAll
    static void loadData() {
        Optional<Product> p = productService.save( new ProductRequest("mayonesa",200.3,25) );
        Optional<Product> p1 = productService.save( new ProductRequest("queso",641.3,50) );
        saleProduct.add( new SaleProductDto( 2, p.get().getIdProduct()));
        saleProduct.add( new SaleProductDto( 3, p1.get().getIdProduct()));
        amountSale = p.get().getPrice() * 2 + p1.get().getPrice() * 3;
    }

    @Test()
    @DisplayName( "adding new sale")
    public void addingSale(){
        rs = new RequestSale( 9, saleProduct );
        Optional<Sale> sale = saleService.save( rs );
        assertTrue( !sale.isEmpty(), "Should save the purchase" );
        assertTrue( sale.get().getAmount() == amountSale, "Amount should be: "+ amountSale );
    }

    @Test()
    @DisplayName( "adding wrong product")
    public void addingWrongProduct() {
        saleProduct.clear();
        Optional<Product> p = productService.save( new ProductRequest("tita", 70.7,50) );
        saleProduct.add( new SaleProductDto( 50, p.get().getIdProduct()) );
        Optional<Sale> sale = saleService.save( new RequestSale(9, saleProduct ) );
        assertTrue( sale.isEmpty(), "Shouldn't save this sale cause has more than 3 units" );
    }

}
