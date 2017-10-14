package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import animals.Animal;

/**
 * @author Dima Brusilovsky. ID 313026221, Campus Be'er Sheva
 * HW 4
 * DecoratorDialog Class
 */
public class DecorateDialog extends JDialog implements ActionListener,
		ItemListener {
	private static final long serialVersionUID = 1L;
	private ZooPanel panel;
	private JPanel mainPanel, selectPanel, colorPanel;
	private Animal an;
	private JComboBox<String> list;
	private ButtonGroup rbg;
	String col;
	private String[] colors = { "Red", "Blue" };
	private JRadioButton[] rb;
	private JButton ok;

	/**
	 * ctor
	 * @param pan zoo panel
	 */
	public DecorateDialog(ZooPanel pan) {
		super(new JFrame(), "Decorate an animal", true);
		panel = pan;
		an = null;
		col = null;

		setSize(750, 250);
		setResizable(true);

		setBackground(new Color(100, 230, 255));

		mainPanel = new JPanel();
		add(mainPanel);

		mainPanel.setLayout(new GridLayout(1, 2));

		selectPanel = new JPanel();
		selectPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Select Animal to decorate"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		selectPanel.setLayout(new BorderLayout());

		list = new JComboBox<String>();
		list.addItem("No animal");
		panel.fillComboBox(list,"Natural");
		list.addActionListener(this);
		selectPanel.add("North", list);

		ok = new JButton("OK");
		ok.addActionListener(this);
		selectPanel.add("South", ok);
		mainPanel.add(selectPanel);

		colorPanel = new JPanel();
		colorPanel.setLayout(new GridLayout(1, 2));
		colorPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Choose decoration color"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)));

		rbg = new ButtonGroup();
		rb = new JRadioButton[colors.length];
		for (int i = 0; i < colors.length; i++) {
			rb[i] = new JRadioButton(colors[i], false);
			rb[i].addItemListener(this);
			rbg.add(rb[i]);
			colorPanel.add(rb[i]);
		}

		mainPanel.add(colorPanel);

	}

	/**
	 * check if state is changed
	 */
	public void itemStateChanged(ItemEvent e) {
		for (int i = 0; i < rb.length; i++)
			if (rb[i].isSelected()) {
				col = colors[i];
				break;
			}
	}

	/**
	 * perform action upon approval
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			if ((an != null) && (col != null)) {
				ColoredAnimalDecorator dec = new ColoredAnimalDecorator(an);
				dec.paintAnimal(col);
			}
			setVisible(false);
		} else {
			int index;
			if (list.getSelectedIndex() != 0) {
				index = Integer.parseInt(((String) (list.getSelectedItem()))
						.substring(0, 1));
				try {
					an = panel.getAnimal(index - 1);
				} catch (Exception e1) {
					System.out.println("Decorate error! ");
					an = null;
				}
			}
		}
	}

}
