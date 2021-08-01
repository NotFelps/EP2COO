import java.time.*;
import java.util.*;


public class MarcadorReuniao {
    private LocalDate dataIni;
    private LocalDate dataFim;
    private List<Participante> lista;
    private Set<Disponibilidade> listaresp;
    
    MarcadorReuniao() {
        listaresp = new HashSet<>();
        lista = new LinkedList<>();
    }

    public void addParticipante(String email) {
           
            Participante p = new Participante();
            p.setPart(email);
            lista.add(p);
    }


    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        dataIni = dataInicial;
        dataFim = dataFinal;
   
        for(String participante : listaDeParticipantes){
            addParticipante(participante);
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
    void prof(int i, int j, LocalDateTime auxinicio,LocalDateTime auxfim) {
        List<Disponibilidade> l = lista.get(i).getDisp();
        LocalDateTime auxiniciovelho = auxinicio;
        LocalDateTime auxfimvelho = auxfim;
           
        while (j < lista.get(i+1).getDisp().size()) {

            if (lista.get(i+1).getDisp().get(j).getFlag() == 0) {

                /////////////////////////////////////////////////////

                // Caso haja intersecção entre as disponibilidades

                // Esse if verifica se há intersecção
                if( (auxiniciovelho.isBefore(lista.get(i+1).getDisp().get(j).getDataFim()) || auxiniciovelho.isEqual(lista.get(i+1).getDisp().get(j).getDataFim())) 
                 && (auxfimvelho.isAfter(lista.get(i+1).getDisp().get(j).getDataIni()) || auxfimvelho.isEqual(lista.get(i+1).getDisp().get(j).getDataIni()))) {   

                    //as duas linhas seguintes arrumam os valores de aux com a intersecção entre os valores das duas disponibilidades
                    if(auxiniciovelho.isBefore(lista.get(i+1).getDisp().get(j).getDataIni())) auxinicio = lista.get(i+1).getDisp().get(j).getDataIni();
                    if(auxfimvelho.isAfter(lista.get(i+1).getDisp().get(j).getDataFim())) auxfim = lista.get(i+1).getDisp().get(j).getDataFim(); 

                    if(i==lista.size()-2) {                            //caso esteja no penúltimo participante, coloca o aux na lista de resposta (pois as comparações são com i+1)
                        Disponibilidade d = new Disponibilidade();
                        d.setDisponibilidade(auxinicio, auxfim);
                        listaresp.add(d);
                    } else {                                          //caso os dois valores possuam um ponto em comum, mas ainda não seja o ultimo participante
                        prof(i+1, 0, auxinicio, auxfim);
                    }
                }
                /////////////////////////////////////////////////////
            }
            j++;
        }
        
    }


    public void mostraSobreposicao(){                         //vai criar a lista com as respostas (horarios possiveis de reuniao)
       
        listaresp = new HashSet<>();

        //mostra todas as disponibilidades possiveis ----- PRONTO -----
        for(Participante p : lista){
            System.out.print("Nome: "+ p.getNome() + " Disponibilidade: ");
            for(Disponibilidade d : p.getDisp()){
                System.out.print(" (" + d.getDataIni() + " - " + d.getDataFim() + ") ");
            }
            System.out.print("\n");
        }
        ///////////////////////////////////////////////
        //agora é a parte que cria a lista com os horarios possiveis de reuniao
        /* A ideia é realizar uma busca em profundidade entre todos os participantes
        e seus respectivos horários disponíveis, e durante o processor ir criando uma lista 
        com a resposta (horários possíveis de reunião com todos os participantes) */

        

        
        if(lista.size() > 1) {
            int m = 0;
            while(m < lista.get(0).getDisp().size()) {
                LocalDateTime auxinicio = lista.get(0).getDisp().get(m).getDataIni();
                LocalDateTime auxfim = lista.get(0).getDisp().get(m).getDataFim();     //variavel auxiliar de resposta
                prof(0, 0, auxinicio, auxfim);
                m++;
            }
        }

        if(listaresp.size() != 0) {

            System.out.print(listaresp.size()+" horarios possiveis de reuniao : ");
            for(Disponibilidade d : listaresp) {
                LocalDateTime inicial1 = dataIni.atStartOfDay();
                LocalDateTime final1 = dataFim.atTime(23, 59);

                if( (d.getDataIni().isAfter(inicial1) || d.getDataIni().isEqual(inicial1)) && (d.getDataFim().isBefore(final1) || d.getDataIni().isEqual(final1))) {
                    System.out.print(" ("+d.getDataIni() + " - " + d.getDataFim()+") ");
                }
            }
            System.out.print("\n");
        } else {
            System.out.println("Sem horarios possiveis de reuniao com todos os participantes.");
        }

        


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