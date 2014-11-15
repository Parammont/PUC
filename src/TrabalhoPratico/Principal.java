/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico;

import TrabalhoPratico.AnSintatico.Iniciar;
import java.io.IOException;


public class Principal {

    public static TabelaSimbolos tb;
    public static LeituraArquivo arquivo;
    public static Iniciar parser;
    public static boolean parar;
    public static boolean fimArquivo;

    public static void main(String[] args) throws IOException {
        parar = false;
        tb = new TabelaSimbolos();
        arquivo = new LeituraArquivo();

        //teste da Tabela de Simbolo
        //arquivo.testeTabela();
//        System.out.println("TABELA DE SIMBOLOS ");
//        tb.testeIpmprimir();

        parser = new Iniciar();
        parser.executar();

    }

}
