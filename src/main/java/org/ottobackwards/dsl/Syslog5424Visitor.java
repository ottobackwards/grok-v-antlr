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

import org.ottobackwards.dsl.generated.Rfc5424BaseVisitor;
import org.ottobackwards.dsl.generated.Rfc5424Parser;

public class Syslog5424Visitor extends Rfc5424BaseVisitor<SLValue> {


  @Override
  public SLValue visitSyslogHeader(Rfc5424Parser.SyslogHeaderContext ctx) {
    super.visitSyslogHeader(ctx);
    //System.out.println(visit(ctx.app_name()));
    //System.out.println(visit(ctx.hostname()));
    //System.out.println(visit(ctx.pri()));
    //System.out.println(visit(ctx.procid()));
    //System.out.println(visit(ctx.timestamp()));

    return SLValue.VOID;
  }

  @Override
  public SLValue visitFullDate(Rfc5424Parser.FullDateContext ctx) {
    SLValue value =  super.visitFullDate(ctx);
 //   System.out.println(value);
    return value;
  }

  @Override
  public SLValue visitFullTime(Rfc5424Parser.FullTimeContext ctx) {
    SLValue value =  super.visitFullTime(ctx);
 //   System.out.println(value);
    return value;
  }

  @Override
  public SLValue visitPartialTime(Rfc5424Parser.PartialTimeContext ctx) {
    SLValue value =  super.visitPartialTime(ctx);
  //  System.out.println(value);
    return value;
  }

  @Override
  public SLValue visitFullYearExpression(Rfc5424Parser.FullYearExpressionContext ctx) {
    super.visitFullYearExpression(ctx);
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitMonthExpression(Rfc5424Parser.MonthExpressionContext ctx) {
    super.visitMonthExpression(ctx);
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitMDayExpression(Rfc5424Parser.MDayExpressionContext ctx) {
    super.visitMDayExpression(ctx);
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHourExpression(Rfc5424Parser.HourExpressionContext ctx) {
    super.visitHourExpression(ctx);
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitMinuteExpression(Rfc5424Parser.MinuteExpressionContext ctx) {
    super.visitMinuteExpression(ctx);
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitSecondExpression(Rfc5424Parser.SecondExpressionContext ctx) {
    super.visitSecondExpression(ctx);
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHeaderHostName(Rfc5424Parser.HeaderHostNameContext ctx) {
    //System.out.println(ctx.getText());
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHeaderProcId(Rfc5424Parser.HeaderProcIdContext ctx) {
    //System.out.println(ctx.getText());
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitHeaderAppName(Rfc5424Parser.HeaderAppNameContext ctx) {
    //System.out.println(ctx.getText());
    return new SLValue(ctx.getText());
  }
  @Override
  public SLValue visitHeaderMsgId(Rfc5424Parser.HeaderMsgIdContext ctx) {
    //System.out.println(ctx.getText());
    return new SLValue(ctx.getText());
  }
  @Override
  public SLValue visitHeaderTimeStamp(Rfc5424Parser.HeaderTimeStampContext ctx) {
    super.visitHeaderTimeStamp(ctx);
    StringBuffer buffer = new StringBuffer();
    buffer.append(ctx.full_date().getText()).append("T").append(ctx.full_time().getText());

 //   System.out.println(value.toString());
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
    ctx.digit().forEach((c)-> buffer.append(c.getText()));
    return new SLValue(buffer.toString());
  }

  @Override
  public SLValue visitZeroDigit(Rfc5424Parser.ZeroDigitContext ctx) {
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitNonZeroDigit(Rfc5424Parser.NonZeroDigitContext ctx) {
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitName(Rfc5424Parser.NameContext ctx) {
   // System.out.println(ctx.getText());
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitStructuredData(Rfc5424Parser.StructuredDataContext ctx) {
    System.out.println("STRUCTURED " + ctx.getText());
    return super.visitStructuredData(ctx);
  }

  @Override
  public SLValue visitSdElement(Rfc5424Parser.SdElementContext ctx) {
     System.out.println("ELEMENT " + ctx.getText());
     return super.visitSdElement(ctx);
  }

  @Override
  public SLValue visitSdId(Rfc5424Parser.SdIdContext ctx) {
    System.out.println("SDID " + ctx.getText());
    return super.visitSdId(ctx);
  }

  @Override
  public SLValue visitSdParam(Rfc5424Parser.SdParamContext ctx) {
    System.out.println("PARAM " + ctx.getText());
    super.visitSdParam(ctx);
    return new SLValue((ctx.getText()));
  }

  @Override
  public SLValue visitParamName(Rfc5424Parser.ParamNameContext ctx) {
    System.out.println("PARAM NAME " + ctx.getText());
    super.visitParamName(ctx);
    return new SLValue(ctx.getText());
  }

  @Override
  public SLValue visitParamValue(Rfc5424Parser.ParamValueContext ctx) {
    System.out.println("PARAM VALUE " + ctx.getText());
    super.visitParamValue(ctx);
    return new SLValue(ctx.getText());
  }


}
