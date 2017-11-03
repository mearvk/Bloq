package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.SaveApmlDocumentEvent;
import org.events.TreeStructureUpdatedEvent;
import org.fife.ui.rsyntaxtextarea.*;
import org.listeners.CustomKeyEventListener;
import org.listeners.LineCountDocumentListener;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.text.Element;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class RSTextPane_000 extends RSyntaxTextArea
{
	public String bodi = "//editor/ui/rstextpane_000";

	public RTextScrollPane_000 scrollpane;

	public Component parent;

	public Apmlbasesystem monitor;

	public RSyntaxDocument document;

	//
	public RSTextPane_000(Component parent)
	{
		//setters

		this.setBackground(new Color(245, 245, 245));

		this.setCurrentLineHighlightColor(new Color(225, 225, 225));

		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);

		SyntaxScheme scheme = this.getSyntaxScheme();

		scheme.getStyle(Token.RESERVED_WORD).foreground = new Color(35, 35, 110);

		scheme.getStyle(Token.LITERAL_STRING_DOUBLE_QUOTE).foreground = new Color(35, 35, 110);

		//instantiation

		this.document = new RSyntaxDocument(SyntaxConstants.SYNTAX_STYLE_XML);

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

		//listeners

		this.document.addDocumentListener(new LineCountDocumentListener(this));

		this.addKeyListener(new CustomKeyEventListener(this));
	}

	//
	public RSTextPane_000(Component parent, Apmlbasesystem monitor)
	{
		//setters

		this.setBackground(new Color(245, 245, 245));

		this.setCurrentLineHighlightColor(new Color(225, 225, 225));

		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

		//instantiation

		this.document = new RSyntaxDocument(SYNTAX_STYLE_HTML);

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

		this.monitor = monitor;

		//listeners

		this.document.addDocumentListener(new LineCountDocumentListener(this));

		this.addKeyListener(new CustomKeyEventListener(this));
	}

	//
	public RSTextPane_000(Component parent, String bodi)
	{
		//setters

		this.setBackground(new Color(245, 245, 245));

		this.setCurrentLineHighlightColor(new Color(225, 225, 225));

		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

		//instantiation

		this.document = new RSyntaxDocument(SYNTAX_STYLE_HTML);

		//bodi

		Bodi.context("editor").put(bodi, this);

		//devolvement

		this.parent = parent;

		this.monitor = monitor;

		this.bodi = bodi;

		//listeners

		this.document.addDocumentListener(new LineCountDocumentListener(this));

		this.addKeyListener(new CustomKeyEventListener(this));
	}

	//
	public RSTextPane_000(Component parent, Apmlbasesystem monitor, String bodi)
	{
		//setters

		this.setBackground(new Color(245, 245, 245));

		this.setCurrentLineHighlightColor(new Color(225, 225, 225));

		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

		//instantiation

		this.document = new RSyntaxDocument(SYNTAX_STYLE_HTML);

		//bodi

		Bodi.context("editor").put(bodi, this);

		//devolvement

		this.parent = parent;

		this.monitor = monitor;

		this.bodi = bodi;

		//listeners

		this.document.addDocumentListener(new LineCountDocumentListener(this));

		this.addKeyListener(new CustomKeyEventListener(this));
	}

	//
	public void setscrollpane(RTextScrollPane_000 scrollpane)
	{
		this.scrollpane = scrollpane;
	}

	//
	public void savedocument(SaveApmlDocumentEvent event) throws Exception
	{
		try
		{
			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this);

			if (retval == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.getText(), 0, this.getText().length());

				writer.flush();

				writer.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	public void processtreechange(TreeStructureUpdatedEvent event)
	{
		TreeSelectionEvent treeevent;

		treeevent = (TreeSelectionEvent) event.getSource();

		System.out.println("JTree updated ");

		//finish

		/*

		1. find pertinent line number

		2. insert new line

		3. insert new node

		 */
	}

	@Override
	public Dimension getPreferredSize()
	{
		Element element;

		element = this.getDocument().getDefaultRootElement();

		//

		int lineheight = 15; //12 + 3

		int linecount = element.getElementCount();

		//

		Dimension calculated;

		calculated = new Dimension(this.parent.getWidth(), (linecount * lineheight));

		return calculated;
	}
}

