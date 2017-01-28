/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apml.helpers;

import apml.compilers.codemodel.StdCodeModelCompiler;
import apml.xml.handlers.StdXmlHandler;
import java.io.File;

public class StdApmlWriter
{
    public StdXmlHandler handler;
    
    public StdApmlWriter(StdXmlHandler handler)
    {
        this.handler = handler;
    }
    
    public void write(StdCodeModelCompiler compiler, File output) throws Exception
    {
        throw new Exception("No writer implemented at this hour!");
    }
}
