import java.time.*;
import java.util.*;


public class Reuniao {


    static void reuniaomenu(Set<String> lista, MarcadorReuniao aux, Scanner sc) {
        boolean flag = true;

        while(flag) {
            System.out.print("\nMenu de Reuniao\n1 : Agendar reuniao entre\n2 : Inserir participante\n3 : Inserir disponibilidade\n4 : Mostrar relatorio\n0 : Voltar ao menu principal\nInsira sua opcao : ");
            int op = sc.nextInt();
            switch (op) {
                case 1 :
                    agendaReuniao(aux, lista);
                    break;

                case 2 : 
                    insereParticipante(aux, lista);
                    break;

                case 3 :
                    insereDisponibilidade(aux, lista);
                    break;

                case 4 :
                    mostraRelatorio(aux, lista);
                    break;
                
                case 0 :
                    flag = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }

    static void agendaReuniao(MarcadorReuniao aux, Collection<String> lista) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Data de inicio do periodo possivel de reuniao (dd/mm/yyyy) : ");
            LocalDate dataInicial;
            LocalDate dataFinal;

            String datainicial = sc.nextLine();
            String[] datainicialseparada = datainicial.split("/");
            dataInicial = LocalDate.of(Integer.parseInt(datainicialseparada[2]), Integer.parseInt(datainicialseparada[1]), Integer.parseInt(datainicialseparada[0]));

            System.out.print("Data de termino do periodo possivel de reuniao (dd/mm/yyyy) : ");
            String datafinal = sc.nextLine();
            String[] datafinalseparada = datafinal.split("/");
            dataFinal = LocalDate.of(Integer.parseInt(datafinalseparada[2]), Integer.parseInt(datafinalseparada[1]), Integer.parseInt(datafinalseparada[0]));

            aux.marcarReuniaoEntre(dataInicial, dataFinal, lista);
        } catch (Exception e) {
            System.out.println("Data invalida!");
        }
        

    }


    static void insereParticipante(MarcadorReuniao aux, Collection<String> lista) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Email do participante : ");
        String email = sc.nextLine();

        lista.add(email);
        aux.addParticipante(email);
    }

    static void insereDisponibilidade(MarcadorReuniao aux, Collection<String> lista) {
        try {
            Scanner sc = new Scanner(System.in);
        System.out.println("Insira o email do participante : ");
        String email = sc.nextLine();
        if(lista.contains(email)) {

            System.out.print("Data de inicio da disponibilidade (dd/mm/yyyy hh:minmin) : ");
            LocalDateTime dataInicial;
            LocalDateTime dataFinal;
            

            String datainicial = sc.nextLine();
            String[] tempoinicial = datainicial.split(" ");
            String[] datainicialseparada = tempoinicial[0].split("/");
            String[] tempoinicialseparado = tempoinicial[1].split(":");
            LocalDate datainicial1 = LocalDate.of(Integer.parseInt(datainicialseparada[2]), Integer.parseInt(datainicialseparada[1]), Integer.parseInt(datainicialseparada[0]));
            LocalTime tempoinicial1 = LocalTime.of(Integer.parseInt(tempoinicialseparado[0]), Integer.parseInt(tempoinicialseparado[1]));


            dataInicial = LocalDateTime.of(datainicial1, tempoinicial1);

            System.out.print("Data de termino da disponibilidade (dd/mn/yyyy hh:minmin) : ");
            String datafinal = sc.nextLine();
            String[] tempofinal = datafinal.split(" ");
            String[] datafinalseparada = tempofinal[0].split("/");
            String[] tempofinalseparado = tempofinal[1].split(":");
            LocalDate datafinal1 = LocalDate.of(Integer.parseInt(datafinalseparada[2]), Integer.parseInt(datafinalseparada[1]), Integer.parseInt(datafinalseparada[0]));
            LocalTime tempofinal1 = LocalTime.of(Integer.parseInt(tempofinalseparado[0]), Integer.parseInt(tempofinalseparado[1]));

            dataFinal = LocalDateTime.of(datafinal1, tempofinal1);

            aux.indicaDisponibilidadeDe(email, dataInicial, dataFinal);

        } else {
            System.out.println("Participante nao encontrado!");
        }
        } catch (Exception e) {
            System.out.println("Data invalida!");
        }
    }

    static void mostraRelatorio(MarcadorReuniao aux, Collection<String> lista) {
        aux.mostraSobreposicao();
    }

    static void reservamenu(GerenciadorDeSalas aux, Scanner sc) {
        boolean flag = true;

        while(flag) {
            System.out.println("\nMenu Reservas");
            System.out.println("1-Adicionar uma nova Sala");
            System.out.println("2-Remover uma Sala");
            System.out.println("3-Retornar a lista de Salas");
            System.out.println("4-Reserva uma Sala");
            System.out.println("5-Cancela uma Reserva");
            System.out.println("6-Retornar todas as Reservas de uma Sala");
            System.out.println("0-Voltar ao menu principal");
            System.out.print("Digite sua opcao: ");         
            int op = sc.nextInt();
            switch (op) {
                case 1 :
                    adicionaSalaNova(aux,sc);
                    break;

                case 2 :
                    removeSala(aux,sc);
                    break;

                case 3 :
                    listaSala(aux,sc);
                    break;
                case 4 :
                    reservaSala(aux,sc);
                    break;

                case 5 : 
                    cancelaReserva(aux,sc);
                    break;

                case 6 :
                    listaReservasDaSala(aux,sc);
                    break;
                case 0 :
                    flag = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }
    private static void listaReservasDaSala(GerenciadorDeSalas aux,Scanner sc) {
        System.out.println("Digite o nome da Sala: ");
        String nome = sc.next();
        if(aux.buscaSala(nome) == null)
            return;
        aux.imprimeReservasDaSala(nome);
    }

    private static void cancelaReserva(GerenciadorDeSalas aux,Scanner sc) {
        System.out.println("Digite o nome da Sala em que a reserva foi efetuada: ");
        String nome = sc.next();
        sc.nextLine();
        Sala s = aux.buscaSala(nome);
        if(s == null)
            return;

        int i = 1;
            
        for(Reserva r : s.getReservas()) {
            System.out.println(i+"-Data inicial : "+r.getDataIni()+" Data final :  "+r.getDataFim());
            i++;
        }

        System.out.println("Digite o numero da reserva a ser removida : ");
        int aux1 = sc.nextInt();

        Reserva r = s.getReservas().get(aux1-1);
        aux.cancelaReserva(r);
    }

    private static void reservaSala(GerenciadorDeSalas aux,Scanner sc) {
        try {
            System.out.println("Digite o nome da Sala a ser reservada: ");
            String nome = sc.next();
            sc.nextLine();
            if(aux.buscaSala(nome) == null)
                return;
            System.out.println("Data de inicio da reserva (dd/mm/yyyy hh:minmin) : ");
            LocalDateTime dataInicial;
            LocalDateTime dataFinal;

            String datainicial = sc.nextLine();
            String[] tempoinicial = datainicial.split(" ");
            String[] datainicialseparada = tempoinicial[0].split("/");
            String[] tempoinicialseparado = tempoinicial[1].split(":");
            LocalDate datainicial1 = LocalDate.of(Integer.parseInt(datainicialseparada[2]), Integer.parseInt(datainicialseparada[1]), Integer.parseInt(datainicialseparada[0]));
            LocalTime tempoinicial1 = LocalTime.of(Integer.parseInt(tempoinicialseparado[0]), Integer.parseInt(tempoinicialseparado[1]));


            dataInicial = LocalDateTime.of(datainicial1, tempoinicial1);

            System.out.println("Data de termino da reserva (dd/mn/yyyy hh:minmin) : ");
            String datafinal = sc.nextLine();
            String[] tempofinal = datafinal.split(" ");
            String[] datafinalseparada = tempofinal[0].split("/");
            String[] tempofinalseparado = tempofinal[1].split(":");
            LocalDate datafinal1 = LocalDate.of(Integer.parseInt(datafinalseparada[2]), Integer.parseInt(datafinalseparada[1]), Integer.parseInt(datafinalseparada[0]));
            LocalTime tempofinal1 = LocalTime.of(Integer.parseInt(tempofinalseparado[0]), Integer.parseInt(tempofinalseparado[1]));

            dataFinal = LocalDateTime.of(datafinal1, tempofinal1);

            Reserva r = aux.reservaSalaChamada(nome, dataInicial, dataFinal);
            if(r == null)
                System.out.println("A reserva nao pode ser efetuada pois ja existe uma reserva nesse periodo!");
            else
                System.out.println("A reserva da sala " + r.getSala().getNome() +" no periodo de: "+ r.getDataIni() + "-" + r.getDataFim() + " foi efetuada com sucesso");
 
        } catch (Exception e) {
            System.out.println("Um erro ocorreu");
        }
        
    }

    private static void listaSala(GerenciadorDeSalas aux,Scanner sc) {
        List<Sala> lista = aux.listaDeSalas();
        System.out.println("Existem " + lista.size() + " Salas:");
        for (Sala sala : lista) {
            System.out.println("Nome: " + sala.getNome() + " / Capacidade: " + sala.getCapa() + " / Descricao: " + sala.getDesc());
        }
    }

    private static void removeSala(GerenciadorDeSalas aux,Scanner sc) {
        System.out.println("Digite o nome da Sala a ser removida (sem espaco): ");
        String nome = sc.next();
        aux.removeSalaChamada(nome);
    }

    private static void adicionaSalaNova(GerenciadorDeSalas aux,Scanner sc) {
        System.out.println("Digite o nome da Sala (sem espaco): ");
        String nome = sc.next();
        sc.nextLine();
        System.out.println("Digite a capacidade da Sala: ");
        int cap = Integer.parseInt(sc.nextLine());
        //System.out.println("\n");
        System.out.println("Digite a descricao da Sala: ");
        String desc = sc.nextLine();
        aux.adicionaSalaChamada(nome,cap,desc);
    }

    public static void main(String[] args) {
       
        Set<String> lista = new HashSet<String>();
        MarcadorReuniao aux = new MarcadorReuniao();
        GerenciadorDeSalas aux2 = new GerenciadorDeSalas();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println("\nMenu Principal");
            System.out.println("1-Marcar Reuniao");
            System.out.println("2-Reservar Sala");
            System.out.println("0-Sair");
            System.out.print("Digite sua opcao: ");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    reuniaomenu(lista,aux,sc);
                    break;
                case 2:
                    reservamenu(aux2,sc);
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }
        }
    }
}
