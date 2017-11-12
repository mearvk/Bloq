package org.listeners;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CustomKeyEventListener implements KeyListener
{
	public RSyntaxTextArea textarea;

	public CustomKeyEventListener(RSyntaxTextArea textarea)
	{
		this.textarea = textarea;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		//
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		int MODS = e.getModifiers();

		//

		if ((e.getKeyCode() == KeyEvent.VK_HOME && MODS == KeyEvent.SHIFT_MASK) || (e.getKeyCode() == KeyEvent.VK_END && MODS == KeyEvent.SHIFT_MASK))
		{
			e.consume();

			//

			int start = 0;

			int end = 0;

			//

			if (e.getKeyCode() == KeyEvent.VK_HOME)
			{
				start = this.textarea.getLineStartOffsetOfCurrentLine() + 0;

				end = this.textarea.getCaretPosition();

				this.textarea.setCaretPosition(start);
			}

			if (e.getKeyCode() == KeyEvent.VK_END)
			{
				start = this.textarea.getCaretPosition();

				end = this.textarea.getLineEndOffsetOfCurrentLine() - 1;

				this.textarea.setCaretPosition(end);
			}

			//

			this.textarea.select(start, end);

			return;
		}

		//

		if (e.getKeyCode() == KeyEvent.VK_HOME)
		{
			e.consume();

			//

			int caretposition = this.textarea.getLineStartOffsetOfCurrentLine() + 0;

			//

			this.textarea.setCaretPosition(caretposition);

			return;
		}

		//

		if (e.getKeyCode() == KeyEvent.VK_END)
		{
			e.consume();

			//

			int caretposition = this.textarea.getLineEndOffsetOfCurrentLine() - 1;

			//

			this.textarea.setCaretPosition(caretposition);

			return;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		//
	}
}