// Generated from org/ottobackwards/dsl/generated/Rfc5424.g4 by ANTLR 4.5
package org.ottobackwards.dsl.generated;

//CHECKSTYLE:OFF
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Rfc5424Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Rfc5424Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#syslog_msg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSyslog_msg(Rfc5424Parser.Syslog_msgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code syslogHeader}
	 * labeled alternative in {@link Rfc5424Parser#header}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSyslogHeader(Rfc5424Parser.SyslogHeaderContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerPriority}
	 * labeled alternative in {@link Rfc5424Parser#pri}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderPriority(Rfc5424Parser.HeaderPriorityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerPriorityValue}
	 * labeled alternative in {@link Rfc5424Parser#prival}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderPriorityValue(Rfc5424Parser.HeaderPriorityValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerVersion}
	 * labeled alternative in {@link Rfc5424Parser#version}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderVersion(Rfc5424Parser.HeaderVersionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerNilHostName}
	 * labeled alternative in {@link Rfc5424Parser#hostname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderNilHostName(Rfc5424Parser.HeaderNilHostNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerHostName}
	 * labeled alternative in {@link Rfc5424Parser#hostname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderHostName(Rfc5424Parser.HeaderHostNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerNilAppName}
	 * labeled alternative in {@link Rfc5424Parser#app_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderNilAppName(Rfc5424Parser.HeaderNilAppNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerAppName}
	 * labeled alternative in {@link Rfc5424Parser#app_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderAppName(Rfc5424Parser.HeaderAppNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerNilProcId}
	 * labeled alternative in {@link Rfc5424Parser#procid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderNilProcId(Rfc5424Parser.HeaderNilProcIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerProcId}
	 * labeled alternative in {@link Rfc5424Parser#procid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderProcId(Rfc5424Parser.HeaderProcIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerNilMsgId}
	 * labeled alternative in {@link Rfc5424Parser#msgid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderNilMsgId(Rfc5424Parser.HeaderNilMsgIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerMsgId}
	 * labeled alternative in {@link Rfc5424Parser#msgid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderMsgId(Rfc5424Parser.HeaderMsgIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerNilTimestamp}
	 * labeled alternative in {@link Rfc5424Parser#timestamp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderNilTimestamp(Rfc5424Parser.HeaderNilTimestampContext ctx);
	/**
	 * Visit a parse tree produced by the {@code headerTimeStamp}
	 * labeled alternative in {@link Rfc5424Parser#timestamp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHeaderTimeStamp(Rfc5424Parser.HeaderTimeStampContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fullDate}
	 * labeled alternative in {@link Rfc5424Parser#full_date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullDate(Rfc5424Parser.FullDateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fullYearExpression}
	 * labeled alternative in {@link Rfc5424Parser#date_fullyear}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullYearExpression(Rfc5424Parser.FullYearExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code monthExpression}
	 * labeled alternative in {@link Rfc5424Parser#date_month}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonthExpression(Rfc5424Parser.MonthExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mDayExpression}
	 * labeled alternative in {@link Rfc5424Parser#date_mday}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMDayExpression(Rfc5424Parser.MDayExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code fullTime}
	 * labeled alternative in {@link Rfc5424Parser#full_time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFullTime(Rfc5424Parser.FullTimeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code partialTime}
	 * labeled alternative in {@link Rfc5424Parser#partial_time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialTime(Rfc5424Parser.PartialTimeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hourExpression}
	 * labeled alternative in {@link Rfc5424Parser#time_hour}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHourExpression(Rfc5424Parser.HourExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minuteExpression}
	 * labeled alternative in {@link Rfc5424Parser#time_minute}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinuteExpression(Rfc5424Parser.MinuteExpressionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code secondExpression}
	 * labeled alternative in {@link Rfc5424Parser#time_second}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSecondExpression(Rfc5424Parser.SecondExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#time_secfrac}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_secfrac(Rfc5424Parser.Time_secfracContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#time_offset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_offset(Rfc5424Parser.Time_offsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#time_numoffset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_numoffset(Rfc5424Parser.Time_numoffsetContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#structured_data}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructured_data(Rfc5424Parser.Structured_dataContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sdElement}
	 * labeled alternative in {@link Rfc5424Parser#sd_element}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSdElement(Rfc5424Parser.SdElementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sdParam}
	 * labeled alternative in {@link Rfc5424Parser#sd_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSdParam(Rfc5424Parser.SdParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#sd_id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSd_id(Rfc5424Parser.Sd_idContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#param_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_name(Rfc5424Parser.Param_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#param_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam_value(Rfc5424Parser.Param_valueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code name}
	 * labeled alternative in {@link Rfc5424Parser#sd_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(Rfc5424Parser.NameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code msgAny}
	 * labeled alternative in {@link Rfc5424Parser#msg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMsgAny(Rfc5424Parser.MsgAnyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code msgUTF8}
	 * labeled alternative in {@link Rfc5424Parser#msg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMsgUTF8(Rfc5424Parser.MsgUTF8Context ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#msg_any}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMsg_any(Rfc5424Parser.Msg_anyContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#msg_utf8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMsg_utf8(Rfc5424Parser.Msg_utf8Context ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#bom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBom(Rfc5424Parser.BomContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#utf_8_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUtf_8_string(Rfc5424Parser.Utf_8_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#octet}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctet(Rfc5424Parser.OctetContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#sp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSp(Rfc5424Parser.SpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#printusascii}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintusascii(Rfc5424Parser.PrintusasciiContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#printusasciinospecials}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintusasciinospecials(Rfc5424Parser.PrintusasciinospecialsContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#nonzero_digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonzero_digit(Rfc5424Parser.Nonzero_digitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code zeroDigit}
	 * labeled alternative in {@link Rfc5424Parser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitZeroDigit(Rfc5424Parser.ZeroDigitContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nonZeroDigit}
	 * labeled alternative in {@link Rfc5424Parser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonZeroDigit(Rfc5424Parser.NonZeroDigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link Rfc5424Parser#nilvalue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNilvalue(Rfc5424Parser.NilvalueContext ctx);
}