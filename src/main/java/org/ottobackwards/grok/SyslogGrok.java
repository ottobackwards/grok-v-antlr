package org.ottobackwards.grok;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;
import oi.thekraken.grok.api.Grok;
import oi.thekraken.grok.api.Match;

public class SyslogGrok {

  public Map<String,Object> parse(String data, byte[] groks) throws Exception {

    Grok grok = new Grok();
    grok.addPatternFromReader(new InputStreamReader(new ByteArrayInputStream(groks)));

    String grokPattern = "%{SYSLOG5424LINE}";
    grok.compile(grokPattern);

    Match gm = grok.match(data);
    gm.captures();
    return gm.toMap();
  }

}
