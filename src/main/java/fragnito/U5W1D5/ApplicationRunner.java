package fragnito.U5W1D5;

import fragnito.U5W1D5.entities.Utente;
import fragnito.U5W1D5.exceptions.ValidationException;
import fragnito.U5W1D5.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
    @Autowired
    private UtenteService utenteService;

    @Override
    public void run(String... args) throws Exception {
        try {
            utenteService.saveUtente(new Utente("IlSurname", "Aldo Baglio", "aldo@gmail.com"));
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
