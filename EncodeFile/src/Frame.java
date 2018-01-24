import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Frame extends JFrame {

	private static final long serialVersionUID = -5151041547543472432L;

	private JPanel mainPanel = null;
	private JLabel iconLabel = null;

	private JButton buttonCryptFile = null;
	private JButton buttonDecryptFile = null;
	private FileCodecBase64 base64File = null;

	public Frame() {

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		ImageIcon imageIcon = new ImageIcon("file_icon64.png");
		iconLabel = new JLabel(imageIcon);

		buttonCryptFile = new JButton("Encrypt file");
		buttonDecryptFile = new JButton("Decrypt file");

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(iconLabel, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(buttonCryptFile, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(buttonDecryptFile, c);

		// mainPanel.add(buttonCryptFile);
		// mainPanel.add(buttonDecryptFile);

		buttonCryptFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String absolutePath = null;
				JFileChooser fileChooser = new JFileChooser();
				int accept = fileChooser.showOpenDialog(Frame.this);
				if (accept == JFileChooser.APPROVE_OPTION) {
					fileChooser.getSelectedFile().getName();
					absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
				}

				if (absolutePath != null) {
					if (base64File == null) {
						base64File = new FileCodecBase64();
					}
					try {
						base64File.encode(absolutePath, absolutePath);
						JOptionPane.showMessageDialog(Frame.this, "File encrypted", "File encrypted correctly",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		buttonDecryptFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String absolutePath = null;
				JFileChooser fileChooser = new JFileChooser();
				int accept = fileChooser.showOpenDialog(Frame.this);
				if (accept == JFileChooser.APPROVE_OPTION) {
					fileChooser.getSelectedFile().getName();
					absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
				}

				if (absolutePath != null) {
					if (base64File == null) {
						base64File = new FileCodecBase64();
					}
					try {
						base64File.decode(absolutePath, absolutePath);
						JOptionPane.showMessageDialog(Frame.this, "File decrypted", "File decrypted correctly",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		this.add(mainPanel);
		this.setLocationRelativeTo(null);
		this.setSize(300, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Crypte file");
	}
}
