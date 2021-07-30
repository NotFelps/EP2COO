import java.util.*;

public class Participante {
    private String nome;
    private List<Disponibilidade> disponibilidades = new LinkedList<Disponibilidade>();
    
    public void setPart(String nomePart){
        this.nome = nomePart;
    }

    public void setDisp(Disponibilidade disp){
        disponibilidades.add(disp);        
    }

    public String getNome(){
        return nome;
    }

    public List<Disponibilidade> getDisp(){
        return disponibilidades;
    }
}