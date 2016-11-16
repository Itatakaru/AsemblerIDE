
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matheus
 */
public class SelectProgram {
    public static void main(String[] args) throws IOException {
        new SelectProgram().run();
    }
    private short mem[];
    public void run() throws FileNotFoundException, IOException{
        mem = new short[1024];
        JFileChooser choose = new JFileChooser();
        int returnVal = choose.showOpenDialog(null);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = choose.getSelectedFile();
            //This is where a real application would open the file.
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String valor = br.readLine();
            Codify cdfy = new Codify();
            int inicio = 0;
            while(valor != null){
                cdfy.codify(valor);
                if(cdfy.HasInstruction){
                    mem[inicio] = (short)cdfy.Instruction;
                    if(cdfy.HasNextInstruction){
                        mem[inicio] = (short)cdfy.NextInstruction;
                        valor = br.readLine();
                        inicio++;
                    }
                }
                inicio++;
                valor = br.readLine();
            }


            MVM.decodificador(mem, 0, 0);
        } else {
        }
        
        
    }
}
