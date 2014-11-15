/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.0
 */
package TrabalhoPratico.AnSemantico;

public class Rotulo {
    private String nome;
    private int PC;
    private int endereco;
    
    public Rotulo(String nome, int PC, int endereco){
        this.nome = nome;
        this.PC = PC;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPC() {
        return PC;
    }

    public void setPC(int PC) {
        this.PC = PC;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }
    
    public int end(){
        return endereco;
    }
}
