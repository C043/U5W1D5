package fragnito.U5W1D5;

import com.github.javafaker.Faker;
import fragnito.U5W1D5.entities.Utente;
import fragnito.U5W1D5.enums.TipoPostazione;
import fragnito.U5W1D5.exceptions.NotFoundException;
import fragnito.U5W1D5.exceptions.ValidationException;
import fragnito.U5W1D5.services.EdificioService;
import fragnito.U5W1D5.services.PostazioneService;
import fragnito.U5W1D5.services.PrenotazioneService;
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

    @Autowired
    private PostazioneService postazioneService;

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        try {
            utenteService.findUtenteByIdAndUpdate(1L, new Utente("Pdor", "Aldo Baglio", "aldo@gmail.com"));
        } catch (ValidationException | NotFoundException e) {
            System.out.println(e.getMessage());
        }

        postazioneService.filterByTipoPostazioneAndCitta(TipoPostazione.OPENSPACE, "Milano").forEach(System.out::println);

    }
}
