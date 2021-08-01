import java.time.*;
import java.util.*;


public class Reuniao {


    //static void reuniaomenu() {
    //
    //}
    public static void main(String[] args) {
       
        Set<String> lista = new HashSet<String>();
        MarcadorReuniao aux = new MarcadorReuniao();

        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while(flag) {
            System.out.print("1 : Agendar reuniao entre\n2 : Inserir participante\n3 : Inserir disponibilidade\n4 : Mostrar relatorio\n0 : Sair do programa\nInsira sua opcao : ");
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
            }
        }
    }

    static void agendaReuniao(MarcadorReuniao aux, Collection<String> lista) {

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

    }


    static void insereParticipante(MarcadorReuniao aux, Collection<String> lista) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Email do participante : ");
        String email = sc.nextLine();

        lista.add(email);
        aux.addParticipante(email);
    }

    static void insereDisponibilidade(MarcadorReuniao aux, Collection<String> lista) {
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

            System.out.print("Data de termino do periodo possivel de reuniao (dd/mn/yyyy hh:minmin) : ");
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
    }


    static void mostraRelatorio(MarcadorReuniao aux, Collection<String> lista) {
        aux.mostraSobreposicao();
    }
}
