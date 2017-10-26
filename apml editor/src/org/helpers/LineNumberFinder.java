package org.helpers;

import apml.system.bodi.Bodi;
import org.widgets.JTree_000;
import org.widgets.RSTextPane_000;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Pattern;

public class LineNumberFinder
{
	public Integer charcount=0;

	public Integer linecount=0;

	//

	public int processByClick(DefaultMutableTreeNode selected, RSTextPane_000 textpane, MouseEvent e)
	{
		JTree_000 tree;

		Enumeration<DefaultMutableTreeNode> siblings;

		//

		String finalfullstring = "";

		String finalparentstring = "";

		//

		tree = (JTree_000)Bodi.context("editor").pull("//editor/ui/jtree_000");

		//selected = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();

		//

		if(selected==null)
		{
			System.err.println("No selected JTreeNode could be found.");

			return -1;
		}

		else
		{

			siblings = selected.getParent().children();

			//

			if (selected == null || selected.getParent() == null)
			{
				tree.selected_child_index = 0;
			}
			else
			{
				for (int i = 0; i < selected.getParent().getChildCount(); i++)
				{
					if (selected.equals(selected.getParent().getChildAt(i)))
					{
						tree.selected_child_index = i;

						System.out.println("ith index is " + i);
					}
				}
			}

			//

			Point point = e.getPoint();

			TreePath path = tree.getClosestPathForLocation(point.x, point.y);

			String pathstring = "";

			//

			String[] tokens;

			pathstring = path.toString().replace("[", "");

			pathstring = pathstring.toString().replace("]", "");

			pathstring = pathstring.toString().replace("User Design Area,", "");

			pathstring = pathstring.toString().replace("APML Projects,", "");

			pathstring = pathstring.toString().replace(" ", "");

			//

			tokens = new String[pathstring.split(",").length];

			tokens = pathstring.split(",");

			//

			for (int i = 0; i < tokens.length - 1; i++)
			{
				finalfullstring += "/" + tokens[i];
			}

			//

			ArrayList<DefaultMutableTreeNode> _siblings = new ArrayList<>();

			for (int i = 0; i < tree.selected_child_index; i++)
			{
				DefaultMutableTreeNode node = siblings.nextElement();

				_siblings.add(node);

				//System.out.println("Siblings: " + node);
			}

			//

			for (int i = 0; i < tree.selected_child_index; i++)
			{
				finalfullstring = finalfullstring + "/" + _siblings.get(i);
			}

			finalfullstring += "/" + selected.toString();

			System.out.println("Child token presumes its own CES line and begins: " + finalfullstring);

		}

		//

		String document = textpane.getText();

		BufferedReader reader = new BufferedReader(new StringReader(document));

		String line = "";

		Integer charcount = 0;

		Integer watched_index = 1;

		try
		{
			for (int i = 0; (line = reader.readLine()) != null; i++)
			{
				charcount += line.length();

				//

				String watching = finalfullstring.split("/")[watched_index];

				if (watching.equals(""))
				{
					System.out.println("Correct line could not be found; unable to parse walkable tree path.");

					break;
				}

				//

				Pattern regex = Pattern.compile("<(" + watching + ")[(\\s.*)(>)]"); //

				if (regex.matcher(line).find())
				{
					watched_index += 1;

					System.out.println("Matcher found: " + regex + " in " + line);

					if (watched_index == finalparentstring.split("/").length)
					{
						System.out.println("Correct parent line should be around " + i);
					}

					if (watched_index == finalfullstring.split("/").length)
					{
						System.out.println("Correct child line should be around " + i);

						this.linecount = i;

						break;
					}
				}
				else
				{
					System.out.println("Matcher did not find: " + regex + " in " + line);
				}
			}
		}
		catch (Exception exception)
		{
			//
		}

		return this.linecount;
	}

	public int processByPath(DefaultMutableTreeNode treenode, RSTextPane_000 textpane, TreePath path)
	{
		JTree_000 tree;

		DefaultMutableTreeNode selected;

		Enumeration<DefaultMutableTreeNode> siblings;

		//

		String finalfullstring = "";

		String finalparentstring = "";

		//

		tree = (JTree_000)Bodi.context("editor").pull("//editor/ui/jtree_000");

		selected = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();

		if(selected!=null || selected.getParent()!=null)
		{

			siblings = selected.getParent().children();

			//

			if (selected == null || selected.getParent() == null)
			{
				tree.selected_child_index = 0;
			}
			else
			{
				for (int i = 0; i < selected.getParent().getChildCount(); i++)
				{
					if (selected.equals(selected.getParent().getChildAt(i)))
					{
						tree.selected_child_index = i;

						System.out.println("ith index is " + i);
					}
				}
			}

			//

			String pathstring = "";

			//

			String[] tokens;

			pathstring = path.toString().replace("[", "");

			pathstring = pathstring.toString().replace("]", "");

			pathstring = pathstring.toString().replace("User Design Area,", "");

			pathstring = pathstring.toString().replace("APML Projects,", "");

			pathstring = pathstring.toString().replace(" ", "");

			//

			tokens = new String[pathstring.split(",").length];

			tokens = pathstring.split(",");

			//

			for (int i = 0; i < tokens.length - 1; i++)
			{
				finalfullstring += "/" + tokens[i];
			}

			//

			ArrayList<DefaultMutableTreeNode> _siblings = new ArrayList<>();

			for (int i = 0; i < tree.selected_child_index; i++)
			{
				DefaultMutableTreeNode node = siblings.nextElement();

				_siblings.add(node);

				//System.out.println("Siblings: " + node);
			}

			//

			for (int i = 0; i < tree.selected_child_index; i++)
			{
				finalfullstring = finalfullstring + "/" + _siblings.get(i);
			}

			finalfullstring += "/" + selected.toString();

			System.out.println("Child token presumes its own CES line and begins: " + finalfullstring);
		}

		//

		String document = textpane.getText();

		BufferedReader reader = new BufferedReader(new StringReader(document));

		String line = "";

		Integer charcount = 0;

		Integer watched_index = 1;

		try
		{
			for (int i = 0; (line = reader.readLine()) != null; i++)
			{
				charcount += line.length();

				//

				String watching = finalfullstring.split("/")[watched_index];

				if (watching.equals(""))
				{
					System.out.println("Correct line could not be found; unable to parse walkable tree path.");

					break;
				}

				//

				Pattern regex = Pattern.compile("<(" + watching + ")[(\\s.*)(>)]"); //

				if (regex.matcher(line).find())
				{
					watched_index += 1;

					System.out.println("Matcher found: " + regex + " in " + line);

					if (watched_index == finalparentstring.split("/").length)
					{
						System.out.println("Correct parent line should be around " + i);
					}

					if (watched_index == finalfullstring.split("/").length)
					{
						System.out.println("Correct child line should be around " + i);

						this.linecount = i;

						break;
					}
				}
				else
				{
					System.out.println("Matcher did not find: " + regex + " in " + line);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return this.linecount;
	}
}
