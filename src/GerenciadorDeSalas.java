import java.util.*;
import java.time.*;

public class GerenciadorDeSalas {
    
    List<Sala> salas = new ArrayList<>(); 
    Map<Reserva,Sala> mapa = new HashMap<>();
 

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        Sala s = new Sala(nome,capacidadeMaxima,descricao,mapa.size()+1);
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
        try{
            Reserva r = null;
            for(Reserva reservas : reservasParaSala(nomeDaSala)){
                if((reservas.getDataFim().isAfter(dataInicial) && reservas.getDataIni().isBefore(dataInicial)) || (reservas.getDataIni().isBefore(dataFinal) && reservas.getDataFim().isAfter(dataFinal)) 
                || (reservas.getDataIni().isEqual(dataInicial)) || (reservas.getDataFim().isEqual(dataFinal))){
                    return r;
                }
            }
            Sala s = buscaSala(nomeDaSala);
            r = s.addReserva(s,dataInicial, dataFinal);
            mapa.put(r, s);
            return r;
        }
        catch(Exception e){
            System.out.println("A reserva não pode ser efetuada pois: " + e);
            return null;
        }
        
    }

    public void cancelaReserva(Reserva cancelada) {
       mapa.remove(cancelada);
       Sala s = cancelada.getSala();
       s.removeReserva(cancelada);
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) {
        Sala s = buscaSala(nomeSala);
        return s.getReservas();
    }

    public void imprimeReservasDaSala(String nomeSala) {
        Collection<Reserva> reservas = reservasParaSala(nomeSala);
        System.out.println("Existem "+reservas.size()+" reservas para a sala "+nomeSala);
        for(Reserva r : reservas){
            System.out.println("Data de Inicio: " + r.getDataIni()+ " / Data Final: " + r.getDataFim());
        }
    }

    public Sala buscaSala(String nomeSala){
        for(Sala s : salas){
            if(s.getNome().equals(nomeSala))
               return s; 
        }
        return null;
    }
}
