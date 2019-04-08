import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Vue extends JFrame {

	public Vue(StructureUltime s){
		JPanel container = new JPanel();
		this.setSize(500,500);
		this.setLocation(700,300);
		container.setLayout(new GridLayout(10,10));
		
		for(int i = 0; i < s.getGrille().size(); i++){
			for(int j = 0; j < s.getGrille().get(i).size(); j++){
				if(s.getGrille().get(i).get(j)!=-1){
					JButton button = new JButton();
					switch(s.getGrille().get(i).get(j).intValue()){
						case 1:
							button.setBackground(Color.BLACK);
							break;
						case 2:
							button.setBackground(Color.BLUE);
							break;
						case 3:
							button.setBackground(Color.CYAN);
							break;
						case 4:
							button.setBackground(Color.GRAY);
							break;
						case 5:
							button.setBackground(Color.GREEN);
							break;
						case 6:
							button.setBackground(Color.MAGENTA);
							break;
						case 7:
							button.setBackground(Color.ORANGE);
							break;
						case 8:
							button.setBackground(Color.PINK);
							break;
						case 9:
							button.setBackground(Color.RED);
							break;
						case 10:
							button.setBackground(Color.YELLOW);
							break;
						default:
							button.setBackground(Color.DARK_GRAY);
							break;
					}
					container.add(button);
				} else {
					container.add(new JButton());				
				}
			}
		}
		
		this.setContentPane(container);
		this.setVisible(true);
	}

}
