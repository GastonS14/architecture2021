package com.integrador4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Integrador4Application {

    public static void main(String[] args) {
        SpringApplication.run(Integrador4Application.class, args);
    }
/*
    @Component
    public class DemoData implements ApplicationRunner {

        @Autowired
        private final ClienteRepo clienteRepo;
        @Autowired
        private final ProductoRepo productoRepo;

        public DemoData(ClienteRepo crepo, ProductoRepo prepo) {
            this.clienteRepo = crepo;
            this.productoRepo = prepo;
        }

        @Override
        public void run(ApplicationArguments args) throws Exception {
            productoRepo.save ( new Producto( "tomate", 259.4, 480 ) );
            productoRepo.save ( new Producto( "lechuga", 760.4, 123 ) );
            productoRepo.save ( new Producto( "nesquik", 40.4, 600 ) );
            productoRepo.save ( new Producto( "fernet", 150.5, 20 ) );
            productoRepo.save ( new Producto( "leche", 85.4, 54 ) );
            productoRepo.save ( new Producto( "yerba", 21.4, 680 ) );
            productoRepo.save ( new Producto( "azucar", 232.4, 230 ) );
            clienteRepo.save( new Cliente("juan","a" ) );
            clienteRepo.save( new Cliente("pedro","b" ) );
            clienteRepo.save( new Cliente("fran","c" ) );
            clienteRepo.save( new Cliente("gaston","d" ) );
            clienteRepo.save( new Cliente("manu","e" ) );
            clienteRepo.save( new Cliente("alberto","f" ) );
            clienteRepo.save( new Cliente("julian","g" ) );
        }
    }
*/





}
