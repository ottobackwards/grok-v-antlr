package org.ottobackwards;

import com.sun.org.apache.xml.internal.security.utils.RFC2253Parser;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.ottobackwards.dsl.Syslog5424Visitor;
import org.ottobackwards.dsl.generated.Rfc5424Lexer;
import org.ottobackwards.dsl.generated.Rfc5424Parser;
import org.ottobackwards.dsl.generated.Rfc5424Parser.Syslog_msgContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        try {
            Rfc5424Lexer lexer = new Rfc5424Lexer(new ANTLRFileStream("src/test/resources/log.txt"));
            Rfc5424Parser parser = new Rfc5424Parser(new CommonTokenStream(lexer));
            parser.setBuildParseTree(true);
            ParseTree ctx = parser.syslog_msg();
            Syslog5424Visitor visitor = new Syslog5424Visitor();
            visitor.visit(ctx);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
