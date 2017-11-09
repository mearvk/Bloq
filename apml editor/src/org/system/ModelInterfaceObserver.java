package org.system;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathparameter;
import apml.xpath.helpers.Xpathquick;
import org.w3c.dom.Document;
import org.widgets.JTree_Apml_000;
import org.widgets.RSTextPane_Apml_000;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;


public class ModelInterfaceObserver
{
	public final String bodi = "//editor/ui/observers/model_interface_observer";
	public final String id = "processor";
	public final String tag = "object";
	public ModelInterfaceObserver observer = this;
	protected Apmlbasesystem monitor;

	//

	ApmlTextPaneObserver apmltextpaneobsever;

	ApmlJTreeObserver apmljtreeobsever;

	//

	public ModelInterfaceObserver(final Apmlbasesystem monitor)
	{
		this.monitor = monitor;
	}

	//

	public ModelInterfaceObserver()
	{
		// bodi

		Bodi.context("editor").put("//editor/ui/observers/model_interface_observer", this);

		//

		this.apmltextpaneobsever = new ApmlTextPaneObserver();

		this.apmljtreeobsever = new ApmlJTreeObserver();

		//

		this.apmltextpaneobsever.start();

		this.apmljtreeobsever.start();
	}

	//
	public void update(Event event, String target, String action)
	{

	}
}

class ApmlTextPaneObserver extends Thread implements DocumentListener
{
	public RSTextPane_Apml_000 textpane;

	public Document document;

	public XPath xpath;

	public Xpathquick xpathquick;

	public Map<String, Integer> counts = new HashMap<>();

	public ApmlTextPaneObserver()
	{
		this.textpane = (RSTextPane_Apml_000) Bodi.context("editor").pull("//editor/ui/rstextpane_apml_000");

		this.textpane.document.addDocumentListener(this);

		//

		this.counts.put("apml_tags", 0);

		this.counts.put("apml_attributes", 0);

		this.counts.put("apml_errors", 0);

		//

		this.xpath = XPathFactory.newInstance().newXPath();
	}

	@Override
	public void run()
	{

	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		//

		JOptionPane.showMessageDialog(null, "insert");
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		//

		JOptionPane.showMessageDialog(null, "removal");
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			this.requestElementCountVote();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private int requestElementCountVote()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			Xpathparameter xparam;

			xparam = new Xpathparameter();

			xparam.document = this.document;

			xparam.xpath = this.xpath;

			xparam.apmltag = "/*";

			//

			int _old = this.counts.get("tags");

			int _new = Xpathquick.count(xparam);

			//

			if(_old != _new)
			{
				ModelInterfaceSystem mis;

				mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

				mis.update(new ActionEvent(null,0,"apml_structure_updated_event"));
			}

			return _new;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return 0;
	}

	private void requestAttributeCountVote()
	{

	}

	private void requestErrorCountVote()
	{
		//xml rule base if possible
	}
}

class ApmlJTreeObserver extends Thread implements TreeSelectionListener
{
	public JTree_Apml_000 jtree;

	public ApmlJTreeObserver()
	{
		jtree = (JTree_Apml_000) Bodi.context("editor").pull("//editor/ui/jtree_apml_000");

		this.jtree.addTreeSelectionListener(this);
	}

	@Override
	public void run()
	{

	}

	@Override
	public void valueChanged(TreeSelectionEvent e)
	{

	}
}



