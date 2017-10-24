package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.LoadApmlDocumentEvent;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class RSTextPane_000 extends RSyntaxTextArea
{
	public String bodi = "//ui/editor/rstextpane_000";

	public RTextScrollPane_000 scrollpane;

	public Component parent;

	public RSyntaxDocument document;

	//
	public RSTextPane_000(Component parent)
	{
		this.parent = parent;

		try
		{
			Bodi.context("editor").put("//ui/editor/rstextpane_000", this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	public RSTextPane_000(Component parent, Apmlbasesystem monitor)
	{
		this.parent = parent;

		try
		{
			Bodi.context("editor").put("//ui/editor/rstextpane_000", this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	public void setscrollpane(RTextScrollPane_000 scrollpane)
	{
		this.scrollpane = scrollpane;
	}

	//
	public void loaddocument(LoadApmlDocumentEvent event)
	{
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(event.file));

			String line = null;

			StringBuffer text = new StringBuffer();

			int offset = 0;

			this.document = new RSyntaxDocument(SYNTAX_STYLE_XML);

			while((line=reader.readLine())!=null)
			{
				this.document.insertString(0, line+"\n", new SimpleAttributeSet());
			}

			//

			this.document.addDocumentListener(new LineCountDocumentListener(this));

			//

			this.setDocument(document);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public Dimension getPreferredSize()
	{
		Element element;

		element = this.getDocument().getDefaultRootElement();

		//

		int lineheight = 14;

		int linecount = element.getElementCount()+5;

		//



		//

		Dimension calculated = new Dimension(this.parent.getWidth(), (linecount*lineheight));

		//

		return calculated;
	}
}

class LineCountDocumentListener implements DocumentListener
{

	public RSTextPane_000 textarea;

	public LineCountDocumentListener(RSTextPane_000 textarea)
	{
		this.textarea = textarea;
	}

	@Override
	public void insertUpdate(DocumentEvent e) { }

	@Override
	public void removeUpdate(DocumentEvent e) { }

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		Element element;

		element = this.textarea.getDocument().getDefaultRootElement();

		//

		System.out.println(""+element.getElementCount()+" lines in document.");

		System.out.println(""+this.textarea.getPreferredSize()+" preferred size of document.");

		System.out.println(""+this.textarea.getSize()+" actual size of document.");

		System.out.println(""+this.textarea.scrollpane.getVisibleRect()+" visible rectangle.");

		//

		//

		//this.textarea.setColumns(40);
	}
}

