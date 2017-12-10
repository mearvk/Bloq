package org.helpers;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMaker;
import org.fife.ui.rsyntaxtextarea.RSyntaxUtilities;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMap;

import javax.swing.text.Segment;

public class ApmlKeyWordTokenMap extends AbstractTokenMaker
{

	@Override
	public TokenMap getWordsToHighlight()
	{
		TokenMap tokenMap = new TokenMap();

		tokenMap.put("jpanel", Token.RESERVED_WORD);

		tokenMap.put("jframe", Token.RESERVED_WORD);

		tokenMap.put("package", Token.RESERVED_WORD);

		tokenMap.put("project", Token.RESERVED_WORD);

		tokenMap.put("jscrollpane", Token.RESERVED_WORD);

		tokenMap.put("jtextpane", Token.FUNCTION);

		tokenMap.put("scanf", Token.FUNCTION);

		tokenMap.put("fopen", Token.FUNCTION);

		return tokenMap;
	}

	/**
	 * Returns a list of tokens representing the given text.
	 *
	 * @param text           The text to break into tokens.
	 * @param startTokenType The token with which to start tokenizing.
	 * @param startOffset    The offset at which the line of tokens begins.
	 * @return A linked list of tokens representing <code>text</code>.
	 */
	public Token getTokenList(Segment text, int startTokenType, int startOffset)
	{

		resetTokenList();

		char[] array = text.array;
		int offset = text.offset;
		int count = text.count;
		int end = offset + count;

		// Token starting offsets are always of the form:
		// 'startOffset + (currentTokenStart-offset)', but since startOffset and
		// offset are constant, tokens' starting positions become:
		// 'newStartOffset+currentTokenStart'.
		int newStartOffset = startOffset - offset;

		int currentTokenStart = offset;
		int currentTokenType = startTokenType;

		for (int i = offset; i < end; i++)
		{

			char c = array[i];

			switch (currentTokenType)
			{

				case Token.NULL:

					currentTokenStart = i;   // Starting a new token here.

					switch (c)
					{

						case ' ':
						case '\t':
							currentTokenType = Token.WHITESPACE;
							break;

						case '"':
							currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
							break;

						case '#':
							currentTokenType = Token.COMMENT_EOL;
							break;

						default:
							if (RSyntaxUtilities.isDigit(c))
							{
								currentTokenType = Token.LITERAL_NUMBER_DECIMAL_INT;
								break;
							}
							else if (RSyntaxUtilities.isLetter(c) || c == '/' || c == '_')
							{
								currentTokenType = Token.IDENTIFIER;
								break;
							}

							// Anything not currently handled - mark as an identifier
							currentTokenType = Token.IDENTIFIER;
							break;

					} // End of switch (c).

					break;

				case Token.WHITESPACE:

					switch (c)
					{

						case ' ':
						case '\t':
							break;   // Still whitespace.

						case '"':
							addToken(text, currentTokenStart, i - 1, Token.WHITESPACE, newStartOffset + currentTokenStart);
							currentTokenStart = i;
							currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
							break;

						case '#':
							addToken(text, currentTokenStart, i - 1, Token.WHITESPACE, newStartOffset + currentTokenStart);
							currentTokenStart = i;
							currentTokenType = Token.COMMENT_EOL;
							break;

						default:   // Add the whitespace token and start anew.

							addToken(text, currentTokenStart, i - 1, Token.WHITESPACE, newStartOffset + currentTokenStart);
							currentTokenStart = i;

							if (RSyntaxUtilities.isDigit(c))
							{
								currentTokenType = Token.LITERAL_NUMBER_DECIMAL_INT;
								break;
							}
							else if (RSyntaxUtilities.isLetter(c) || c == '/' || c == '_')
							{
								currentTokenType = Token.IDENTIFIER;
								break;
							}

							// Anything not currently handled - mark as identifier
							currentTokenType = Token.IDENTIFIER;

					} // End of switch (c).

					break;

				default: // Should never happen
				case Token.IDENTIFIER:

					switch (c)
					{

						case ' ':
						case '\t':
							addToken(text, currentTokenStart, i - 1, Token.IDENTIFIER, newStartOffset + currentTokenStart);
							currentTokenStart = i;
							currentTokenType = Token.WHITESPACE;
							break;

						case '"':
							addToken(text, currentTokenStart, i - 1, Token.IDENTIFIER, newStartOffset + currentTokenStart);
							currentTokenStart = i;
							currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
							break;

						default:
							if (RSyntaxUtilities.isLetterOrDigit(c) || c == '/' || c == '_')
							{
								break;   // Still an identifier of some type.
							}
							// Otherwise, we're still an identifier (?).

					} // End of switch (c).

					break;

				case Token.LITERAL_NUMBER_DECIMAL_INT:

					switch (c)
					{

						case ' ':
						case '\t':
							addToken(text, currentTokenStart, i - 1, Token.LITERAL_NUMBER_DECIMAL_INT, newStartOffset + currentTokenStart);
							currentTokenStart = i;
							currentTokenType = Token.WHITESPACE;
							break;

						case '"':
							addToken(text, currentTokenStart, i - 1, Token.LITERAL_NUMBER_DECIMAL_INT, newStartOffset + currentTokenStart);
							currentTokenStart = i;
							currentTokenType = Token.LITERAL_STRING_DOUBLE_QUOTE;
							break;

						default:

							if (RSyntaxUtilities.isDigit(c))
							{
								break;   // Still a literal number.
							}

							// Otherwise, remember this was a number and start over.
							addToken(text, currentTokenStart, i - 1, Token.LITERAL_NUMBER_DECIMAL_INT, newStartOffset + currentTokenStart);
							i--;
							currentTokenType = Token.NULL;

					} // End of switch (c).

					break;

				case Token.COMMENT_EOL:
					i = end - 1;
					addToken(text, currentTokenStart, i, currentTokenType, newStartOffset + currentTokenStart);
					// We need to set token type to null so at the bottom we don't add one more token.
					currentTokenType = Token.NULL;
					break;

				case Token.LITERAL_STRING_DOUBLE_QUOTE:
					if (c == '"')
					{
						addToken(text, currentTokenStart, i, Token.LITERAL_STRING_DOUBLE_QUOTE, newStartOffset + currentTokenStart);
						currentTokenType = Token.NULL;
					}
					break;

			} // End of switch (currentTokenType).

		} // End of for (int i=offset; i<end; i++).

		switch (currentTokenType)
		{

			// Remember what token type to begin the next line with.
			case Token.LITERAL_STRING_DOUBLE_QUOTE:
				addToken(text, currentTokenStart, end - 1, currentTokenType, newStartOffset + currentTokenStart);
				break;

			// Do nothing if everything was okay.
			case Token.NULL:
				addNullToken();
				break;

			// All other token types don't continue to the next line...
			default:
				addToken(text, currentTokenStart, end - 1, currentTokenType, newStartOffset + currentTokenStart);
				addNullToken();

		}

		// Return the first token in our linked list.
		return firstToken;

	}
}
