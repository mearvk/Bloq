package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.TreeStructureUpdatedEvent;
import org.fife.ui.rsyntaxtextarea.*;
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

		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);

		//instantiation

		this.document = new RSyntaxDocument(SYNTAX_STYLE_HTML);

		//

		SyntaxScheme scheme = this.getSyntaxScheme();

		scheme.getStyle(Token.MARKUP_TAG_NAME).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE).foreground = Color.DARK_GRAY.brighter();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE_VALUE).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_DELIMITER).foreground = Color.DARK_GRAY.darker();

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

		//listeners
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

		//

		SyntaxScheme scheme = this.getSyntaxScheme();

		scheme.getStyle(Token.MARKUP_TAG_NAME).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE).foreground = Color.DARK_GRAY.brighter();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE_VALUE).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_DELIMITER).foreground = Color.DARK_GRAY.darker();

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

		this.monitor = monitor;

		//listeners
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

		//

		SyntaxScheme scheme = this.getSyntaxScheme();

		scheme.getStyle(Token.MARKUP_TAG_NAME).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE).foreground = Color.DARK_GRAY.brighter();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE_VALUE).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_DELIMITER).foreground = Color.DARK_GRAY.darker();

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

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

		//

		SyntaxScheme scheme = this.getSyntaxScheme();

		scheme.getStyle(Token.MARKUP_TAG_NAME).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE).foreground = Color.DARK_GRAY.brighter();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE_VALUE).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_DELIMITER).foreground = Color.DARK_GRAY.darker();

		//bodi

		Bodi.context("editor").put(this.bodi, this);

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

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.parent.getWidth(), this.parent.getHeight());
	}
}

