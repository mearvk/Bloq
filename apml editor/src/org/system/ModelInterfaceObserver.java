package org.system;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
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

	public ApmlTextPaneObserver apmltextpaneobserver;

	public ApmlJTreeObserver apmljtreeobserver;

	//

	public ModelInterfaceObserver(final Apmlbasesystem monitor)
	{
		this.monitor = monitor;
	}

	//

	public ModelInterfaceObserver()
	{
		//

		this.apmltextpaneobserver = new ApmlTextPaneObserver();

		this.apmljtreeobserver = new ApmlJTreeObserver();

		// bodi

		Bodi.context("editor").put(this.bodi, this);
	}
}

class ApmlTextPaneObserver extends Thread implements DocumentListener
{
	public RSTextPane_Apml_000 textpane;

	public Document document;

	public XPath xpath;

	public Map<String, Integer> counted_nodes = new HashMap<>();

	//

	public ApmlTextPaneObserver()
	{
		this.textpane = (RSTextPane_Apml_000) Bodi.context("editor").pull("//editor/ui/rstextpane_apml_000");

		this.textpane.getDocument().addDocumentListener(this);

		//

		this.counted_nodes.put("//apml/tags", 0);

		this.counted_nodes.put("//apml/attributes", 0);

		this.counted_nodes.put("//apml/errors", 0);
	}

	@Override
	public void run()
	{
		//
	}

	@Override
	public void insertUpdate(DocumentEvent e)
	{
		//
	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{
		//
	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{
		try
		{
			System.out.println("TOTAL ELEMENT COUNT: "+this.requestUpdateOnElementCountChange());

			//this.requestUpdateOnAttributeCountChange();
		}
		catch(Exception ex)
		{
			//
		}
	}

	private int requestUpdateOnAttributeCountChange()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			//

			ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

			//

			int _old_ = this.counted_nodes.get("//apml/attributes");

			int _new_ = walk_document_attribute_count(this.document, this.document.getDocumentElement(), null, 0);

			System.out.println("Total attributes found: "+_new_);

			//

			if(_old_ != _new_)
			{
				this.counted_nodes.put("//apml/attributes", _new_);

				JOptionPane.showMessageDialog(null, "Attributes: "+_new_);

				mis.update(new ActionEvent(this,0,"apml_structure_updated_event"));
			}

			return _new_;
		}
		catch(NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}

		return 0;
	}

	private int requestUpdateOnElementCountChange()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			//

			ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

			//

			int _old_ = this.counted_nodes.get("//apml/tags");

			int _new_ = walk_document_element_count(this.document, this.document.getDocumentElement(), null, 1);

			//System.out.println("Total elements found: "+_new_);

			//

			if(_old_ != _new_)
			{
				this.counted_nodes.put("//apml/tags", _new_);

				//JOptionPane.showMessageDialog(null, "Elements: "+_new_);

				mis.update(new ActionEvent(this,0,"apml_structure_updated_event"));
			}

			return _new_;
		}
		catch(NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch(Exception e)
		{
			System.err.println(e.getMessage());
		}

		return 0;
	}

	private void requestAttributeCountVote()
	{

	}

	private void requestErrorCountVote()
	{

	}

	protected Integer walk_document_element_count(Document document, Node current, Node next, Integer count)
	{
		for(int i=0; i<current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if( iterative_current.getNodeType()==Node.ELEMENT_NODE && iterative_current.getChildNodes().getLength()>0 )
			{
				count = this.walk_document_element_count(document, iterative_current, next, count+1);
			}

			else if(iterative_current.getNodeType()==Node.ELEMENT_NODE) count++;
		}

		return count;
	}

	protected Integer walk_document_attribute_count(Document document, Node current, Node next, Integer count)
	{
		int prior_count = count;

		int node_count = current.getChildNodes().getLength();

		//

		for(int i=0; i<current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if(iterative_current.getChildNodes().getLength()>0)
			{
				count += this.walk_document_element_count(document, iterative_current, next, node_count);
			}
		}

		//

		return count;
	}
}

class ApmlJTreeObserver extends Thread implements TreeSelectionListener
{
	public JTree_Apml_000 jtree;

	public ApmlJTreeObserver()
	{
		this.jtree = (JTree_Apml_000) Bodi.context("editor").pull("//editor/ui/jtree_apml_000");

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



