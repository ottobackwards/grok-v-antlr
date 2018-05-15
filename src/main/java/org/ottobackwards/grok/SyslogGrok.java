package org.ottobackwards.grok;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import oi.thekraken.grok.api.Grok;
import oi.thekraken.grok.api.Match;

public class SyslogGrok {
private static final String SYSLOG_GROK_PATTERN = "%{SYSLOG5424LINE}";
  public Map<String,Object> parse(String data, byte[] groks) throws Exception {

    Grok grok = new Grok();
    grok.addPatternFromReader(new InputStreamReader(new ByteArrayInputStream(groks)));

    grok.compile(SYSLOG_GROK_PATTERN);

    Match gm = grok.match(data);
    gm.captures();
    return gm.toMap();
  }

}
