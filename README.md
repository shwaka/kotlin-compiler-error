`./gradlew compileKotlin` causes `java.lang.OutOfMemoryError`

## memo
- If `if (true) Foo() else Foo()` is replaced with `Foo()`, then `OutOfMemoryError` does not occur.
- The number of type parameters is important. If it is decreased to 4 (depending on environment?), then `OutOfMemoryError` does not occur.

## environment
```
$ ./gradlew --version

------------------------------------------------------------
Gradle 6.6.1
------------------------------------------------------------

Build time:   2020-08-25 16:29:12 UTC
Revision:     f2d1fb54a951d8b11d25748e4711bec8d128d7e3

Kotlin:       1.3.72
Groovy:       2.5.12
Ant:          Apache Ant(TM) version 1.10.8 compiled on May 10 2020
JVM:          10.0.2 (Azul Systems, Inc. 10.0.2+13)
OS:           Linux 5.8.0-48-generic amd64
```

## output of `gradle`
```
> Task :compileKotlin FAILED
e: org.jetbrains.kotlin.util.KotlinFrontEndException: Exception while analyzing expression at (7,15) in /path/to/kotlin-compiler-error/src/main/kotlin/main.kt
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.logOrThrowException(ExpressionTypingVisitorDispatcher.java:253)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:224)
	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:101)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:164)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:134)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:146)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeInfo(ExpressionTypingServices.java:119)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeInfo(ExpressionTypingServices.java:94)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getType(ExpressionTypingServices.java:136)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.safeGetType(ExpressionTypingServices.java:79)
	at org.jetbrains.kotlin.resolve.VariableTypeAndInitializerResolver.resolveInitializerType(VariableTypeAndInitializerResolver.kt:171)
	at org.jetbrains.kotlin.resolve.VariableTypeAndInitializerResolver.resolveTypeNullable(VariableTypeAndInitializerResolver.kt:97)
	at org.jetbrains.kotlin.resolve.VariableTypeAndInitializerResolver.resolveType(VariableTypeAndInitializerResolver.kt:52)
	at org.jetbrains.kotlin.resolve.LocalVariableResolver.resolveLocalVariableDescriptor(LocalVariableResolver.kt:195)
	at org.jetbrains.kotlin.resolve.LocalVariableResolver.process(LocalVariableResolver.kt:79)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitProperty(ExpressionTypingVisitorForStatements.java:114)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitProperty(ExpressionTypingVisitorForStatements.java:65)
	at org.jetbrains.kotlin.psi.KtProperty.accept(KtProperty.java:58)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:175)
	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:101)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:164)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:147)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getTypeOfLastExpressionInBlock(ExpressionTypingServices.java:395)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedTypeWithWritableScope(ExpressionTypingServices.java:320)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedType(ExpressionTypingServices.java:200)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.getBlockReturnedType(ExpressionTypingServices.java:177)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitBlockExpression(ExpressionTypingVisitorForStatements.java:430)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorForStatements.visitBlockExpression(ExpressionTypingVisitorForStatements.java:65)
	at org.jetbrains.kotlin.psi.KtBlockExpression.accept(KtBlockExpression.java:78)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.lambda$getTypeInfo$0(ExpressionTypingVisitorDispatcher.java:175)
	at org.jetbrains.kotlin.util.PerformanceCounter.time(PerformanceCounter.kt:101)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:164)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingVisitorDispatcher.getTypeInfo(ExpressionTypingVisitorDispatcher.java:147)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.checkFunctionReturnType(ExpressionTypingServices.java:172)
	at org.jetbrains.kotlin.types.expressions.ExpressionTypingServices.checkFunctionReturnType(ExpressionTypingServices.java:155)
	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:985)
	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBody(BodyResolver.java:932)
	at org.jetbrains.kotlin.resolve.BodyResolver.resolveFunctionBodies(BodyResolver.java:918)
	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBehaviorDeclarationBodies(BodyResolver.java:125)
	at org.jetbrains.kotlin.resolve.BodyResolver.resolveBodies(BodyResolver.java:246)
	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.analyzeDeclarations(LazyTopDownAnalyzer.kt:225)
	at org.jetbrains.kotlin.resolve.LazyTopDownAnalyzer.analyzeDeclarations$default(LazyTopDownAnalyzer.kt:60)
	at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration(TopDownAnalyzerFacadeForJVM.kt:123)
	at org.jetbrains.kotlin.cli.jvm.compiler.TopDownAnalyzerFacadeForJVM.analyzeFilesWithJavaIntegration$default(TopDownAnalyzerFacadeForJVM.kt:93)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler$analyze$1.invoke(KotlinToJVMBytecodeCompiler.kt:526)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler$analyze$1.invoke(KotlinToJVMBytecodeCompiler.kt:90)
	at org.jetbrains.kotlin.cli.common.messages.AnalyzerWithCompilerReport.analyzeAndReport(AnalyzerWithCompilerReport.kt:115)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.analyze(KotlinToJVMBytecodeCompiler.kt:517)
	at org.jetbrains.kotlin.cli.jvm.compiler.KotlinToJVMBytecodeCompiler.compileModules$cli(KotlinToJVMBytecodeCompiler.kt:185)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:164)
	at org.jetbrains.kotlin.cli.jvm.K2JVMCompiler.doExecute(K2JVMCompiler.kt:51)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:86)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:44)
	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:98)
	at org.jetbrains.kotlin.incremental.IncrementalJvmCompilerRunner.runCompiler(IncrementalJvmCompilerRunner.kt:346)
	at org.jetbrains.kotlin.incremental.IncrementalJvmCompilerRunner.runCompiler(IncrementalJvmCompilerRunner.kt:102)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.compileIncrementally(IncrementalCompilerRunner.kt:240)
	at org.jetbrains.kotlin.incremental.IncrementalCompilerRunner.compile(IncrementalCompilerRunner.kt:90)
	at org.jetbrains.kotlin.daemon.CompileServiceImplBase.execIncrementalCompiler(CompileServiceImpl.kt:601)
	at org.jetbrains.kotlin.daemon.CompileServiceImplBase.access$execIncrementalCompiler(CompileServiceImpl.kt:93)
	at org.jetbrains.kotlin.daemon.CompileServiceImpl.compile(CompileServiceImpl.kt:1633)
	at jdk.internal.reflect.GeneratedMethodAccessor83.invoke(Unknown Source)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:564)
	at java.rmi/sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:359)
	at java.rmi/sun.rmi.transport.Transport$1.run(Transport.java:200)
	at java.rmi/sun.rmi.transport.Transport$1.run(Transport.java:197)
	at java.base/java.security.AccessController.doPrivileged(Native Method)
	at java.rmi/sun.rmi.transport.Transport.serviceCall(Transport.java:196)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:562)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:796)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:677)
	at java.base/java.security.AccessController.doPrivileged(Native Method)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:676)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1135)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	at java.base/java.lang.Thread.run(Thread.java:844)
Caused by: java.lang.OutOfMemoryError: Java heap space


FAILURE: Build failed with an exception.

* What went wrong:
Execution failed for task ':compileKotlin'.
> Internal compiler error. See log for more details

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output. Run with --scan to get full insights.

* Get more help at https://help.gradle.org

BUILD FAILED in 8s
1 actionable task: 1 executed
```
