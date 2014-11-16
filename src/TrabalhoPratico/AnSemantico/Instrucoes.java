/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico.AnSemantico;

import static TrabalhoPratico.Registros.ApontadorRegistro.getApontador;
import TrabalhoPratico.AnSemantico.Retrocorreção.dados;
import TrabalhoPratico.Registros.ApontadorRegistro;
import TrabalhoPratico.Registros.RegistroLexico;
import TrabalhoPratico.Principal;
import java.util.ArrayList;

public class Instrucoes {

    public static ApontadorRegistro rl = getApontador();
    public static RegistroLexico rlAntigo = new RegistroLexico();
    public static int contTemp = 0;          //contador de temporario
    public static int contRot = 0;           //contador de rotulos
    public static int PC = 0;
    private static ArrayList<Rotulo> dadoRotulo = new ArrayList<Rotulo>();
    private byte desvios = 0;

    public static int DS = 0;           //armazena o endereço da área de dados da memória principal

    //ADD	1	RegD <- RegD + RegO	ADD A,B
    public void ADD(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(1, regD, regO);
        } else {
            Principal.arquivo.gravarAsm("ADD " + regD + "," + regO);
            Principal.arquivo.gravarExe(1);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //ADDF	2	RegD <- RegD + RegO	ADDF A,B
    public void ADDF(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(2, regD, regO);
        } else {
            Principal.arquivo.gravarAsm("ADDF " + regD + "," + regO);
            Principal.arquivo.gravarExe(2);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //ADI	3	RegD <- RegD + Imed	ADI A,#1
    public void ADI(String regD, int Imed) {
        if (desvios > 0) {
            dados aff = new dados(3, regD, Imed);
        } else {
            Principal.arquivo.gravarAsm("ADI " + regD + ",#" + Imed);
            Principal.arquivo.gravarExe(3);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(Imed);
        }
        PC += 3;
    }

    //ADIF	4	RegD <- RegD + Imed	ADIF A,#1.0
    public void ADIF(String regD, int inte, int deci) {
        if (desvios > 0) {
            dados aff = new dados(4, regD, inte, deci);
        } else {
            Principal.arquivo.gravarAsm("ADI " + regD + ",#" + inte + "." + deci);
            Principal.arquivo.gravarExe(4);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(inte);
            Principal.arquivo.gravarExe(deci);
        }
        PC += 4;
        System.out.println("Atenção ADIF ERRADO");
    }

    //BNG	5	se (Reg<0) PC <- CS+Desl	BNG A,10(CS)
    public void BNG(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(5, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BNG " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(5);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BNGF	6	se (Reg<0) PC <- CS+Desl	BNGF A,10(CS)
    public void BNGF(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(6, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BNGF " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(6);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BNN	7	se (Reg≥0) PC <- CS+Desl	BNN A,10(CS)
    public void BNN(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(7, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BNN " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(7);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BNNF	8	se (Reg≥0) PC <- CS+Desl 	BNNF A,10(CS)
    public void BNNF(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(8, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BNNF " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(8);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BNP	9	se (Reg≤0) PC <- CS+Desl	BNP A,10(CS)
    public void BNP(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(9, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BNP " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(9);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BNPF	10	se (Reg≤0) PC <- CS+Desl	BNPF A,10(CS)
    public void BNPF(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(10, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BNPF " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(10);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BNZ	11	se (Reg≠0) PC <- CS+Desl	BNZ A,10(CS)
    public void BNZ(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(11, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BNZ " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(11);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BNZF	12	se (Reg≠0) PC <- CS+Desl 	BNZF A,10(CS)
    public void BNZF(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(12, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BNZF " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(12);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BPS	13	se (Reg>0) PC <- CS+Desl	BPS A,10(CS)
    public void BPS(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(13, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BPS " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(13);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BPSF	14	se (Reg>0) PC <- CS+Desl	BPSF A,10(CS)
    public void BPSF(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(14, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BPSF " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(14);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BZR	15	se (Reg=0) PC <- CS+Desl	BZR A,10(CS)
    public void BZR(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(15, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BZR " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(15);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //BZRF	16	se (Reg=0) PC <- CS+Desl	BZRF A,10(CS)
    public void BZRF(String reg, Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(16, reg, rot);
        } else {
            Principal.arquivo.gravarAsm("BZRF " + reg + "," + rot.getNome());
            Principal.arquivo.gravarExe(16);
            Principal.arquivo.gravarExe(getReg(reg));
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 3;
    }

    //CNV	17	RegD <- RegO		CNV A,A
    public void CNV(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(17, regD, regO);
        } else {
            Principal.arquivo.gravarAsm("CNV " + regD + "," + regO);
            Principal.arquivo.gravarExe(17);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //DIV	18	RegD <- RegD / RegO	DIV A,B
    public void DIV(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(18, regD, regO);
        } else {
            Principal.arquivo.gravarAsm("DIV " + regD + "," + regO);
            Principal.arquivo.gravarExe(18);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //ESC	19	Reg1 -> escala <- Reg2	ESC A,B
    public void ESC(String reg1, String reg2) {
        if (desvios > 0) {
            dados aff = new dados(19, reg1, reg2);
        } else {
            Principal.arquivo.gravarAsm("ESC " + reg1 + "," + reg2);
            Principal.arquivo.gravarExe(19);
            Principal.arquivo.gravarExe(getReg(reg1));
            Principal.arquivo.gravarExe(getReg(reg2));
        }
        PC += 3;
    }

    //HLT	20	----------------------	HLT
    public void HLT() {
        if (desvios > 0) {
            dados aff = new dados(20);
        } else {
            Principal.arquivo.gravarAsm("HLT");
            Principal.arquivo.gravarExe(20);
        }
        PC += 1;
    }

    //JMP	21	PC <- CS+Desl		JMP 10(CS)
    public void JMP(Rotulo rot) {
        if (desvios > 0) {
            dados aff = new dados(21, rot);
        } else {
            Principal.arquivo.gravarAsm("JMP " + rot.getNome());
            Principal.arquivo.gravarExe(21);
            Principal.arquivo.gravarExe(rot.end());
        }
        PC += 2;
    }

    //LDI	22	RegD <- Imed		LDI A,#1
    public void LDI(String regD, int Imed) {
        if (desvios > 0) {
            dados aff = new dados(22, regD, Imed);
        } else {
            Principal.arquivo.gravarAsm("LDI " + regD + ",#" + Imed);
            Principal.arquivo.gravarExe(22);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(Imed);
        }
        PC += 3;
    }

    //LDIF	23	RegD <- Imed		LDIF A,#1.0
    public void LDIF(String regD, int inte, int deci) {
        if (desvios > 0) {
            dados aff = new dados(23, regD, inte, deci);
        } else {
            Principal.arquivo.gravarAsm("LDIF " + regD + ",#" + inte + "." + deci);
            Principal.arquivo.gravarExe(23);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(inte);
            Principal.arquivo.gravarExe(deci);
        }
        PC += 4;
    }

    //LGT	24	LuzCor <- Reg		LGT A
    public void LGT(String reg) {
        if (desvios > 0) {
            dados aff = new dados(24, reg);
        } else {
            Principal.arquivo.gravarAsm("LGT " + reg);
            Principal.arquivo.gravarExe(24);
            Principal.arquivo.gravarExe(getReg(reg));
        }
        PC += 2;
    }

    //LOD	25	RegD <- M[DS+Desl]	LOD A,10(DS)
    public void LOD(String regD, String M) {
        if (desvios > 0) {
            dados aff = new dados(25, regD, M);
        } else {
            Principal.arquivo.gravarAsm("LOD " + regD + "," + M + "(DS)");
            Principal.arquivo.gravarExe(25);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(M);
        }
        PC += 3;
    }

    //LODF	26	RegD <- M[DS+Desl]	LOD A,10(DS)
    public void LODF(String regD, String M) {
        if (desvios > 0) {
            dados aff = new dados(26, regD, M);
        } else {
            Principal.arquivo.gravarAsm("LODF " + regD + "," + M + "(DS)");
            Principal.arquivo.gravarExe(26);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(M);
        }
        PC += 3;
    }

    //MVE	27	RegD <- RegO		MVE A,B
    public void MVE(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(27, regD, regO);
        } else {
            Principal.arquivo.gravarAsm("MVE " + regD + "," + regO);
            Principal.arquivo.gravarExe(27);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //MVEF	28	RegD <- RegO		MVE A,B
    public void MVEF(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(28, regD, regO);
        } else {
            Principal.arquivo.gravarAsm("MVEF " + regD + "," + regO);
            Principal.arquivo.gravarExe(28);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //MUL	29	RegD <- RegD x RegO	MUL A,B
    public void MUL(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(29, regD, regO);
        } else {
            Principal.arquivo.gravarAsm("MUL " + regD + "," + regO);
            Principal.arquivo.gravarExe(29);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //MULF	30	RegD <- RegD x RegO	MUL A,B
    public void MULF(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(30, regD, regO);
        } else {
            Principal.arquivo.gravarAsm("MULF " + regD + "," + regO);
            Principal.arquivo.gravarExe(30);
            Principal.arquivo.gravarExe(getReg(regD));
            Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //NEG	31	Reg <- - Reg		NEG A
    public void NEG(String reg) {
        if (desvios > 0) {
            dados aff = new dados(30, reg);
        } else {
        Principal.arquivo.gravarAsm("NEG " + reg);
        Principal.arquivo.gravarExe(31);
        Principal.arquivo.gravarExe(getReg(reg));
        }
        PC += 2;
    }

    //NEGF	32	Reg <- - Reg		NEGF A
    public void NEGF(String reg) {
        if (desvios > 0) {
            dados aff = new dados(32, reg);
        } else {
        Principal.arquivo.gravarAsm("NEGF " + reg);
        Principal.arquivo.gravarExe(32);
        Principal.arquivo.gravarExe(getReg(reg));
        }
        PC += 2;
    }

    //RTR	33	----------------------	RTR
    public void RTR(String reg) {
        if (desvios > 0) {
            dados aff = new dados(33, reg);
        } else {
        Principal.arquivo.gravarAsm("RTR");
        Principal.arquivo.gravarExe(33);
        }
        PC += 1;
    }

    //STI	34	M[DS+Desl] <- Imed	STI #1,10(DS)
    public void STI(int Imed, String M) {
        if (desvios > 0) {
            dados aff = new dados(34, M, Imed);
        } else {
            Principal.arquivo.gravarAsm("STI #" + Imed + "," + M + "(DS)");
            Principal.arquivo.gravarExe(34);
            Principal.arquivo.gravarExe(Imed);
            Principal.arquivo.gravarExe(M);
        }
        DS += 1;
        PC += 3;
    }

    //STIF	35	M[DS+Desl] <- Imed	STIF #1.0,10(DS)
    public void STIF(int inte, int deci, String M) {
        if (desvios > 0) {
            dados aff = new dados(35, M, inte, deci);
        } else {
            Principal.arquivo.gravarAsm("STIF #" + inte + "." + deci + "," + M + "(DS)");
            Principal.arquivo.gravarExe(35);
            Principal.arquivo.gravarExe(inte);
            Principal.arquivo.gravarExe(deci);
            Principal.arquivo.gravarExe(M);
        }
        DS += 2;
        PC += 4;
    }

    //STO	36	M[DS+Desl] <- Reg	STO A,10(DS)
    public void STO(String regD, String M) {
        if (desvios > 0) {
            dados aff = new dados(36, regD, M);
        } else {
        Principal.arquivo.gravarAsm("STO " + regD + "," + M + "(DS)");
        Principal.arquivo.gravarExe(36);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(M));
        }
        DS += 1;
        PC += 3;
    }

    //STOF	37	M[DS+Desl] <- Reg	STOF A,10(DS)
    public void STOF(String regD, String M) {
        if (desvios > 0) {
            dados aff = new dados(37, regD, M);
        } else {
        Principal.arquivo.gravarAsm("STOF " + regD + "," + M + "(DS)");
        Principal.arquivo.gravarExe(37);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(M));
        }
        DS += 2;
        PC += 3;
    }

    //SUB	38	RegD <- RegD – RegO	SUB A,B
    public void SUB(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(38, regD, regO);
        } else {
        Principal.arquivo.gravarAsm("SUB " + regD + "," + regO);
        Principal.arquivo.gravarExe(38);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

    //SUBF	39	RegD <- RegD – RegO	SUBF A,B
    public void SUBF(String regD, String regO) {
        if (desvios > 0) {
            dados aff = new dados(39, regD, regO);
        } else {
        Principal.arquivo.gravarAsm("SUBF " + regD + "," + regO);
        Principal.arquivo.gravarExe(39);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
        }
        PC += 3;
    }

//TIME	40	----------------------	TIME A
    public void TIME(String regD) {
        if (desvios > 0) {
            dados aff = new dados(40, regD);
        } else {
        Principal.arquivo.gravarAsm("TIME " + regD);
        Principal.arquivo.gravarExe(40);
        Principal.arquivo.gravarExe(getReg(regD));
        }
        PC += 2;
    }
    
    
    private String paraString(int num) {
        String temp = "" + num;
        while (temp.length() < 4) {
            temp = "0" + temp;
        }

        return temp;
    }

    public void fimDesvio(Rotulo rot) {
        Principal.arquivo.gravarAsm(rot.getNome() + ":");
        //Principal.arquivo.gravarExe(rot.end());
        desvios--;
    }

    public int novoTemp(int tamanho) {
        DS += tamanho;
        return (DS - tamanho);
    }

    public Rotulo novoRot() {
        ++contRot;
        Rotulo resp = new Rotulo("ROT" + contRot, PC, -1);
        dadoRotulo.add(resp);
        return resp;
    }

    private int getReg(String regX) {
        switch (regX) {
            case "A":
                return 1;
            case "AX":
                return 1;
            case "B":
                return 2;
            case "BX":
                return 2;
            case "C":
                return 3;
            case "CX":
                return 3;
            case "D":
                return 4;
            case "DX":
                return 4;
            case "E":
                return 5;
            case "EX":
                return 5;
            case "F":
                return 6;
            case "FX":
                return 6;
            default:
                return 0;
        }
    }
}
