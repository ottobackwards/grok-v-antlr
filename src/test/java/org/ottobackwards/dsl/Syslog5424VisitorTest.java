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

package org.ottobackwards.dsl;

import java.util.Map;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import org.ottobackwards.dsl.generated.Rfc5424Lexer;
import org.ottobackwards.dsl.generated.Rfc5424Parser;
import org.ottobackwards.dsl.util.StructuredDataUtil;

public class Syslog5424VisitorTest {

  private static final String expectedVersion = "1";
  private static final String expectedMessage = "Removing instance";
  private static final String expectedAppName = "d0602076-b14a-4c55-852a-981e7afeed38";
  private static final String expectedHostName = "loggregator";
  private static final String expectedPri = "14";
  private static final String expectedProcId = "DEA";
  private static final String expectedTimestamp = "2014-06-20T09:14:07+00:00";
  private static final String expectedMessageId = "MSG-01";

  private static final String expectedIUT1 = "3";
  private static final String expectedIUT2 = "4";
  private static final String expectedEventSource1 = "Application";
  private static final String expectedEventSource2 = "Other Application";
  private static final String expectedEventID1 = "1011";
  private static final String expectedEventID2 = "2022";

  @Test
  @SuppressWarnings("unchecked")
  public void testAllPresent() throws Exception{
    Map<String,Object> map = handleFile("src/test/resources/log_all.txt");
    Assert.assertEquals(expectedVersion,map.get(SyslogFieldKeys.HEADER_VERSION.getField()));
    Assert.assertEquals(expectedMessage,map.get(SyslogFieldKeys.MESSAGE.getField()));
    Assert.assertEquals(expectedAppName,map.get(SyslogFieldKeys.HEADER_APPNAME.getField()));
    Assert.assertEquals(expectedHostName,map.get(SyslogFieldKeys.HEADER_HOSTNAME.getField()));
    Assert.assertEquals(expectedPri,map.get(SyslogFieldKeys.HEADER_PRI.getField()));
    Assert.assertEquals(expectedProcId,map.get(SyslogFieldKeys.HEADER_PROCID.getField()));
    Assert.assertEquals(expectedTimestamp,map.get(SyslogFieldKeys.HEADER_TIMESTAMP.getField()));
    Assert.assertEquals(expectedMessageId,map.get(SyslogFieldKeys.HEADER_MSGID.getField()));

    // structured data
    Map<String,Object> structured = StructuredDataUtil.unFlattenStructuredData(map);
    Assert.assertTrue(structured.containsKey("exampleSDID@32473"));
    Map<String,Object> example1 = (Map<String,Object>)structured.get("exampleSDID@32473");
    Assert.assertTrue(example1.containsKey("iut"));
    Assert.assertTrue(example1.containsKey("eventSource"));
    Assert.assertTrue(example1.containsKey("eventID"));
    Assert.assertEquals(expectedIUT1,example1.get("iut").toString());
    Assert.assertEquals(expectedEventSource1,example1.get("eventSource").toString());
    Assert.assertEquals(expectedEventID1,example1.get("eventID").toString());

    Assert.assertTrue(structured.containsKey("exampleSDID@32480"));
    Map<String,Object> example2 = (Map<String,Object>)structured.get("exampleSDID@32480");
    Assert.assertTrue(example2.containsKey("iut"));
    Assert.assertTrue(example2.containsKey("eventSource"));
    Assert.assertTrue(example2.containsKey("eventID"));
    Assert.assertEquals(expectedIUT2,example2.get("iut").toString());
    Assert.assertEquals(expectedEventSource2,example2.get("eventSource").toString());
    Assert.assertEquals(expectedEventID2,example2.get("eventID").toString());

  }

  @Test
  public void testMissingHeaderField() throws Exception{
    Map<String,Object> map = handleFile("src/test/resources/log.txt");
    Assert.assertEquals(expectedVersion,map.get(SyslogFieldKeys.HEADER_VERSION.getField()));
    Assert.assertEquals(expectedMessage,map.get(SyslogFieldKeys.MESSAGE.getField()));
    Assert.assertEquals(expectedAppName,map.get(SyslogFieldKeys.HEADER_APPNAME.getField()));
    Assert.assertEquals(expectedHostName,map.get(SyslogFieldKeys.HEADER_HOSTNAME.getField()));
    Assert.assertEquals(expectedPri,map.get(SyslogFieldKeys.HEADER_PRI.getField()));
    Assert.assertEquals(expectedProcId,map.get(SyslogFieldKeys.HEADER_PROCID.getField()));
    Assert.assertEquals(expectedTimestamp,map.get(SyslogFieldKeys.HEADER_TIMESTAMP.getField()));
    Assert.assertFalse(map.containsKey(SyslogFieldKeys.HEADER_MSGID.getField()));

    // structured data
    Map<String,Object> structured = StructuredDataUtil.unFlattenStructuredData(map);
    Assert.assertTrue(structured.containsKey("exampleSDID@32473"));
    Map<String,Object> example1 = (Map<String,Object>)structured.get("exampleSDID@32473");
    Assert.assertTrue(example1.containsKey("iut"));
    Assert.assertTrue(example1.containsKey("eventSource"));
    Assert.assertTrue(example1.containsKey("eventID"));
    Assert.assertEquals(expectedIUT1,example1.get("iut").toString());
    Assert.assertEquals(expectedEventSource1,example1.get("eventSource").toString());
    Assert.assertEquals(expectedEventID1,example1.get("eventID").toString());

    Assert.assertTrue(structured.containsKey("exampleSDID@32480"));
    Map<String,Object> example2 = (Map<String,Object>)structured.get("exampleSDID@32480");
    Assert.assertTrue(example2.containsKey("iut"));
    Assert.assertTrue(example2.containsKey("eventSource"));
    Assert.assertTrue(example2.containsKey("eventID"));
    Assert.assertEquals(expectedIUT2,example2.get("iut").toString());
    Assert.assertEquals(expectedEventSource2,example2.get("eventSource").toString());
    Assert.assertEquals(expectedEventID2,example2.get("eventID").toString());
  }

  @Test
  public void testMissingStructured() throws Exception{
    Map<String,Object> map = handleFile("src/test/resources/log_nils.txt");
    Assert.assertEquals(expectedVersion,map.get(SyslogFieldKeys.HEADER_VERSION.getField()));
    Assert.assertEquals(expectedMessage,map.get(SyslogFieldKeys.MESSAGE.getField()));
    Assert.assertEquals(expectedAppName,map.get(SyslogFieldKeys.HEADER_APPNAME.getField()));
    Assert.assertEquals(expectedHostName,map.get(SyslogFieldKeys.HEADER_HOSTNAME.getField()));
    Assert.assertEquals(expectedPri,map.get(SyslogFieldKeys.HEADER_PRI.getField()));
    Assert.assertEquals(expectedProcId,map.get(SyslogFieldKeys.HEADER_PROCID.getField()));
    Assert.assertEquals(expectedTimestamp,map.get(SyslogFieldKeys.HEADER_TIMESTAMP.getField()));
    Assert.assertFalse(map.containsKey(SyslogFieldKeys.HEADER_MSGID.getField()));
    for (String key : map.keySet()) {
      if(key.startsWith(SyslogFieldKeys.STRUCTURED_BASE.getField())) {
        Assert.assertTrue(false);
      }
    }

  }

  private static Map<String,Object> handleFile(String fileName) throws Exception {
    Rfc5424Lexer lexer = new Rfc5424Lexer(new ANTLRFileStream(fileName));
    Rfc5424Parser parser = new Rfc5424Parser(new CommonTokenStream(lexer));
    parser.setBuildParseTree(true);
    ParseTree ctx = parser.syslog_msg();
    Syslog5424Visitor visitor = new Syslog5424Visitor();
    SLValue value = visitor.visit(ctx);
    return value.asMap();
  }

}