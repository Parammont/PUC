/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico.AnSintatico;

import TrabalhoPratico.AnSemantico.*;
import TrabalhoPratico.AnSemantico.Retrocorreção.Fila;
import TrabalhoPratico.AnSemantico.Retrocorreção.dados;
import TrabalhoPratico.LeituraArquivo;
import TrabalhoPratico.Principal;
import TrabalhoPratico.Registros.RegistroLexico;

public class Declaracao extends CasaToken {

    private ClasseDeTeste teste;

    public Declaracao() {
        System.out.println("Declaracao iniciada");
        //ClaseDeTeste teste = new ClasseDeTeste("seila");
        do {    //enquanto tiver declaracao
            if (rl == null) {
                System.out.println(LeituraArquivo.linha + ": fim de arquiivo não esperado..");
                Principal.parar = true;
            } else if (rl.getToken().equals("point")) {
                teste = new ClasseDeTeste("classe-ponto");
                point();
            } else if (rl.getToken().equals("light")) {
                teste = new ClasseDeTeste("classe-luz");
                light();
            } else if (rl.getToken().equals("color")) {
                teste = new ClasseDeTeste("classe-cor");
                color();
            } else if (rl.getToken().equals("face")) {
                teste = new ClasseDeTeste("classe-face");
                face();
            } else if (rl.getToken().equals("object")) {
                teste = new ClasseDeTeste("classe-objeto");
                object();
            } else if (rl.getToken().equals("var")) {
                teste = new ClasseDeTeste("classe-var");
                var();
            } else if (rl.getToken().equals("const")) {
                teste = new ClasseDeTeste("classe-const");
                consta();
            }
        } while ((rl.getEndereco() >= 0 && rl.getEndereco() <= 6) && !(Principal.parar));
        CS = DS;
    }

    private void point() {
        CasaToken("point");
        pointElight();
    }

    private void light() {
        CasaToken("light");
        pointElight();
    }

    //(point | light) {id = '(' num,num,num')';}+
    private void pointElight() {
        do {
            rl.setEndMen(DS);
            id(teste, false);
            CasaToken("=");
            CasaToken("(");

            num();
            rlAntigo.setEndMen(DS);
            STIF(rlAntigo.getInteiro(), rlAntigo.getDecimal(), "" + DS);
            CasaToken(",");
            num();
            rlAntigo.setEndMen(DS);
            STIF(rlAntigo.getInteiro(), rlAntigo.getDecimal(), "" + DS);
            CasaToken(",");
            num();
            rlAntigo.setEndMen(DS);
            STIF(rlAntigo.getInteiro(), rlAntigo.getDecimal(), "" + DS);

            CasaToken(")");
            CasaToken(";");

        } while (isIdentificador());
    }

    //color {id = num, X, X, X, X, X, X;}+
    private void color() {
        CasaToken("color");
        do {
            rl.setEndMen(DS);
            id(teste, false);
            CasaToken("=");
            num();
            rlAntigo.setEndMen(DS);
            STI(rlAntigo.getInteiro(), "" + DS);
            CasaToken(",");
            X();
            rlAntigo.setEndMen(DS);
            STI(rlAntigo.getInteiro(), "" + DS);
            CasaToken(",");
            X();
            rlAntigo.setEndMen(DS);
            STI(rlAntigo.getInteiro(), "" + DS);
            CasaToken(",");
            X();
            rlAntigo.setEndMen(DS);
            STI(rlAntigo.getInteiro(), "" + DS);
            CasaToken(",");
            X();
            rlAntigo.setEndMen(DS);
            STI(rlAntigo.getInteiro(), "" + DS);
            CasaToken(",");
            X();
            rlAntigo.setEndMen(DS);
            STI(rlAntigo.getInteiro(), "" + DS);
            CasaToken(",");
            X();
            rlAntigo.setEndMen(DS);
            STI(rlAntigo.getInteiro(), "" + DS);
            CasaToken(";");

        } while (isIdentificador());
    }

    // face {id = id, id, id, id {, id}*;}+
    private void face() {
        CasaToken("face");

        do {
            int cont = 0;
            Fila inst2 = new Fila();
            rl.setEndMen(DS);
            int DSQuantPontos = DS++;

            id(teste, false);
            CasaToken("=");
            if (rl.getClasse().equals("classe-cor")) {
                inst2.insere(new dados(34, DS, rl.getEndMen()));       /// Guarda STI
                DS += 1;
                PC += 3;
                id(teste, true);
            } else {
                System.out.println(rl.getLinha() + ":identificador incompatível [" + rl.getLexema() + "]");
                Principal.parar = true;
            }

            while (rl.getLexema().equals(",") || cont < 3) {
                CasaToken(",");
                if (rl.getClasse().equals("classe-ponto")) {
                    inst2.insere(new dados(34, DS, rl.getEndMen()));       /// Guarda STI
                    DS += 1;
                    PC += 3;
                    id(teste, true);
                } else {
                    System.out.println(rl.getLinha() + ":identificador incompatível [" + rl.getLexema() + "]");
                    Principal.parar = true;
                }
                cont++;
            }
            CasaToken(";");
            STI(cont, "" + DSQuantPontos);
            while (!inst2.vazia()) {
                inst2.reiniciar();
            }
            DS -= 1;
            //PC -= 3;
        } while (isIdentificador());
    }

    //object {id = id {, id}*;}+
    private void object() {
        CasaToken("object");
        do {
            int cont = 0;
            Fila inst2 = new Fila();
            rl.setEndMen(DS);
            int DSQuantPontos = DS;
            DS += 3;

            id(teste, false);
            CasaToken("=");
            if (rl.getClasse().equals("classe-face")) {
                inst2.insere(new dados(34, DS, rl.getEndMen()));       /// Guarda STI
                DS += 1;
                PC += 3;
                id(teste, true);
            } else {
                System.out.println(rl.getLinha() + ":identificador incompatível [" + rl.getLexema() + "]");
                Principal.parar = true;
            }
            cont++;

            while (rl.getLexema().equals(",")) {
                CasaToken(",");
                if (rl.getClasse().equals("classe-face")) {
                    inst2.insere(new dados(34, DS, rl.getEndMen()));       /// Guarda STI
                    DS += 1;
                    PC += 3;
                    id(teste, true);
                } else {
                    System.out.println(rl.getLinha() + ":identificador incompatível [" + rl.getLexema() + "]");
                    Principal.parar = true;
                }
                cont++;
            }
            CasaToken(";");

            STI(cont, "" + DSQuantPontos);
            STIF(1, 0, "" + (DSQuantPontos + 1));
            while (!inst2.vazia()) {
                inst2.reiniciar();
            }
            DS -= 3;
            //PC -= 3;

        } while (isIdentificador());
    }

    //var {(int | real){(id {, id}*; | id = num;)}+}+
    private void var() {
        CasaToken("var");
        do {
            if (rl.getLexema().equals("int")) {
                CasaToken("int");
                teste.definirTipo("tipo-inteiro");
                do {
                    rl.setEndMen(DS);
                    id(teste, false);
                    if (rl.getLexema().equals("=")) {
                        CasaToken("=");
                        num(teste);
                        if (rlAntigo.getTipo().equals("tipo-real")) { // tipo inteiro não pode receber tipo reald
                            System.out.println(rl.getLinha() + ":tipo incompatível [" + rlAntigo.getLexema() + "]");
                            Principal.parar = true;
                        }
                    }
                    if (rl.getLexema().equals(",")) {
                        while (rl.getLexema().equals(",")) {
                            CasaToken(",");
                            //id(teste, true);
                        }
                    }
                } while (isIdentificador());
                CasaToken(";");

            } else if (rl.getLexema().equals("real")) {
                CasaToken("real");
                teste.definirTipo("tipo-real");
                id(teste, false);
                if (rl.getLexema().equals("=")) {
                    CasaToken("=");
                    num(teste);
                } else if (rl.getLexema().equals(",")) {
                    while (rl.getLexema().equals(",")) {
                        CasaToken(",");
                        id(teste, true);
                    }
                }
                CasaToken(";");
            } else {
                // erro int ou real era esperado e não foi reconhecido
                erroParar("] onde esperava-se um tipo na linha: ");
            }
        } while (rl.getLexema().equals("int") || rl.getLexema().equals("real"));
    } //const {id = num;}+

    private void consta() {
        CasaToken("const");

        do {
            RegistroLexico rlDeclarado = new RegistroLexico();
            rl.setEndMen(DS);
            id(teste, false);
            rlDeclarado.clone(rlAntigo);
            CasaToken("=");
            num(teste);

            if (rlAntigo.getTipo().equals("tipo-inteiro")) {
                rlDeclarado.setTipo(true);
                STI(rlAntigo.getInteiro(), "" + DS);
            } else {
                rlDeclarado.setTipo(false);
                STIF(rlAntigo.getInteiro(), rlAntigo.getDecimal(), "" + DS);
            }
            CasaToken(";");
        } while (isIdentificador());
    }

    private void X() {
        String lex = rl.getLexema();
        if (lex.length() == 1) { // 0-9
            CasaToken(lex);
        } else if (lex.length() == 2) {
            if (lex.charAt(0) <= '5') { // Até 59
                CasaToken(lex);
            } else if (lex.charAt(0) == '6' && lex.charAt(1) <= '3') {  //de 60 á 63
                CasaToken(lex);
            } else {
                System.out.println(rl.getLinha() + ": token nao esperado [" + lex + "].");
                Principal.parar = true;
                System.exit(1);
            }
        } else {
            System.out.println(rl.getLinha() + ": token nao esperado [" + lex + "].");
            Principal.parar = true;
            System.exit(1);
        }
    }
}
