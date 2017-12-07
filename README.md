jetbrains-sample
================

This project is an example project for debugging IntelliJ classpath issues identified with ElasticSearch's `JarHell`
checks. I am able to successfully build this project from the command line:

```commandline
$ gradle --version

------------------------------------------------------------
Gradle 4.3
------------------------------------------------------------

Build time:   2017-10-30 15:43:29 UTC
Revision:     c684c202534c4138b51033b52d871939b8d38d72

Groovy:       2.4.12
Ant:          Apache Ant(TM) version 1.9.6 compiled on June 29 2015
JVM:          1.8.0_144 (Oracle Corporation 25.144-b01)
OS:           Linux 4.13.12-200.fc26.x86_64 amd64

$ gradle build

... 

> Task :test
==> Test Info: seed=F4E5ABF08A33662A; jvm=1; suite=1
==> Test Summary: 1 suite, 2 tests

> Task :integTestRunner
==> Test Info: seed=759F65207D48FA22; jvm=1; suite=1
==> Test Summary: 1 suite, 1 test


BUILD SUCCESSFUL in 9s
32 actionable tasks: 19 executed, 13 up-to-date
```

However, when I attempt to run either test file in IntelliJ (IU-173.33727.127) using the default JUnit runner the 
the tests will not run due to the `JarHell` checks (note how `idea_rt.jar` is added at the beginning and end of the 
classpath):

```
java.lang.RuntimeException: found jar hell in test classpath

	at org.elasticsearch.bootstrap.BootstrapForTesting.<clinit>(BootstrapForTesting.java:91)
	at org.elasticsearch.test.ESTestCase.<clinit>(ESTestCase.java:178)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at com.carrotsearch.randomizedtesting.RandomizedRunner$2.run(RandomizedRunner.java:592)
Caused by: java.lang.IllegalStateException: jar hell!
duplicate jar [/opt/idea/idea-IU-173.3727.127/lib/idea_rt.jar] on classpath: /opt/idea/idea-IU-173.3727.127/lib/idea_rt.jar:/opt/idea/idea-IU-173.3727.127/plugins/junit/lib/junit-rt.jar:/opt/idea/idea-IU-173.3727.127/plugins/junit/lib/junit5-rt.jar:/usr/java/latest/jre/lib/charsets.jar:/usr/java/latest/jre/lib/deploy.jar:/usr/java/latest/jre/lib/ext/cldrdata.jar:/usr/java/latest/jre/lib/ext/dnsns.jar:/usr/java/latest/jre/lib/ext/jaccess.jar:/usr/java/latest/jre/lib/ext/jfxrt.jar:/usr/java/latest/jre/lib/ext/localedata.jar:/usr/java/latest/jre/lib/ext/nashorn.jar:/usr/java/latest/jre/lib/ext/sunec.jar:/usr/java/latest/jre/lib/ext/sunjce_provider.jar:/usr/java/latest/jre/lib/ext/sunpkcs11.jar:/usr/java/latest/jre/lib/ext/zipfs.jar:/usr/java/latest/jre/lib/javaws.jar:/usr/java/latest/jre/lib/jce.jar:/usr/java/latest/jre/lib/jfr.jar:/usr/java/latest/jre/lib/jfxswt.jar:/usr/java/latest/jre/lib/jsse.jar:/usr/java/latest/jre/lib/management-agent.jar:/usr/java/latest/jre/lib/plugin.jar:/usr/java/latest/jre/lib/resources.jar:/usr/java/latest/jre/lib/rt.jar:/home/abrown/Code/jetbrains-sample/out/test/classes:/home/abrown/Code/jetbrains-sample/out/production/classes:/home/abrown/.gradle/caches/modules-2/files-2.1/org.elasticsearch.test/framework/5.5.2/a3d3b144913b210c9cc75002102d71f44b4a18b5/framework-5.5.2.jar:/home/abrown/Code/jetbrains-sample/build/generated-resources:/home/abrown/.gradle/caches/modules-2/files-2.1/org.elasticsearch/elasticsearch/5.5.2/f719cbe01e1e0ac3b3e99d1a528a90966c0fbc1f/elasticsearch-5.5.2.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.locationtech.spatial4j/spatial4j/0.6/21b15310bddcfd8c72611c180f20cf23279809a3/spatial4j-0.6.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/com.vividsolutions/jts/1.13/3ccfb9b60f04d71add996a666ceb8902904fd805/jts-1.13.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-api/2.8.2/e590eeb783348ce8ddef205b82127f9084d82bf3/log4j-api-2.8.2.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-core/2.8.2/979fc0cf8460302e4ffbfe38c1b66a99450b0bb7/log4j-core-2.8.2.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.elasticsearch/jna/4.4.0/6edc9b4514969d768039acf43f04210b15658cd7/jna-4.4.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-core/6.6.0/918030732dcbddcf893043729ae2486a3a1fe743/lucene-core-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-analyzers-common/6.6.0/d3f77cf23f5887140f4f8870c77bdfffdf98d9/lucene-analyzers-common-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-backward-codecs/6.6.0/5046c3e1d3cf5caec6a5fcf4bd2ffb5d0ed41005/lucene-backward-codecs-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-grouping/6.6.0/32eec94fb2297a23fe23de89fcf532bfdd888a9/lucene-grouping-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-highlighter/6.6.0/34da3b4eaa93a6b45420344723596d0767853c9b/lucene-highlighter-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-join/6.6.0/8500d60797a5fae5e039a31fd830768220c0804/lucene-join-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-memory/6.6.0/a59ce0e83a0c4b7213d929d17b1b72472d538f4d/lucene-memory-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-misc/6.6.0/cef312d4a3c91402057316b98999690bc9047712/lucene-misc-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-queries/6.6.0/216e4980f45b5532d59da6420fc37857314aef25/lucene-queries-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-queryparser/6.6.0/a07fe44186311b4d0de7bab907fe6834e584aaa7/lucene-queryparser-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-sandbox/6.6.0/e8f37447688daf941e82e696bcfe6a5280d311b9/lucene-sandbox-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-spatial/6.6.0/b194415ea03efa41b5fb5302f52dadec78ddfcc5/lucene-spatial-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-spatial-extras/6.6.0/e501452ba4d02913ae9ec20e80e5ea0cbfc7577c/lucene-spatial-extras-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-spatial3d/6.6.0/601cdc95fc1551a72dfe26881ab36e0a493b50ce/lucene-spatial3d-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-suggest/6.6.0/de789eec7b6c1076b32e142e20f40e2294ea5eb2/lucene-suggest-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.elasticsearch/securesm/1.1/1e423447d020041534be94c0f31a49fbdc1f2950/securesm-1.1.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/net.sf.jopt-simple/jopt-simple/5.0.2/98cafc6081d5632b61be2c9e60650b64ddbc637c/jopt-simple-5.0.2.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/com.carrotsearch/hppc/0.7.1/8b5057f74ea378c0150a1860874a3ebdcb713767/hppc-0.7.1.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/joda-time/joda-time/2.9.5/5f01da7306363fad2028b916f3eab926262de928/joda-time-2.9.5.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.yaml/snakeyaml/1.15/3b132bea69e8ee099f416044970997bde80f4ea6/snakeyaml-1.15.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-core/2.8.6/2ef7b1cc34de149600f5e75bc2d5bf40de894e60/jackson-core-2.8.6.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.dataformat/jackson-dataformat-smile/2.8.6/71590ad45cee21249774e2f93e5eca66e446cef3/jackson-dataformat-smile-2.8.6.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.dataformat/jackson-dataformat-yaml/2.8.6/8bd44d50f9a6cdff9c7578ea39d524eb519e35ab/jackson-dataformat-yaml-2.8.6.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.dataformat/jackson-dataformat-cbor/2.8.6/b88721371cfa2d7242bb5e52fe70861aa061c050/jackson-dataformat-cbor-2.8.6.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/com.tdunning/t-digest/3.0/84ccf145ac2215e6bfa63baa3101c0af41017cfc/t-digest-3.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.hdrhistogram/HdrHistogram/2.1.9/e4631ce165eb400edecfa32e03d3f1be53dee754/HdrHistogram-2.1.9.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/com.carrotsearch.randomizedtesting/randomizedtesting-runner/2.5.0/2d00ff1042ae258f33830f26f9b30fc3a43d37e1/randomizedtesting-runner-2.5.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/junit/junit/4.12/2973d150c0dc1fefe998f834810d68f278ea58ec/junit-4.12.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.hamcrest/hamcrest-all/1.3/63a21ebc981131004ad02e0434e799fd7f3a8d5a/hamcrest-all-1.3.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-test-framework/6.6.0/208b81d72f6871bbc549f5a0bd644c8daf797def/lucene-test-framework-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.lucene/lucene-codecs/6.6.0/1f85b2ece48663288f5b26666c9614f7a2733523/lucene-codecs-6.6.0.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.elasticsearch.client/rest/5.5.2/4805969ef1fa18a18439ac55f565962fef385cc/rest-5.5.2.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpclient/4.5.2/733db77aa8d9b2d68015189df76ab06304406e50/httpclient-4.5.2.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpcore/4.4.5/e7501a1b34325abb00d17dde96150604a0658b54/httpcore-4.4.5.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/commons-logging/commons-logging/1.1.3/f6f66e966c70a83ffbdb6f17a0919eaf7c8aca7f/commons-logging-1.1.3.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/commons-codec/commons-codec/1.10/4b95f4897fa13f2cd904aee711aeafc0c5295cd8/commons-codec-1.10.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.elasticsearch/securemock/1.2/98201d4ad5ac93f6b415ae9172d52b5e7cda490e/securemock-1.2.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpasyncclient/4.1.2/95aa3e6fb520191a0970a73cf09f62948ee614be/httpasyncclient-4.1.2.jar:/home/abrown/.gradle/caches/modules-2/files-2.1/org.apache.httpcomponents/httpcore-nio/4.4.5/f4be009e7505f6ceddf21e7960c759f413f15056/httpcore-nio-4.4.5.jar:/opt/idea/idea-IU-173.3727.127/lib/idea_rt.jar
	at org.elasticsearch.bootstrap.JarHell.parseClassPath(JarHell.java:142)
	at org.elasticsearch.bootstrap.JarHell.parseClassPath(JarHell.java:99)
	at org.elasticsearch.bootstrap.JarHell.checkJarHell(JarHell.java:90)
	at org.elasticsearch.bootstrap.BootstrapForTesting.<clinit>(BootstrapForTesting.java:89)
	... 4 more

```

If I change to using the Gradle Test Runner under Settings/Build, Execution, Deployment/Build Tools/Gradle, the tests
will pass; the issue seems to be isolated to the JUnit test runner.
