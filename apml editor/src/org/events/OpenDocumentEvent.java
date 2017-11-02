package org.events;

import apml.system.bodi.Bodi;
import apml.xpath.helpers.Xpathquick;
import org.system.UserInterfaceProcessor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.widgets.APMLGui;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenDocumentEvent extends ActionEvent
{
	public File fileRef = null;

	public String fileType = "";

	public Document document;

	public XPath xpath;

	public NodeList nodes;

	//

	public OpenDocumentEvent(ActionEvent event, File fileRef)
	{
		super(event.getSource(), event.getID(), "open_document_event");

		this.fileRef = fileRef;

		//

		try
		{
			this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fileRef);

			this.xpath = XPathFactory.newInstance().newXPath();

			nodes = Xpathquick.evaluate(document, xpath,"//apml/project[@type]");

			//

			APMLGui apmlgui;

			apmlgui = (APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui");

			//

			if(nodes.getLength()==0)
				JOptionPane.showMessageDialog(apmlgui, "Unable to locate any document type; make sure you have a project type set ( //apml/project[@type] ).");

			if(nodes.getLength()>1)
				JOptionPane.showMessageDialog(apmlgui, "Unable to locate unambiguous document type; make sure you have a single project type set ( //apml/project[@type] ).");


			Element element;

			element = (Element)nodes.item(0);

			String type;

			type = element.getAttribute("type");

			this.fileType = type.trim().toLowerCase();

			switch(this.fileType)
			{
				case "apml": break;

				case "bloq": break;

				case "bodi": break;

				case "munction": break;

				case "runyn": break;

				case "sprung": break;

				case "falthruu": break;

				default:

					JOptionPane.showMessageDialog(apmlgui, "Unknown file type; no can help.");

					return;
			}

			JOptionPane.showMessageDialog(apmlgui, "Document type located as ["+this.fileType+"].");

			//

			UserInterfaceProcessor processor;

			processor = (UserInterfaceProcessor)Bodi.context("editor").pull("//editor/ui/uiprocessor_000");



			switch(this.fileType)
			{
				case "apml":

					processor.update(new LoadApmlDocumentEvent(this,this.fileRef));

					break;

				case "bloq":

					processor.update(new LoadBodiDocumentEvent(this,this.fileRef));

					break;

				case "bodi":

					processor.update(new LoadBodiDocumentEvent(this,this.fileRef));

					break;

				case "munction": break;

				case "runyn": break;

				case "sprung": break;

				case "falthruu": break;

				default: JOptionPane.showMessageDialog(apmlgui, "Unknown file type; no can help.");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//
	}

	public File getFileRef()
	{
		return this.fileRef;
	}
}
