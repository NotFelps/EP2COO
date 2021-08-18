import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String nome;
    private String observacao;
    private int capacidadeMax;
    private String local;
    private List<Reserva> reservas;

    Sala(){
        nome = "";
        observacao = "";
        capacidadeMax = 0;
        local = "";
        reservas = new ArrayList<>();
    }

    Sala(String nome, int capacidadeMax, String observacao){
        this.nome = nome;
        this.observacao = observacao;
        this.capacidadeMax = capacidadeMax;
        reservas = new ArrayList<>();
    }

    public void setLocal(String local){
        this.local = local;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setCapacidade(int capacidadeMax){
        this.capacidadeMax = capacidadeMax;
    }

    public void setObservacoes(String observacao){
        this.observacao = observacao;
    }

    public Sala(String nome, int capacidadeMax, String observacao, String local){
        this.nome = nome;
        this.observacao = observacao;
        this.capacidadeMax = capacidadeMax;
        this.local = local;
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

    public String getObservacoes(){
        return observacao;
    }

    public int getCapa(){
        return capacidadeMax;
    }

    public String getLocal(){
        return local;
    }

    public List<Reserva> getReservas(){
        return reservas;
    }
}
