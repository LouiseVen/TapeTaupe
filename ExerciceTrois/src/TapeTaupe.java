import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TapeTaupe extends Canvas implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Point position;
	int espacementColonnes;
	int espacementLignes;
	int score;
	Timer gameTimer;
	int gameDuration = 20;
	JLabel timerLabel;



	TapeTaupe(){
		Random rand = new Random();
		position = new Point(rand.nextInt(10), rand.nextInt(10));
		addMouseListener(this);

		score = 0;

		timerLabel = new JLabel("Time remaining: " + gameDuration + " seconds");
		gameTimer = new Timer(gameDuration * 100, new ActionListener() {
			int remainingTime = gameDuration;

			@Override
			public void actionPerformed(ActionEvent e) {
				remainingTime--;
				
				if (remainingTime >= 0) {
					// Update the timer label with the remaining time
					timerLabel.setText("Time Remaining: " + remainingTime + " seconds");
					repaint();
				} else {
					// Code to be executed when the game timer expires (ends the game)
					System.out.println("Game Over! Final Score: " + score);
					System.exit(0); // Exit the game
				}
			}
		});
		gameTimer.setRepeats(true); // Fire repeatedly to update the timer label

		// Start the game timer
		gameTimer.start();
	}


	public void paint(Graphics g) {
		super.paint(g);
		int largeur = getWidth();
		int hauteur = getHeight();

		int lignes = 10;
		int colonnes = 10;

		espacementLignes = hauteur/lignes;
		espacementColonnes = largeur/colonnes;

		for (int i=0; i<lignes; i++) {
			int y = i * espacementLignes;
			g.drawLine(0, y, largeur, y);
		}

		for (int i = 0; i<colonnes; i++) {
			int x = i * espacementColonnes;
			g.drawLine(x, 0, x, hauteur);
		}

		g.setColor(Color.YELLOW);
		g.fillRect(position.x * espacementColonnes, position.y * espacementLignes, espacementColonnes, espacementLignes);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Score: " + score, 20, 30);

	}





	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Tape Taupe");
		TapeTaupe canvas = new TapeTaupe();

		JPanel panel = new JPanel();
		panel.add(canvas.timerLabel); 

		frame.setLayout(new BorderLayout()); // Set the layout manager to BorderLayout
	    frame.add(canvas, BorderLayout.CENTER); // Add canvas to the center
	    frame.add(panel, BorderLayout.SOUTH); // Add panel with timerLabel to the south

	    frame.setSize(400, 400);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);


	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		int squareX = position.x * espacementColonnes;
		int squareY = position.y * espacementLignes;

		if (mouseX >= squareX && mouseX < squareX + espacementColonnes &&
				mouseY >= squareY && mouseY < squareY + espacementLignes) {
			// Perform your action here when the yellow square is clicked
			Random rand = new Random();
			position = new Point(rand.nextInt(10), rand.nextInt(10));
			System.out.println("Nice !");
			score++;
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub


	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}



}
