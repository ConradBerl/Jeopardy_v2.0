package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

//Specialized JButton
public class JeopardyButton extends JButton {

	private static final long serialVersionUID = 1L;
	private int cat;
	private int que;
	
	
	public JeopardyButton(ActionListener listener, int cat, int que, int amount){
		this.addActionListener(listener);
		this.setText(String.valueOf(amount));
		this.cat = cat;
		this.que = que;
		this.setFont(new Font("Helvetica", Font.PLAIN, 30));
	}
	
	public int getCategory(){
		return this.cat;
	}
	
	public int getQuestion(){
		return this.que;
	}
	
	
	public void selectedQuestionDisplay(String a, String q){
		Color background = Color.BLUE;
		Color foreground = Color.WHITE;
		
		//get the monitor dimensions and set the text area to 2/3 of it
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) (screenSize.getWidth() /3 * 2);
		int height = (int) (screenSize.getHeight() /3 * 2);
		
		/*
		 * Frame
		 */
		JFrame frameAnswer = new JFrame();
		frameAnswer.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frameAnswer.setLayout(new GridBagLayout());

		frameAnswer.getContentPane().setBackground(background);
		
	

		
		
		/*
		 * Text area
		 */
		JTextArea questionLabel = new JTextArea();
		questionLabel.setText(a);
		questionLabel.setFont(new Font("Tohama", Font.PLAIN, 50));

		questionLabel.setSize(new Dimension(width, height));

		questionLabel.setLineWrap(true);
		questionLabel.setWrapStyleWord(true);

		questionLabel.setBackground(background);
		questionLabel.setForeground(foreground);
		
		frameAnswer.add(questionLabel);
		
		
		
		/*
		 * Create 2 transparent overlaying panels.
		 * 1st Click = remove first layer, change text
		 * 2nd Click = remove second layer
		 */

		JPanel glass = (JPanel) frameAnswer.getGlassPane();
	    glass.setVisible(true);
		frameAnswer.setGlassPane(glass);
		
		
		
		//add a listener to react on click - with same reaction as if frame was clicked
		glass.addMouseListener(new MouseAdapter() {
			int click = 0;
			
			public void mouseClicked(MouseEvent me){
				
				//First click, show answer
				if (click == 0){
					questionLabel.setText(q);
				}
				
				//Second click, close the panel
				else if (click == 1){
					frameAnswer.dispose();
				}

				click++;
			}
		});
		
		
		/*
		 * Turn it all on
		 */
		frameAnswer.setVisible(true);
		
	}
}