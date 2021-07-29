import java.time.*;
import java.util.*;


public class MarcadorReuniao {
    private LocalDate dataIni;
    private LocalDate dataFim;
    private Set<Participante> lista;


    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        dataIni = dataInicial;
        dataFim = dataFinal;
        lista = new HashSet<>();
        for(String participante : listaDeParticipantes){
            Participante p = new Participante();
            p.setPart(participante);
            lista.add(p);
        }
    }

    public void indicaDisponibilidadeDe(String participante,LocalDateTime inicio,LocalDateTime fim){
        for(Participante participantes: lista){
            if(participante.equals(participantes.getNome())){
                Disponibilidade d = new Disponibilidade();
                d.setDisponibilidade(inicio, fim);
                participantes.setDisp(d);
                break;
            }
        }
    }

    public void mostraSobreposicao(){
        for(Participante p : lista){
            System.out.print("Nome: "+ p.getNome() + " Disponibilidade: ");
            for(Disponibilidade d : p.getDisp()){
                System.out.print(" " + d.getDataIni() + " - " + d.getDataFim() + " ");
            }
            System.out.print("\n");
        }
    }

    public LocalDate getdataIni(){
        return dataIni;
    }

    public LocalDate getdataFim(){
        return dataFim;
    }

    public Set<Participante> getLista(){
        return lista;
    }
}