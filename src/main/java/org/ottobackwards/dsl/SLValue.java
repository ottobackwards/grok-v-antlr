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

import java.util.List;

public class SLValue implements Comparable<SLValue>{
  public static final SLValue NULL = new SLValue();
  public static final SLValue VOID = new SLValue();

  private Object value;

  private SLValue() {
    // private constructor: only used for NULL and VOID
    value = new Object();
  }

  public SLValue(Object v) {
    if(v == null) {
      throw new RuntimeException("v == null");
    }
    value = v;
    // only accept boolean, list, number or string types
    if(!(isBoolean() || isList() || isNumber() || isString())) {
      throw new RuntimeException("invalid data type: " + v + " (" + v.getClass() + ")");
    }
  }

  public Boolean asBoolean() {
    return (Boolean)value;
  }

  public Double asDouble() {
    return ((Number)value).doubleValue();
  }

  public Long asLong() {
    return ((Number)value).longValue();
  }

  @SuppressWarnings("unchecked")
  public List<SLValue> asList() {
    return (List<SLValue>)value;
  }

  public String asString() {
    return (String)value;
  }

  //@Override
  public int compareTo(SLValue that) {
    if(this.isNumber() && that.isNumber()) {
      if(this.equals(that)) {
        return 0;
      }
      else {
        return this.asDouble().compareTo(that.asDouble());
      }
    }
    else if(this.isString() && that.isString()) {
      return this.asString().compareTo(that.asString());
    }
    else {
      throw new RuntimeException("illegal expression: can't compare `" + this + "` to `" + that + "`");
    }
  }

  @Override
  public boolean equals(Object o) {
    if(this == VOID || o == VOID) {
      throw new RuntimeException("can't use VOID: " + this + " ==/!= " + o);
    }
    if(this == o) {
      return true;
    }
    if(o == null || this.getClass() != o.getClass()) {
      return false;
    }
    SLValue that = (SLValue)o;
    if(this.isNumber() && that.isNumber()) {
      double diff = Math.abs(this.asDouble() - that.asDouble());
      return diff < 0.00000000001;
    }
    else {
      return this.value.equals(that.value);
    }
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  public boolean isBoolean() {
    return value instanceof Boolean;
  }

  public boolean isNumber() {
    return value instanceof Number;
  }

  public boolean isList() {
    return value instanceof List<?>;
  }

  public boolean isNull() {
    return this == NULL;
  }

  public boolean isVoid() {
    return this == VOID;
  }

  public boolean isString() {
    return value instanceof String;
  }

  @Override
  public String toString() {
    return isNull() ? "NULL" : isVoid() ? "VOID" : String.valueOf(value);
  }
}
