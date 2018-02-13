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


# Run complete. Total time: 00:13:31

Benchmark                            Mode  Cnt        Score       Error  Units
GrokPerformanceTest.testGrok         avgt  200  2100101.620 ± 21536.585  ns/op
Syslog5424PerformanceTest.parserLog  avgt  200   172587.608 ±  1672.403  ns/op

Why is it so different?  Because of all the patterns required to support the groks.  A single grok
patterns is actually a composite or stack of regex patterns.  So parsing the patterns and replacing them into one HUGE pattern for a name
takes time. Executing and creating the captures also takes time, as there is type checking and conversion.


This test is 'fair', in that both the grok and the lexer etc are created each run.  In practice,
we would not build the grok over and over, although there would still need to be executions.

We would however have to build the lexer each new data.