package apml.ui.compilers.java;

import apml.system.bodi.Bodi;
import apml.ui.compilers.java.builders.Jcmabstractbuilder;
import com.sun.codemodel.*;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPathConstants;
import java.util.ArrayList;


/**
 * Class for handling output and finalization of JCM files (Jcodemodel) for Bloq implementation version 1.0x
 * 
 * @author Max Rupplin
 * @since 04.30.2017
 * @version Bloq 1.0
 */
public class Uioutputmanager
{
    protected final Integer hash = 0x00888FE8;
    
    public Uicompiler compiler;
    
    public Uioutputmanager(Uicompiler compiler)
    {
        this.compiler = compiler;
    }    
    
    /**
     * Primary method presumed called by Localdriver and then Uicompiler
     * 
     * @param jcodemodels 
     */
    public void generatefiles(ArrayList<JCodeModel> jcodemodels)
    {
        try
        {                   
            for(JCodeModel jcodemodel : jcodemodels)
            {                  
                Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel); 

                this.setuisetters(uip);
                
                this.setfields(uip);
                
                this.setdevolvement(uip);  
                                
                this.setchildren(uip);
                
                this.setlisteners(uip);
                
                this.setconstructorcomments(uip);
                                
                uip.jcm.build(this.compiler.fileguardian.outputdir);
            }
        }
        catch(Exception e)
        {
            //e.printStackTrace();
        }    
    }
    
    private void setconstructorcomments(Uiparameter uip)
    {        
        uip.constructor1.javadoc().addParam("parent : The parent AWT object.");
        
        /*---------------------------------------------------------------------*/
        
        uip.constructor2.javadoc().addParam("system : The APML system object.");

        uip.constructor2.javadoc().addParam("parent : The parent AWT object.");
    }
    
    private void setlisteners(Uiparameter uip)
    {       
        try
        {
            NodeList children = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);             
            
            uip.constructor1.body().directStatement("/* ------------------  listeners  -------------------- */\n\t");
            
            uip.constructor2.body().directStatement("/* ------------------  listeners  -------------------- */\n\t");            
            
            
            /*------------------------ Listener Additions [handled by Jcmabstractbuilder now] --------------------------*/            
            
            for(int i=0; i<children.getLength(); i++)
            {
                //send only children that are listener elements - quick check - handle 'listeners' on far end
                if (children.item(i).getNodeName().equalsIgnoreCase("listener")) {
                    //now sending children that are only UI elements
                    new Jcmabstractbuilder().addListeners(uip, children.item(i), i);
                }
            }
            
            /*------------------------- Overridden Methods [handled by Jcmabstractbuilder now] --------------------------*/      
            
            for(int i=0; i<children.getLength(); i++)
            {
                //send only children that are UI elements - quick check - handle 'listeners' on far end
                if (children.item(i).getNodeName().equalsIgnoreCase("listener")) {
                    //now sending children that are only UI elements
                    new Jcmabstractbuilder().addListenerMethods(uip, children.item(i), i);
                }
            }

            /*------------------------- Listener Inner Class and/or Implementations [handled by Jcmabstractbuilder now] --------------------------*/

            for (int i = 0; i < children.getLength(); i++) {
                //send only children that are UI elements - quick check - handle 'listeners' on far end
                if (children.item(i).getNodeName().equalsIgnoreCase("listener")) {
                    //now sending children that are only UI elements
                    new Jcmabstractbuilder().addListenerClasses(uip, children.item(i));
                }
            }
        }
        catch(Exception exception)
        {
            
        }
    }
    
    private void setuisetters(Uiparameter uip)
    {
        uip.constructor1.body().directStatement("/* ------------------  setters  ---------------- */\n\t");

		uip.constructor2.body().directStatement("/* ------------------  setters  ---------------- */\n\t");

		//

		Bodi.setcontext("bodi://apml.org/uioutmanager/build/setters");

		//

        try
        {              
            NamedNodeMap attribs = uip.element.getAttributes();
            
            for(int i=0; i<attribs.getLength(); i++)
            {
                Node attribute = attribs.item(i);

                if (attribute.getNodeName().equalsIgnoreCase("setAccelerator"))
                {
                    String string = "this.setAccelerator(KeyStroke.getKeyStroke("+attribute.getNodeName()+"));";
                    
                    uip.constructor1.body().directStatement("this.setAccelerator(KeyStroke.getKeyStroke("+string+"));\n\t");

                    uip.constructor2.body().directStatement("this.setAccelerator(KeyStroke.getKeyStroke("+string+"));\n\t");
                    
                    continue;
                }

				if (attribute.getNodeName().equalsIgnoreCase("setBounds"))
				{
					String prestring;

                    prestring = attribute.getNodeValue().replace(":", ","); //move colons (:) to commas (,)

                    prestring = prestring.replace("px", ""); //move pixel (px) notation out completely

                    String string = "this.setBounds(" + prestring + ");\n\t";

                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);

                    continue;
                }

                if (attribute.getNodeName().equalsIgnoreCase("setIconAt"))
                {
                    String string = "this.setIconAt(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }

                if (attribute.getNodeName().equalsIgnoreCase("setIcon"))
                {
                    String string = "this.setIcon(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }

                if (attribute.getNodeName().equalsIgnoreCase("setLabel"))
                {
                    String string = "this.setLabel(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);
                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }

				if (attribute.getNodeName().equalsIgnoreCase("setMargin"))
				{
					//

					Bodi.context("bodi://apml.org/uioutmanager/build/setters").put("issetmargin", true);

					//

					JFieldVar field1 = uip.jdc.field(JMod.PUBLIC, Integer.class, "marginleft");

					JFieldVar field2 = uip.jdc.field(JMod.PUBLIC, Integer.class, "margintop");

					JFieldVar field3 = uip.jdc.field(JMod.PUBLIC, Integer.class, "marginright");

					JFieldVar field4 = uip.jdc.field(JMod.PUBLIC, Integer.class, "marginbottom");

					//

					String[] values = attribute.getNodeValue().split(":");

					//

					Integer left = Integer.parseInt(values[0]);

					Integer top = Integer.parseInt(values[1]);

					Integer right = Integer.parseInt(values[2]);

					Integer bottom = Integer.parseInt(values[3]);

					//

					field1.init(JExpr.lit(left));

					field2.init(JExpr.lit(top));

					field3.init(JExpr.lit(right));

					field4.init(JExpr.lit(bottom));

					//

					continue;
				}

                if (attribute.getNodeName().equalsIgnoreCase("setName"))
                {
                    String string = "this.setName(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }

                if (attribute.getNodeName().equalsIgnoreCase("setText"))
                {
                    String string = "this.setText(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }

                if (attribute.getNodeName().equalsIgnoreCase("setTitle"))
                {
                    String string = "this.setTitle(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }

                if (attribute.getNodeName().equalsIgnoreCase("setToolTipText"))
                {
                    String string = "this.setToolTipText(\""+attribute.getNodeValue()+"\");\n\t";
                    
                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }

                /**
                 * This will set size via getPreferredSize, not some other thing.
                 */
                if (attribute.getNodeName().equalsIgnoreCase("setSize"))
				{
					Boolean marginisset = (Boolean) Bodi.context("bodi://apml.org/uioutmanager/build/setters").pull("issetmargin");

					//

                    JMethod method;
                    
                    method = uip.jdc.method(JMod.PUBLIC, java.awt.Dimension.class, "getPreferredSize");

					//

                    String[] sizes = attribute.getNodeValue().trim().split(":");

					//

					if( (sizes[0]!=null && !sizes[0].isEmpty()) && (sizes[1]!=null && !sizes[1].isEmpty()) )
                    {                       
                        if(sizes[0].endsWith("%") && sizes[1].endsWith("%")) //uniform; both width and heigh are percentage based
                        {
                            try
                            {
                                Double width = Double.parseDouble(sizes[0].trim().replace("%", ""));

                                Double height = Double.parseDouble(sizes[1].trim().replace("%", ""));

								//

								if (marginisset == true)
									method.body().directStatement("return new Dimension( ((int)(parent.getWidth()*" + (width / 100d) + ")-this.marginleft), ((int)(parent.getHeight()*" + (height / 100d) + ")-this.margintop));");

								if (marginisset == false)
									method.body().directStatement("return new Dimension( ((int)(parent.getWidth()*" + (width / 100d) + ")), ((int)(parent.getHeight()*" + (height / 100d) + "));");
							}
                            catch(Exception e)
                            {
                                //
                            }
                            finally
                            {
                                continue;
                            }
                        }
                        
                        if(sizes[0].endsWith("%") && sizes[1].endsWith("px")) //mixed; width is percentage based and height is pixel based
                        {
                            try
                            {
                                Double width = Double.parseDouble(sizes[0].trim().replace("%", ""));
                                
                                Double height = Double.parseDouble(sizes[1].trim().replace("px", ""));

								//

								if (marginisset == true)
									method.body().directStatement("return new Dimension( ((int)(parent.getWidth()*" + (width / 100d) + ")-this.marginleft), ((int)(parent.getHeight()*" + (height / 100d) + ")-this.margintop));");

								if (marginisset == false)
									method.body().directStatement("return new Dimension( ((int)(parent.getWidth()*" + (width / 100d) + ")), ((int)(parent.getHeight()*" + (height / 100d) + "));");
							}
                            catch(Exception e)
                            {
                                //
                            }
                            finally
                            {
                                continue;
                            }
                        }  
                        
                        if(sizes[0].endsWith("px") && sizes[1].endsWith("%")) //mixed; width is pixel based and height is percentage based
                        {
                            try
                            {
                                Double width = Double.parseDouble(sizes[0].trim().replace("px", ""));
                                
                                Double height = Double.parseDouble(sizes[1].trim().replace("%", ""));

								//

								if (marginisset == true)
									method.body().directStatement("return new Dimension(" + width.intValue() + "-this.marginleft, ((int)(parent.getHeight()*" + (height / 100d) + ")-this.margintop));");

								if (marginisset == false)
									method.body().directStatement("return new Dimension(" + width.intValue() + ", ((int)(parent.getHeight()*" + (height / 100d) + "));");
							}
                            catch(Exception e)
                            {
                                //
                            }
                            finally
                            {
                                continue;
                            }
                        }

                        if(sizes[0].endsWith("px") && sizes[1].endsWith("px")) //uniform; fixed basis in pixels for both width and height
                        {
                            try
                            {
                                Double width = Double.parseDouble(sizes[0].trim().replace("px", ""));
                                
                                Double height = Double.parseDouble(sizes[1].trim().replace("px", ""));

								//

								if (marginisset == true)
									method.body().directStatement("return new Dimension(" + (width.intValue() + "-this.marginleft") + ", " + (height.intValue() + "-this.margintop"));

								if (marginisset == false)
									method.body().directStatement("return new Dimension(" + (width.intValue()) + ", " + (height.intValue()) + ");");
							}
                            catch(Exception e)
                            {
                                //
                            }
                            finally
                            {
                                continue;
                            }
                        }                                                
                    }
					else
					{
                        method.body().directStatement("return new Dimension(parent.getWidth(), 50);");
                    }

					//

                    continue;
                }

                if (attribute.getNodeName().equalsIgnoreCase("setBackgroundImage"))
                {
                    String string = "this.backgroundimagename = \""+uip.backgroundimagename+"\";\n\t";
                    
                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);
                    
                    continue;                    
                }
                
                if(attribute.getNodeName().startsWith("set"))
                {
                    //
                    
                    String string = "this."+attribute.getNodeName()+"("+attribute.getNodeValue()+");\n\t";
                    
                    uip.constructor1.body().directStatement(string);

                    uip.constructor2.body().directStatement(string);
                    
                    continue;
                }
            }                   
        }
        catch(Exception exception)
        {
			exception.printStackTrace();
		}
		finally
		{
			//
			try
			{
				Bodi.removecontext("bodi://apml.org/uioutmanager/build/setters");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
    
    private void setfields(Uiparameter uip)
    {
        try
        {                                       
            uip.jdc.direct("\n\t");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.KeyEvent"), "importref_001");                          

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.KeyStroke"), "importref_002");               

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.event.ActionEvent"), "importref_003"); 

            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.ImageIcon"), "importref_004");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.net.URL"), "importref_005");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Color"), "importref_006");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.BorderLayout"), "importref_007");

            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.FlowLayout"), "importref_008");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.GridLayout"), "importref_009");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Color"), "importref_010");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.border.EmptyBorder"), "importref_011");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.event.ChangeEvent"), "importref_012");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Dimension"), "importref_013");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.awt.Rectangle"), "importref_014");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("javax.imageio.ImageIO"), "importref_015");
            
            uip.jdc.field(JMod.PUBLIC, Class.forName("java.io.File"), "importref_016");
            
                        
            NodeList children = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);  
            
            
            /*-----------------------------------------------------------------*/
                        
            for(int i=0; i<children.getLength(); i++)                           
            {       
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));

                if (uipi == null) continue; //verify correctness

                new Jcmabstractbuilder().addListenerFields(uip, children.item(i));
            }

            
            /*-----------------------------------------------------------------*/

            uip.jdc.direct("\n");

            for(int i=0; i<children.getLength(); i++)
            {
                Uiparameter uipi = (Uiparameter)Bodi.context("widgets").softpull(children.item(i));

                if (uipi == null) continue; //verify correctness

                uip.jdc.direct("    " + uipi.classname + " " + uipi.instancename + ";\n");
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }
    
    /**
     * 
     * @param uip The user interface paraameter 
     */
    private void setdevolvement(Uiparameter uip)
    {        
        try
        {
            uip.jdc.field(JMod.PUBLIC, java.awt.Component.class, "parent");

            uip.jdc.field(JMod.PUBLIC, apml.system.Apmlsystem.class, "system");
        }
        catch(Exception exception)
        {
            System.err.println(exception);
        }        
    }
     
    /**
     * 
     * @param uip The user interface parameter
     */
    private void setchildren(Uiparameter uip)
    {
        try
        {             
            //Uiparameter uip = (Uiparameter)Bodi.context("widgets").pull(jcodemodel);   
            
            //NodeList nodes = (NodeList)uip.xpath.evaluate("./*", uip.node, XPathConstants.NODESET);                                                    
            
            this.doinstantiation(uip);
           
            this.dohierarchy(uip);
                  
            this.dodevolvement(uip);
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }        
    }    
    
    /**
     * 
     * @param uip The user interface parameter
     */
    private void doinstantiation(Uiparameter uip)
    {                  
        uip.constructor1.body().directStatement("/* ------------------  instantiation  ---------------- */\n\t");
        
        uip.constructor2.body().directStatement("/* ------------------  instantiation  ---------------- */\n\t");

		//

        if(uip.children==null) return;

		//

		if (uip.jdc._extends().name().contains("JFrame") || uip.jdc._extends().name().contains("JPanel")) /// jframe & jpanel instantiation case
		{
			for (int i = 0; i < uip.children.getLength(); i++)
			{
				Uiparameter uipi = (Uiparameter) Bodi.context("widgets").softpull(uip.children.item(i));

				//

				if (uipi == null)
					continue; ///safety check; ok

				//

				if (uipi.jdc._extends().name().contains("JSplitPane")) /// jsplitpane instantiation
				{
					try
					{
						uip.jdc.field(JMod.PUBLIC, Class.forName("javax.swing.JSplitPane"), "importref_017");

						//

						if (uipi.children.getLength() < 2 || uipi.children.getLength() > 2)
							break;

						//

						Uiparameter lt_addend = (Uiparameter) Bodi.context("widgets").softpull(uipi.children.item(0));

						Uiparameter rt_addend = (Uiparameter) Bodi.context("widgets").softpull(uipi.children.item(1));

						//

						uip.constructor1.body().directStatement("this." + uipi.classname.toLowerCase() + " = new " + uipi.classname + "(this, JSplitPane.HORIZONTAL_SPLIT, new " + lt_addend.classname + "(this), new " + rt_addend.classname + "(this));\n\t");

						uip.constructor2.body().directStatement("this." + uipi.classname.toLowerCase() + " = new " + uipi.classname + "(this, system, JSplitPane.HORIZONTAL_SPLIT, new " + lt_addend.classname + "(this), new " + rt_addend.classname + "(this));\n\t");
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					//

					uip.constructor1.body().directStatement("this." + uipi.classname.toLowerCase() + " = new " + uipi.classname + "(this);\n\t");

					uip.constructor2.body().directStatement("this." + uipi.classname.toLowerCase() + " = new " + uipi.classname + "(this);\n\t");
				}
			}

			return;
		}

		//

		if (uip.jdc._extends().name().contains("JSplitPane")) ///  jsplitpane instantiation case
		{

			Uiparameter component_000 = (Uiparameter) Bodi.context("widgets").softpull(uip.children.item(0));

			Uiparameter component_001 = (Uiparameter) Bodi.context("widgets").softpull(uip.children.item(1));

			//

			if (component_000 == null)
				return;

			if (component_001 == null)
				return;

			//

			uip.constructor1.body().directStatement("this." + component_000.classname.toLowerCase() + " = (" + component_000.classname + ")component_000;\n\t");

			uip.constructor1.body().directStatement("this." + component_001.classname.toLowerCase() + " = (" + component_001.classname + ")component_001;\n\t");

			//

			uip.constructor2.body().directStatement("this." + component_000.classname.toLowerCase() + " = (" + component_000.classname + ")component_000;\n\t");

			uip.constructor2.body().directStatement("this." + component_001.classname.toLowerCase() + " = (" + component_001.classname + ")component_001;\n\t");

			//

			return;
		}

		//

		for (int i = 0; i < uip.children.getLength(); i++) /// general javax.swing.JComponent instantiation case
		{
			Uiparameter uipi = (Uiparameter) Bodi.context("widgets").softpull(uip.children.item(i));

			//

            if (uipi == null) continue;

			//

			if (uip.jdc._extends().name().contains("JSplitPane"))
				continue; /// program to skip the jsplitpane instantiation case

			//

            uip.constructor1.body().directStatement("this." + uipi.classname.toLowerCase() + " = new " + uipi.classname + "(this);\n\t");

            uip.constructor2.body().directStatement("this." + uipi.classname.toLowerCase() + " = new " + uipi.classname + "(this);\n\t");

        }

		//

		for (int i = 0; i < uip.children.getLength(); i++) /// listener instantiation
		{
			new Jcmabstractbuilder().addListenerInstantiation(uip, uip.children.item(i));
        }
    }
       
    private void dohierarchy(Uiparameter uip)
    {                 
        uip.constructor1.body().directStatement("/* ------------------  hierarchy  -------------------- */\n\t");
        
        uip.constructor2.body().directStatement("/* ------------------  hierarchy  -------------------- */\n\t");
            
        if(uip.children==null) return;

		//

		if (uip.jdc._extends().name().contains("JSplitPane"))
			return; //exception for jsplitpanes (at this time)

		//

        for(int i=0; i<uip.children.getLength(); i++)
        {    
            Uiparameter child = (Uiparameter)Bodi.context("widgets").softpull(uip.children.item(i));
            
            //safety check
            if (child == null) continue;
                        
            String parentclassname = uip.classname;
            
            String childsuperclass = child.jdc._extends().name();
            
            String childfieldname = child.jdc.name().toLowerCase();                            

            //jframe as parent etc
            if(childsuperclass.contains("JMenuBar"))            
            {
                uip.constructor1.body().directStatement("this.setJMenuBar(" + childfieldname + ");\n\t");

                uip.constructor2.body().directStatement("this.setJMenuBar("+childfieldname+");\n\t");
                
                continue;                
            }                        

            //else a more standard case
            else
            {
                uip.constructor1.body().directStatement("this.add("+childfieldname+");\n\t");

                uip.constructor2.body().directStatement("this.add("+childfieldname+");\n\t");
                
                continue;
            }
        } 
    }
    
    private void dodevolvement(Uiparameter uip)
    {       
        /* ------------------------ devolvement setters ------------------------ */
        
        uip.constructor1.body().directStatement("/* ------------------  devolvement  -------------------- */\n\t");   
        
        uip.constructor2.body().directStatement("/* ------------------  devolvement  -------------------- */\n\t");   
        
        
        /*---------------------------------------------------------------------*/
        
        uip.constructor1.body().directStatement("this.parent = parent;\n\t");                        
        
        
        /*---------------------------------------------------------------------*/
        
        uip.constructor2.body().directStatement("this.parent = parent;\n\t");  
        
        uip.constructor2.body().directStatement("this.system = system;\n\t"); 
        
        
        /*---------------------------------------------------------------------*/
        
        uip.constructor1.body().directStatement("this.setVisible(true);\n\t");   
        
        uip.constructor2.body().directStatement("this.setVisible(true);\n\t");            
    }
}