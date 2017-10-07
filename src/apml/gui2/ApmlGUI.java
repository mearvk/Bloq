package apml.gui2;

import apml.gui2.org.widgets.JFrame_000;

import javax.swing.*;
import java.awt.*;

public class ApmlGUI extends Component
{
	public ApmlGUI()
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				new JFrame_000(null);
			}
		});
	}

	public static void main(String... args)
	{
		new ApmlGUI();
	}
}