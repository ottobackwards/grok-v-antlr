package org.ottobackwards.dsl.performance;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import oi.thekraken.grok.api.Grok;
import oi.thekraken.grok.api.Match;
import org.apache.commons.io.IOUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OperationsPerInvocation;
import org.openjdk.jmh.annotations.OutputTimeUnit;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@BenchmarkMode(Mode.AverageTime)
@OperationsPerInvocation(1)
public class GrokPerformanceTest {
  final static String data = "<14>1 2014-06-20T09:14:07+00:00 loggregator d0602076-b14a-4c55-852a-981e7afeed38 DEA MSG-01 [exampleSDID@32473 iut=\"3\" eventSource=\"Application\" eventID=\"1011\"] [exampleSDID@32480 iut=\"4\" eventSource=\"Other Application\" eventID=\"2022\"] Removing instance";
  final static byte[] bytes = getInputStream();
  private static byte[] getInputStream() {
    try {
      File f = new File("src/test/resources/grok");
      return IOUtils.toByteArray(new FileInputStream(f));
    } catch (Exception e) {
      return null;
    }
  }

  @Benchmark
  public void testGrok() throws Exception {
    Grok grok = new Grok();
    grok.addPatternFromReader(new InputStreamReader(new ByteArrayInputStream(bytes)));

    String grokPattern = "%{SYSLOG5424LINE}";
    grok.compile(grokPattern);

    Match gm = grok.match(data);
    gm.captures();
  }
}

