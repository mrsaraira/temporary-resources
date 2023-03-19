# Temporary resources
![Java CI with Maven](https://github.com/mrsaraira/temporary-resources/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)
## Main purpose
This library is created for auto-managing temporary resources cleaning with pre-defined scope by delegating invocation
of the cleaning logic to some Temporary resources cleaner. 

## Features
A brief review of main features provided by the temporary resources library:
+ Temporary resources registration and abstractions infrastructure.
+ Temporary files cleaning service to register files to be auto-deleted.
+ Providing many scopes to register Temporary resources.
+ It is auto-configurable Spring Boot library – you don’t have to do anything more than including it as a dependency to
your application to make it work.
+ You can build on this library by creating your own Temporary resource implementation in your project.


### Temporary resource lifetime scopes:

* Application - Application scope.
* Session - HTTP session.
* Request - HTTP request / thread-bound.


### Temporary files cleaner service:
Service to automatically register files with pre-defined lifetime scope to be cleaned.

Temporary files - are files that eventually will be deleted. These files don't have to be placed in temp/ directory.
This service registers temporary files and directories by default in the Application scope, or by predefining temporary
file lifetime scope after which it will be removed.

**Notes:**
* Once the temporary file is registered for deletion it cannot be undone.<br>
* This service works for both files and directories.

---

### Getting Started:

1. Add temporary resources library dependency to your project. Latest version is **1.1.0**.

Maven:
```xml
<dependency>
    <groupId>io.github.mrsaraira</groupId>
    <artifactId>temporary-resources</artifactId>
    <version>1.1.0</version>
</dependency>
```

Gradle:
```
implementation 'io.github.mrsaraira:temporary-resources:1.1.0'
```

2. By default, the library is **enabled**, but it checks for Sping Web dependency to ensure that the context has additional
registered scopes: (session, request) for the library to work properly.
To set library auto-configuration enabled set property `temporary-resources.enabled` in your application
settings. Example:

```yaml
temporary-resources:
  enabled: true
  temporary-files-cleaner-service-enabled: true
```

### Usage:
You need to inject or use static reference to TemporaryFilesCleanerService and register your temporary file to some scope.
Example:
```java
void myMethod() {
    temporaryFilesCleanerService.register(new File("path_to_my_temp_file"), TemporaryResourceLifetimeScope.SESSION);
}
```
