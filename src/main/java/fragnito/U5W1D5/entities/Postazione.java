package fragnito.U5W1D5.entities;

import fragnito.U5W1D5.enums.TipoPostazione;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "postazioni")
@Getter
@Setter
@NoArgsConstructor
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String descrizione;

    @Column(name = "tipo_postazione", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipoPostazione;

    @Column(name = "occupanti_max", nullable = false)
    private int maxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;

    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioneList;

    public Postazione(String descrizione, TipoPostazione tipoPostazione, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipoPostazione = tipoPostazione;
        switch (tipoPostazione) {
            case PRIVATO -> this.maxOccupanti = 5;
            case OPENSPACE -> this.maxOccupanti = 50;
            case SALA_RIUNIONI -> this.maxOccupanti = 25;
        }
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        return "Postazione{" +
                "id=" + id +
                ", descrizione='" + descrizione + '\'' +
                ", tipoPostazione=" + tipoPostazione +
                ", maxOccupanti=" + maxOccupanti +
                ", edificio=" + edificio +
                '}';
    }
}
