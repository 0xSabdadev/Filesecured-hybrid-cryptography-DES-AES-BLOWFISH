package hybrid.cryptography;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class BackgroundImageJFrame extends JFrame {
	public BackgroundImageJFrame()
	{
		setContentPane(new JLabel(new ImageIcon("C:\\Users\\Pranita Deshpande\\Documents\\Hybrid Cryptography[6211]\\Hybrid Cryptography\\src\\hybrid\\cryptography.jpg")));
	}
	private void setContentPane(JLabel jLabel) {
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[])
    {
    new BackgroundImageJFrame();
    }
}
