package apml.xpath.helpers;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;


/**
 * @author Max Rupplin
 */
public class BKUPXpathbuilder
{
	protected final Integer hash = 0x888fe8;

	public String sexpr0;
	public String sexpr1;
	public String sexpr2;
	public String sexpr3;
	public String sexpr4;
	public String sexpr5;
	public String sexpr6;
	public String sexpr7;
	public String sexpr8;
	public String sexpr9;
	public String sexpr10;
	public String sexpr11;

	public XPath xpath;

	public XPathExpression expr0 = null;
	public XPathExpression expr1 = null;
	public XPathExpression expr2 = null;
	public XPathExpression expr3 = null;
	public XPathExpression expr4 = null;
	public XPathExpression expr5 = null;
	public XPathExpression expr6 = null;
	public XPathExpression expr7 = null;
	public XPathExpression expr8 = null;
	public XPathExpression expr9 = null;
	public XPathExpression expr10 = null;
	public XPathExpression expr11 = null;

	public Xpathparameter xparam = null;

	public BKUPXpathbuilder(String apmltag, Xpathparameter xparam) throws Exception
	{
		this(apmltag, xparam, xparam.parser.xpath);
	}

	public BKUPXpathbuilder(String apmltag, Xpathparameter xparam, XPath xpath) throws Exception
	{
		this.xpath = xpath;

		this.xparam = xparam;

		this.sexpr0 = "count(" + apmltag + ")";
		this.sexpr1 = apmltag;
		this.sexpr2 = apmltag + "/@autostart";
		this.sexpr3 = apmltag + "/@class";
		this.sexpr4 = apmltag + "/@id";
		this.sexpr5 = apmltag + "/@init";
		this.sexpr6 = apmltag + "/@package";
		this.sexpr7 = apmltag + "/@run";
		this.sexpr8 = apmltag + "/@start";
		this.sexpr9 = apmltag + "/implements";
		this.sexpr10 = apmltag + "/listener";
		this.sexpr11 = apmltag + "/object";

		this.xparam.e0000_count = xpath.compile(sexpr0);
		this.xparam.e0001_tagname = xpath.compile(sexpr1);
		this.xparam.e0002_autostart = xpath.compile(sexpr2);
		this.xparam.e0003_classname = xpath.compile(sexpr3);
		this.xparam.e0004_id = xpath.compile(sexpr4);
		this.xparam.e0005_init = xpath.compile(sexpr5);
		this.xparam.e0006_package = xpath.compile(sexpr6);
		this.xparam.e0007_run = xpath.compile(sexpr7);
		this.xparam.e0008_start = xpath.compile(sexpr8);
		this.xparam.e0009_implements = xpath.compile(sexpr9);
		this.xparam.e0010_listeners = xpath.compile(sexpr10);
		this.xparam.e0011_objects = xpath.compile(sexpr11);
	}
}
