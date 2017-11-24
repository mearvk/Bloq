package org.system;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.events.ReloadApmlTreeEvent;
import org.w3c.dom.*;
import org.widgets.*;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
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

	public BloqTextPaneObserver bloqtextpaneobserver;

	public BloqJTreeObserver bloqjtreeobserver;

	public BodiTextPaneObserver boditextpaneobserver;

	public BodiJTreeObserver bodijtreeobserver;

	public MunctionTextPaneObserver munctiontextpaneobserver;

	public MunctionJTreeObserver munctionjtreeobserver;

	public RunynTextPaneObserver runyntextpaneobserver;

	public RunynJTreeObserver runynjtreeobserver;

	public SprungTextPaneObserver sprungtextpaneobserver;

	public SprungJTreeObserver sprungjtreeobserver;

	public FalthruuTextPaneObserver falthruutextpaneobserver;

	public FalthruuJTreeObserver falthruujtreeobserver;

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

		this.bloqtextpaneobserver = new BloqTextPaneObserver();

		this.bloqjtreeobserver = new BloqJTreeObserver();

		this.boditextpaneobserver = new BodiTextPaneObserver();

		this.bodijtreeobserver = new BodiJTreeObserver();

		this.munctiontextpaneobserver = new MunctionTextPaneObserver();

		this.munctionjtreeobserver = new MunctionJTreeObserver();

		this.runyntextpaneobserver = new RunynTextPaneObserver();

		this.runynjtreeobserver = new RunynJTreeObserver();

		this.sprungtextpaneobserver = new SprungTextPaneObserver();

		this.sprungjtreeobserver = new SprungJTreeObserver();

		this.falthruutextpaneobserver = new FalthruuTextPaneObserver();

		this.falthruujtreeobserver = new FalthruuJTreeObserver();

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

		//

		if (this.xpath == null)
			this.xpath = XPathFactory.newInstance().newXPath();
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
			this.requestUpdateOnAttributeValueChange();

			//this.requestUpdateOnElementCountChange();

			//this.requestUpdateOnAttributeCountChange();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private int requestUpdateOnAttributeValueChange()
	{
		NodeList new_nodes;

		NodeList old_nodes;

		Document old_document;

		Document new_document;

		ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

		try
		{
			new_document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			old_document = (Document) Bodi.context("editor").pull("//editor/mis/apmlchangelistener/docref");

			//

			if (old_document == null)
			{
				Bodi.context("editor").put("//editor/mis/apmlchangelistener/docref", new_document);

				//

				old_document = new_document;
			}

			//

			new_nodes = Xpathquick.evaluate(new_document, this.xpath, "/*");

			old_nodes = Xpathquick.evaluate(old_document, this.xpath, "/*");

			//

			int new_node_count = 0;

			int old_node_count = 0;

			//

			for (int i = 0; i < new_nodes.getLength(); i++)
			{
				Node node = new_nodes.item(i);

				if (node.getNodeType() == Node.TEXT_NODE)
				{
					//add future impl for carriage returns or actual text nodes
				}
				else
				{
					new_node_count++;
				}
			}

			for (int i = 0; i < old_nodes.getLength(); i++)
			{
				Node node = old_nodes.item(i);

				if (node.getNodeType() == Node.TEXT_NODE)
				{
					//add future impl for carriage returns or actual text nodes
				}
				else
				{
					old_node_count++;
				}
			}

			if (new_node_count != old_node_count)
			{
				//call reloadfromfile on tree

				System.err.println("Total node count has changed.");
			}

			//

			for (int i = 0; i < new_nodes.getLength(); i++)
			{
				Node new_node = new_nodes.item(i);

				Node old_node = old_nodes.item(i);

				if (new_node.getNodeType() == Node.TEXT_NODE)
				{
					continue;
				}

				if (!new_node.isEqualNode(old_node))
				{
					//call reloadfromfile on tree

					System.err.println("Some particular node has changed.");

					mis.update(new ActionEvent(this, 0, "apml_structure_updated_event"));
				}
			}

			//

			Bodi.context("editor").put("//editor/mis/apmlchangelistener/docref", new_document);
		}
		catch (org.xml.sax.SAXParseException spe)
		{
			System.err.println(spe.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return 0;
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

			//System.out.println("Total attributes found: "+_new_);

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

			//

			if(_old_ != _new_)
			{
				this.counted_nodes.put("//apml/tags", _new_);

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
		for(int i=0; i<current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ATTRIBUTE_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_attribute_count(document, iterative_current, next, count + 1);
			}
		}

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

class BloqTextPaneObserver extends Thread implements DocumentListener
{
	public RSTextPane_Bloq_000 textpane;

	public Document document;

	public XPath xpath;

	public Map<String, Integer> counted_nodes = new HashMap<>();

	//

	public BloqTextPaneObserver()
	{
		this.textpane = (RSTextPane_Bloq_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bloq_000");

		this.textpane.getDocument().addDocumentListener(this);

		//

		this.counted_nodes.put("//bloq/tags", 0);

		this.counted_nodes.put("//bloq/attributes", 0);

		this.counted_nodes.put("//bloq/errors", 0);
	}

	//

	@Override
	public void insertUpdate(DocumentEvent e)
	{

	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{

	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{

	}

	private int requestUpdateOnAttributeCountChange()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			//

			ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

			//

			int _old_ = this.counted_nodes.get("//bloq/attributes");

			int _new_ = walk_document_attribute_count(this.document, this.document.getDocumentElement(), null, 0);

			System.out.println("Total attributes found: " + _new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//apml/attributes", _new_);

				JOptionPane.showMessageDialog(null, "Attributes: " + _new_);

				mis.update(new ActionEvent(this, 0, "bloq_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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

			int _old_ = this.counted_nodes.get("//bloq/tags");

			int _new_ = walk_document_element_count(this.document, this.document.getDocumentElement(), null, 1);

			//System.out.println("Total elements found: "+_new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//apml/tags", _new_);

				//JOptionPane.showMessageDialog(null, "Elements: "+_new_);

				mis.update(new ActionEvent(this, 0, "bloq_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ELEMENT_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_element_count(document, iterative_current, next, count + 1);
			}

			else if (iterative_current.getNodeType() == Node.ELEMENT_NODE)
				count++;
		}

		return count;
	}

	protected Integer walk_document_attribute_count(Document document, Node current, Node next, Integer count)
	{
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ATTRIBUTE_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_attribute_count(document, iterative_current, next, count + 1);
			}
		}

		return count;
	}
}

class BloqJTreeObserver extends Thread implements TreeSelectionListener
{
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{

	}
}

class BodiTextPaneObserver extends Thread implements DocumentListener
{
	public RSTextPane_Bodi_000 textpane;

	public Document document;

	public XPath xpath;

	public Map<String, Integer> counted_nodes = new HashMap<>();

	//

	public BodiTextPaneObserver()
	{
		this.textpane = (RSTextPane_Bodi_000) Bodi.context("editor").pull("//editor/ui/rstextpane_bodi_000");

		this.textpane.getDocument().addDocumentListener(this);

		//

		this.counted_nodes.put("//bodi/tags", 0);

		this.counted_nodes.put("//bodi/attributes", 0);

		this.counted_nodes.put("//bodi/errors", 0);
	}

	//

	@Override
	public void insertUpdate(DocumentEvent e)
	{

	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{

	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{

	}

	private int requestUpdateOnAttributeCountChange()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			//

			ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

			//

			int _old_ = this.counted_nodes.get("//bodi/attributes");

			int _new_ = walk_document_attribute_count(this.document, this.document.getDocumentElement(), null, 0);

			System.out.println("Total attributes found: " + _new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//bodi/attributes", _new_);

				JOptionPane.showMessageDialog(null, "Attributes: " + _new_);

				mis.update(new ActionEvent(this, 0, "bodi_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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

			int _old_ = this.counted_nodes.get("//bodi/tags");

			int _new_ = walk_document_element_count(this.document, this.document.getDocumentElement(), null, 1);

			//System.out.println("Total elements found: "+_new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//bodi/tags", _new_);

				//JOptionPane.showMessageDialog(null, "Elements: "+_new_);

				mis.update(new ActionEvent(this, 0, "bodi_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ELEMENT_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_element_count(document, iterative_current, next, count + 1);
			}

			else if (iterative_current.getNodeType() == Node.ELEMENT_NODE)
				count++;
		}

		return count;
	}

	protected Integer walk_document_attribute_count(Document document, Node current, Node next, Integer count)
	{
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ATTRIBUTE_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_attribute_count(document, iterative_current, next, count + 1);
			}
		}

		return count;
	}
}

class BodiJTreeObserver extends Thread implements TreeSelectionListener
{
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{

	}
}

class MunctionTextPaneObserver extends Thread implements DocumentListener
{
	public RSTextPane_Munction_000 textpane;

	public Document document;

	public XPath xpath;

	public Map<String, Integer> counted_nodes = new HashMap<>();

	//

	public MunctionTextPaneObserver()
	{
		this.textpane = (RSTextPane_Munction_000) Bodi.context("editor").pull("//editor/ui/rstextpane_munction_000");

		this.textpane.getDocument().addDocumentListener(this);

		//

		this.counted_nodes.put("//munction/tags", 0);

		this.counted_nodes.put("//munction/attributes", 0);

		this.counted_nodes.put("//munction/errors", 0);
	}

	//

	@Override
	public void insertUpdate(DocumentEvent e)
	{

	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{

	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{

	}

	private int requestUpdateOnAttributeCountChange()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			//

			ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

			//

			int _old_ = this.counted_nodes.get("//munction/attributes");

			int _new_ = walk_document_attribute_count(this.document, this.document.getDocumentElement(), null, 0);

			System.out.println("Total attributes found: " + _new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//munction/attributes", _new_);

				JOptionPane.showMessageDialog(null, "Attributes: " + _new_);

				mis.update(new ActionEvent(this, 0, "munction_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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

			int _old_ = this.counted_nodes.get("//munction/tags");

			int _new_ = walk_document_element_count(this.document, this.document.getDocumentElement(), null, 1);

			//System.out.println("Total elements found: "+_new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//munction/tags", _new_);

				//JOptionPane.showMessageDialog(null, "Elements: "+_new_);

				mis.update(new ActionEvent(this, 0, "munction_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ELEMENT_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_element_count(document, iterative_current, next, count + 1);
			}

			else if (iterative_current.getNodeType() == Node.ELEMENT_NODE)
				count++;
		}

		return count;
	}

	protected Integer walk_document_attribute_count(Document document, Node current, Node next, Integer count)
	{
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ATTRIBUTE_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_attribute_count(document, iterative_current, next, count + 1);
			}
		}

		return count;
	}
}

class MunctionJTreeObserver extends Thread implements TreeSelectionListener
{
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{

	}
}

class RunynTextPaneObserver extends Thread implements DocumentListener
{
	public RSTextPane_Runyn_000 textpane;

	public Document document;

	public XPath xpath;

	public Map<String, Integer> counted_nodes = new HashMap<>();

	//

	public RunynTextPaneObserver()
	{
		this.textpane = (RSTextPane_Runyn_000) Bodi.context("editor").pull("//editor/ui/rstextpane_runyn_000");

		this.textpane.getDocument().addDocumentListener(this);

		//

		this.counted_nodes.put("//apml/tags", 0);

		this.counted_nodes.put("//apml/attributes", 0);

		this.counted_nodes.put("//apml/errors", 0);
	}

	//

	@Override
	public void insertUpdate(DocumentEvent e)
	{

	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{

	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{

	}

	private int requestUpdateOnAttributeCountChange()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			//

			ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

			//

			int _old_ = this.counted_nodes.get("//runyn/attributes");

			int _new_ = walk_document_attribute_count(this.document, this.document.getDocumentElement(), null, 0);

			System.out.println("Total attributes found: " + _new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//runyn/attributes", _new_);

				JOptionPane.showMessageDialog(null, "Attributes: " + _new_);

				mis.update(new ActionEvent(this, 0, "runyn_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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

			int _old_ = this.counted_nodes.get("//runyn/tags");

			int _new_ = walk_document_element_count(this.document, this.document.getDocumentElement(), null, 1);

			//System.out.println("Total elements found: "+_new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//runyn/tags", _new_);

				//JOptionPane.showMessageDialog(null, "Elements: "+_new_);

				mis.update(new ActionEvent(this, 0, "runyn_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ELEMENT_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_element_count(document, iterative_current, next, count + 1);
			}

			else if (iterative_current.getNodeType() == Node.ELEMENT_NODE)
				count++;
		}

		return count;
	}

	protected Integer walk_document_attribute_count(Document document, Node current, Node next, Integer count)
	{
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ATTRIBUTE_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_attribute_count(document, iterative_current, next, count + 1);
			}
		}

		return count;
	}
}

class RunynJTreeObserver extends Thread implements TreeSelectionListener
{
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{

	}
}

class SprungTextPaneObserver extends Thread implements DocumentListener
{
	public RSTextPane_Sprung_000 textpane;

	public Document document;

	public XPath xpath;

	public Map<String, Integer> counted_nodes = new HashMap<>();

	//

	public SprungTextPaneObserver()
	{
		this.textpane = (RSTextPane_Sprung_000) Bodi.context("editor").pull("//editor/ui/rstextpane_sprung_000");

		this.textpane.getDocument().addDocumentListener(this);

		//

		this.counted_nodes.put("//sprung/tags", 0);

		this.counted_nodes.put("//sprung/attributes", 0);

		this.counted_nodes.put("//sprung/errors", 0);
	}

	//

	@Override
	public void insertUpdate(DocumentEvent e)
	{

	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{

	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{

	}

	private int requestUpdateOnAttributeCountChange()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			//

			ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

			//

			int _old_ = this.counted_nodes.get("//sprung/attributes");

			int _new_ = walk_document_attribute_count(this.document, this.document.getDocumentElement(), null, 0);

			System.out.println("Total attributes found: " + _new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//sprung/attributes", _new_);

				JOptionPane.showMessageDialog(null, "Attributes: " + _new_);

				mis.update(new ActionEvent(this, 0, "sprung_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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

			int _old_ = this.counted_nodes.get("//sprung/tags");

			int _new_ = walk_document_element_count(this.document, this.document.getDocumentElement(), null, 1);

			//System.out.println("Total elements found: "+_new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//sprung/tags", _new_);

				//JOptionPane.showMessageDialog(null, "Elements: "+_new_);

				mis.update(new ActionEvent(this, 0, "sprung_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ELEMENT_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_element_count(document, iterative_current, next, count + 1);
			}

			else if (iterative_current.getNodeType() == Node.ELEMENT_NODE)
				count++;
		}

		return count;
	}

	protected Integer walk_document_attribute_count(Document document, Node current, Node next, Integer count)
	{
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ATTRIBUTE_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_attribute_count(document, iterative_current, next, count + 1);
			}
		}

		return count;
	}
}

class SprungJTreeObserver extends Thread implements TreeSelectionListener
{
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{

	}
}

class FalthruuTextPaneObserver extends Thread implements DocumentListener
{
	public RSTextPane_Falthruu_000 textpane;

	public Document document;

	public XPath xpath;

	public Map<String, Integer> counted_nodes = new HashMap<>();

	//

	public FalthruuTextPaneObserver()
	{
		this.textpane = (RSTextPane_Falthruu_000) Bodi.context("editor").pull("//editor/ui/rstextpane_falthruu_000");

		this.textpane.getDocument().addDocumentListener(this);

		//

		this.counted_nodes.put("//falthruu/tags", 0);

		this.counted_nodes.put("//falthruu/attributes", 0);

		this.counted_nodes.put("//falthruu/errors", 0);
	}

	//

	@Override
	public void insertUpdate(DocumentEvent e)
	{

	}

	@Override
	public void removeUpdate(DocumentEvent e)
	{

	}

	@Override
	public void changedUpdate(DocumentEvent e)
	{

	}

	private int requestUpdateOnAttributeCountChange()
	{
		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new ByteArrayInputStream(this.textpane.getText().getBytes())));

			//

			ModelInterfaceSystem mis = (ModelInterfaceSystem) Bodi.context("editor").pull("//editor/ui/observers/model_interface_system");

			//

			int _old_ = this.counted_nodes.get("//falthruu/attributes");

			int _new_ = walk_document_attribute_count(this.document, this.document.getDocumentElement(), null, 0);

			System.out.println("Total attributes found: " + _new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//falthruu/attributes", _new_);

				JOptionPane.showMessageDialog(null, "Attributes: " + _new_);

				mis.update(new ActionEvent(this, 0, "falthruu_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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

			int _old_ = this.counted_nodes.get("//falthruu/tags");

			int _new_ = walk_document_element_count(this.document, this.document.getDocumentElement(), null, 1);

			//System.out.println("Total elements found: "+_new_);

			//

			if (_old_ != _new_)
			{
				this.counted_nodes.put("//falthruu/tags", _new_);

				//JOptionPane.showMessageDialog(null, "Elements: "+_new_);

				mis.update(new ActionEvent(this, 0, "falthruu_structure_updated_event"));
			}

			return _new_;
		}
		catch (NullPointerException npe)
		{
			npe.printStackTrace();
		}
		catch (Exception e)
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
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ELEMENT_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_element_count(document, iterative_current, next, count + 1);
			}

			else if (iterative_current.getNodeType() == Node.ELEMENT_NODE)
				count++;
		}

		return count;
	}

	protected Integer walk_document_attribute_count(Document document, Node current, Node next, Integer count)
	{
		for (int i = 0; i < current.getChildNodes().getLength(); i++)
		{
			Node iterative_current = current.getChildNodes().item(i);

			if (iterative_current.getNodeType() == Node.ATTRIBUTE_NODE && iterative_current.getChildNodes().getLength() > 0)
			{
				count = this.walk_document_attribute_count(document, iterative_current, next, count + 1);
			}
		}

		return count;
	}
}

class FalthruuJTreeObserver extends Thread implements TreeSelectionListener
{
	@Override
	public void valueChanged(TreeSelectionEvent e)
	{

	}
}

