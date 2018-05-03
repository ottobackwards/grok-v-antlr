package org.ottobackwards.performance;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.ottobackwards.dsl.SLValue;
import org.ottobackwards.dsl.Syslog5424Listener;
import org.ottobackwards.dsl.Syslog5424Visitor;
import org.ottobackwards.dsl.generated.Rfc5424Lexer;
import org.ottobackwards.dsl.generated.Rfc5424Parser;

import java.util.concurrent.TimeUnit;

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(1)
public class Syslog5424ListenerPerformanceTest {

  final static String data = "<14>1 2014-06-20T09:14:07+00:00 loggregator d0602076-b14a-4c55-852a-981e7afeed38 DEA MSG-01 [exampleSDID@32473 iut=\"3\" eventSource=\"Application\" eventID=\"1011\"] [exampleSDID@32480 iut=\"4\" eventSource=\"Other Application\" eventID=\"2022\"] Removing instance";
  @Benchmark
  public void parserLog() throws Exception{
    Rfc5424Lexer lexer = new Rfc5424Lexer(new ANTLRInputStream(data));
    Rfc5424Parser parser = new Rfc5424Parser(new CommonTokenStream(lexer));
    Syslog5424Listener listener = new Syslog5424Listener();
    parser.addParseListener(listener);
    Rfc5424Parser.Syslog_msgContext ctx = parser.syslog_msg();
  }
}
