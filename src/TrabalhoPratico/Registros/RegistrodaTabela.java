/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.0
 */
package TrabalhoPratico.Registros;

public class RegistrodaTabela {

    private String lexema;          //EX. temp, 10
    private String token;           //EX. Indent, numero
    private short parteInteira;     //
    private short parteDecimal;     //
    //private boolean tipoInteiro;  // que poderá assumir os valores (1*)tipo-inteiro e (2*)tipo-real
    private byte classeEtipo;       // que poderá assumir os valores (1)classe-var, (2)classe-const, (3)classe-ponto, (4)classe-face, (5)classe-objeto, (6)classe-luz e (7)classe-cor
    private short linhaDeclaracao = -1;
    private short endereco;

    public RegistrodaTabela() {
        lexema = "";
        token = "";
        classeEtipo = 0;
        //tipoInteiro = null;    // Inteiro ou Fração
    }

    //
    public RegistrodaTabela(String tok, String tipo_token, String lex) {
        //this.tipoInteiro = tipo_token;
        this.lexema = lex;
        this.token = tok;
        classeEtipo = 0;
    }

    //valores (-1)tipo-inteiro e (-2)tipo-real
    public String getTipo() {
        if (classeEtipo == 11 || classeEtipo == 12 || classeEtipo == 10) {
            return "tipo-inteiro";
        } else if (classeEtipo == 21 || classeEtipo == 22 || classeEtipo == 20) {
            return "tipo-real";
        } else {
            return "";
        }
    }

    //valores true(tipo-inteiro) e false(tipo-real)
    public void setTipo(boolean tipo) {
        if (tipo) {
            this.classeEtipo =(byte) (10 + (classeEtipo%10));
        } else {
            this.classeEtipo =(byte) (20 + (classeEtipo%10));
        }
    }

    public String getClasse() {
        if (classeEtipo == 1) {
            return "classe-var";
        } else if (classeEtipo == 2) {
            return "classe-const";
        } else if (classeEtipo == 3) {
            return "classe-ponto";
        } else if (classeEtipo == 4) {
            return "classe-face";
        } else if (classeEtipo == 5) {
            return "classe-objeto";
        } else if (classeEtipo == 6) {
            return "classe-luz";
        } else if (classeEtipo == 7) {
            return "classe-cor";
        } else {
            return "";
        }
    }

    public void setClasse(byte clas) {
        this.classeEtipo = clas;
    }

    public String toSting() {
        return "RegistroLexico [token=" + token + ", lexema=" + lexema + ", tipo=" + getTipo() + "]";
    }

    public String getLexema() {
        return lexema;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String novo) {
        token = novo;
    }

    public void setInteiro(int inteiro) {
        parteInteira = (short) inteiro;
    }

    public void setDecimal(int decimal) {
        parteDecimal = (short) decimal;
    }

    public int getInteiro() {
        return parteInteira;
    }

    public int getDecimal() {
        return parteDecimal;
    }

    public void inverterSinal() {
        if (parteInteira > 0) {
            parteInteira *= -1;
        }
    }

    public int getLinhaDaDeclaracao() {
        return linhaDeclaracao;
    }

    public void setLinhaDaDeclaracao(int linha) {
        if (linhaDeclaracao < 0) {
            linhaDeclaracao = (short) linha;
        }
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int end) {
        endereco = (short)end;
    }

}
