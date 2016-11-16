
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author
 */
public class MainMVM {
    
    public static void main(String[] args) throws IOException {
        //RunMVM maquina = new RunMVM();
        //maquina.Inicializa();
        //maquina.Run();
    	//try { new TestTextAre(); } catch (IOException e) { }
    	JFrame panel = new JFrame();
    	panel.setBackground(Color.green);
    	panel.setSize(500, 500);
    	JTextArea console = new JTextArea();
    	NumberedBorder border = new NumberedBorder();
    	console.setBorder(border);
    	JScrollPane scroll = new JScrollPane(console);
    	Insets inset = border.getBorderInsets(scroll);
    	console.setAutoscrolls(true);
    	console.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	Point p = scroll.getMousePosition();
                int x = p.x;//evt.getPoint().x;
                int y = p.y;//evt.getPoint().y;
                
            	Point localizacao = console.getLocation();
            	int iniY = localizacao.y;
                int maxY = console.getHeight() + iniY;
                int iniX = localizacao.x;
                int maxX = inset.left;
                boolean isBorder = (x < maxX && x > iniX)&&(y < maxY && x > iniY);
                if(isBorder){
                	border.ClickPos(y - iniY, console.getHeight());
                	panel.repaint();
                }
            }
        });
    	panel.add( scroll);
    	
    	panel.show(true);
    }
}