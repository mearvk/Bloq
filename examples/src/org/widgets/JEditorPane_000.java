package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlDocumentEvent;
import xmleditorkit.XMLEditorKit;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;

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

		this.setBackground(new Color(227,227,127));

		this.setFont(new Font("Courier", Font.PLAIN, 12));

		this.getDocument().putProperty(PlainDocument.tabSizeAttribute, new Integer(4));

		this.setEditorKit(new XMLEditorKit());

		//devolvement

		this.parent = parent;

		//

		try
		{
			Bodi.context("editor").put(this.bodi, this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	public JEditorPane_000(Component parent, Apmlbasesystem system)
	{
		//setters

		this.setBackground(new Color(255,255,255));

		this.setFont(new Font("Courier", Font.PLAIN, 12));

		this.getDocument().putProperty(PlainDocument.tabSizeAttribute, new Integer(4));

		this.setEditorKit(new XMLEditorKit());

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
			e.printStackTrace();
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
		try
		{
			System.out.println("Name: "+event.file.getName());

			System.out.println("Path: "+event.file.getPath());

			System.out.println("Exists: "+event.file.exists());

			System.out.println("URL: "+event.file.toURI().toURL());

			//

			this.setPage(event.file.toURI().toURL());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{

		}
	}
}
