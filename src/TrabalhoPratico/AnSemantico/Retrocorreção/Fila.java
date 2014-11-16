/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 1.0
 */
package TrabalhoPratico.AnSemantico.Retrocorreção;

import TrabalhoPratico.AnSemantico.Instrucoes;
import java.util.LinkedList;
import java.util.List;

public class Fila extends Instrucoes{

    private List<dados> objetos;

    public Fila() {
        objetos = new LinkedList<dados>();
    }

    public void insere(dados t) {
        this.objetos.add(t);
    }

    public dados remove() {
        return this.objetos.remove(0);
    }

    public boolean liberado() {
        dados x = objetos.get(0);
        if (x.getRot() != null) {
            System.out.println("Rota:" + x.getRot() + "  End:" + x.getRot().end());
            if (x.getRot().end() < 0) {
                return false;
            }
        }
        return true;
    }
    
    public String teste() {
        dados x = objetos.get(0);
        if (x.getRot() != null) {
            return x.getRot().getNome();
        }
        return "";
    }

    public boolean vazia() {
        return this.objetos.size() == 0;
    }
    
    
    public void reiniciar () {
        dados x = remove();
        int inst = x.getInstrucao();
        switch (inst) {
            case 1:
                ADD(x.getReg1(), x.getReg2());
            case 2:
                ADDF(x.getReg1(), x.getReg2());
            case 3:
                ADI(x.getReg1(),x.getImed());
            case 4:
                ADIF(x.getReg1(), x.getImed(), x.getImed1());
            case 5:
                BNG(x.getReg1(), x.getRot());
            case 6:
                BNGF(x.getReg1(), x.getRot());
            case 7:
                BNN(x.getReg1(), x.getRot());
            case 8:
                BNNF(x.getReg1(), x.getRot());
            case 9:
                BNP(x.getReg1(), x.getRot());
            case 10:
                BNPF(x.getReg1(), x.getRot());
            case 11:
                BNZ(x.getReg1(), x.getRot());
            case 12:
                BNZF(x.getReg1(), x.getRot());
            case 13:
                BPS(x.getReg1(), x.getRot());
            case 14:
                BPSF(x.getReg1(), x.getRot());
            case 15:
                BZR(x.getReg1(), x.getRot());
            case 16:
                BZRF(x.getReg1(), x.getRot());
            case 17:
                CNV(x.getReg1(), x.getReg2());
            case 18:
                DIV(x.getReg1(), x.getReg2());
            case 19:
                ESC(x.getReg1(), x.getReg2());
            case 20:
                HLT();
            case 21:
                JMP(x.getRot());
            case 22:
                LDI(x.getReg1(), x.getImed());
            case 23:
                LDIF(x.getReg1(), x.getImed(), x.getImed1());
            case 24:
                LGT(x.getReg1());
            case 25:
                LOD(x.getReg1(), ""+x.getImed());
            case 26:
                LODF(x.getReg1(), ""+x.getImed());
            case 27:
                MVE(x.getReg1(), x.getReg2());
            case 28:
                MVEF(x.getReg1(), x.getReg2());
            case 29:
                MUL(x.getReg1(), x.getReg2());
            case 30:
                MULF(x.getReg1(), x.getReg2());
            case 31:
                NEG(x.getReg1());
            case 32:
                NEGF(x.getReg1());
            case 33:
                RTR();
            case 34:
                STI(x.getImed1(),""+x.getImed());
            case 35:
                STIF(x.getImed1(),x.getImed2(),""+x.getImed());
            case 36:
                STO(x.getReg1(), ""+x.getImed());
            case 37:
                STOF(x.getReg1(), ""+x.getImed());
            case 38:
                SUB(x.getReg1(), x.getReg2());
            case 39:
                SUBF(x.getReg1(), x.getReg2());
            case 40:
                TIME(x.getReg1());
                
        default:
            System.out.println("ERRO ao remover da fila\nInstrução invalida: " + inst);
        }
    }
}
