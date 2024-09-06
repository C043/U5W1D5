package fragnito.U5W1D5;

import com.github.javafaker.Faker;
import fragnito.U5W1D5.entities.Edificio;
import fragnito.U5W1D5.exceptions.ValidationException;
import fragnito.U5W1D5.services.EdificioService;
import fragnito.U5W1D5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EdificioService edificioService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        try {
            edificioService.saveEdificio(new Edificio(faker.company().name(), faker.address().fullAddress(), faker.address().city()));
            edificioService.saveEdificio(new Edificio(faker.company().name(), faker.address().fullAddress(), faker.address().city()));
            edificioService.saveEdificio(new Edificio(faker.company().name(), faker.address().fullAddress(), faker.address().city()));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }


    }
}
