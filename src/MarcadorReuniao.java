import java.time.*;
import java.util.*;


public class MarcadorReuniao {
    private LocalDate dataIni;
    private LocalDate dataFim;
    private List<Participante> lista;
    
    

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        dataIni = dataInicial;
        dataFim = dataFinal;
        lista = new LinkedList<>();
   
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

    //função que realiza a busca em profundidade na lista com os participantes
    void prof(Set<Disponibilidade> listaresp, int i, int j, LocalDateTime auxinicio,LocalDateTime auxfim) {
        List<Disponibilidade> l = lista.get(i).getDisp();
        l.get(j).mudarFlag(1);
        int primeiroj = j;
        
        while (j < l.size()) {
            if (lista.get(i+1).getDisp().get(j).getFlag() == 0) {
                if(auxinicio.isBefore(lista.get(i+1).getDisp().get(j).getDataFim()) && auxfim.isAfter(lista.get(i+1).getDisp().get(j).getDataIni())) {
                    if(i==lista.size()-1) {
                        Disponibilidade d = new Disponibilidade();
                        d.setDisponibilidade(auxinicio, auxfim);
                        listaresp.add(d);
                    } else {
                    prof(listaresp, i+1, j, auxinicio, auxfim);
                    }
                }
               
            }
            j++;
        }
        l.get(primeiroj).mudarFlag(2);
    }


    public void mostraSobreposicao(){                         //vai criar a lista com as respostas (horarios possiveis de reuniao)
       
        //mostra todas as disponibilidades possiveis ----- PRONTO -----
        for(Participante p : lista){
            System.out.print("Nome: "+ p.getNome() + " Disponibilidade: ");
            for(Disponibilidade d : p.getDisp()){
                System.out.print(" " + d.getDataIni() + " - " + d.getDataFim() + " ");
            }
            System.out.print("\n");
        }
        ///////////////////////////////////////////////
        //agora é a parte que cria a lista com os horarios possiveis de reuniao
        /* A ideia é realizar uma busca em profundidade entre todos os participantes
        e seus respectivos horários disponíveis, e durante o processor ir criando uma lista 
        com a resposta (horários possíveis de reunião com todos os participantes) */

        Set<Disponibilidade> listaresp;
        listaresp = new HashSet<>();           //lista com a resposta

        LocalDateTime auxinicio = lista.get(0).getDisp().get(0).getDataIni();
        LocalDateTime auxfim = lista.get(0).getDisp().get(0).getDataFim();     //variavel auxiliar de resposta

        prof(listaresp, 0, 0, auxinicio, auxfim);
        

        


        ///////////////////////////////////////////////
    }

    public LocalDate getdataIni(){
        return dataIni;
    }

    public LocalDate getdataFim(){
        return dataFim;
    }

    public List<Participante> getLista(){
        return lista;
    }
}