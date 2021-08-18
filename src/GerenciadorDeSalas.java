import java.util.*;
import java.time.*;

public class GerenciadorDeSalas {
    
    List<Sala> salas = new ArrayList<>(); 
    Map<Reserva,Sala> mapa = new HashMap<>();
 

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        Sala s = new Sala(nome,capacidadeMaxima,descricao);
        adicionaSala(s);
    }

    public void removeSalaChamada(String nomeDaSala) {
        Sala s = buscaSala(nomeDaSala);
        if(s != null){
            salas.remove(s);
        }
        else{
            System.out.println("Sala inexistente");
        }
        
    }

    public List<Sala> listaDeSalas() {
        return salas;
    }

    public void adicionaSala(Sala novaSala) {
        Sala s = buscaSala(novaSala.getNome());

        if(s == null)
            salas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        try{
            Reserva r = null;
            if(dataInicial.isAfter(dataFinal))
                return r;
                
            Sala s = buscaSala(nomeDaSala);
            if(s != null){
                
                for(Reserva reservas : reservasParaSala(nomeDaSala)){
                    if((reservas.getFim().isAfter(dataInicial) && reservas.getInicio().isBefore(dataInicial)) || (reservas.getInicio().isBefore(dataFinal) && reservas.getFim().isAfter(dataFinal)) 
                    || (reservas.getInicio().isEqual(dataInicial)) || (reservas.getFim().isEqual(dataFinal))){
                        return r;
                    }
                }
               
                r = s.addReserva(s,dataInicial, dataFinal);
                mapa.put(r, s);
                return r;
            }
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
            System.out.println("Data de Inicio: " + r.getInicio()+ " / Data Final: " + r.getFim());
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
