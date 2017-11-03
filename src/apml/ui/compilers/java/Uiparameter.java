/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.ui.compilers.java;

import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JPackage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import java.awt.*;

/**
 * The one utility parameter we use in system; all things come into a Uiparameter and are passed as method parameters or into Bodi
 *
 * @author Max Rupplin
 * @version Bloq 1.0
 * @since 04.30.2017
 */
public class Uiparameter
{
	protected final Integer hash = 0x888fe8;

	public Uiparameter uip;
	public Image backgroundimage;
	public String backgroundimagename;
	public JCodeModel jcm;
	public JMethod constructor1;
	public JMethod constructor2;
	public JDefinedClass jdc;
	public String classname;
	public String instancename;
	public String tagname;
	public Node parent_node;
	public Node node;
	public Node child_node;
	public NodeList children;
	public XPath xpath;
	public Document doc;
	public Element element;
	public Integer index;
	public JPackage jpackage;
	public Integer hashcode;

	public Uiparameter(Uiparameter uip)
	{
		this.uip = uip;
	}

	public Uiparameter(JCodeModel jcm)
	{
		this.jcm = jcm;
	}

	public Uiparameter(JCodeModel jcm, Integer index)
	{
		this.jcm = jcm;

		this.index = index;
	}
}
