
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 
 */
public class SalvarPrograma {

    private static File program = new File("c:/Temp/program.txt");
    private static File clock = new File("c:/Temp/clock.txt");
    private static String aux;
    private static short aux2;
    private static String tabelaRealocacao;

    public static File getProgram() {
        return program;
    }

    public static void setProgram(File program) {
        SalvarPrograma.program = program;
    }

    public static String getAux() {
        return aux;
    }

    public static void setAux(String aux) {
        SalvarPrograma.aux = aux;
    }

    public static short getAux2() {
        return aux2;
    }

    public static void setAux2(short aux2) {
        SalvarPrograma.aux2 = aux2;
    }

    public static String getTabelaRealocacao() {
        return tabelaRealocacao;
    }

    public static void setTabelaRealocacao(String tabelaRealocacao) {
        SalvarPrograma.tabelaRealocacao = tabelaRealocacao;
    }
    
    public static void salvar(int inicio, int fim, short mem[]) throws IOException {
        if (getProgram() == null) {
            setProgram(new File("c:/Temp/program.txt"));
        }
        FileWriter fw = new FileWriter(getProgram(), false);
        BufferedWriter bw = new BufferedWriter(fw);
        int start = inicio;
        int next = inicio;
        tabelaRealocacao = "";
        while (next <= fim) {
            setAux2(mem[next]);
            //bw.write(next + ": ");
            //Instrução de 2 bytes com posição de memória
            if (isDoubleByte(getAux2())) {
                String comando = decodificar(getAux2());
                next++;
                bw.write(String.format(comando + "\r\n\r\n", mem[next] ));//,next));
                tabelaRealocacao += (next-start) + "\r\n";
            } else {
                //instruções de 2 bytes com chaves
//                if (getAux2() == 44) {
//                    bw.write(decodificar(getAux2()));
//                    next++;
//                    bw.write(mem[next] + "}\r\n");
//                } else {
                    //Instrução de 1 byte
                    bw.write(decodificar(getAux2()) + "\r\n");
//                }
            }
            next++;
        }
        //Adiciona a tabela de realocação ao final do programa separado por "|"
        //bw.write("|\n" + tabelaRealocacao);
        bw.close();
        fw.close();

    }
    
    public static void SalvarTempo(int quantidadeTempo) throws IOException{
        if (getClock() == null) {
            setClock(new File("c:/Temp/clock.txt"));
        }
        
        FileWriter fw = new FileWriter(getClock(), false);
        BufferedWriter bw = new BufferedWriter(fw);
        
        bw.write(String.valueOf(quantidadeTempo));
        bw.close();
        fw.close();
    }

    public static int CarregarTempo(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(SalvarPrograma.clock));
            return Integer.parseInt(br.readLine());
        }
        catch(Exception exc){
            return 0;
        }
    }
    
    public static File getClock(){
        return clock;
    }
    
    public static void setClock(File clock){
        SalvarPrograma.clock = clock;
    }
    
    public static boolean isDoubleByte( int value ) {
        return value == 5
                || value == 6
                || value == 7
                || value == 8
                || value == 9
                || value == 10
                || value == 25
                || value == 26
                || value == 27
                || value == 42
                || value == 43
                || value == 45
                || value == 44
                || value == 52;
    }

    public static void ler(short mem[], int posicao) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(getProgram());
        BufferedReader br = new BufferedReader(fr);
        int posicaoIni = posicao;
        boolean terminouPrograma = false;
        while (br.ready()) {
            aux = br.readLine();
            if (aux.equals("")) {
            } else {
                if (aux.equals("|")) {
                    terminouPrograma = true;
                } else {
                    if (!terminouPrograma) {
                        mem[posicao] = Short.parseShort(aux);
                        posicao++;
                    } else {
                        mem[Integer.parseInt(aux)+posicaoIni] += posicaoIni;
                    }
                    
                }
            }
        }
        br.close();
        fr.close();
    }
    
    public static String decodificar(short instrucao){
        
        switch(instrucao){
        
            case 0:// "init ax"
                    return "init ax";

            case 1:// "move ax,bx"
                return "move ax, bx";

            case 2:// "move ax,cx",
                return "move ax, cx";

            case 3:// "move bx,ax"
                return "move bx, ax";

            case 4:// "move cx,ax"
                return "move cx, ax";

            case 5:// "move ax,[",
                return "move ax, [%s]";

            case 6:// "move ax,[bx+"
                return "move ax, [bx+%s]";

            case 7:// "move ax,[bp-"
                return "move ax, [bp-%s]";

            case 8://"move ax,[bp+"
                return "move ax, [bp+%s]";

            case 9://"move ["
                return "move [%s], ax";

            case 10://"move [bx+"
                return "move [bx+%s], ax";

            case 11://"move bp,sp"
                return "move bp, sp";

            case 12://"move sp,bp"
                return "move sp, bp";

            case 13://"add ax,bx"
                return "add ax, bx";

            case 14://"add ax,cx"
                return "add ax, cx";

            case 15://"add bx,cx"
                return "add bx, cx";

            case 16://"sub ax,bx"
                return "sub ax, bx";

            case 17://"sub ax,cx"
                return "sub ax, cx";

            case 18://"sub bx,cx"
                return "sub bx, cx";

            case 19://"inc ax"
                return "inc ax";

            case 20://"inc bx"
                return "inc bx";

            case 21://"inc cx"
                return "inc cx";

            case 22://"dec ax"
                return "dec ax";

            case 23://"dec bx"
                return "dec bx";

            case 24://"dec cx"
                return "dec cx";

            case 25://"test ax0,"
                return "test ax0, %s";

            case 26://"jmp "
                return "jmp %s";

            case 27://"call"
                return "call %s";

            case 28://"ret"
                return "ret";

            case 29://"in ax"
                return "in ax";

            case 30://"out ax"
                return "out ax";

            case 31://"push ax"
                return "push ax";

            case 32://"push bx"
                return "push bx";

            case 33://"push cx"
                return "push cx";

            case 34://"push bp"
                return "push bp";

            case 35://"pop bp"
                return "pop bp";

            case 36://"pop cx"
                return "pop cx";

            case 37://"pop bx"
                return "pop bx";

            case 38://"pop ax"
                return "pop ax";

            case 39://"nop"
                return "nop";

            case 40: //"halt"
                return "halt";

            case 41://"dec sp"
                return "dec sp";

            case 42://"move [bp-"
                return "move [bp-%s], ax";

            case 43://"move [bp+"
                return "move [bp+%s], ax";

            case 44://"move ax,{"
                return "move ax, {%s}";

            case 45://"test axEqbx,"
                return "test axEqbx, %s";

            case 46://"inc sp"
                return "inc sp";

            case 47://"move ax,sp"
                return "move ax, sp";

            case 48://"move sp,ax"
                return "move sp, ax";

            case 49://"move ax,bp"
                return "move ax, bp";

            case 50://"move bp,ax"
                return "move bp, ax";

            case 51://"iret"
                return "iret";

            case 52://"int"
                return "int %s";

            case 53://"sub bx,ax"
                return "sub bx, ax";

            default:
                return "";
        }
    }
    
    
    public void getValueExpression(String expression, String Line){
        Pattern p = Pattern.compile(expression);
        //Matcher m = p.
    }
}
