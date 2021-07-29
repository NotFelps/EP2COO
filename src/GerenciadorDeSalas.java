import java.util.*;
import java.time.*;

public class GerenciadorDeSalas {
    
    List<Sala> salas = new ArrayList<>(); 
 

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        Sala s = new Sala(nome,capacidadeMaxima,descricao);
        adicionaSala(s);
    }

    public void removeSalaChamada(String nomeDaSala) {
        Sala s = buscaSala(nomeDaSala);
        salas.remove(s);
    }

    public List<Sala> listaDeSalas() {
        return salas;
    }

    public void adicionaSala(Sala novaSala) {
        salas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        Reserva r = null;
        Sala s = buscaSala(nomeDaSala);
        r = s.addReserva(nomeDaSala,dataInicial, dataFinal);
        return r;
    }

    public void cancelaReserva(Reserva cancelada) {
       
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) {
        Sala s = buscaSala(nomeSala);
        return s.getReservas();
    }

    public void imprimeReservasDaSala(String nomeSala) {
        Collection<Reserva> reservas = reservasParaSala(nomeSala);
        for(Reserva r : reservas){
            System.out.println("Data de Inicio:" + r.getDataIni()+ " Data Final: " + r.getDataFim());
        }
    }

    private Sala buscaSala(String nomeSala){
        for(Sala s : salas){
            if(s.getNome().equals(nomeSala))
               return s; 
        }
        return null;
    }
}
