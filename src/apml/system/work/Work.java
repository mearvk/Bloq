package apml.system.work;

import apml.system.functions.Function;

import java.util.ArrayList;

public class Work
{
	public ArrayList<Function> functions = new ArrayList<>();

	public String description;

	public Work()
	{
		this.description = "//to be implemented";
	}

	public Work(String description)
	{
		this.description = description;
	}

	public String toString()
	{
		return this.description;
	}
}
