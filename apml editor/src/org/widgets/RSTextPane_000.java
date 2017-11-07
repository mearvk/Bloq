package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.TreeStructureUpdatedEvent;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.listeners.CustomKeyEventListener;
import org.listeners.LineCountDocumentListener;

import java.awt.*;

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
	public void processtreechange(TreeStructureUpdatedEvent event)
	{
		System.err.println("Possible errant call: "+this.getClass()+" processtreechange");
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.parent.getWidth(), this.parent.getHeight());
	}
}

