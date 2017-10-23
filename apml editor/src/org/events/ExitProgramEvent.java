package org.events;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ExitProgramEvent extends ActionEvent
{
	public JComponent source;

	public ExitProgramEvent(JComponent source)
	{
		super(source, 0, "exit_program");

		this.source = source;
	}
}
