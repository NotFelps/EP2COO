import java.time.LocalDateTime;

public class Disponibilidade {
    private LocalDateTime dataIni;
    private LocalDateTime dataFim;
    
    
    public void setDisponibilidade(LocalDateTime dataIni, LocalDateTime dataFim ){
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        
    }

    public LocalDateTime getDataIni(){
        return dataIni;
    }

    public LocalDateTime getDataFim(){
        return dataFim;
    }
}