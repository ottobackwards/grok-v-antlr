### grok-v-antlr

I want to see if grok/regex/customparsing are faster than an antlr dsl. For
parsing syslog messages.
So I'm going to try it


> NOTE: Grok does not break out the structured data at all

# Run complete. Total time: 00:13:31

Benchmark                            Mode  Cnt       Score     Error  Units
GrokPerformanceTest.testGrok         avgt  200  115583.960 ± 617.263  ns/op
Syslog5424PerformanceTest.parserLog  avgt  200  174259.686 ± 852.711  ns/op

