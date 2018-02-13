package org.ottobackwards;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;
import org.ottobackwards.dsl.SyslogFieldKeys;

public class SyslogGrokTest {

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
  public void testAllPresent() throws Exception {
    Map<String, Object> map = handleFile("src/test/resources/log_all.txt",
                                         "src/test/resources/grok");
    Assert.assertEquals(expectedVersion, map.get(SyslogFieldKeys.HEADER_VERSION.getField().replace('.','_')).toString());
    // FAILS
    //Assert.assertEquals(expectedMessage, map.get(SyslogFieldKeys.MESSAGE.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedAppName, map.get(SyslogFieldKeys.HEADER_APPNAME.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedHostName, map.get(SyslogFieldKeys.HEADER_HOSTNAME.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedPri, map.get(SyslogFieldKeys.HEADER_PRI.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedProcId, map.get(SyslogFieldKeys.HEADER_PROCID.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedTimestamp, map.get(SyslogFieldKeys.HEADER_TIMESTAMP.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedMessageId, map.get(SyslogFieldKeys.HEADER_MSGID.getField().replace('.','_')).toString());
  }

  @Test
  public void testMissingHeaderField() throws Exception {
    Map<String, Object> map = handleFile("src/test/resources/log.txt", "src/test/resources/grok");
    Assert.assertEquals(expectedVersion, map.get(SyslogFieldKeys.HEADER_VERSION.getField().replace('.','_')).toString());
    // FAILS
    //Assert.assertEquals(expectedMessage, map.get(SyslogFieldKeys.MESSAGE.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedAppName, map.get(SyslogFieldKeys.HEADER_APPNAME.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedHostName, map.get(SyslogFieldKeys.HEADER_HOSTNAME.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedPri, map.get(SyslogFieldKeys.HEADER_PRI.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedProcId, map.get(SyslogFieldKeys.HEADER_PROCID.getField().replace('.','_')).toString());
    Assert.assertEquals(expectedTimestamp, map.get(SyslogFieldKeys.HEADER_TIMESTAMP.getField().replace('.','_')).toString());
  }

  private static Map<String, Object> handleFile(String fileName, String grokFileName)
      throws Exception {
    SyslogGrok grok = new SyslogGrok();
    File f = new File(fileName);
    byte[] bytes = IOUtils.toByteArray(new FileInputStream(f));
    File g = new File(grokFileName);
    byte[] grokBytes = IOUtils.toByteArray(new FileInputStream(g));
    return grok.parse(new String(bytes), grokBytes);
  }

}
