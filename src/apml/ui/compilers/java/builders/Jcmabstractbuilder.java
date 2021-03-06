package apml.ui.compilers.java.builders;

import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;
import apml.ui.compilers.java.Uiparameter;
import apml.xpath.helpers.Xpathquick;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Max Rupplin
 */
public class Jcmabstractbuilder
{
	protected final Integer hash = 0x00888FE8;

	//

	public ArrayList<JCodeModel> jcodemodels = new ArrayList<>();

	public Jcmabstractbuilder builder = this;

	public Document doc;

	public File apml;

	public NodeList nodes;

	public XPath xpath;

	public String tagname;

	public Class classname;

	public ArrayList<Uiparameter> storage = new ArrayList<Uiparameter>();

	/**
	 *
	 */
	public Jcmabstractbuilder()
	{
		//
	}

	/**
	 * @param apml
	 * @param tagname
	 * @param classname
	 */
	public Jcmabstractbuilder(File apml, String tagname, Class classname)
	{
		Bodi.setcontext("widgets");

		try
		{
			this.tagname = tagname;

			this.apml = apml;

			this.classname = classname;

			this.setxpath();

			this.setdocument();

			this.setnodes();
		}
		catch (Exception exception)
		{
			System.err.println(exception);
		}
	}

	/**
	 * @return
	 */
	public ArrayList<JCodeModel> build()
	{
		try
		{
			for (int index = 0; index < this.nodes.getLength(); index++) //ordering somewhat important
			{
				Uiparameter uip = new Uiparameter(new JCodeModel(), index);

				this.setjcmpackage(uip);

				this.setjcmclass(uip);

				this.setuipvalues(uip);

				this.setbodi(uip);

				this.setsuperclass(uip);

				this.setjcodemodel(uip);

				this.setconstructors(uip);

				this.setbackgroundimage(uip);

				this.storage.add(uip);
			}
		}
		catch (Exception exception)
		{
			//exception.printStackTrace();
		}

		return jcodemodels;
	}

	/**
	 * @param uip
	 */
	public void setconstructors(Uiparameter uip)
	{
		try
		{
			if (uip.jdc._extends().name().contains("JSplitPane"))
			{
				//

				uip.constructor1 = uip.jdc.constructor(JMod.PUBLIC);

				uip.constructor1.param(java.awt.Component.class, "parent");

				uip.constructor1.param(Integer.class, "orientation");

				uip.constructor1.param(java.awt.Component.class, "component_000");

				uip.constructor1.param(java.awt.Component.class, "component_001");

				uip.constructor1.body().directStatement("// super\n\t");

				uip.constructor1.body().directStatement("super(orientation, component_000, component_001);\n\t");

				//

				uip.constructor2 = uip.jdc.constructor(JMod.PUBLIC);

				uip.constructor2.param(java.awt.Component.class, "parent");

				uip.constructor2.param(Apmlbasesystem.class, "system");

				uip.constructor2.param(Integer.class, "orientation");

				uip.constructor2.param(java.awt.Component.class, "component_000");

				uip.constructor2.param(java.awt.Component.class, "component_001");

				uip.constructor2.body().directStatement("// super \n\t");

				uip.constructor2.body().directStatement("super(orientation, component_000, component_001);\n\t");

				//

				return;
			}

			else
			{
				//

				uip.constructor1 = uip.jdc.constructor(JMod.PUBLIC);

				uip.constructor1.param(java.awt.Component.class, "parent");

				//

				uip.constructor2 = uip.jdc.constructor(JMod.PUBLIC);

				uip.constructor2.param(java.awt.Component.class, "parent");

				uip.constructor2.param(Apmlbasesystem.class, "system");
			}
		}
		catch (Exception e)
		{
			//
		}
	}

	/**
	 *
	 */
	public void setxpath()
	{
		this.xpath = XPathFactory.newInstance().newXPath();
	}

	/**
	 * @param uip The housing parameter for APML element
	 */
	public void setsuperclass(Uiparameter uip)
	{
		uip.jdc._extends(this.classname);
	}

	/**
	 * @param uip The housing parameter for APML element
	 */
	public void setuipvalues(Uiparameter uip)
	{
		//

		try
		{
			uip.children = (NodeList) xpath.evaluate("./*", nodes.item(uip.index), XPathConstants.NODESET);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			uip.node = nodes.item(uip.index);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			uip.backgroundimagename = ((Element) uip.node).getAttribute("setBackgroundImage");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		try
		{
			uip.jdc = uip.jcm.packages().next().classes().next();
		}
		catch (Exception e)
		{
		}

		try
		{
			uip.jpackage = uip.jcm.rootPackage();
		}
		catch (Exception e)
		{
		}

		try
		{
			uip.xpath = xpath;
		}
		catch (Exception e)
		{
		}

		try
		{
			uip.parent_node = nodes.item(uip.index).getParentNode();
		}
		catch (Exception e)
		{
		}

		try
		{
			uip.classname = uip.jdc.name();
		}
		catch (Exception e)
		{
		}

		try
		{
			uip.instancename = uip.jdc.name().toLowerCase();
		}
		catch (Exception e)
		{
		}

		try
		{
			uip.doc = this.doc;
		}
		catch (Exception e)
		{
		}

		try
		{
			uip.element = (Element) uip.node;
		}
		catch (Exception e)
		{
		}
	}

	/**
	 * @param uip The housing parameter for APML element
	 */
	public void setbodi(Uiparameter uip)
	{
		//

		try
		{
			Bodi.context("widgets").put(uip.jcm, uip);
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}

		try
		{
			Bodi.context("widgets").put(uip.node, uip);
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}

		try
		{
			Bodi.context("widgets").put(uip.classname, uip);
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}

	/**
	 * @param uip The housing parameter for APML element
	 */
	public void setjcmclass(Uiparameter uip) throws Exception
	{
		JDefinedClass theclass;

		theclass = uip.jcm.packages().next()._class(this.classname.getSimpleName() + "_" + String.format("%1$03d", uip.index));

		//

		String bodiversion = (String) Bodi.context("//bodi/version").pull("version");

		//

		theclass.javadoc().add("Software programmatically produced by Bloq implementation version " + bodiversion + "\n\n");

		theclass.javadoc().add("@author\n");

		theclass.javadoc().add("@see\n");

		theclass.javadoc().add("@since\n");

		theclass.javadoc().add("@version");
	}

	/**
	 * @param uip The housing parameter for APML element
	 */
	public void setjcmpackage(Uiparameter uip) throws Exception
	{

		try
		{
			NodeList packagename;

			packagename = Xpathquick.evaluate(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.apml), xpath, "//package[@default]");

			if (packagename.getLength() == 1)
			{
				String pname = ((Element) packagename.item(0)).getAttribute("default").trim().toLowerCase();

				//

				//uip.jcm.packages().remove();

				uip.jcm._package(pname);
			}
		}
		catch (Exception e)
		{
			System.err.println("Error setting package in Uioutputmanager:setpackage");
		}
	}

	/**
	 * @throws Exception
	 */
	public void setdocument() throws Exception
	{
		this.doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(apml);
	}

	/**
	 * @throws Exception
	 */
	public void setnodes() throws Exception
	{
		try
		{
			this.nodes = (NodeList) xpath.evaluate(this.tagname, this.doc, XPathConstants.NODESET);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param uip The housing parameter for APML element
	 */
	protected void setjcodemodel(Uiparameter uip)
	{
		this.jcodemodels.add(uip.jcm);
	}

	/**
	 * @param uip The housing parameter for APML element
	 */
	private void setbackgroundimage(Uiparameter uip)
	{
		if (uip.backgroundimagename == null || uip.backgroundimagename.isEmpty())
			return;

		JDefinedClass jdefinedclass;

		jdefinedclass = uip.jdc;

		//

		jdefinedclass.field(JMod.PUBLIC, java.awt.Image.class, "backgroundimage");

		jdefinedclass.field(JMod.PUBLIC, java.lang.String.class, "backgroundimagename");

		//

		JMethod method;

		method = jdefinedclass.method(JMod.PUBLIC, uip.jcm.VOID, "paintComponent");

		method.param(java.awt.Graphics.class, "g");

		method.body().directStatement("super.paintComponent(g);\n");

		method.body().directStatement("try{this.backgroundimage = ImageIO.read(new File(backgroundimagename));}catch(Exception e){e.printStackTrace();}\n");

		method.body().directStatement("g.drawImage(backgroundimage, 0, 0, this);");
	}

	/**
	 * @param uip   The housing parameter for APML element
	 * @param child The child to the implied parent Node
	 * @param i     The child's index for naming purposes
	 */
	public void addListeners(Uiparameter uip, Node child, int i)
	{
		Element element = (Element) child;

		String[] reply = new ListenerComparator().getListenerClassnames(uip, child);

		String classname = reply[0];

		String classful_listener_type = element.getAttribute("class");

		//

		if (classful_listener_type == null || classful_listener_type.isEmpty() || classful_listener_type.toLowerCase().equalsIgnoreCase("inherited"))
		{
			uip.constructor1.body().directStatement("this.add" + classname + "(this);\n");

			uip.constructor2.body().directStatement("this.add" + classname + "(this);\n");
		}
		else if (classful_listener_type.toLowerCase().equalsIgnoreCase("local"))
		{
			uip.constructor1.body().directStatement("this.add" + classname + "(" + classname.toLowerCase() + ");\n");

			uip.constructor2.body().directStatement("this.add" + classname + "(" + classname.toLowerCase() + ");\n");
		}
		else
		{
			System.err.println("Unknown class type for UI listener element; hire another programmer!");
		}
	}

	/**
	 * @param uip   The housing parameter for APML element
	 * @param child The child to the implied parent Node
	 * @param i     The child's index for naming purposes
	 */
	public void addListenerMethods(Uiparameter uip, Node child, int i)
	{
		Element element = (Element) child;

		String[] reply = new ListenerComparator().getListenerClassnames(uip, child);

		String fullyqualifiedclassname = reply[1];

		//

		try
		{
			if (element.getAttribute("stubmethod") != null && element.getAttribute("stubmethod").toLowerCase().equals("true"))
			{
				Class classref = Class.forName(fullyqualifiedclassname);

				for (Method method : classref.getDeclaredMethods())
				{
					JMethod jmethod;

					jmethod = uip.jdc.method(JMod.PUBLIC, method.getReturnType(), method.getName());

					jmethod.param(method.getParameterTypes()[0], "event");

					jmethod.body().directStatement("//to be implemented");
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * @param uip   The housing parameter for APML element
	 * @param child The child to the implied parent Node
	 */
	public void addListenerClasses(Uiparameter uip, Node child)
	{
		Element element = (Element) child;

		String[] reply = new ListenerComparator().getListenerClassnames(uip, child);

		String classname = reply[0];

		String fullyqualifiedclassname = reply[1];

		//

		if (element.getAttribute("class") != null && element.getAttribute("class").toLowerCase().equalsIgnoreCase("inherited"))
		{
			try
			{
				uip.jdc._implements(Class.forName(fullyqualifiedclassname));
			}
			catch (Exception e)
			{
				System.err.println("Could not find " + fullyqualifiedclassname + " on the classpath. Ok.");
			}
		}

		//

		if (element.getAttribute("class") != null && element.getAttribute("class").toLowerCase().equalsIgnoreCase("local"))
		{
			try
			{
				Class theclass = Class.forName(fullyqualifiedclassname);

				String parameterclass = theclass.getDeclaredMethods()[0].getParameterTypes()[0].getSimpleName();

				uip.jdc.direct("\n");

				uip.jdc.direct("    class " + classname + "_000 implements " + classname);

				uip.jdc.direct("{\n");

				uip.jdc.direct("\tpublic " + theclass.getDeclaredMethods()[0].getReturnType() + " " + theclass.getDeclaredMethods()[0].getName() + "(" + parameterclass + " event)");

				uip.jdc.direct("{\n\n");

				uip.jdc.direct("\t}\n");

				uip.jdc.direct("\t}\n");

				uip.jdc.direct("\n");
			}
			catch (Exception e)
			{
				System.err.println("Could not find " + fullyqualifiedclassname + " on the classpath. Ok.");
			}
		}
	}

	/**
	 * @param uip   The housing parameter for APML element
	 * @param child The child to the implied parent Node
	 */
	public void addListenerFields(Uiparameter uip, Node child)
	{
		String[] reply = new ListenerComparator().getListenerClassnames(uip, child);

		if (reply == null)
			return; //presume we sent a non-listener through

		String classname = reply[0];

		String fullyqualifiedclassname = reply[1];

		//

		try
		{
			uip.jdc.field(JMod.PUBLIC, Class.forName(fullyqualifiedclassname), uip.classname.toLowerCase() + "_" + classname.toLowerCase());
		}
		catch (Exception e)
		{
			System.err.println("Could not find " + fullyqualifiedclassname + " on the classpath. Ok.");
		}
	}

	/**
	 * @param uip   The housing parameter for APML element
	 * @param child The child to the implied parent Node
	 */
	public void addListenerInstantiation(Uiparameter uip, Node child)
	{
		String[] reply = new ListenerComparator().getListenerClassnames(uip, child);

		if (reply == null || reply[0] == null)
		{
			return;
		}

		String classname = reply[0];

		Element element = (Element) child;

		String classful_listener_type = element.getAttribute("class");

		//

		if (classful_listener_type == null || classful_listener_type.toLowerCase().equalsIgnoreCase("inherited"))
		{
			//
		}
		else if (classful_listener_type.toLowerCase().equalsIgnoreCase("local"))
		{
			uip.constructor1.body().directStatement("this." + classname.toLowerCase() + " = new " + classname + "();\n");

			uip.constructor2.body().directStatement("this." + classname.toLowerCase() + " = new " + classname + "();\n");
		}
		else
		{
			System.err.println("Jcmabstractbuilder.addListenerInstantiation Unknown variable value for 'class' attribute in element type listener.");
		}
	}
}

class ListenerComparator
{
	public String[] getListenerClassnames(Uiparameter uip, Node child)
	{
		String fullyqualifiedclassname = null;

		String classname = null;

		Element element = (Element) child;

		//

		if (element.getTagName().toLowerCase().equals("listener"))
		{
			//

			if ("ActionListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "ActionListener";

				fullyqualifiedclassname = "java.awt.event.ActionListener";
			}

			if ("AdjustmentListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "AdjustmentListener";

				fullyqualifiedclassname = "java.awt.event.AdjustmentListener";
			}

			if ("AWTEventListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "AWTEventListener";

				fullyqualifiedclassname = "java.awt.event.AWTEventListener";
			}

			if ("ComponentListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "ComponentListener";

				fullyqualifiedclassname = "java.awt.event.ComponentListener";
			}

			if ("ContainerListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "ContainerListener";

				fullyqualifiedclassname = "java.awt.event.ContainerListener";
			}

			if ("FocusListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "FocusListener";

				fullyqualifiedclassname = "java.awt.event.FocusListener";
			}

			if ("HierarchyBoundsListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "HierarchyBoundsListener";

				fullyqualifiedclassname = "java.awt.event.HierarchyBoundsListener";
			}

			if ("HierarchyListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "HierarchyListener";

				fullyqualifiedclassname = "java.awt.event.HierarchyListener";
			}

			if ("InputMethodListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "InputMethodListener";

				fullyqualifiedclassname = "java.awt.event.InputMethodListener";
			}

			if ("ItemListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "ItemListener";

				fullyqualifiedclassname = "java.awt.event.ItemListener";
			}

			if ("KeyListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "KeyListener";

				fullyqualifiedclassname = "java.awt.event.KeyListener";
			}

			if ("MouseListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "MouseListener";

				fullyqualifiedclassname = "java.awt.event.MouseListener";
			}

			if ("MouseMotionListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "MouseMotionListener";

				fullyqualifiedclassname = "java.awt.event.MouseMotionListener";
			}

			if ("MouseWheelListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "MouseWheelListener";

				fullyqualifiedclassname = "java.awt.event.MouseWheelListener";
			}

			if ("TextListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "TextListener";

				fullyqualifiedclassname = "java.awt.event.TextListener";
			}

			if ("WindowFocusListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "WindowFocusListener";

				fullyqualifiedclassname = "java.awt.event.WindowFocusListener";
			}

			if ("WindowListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "WindowListener";

				fullyqualifiedclassname = "java.awt.event.WindowListener";
			}

			if ("WindowStateListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "WindowStateListener";

				fullyqualifiedclassname = "java.awt.event.WindowStateListener";
			}

            /* Standard Java Swing UI Items */

			if ("AncestorListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "AncestorListener";

				fullyqualifiedclassname = "javax.swing.event.AncestorListener";
			}

			if ("CaretListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "CaretListener";

				fullyqualifiedclassname = "javax.swing.event.CaretListener";
			}

			if ("CellEditorListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "CellEditorListener";

				fullyqualifiedclassname = "javax.swing.event.CellEditorListener";
			}

			if ("ChangeListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "ChangeListener";

				fullyqualifiedclassname = "javax.swing.event.ChangeListener";
			}

			if ("DocumentEvent".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "DocumentEvent";

				fullyqualifiedclassname = "javax.swing.event.DocumentEvent";
			}

			if ("DocumentEvent.ElementChange".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "DocumentEvent.ElementChange";

				fullyqualifiedclassname = "javax.swing.event.DocumentEvent.ElementChange";
			}

			if ("DocumentListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "DocumentListener";

				fullyqualifiedclassname = "javax.swing.event.DocumentListener";
			}

			if ("HyperlinkListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "HyperlinkListener";

				fullyqualifiedclassname = "javax.swing.event.HyperlinkListener";
			}

			if ("InternalFrameListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "InternalFrameListener";

				fullyqualifiedclassname = "javax.swing.event.InternalFrameListener";
			}

			if ("ListDataListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "ListDataListener";

				fullyqualifiedclassname = "javax.swing.event.ListDataListener";
			}

			if ("ListSelectionListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "ListSelectionListener";

				fullyqualifiedclassname = "javax.swing.event.ListSelectionListener";
			}

			if ("MenuDragMouseListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "MenuDragMouseListener";

				fullyqualifiedclassname = "javax.swing.event.MenuDragMouseListener";
			}

			if ("MenuKeyListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "MenuKeyListener";

				fullyqualifiedclassname = "javax.swing.event.MenuKeyListener";
			}

			if ("MenuListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "MenuListener";

				fullyqualifiedclassname = "javax.swing.event.MenuListener";
			}

			if ("MouseInputListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "MouseInputListener";

				fullyqualifiedclassname = "javax.swing.event.MouseInputListener";
			}

			if ("PopupMenuListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "PopupMenuListener";

				fullyqualifiedclassname = "javax.swing.event.PopupMenuListener";
			}

			if ("RowSorterListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "RowSorterListener";

				fullyqualifiedclassname = "javax.swing.event.RowSorterListener";
			}

			if ("TableColumnModelListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "TableColumnModelListener";

				fullyqualifiedclassname = "javax.swing.event.TableColumnModelListener";
			}

			if ("TableModelListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "TableModelListener";

				fullyqualifiedclassname = "javax.swing.event.TableModelListener";
			}

			if ("TreeExpansionListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "TreeExpansionListener";

				fullyqualifiedclassname = "javax.swing.event.TreeExpansionListener";
			}

			if ("TreeModelListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "TreeModelListener";

				fullyqualifiedclassname = "javax.swing.event.TreeModelListener";
			}

			if ("TreeSelectionListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "TreeSelectionListener";

				fullyqualifiedclassname = "javax.swing.event.TreeSelectionListener";
			}

			if ("TreeWillExpandListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "TreeWillExpandListener";

				fullyqualifiedclassname = "javax.swing.event.TreeWillExpandListener";
			}

			if ("UndoableEditListener".toLowerCase().equalsIgnoreCase(element.getAttribute("type")))
			{
				classname = "UndoableEditListener";

				fullyqualifiedclassname = "javax.swing.event.UndoableEditListener";
			}

			String[] reply = new String[2];

			reply[0] = classname;

			reply[1] = fullyqualifiedclassname;

			return reply;
		}

		return null;
	}
}

class AlphabeticalComparator implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		if (o1 == null || o2 == null)
			return -1;

		Method m1 = (Method) o1;

		Method m2 = (Method) o2;

		return m1.getName().compareToIgnoreCase(m2.getName());
	}
}