/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ottobackwards;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.ottobackwards.dsl.Syslog5424Visitor;
import org.ottobackwards.dsl.generated.Rfc5424Lexer;
import org.ottobackwards.dsl.generated.Rfc5424Parser;

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
