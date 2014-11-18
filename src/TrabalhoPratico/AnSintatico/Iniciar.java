/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico.AnSintatico;

import TrabalhoPratico.AnSemantico.Instrucoes;
import TrabalhoPratico.Principal;
import TrabalhoPratico.Registros.ApontadorRegistro;
import static TrabalhoPratico.Registros.ApontadorRegistro.getApontador;

public class Iniciar {

    private static ApontadorRegistro rl; //, rlAntigo;

    public Iniciar() {
        rl = getApontador();
        //rlAntigo = new Registro();
    }

    public void executar() {
        Principal.arquivo.Proxima();
        double i = 0;
        //do {
        S();
        //} while (rl != null && !(Principal.parar));

        if (Principal.fimArquivo) {
            System.out.println(rl.getLinha() + ": Fim de Arquivo nao esperado ");
        } else if (!(Principal.parar)) {
            System.out.println("Compilação concluida.");
            Principal.arquivo.fecharArquivo();
        }
    }

    private void S() {
        new Declaracao();
        Principal.arquivo.gravarAsm("************************** Corpo Programa ******");
        new BlocoComando();

        Principal.arquivo.gravarAsm("\tHLT");
        Principal.arquivo.gravarExe(20);
    }
}
