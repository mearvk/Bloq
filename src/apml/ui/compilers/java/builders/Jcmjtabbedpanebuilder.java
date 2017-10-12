package apml.ui.compilers.java.builders;

import apml.ui.compilers.java.Uiparameter;
import com.sun.codemodel.JCodeModel;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Max Rupplin
 */
public class Jcmjtabbedpanebuilder extends Jcmabstractbuilder
{
	protected final Integer hash = 0x00888FE8;

	//

	public Jcmjtabbedpanebuilder(File apml)
	{
		super(apml, "//jtabbedpane", JTabbedPane.class);

		this.apml = apml;

		this.xpath = XPathFactory.newInstance().newXPath();
	}

	@Override
	public ArrayList<JCodeModel> build()
	{
		ArrayList<JCodeModel> retval = super.build();

		NodeList nodes;

		/*for (JCodeModel model : retval)
		{
			Uiparameter uip = (Uiparameter) Bodi.context("widgets").pull(model);

			//

			this.addTabs(uip);

			this.setSelected(uip);

			this.addDefaultListener(uip);

			try
			{
				Bodi.context("widgets").put(uip.node, uip);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}*/

		return jcodemodels;
	}

	/**
	 * @param uip
	 */
	private void setSelected(Uiparameter uip)
	{
		//may not need as of yet
	}

	/**
	 * @param uip
	 */
	private void addTabs(Uiparameter uip)
	{
		uip.constructor1.body().directStatement("// tab items");

		uip.constructor2.body().directStatement("// tab items");

		try
		{
			NodeList nodes = (NodeList) this.xpath.evaluate("./tab", uip.node, XPathConstants.NODESET);

			for (int index = 0; index < nodes.getLength(); index++)
			{
				Element element = (Element) nodes.item(index);

                /*----------------------- grab element attribs ---------------------*/

				String icon = (!element.getAttribute("setIcon").isEmpty()) ? element.getAttribute("setIcon") : "null";

				String tooltip = (!element.getAttribute("setTooltip").isEmpty()) ? element.getAttribute("setTooltip") : "null";

				String text = (!element.getAttribute("setText").isEmpty()) ? element.getAttribute("setText") : "null";

				String parent = (!element.getAttribute("setParent").isEmpty()) ? element.getAttribute("setParent") : "null";

                /*----------------------- add to constructors ---------------------*/

				uip.constructor1.body().directStatement("this.addTab(\"" + text + "\", " + icon + ", " + parent + ", \"" + tooltip + "\");\n");

				uip.constructor2.body().directStatement("this.addTab(\"" + text + "\", " + icon + ", " + parent + ", \"" + tooltip + "\");\n");
			}
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * @param uip
	 */
	public void addDefaultListener(Uiparameter uip)
	{
		NodeList children = uip.element.getChildNodes();

		for (int i = 0; i < children.getLength(); i++)
		{
			if (!(children.item(i) instanceof Element))
				continue;

			Element e = (Element) children.item(i);

			if (e.getTagName().toLowerCase().trim().equalsIgnoreCase("listener"))
			{
				if (!e.getAttribute("type").isEmpty())
				{
					if (e.getAttribute("type").trim().toLowerCase().equalsIgnoreCase("changelistener"))
					{
						return;
					}
				}
			}
		}

		//manually add this here; outputmanager is too late (fix me?)

		try
		{
			uip.jdc._implements(Class.forName("javax.swing.event.ChangeListener"));

			uip.jdc.direct("\n");

			uip.jdc.direct("    public void stateChanged(ChangeEvent ce) {\n");

			uip.jdc.direct("    \tSystem.out.println(\"Event Source: \"+ce.getSource());\n");

			uip.jdc.direct("    }\n");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		//for output manager we still must set an XML <listener> reference for the /listener fill in constructor (ok for now)

		Element e;

		e = uip.doc.createElement("listener");

		e.setAttribute("type", "changelistener");

		e.setAttribute("studmethod", "true");

		e.setAttribute("class", "inherited");

		uip.element.appendChild(e);
	}
}
