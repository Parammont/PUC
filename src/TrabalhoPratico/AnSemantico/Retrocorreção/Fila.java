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

public class Fila extends Instrucoes {

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
        if (!vazia()) {
            dados x = objetos.get(0);
            if ((x.getInstrucao() > 4 && x.getInstrucao() < 17) || x.getInstrucao() == 21) {
                if (dadoRotulo.get(x.getImed()).getEndereco() < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean vazia() {
        return this.objetos.size() == 0;
    }

    public void reiniciar() {
        dados x = remove();
        int inst = x.getInstrucao();
        if (inst < 10) {
//        switch (inst) {
            if (inst == 1) {
                ADD(x.getReg1(), x.getReg2());
                PC -= 3;
            } else if (inst == 2) {
                ADDF(x.getReg1(), x.getReg2());
                PC -= 3;
            } else if (inst == 3) {
                ADI(x.getReg1(), x.getImed());
                PC -= 3;
            } else if (inst == 4) {
                ADIF(x.getReg1(), x.getImed(), x.getImed1());
                PC -= 4;
            } else if (inst == 5) {
                BNG(x.getReg1(), x.getImed());
                PC -= 3;
            } else if (inst == 6) {
                BNGF(x.getReg1(), x.getImed());
                PC -= 3;
            } else if (inst == 7) {
                BNN(x.getReg1(), x.getImed());
                PC -= 3;
            } else if (inst == 8) {
                BNNF(x.getReg1(), x.getImed());
                PC -= 3;
            } else if (inst == 9) {
                BNP(x.getReg1(), x.getImed());
                PC -= 3;
            }
        } else if (inst < 20) {
            if (inst == 10) {
                BNPF(x.getReg1(), x.getImed());
            } else if (inst == 11) {
                BNZ(x.getReg1(), x.getImed());
            } else if (inst == 12) {
                BNZF(x.getReg1(), x.getImed());
            } else if (inst == 13) {
                BPS(x.getReg1(), x.getImed());
            } else if (inst == 14) {
                BPSF(x.getReg1(), x.getImed());
            } else if (inst == 15) {
                BZR(x.getReg1(), x.getImed());
            } else if (inst == 16) {
                BZRF(x.getReg1(), x.getImed());
            } else if (inst == 17) {
                CNV(x.getReg1(), x.getReg2());
            } else if (inst == 18) {
                DIV(x.getReg1(), x.getReg2());
            } else if (inst == 19) {
                ESC(x.getReg1(), x.getReg2());
            }
            PC -= 3;
        } else if (inst < 20) {
            if (inst == 20) {
                HLT();
                PC -= 1;
            } else if (inst == 21) {
                JMP(x.getImed());
                PC -= 2;
            } else if (inst == 22) {
                LDI(x.getReg1(), x.getImed());
                PC -= 3;
            } else if (inst == 23) {
                LDIF(x.getReg1(), x.getImed(), x.getImed1());
                PC -= 4;
            } else if (inst == 24) {
                LGT(x.getReg1());
                PC -= 2;
            } else if (inst == 25) {
                LOD(x.getReg1(), "" + x.getImed());
                PC -= 3;
            } else if (inst == 26) {
                LODF(x.getReg1(), "" + x.getImed());
                PC -= 3;
            } else if (inst == 27) {
                MVE(x.getReg1(), x.getReg2());
                PC -= 3;
            } else if (inst == 28) {
                MVEF(x.getReg1(), x.getReg2());
                PC -= 3;
            } else if (inst == 29) {
                MUL(x.getReg1(), x.getReg2());
                PC -= 3;
            }
        } else {
            if (inst == 30) {
                MULF(x.getReg1(), x.getReg2());
                PC -= 3;
            } else if (inst == 31) {
                NEG(x.getReg1());
                PC -= 2;
            } else if (inst == 32) {
                NEGF(x.getReg1());
                PC -= 2;
            } else if (inst == 33) {
                RTR();
                PC -= 1;
            } else if (inst == 34) {
                STI(x.getImed1(), "" + x.getImed());
                DS -= 1;
                PC -= 3;
            } else if (inst == 35) {
                STIF(x.getImed1(), x.getImed2(), "" + x.getImed());
                DS -= 2;
                PC -= 4;
            } else if (inst == 36) {
                STO(x.getReg1(), "" + x.getImed());
                DS -= 1;
                PC -= 3;
            } else if (inst == 37) {
                STOF(x.getReg1(), "" + x.getImed());
                DS -= 2;
                PC -= 3;
            } else if (inst == 38) {
                SUB(x.getReg1(), x.getReg2());
                PC -= 3;
            } else if (inst == 39) {
                SUBF(x.getReg1(), x.getReg2());
                PC -= 3;
            } else if (inst == 40) {
                TME(x.getReg1());
                PC -= 2;
            } else {
                System.out.println("ERRO ao remover da fila\nInstrução invalida: " + inst);
            }
        }
    }
}
