
package org.widgets;

import apml.compilers.java.codemodel.Bloqcompiler;
import apml.system.Apmlbasesystem;
import apml.system.bodi.Bodi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;


/**
 * Software programmatically produced by Bloq implementation version 1.05 - Bodi Remote Version
 * 
 * @author
 * @see
 * @since
 * @version
 * 
 */
public class JMenu_006
    extends JMenu
{

    public KeyEvent importref_001;
    public KeyStroke importref_002;
    public ActionEvent importref_003;
    public ImageIcon importref_004;
    public URL importref_005;
    public Color importref_006;
    public BorderLayout importref_007;
    public FlowLayout importref_008;
    public GridLayout importref_009;
    public Color importref_010;
    public EmptyBorder importref_011;
    public ChangeEvent importref_012;
    public Dimension importref_013;
    public Rectangle importref_014;
    public ImageIO importref_015;
    public File importref_016;
    public Component parent;
    public Apmlbasesystem system;

    //

	public String bodi = "//editor/ui/jmenu_006";

	//

    /**
     * 
     * @param parent : The tree AWT object.
     */
    public JMenu_006(Component parent) {
        // setters 
	
        this.setText("  Build  ");

        this.setMnemonic(KeyEvent.VK_B);
	
        // instantiation

		this.menu_listener_000 = new BuildApmlStandaloneListener();
	
        this.jmenuitem_002 = new JMenuItem_002(this);
	
        this.jmenuitem_003 = new JMenuItem_003(this);

        this.jmenuitem_004 = new JMenuItem_004(this);
	
        // hierarchy 
	
        this.add(jmenuitem_002);
	
        this.add(jmenuitem_003);

        this.add(jmenuitem_004);
	
        // devolvement 
	
        this.parent = parent;
	
        this.setVisible(true);
	
        // listeners 

		//bodi

		Bodi.context("editor").put(this.bodi, this);
	}

    /**
     * 
     * @param parent : The tree AWT object.
     * @param system : The APML system object.
     */
    public JMenu_006(Component parent, Apmlbasesystem system) {
        // setters 
	
        this.setText("  Build  ");

        this.setMnemonic(KeyEvent.VK_B);
	
        // instantiation 

		this.menu_listener_000 = new BuildApmlStandaloneListener();

        this.jmenuitem_002 = new JMenuItem_002(this);

        this.jmenuitem_003 = new JMenuItem_003(this);

        this.jmenuitem_004 = new JMenuItem_004(this);

        // hierarchy

        this.add(jmenuitem_002);

        this.add(jmenuitem_003);

        this.add(jmenuitem_004);
	
        // devolvement 
	
        this.parent = parent;
	
        this.system = system;
	
        this.setVisible(true);
	
        // listeners

		//bodi

		Bodi.context("editor").put(this.bodi, this);
    }
    
	
    JMenuItem_002 jmenuitem_002;
    JMenuItem_003 jmenuitem_003;
    JMenuItem_004 jmenuitem_004;

    //

	public BuildApmlStandaloneListener menu_listener_000 = new BuildApmlStandaloneListener();
}

class BuildApmlStandaloneListener implements ActionListener
{
	@Override
	public void actionPerformed(ActionEvent e)
	{
		switch (e.getActionCommand())
		{
			case "Apml Standalone":

				this.doApmlstandalonebuild();

				break;

			case "Apml System":

				this.doApmlsystembuild();

				break;

			case "Rebuild":

				this.doApmlrebuild();

				break;

			default: break;
		}
	}

	//
	private void doApmlstandalonebuild()
	{
		RSTextPane_000 textpane;

		String target_text;

		Bloqcompiler compiler;

		//

		textpane = (RSTextPane_000)Bodi.context("editor").pull("//editor/ui/rstextpane_000");

		target_text = textpane.getText();

		compiler =  new Bloqcompiler();

		//

		if(target_text==null || target_text.length()==0)
		{
			//Bodi.context("editor").pull();

			//

			JFileChooser input;

			input = new JFileChooser();

			input.setDialogTitle("Please Select APML Document");

			input.setFileSelectionMode(JFileChooser.FILES_ONLY);

			input.showOpenDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

			//

			compiler.fileguardian.apmlinputfile = input.getSelectedFile();

			compiler.fileguardian.apmlfilename = input.getSelectedFile().getName() + "/";

			compiler.fileguardian.apmlinurl = input.getSelectedFile().getPath() + "/";

			//

			System.out.println("TODO: Please verify integrity of APML file");

			//

			JFileChooser output;

			output = new JFileChooser();

			output.setDialogTitle("Please Select Output Directory");

			output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			output.showSaveDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

			//

			compiler.fileguardian.basedirurl = output.getSelectedFile().getPath().toString() + "/";

			compiler.fileguardian.apmlinurl = output.getSelectedFile().getPath()+"/in/";

			compiler.fileguardian.apmloutjarurl = output.getSelectedFile().getPath() + "/out/jar/";

			compiler.fileguardian.apmlinjarurl = output.getSelectedFile().getPath() + "/in/jar/";

			//

			compiler.fileguardian.apmlinputfile = output.getSelectedFile();

			compiler.fileguardian.apmlfilename = output.getSelectedFile().getName() + "/";

			compiler.fileguardian.apmlinurl = output.getSelectedFile().getPath() + "/";

			//

			compiler.setapmlfiles(compiler.fileguardian);

			compiler.settempfiles(compiler.inputmanager);

			compiler.setoutputfiles(compiler.inputmanager);

			compiler.setsourcefiles(compiler.outputmanager);

			compiler.writebytecode(compiler.inputmanager);

			try
			{
				compiler.writejarfile();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			JFileChooser output;

			output = new JFileChooser();

			output.setDialogTitle("Please Select Output Directory");

			output.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

			output.showSaveDialog((APMLGui)Bodi.context("editor").pull("//editor/ui/apmlgui"));

			//

			compiler.fileguardian.basedirurl = output.getSelectedFile().getPath().toString() + "/";

			compiler.fileguardian.apmlinurl = output.getSelectedFile().getPath()+"/in/";

			compiler.fileguardian.apmloutjarurl = output.getSelectedFile().getPath() + "/out/jar/";

			compiler.fileguardian.apmlinjarurl = output.getSelectedFile().getPath() + "/in/jar/";

			//

			compiler.setapmlfiles(compiler.fileguardian);

			compiler.settempfiles(compiler.inputmanager);

			compiler.setoutputfiles(compiler.inputmanager);

			compiler.setsourcefiles(compiler.outputmanager);

			compiler.writebytecode(compiler.inputmanager);
		}
	}

	//
	private void doApmlsystembuild()
	{
		System.out.println("xxx");
	}

	//
	private void doApmlrebuild()
	{
		System.out.println("xxx");
	}
}