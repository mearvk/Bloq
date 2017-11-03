package org.widgets;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.events.LoadApmlDocumentEvent;

import javax.swing.*;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.PlainDocument;
import java.awt.*;

public class JEditorPane_000 extends JEditorPane
{
	public String bodi = "//ui/system/jeditorpane_000";

	public Integer marginleft = 10;

	public Integer margintop = 10;

	public Integer marginright = 10;

	public Integer marginbottom = 10;

	public Integer fontsize = 12;

	public Component parent;

	public Apmlbasesystem system;

	//
	public JEditorPane_000(Component parent)
	{
		//setters

		this.setBackground(new Color(120, 106, 123));

		this.setFont(new Font("Courier", Font.PLAIN, 12));

		this.getDocument().putProperty(PlainDocument.tabSizeAttribute, new Integer(4));

		this.setForeground(Color.WHITE);

		//highlighting

		this.setHighlighter(new DefaultHighlighter());

		//devolvement

		this.parent = parent;

		//

		try
		{
			Bodi.context("system").put(this.bodi, this);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	public JEditorPane_000(Component parent, Apmlbasesystem system)
	{
		//setters

		this.setBackground(new Color(120, 106, 123));

		this.setFont(new Font("Courier", Font.PLAIN, 12));

		this.getDocument().putProperty(PlainDocument.tabSizeAttribute, new Integer(4));

		this.setForeground(Color.WHITE);

		//highlighting

		this.setHighlighter(new DefaultHighlighter());

		//devolvement

		this.parent = parent;

		this.system = system;

		//

		try
		{
			Bodi.context("system").put(this.bodi, this);
		}
		catch (Exception e)
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
	public void loaddocument(LoadApmlDocumentEvent event)
	{
		try
		{
			this.setPage(event.getFileRef().toURI().toURL());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			//
		}
	}

	//
	public void highlightline(int startcharindex, int endcharindex)
	{
		Color highlightcolor = new Color(120, 106, 123);

		try
		{
			this.getHighlighter().addHighlight(startcharindex, endcharindex, new DefaultHighlighter.DefaultHighlightPainter(highlightcolor));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	//
	@Override
	public void paintComponents(Graphics g)
	{
		int width = this.getWidth();

		int height = this.getHeight();

		int lineheight = this.fontsize;

		int rectangles = height / lineheight;

		//

		for (int i = 0; i < rectangles; i++)
		{
			if (i % 2 == 0)
				g.setColor(new Color(240, 240, 240));

			if (i % 2 == 1)
				g.setColor(new Color(235, 235, 235));

			//

			g.fillRect(0, lineheight * i, width, lineheight);
		}

		super.paintComponents(g);
	}
}
