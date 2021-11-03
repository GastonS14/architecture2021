package com.integrador4;

import com.integrador4.entity.Client;
import com.integrador4.entity.Product;
import com.integrador4.repository.ClientRepository;
import com.integrador4.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Integrador4Application {

    public static void main(String[] args) {
        SpringApplication.run(Integrador4Application.class, args);
    }
/*
    @Component
    public class DemoData implements ApplicationRunner {

        @Autowired
        private final ClientRepo clienteRepo;
        @Autowired
        private final ProductRepo productoRepo;

        public DemoData(ClientRepo crepo, ProductRepo prepo) {
            this.clienteRepo = crepo;
            this.productoRepo = prepo;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            productoRepo.save ( new Product( "tomate", 259.4, 480 ) );
            productoRepo.save ( new Product( "lechuga", 760.4, 123 ) );
            productoRepo.save ( new Product( "nesquik", 40.4, 600 ) );
            productoRepo.save ( new Product( "fernet", 150.5, 20 ) );
            productoRepo.save ( new Product( "leche", 85.4, 54 ) );
            productoRepo.save ( new Product( "yerba", 21.4, 680 ) );
            productoRepo.save ( new Product( "azucar", 232.4, 230 ) );
            clienteRepo.save( new Client("juan","a" ) );
            clienteRepo.save( new Client("pedro","b" ) );
            clienteRepo.save( new Client("fran","c" ) );
            clienteRepo.save( new Client("gaston","d" ) );
            clienteRepo.save( new Client("manu","e" ) );
            clienteRepo.save( new Client("alberto","f" ) );
            clienteRepo.save( new Client("julian","g" ) );
        }
    }

 */






}
