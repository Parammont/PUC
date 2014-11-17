/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico.AnSintatico;

import TrabalhoPratico.AnSemantico.ClasseDeTeste;
import TrabalhoPratico.AnSemantico.Rotulo;
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
        Principal.arquivo.gravarAsm("************************** Enquanto ******");
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
        int temp = 0;
        if (rl.getClasse().equals("classe-objeto")) {
            id(new ClasseDeTeste(), true);
            temp = rlAntigo.getEndMen();
        } else {
            System.out.println(rl.getLinha() + ":Identificador incompativel [" + rl.getLexema() + "]");
            Principal.parar = true;
        }
        CasaToken(",");
        String [] temp2 = Exp();
        if (temp2[0].equals("tipo-logico")) {
            System.out.println(rl.getLinha() + ":tipos incompatíveis.");
            Principal.parar = true;
        }
        LODF("A", temp2[1]);
        LDI("A",temp);
        ESC("A","A");
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
            LDI("A", rlAntigo.getEndMen());
            LGT("A");
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
            if (dadosExpS[0].equals(resp[0])) {
                if (resp[0].equals("tipo-inteiro")) {
                    //BZR
                    LOD("A", resp[1]);
                    LOD("B", dadosExpS[1]);
                    SUB("A", "B");

                    Rotulo rot = novoRot();
                    BZR("A", rot);
                    LDI("A", 1);
                    Principal.arquivo.gravarAsm(rot.getNome() + ":");
                    //Principal.arquivo.gravarExe(rot.end());
                } else {
                    //BZRF
                    LODF("A", resp[1]);
                    LODF("B", dadosExpS[1]);
                    SUBF("A", "B");

                    Rotulo rot = novoRot();
                    BZRF("A", rot);
                    LDIF("A", 1, 0);
                    Principal.arquivo.gravarAsm(rot.getNome() + ":");
                    //Principal.arquivo.gravarExe(rot.end());
                }
            } else {    // tipos diferentes
                if (resp[0].equals("tipo-inteiro")) {
                    //CNV
                    LOD("A", resp[1]);
                    CNV("A", "A");
                    LODF("B", dadosExpS[1]);
                } else {
                    //CNV
                    LODF("A", resp[1]);
                    LOD("B", dadosExpS[1]);
                    CNV("B", "B");
                }
                //BZRF
                SUBF("A", "B");
                Rotulo rot = novoRot();
                BZRF("A", rot);
                LDIF("A", 1, 0);
                Principal.arquivo.gravarAsm(rot.getNome() + ":");
                //Principal.arquivo.gravarExe(rot.end());
                resp[0] = "tipo-real";
            }
            resp = salvar(resp);
            resp[0] = "tipo-logico";

        } else if (rl.getLexema().equals("<")) {
            CasaToken("<");
            if (rl.getLexema().equals("=")) {
                CasaToken("=");
                String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BNP e BNPF para desvio
                if (dadosExpS[0].equals(resp[0])) {
                    if (resp[0].equals("tipo-inteiro")) {
                        //BNP
                        LOD("A", resp[1]);
                        LOD("B", dadosExpS[1]);
                        SUB("A", "B");

                        Rotulo rot = novoRot();
                        BNP("A", rot);
                        LDI("A", 1);
                        Principal.arquivo.gravarAsm(rot.getNome() + ":");
                        //Principal.arquivo.gravarExe(rot.end());

                    } else {
                        //BNPF
                        LODF("A", resp[1]);
                        LODF("B", dadosExpS[1]);
                        SUBF("A", "B");

                        Rotulo rot = novoRot();
                        BNPF("A", rot);
                        LDIF("A", 1, 0);
                        Principal.arquivo.gravarAsm(rot.getNome() + ":");
                        //Principal.arquivo.gravarExe(rot.end());
                    }
                } else {    // tipos diferentes
                    if (resp[0].equals("tipo-inteiro")) {
                        //CNV
                        LOD("A", resp[1]);
                        LODF("B", dadosExpS[1]);
                        CNV("A", "A");
                    } else {
                        //CNV
                        LODF("A", resp[1]);
                        LOD("B", dadosExpS[1]);
                        CNV("B", "B");
                    }
                    //BNPF
                    SUBF("A", "B");
                    Rotulo rot = novoRot();
                    BNPF("A", rot);
                    LDIF("A", 1, 0);
                    Principal.arquivo.gravarAsm(rot.getNome() + ":");
                    //Principal.arquivo.gravarExe(rot.end());
                    resp[0] = "tipo-real";
                }
            } else {        //////////  no casso de ser apenas menor
                String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BNG e BNGF para desvio
                if (dadosExpS[0].equals(resp[0])) {
                    if (resp[0].equals("tipo-inteiro")) {
                        //BNG
                        LOD("A", resp[1]);
                        LOD("B", dadosExpS[1]);
                        SUB("A", "B");

                        Rotulo rot = novoRot();
                        BNG("A", rot);
                        LDI("A", 1);
                        Principal.arquivo.gravarAsm(rot.getNome() + ":");
                        //Principal.arquivo.gravarExe(rot.end());

                    } else {
                        //BNGF
                        LODF("A", resp[1]);
                        LODF("B", dadosExpS[1]);
                        SUBF("A", "B");

                        Rotulo rot = novoRot();
                        BNGF("A", rot);
                        LDIF("A", 1, 0);
                        Principal.arquivo.gravarAsm(rot.getNome() + ":");
                        //Principal.arquivo.gravarExe(rot.end());
                    }
                } else {    // tipos diferentes
                    if (resp[0].equals("tipo-inteiro")) {
                        //CNV
                        LOD("A", resp[1]);
                        CNV("A", "A");
                        LODF("B", dadosExpS[1]);
                    } else {
                        //CNV
                        LODF("A", resp[1]);
                        LOD("B", dadosExpS[1]);
                        CNV("B", "B");
                    }
                    //BNGF
                    SUBF("A", "B");
                    Rotulo rot = novoRot();
                    BNGF("A", rot);
                    LDIF("A", 1, 0);
                    Principal.arquivo.gravarAsm(rot.getNome() + ":");
                    //Principal.arquivo.gravarExe(rot.end());
                    resp[0] = "tipo-real";
                }
            }
            resp = salvar(resp);
            resp[0] = "tipo-logico";

        } else if (rl.getLexema().equals(">")) {
            CasaToken(">");
            if (rl.getLexema().equals("=")) {
                CasaToken("=");
                String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BNN e BNNF para desvio
                if (dadosExpS[0].equals(resp[0])) {
                    if (resp[0].equals("tipo-inteiro")) {
                        //BNN
                        LOD("A", resp[1]);
                        LOD("B", dadosExpS[1]);
                        SUB("A", "B");

                        Rotulo rot = novoRot();
                        BNN("A", rot);
                        LDI("A", 1);
                        Principal.arquivo.gravarAsm(rot.getNome() + ":");
                        //Principal.arquivo.gravarExe(rot.end());

                    } else {
                        //BNNF
                        LODF("A", resp[1]);
                        LODF("B", dadosExpS[1]);
                        SUBF("A", "B");

                        Rotulo rot = novoRot();
                        BNNF("A", rot);
                        LDIF("A", 1, 0);
                        Principal.arquivo.gravarAsm(rot.getNome() + ":");
                        //Principal.arquivo.gravarExe(rot.end());
                    }
                } else {    // tipos diferentes
                    if (resp[0].equals("tipo-inteiro")) {
                        //CNV
                        LOD("A", resp[1]);
                        LODF("B", dadosExpS[1]);
                        CNV("A", "A");
                    } else {
                        //CNV
                        LODF("A", resp[1]);
                        LOD("B", dadosExpS[1]);
                        CNV("B", "B");
                    }
                    //BNNF
                    SUBF("A", "B");
                    Rotulo rot = novoRot();
                    BNNF("A", rot);
                    LDIF("A", 1, 0);
                    Principal.arquivo.gravarAsm(rot.getNome() + ":");
                    //Principal.arquivo.gravarExe(rot.end());
                    resp[0] = "tipo-real";

                }
            } else {        //////////  no casso de ser apenas Maior
                String dadosExpS[] = ExpS();
                // Aqui tem loas de Exp em A e ExpS em B e sub A,B BPS e BPSF para desvio
                if (dadosExpS[0].equals(resp[0])) {
                    if (resp[0].equals("tipo-inteiro")) {
                        //BPS
                        LOD("A", resp[1]);
                        LOD("B", dadosExpS[1]);
                        SUB("A", "B");

                        Rotulo rot = novoRot();
                        BPS("A", rot);
                        LDI("A", 1);
                        Principal.arquivo.gravarAsm(rot.getNome() + ":");
                        //Principal.arquivo.gravarExe(rot.end());

                    } else {
                        //BPSF
                        LODF("A", resp[1]);
                        LODF("B", dadosExpS[1]);
                        SUBF("A", "B");

                        Rotulo rot = novoRot();
                        BPSF("A", rot);
                        LDIF("A", 1, 0);
                        Principal.arquivo.gravarAsm(rot.getNome() + ":");
                        //Principal.arquivo.gravarExe(rot.end());
                    }
                } else {    // tipos diferentes
                    if (resp[0].equals("tipo-inteiro")) {
                        //CNV
                        LOD("A", resp[1]);
                        LODF("B", dadosExpS[1]);
                        CNV("A", "A");
                    } else {
                        //CNV
                        LODF("A", resp[1]);
                        LOD("B", dadosExpS[1]);
                        CNV("B", "B");
                    }
                    //BPSF
                    SUBF("A", "B");
                    Rotulo rot = novoRot();
                    BPSF("A", rot);
                    LDIF("A", 1, 0);
                    Principal.arquivo.gravarAsm(rot.getNome() + ":");
                    //Principal.arquivo.gravarExe(rot.end());
                    resp[0] = "tipo-real";
                }
            }
            resp = salvar(resp);
            resp[0] = "tipo-logico";
            
        } else if (rl.getLexema().equals("!")) {
            CasaToken("!");
            CasaToken("=");
            String dadosExpS[] = ExpS();
            // Aqui tem loas de Exp em A e ExpS em B e sub A,B BNZ e BNZF para desvio
            if (dadosExpS[0].equals(resp[0])) {
                if (resp[0].equals("tipo-inteiro")) {
                    //BNZ
                    LOD("A", resp[1]);
                    LOD("B", dadosExpS[1]);
                    SUB("A", "B");

                    Rotulo rot = novoRot();
                    BNZ("A", rot);
                    LDI("A", 1);
                    Principal.arquivo.gravarAsm(rot.getNome() + ":");
                    //Principal.arquivo.gravarExe(rot.end());
                } else {
                    //BNZF
                    LODF("A", resp[1]);
                    LODF("B", dadosExpS[1]);
                    SUBF("A", "B");

                    Rotulo rot = novoRot();
                    BNZF("A", rot);
                    LDIF("A", 1, 0);
                    Principal.arquivo.gravarAsm(rot.getNome() + ":");
                    //Principal.arquivo.gravarExe(rot.end());
                }
            } else {    // tipos diferentes
                if (resp[0].equals("tipo-inteiro")) {
                    //CNV
                    LOD("A", resp[1]);
                    LODF("B", dadosExpS[1]);
                    CNV("A", "A");
                } else {
                    //CNV
                    LODF("A", resp[1]);
                    LOD("B", dadosExpS[1]);
                    CNV("B", "B");
                }
                //BNZF
                SUBF("A", "B");
                Rotulo rot = novoRot();
                BNZF("A", rot);
                LDIF("A", 1, 0);
                Principal.arquivo.gravarAsm(rot.getNome() + ":");
                //Principal.arquivo.gravarExe(rot.end());
                resp[0] = "tipo-real";
            }
            resp = salvar(resp);
            resp[0] = "tipo-logico";
        }
        return resp;
    }

    //ExpS -> T {(+ | - | || )T}
    private String[] ExpS() {
        String resp[] = T();        //onde tipo 0 e end 1
        while ((rl.getLexema().equals("+") || rl.getLexema().equals("-") || rl.getLexema().equals("||")) && !(Principal.parar)) {
            char operador = rl.getLexema().charAt(0);
            if (new ClasseDeTeste().isTipoLogico() && resp[0].equals("tipo-logico")) {
                CasaToken(rl.getLexema());
                String dadosT2[] = T();
                LOD("A", resp[1]);
                LOD("B", dadosT2[1]);
                if (!dadosT2[0].equals(resp[0])) {
                    // T não e logico
                    System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                    Principal.parar = true;
                }
                Rotulo rot = novoRot();
                BZR("A", rot);
                LDI("A", 1);
                Principal.arquivo.gravarAsm(rot.getNome() + ":");
                //Principal.arquivo.gravarExe(rot.end());

            } else if (new ClasseDeTeste().isTipoLogico()) {
                // O primeiro T não e logico
                System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                Principal.parar = true;
            } else {
                CasaToken(rl.getLexema());
                String dadosT2[] = T();
                if (dadosT2[0].equals(resp[0])) {
                    if (resp[0].equals("tipo-inteiro")) {
                        LOD("A", resp[1]);
                        LOD("B", dadosT2[1]);
                    } else {
                        LODF("A", resp[1]);
                        LODF("B", dadosT2[1]);
                    }
                } else {
                    if (resp[0].equals("tipo-inteiro")) {
                        LOD("A", resp[1]);
                        LODF("B", dadosT2[1]);
                        CNV("A", "A");
                    } else {
                        LODF("A", resp[1]);
                        LOD("B", dadosT2[1]);
                        CNV("B", "B");
                    }
                    resp[0] = "tipo-real";
                }
                if (operador == '+') {
                    if (resp[0].equals("tipo-inteiro")) {
                        ADD("A", "B");
                    } else {
                        ADDF("A", "B");
                    }
                } else {
                    if (resp[0].equals("tipo-inteiro")) {
                        SUB("A", "B");
                    } else {
                        SUBF("A", "B");
                    }
                }
            }
            resp = salvar(resp);
        }
        return resp;

    }

    //T -> F {(* | / | && )F}
    private String[] T() {
        String resp[] = F();   //onde tipo 0 e end 1
        while ((rl.getLexema().equals("*") || rl.getLexema().equals("/") || rl.getLexema().equals("&&")) && !(Principal.parar)) {
            char operador = rl.getLexema().charAt(0);
            if (new ClasseDeTeste().isTipoLogico() && resp[0].equals("tipo-logico")) {
                CasaToken(rl.getLexema());
                String dadosF2[] = F();
                LOD("A", resp[1]);
                LOD("B", dadosF2[1]);
                if (!dadosF2[0].equals(resp[0])) {
                    // F não e logico
                    System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                    Principal.parar = true;
                }
                // Ação para && onde 1 e verdadeiro e 0 e falso
                // MUL A, B
                MUL("A", "B");
            } else if (new ClasseDeTeste().isTipoLogico()) {
                // O primeiro F não e logico
                System.out.println(rl.getLinha() + ":tipos incompatíveis.");
                Principal.parar = true;
            } else {
                CasaToken(rl.getLexema());
                String dadosF2[] = F();
                if (dadosF2[0].equals(resp[0])) {
                    if (resp[0] == "tipo-inteiro") {
                        LOD("A", resp[1]);
                        LOD("B", dadosF2[1]);
                        if (rlAntigo.getLexema().equals("/")) {
                            CNV("A", "A");
                            CNV("B", "B");
                            resp[0] = "tipo-real";
                        }
                    } else {
                        LODF("A", resp[1]);
                        LODF("B", dadosF2[1]);
                    }
                } else {
                    if (resp[0] == "tipo-inteiro") {
                        LOD("A", resp[1]);
                        LODF("B", dadosF2[1]);
                        CNV("A", "A");
                    } else {
                        LODF("A", resp[1]);
                        LOD("B", dadosF2[1]);
                        CNV("B", "B");
                    }
                    resp[0] = "tipo-real";
                }

                if (operador == '*') {
                    if (resp[0] == "tipo-inteiro") {
                        MUL("A", "B");
                    } else {
                        MULF("A", "B");
                    }
                } else {
                    DIV("A", "B");
                }
            }
            resp = salvar(resp);
        }
        return resp;
    }

    //F -> !F | id | '(' Exp ')'| numero
    private String[] F() {
        String resp[] = new String[2];
        if (isIdentificador()) {
            resp[0] = rl.getTipo();
            System.out.println("Erro end de " + rl.getLexema() + "  " + rl.getEndMen());
            resp[1] = "" + rl.getEndMen();
            id(new ClasseDeTeste(), true);
        } else if (rl.getLexema().equals("!")) {
            CasaToken("!");
            String dadosF[] = F();
            resp[1] = "" + novoTemp(1);
            LOD("A", dadosF[1]);
            NEG("A");
            ADI("A", 1);
            STO("A", resp[1]);
            // deve se negar o resultade de F
            resp[0] = "tipo-logico";
        } else if (isNumero()) {
            resp[0] = num(new ClasseDeTeste());
            int tamanho = rlAntigo.getTipo().equals("tipo-inteiro") ? 1 : 2;
            resp[1] = "" + novoTemp(tamanho);
            if (tamanho == 1) {
                rlAntigo.setEndMen(DS);
                STI(rlAntigo.getInteiro(), resp[1]);
/*                Principal.arquivo.gravarAsm("STI  #" + rlAntigo.getLexema() + " , " + resp[1] + "(DS)");
                Principal.arquivo.gravarExe(34);                    //STI
                Principal.arquivo.gravarExe(rlAntigo.getInteiro()); //parte inteira
                Principal.arquivo.gravarExe(resp[1]);               //Endereço*/
            } else {
                rlAntigo.setEndMen(DS);
                STIF(rlAntigo.getInteiro(), rlAntigo.getDecimal(), resp[1]);
/*                Principal.arquivo.gravarAsm("STIF  #" + rlAntigo.getInteiro() + "." + rlAntigo.getDecimal() + " , " + resp[1] + "(DS)");
                Principal.arquivo.gravarExe(35);                    //STIF
                Principal.arquivo.gravarExe(rlAntigo.getInteiro());
                Principal.arquivo.gravarExe(rlAntigo.getDecimal()); //parte Fracionada
                Principal.arquivo.gravarExe(resp[1]);               //Endereço*/
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

    private String[] salvar(String[] resp) {
        if (resp[0].equals("tipo-real")) {
            resp[1] = "" + novoTemp(2);
            STOF("A", resp[1]);
        } else {
            resp[1] = "" + novoTemp(1);
            STO("A", resp[1]);
        }
        return resp;
    }

}
