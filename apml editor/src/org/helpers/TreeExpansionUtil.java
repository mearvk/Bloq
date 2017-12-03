//http://www.algosome.com/articles/save-jtree-expand-state.html @author G. Cope

package org.helpers;

import javax.swing.*;

//Utility class that can be used to retrieve and/or restore the expansion state of a JTree.

public class TreeExpansionUtil
{
	private final JTree tree;

	public TreeExpansionUtil(JTree tree) //Constructs a new utility object based upon the parameter JTree
	{
		this.tree = tree;
	}

	public String getExpansionState() //Retrieves the expansion state as a String, defined by a comma delimited list of
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < tree.getRowCount(); i++)
		{
			if (tree.isExpanded(i))
			{
				sb.append(i).append(",");
			}
		}

		return sb.toString();
	}

	public void setExpansionState(String s) //Sets the expansion state based upon a comma delimited list of row indexes that are expanded.
	{
		String[] indexes = s.split(",");

		for (String st : indexes)
		{
			int row = Integer.parseInt(st);

			tree.expandRow(row);
		}
	}
}

