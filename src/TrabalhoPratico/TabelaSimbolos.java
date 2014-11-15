/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.17
 * @status updated to 2.0
 */
package TrabalhoPratico;

import TrabalhoPratico.Registros.*;
import java.util.HashMap;

public class TabelaSimbolos {

    static int tam = 50;
    static HashMap<Integer, RegistrodaTabela> tabela;
    private int codigo;

    public TabelaSimbolos() {
        tabela = new HashMap<Integer, RegistrodaTabela>();

        inserir(0, "ERRO");
        inserir(1, "face");
        inserir(2, "object");
        inserir(3, "color");
        inserir(4, "light");
        inserir(5, "var");
        inserir(6, "const");
        inserir(7, "int");
        inserir(8, "real");
        inserir(9, "scale");
        inserir(10, "pause");
        inserir(11, "rottrans");
        inserir(12, "while");
        inserir(13, "if");
        inserir(14, "then");
        inserir(15, "else");
        inserir(16, "point");
        inserir(17, "&&", "and");
        inserir(18, "||", "or");
        inserir(19, "==", "testeLogico");
        inserir(20, "=", "atribuicao");
        inserir(21, "(", "abreParentese");
        inserir(22, ")", "fechaParentese");
        inserir(23, ",", "virgula");
        inserir(24, "+", "soma");
        inserir(25, "-", "subtracao");
        inserir(26, "*", "multiplicacao");
        inserir(27, "<", "menor");
        inserir(28, ">", "maior");
        inserir(29, "!=", "diferente");
        inserir(30, ">=", "maiorIgual");
        inserir(31, "<=", "menorIgual");
        inserir(32, "/", "divisao");
        inserir(33, "!", "not");
        inserir(34, "begin");
        inserir(35, "end");
        inserir(36, ";", "pontoVirgula");

    }

    //Pesquisa e retorna o local de um Lexema da Tabela
    public int pesquisa(String lex) {
        int reserv = 0;

        for (int cod = codigoHash(lex); cod < codigo || reserv < 36; cod += tam, reserv++) {
            if (tabela.get(cod) != null) {
                if (lex.equals(tabela.get(cod).getLexema())) {
                    return cod;
                }
            }
            if (tabela.get(reserv) != null) {
                if (lex.equals(tabela.get(reserv).getLexema())) {
                    return reserv;
                }
            }
        }
        return -1;
    }

    //retorna verdadeiro se Lexema existir na tabela
    public boolean pesquisaKey(String lex) {
        int cod = codigoHash(lex);
        return tabela.containsKey(cod);  //O método containsKey(key) é usado para testar se o objeto é uma chave na tabela hash.
    }

    public void inserir(int NumToken, String lex) {
        inserir(NumToken, lex, lex);
    }

    // Insere um Lexema na Tabela
    public void inserir(int NumToken, String lex, String tok) {
        int cod = 0;
        RegistrodaTabela temp = new RegistrodaTabela(tok, null, lex);
        if (NumToken < 0) {
            cod = codigoHash(lex);
            if (!(pesquisaKey(lex))) {
                tabela.put(cod, temp);
            } else {
                while (tabela.containsKey(cod)) {
                    cod += tam;
                }
                tabela.put(cod, temp);
            }
            if (codigo < cod) {
                codigo = cod;
            }
        } else {
            while (tabela.containsKey(NumToken)) {
                NumToken += tam;
            }
            tabela.put(NumToken, temp);
            if (codigo < cod) {
                codigo = cod;
            }
        }
    }

    // Insere um Lexema na Tabela
    public void inserir(RegistrodaTabela novo) {
        int cod;
        cod = codigoHash(novo.getLexema());
        if (!(pesquisaKey(novo.getLexema()))) {
            while (tabela.containsKey(cod)) {
                cod += tam;
            }
            tabela.put(cod, novo);
        } else {
            // Lexema ja existe na tabela
        }
        if (codigo < cod) {
            codigo = cod;
        }

    }

    //Imprime todos os Lexema da tabela para Teste
    public void testeIpmprimir() {

        for (int key : tabela.keySet()) {
            System.out.println("Val:  " + key + " " + tabela.get(key).getLexema() + "\t- " + tabela.get(key).getToken());
            // System.out.println("Cod: " + key*2 + " " + tabela.containsKey(key*2));
        }

    }

    //Retorna nosso codigo Hash baseado no primeiro e ultimo char da palavra
    public int codigoHash(String palavra) {
        int cod = -65;
        cod += palavra.charAt(0) + palavra.charAt(palavra.length() - 1);

        return cod % tam;
    }

    //Retorna o Lexema apartir do endereço
    public String lexema(int key) {
        return tabela.get(key).getLexema();
    }

    //Retorna o Lexema apartir do endereço
    public String getToken(int key) {
        return tabela.get(key).getToken();
    }

    //Retorna o Inteiro apartir do endereço
    public int getInteiro(int key) {
        return tabela.get(key).getInteiro();
    }

    //Retorna o Decimal apartir do endereço
    public int getDecimal(int key) {
        return tabela.get(key).getDecimal();
    }

    //Altera o Lexema apartir do endereço
    public void setToken(int key, String novo) {
        tabela.get(key).setToken(novo);
    }

    //
    public void setInteiro(int key, int num) {
        tabela.get(key).setInteiro(num);
    }

    //
    public void setDecimal(int key, int num) {
        tabela.get(key).setDecimal(num);
    }

    //Retorna o Decimal apartir do endereço
    public void inverterSinal(int key) {
        tabela.get(key).inverterSinal();
    }

    public String getTipo(int key) {
        return tabela.get(key).getTipo();
    }

    public void setTipo(int key, boolean isInteiro) {
        tabela.get(key).setTipo(isInteiro);
    }

    public String getClasse(int key) {
        return tabela.get(key).getClasse();
    }

    public void setClasse(int key, int classe) {
        tabela.get(key).setClasse((byte) classe);
    }
    
    public int getLinhaDeclaracao(int key) {
        return tabela.get(key).getLinhaDaDeclaracao();
    }
    
    public void setLinhaDeclaracao(int key, int linha) {
        tabela.get(key).setLinhaDaDeclaracao(linha);
    }

    public int getEndereco(int key) {
        return tabela.get(key).getEndereco();
    }

    public void setEndereco(int key, int endMen) {
        tabela.get(key).setEndereco(endMen);
    }

}
