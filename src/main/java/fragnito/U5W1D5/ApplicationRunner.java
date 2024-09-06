package fragnito.U5W1D5;

import com.github.javafaker.Faker;
import fragnito.U5W1D5.entities.Prenotazione;
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

import java.time.LocalDate;

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
            utenteService.saveUtente(new Utente("Ajeje", "Aldo Baglio", "ajeje@gmail.com"));
            utenteService.saveUtente(new Utente("Ajeje", "Aldo Baglio", "aiaiai@gmail.com"));
            prenotazioneService.savePrenotazione(new Prenotazione(LocalDate.now().plusDays(1), utenteService.findUtenteById(1L),
                    postazioneService.findPostazioneById(8L)));
            prenotazioneService.savePrenotazione(new Prenotazione(LocalDate.now().plusDays(1), utenteService.findUtenteById(2L),
                    postazioneService.findPostazioneById(8L)));
            prenotazioneService.savePrenotazione(new Prenotazione(LocalDate.now().plusDays(1), utenteService.findUtenteById(3L),
                    postazioneService.findPostazioneById(8L)));
            prenotazioneService.savePrenotazione(new Prenotazione(LocalDate.now().plusDays(1), utenteService.findUtenteById(4L),
                    postazioneService.findPostazioneById(8L)));
            prenotazioneService.savePrenotazione(new Prenotazione(LocalDate.now().plusDays(1), utenteService.findUtenteById(5L),
                    postazioneService.findPostazioneById(8L)));
            prenotazioneService.savePrenotazione(new Prenotazione(LocalDate.now().plusDays(1), utenteService.findUtenteById(6L),
                    postazioneService.findPostazioneById(8L)));
        } catch (ValidationException | NotFoundException e) {
            System.out.println(e.getMessage());
        }

        postazioneService.filterByTipoPostazioneAndCitta(TipoPostazione.OPENSPACE, "Milano").forEach(System.out::println);

    }
}
