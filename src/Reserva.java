import java.time.LocalDateTime;

public class Reserva {
    private LocalDateTime dataIni;
    private LocalDateTime dataFim;
    private Sala sala;

    public Reserva(LocalDateTime dataIni, LocalDateTime dataFim, Sala sala){
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.sala = sala;
    }

    public LocalDateTime getInicio(){
        return dataIni;
    }

    public LocalDateTime getFim(){
        return dataFim;
    }

    public Sala getSala(){
        return sala;
    }
}
