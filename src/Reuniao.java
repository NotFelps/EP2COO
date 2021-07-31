import java.time.*;
import java.util.*;


public class Reuniao {

    public static void main(String[] args) {
        LocalDate dataInicial = LocalDate.of(2019, Month.APRIL, 10);
        LocalDate dataFinal = LocalDate.of(2019, Month.APRIL, 30);
        Set<String> lista = new HashSet<String>();


        lista.add("Juscilei");
        lista.add("Lima");
        lista.add("Senna");
        //lista.add("Omo");

        MarcadorReuniao reuniao = new MarcadorReuniao();
        reuniao.marcarReuniaoEntre(dataInicial, dataFinal, lista);


        LocalDateTime ini1 = LocalDateTime.of(2019, Month.APRIL, 15, 10, 00);
        LocalDateTime ini2 = LocalDateTime.of(2019, Month.APRIL, 14, 10, 00);
        LocalDateTime ini3 = LocalDateTime.of(2019, Month.APRIL, 11, 10, 00);
        LocalDateTime ini4 = LocalDateTime.of(2019, Month.APRIL, 22, 10, 00);
        LocalDateTime fim1 = LocalDateTime.of(2019, Month.APRIL, 20, 10, 00);
        LocalDateTime fim2 = LocalDateTime.of(2019, Month.APRIL, 25, 10, 00);
        LocalDateTime fim3 = LocalDateTime.of(2019, Month.APRIL, 30, 10, 00);
        LocalDateTime fim4 = LocalDateTime.of(2019, Month.APRIL, 30, 10, 00);

        reuniao.indicaDisponibilidadeDe("Juscilei", ini1, fim1);
        reuniao.indicaDisponibilidadeDe("Juscilei", ini4, fim4);
        reuniao.indicaDisponibilidadeDe("Lima", ini2, fim2);
        reuniao.indicaDisponibilidadeDe("Senna", ini3, fim3);
        //reuniao.indicaDisponibilidadeDe("Omo", ini4, fim4);
        
        reuniao.mostraSobreposicao();
    }

}
