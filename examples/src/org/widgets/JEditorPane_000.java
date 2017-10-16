package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlDocumentEvent;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.Scanner;

public class JEditorPane_000 extends JEditorPane
{
	public String bodi = "//ui/editor/jeditorpane_000";

	public Integer marginleft = 10;

	public Integer margintop = 10;

	public Integer marginright = 10;

	public Integer marginbottom = 10;

	public Component parent;

	public Apmlbasesystem system;

	//
	public JEditorPane_000(Component parent)
	{
		//setters

		this.setBackground(new Color(255,255,255));

		//devolvement

		this.parent = parent;

		//

		try
		{
			Bodi.context("editor").put(this.bodi, this);
		}
		catch(Exception e)
		{

		}
	}

	//
	public JEditorPane_000(Component parent, Apmlbasesystem system)
	{
		//setters

		this.setBackground(new Color(255,255,255));

		//devolvement

		this.parent = parent;

		this.system = system;

		//

		try
		{
			Bodi.context("editor").put(this.bodi, this);
		}
		catch(Exception e)
		{
			//
		}
	}

	//
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(((int) (parent.getWidth() * 1.0) - this.marginleft), ((int) (parent.getHeight() * 1.0) - this.margintop));
	}

	//
	@Override
	public Dimension getMinimumSize()
	{
		return new Dimension(210, ((int) (parent.getHeight() * 1.0) - this.margintop));
	}

	//
	public void closedocument(CloseApmlDocumentEvent event)
	{
		this.setText("");
	}

	//
	public void loaddocument(LoadApmlDocumentEvent event)
	{
		SimpleAttributeSet attributeSet = new SimpleAttributeSet();

		StyleConstants.setBackground(attributeSet, Color.WHITE);

		StyleConstants.setForeground(attributeSet, Color.BLACK);

		StyleConstants.setBold(attributeSet, false);

		try
		{

			Scanner scanner = new Scanner(event.file);

			String line;

			while( (line=scanner.nextLine())!=null )
			{
				this.getDocument().insertString(getDocument().getLength(), line, attributeSet);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
