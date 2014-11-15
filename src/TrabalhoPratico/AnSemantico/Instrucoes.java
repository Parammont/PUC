/**
 * @author Pedro Henrique A. M. de Carvalho
 * @author Savio Mota da Silva
 * @author Thais das G. P. Mairink
 * @since 2.0
 */
package TrabalhoPratico.AnSemantico;

import TrabalhoPratico.Principal;
import TrabalhoPratico.Registros.ApontadorRegistro;
import static TrabalhoPratico.Registros.ApontadorRegistro.getApontador;
import TrabalhoPratico.Registros.RegistroLexico;
import java.util.ArrayList;

public class Instrucoes {

    public static ApontadorRegistro rl = getApontador();
    public static RegistroLexico rlAntigo = new RegistroLexico();
    public static int contTemp = 0;          //contador de temporario
    public static int contRot = 0;           //contador de rotulos
    public static int PC = 0;
    private static ArrayList<Rotulo> dadoRotulo = new ArrayList<Rotulo>();

    public static int DS = 0;           //armazena o endereço da área de dados da memória principal

    //ADD	1	RegD <- RegD + RegO	ADD A,B
    public void ADD(String regD, String regO) {
        Principal.arquivo.gravarAsm("ADD " + regD + "," + regO);
        Principal.arquivo.gravarExe(1);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
        PC += 3;
    }

    //ADDF	2	RegD <- RegD + RegO	ADDF A,B
    public void ADDF(String regD, String regO) {
        Principal.arquivo.gravarAsm("ADDF " + regD + "," + regO);
        Principal.arquivo.gravarExe(2);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
        PC += 3;
    }

    //ADI	3	RegD <- RegD + Imed	ADI A,#1
    public void ADI(String regD, int Imed) {
        Principal.arquivo.gravarAsm("ADI " + regD + ",#" + Imed);
        Principal.arquivo.gravarExe(3);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(Imed);
        PC += 3;
    }

    //ADIF	4	RegD <- RegD + Imed	ADIF A,#1.0
    public void ADIF(String regD, String Imed) {
        Principal.arquivo.gravarAsm("ADI " + regD + ",#" + Imed);
        Principal.arquivo.gravarExe(4);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(Imed);/// Aqui tem que separar parte inteira de fração *************************************************************************************
        PC += 4;
        System.out.println("Atenção ADIF ERRADO");
    }

    //BNG	5	se (Reg<0) PC <- CS+Desl	BNG A,10(CS)
    public void BNG(String reg, Rotulo rot) {
        Principal.arquivo.gravarAsm("BNG " + reg + "," + rot.getNome() + "(CS)");
        PC += 3;
        Principal.arquivo.gravarExe(5);
        Principal.arquivo.gravarExe(getReg(reg));
        Principal.arquivo.gravarExe(rot.end());
    }

    //BNGF	6	se (Reg<0) PC <- CS+Desl	BNGF A,10(CS)
    public void BNGF(String reg, Rotulo rot) {
        Principal.arquivo.gravarAsm("BNGF " + reg + "," + rot.getNome() + "(CS)");
        PC += 3;
        Principal.arquivo.gravarExe(6);
        Principal.arquivo.gravarExe(getReg(reg));
        Principal.arquivo.gravarExe(rot.end());
    }

    //BNN	7	se (Reg≥0) PC <- CS+Desl	BNN A,10(CS)
    public void BNN() {
        Rotulo rot = novoRot();
        Principal.arquivo.gravarAsm("BNN A, " + rot.getNome());
        PC += 3;
        Principal.arquivo.gravarExe(7);
        Principal.arquivo.gravarExe(1);
        Principal.arquivo.gravarExe(rot.end());
    }

    //BNNF	8	se (Reg≥0) PC <- CS+Desl 	BNNF A,10(CS)
    public void BNNF() {
        Rotulo rot = novoRot();
        Principal.arquivo.gravarAsm("BNNF A, " + rot.getNome());
        PC += 3;
        Principal.arquivo.gravarExe(8);
        Principal.arquivo.gravarExe(1);
        Principal.arquivo.gravarExe(rot.end());
    }

    //BNP	9	se (Reg≤0) PC <- CS+Desl	BNP A,10(CS)
    public void BNP(String reg, Rotulo rot) {
        Principal.arquivo.gravarAsm("BNP " + reg + "," + rot.getNome() + "(CS)");
        PC += 3;
        Principal.arquivo.gravarExe(9);
        Principal.arquivo.gravarExe(getReg(reg));
        Principal.arquivo.gravarExe(rot.end());
    }

    //BNPF	10	se (Reg≤0) PC <- CS+Desl	BNPF A,10(CS)
    public void BNPF(String reg, Rotulo rot) {
        Principal.arquivo.gravarAsm("BNPF " + reg + "," + rot.getNome() + "(CS)");
        PC += 3;
        Principal.arquivo.gravarExe(10);
        Principal.arquivo.gravarExe(getReg(reg));
        Principal.arquivo.gravarExe(rot.end());
    }

    //BNZ	11	se (Reg≠0) PC <- CS+Desl	BNZ A,10(CS)
    public void BNZ(String reg, Rotulo rot) {
        Principal.arquivo.gravarAsm("BNZ " + reg + "," + rot.getNome() + "(CS)");
        PC += 3;
        Principal.arquivo.gravarExe(11);
        Principal.arquivo.gravarExe(getReg(reg));
        Principal.arquivo.gravarExe(rot.end());
    }

    //BNZF	12	se (Reg≠0) PC <- CS+Desl 	BNZF A,10(CS)
    public void BNZF(String reg, Rotulo rot) {
        Principal.arquivo.gravarAsm("BNZF " + reg + "," + rot.getNome() + "(CS)");
        PC += 3;
        Principal.arquivo.gravarExe(12);
        Principal.arquivo.gravarExe(getReg(reg));
        Principal.arquivo.gravarExe(rot.end());
    }

    //BPS	13	se (Reg>0) PC <- CS+Desl	BPS A,10(CS)
    public void BPS() {
        Rotulo rot = novoRot();
        Principal.arquivo.gravarAsm("BPS A, " + rot.getNome());
        PC += 3;
        Principal.arquivo.gravarExe(13);
        Principal.arquivo.gravarExe(1);
        Principal.arquivo.gravarExe(rot.end());
    }

    //BPSF	14	se (Reg>0) PC <- CS+Desl	BPSF A,10(CS)
    public void BPSF() {
        Rotulo rot = novoRot();
        Principal.arquivo.gravarAsm("BPSF A, " + rot.getNome());
        PC += 3;
        Principal.arquivo.gravarExe(14);
        Principal.arquivo.gravarExe(1);
        Principal.arquivo.gravarExe(rot.end());
    }

    //BZR	15	se (Reg=0) PC <- CS+Desl	BZR A,10(CS)
    public void BZR(String reg, Rotulo rot) {
        Principal.arquivo.gravarAsm("BZR " + reg + "," + rot.getNome() + "(CS)");
        PC += 3;
        Principal.arquivo.gravarExe(15);
        Principal.arquivo.gravarExe(getReg(reg));
        Principal.arquivo.gravarExe(rot.end());
    }

    //BZRF	16	se (Reg=0) PC <- CS+Desl	BZRF A,10(CS)
    public void BZRF() {
        Rotulo rot = novoRot();
        Principal.arquivo.gravarAsm("BZRF A, " + rot.getNome());
        PC += 3;
        Principal.arquivo.gravarExe(16);
        Principal.arquivo.gravarExe(1);
        Principal.arquivo.gravarExe(rot.end());
    }

    //CNV	17	RegD <- RegO		CNV A,A
    public void CNV(String regD, String regO) {
        Principal.arquivo.gravarAsm("CNV " + regD + "," + regO);
        PC += 3;
        Principal.arquivo.gravarExe(17);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
    }

    //DIV	18	RegD <- RegD / RegO	DIV A,B
    public void DIV(String regD, String regO) {
        Principal.arquivo.gravarAsm("DIV " + regD + "," + regO);
        PC += 3;
        Principal.arquivo.gravarExe(18);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
    }

    //ESC	19	Reg1 -> escala <- Reg2	ESC A,B
    public void ESC() {
        Principal.arquivo.gravarAsm("ESC A,B");
        PC += 3;
        Principal.arquivo.gravarExe(19);
        Principal.arquivo.gravarExe(1);
        Principal.arquivo.gravarExe(2);
    }

    //HLT	20	----------------------	HLT
    public void HLT() {
        Principal.arquivo.gravarAsm("HLT");
        PC += 1;
        Principal.arquivo.gravarExe(20);
    }

    //JMP	21	PC <- CS+Desl		JMP 10(CS)
    public void JMP(int end) {
        Principal.arquivo.gravarAsm("JMP " + end);
        PC += 2;
        Principal.arquivo.gravarExe(21);
        Principal.arquivo.gravarExe(end);
    }

    //LDI	22	RegD <- Imed		LDI A,#1
    public void LDI(String regD, int Imed) {
        Principal.arquivo.gravarAsm("LDI " + regD + ",#" + Imed);
        PC += 3;
        Principal.arquivo.gravarExe(22);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(Imed);
    }

    //LDIF	23	RegD <- Imed		LDIF A,#1.0
    public void LDIF(String regD, int Imed) {
        Principal.arquivo.gravarAsm("LDIF " + regD + ",#" + Imed);
        PC += 4;
        Principal.arquivo.gravarExe(23);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(Imed);
        Principal.arquivo.gravarExe(Imed);/// Aqui tem que separar parte inteira de fração *************************************************************************************
        System.out.println("Atenção LDIF ERRADO");
    }

    //LGT	24	LuzCor <- Reg		LGT A
    public void LGT(String reg) {
        Principal.arquivo.gravarAsm("LGT " + reg);
        PC += 2;
        Principal.arquivo.gravarExe(24);
        Principal.arquivo.gravarExe(getReg(reg));
    }

    //LOD	25	RegD <- M[DS+Desl]	LOD A,10(DS)
    public void LOD(String regD, String M) {
        Principal.arquivo.gravarAsm("LOD " + regD + "," + M + "(DS)");
        PC += 3;
        Principal.arquivo.gravarExe(25);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(M);
    }

    //LODF	26	RegD <- M[DS+Desl]	LOD A,10(DS)
    public void LODF(String regD, String M) {
        Principal.arquivo.gravarAsm("LODF " + regD + "," + M + "(DS)");
        PC += 3;
        Principal.arquivo.gravarExe(26);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(M);
    }

    //MVE	27	RegD <- RegO		MVE A,B
    public void MVE(String regD, String regO) {
        Principal.arquivo.gravarAsm("MVE " + regD + "," + regO);
        PC += 3;
        Principal.arquivo.gravarExe(26);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
    }

    //MVEF	28	RegD <- RegO		MVE A,B
    public void MVEF(String regD, String regO) {
        Principal.arquivo.gravarAsm("MVEF " + regD + "," + regO);
        PC += 3;
        Principal.arquivo.gravarExe(28);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
    }

    //MUL	29	RegD <- RegD x RegO	MUL A,B
    public void MUL(String regD, String regO) {
        Principal.arquivo.gravarAsm("MUL " + regD + "," + regO);
        PC += 3;
        Principal.arquivo.gravarExe(29);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
    }

    //MULF	30	RegD <- RegD x RegO	MUL A,B
    public void MULF(String regD, String regO) {
        Principal.arquivo.gravarAsm("MULF " + regD + "," + regO);
        PC += 3;
        Principal.arquivo.gravarExe(30);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
    }

    //NEG	31	Reg <- - Reg		NEG A
    public void NEG(String reg) {
        Principal.arquivo.gravarAsm("NEG " + reg);
        PC += 2;
        Principal.arquivo.gravarExe(31);
        Principal.arquivo.gravarExe(getReg(reg));
    }

//NEGF	32	Reg <- - Reg		NEGF A
//RTR	33	----------------------	RTR
    //STI	34	M[DS+Desl] <- Imed	STI #1,10(DS)       ******************************************************************************
    public void STI() {
        String[] print = sti(rlAntigo.getInteiro());
        Principal.arquivo.gravarAsm(print[0]);
        Principal.arquivo.gravarExe(34);
        Principal.arquivo.gravarExe(print[1]);
        Principal.arquivo.gravarExe(print[2]);
    }

    public String[] sti(int num) {
        String[] print = new String[3];

        String s_int = "" + num;
        print[0] = "STI #" + s_int + " , " + DS + "(DS)";
        rlAntigo.setEndMen(DS);
        print[1] = s_int;
        print[2] = "" + DS;
        DS += 1;
        PC += 3;
        return print;
    }

    //STIF	34	M[DS+Desl] <- Imed	STI #1,10(DS)       ******************************************************************************
    public void STIF() {
        String[] print = stif(rlAntigo.getInteiro(), rlAntigo.getDecimal());
        Principal.arquivo.gravarAsm(print[0]);
        Principal.arquivo.gravarExe(35);
        //Principal.arquivo.gravarExe(print[1]);
        for (int i = 1; i < print.length; i++) {
            Principal.arquivo.gravarExe(print[i]);
        }
    }

    public String[] stif(int inte, int deci) {
        String[] print = new String[4];

        String s_int = "" + inte;
        rlAntigo.setEndMen(DS);
        String s_dec = paraString(deci);
        print[0] = "STIF  #" + s_int + "." + s_dec + " , " + rlAntigo.getEndMen() + "(DS)";
        print[1] = s_int;
        print[2] = s_dec;
        print[3] = "" + rlAntigo.getEndMen();
        DS += 2;
        PC += 3;
        return print;
    }

    //STO	36	M[DS+Desl] <- Reg	STO A,10(DS)
    public void STO(String regD, String M) {
        Principal.arquivo.gravarAsm("STO " + regD + "," + M + "(DS)");
        PC += 3;
        Principal.arquivo.gravarExe(36);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(M));
    }

    //STOF	37	M[DS+Desl] <- Reg	STOF A,10(DS)
    public void STOF(String regD, String M) {
        Principal.arquivo.gravarAsm("STOF " + regD + "," + M + "(DS)");
        PC += 3;
        Principal.arquivo.gravarExe(36);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(M));
    }

    //SUB	38	RegD <- RegD – RegO	SUB A,B
    public void SUB(String regD, String regO) {
        Principal.arquivo.gravarAsm("SUB " + regD + "," + regO);
        Principal.arquivo.gravarExe(38);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
        PC += 3;
    }

    //SUBF	39	RegD <- RegD – RegO	SUBF A,B
    public void SUBF(String regD, String regO) {
        Principal.arquivo.gravarAsm("SUBF " + regD + "," + regO);
        Principal.arquivo.gravarExe(39);
        Principal.arquivo.gravarExe(getReg(regD));
        Principal.arquivo.gravarExe(getReg(regO));
        PC += 3;
    }

//TIME	40	----------------------	TIME A

    private String paraString(int num) {
        String temp = "" + num;
        while (temp.length() < 4) {
            temp = "0" + temp;
        }

        return temp;
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
