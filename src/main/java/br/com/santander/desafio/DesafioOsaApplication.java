package br.com.santander.desafio;

import br.com.santander.desafio.domain.util.DistanceUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DesafioOsaApplication {

    public static void main(String[] args) {SpringApplication.run(DesafioOsaApplication.class, args);
    }

    @Bean
    public DistanceUtil distanceService(){
        return new DistanceUtil();
    }
}
