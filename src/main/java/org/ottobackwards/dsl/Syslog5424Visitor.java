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
    visitIfExists(SyslogFieldKeys.HEADER_VERSION.getField(), ctx.version());
    visitIfExists(SyslogFieldKeys.HEADER_APPNAME.getField(), ctx.app_name());
    visitIfExists(SyslogFieldKeys.HEADER_HOSTNAME.getField(), ctx.hostname());
    visitIfExists(SyslogFieldKeys.HEADER_PRI.getField(), ctx.pri());
    visitIfExists(SyslogFieldKeys.HEADER_PROCID.getField(), ctx.procid());
    visitIfExists(SyslogFieldKeys.HEADER_TIMESTAMP.getField(), ctx.timestamp());
    visitIfExists(SyslogFieldKeys.HEADER_MSGID.getField(), ctx.msgid());
    return SLValue.VOID;
  }

  private void visitIfExists(String name, ParserRuleContext ctx) {
    SLValue value = visit(ctx);
    if (value != null && !value.isNull() && !value.isVoid()) {
      msgMap.put(name, value.toString());
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
    return new SLValue(ctx.full_date().getText() + "T" + ctx.full_time().getText());
  }

  @Override
  public SLValue visitHeaderPriority(Rfc5424Parser.HeaderPriorityContext ctx) {
    return super.visit(ctx.prival());
  }

  @Override
  public SLValue visitHeaderPriorityValue(Rfc5424Parser.HeaderPriorityValueContext ctx) {
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHeaderVersion(Rfc5424Parser.HeaderVersionContext ctx) {
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitSdElement(Rfc5424Parser.SdElementContext ctx) {
    String id = ctx.sd_id().getText();
    for (Rfc5424Parser.Sd_paramContext paramContext : ctx.sd_param()) {
      msgMap.put(String.format(SyslogFieldKeys.STRUCTURED_ELEMENT_ID_PNAME_FMT.getField(), (id),
                               ((Rfc5424Parser.SdParamContext) paramContext).param_name()
                                                                            .getText()),
                 ((Rfc5424Parser.SdParamContext) paramContext).param_value().getText());
    }
    return SLValue.VOID;
  }

  @Override
  public SLValue visitMsgAny(Rfc5424Parser.MsgAnyContext ctx) {
    msgMap.put(SyslogFieldKeys.MESSAGE.getField(), ctx.getText().trim());
    return SLValue.VOID;
  }

  @Override
  public SLValue visitMsgUTF8(Rfc5424Parser.MsgUTF8Context ctx) {
    msgMap.put(SyslogFieldKeys.MESSAGE.getField(), ctx.getText().trim());
    return SLValue.VOID;
  }
}
