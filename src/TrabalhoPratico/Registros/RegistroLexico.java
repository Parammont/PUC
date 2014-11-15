/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.17
 * @status updated to 2.0
 */
package TrabalhoPratico.Registros;

import TrabalhoPratico.Principal;

public class RegistroLexico {//extends Registro{

    private int linha;      //linha que foi encontrado o token
    private int endereco;   // Endereço na tabela Hash
    //private String tipo;    // Inteiro ou Fração

    public RegistroLexico() {
        //lexema = "";
        //token = "";
        //tipo = null;    // Inteiro ou Fração
        endereco = -1;
    }

/*    //
    public RegistroLexico(String tipo_token, int end) {
        this.tipo = tipo_token;
        this.endereco = end;
    }*/

    public String getLexema() {
            return Principal.tb.lexema(endereco);
    }

    public String getToken() {
        if (endereco >=0)    {
            return Principal.tb.getToken(endereco);
        } else {
            return "";
        }
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int end) {
        this.endereco = end;
    }

    public int getEndMen() {
        return Principal.tb.getEndereco(endereco);
    }

    public void setEndMen(int endMen) {
        Principal.tb.setEndereco(endereco, endMen);
    }

    public String getTipo() {
        return Principal.tb.getTipo(endereco);
    }

    public void setTipo(boolean isTipoInteiro) {
        Principal.tb.setTipo(endereco, isTipoInteiro);
    }

    public void setClasseVar() {
        Principal.tb.setClasse(endereco, 1);
    }

    public void setClasseConst() {
        Principal.tb.setClasse(endereco, 2);
    }

    public void setClassePonto() {
        Principal.tb.setClasse(endereco, 3);
    }

    public void setClasseFace() {
        Principal.tb.setClasse(endereco, 4);
    }

    public void setClasseObjeto() {
        Principal.tb.setClasse(endereco, 5);
    }

    public void setClasseLuz() {
        Principal.tb.setClasse(endereco, 6);
    }

    public void setClasseCor() {
        Principal.tb.setClasse(endereco, 7);
    }
    
    public String getClasse() {
        return Principal.tb.getClasse(endereco);
    }
    
    public int getInteiro () {
        if (endereco >=0)    {
            return Principal.tb.getInteiro(endereco);
        }
        return -1;
    }
    
    public int getDecimal () {
        if (endereco >=0)    {
            return Principal.tb.getDecimal(endereco);
        } else {
            return -1;
        } 
    }
    
    public void inverterSinal () {
        Principal.tb.inverterSinal(endereco);
    }

    public String toSting() {
        return "RegistroLexico [linah=" + linha + ", token=" + getToken() + ", lexema=" + getLexema() + ", endereço=" + endereco + "]";
    }

    public void clone(ApontadorRegistro rl) {
        this.linha = rl.getLinha();
        this.endereco = rl.getEndereco();
        //this.tipo = rl.getTipo();
    }

    public void clone(RegistroLexico rl) {
        this.linha = rl.getLinha();
        this.endereco = rl.getEndereco();
        //this.tipo = rl.getTipo();
    }
    
    //A linha em que foi declarado
    public int getDeclaracao () {
        return Principal.tb.getLinhaDeclaracao(endereco);
    }
    
    public void setDeclaracao () {
        Principal.tb.setLinhaDeclaracao(endereco, linha);
    }

}
