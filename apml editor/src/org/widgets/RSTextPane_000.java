package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.CloseApmlDocumentEvent;
import org.events.LoadApmlDocumentEvent;
import org.events.SaveApmlDocumentEvent;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class RSTextPane_000 extends RSyntaxTextArea
{
	public String bodi = "//ui/editor/rstextpane_000";

	public RTextScrollPane_000 scrollpane;

	public Component parent;

	public Apmlbasesystem monitor;

	public RSyntaxDocument document;

	//
	public RSTextPane_000(Component parent)
	{
		//setters

		this.setBackground(new Color(245,245,245));

		this.setCurrentLineHighlightColor(new Color(225,225,225));

		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

		//instantiation

		this.document = new RSyntaxDocument(SyntaxConstants.SYNTAX_STYLE_MXML);

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

		//listeners

		this.document.addDocumentListener(new LineCountDocumentListener(this));

		this.addKeyListener(new CustomHomeButtonActionDocumentListener(this));
	}

	//
	public RSTextPane_000(Component parent, Apmlbasesystem monitor)
	{
		//setters

		this.setBackground(new Color(245,245,245));

		this.setCurrentLineHighlightColor(new Color(225,225,225));

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

		this.addKeyListener(new CustomHomeButtonActionDocumentListener(this));
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

			String buffer = new String();

			//

			while((line=reader.readLine())!=null)
			{
				buffer = buffer + line + "\n";
			}

			//this.document.insertString(0, buffer+"\n", new SimpleAttributeSet());

			this.setText(buffer+"\n");

			//

			//this.setDocument(document);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	public void closedocument(CloseApmlDocumentEvent event)
	{
		try
		{
			this.setText("");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	public void savedocument(SaveApmlDocumentEvent event)
	{
		try
		{
			JFileChooser chooser = new JFileChooser();

			int retval = chooser.showSaveDialog(this);

			if(retval==JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();

				BufferedWriter writer;

				writer = new BufferedWriter(new FileWriter(file));

				writer.write(this.getText(),0,this.getText().length());

				writer.flush();

				writer.close();
			}
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

		int lineheight = 15; //12 + 3

		int linecount = element.getElementCount();

		//

		Dimension calculated;

		calculated = new Dimension(this.parent.getWidth(), (linecount*lineheight));

		return calculated;
	}
}

class CustomHomeButtonActionDocumentListener implements KeyListener
{
	public RSTextPane_000 textarea;

	public CustomHomeButtonActionDocumentListener (RSTextPane_000 textarea)
	{
		this.textarea = textarea;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		//
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int MODS = e.getModifiers();

		//

		if( (e.getKeyCode()==KeyEvent.VK_HOME && MODS == KeyEvent.SHIFT_MASK) || (e.getKeyCode()==KeyEvent.VK_END && MODS == KeyEvent.SHIFT_MASK) )
		{
			e.consume();

			//

			int start = 0;

			int end = 0;

			//

			if(e.getKeyCode()==KeyEvent.VK_HOME)
			{
				start = this.textarea.getLineStartOffsetOfCurrentLine()+0;

				end = this.textarea.getCaretPosition();

				this.textarea.setCaretPosition(start);
			}

			if(e.getKeyCode()==KeyEvent.VK_END)
			{
				start = this.textarea.getCaretPosition();

				end = this.textarea.getLineEndOffsetOfCurrentLine()-1;

				this.textarea.setCaretPosition(end);
			}

			//

			this.textarea.select(start, end);

			//System.out.println("Set caret at character position ["+(position-delta)+"]");

			return;
		}

		//

		if(e.getKeyCode()==KeyEvent.VK_HOME)
		{
			e.consume();

			//

			int caretposition = this.textarea.getLineStartOffsetOfCurrentLine()+0;

			//

			this.textarea.setCaretPosition(caretposition);

			//System.out.println("Set caret at character position ["+(position-delta)+"]");

			return;
		}

		//

		if(e.getKeyCode()==KeyEvent.VK_END)
		{
			e.consume();

			//

			int caretposition = this.textarea.getLineEndOffsetOfCurrentLine()-1;

			//

			this.textarea.setCaretPosition(caretposition);

			//System.out.println("Set caret at character position ["+(caretposition)+"]")

			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		//
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
	public void insertUpdate(DocumentEvent e)
	{
		Element element;

		element = this.textarea.getDocument().getDefaultRootElement();

		//
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		Element element;

		element = this.textarea.getDocument().getDefaultRootElement();

		//
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		Element element;

		element = this.textarea.getDocument().getDefaultRootElement();

		//
	}
}

