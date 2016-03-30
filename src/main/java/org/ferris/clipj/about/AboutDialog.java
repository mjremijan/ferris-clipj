package org.ferris.clipj.about;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;

	/**
	 * @param owner
	 */
	public AboutDialog(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 *
	 * @return void
	 */
	private void initialize() {
		this.setSize(385, 325);
		this.setContentPane(getJContentPane());
		this.setTitle("About");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	/**
	 * This method initializes jContentPane
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getContentPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}



	private JPanel contentPanel = null;

	private JLabel imageLabel = null;


	/**
	 * This method initializes contentPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getContentPanel() {
		if (contentPanel == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridwidth = 2;
			gridBagConstraints4.gridy = 1;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints3.gridheight = 1;
			gridBagConstraints3.weighty = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.CENTER;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(5, 5, 0, 0);
			gridBagConstraints3.gridy = 0;
			aboutLabel = new JLabel();
			aboutLabel.setText(getAboutText());
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.NORTH;
			gridBagConstraints.insets = new Insets(5, 0, 0, 0);
			gridBagConstraints.gridheight = 2;
			gridBagConstraints.gridy = 0;
            try {
			imageLabel = new JLabel(getIcon());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
			contentPanel = new JPanel();
			contentPanel.setLayout(new GridBagLayout());
			contentPanel.add(imageLabel, gridBagConstraints);
			contentPanel.add(aboutLabel, gridBagConstraints3);
			contentPanel.add(getButtonPanel(), gridBagConstraints4);
		}
		return contentPanel;
	}

	private String getAboutText()
	{
		About about
            = new About();

        StringBuilder sp
            = new StringBuilder();

		sp.append("<html>");
			sp.append("<body>");
                sp.append("<table>");
                {
                    sp.append("<tr>");
                    {
                        sp.append(String.format("<td>%s</td>", about.getVendor()));
                    }
                    sp.append("</tr>");
                    sp.append("<tr>");
                    {
                        sp.append(String.format("<td>%s</td>", about.getTitle()));
                    }
                    sp.append("</tr>");
                    sp.append("<tr>");
                    {
                        sp.append(String.format("<td>%s</td>", about.getVersion()));
                    }
                    sp.append("</tr>");
                    sp.append("<tr>");
                    {
                        sp.append(String.format("<td><a href=\"%1$s\">%1$s</a></td>", about.getUrl()));
                    }
                }
                sp.append("</table>");
                sp.append("<br />");
                sp.append("<table>");
                {
                    sp.append("<tr>");
                    {
                        sp.append(String.format("<td>Created by:</td><td>%s</td>", about.getVendorId()));
                    }
                    sp.append("</tr>");
                    sp.append("<tr>");
                    {
                        sp.append(String.format("<td>Created on:</td><td>%s</td>", about.getCreatedOn()));
                    }
                    sp.append("</tr>");
                    sp.append("<tr>");
                    {
                        sp.append(String.format("<td>Build agent:</td><td>%s</td>", about.getCreatedBy()));
                    }
                    sp.append("</tr>");
                    sp.append("<tr>");
                    {
                        sp.append(String.format("<td>Build JDK:</td><td>%s</td>", about.getBuildJdk()));
                    }
                    sp.append("</tr>");
                }
                sp.append("</table>");
                sp.append("<br /><br />");
			sp.append("</body>");
		sp.append("</html>");

		return sp.toString();
	}

	/**
	 * This method initializes buttonPanel
	 *
	 * @return javax.swing.JPanel
	 */
	private JPanel getButtonPanel() {
		if (buttonPanel == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.insets = new Insets(5, 0, 5, 0);
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.add(getCloseButton(), gridBagConstraints1);
		}
		return buttonPanel;
	}

	/**
	 * This method initializes closeButton
	 *
	 * @return javax.swing.JButton
	 */
	private JButton getCloseButton() {
		if (closeButton == null) {
			closeButton = new JButton();
			closeButton.setText("Close");
			closeButton.addActionListener(
				new ActionListener()
				{

					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}

				}
			);
		}
		return closeButton;
	}

	public static void main(String[] args) {
		new AboutDialog(null).setVisible(true);
	}

	private Icon icon;

	private JLabel aboutLabel = null;

	private JPanel buttonPanel = null;

	private JButton closeButton = null;

	private Icon getIcon() {
        if (icon == null) {
            try {
                URL url
                    = getClass().getResource("/about.png");
                if (url == null) {
                    throw new Exception("Image resource does not exist.");
                }
                icon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(url));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return icon;
	}
}
