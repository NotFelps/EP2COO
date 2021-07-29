import java.time.*;
import java.util.*;


public class Reuniao {

    public static void main(String[] args) {
        LocalDate dataInicial = LocalDate.of(2019, Month.APRIL, 22);
        LocalDate dataFinal = LocalDate.of(2019, Month.JUNE, 22);
        Set<String> lista = new HashSet<String>();


        lista.add("Juscilei");
        lista.add("Lima");
        lista.add("Senna");
        lista.add("Omo");

        MarcadorReuniao reuniao = new MarcadorReuniao();
        reuniao.marcarReuniaoEntre(dataInicial, dataFinal, lista);


        LocalDateTime ini1 = LocalDateTime.of(2019, Month.APRIL, 30, 22, 32);
        LocalDateTime ini2 = LocalDateTime.of(2019, Month.MAY, 10, 12, 45);
        LocalDateTime ini3 = LocalDateTime.of(2019, Month.MAY, 29, 18, 9);
        LocalDateTime ini4 = LocalDateTime.of(2019, Month.JUNE, 15, 15, 15);
        LocalDateTime fim1 = LocalDateTime.of(2019, Month.JUNE, 21, 15, 45);
        LocalDateTime fim2 = LocalDateTime.of(2019, Month.JUNE, 20, 20, 20);
        LocalDateTime fim3 = LocalDateTime.of(2019, Month.JUNE, 17, 15, 12);
        LocalDateTime fim4 = LocalDateTime.of(2019, Month.JUNE, 18, 20, 20);

        reuniao.indicaDisponibilidadeDe("Juscilei", ini1, fim1);
        reuniao.indicaDisponibilidadeDe("Lima", ini2, fim2);
        reuniao.indicaDisponibilidadeDe("Senna", ini3, fim3);
        reuniao.indicaDisponibilidadeDe("Omo", ini4, fim4);

        reuniao.mostraSobreposicao();
    }

}
