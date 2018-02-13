package org.ottobackwards.dsl.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ottobackwards.dsl.SyslogFieldKeys;

public class StructuredDataUtil {

  private static Pattern structuredPattern = Pattern
      .compile(SyslogFieldKeys.STRUCTURED_ELEMENT_ID_PNAME_PATTERN.getField());

  @SuppressWarnings("unchecked")
  public static Map<String, Object> unFlattenStructuredData(Map<String, Object> flattenedMap) {
    boolean hasStructuredData = false;
    final Map<String, Object> returnMap = new HashMap<>();
    for (String key : flattenedMap.keySet()) {
      if (key.startsWith(SyslogFieldKeys.STRUCTURED_BASE.getField())) {
        hasStructuredData = true;
        break;
      }
    }

    if (!hasStructuredData) {
      return returnMap;
    }
    flattenedMap.forEach((k,v)->{
      Matcher matcher = structuredPattern.matcher(k);
      if(matcher.matches()){
        String id = matcher.group(1);
        String pName = matcher.group(2);
        returnMap.putIfAbsent(id,new HashMap<>());
        Map<String,Object> idMap = (Map<String,Object>)returnMap.get(id);
        idMap.put(pName,v);
      }
    });
    return returnMap;
  }
}
