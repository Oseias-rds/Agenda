package projetoagenda;

//import java.awt.List;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Agenda {
    //array de contatos, a lista contatos vai receber ate 99

    private Contato[] contatos = new Contato[100];

    public Contato[] getContatos() {
        return contatos;
    }

    public void setContatos(Contato[] contatos) {
        this.contatos = contatos;
    }

    public void mostrarContatos() {
        for (int i = 0; i < contatos.length; i++) {
            // != se contato da posição i for diferente de null printe na tela
            if (contatos[i] != null) {
                System.out.println("----------------------------" + i);//estilo
                System.out.println("nome: " + contatos[i].getNome());
                System.out.println("sobrenome: " + contatos[i].getSobrenome());
                System.out.println("endereço: " + contatos[i].getEndereco());
                System.out.println("email: " + contatos[i].getEmail());
                System.out.println("numero: " + contatos[i].getnumero());
            }

        }
    }
    // adicionei esse metod para poder usar na listagem das tabelas
    public List<Contato> copiaContatos() {
        List<Contato> cont = new ArrayList<>();
        for (int i = 0; i < contatos.length; i++) {
            if(contatos[i] != null){
                Contato contatocopia = new Contato();
                contatocopia.setNome(contatos[i].getNome());
                contatocopia.setSobrenome(contatos[i].getSobrenome());
                contatocopia.setEndereco(contatos[i].getEndereco());
                contatocopia.setEmail(contatos[i].getEmail());
                contatocopia.setNumero(contatos[i].getnumero());
                cont.add(contatocopia);
            }
            
        }
        
        return cont;
    }
    public void registraPessoa(String nome, String sobrenome, String endereco,
            String email, String numero) {
        Contato c1 = new Contato();

        //preencher o objeto c1 que esta vazio com os dados que recebe como parâmetro
        c1.setNome(nome);
        c1.setSobrenome(sobrenome);
        c1.setEndereco(endereco);
        c1.setEmail(email);
        c1.setNumero(numero);

        // guardando o novo contato em uma posição no Array
        // contatos[0] = c1; ira sempre preencher a posição 0, apagando a anterior se houvesse
        // i refere-se a indice 
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] == null) {
                contatos[i] = c1;
                break;
            }
        }
        this.salvarArquivo(this.copiaContatos());

    }

    public void editarNome(String nome, String novoNome) {

        Contato contatoParaEditar = pegarContato(nome);
        contatoParaEditar.setNome(novoNome);
        this.salvarArquivo(this.copiaContatos());
    }

    public void excluir(String nome) {

        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] != null) {
                if (contatos[i].getNome().equals(nome)) {
                    contatos[i] = null;
                }
            }

        }
        this.salvarArquivo(this.copiaContatos());

    }
//ess metodo só seria necessario casso não tivesse interface gráfica
    public void verContato(String nome) {
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i]!= null) {
                if (contatos[i].getNome() == nome) {
                    System.out.println("nome do contato: " + contatos[i].getNome());

                }
            }
        }
    }
//esse metodo é utilizado em editar contato
    private Contato pegarContato(String nome) {

        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] != null) {
                if (contatos[i].getNome().equals(nome)) {// mudei o == para um equals pois nao estava rodando na tela grafica
                    return contatos[i];
                }
            }
        }
        return null;
    }
    public void salvarArquivo(List a){
        File arquivo = new File("lista de contatos.txt");
        //vai receber uma instancia
        //try --"tente"
        try {
            //api, manipular documentos
            FileOutputStream fos = new FileOutputStream(arquivo);
            //variavel 
            ObjectOutputStream escritor = new ObjectOutputStream(fos);
            escritor.writeObject(a);
         
        
            
            
            
            
         //catch captura erros que podem acontecer nas operações dentro do try 
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
           ex.printStackTrace();        }
    
    }
    //<> inferencia de diamante (a lista vai ser de um tipo contato por isso utiliza < e >)
    public ArrayList<Contato> lerArquivo(){
        
        try {
            File arquivo = new File("lista de contatos.txt");
            arquivo.createNewFile();
            FileInputStream fis = new FileInputStream(arquivo);
            
            ObjectInputStream leitor = new ObjectInputStream(fis);
            
            return (ArrayList<Contato>) leitor.readObject();//retorna um objeto jenerico 
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
            
            
        return null;
    }
    
    
    

}
