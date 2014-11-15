/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico.AnSintatico;

import TrabalhoPratico.AnSemantico.ClasseDeTeste;
import TrabalhoPratico.Principal;

public class BlocoComando extends CasaToken {

    public BlocoComando() {
        if (!(Principal.parar) && !(Principal.fimArquivo)) {
            System.out.println("Bloco de Comando iniciada " + rl.getLinha());
            CasaToken("begin");
            while (rl != null && !(rl.getLexema().equals("end")) && (isIdentificador() || ispalavraReservada()) && !(Principal.parar)) {
                Comando();
            }
            CasaToken("end");

        }
    }

    private void Comando() {
        //comando -> id = Exp;
        if (isIdentificador()) {
            if (rl.getClasse() == null) {
                System.out.println(rl.getLinha() + "Clase do identificador nao declarado [" + rl.getLexema() + "]");
                Principal.parar = true;
            }
            id(new ClasseDeTeste(), true);           ///falta definir o ClasseDeTeste do AnSemantico
            CasaToken("=");
            if (Exp().equals("tipo-logico")) {
                System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                Principal.parar = true;
            }
            CasaToken(";");
        } else if (rl.getLexema().equals("while")) {
            comandWhile();
        } else if (rl.getLexema().equals("if")) {
            comandIf();
        } else if (rl.getLexema().equals("scale")) {
            comandScale();
        } else if (rl.getLexema().equals("pause")) {
            comandpause();
        } else if (rl.getLexema().equals("light")) {
            comandLight();
        } else if (rl.getLexema().equals("rottrans")) {
            comandRottrans();
        } else if (rl.getLexema().equals("ERRO")) {
            System.err.println("Entrou em " + rl.getLexema());
            //System.out.println(rl.getLinha() + ":Token esperado não foi encontrado [end]");
            Principal.parar = true;
        }

    }

    //comando -> while Exp (comando | BlocoComando) 
    private void comandWhile() {
        CasaToken("while");
        String resp[] = Exp();
        if (!(resp[0].equals("tipo-logico"))) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        if (rl.getLexema().equals("begin")) {
            new BlocoComando();
        } else {
            Comando();
        }
    }

    //comando -> if Exp then (comando | BlocoComando) [else (BlocoComando | comando)]
    private void comandIf() {
        CasaToken("if");
        if (!Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken("then");
        if (rl.getLexema().equals("begin")) {
            new BlocoComando();
        } else {
            Comando();
        }
        if (rl.getLexema().equals("else")) {
            CasaToken("else");
            if (rl.getLexema().equals("begin")) {
                new BlocoComando();
            } else {
                Comando();
            }
        }
    }

    //comando -> scale id, Exp;
    private void comandScale() {
        CasaToken("scale");
        if (rl.getClasse().equals("classe-objeto")) {
            id(new ClasseDeTeste(), true);
        } else {
            System.out.println(rl.getLinha() + ":Identificador incompativel [" + rl.getLexema() + "]");
            Principal.parar = true;
        }
        CasaToken(",");
        if (Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken(";");
    }

    //comando -> pause Exp;
    private void comandpause() {
        CasaToken("pause");
        if (Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken(";");
    }

    //comando -> light id;
    private void comandLight() {
        CasaToken("light");
        if (rl.getClasse().equals("classe-luz")) {
            id(new ClasseDeTeste(), true);
            Principal.arquivo.gravarAsm("LDI " + "\\pegar uma letra livre na tabela" + " , #" + rlAntigo.getEndMen());
            Principal.arquivo.gravarExe(22);
            //Principal.arquivo.gravarExe(); Pegar o numero corespondente a letra escolhida
            Principal.arquivo.gravarExe(1);//esta linha tem que sumir
            Principal.arquivo.gravarExe(rlAntigo.getEndMen());
            
            Principal.arquivo.gravarAsm("LGT " + "\\pegar a letra Utilizada");
            Principal.arquivo.gravarExe(24);
            //Principal.arquivo.gravarExe(); Pegar o numero corespondente a letra escolhida
            Principal.arquivo.gravarExe(1);//esta linha tem que sumir
            
            // Liberar a letra utilizada
        } else {
            System.out.println(rl.getLinha() + ":Identificador incompativel [" + rl.getLexema() + "]");
            Principal.parar = true;
        }
        CasaToken(";");
    }

    //comando -> rottrans id, Exp,Exp, Exp, Exp, Exp, Exp;
    private void comandRottrans() {
        CasaToken("rottrans");
        if (rl.getClasse().equals("classe-objeto")) {
            id(new ClasseDeTeste(), true);
        } else {
            System.out.println(rl.getLinha() + ":Identificador incompativel [" + rl.getLexema() + "]");
            Principal.parar = true;
        }

        CasaToken(",");
        if (Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken(",");
        if (Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken(",");
        if (Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken(",");
        if (Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken(",");
        if (Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken(",");
        if (Exp().equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        CasaToken(";");
    }

    //Exp -> ExpS [(< | > | <= | >= | == | !=) ExpS]
    private String[] Exp() {
        Principal.arquivo.gravarAsm("************************** Expressao ******");
        String resp[] = ExpS();
        if (rl.getLexema().equals("=")) {
            CasaToken("=");
            CasaToken("=");
            String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BZR e BZRF para desvio
            resp[0] = "tipo-logico";
        } else if (rl.getLexema().equals("<")) {
            CasaToken("<");
            if (rl.getLexema().equals("=")) {
                CasaToken("=");
                String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BNP e BNPF para desvio
            } else {
                String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BNG e BNGF para desvio
            }
            
            resp[0] = "tipo-logico";
        } else if (rl.getLexema().equals(">")) {
            CasaToken(">");
            if (rl.getLexema().equals("=")) {
                CasaToken("=");
                String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BNN e BNNF para desvio
            } else {
                String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BPS e BPSF para desvio
            }
            
            resp[0] = "tipo-logico";
        } else if (rl.getLexema().equals("!")) {
            CasaToken("!");
            CasaToken("=");
            String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BNZ e BNZF para desvio
            
            resp[0] = "tipo-logico";
        }
        return resp;

    }

    //ExpS -> T {(+ | - | || )T}
    private String[] ExpS() {
        String resp[] = T();        //onde tipo 0 e end 1
        while ((rl.getLexema().equals("+") || rl.getLexema().equals("-") || rl.getLexema().equals("||")) && !(Principal.parar)) {
            if (new ClasseDeTeste().isTipoLogico() && resp[0].equals("tipo-logico")) {
                CasaToken(rl.getLexema());
                String dadosT2[] = T();
                if (!dadosT2[0].equals(resp[0])) {
                    // T não e logico
                    System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                    Principal.parar = true;
                }
            } else if (new ClasseDeTeste().isTipoLogico()) {
                // O primeiro T não e logico
                System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                Principal.parar = true;
            } else {
                CasaToken(rl.getLexema());
                String dadosT2[] = T();
                if (dadosT2[0].equals(resp[0])) {
                    //return resp;
                } else {
                    resp[0] = "tipo-real";
                }
            }
        }
        return resp;

    }

    //T -> F {(* | / | && )F}
    private String[] T() {
        String resp[] = F();   //onde tipo 0 e end 1
        while ((rl.getLexema().equals("*") || rl.getLexema().equals("/") || rl.getLexema().equals("&&")) && !(Principal.parar)) {
            if (new ClasseDeTeste().isTipoLogico() && resp[0].equals("tipo-logico")) {
                CasaToken(rl.getLexema());
                String dadosF2[] = F();
                if (!dadosF2[0].equals(resp[0])) {
                    // F não e logico
                    System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                    Principal.parar = true;
                }
                // local do codigo
                // Ação para && onde 1 e verdadeiro e 0 e falso
                // mul A, B para isto carrega o endereço de F em a e de F2 em B e mul A,B a resposta e dada em A
                
                
            } else if (new ClasseDeTeste().isTipoLogico()) {
                // O primeiro F não e logico
                System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                Principal.parar = true;
            } else {
                CasaToken(rl.getLexema());
                String dadosF2[] = F();
                if (dadosF2[0].equals(resp[0]) ) {
                    //return respTipo;
                    //troca de tipo para divisão
                    System.out.println("Falta Tudo Aqui");
                    
                } else {
                    if (rlAntigo.getLexema().equals("*")) {
                        System.out.println("Olha entrou em multiplicação em T");
                        
                    }
                    resp[0] = "tipo-real";
                }
            }
        }
        return resp;
    }

    //F -> !F | id | '(' Exp ')'| numero
    private String[] F() {
        String resp[] = new String [2];
        if (isIdentificador()) {
            resp[0] = rl.getTipo();
            resp[1] = "" + rl.getEndMen();
            id(new ClasseDeTeste(), true);
        } else if (rl.getLexema().equals("!")) {
            CasaToken("!");
                String dadosF[] = F();
            // deve se negar o resultade de F
            resp[0] = "tipo-logico";
        } else if (isNumero()) {
            resp[0] = num(new ClasseDeTeste());
            int tamanho = rlAntigo.getTipo().equals("tipo-inteiro") ? 1 : 2;
            resp[1] = "" + novoTemp(tamanho);
            if (tamanho == 1) {
                Principal.arquivo.gravarAsm("STI  #" + rlAntigo.getLexema() + " , " + resp[1] + "(DS)");
                Principal.arquivo.gravarExe(34);                    //STI
                Principal.arquivo.gravarExe(rlAntigo.getInteiro()); //parte inteira
                Principal.arquivo.gravarExe(resp[1]);               //Endereço
            } else {
                Principal.arquivo.gravarAsm("STIF  #" + rlAntigo.getInteiro() + "." + rlAntigo.getDecimal() + " , " + resp[1] + "(DS)");
                Principal.arquivo.gravarExe(35);                    //STIF
                Principal.arquivo.gravarExe(rlAntigo.getInteiro()); 
                Principal.arquivo.gravarExe(rlAntigo.getDecimal()); //parte Fracionada
                Principal.arquivo.gravarExe(resp[1]);               //Endereço
            }
        } else {
            CasaToken("(");
            resp = Exp();
            CasaToken(")");
        }
        return resp;

    }

    private boolean ispalavraReservada() {
        return (rl.getEndereco() < 37);
    }

}
