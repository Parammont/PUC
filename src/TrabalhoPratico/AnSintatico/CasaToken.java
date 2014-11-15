/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico.AnSintatico;

import TrabalhoPratico.AnSemantico.*;
import TrabalhoPratico.LeituraArquivo;
import TrabalhoPratico.Principal;
//import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;

public class CasaToken extends Instrucoes{

    //static ApontadorRegistro rl = getApontador();
    //public static RegistroLexico rlAntigo = new RegistroLexico();

    public void CasaToken(String tokEsperado) {
        //System.out.println("Teste " + rl.getLexema() + " = " + tokEsperado);
        if (rl != null && rl.getLexema().toLowerCase().equals(tokEsperado.toLowerCase())) {
            rlAntigo.clone(rl);
            Principal.arquivo.Proxima();
            //System.out.println("Teste2 " + rl.getLexema());
        } else if (rl.getEndereco() == 0) {
            System.err.println("Entrou em " + rl.getLexema());
            System.out.println(LeituraArquivo.linha + ":Token esperado não foi encontrado [" + tokEsperado + "]");
        } else {
            if (rl == null || rl.getLexema().equals("" + (char) -1)) {
                System.out.println(LeituraArquivo.linha + ": fim de arquiivo não esperado.");
            } else {
                System.out.println(rl.getLinha() + ":Token não esperado [" + rl.getLexema() + "]");
            }
            System.exit(0);
        }
    }

    public boolean isIdentificador() {
        return rl.getToken().equalsIgnoreCase("identificador");
    }

    public boolean isNumero() {
        return rl.getToken().equals("num");
    }
    
    public void id(ClasseDeTeste temp, boolean declaradoEsperada) {
        if (isIdentificador()) {
            
            // verifica se o Identificador ja foi declarado e compara com o que esperava
            // se em Declaracao o valor esperado de "isDeclarado" e falso
            // se em BlocoComando o valor esperado de "isDeclarado" e verdadeiro
            if (temp.isDeclarado() != declaradoEsperada) { 
                System.out.println(rl.getLinha() + ":Erro no identificador: " + rl.getLexema());
                if (temp.isDeclarado()) {
                    System.out.println("O identificador já foi declarado");
                } else {
                    System.out.println("O identificador não foi declarado");
                }
                Principal.parar = true;
                System.exit(0);
            }
            if (!temp.isDeclarado()) { //se não foi declarado
                temp.Declarar();
                temp.definidosIdentificador();
            }
            
            CasaToken(rl.getLexema());
        } else {
            // erro identificador erra esperado e não foi reconhecido
            erroParar("] onde esperava-se um Identificador na linha: ");
        }
    }
    
    public void num() {
        if (rl.getLexema().equals("-")) {
            CasaToken("-");
            if (isNumero()) {
                rl.inverterSinal();
                CasaToken(rl.getLexema());
            } else {
                Principal.parar = true;
                // erro identificador erra esperado e não foi reconhecido
                erroParar("] onde esperava-se um Numero na linha: ");
            }
        } else if (isNumero()) {
            CasaToken(rl.getLexema());
        } else {
            Principal.parar = true;
            // erro identificador erra esperado e não foi reconhecido
            erroParar("] onde esperava-se um Numero na linha: ");
        }
    }
    
    public String num(ClasseDeTeste teste) {
        String resp ="";
        if (rl.getLexema().equals("-")) {
            CasaToken("-");
            if (isNumero()) {
                //System.out.println(rl.getDecimal());
                if(rl.getDecimal() > 0){
                    resp = "tipo-real";
                    teste.definirTipo(resp);
                    rl.setTipo(false);
                } else{
                    resp = "tipo-inteiro";
                    teste.definirTipo(resp);
                    rl.setTipo(true);
                }
                rl.inverterSinal();
                CasaToken(rl.getLexema());
            } else {
                Principal.parar = true;
                // erro identificador erra esperado e não foi reconhecido
                erroParar("] onde esperava-se um Numero na linha: ");
            }
        } else if (isNumero()) {
            //System.out.println(rl.getDecimal());
            if(rl.getDecimal() > 0){
                    resp = "tipo-real";
                    teste.definirTipo(resp);
                    rl.setTipo(false);
                } else{
                    resp = "tipo-inteiro";
                    teste.definirTipo(resp);
                    rl.setTipo(true);
                }
            CasaToken(rl.getLexema());
        } else {
            Principal.parar = true;
            // erro identificador erra esperado e não foi reconhecido
            erroParar("] onde esperava-se um Numero na linha: ");
        }
        return resp;
    }

    public void erroParar(String mensagem) {
        System.out.println(LeituraArquivo.linha + ":Token não esperado [" + rl.getLexema() + mensagem);
        Principal.parar = true;
        System.exit(0);
    }
}
