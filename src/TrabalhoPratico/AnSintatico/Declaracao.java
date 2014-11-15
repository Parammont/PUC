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
            STIF();
            CasaToken(",");
            num();
            STIF();
            CasaToken(",");
            num();
            STIF();

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
            STI();
            CasaToken(",");
            X();
            STI();
            CasaToken(",");
            X();
            STI();
            CasaToken(",");
            X();
            STI();
            CasaToken(",");
            X();
            STI();
            CasaToken(",");
            X();
            STI();
            CasaToken(",");
            X();
            STI();
            CasaToken(";");

        } while (isIdentificador());
    }

    // face {id = id, id, id, id {, id}*;}+
    private void face() {
        CasaToken("face");

        do {
            int cont = 0;
            String printAsm = "";
            String printExe [] = new String [30];
            int ast = 0;
            String temp[] = new String[2];
            rl.setEndMen(DS);
            int DSQuantPontos = DS++;

            id(teste, false);
            CasaToken("=");
            if (rl.getClasse().equals("classe-cor")) {
                temp = sti(rl.getEndMen());
                printAsm += ("\t" + temp[0] + '\n');
                printExe[ast++] = temp[1];
                printExe[ast++] = temp[2];
                id(teste, true);
            } else {
                System.out.println(rl.getLinha() + ":identificador incompatível [" + rl.getLexema() + "]");
                Principal.parar = true;
            }
            
            while (rl.getLexema().equals(",") || cont < 3) {
                CasaToken(",");
                if (rl.getClasse().equals("classe-ponto")) {
                    temp = sti(rl.getEndMen());
                    printAsm += ("\t" + temp[0] + '\n');
                printExe[ast++] = temp[1];
                printExe[ast++] = temp[2];
                    id(teste, true);
                } else {
                    System.out.println(rl.getLinha() + ":identificador incompatível [" + rl.getLexema() + "]");
                    Principal.parar = true;
                }
                cont++;
            }
            CasaToken(";");
            Principal.arquivo.gravarAsm("STI #" + cont + " , " + DSQuantPontos + "(DS)");
            Principal.arquivo.gravarExe(34);Principal.arquivo.gravarExe(cont);Principal.arquivo.gravarExe(DSQuantPontos);
            
            Principal.arquivo.gravarAsm2(printAsm);
            
            for (int i = 0; i < ast; i++) {
                Principal.arquivo.gravarExe(34);
                Principal.arquivo.gravarExe(printExe[i++]);
                Principal.arquivo.gravarExe(printExe[i]);
            }
        } while (isIdentificador());
    }

    //object {id = id {, id}*;}+
    private void object() {
        CasaToken("object");
        do {
            int cont = 0;
            String printAsm = "";
            String printExe [] = new String [30];
            int ast = 0;
            String temp[] = new String[2];
            rl.setEndMen(DS);
            int DSQuantPontos = DS;
            DS = DS + 3;

            id(teste, false);
            CasaToken("=");
            if (rl.getClasse().equals("classe-face")) {
                temp = sti(rl.getEndMen());
                printAsm += ('\t' + temp[0] + "\n");
                printExe[ast++] = temp[1];
                printExe[ast++] = temp[2];
                id(teste, true);
            } else {
                System.out.println(rl.getLinha() + ":identificador incompatível [" + rl.getLexema() + "]");
                Principal.parar = true;
            }
            cont++;

            while (rl.getLexema().equals(",")) {
                CasaToken(",");
                if (rl.getClasse().equals("classe-face")) {
                    temp = sti(rl.getEndMen());
                    printAsm += ('\t' + temp[0] + "\n");
                printExe[ast++] = temp[1];
                printExe[ast++] = temp[2];
                    id(teste, true);
                } else {
                    System.out.println(rl.getLinha() + ":identificador incompatível [" + rl.getLexema() + "]");
                    Principal.parar = true;
                }
                cont++;
            }
            CasaToken(";");
            Principal.arquivo.gravarAsm("STI  #" + cont + " , " + DSQuantPontos + "(DS)");
            Principal.arquivo.gravarExe(34);Principal.arquivo.gravarExe(cont);Principal.arquivo.gravarExe(DSQuantPontos);
            
            Principal.arquivo.gravarAsm("STIF  #" + 1 + "." + "0000" + " , " + (DSQuantPontos + 1) + "(DS)");
            Principal.arquivo.gravarExe(35);Principal.arquivo.gravarExe(1);Principal.arquivo.gravarExe("0000");
            Principal.arquivo.gravarExe(DSQuantPontos + 1);
            
            Principal.arquivo.gravarAsm2(printAsm);
            
            for (int i = 0; i < ast; i++) {
                Principal.arquivo.gravarExe(34);
                Principal.arquivo.gravarExe(printExe[i++]);
                Principal.arquivo.gravarExe(printExe[i]);
            }

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
            id(teste, false);
            rlDeclarado.clone(rlAntigo);
            CasaToken("=");
            num(teste);

            if (rlAntigo.getTipo().equals("tipo-inteiro")) {
                rlDeclarado.setTipo(true);
                STI();
            } else {
                rlDeclarado.setTipo(false);
                STIF();
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
