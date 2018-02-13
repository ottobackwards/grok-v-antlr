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

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.ParserRuleContext;
import org.ottobackwards.dsl.generated.Rfc5424BaseVisitor;
import org.ottobackwards.dsl.generated.Rfc5424Parser;

public class Syslog5424Visitor extends Rfc5424BaseVisitor<SLValue> {

  private Map<String, Object> msgMap = new HashMap<>();

  @Override
  public SLValue visitSyslogMsg(Rfc5424Parser.SyslogMsgContext ctx) {
    super.visitSyslogMsg(ctx);
    return new SLValue(msgMap);
  }

  @Override
  public SLValue visitSyslogHeader(Rfc5424Parser.SyslogHeaderContext ctx) {
    super.visitSyslogHeader(ctx);
    visitIfExists("syslog.header.appName", ctx.app_name());
    visitIfExists("syslog.header.hostName", ctx.hostname());
    visitIfExists("syslog.header.pri", ctx.pri());
    visitIfExists("syslog.header.procId", ctx.procid());
    visitIfExists("syslog.header.timestamp", ctx.timestamp());
    visitIfExists("syslog.header.msgId", ctx.msgid());
    return SLValue.VOID;
  }

  private void visitIfExists(String name, ParserRuleContext ctx) {
    SLValue value = visit(ctx);
    if(value != null && !value.isNull() && !value.isVoid()) {
      msgMap.put(name,value.toString());
    }
  }

  @Override
  public SLValue visitHeaderHostName(Rfc5424Parser.HeaderHostNameContext ctx) {
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHeaderProcId(Rfc5424Parser.HeaderProcIdContext ctx) {
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHeaderAppName(Rfc5424Parser.HeaderAppNameContext ctx) {
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHeaderMsgId(Rfc5424Parser.HeaderMsgIdContext ctx) {
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHeaderTimeStamp(Rfc5424Parser.HeaderTimeStampContext ctx) {
    super.visitHeaderTimeStamp(ctx);
    StringBuffer buffer = new StringBuffer();
    buffer.append(ctx.full_date().getText()).append("T").append(ctx.full_time().getText());
    return new SLValue(buffer.toString());
  }

  @Override
  public SLValue visitHeaderPriority(Rfc5424Parser.HeaderPriorityContext ctx) {
    SLValue v = super.visit(ctx.prival());
    return v;
  }

  @Override
  public SLValue visitHeaderPriorityValue(Rfc5424Parser.HeaderPriorityValueContext ctx) {
    super.visitHeaderPriorityValue(ctx);
    final StringBuffer buffer = new StringBuffer();
    ctx.digit().forEach((c) -> buffer.append(c.getText()));
    return new SLValue(buffer.toString());
  }

  @Override
  public SLValue visitSdElement(Rfc5424Parser.SdElementContext ctx) {
    String id = ctx.sd_id().getText();
    for (Rfc5424Parser.Sd_paramContext paramContext : ctx.sd_param()) {
      msgMap.put(String.format("syslog.stucturedData.%s.%s",
                               (ctx.sd_id().getText()),
                               ((Rfc5424Parser.SdParamContext)paramContext).param_name().getText()),
                               ((Rfc5424Parser.SdParamContext)paramContext).param_value().getText());
    }
    return SLValue.VOID;
  }

  @Override
  public SLValue visitMsgAny(Rfc5424Parser.MsgAnyContext ctx) {
    super.visitMsgAny(ctx);
    msgMap.put("syslog.msg",ctx.getText());
    return SLValue.VOID;
  }

  @Override
  public SLValue visitMsgUTF8(Rfc5424Parser.MsgUTF8Context ctx) {
    super.visitMsgUTF8(ctx);
    msgMap.put("syslog.msg",ctx.getText());
    return SLValue.VOID;
  }
}
