package com.integrador4;

import com.integrador4.dto.RequestSale;
import com.integrador4.dto.SaleProductDto;
import com.integrador4.entity.Client;
import com.integrador4.entity.Product;
import com.integrador4.repository.ClientRepository;
import com.integrador4.repository.ProductRepository;
import com.integrador4.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@SpringBootApplication
public class Integrador4Application {

    public static void main(String[] args) {
        SpringApplication.run(Integrador4Application.class, args);
    }

    @Component
    public class DemoData implements ApplicationRunner {

        @Autowired
        private final ClientRepository clienteRepo;
        @Autowired
        private final ProductRepository productoRepo;
        @Autowired private SaleService saleService;

        public DemoData( ClientRepository crepo, ProductRepository prepo ) {
            this.clienteRepo = crepo;
            this.productoRepo = prepo;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            Product p = productoRepo.save ( new Product( "tomate", 259.4, 480 ) );
            Product p1 = productoRepo.save ( new Product( "lechuga", 760.4, 123 ) );
            Product p2 = productoRepo.save ( new Product( "nesquik", 40.4, 600 ) );
            Product p3 = productoRepo.save ( new Product( "fernet", 150.5, 20 ) );
            productoRepo.save ( new Product( "leche", 85.4, 54 ) );
            productoRepo.save ( new Product( "yerba", 21.4, 680 ) );
            productoRepo.save ( new Product( "azucar", 232.4, 230 ) );
            Client c = clienteRepo.save( new Client("juan","a" ) );
            Client c1 =clienteRepo.save( new Client("pedro","b" ) );
            Client c2 =clienteRepo.save( new Client("fran","c" ) );
            clienteRepo.save( new Client("gaston","d" ) );
            clienteRepo.save( new Client("manu","e" ) );
            clienteRepo.save( new Client("alberto","f" ) );
            clienteRepo.save( new Client("julian","g" ) );
            ArrayList<SaleProductDto> aux = new ArrayList<>();
            aux.add( new SaleProductDto(2, p.getIdProduct()) );
            aux.add( new SaleProductDto(3, p1.getIdProduct()) );
            saleService.save( new RequestSale( c.getId(), aux ) );
            aux.add( new SaleProductDto(3, p2.getIdProduct()) );
            saleService.save( new RequestSale( c1.getId(), aux ));
            aux.add( new SaleProductDto(3, p3.getIdProduct()) );
            saleService.save( new RequestSale( c2.getId(), aux ) );
        }
    }


}
