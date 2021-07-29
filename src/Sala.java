import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nome;
    private String observação;
    private int capacidadeMax;
    private String local;
    private List<Reserva> reservas;

    public Sala(String nome, int capacidadeMax, String observação){
        this.nome = nome;
        this.observação = observação;
        this.capacidadeMax = capacidadeMax;
        reservas = new ArrayList<>();
    }

    public Reserva addReserva(Sala s,LocalDateTime ini, LocalDateTime fim){
        Reserva r = new Reserva(ini,fim,s);
        reservas.add(r);
        return r;
    }

    public void removeReserva(Reserva r){
        reservas.remove(r);
    }


    public String getNome(){
        return nome;
    }

    public String getDesc(){
        return observação;
    }

    public int getCapa(){
        return capacidadeMax;
    }

    public List<Reserva> getReservas(){
        return reservas;
    }
}
