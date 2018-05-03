### grok-v-antlr

I want to see if grok/regex/customparsing are faster than an antlr dsl. For
parsing syslog messages.
So I'm going to try it

------------------

####Note
This test is moot at the moment, for the fact that the GROK rules, that I have found from
Logstash by way of [grokconstructor]()https://grokconstructor.appspot.com/groklib/) are broken.

- they don't handle multiple structured data correctly ( as we have in the test set), so they produce 1 set instead of 2, and the second set bleeds over into the MSG
- they don't break the structured data into workable parts, they are just globs and would require more processing


```bash
with nanoseconds

Result "org.ottobackwards.performance.Syslog5424PerformanceTest.parserLog":
  190261.831 ±(99.9%) 6057.783 ns/op [Average]
  (min, avg, max) = (155779.499, 190261.831, 318131.904), stdev = 25649.031
  CI (99.9%): [184204.049, 196319.614] (assumes normal distribution)


# Run complete. Total time: 00:27:02

Benchmark                                    Mode  Cnt        Score       Error  Units
GrokCachedPerformanceTest.testGrok           avgt  200    37462.124 ±   252.946  ns/op
GrokPerformanceTest.testGrok                 avgt  200  2017339.726 ± 28514.112  ns/op
Syslog5424ListenerPerformanceTest.parserLog  avgt  200   167807.349 ±  5744.643  ns/op
Syslog5424PerformanceTest.parserLog          avgt  200   190261.831 ±  6057.783  ns/op

with milliseconds

Result "org.ottobackwards.performance.Syslog5424PerformanceTest.parserLog":
  0.159 ±(99.9%) 0.001 ms/op [Average]
  (min, avg, max) = (0.154, 0.159, 0.176), stdev = 0.003
  CI (99.9%): [0.159, 0.160] (assumes normal distribution)


# Run complete. Total time: 00:27:04

Benchmark                                    Mode  Cnt  Score    Error  Units
GrokCachedPerformanceTest.testGrok           avgt  200  0.036 ±  0.001  ms/op
GrokPerformanceTest.testGrok                 avgt  200  1.817 ±  0.006  ms/op
Syslog5424ListenerPerformanceTest.parserLog  avgt  200  0.159 ±  0.001  ms/op
Syslog5424PerformanceTest.parserLog          avgt  200  0.159 ±  0.001  ms/op

```

Why is it so different?  Because of all the patterns required to support the groks.  A single grok
patterns is actually a composite or stack of regex patterns.  So parsing the patterns and replacing them into one HUGE pattern for a name
takes time. Executing and creating the captures also takes time, as there is type checking and conversion.


GrokPerformanceTest and Syslog5424Performance tests are 'fair', in that both the grok and the lexer etc are created each run.  In GrokCachedPerformanceTest,
we do not build the grok over and over, although there is still need to execute.

We would however have to build the lexer each new data.  At least as far as I can see.