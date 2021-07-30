import java.time.LocalDateTime;

public class Disponibilidade {
    private LocalDateTime dataIni;
    private LocalDateTime dataFim;
    private int flag;


    
    
    public void setDisponibilidade(LocalDateTime dataIni, LocalDateTime dataFim ){
        this.dataIni = dataIni;
        this.dataFim = dataFim;
        this.flag = 0;
    }

    public void mudarFlag(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    } 

    public LocalDateTime getDataIni(){
        return dataIni;
    }

    public LocalDateTime getDataFim(){
        return dataFim;
    }
}