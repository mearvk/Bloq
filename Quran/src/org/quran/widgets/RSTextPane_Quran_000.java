package org.quran.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.fife.ui.rsyntaxtextarea.*;
import org.listeners.CustomKeyEventListener;
import org.listeners.LineCountDocumentListener;
import org.widgets.RTextScrollPane_000;

import java.awt.*;

public class RSTextPane_Quran_000 extends RSyntaxTextArea
{
	public String bodi = "//editor/ui/rstextpane_quran_000";

	public RTextScrollPane_000 scrollpane;

	public Component parent;

	public Apmlbasesystem monitor;

	public RSyntaxDocument document;

	//
	public RSTextPane_Quran_000(Component parent)
	{
		//setters

		this.setBackground(new Color(245, 245, 245));

		this.setCurrentLineHighlightColor(new Color(225, 225, 225));

		//instantiation

		this.document = new RSyntaxDocument(SyntaxConstants.SYNTAX_STYLE_XML);

		this.setCodeFoldingEnabled(true);

		//bodi

		Bodi.context("editor").put(this.bodi, this);

		//devolvement

		this.parent = parent;

		this.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);

		SyntaxScheme scheme = this.getSyntaxScheme();

		//

		scheme.getStyle(Token.MARKUP_TAG_NAME).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE).foreground = Color.DARK_GRAY.brighter();

		scheme.getStyle(Token.MARKUP_TAG_ATTRIBUTE_VALUE).foreground = Color.DARK_GRAY.darker().darker();

		scheme.getStyle(Token.MARKUP_TAG_DELIMITER).foreground = Color.DARK_GRAY.darker();

		//

		this.revalidate();

		//listeners

		this.document.addDocumentListener(new LineCountDocumentListener(this));

		this.addKeyListener(new CustomKeyEventListener(this));
	}

	//
	public RSTextPane_Quran_000(Component parent, Apmlbasesystem monitor)
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
	public void setscrollpane(RTextScrollPane_000 scrollpane)
	{
		this.scrollpane = scrollpane;
	}
}

