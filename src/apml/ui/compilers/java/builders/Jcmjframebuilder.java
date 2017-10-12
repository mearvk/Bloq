package apml.ui.compilers.java.builders;

import apml.system.bodi.Bodi;
import apml.ui.compilers.java.Uiparameter;
import com.sun.codemodel.JCodeModel;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * @author Max Rupplin
 */
public class Jcmjframebuilder extends Jcmabstractbuilder
{
	protected final Integer hash = 0x00888FE8;

	public Jcmjframebuilder(File apml)
	{
		super(apml, "//jframe", JFrame.class);

		this.apml = apml;

		this.xpath = XPathFactory.newInstance().newXPath();
	}

	@Override
	public ArrayList<JCodeModel> build()
	{
		ArrayList<JCodeModel> retval = super.build();

		NodeList nodes;

		//

		for (JCodeModel model : retval)
		{
			Uiparameter uip = (Uiparameter) Bodi.context("widgets").pull(model);

			Element element = uip.element;

			if (element.getAttribute("setLayout") == null || element.getAttribute("setLayout").isEmpty())
			{
				element.setAttribute("setLayout", "new FlowLayout()");

				uip.element = element;
			}
		}

		return retval;
	}
}
