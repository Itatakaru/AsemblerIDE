
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matheus
 */
public class Codify {
    public int NextInstruction;
    public boolean HasNextInstruction;
    public int Instruction;
    public boolean HasInstruction;
    
    public void codify(String instrucao){
        if(instrucao.equals("")){
            HasInstruction = false;
            return;
        }
        int position = findPositionInstruction(instrucao);
        HasInstruction = true;
        Instruction = position;
        HasNextInstruction = SalvarPrograma.isDoubleByte(position);
        if(HasNextInstruction){
            Pattern p = Pattern.compile(monstrao[position][1]);
            Matcher m = p.matcher(instrucao);
            String vaiiii = "";
            int contado = 0;
            while(m.find()){
                vaiiii = m.group();
            }
            NextInstruction = Integer.parseInt(vaiiii);
        }
    }
    
    
    

    private int findPositionInstruction(String instrucao) {
        for(int i = 0; i< 53;i++){
            Pattern p = Pattern.compile(monstrao[i][1]);
            Matcher m = p.matcher(instrucao);
            if(m.find()) return i;
        }
        return -1;
    }
    
    String[][] monstrao = new String[][]{
        {"0", "init ax"},
        {"1", "move ax,bx"},
        {"2", "move ax,cx"},
        {"3", "move bx,ax"},
        {"4", "move cx,ax"},
        {"5", "move ax,\\[(\\d+)\\]"},
        {"6", "move ax,\\[bx\\+(\\d+)\\]"},
        {"7", "move ax,\\[bp\\-(\\d+)\\]"},
        {"8", "move ax,\\[bp\\+(\\d+)\\]"},
        {"9", "move \\[(\\d+)\\], ax"},
        {"10", "move \\[bx\\+(\\d+)\\], ax"},
        {"11", "move bp,sp"},
        {"12", "move sp,bp"},
        {"13", "add ax,bx"},
        {"14", "add ax,cx"},
        {"15", "add bx,cx"},
        {"16", "sub ax,bx"},
        {"17", "sub ax,cx"},
        {"18", "sub bx,cx"},
        {"19", "inc ax"},
        {"20", "inc bx"},
        {"21", "inc cx"},
        {"22", "dec ax"},
        {"23", "dec bx"},
        {"24", "dec cx"},
        {"25", "test ax0,(\\d+)"},
        {"26", "jmp (\\d+)"},
        {"27", "call (\\d+)"},
        {"28", "ret"},
        {"29", "in ax"},
        {"30", "out ax"},
        {"31", "push ax"},
        {"32", "push bx"},
        {"33", "push cx"},
        {"34", "push bp"},
        {"35", "pop bp"},
        {"36", "pop cx"},
        {"37", "pop bx"},
        {"38", "pop ax"},
        {"39", "nop"},
        {"40", "halt"},
        {"41", "dec sp"},
        {"42", "move \\[bp\\-(\\d+)\\], ax"},
        {"43", "move \\[bp\\+(\\d+)\\], ax"},
        {"44", "move ax,\\{(\\d+)\\}"},
        {"45", "test axEqbx,(\\d+)"},
        {"46", "inc sp"},
        {"47", "move ax,sp"},
        {"48", "move sp,ax"},
        {"49", "move ax,bp"},
        {"50", "move bp,ax"},
        {"51", "iret"},
        {"52", "int (\\d+)"}
    };
}
