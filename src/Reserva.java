import java.time.LocalDateTime;

public class Reserva {
    private LocalDateTime dataIni;
    private LocalDateTime dataFim;
    private String nomeSala;

    public Reserva(LocalDateTime dataIni, LocalDateTime dataFim, String nomeSala){
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.nomeSala = nomeSala;
    }

    public LocalDateTime getDataIni(){
        return dataIni;
    }

    public LocalDateTime getDataFim(){
        return dataFim;
    }

    public String getNomeSala(){
        return nomeSala;
    }
}
