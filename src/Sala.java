import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nome;
    private String observacao;
    private int capacidadeMax;
    private int local;
    private List<Reserva> reservas;

    public Sala(String nome, int capacidadeMax, String observacao, int local){
        this.nome = nome;
        this.observacao = observacao;
        this.capacidadeMax = capacidadeMax;
        this.local = local;
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
        return observacao;
    }

    public int getCapa(){
        return capacidadeMax;
    }

    public int getLocal(){
        return local;
    }

    public List<Reserva> getReservas(){
        return reservas;
    }
}
