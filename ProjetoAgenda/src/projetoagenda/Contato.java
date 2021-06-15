package projetoagenda;

import java.io.Serializable;

//Serializable ele permite que algum objeto possa guardar as informações em algum arquivo
public class Contato implements Serializable {
    private String nome;
    private String sobrenome;
    private String endereco;
    private String email;
    private String numero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getnumero(){
        return numero;
    }
    public void setNumero(String n){
     this.numero = n;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
}
