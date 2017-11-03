package apml.ui.compilers.java.builders;

import apml.system.bodi.Bodi;
import apml.system.bodi.Bodicontext;
import apml.ui.compilers.java.Uiparameter;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JMethod;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Max Rupplin
 */
public class Jcmjcomboboxbuilder extends Jcmabstractbuilder
{
	protected final Integer hash = 0x00888FE8;

	public Jcmjcomboboxbuilder(File apml)
	{
		super(apml, "//jcombobox", JComboBox.class);

		this.apml = apml;

		this.xpath = XPathFactory.newInstance().newXPath();
	}

	@Override
	public ArrayList<JCodeModel> build()
	{
		super.build();

		try
		{
			Bodicontext widgets = Bodi.context("widgets");

			for (int index = 0; index < nodes.getLength(); index++)
			{
				Uiparameter uip = (Uiparameter) widgets.pull(this.jcodemodels.get(index));

				this.additems(uip);
			}
		}
		catch (Exception exception)
		{
			System.err.println(exception);
		}

		return this.jcodemodels;
	}

	public void additems(Uiparameter uip)
	{
		try
		{
			uip.constructor1.body().directStatement("\n");

			uip.constructor2.body().directStatement("\n");

			uip.constructor1.body().directStatement("/*---------------------- combobox items ---------------------*/\n");

			uip.constructor2.body().directStatement("/*---------------------- combobox items ---------------------*/\n");

			NodeList nodes = (NodeList) xpath.evaluate("./item", this.nodes.item(uip.index), XPathConstants.NODESET);

			for (int i = 0; i < nodes.getLength(); i++)
			{
				Element element = (Element) nodes.item(i);

				if (element.getAttribute("name") == null)
					continue;

				Iterator iterator = uip.jdc.constructors();

				while (iterator.hasNext())
				{
					JMethod constructor;

					constructor = (JMethod) iterator.next();

					constructor.body().directStatement("this.addItem(\"" + element.getAttribute("name") + "\");\n");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
