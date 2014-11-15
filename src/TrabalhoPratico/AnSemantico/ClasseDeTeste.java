/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.0
 */
package TrabalhoPratico.AnSemantico;

import TrabalhoPratico.Registros.ApontadorRegistro;
import static TrabalhoPratico.Registros.ApontadorRegistro.getApontador;

public class ClasseDeTeste {

    static ApontadorRegistro rl;
    private boolean isTipo;
    public boolean isClasse;
    private boolean isTipoInteiro;
    private boolean isClasseVar;
    private boolean isClasseConst;
    private boolean isClassePonto;
    private boolean isClasseFace;
    private boolean isClasseObjeto;
    private boolean isClasseLuz;
    private boolean isClasseCor;
    private boolean isTipoLogico;

    public ClasseDeTeste(String novo) {
        isClasse = true;
        rl = getApontador();
        if (novo.equals("classe-var")) {
            isClasseVar = true;
        } else if (novo.equals("classe-const")) {
            isClasseConst = true;
        } else if (novo.equals("classe-ponto")) {
            isClassePonto = true;
        } else if (novo.equals("classe-face")) {
            isClasseFace = true;
        } else if (novo.equals("classe-objeto")) {
            isClasseObjeto = true;
        } else if (novo.equals("classe-luz")) {
            isClasseLuz = true;
        } else if (novo.equals("classe-cor")) {
            isClasseCor = true;
        } else {
            //System.out.println("Erro ao construir AnSemantico.ClaseDeTeste");
            isClasse = false;
        }
    }

    public ClasseDeTeste() {
        rl = getApontador();
    }
    
    //Tipo e definidos apenas para identificadores de variáveis e constantes
    public void definirTipo (String novo) {
        if (isClasseVar || isClasseConst) {
            if (novo.equals("tipo-inteiro")) {
                isTipo = true;
                isTipoInteiro = true;
                isTipoLogico = false;
            } else if (novo.equals("tipo-real")) {
                isTipo = true;
                isTipoInteiro = false;
                isTipoLogico = false;
            } else if (novo.equals("tipo-logico")) {
                isTipo = true;
                isTipoInteiro = false;
                isTipoLogico = true;
            } else {
                //System.out.println("Erro ao construir AnSemantico.ClaseDeTeste");
            }
        } else {
            //System.out.println("Erro ao construir AnSemantico.ClaseDeTeste");
        }
    }

    //1. Modifique a tabela de símbolos
    public void definidosIdentificador() {
        if (isClasse) {
            if (isClasseConst) {
                rl.setClasseConst();
                rl.setTipo(isTipoInteiro);
            } else if (isClasseCor) {
                rl.setClasseCor();
            } else if (isClasseFace) {
                rl.setClasseFace();
            } else if (isClasseLuz) {
                rl.setClasseLuz();
            } else if (isClasseObjeto) {
                rl.setClasseObjeto();
            } else if (isClassePonto) {
                rl.setClassePonto();
            } else if (isClasseVar) {
                rl.setClasseVar();
                rl.setTipo(isTipoInteiro);
            } else {
                //System.out.println("Erro ao definir AnSemantico.ClaseDeTeste");
            }
        } else {
            //System.out.println("Erro ao definir AnSemantico.ClaseDeTeste");
        }
    }
    
    public boolean isDeclarado () {
        return rl.getDeclaracao() > 0;
    }
    
    public void Declarar () {
        rl.setDeclaracao();
    }
    
    public boolean isTipoLogico () {
        if(rl.getLexema().equals("<")) {
            return true;
        } else if(rl.getLexema().equals(">")) {
            return true;
        } else if(rl.getLexema().equals("||")) {
            return true;
        } else if(rl.getLexema().equals("&&")) {
            return true;
        } else if(rl.getLexema().equals("!")) {
            return true;
        }else if(rl.getLexema().equals("==")) {
            return true;
        } else if(rl.getLexema().equals("!=")) {
            return true;
        } else if(rl.getLexema().equals(">=")) {
            return true;
        } else if(rl.getLexema().equals("<=")) {
            return true;
        } else {
            return false;
        }
    }

}
