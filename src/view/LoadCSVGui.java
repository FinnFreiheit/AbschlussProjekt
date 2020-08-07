package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import helper.GenerateCSV;
import model.Dax;

public class LoadCSVGui extends JPanel
{
	String message = "Klicken Sie eine, alle oder mehrere Aktien an, deren Informationen Sie laden möchten:";
	Map<String, String> dax;
	JCheckBox[] checkboxes;

	public LoadCSVGui()
	{
		super();
		this.dax = Dax.getDax();
		this.setLayout(new BorderLayout());
		this.add(createMessagePanel(), BorderLayout.NORTH);
		this.add(createListPanel(), BorderLayout.CENTER);
		this.add(createLoadPanel(), BorderLayout.SOUTH);
	}

	public JPanel createMessagePanel()
	{
		JPanel messagePanel = new JPanel();
		JLabel messageLabel = new JLabel(this.message);
		messagePanel.add(messageLabel);
		return messagePanel;
	}

	public JPanel createLoadPanel()
	{
		JPanel loadPanel = new JPanel();
		JButton loadButton = new JButton("Lade CSV");
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				for(int index=0; index<LoadCSVGui.this.checkboxes.length-1; index++)
				{
					if(LoadCSVGui.this.checkboxes[index].isSelected())
					{
						String aktienKuerzel = LoadCSVGui.this.checkboxes[index].getText();
						System.out.println(aktienKuerzel);
						GenerateCSV.loadCSV(aktienKuerzel);
					}
				}
				
			}
			
		});
		loadPanel.add(loadButton);
		return loadPanel;
	}

	private void initializeCheckboxArray()
	{
		// wir benoetigen genauso viele Checkboxen wie Eintraege im Dax
		// plus eine CheckBox fuer "ALLE"
		this.checkboxes = new JCheckBox[this.dax.size()+1];
		int index = 0;
		for(String key : this.dax.keySet())
		{
			this.checkboxes[index] = new JCheckBox(key);
			this.checkboxes[index].setSelected(true);
			this.checkboxes[index].addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e)
				{
					JCheckBox source = (JCheckBox)e.getSource(); 	
					if(!source.isSelected()) 
					{
						int lastCheckboxIndex = LoadCSVGui.this.checkboxes.length-1;
						LoadCSVGui.this.checkboxes[lastCheckboxIndex].setSelected(false);
					}
				}

			});
			index++;
		}

		int lastCheckboxIndex = this.checkboxes.length-1;
		this.checkboxes[lastCheckboxIndex] = new JCheckBox("ALLE");
		this.checkboxes[lastCheckboxIndex].setSelected(true);	
		this.checkboxes[lastCheckboxIndex].addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				JCheckBox source = (JCheckBox)e.getSource(); 	// kann nur die ALLE-Checkbox sein
				if(!source.isSelected())
				{
					// wenn ALLE ausgeschaltet wird und alle anderen sind selected, dann
					// werden alle anderen deselected
					// ansonsten bleiben alle anderen, wie sie sind
					boolean allSelected = true;
					for(int index=0; index<LoadCSVGui.this.checkboxes.length-1 && allSelected; index++)
					{
						allSelected = LoadCSVGui.this.checkboxes[index].isSelected();
					}
					if(allSelected)
					{
						for(int index=0; index<LoadCSVGui.this.checkboxes.length-1; index++)
						{
							LoadCSVGui.this.checkboxes[index].setSelected(false);
						}
					}
				}
				// wenn ALLE selected wird, dann werden auch alle anderen selected
				else for(int index=0; index<LoadCSVGui.this.checkboxes.length-1; index++)
				{
					LoadCSVGui.this.checkboxes[index].setSelected(true);
				}
			}

		});
	}

	public JPanel createListPanel()
	{
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new GridLayout(0,4));
		this.initializeCheckboxArray();

		for(int index=0; index<this.checkboxes.length-1; index++)
		{
			listPanel.add(this.checkboxes[index]);
		}

		// ein leeres Feld in der CheckBox-Liste
		JLabel empty = new JLabel();
		listPanel.add(empty);

		// ALLE
		int lastCheckboxIndex = this.checkboxes.length-1;
		listPanel.add(this.checkboxes[lastCheckboxIndex]);

		return listPanel;
	}
}
