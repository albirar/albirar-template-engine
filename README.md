# albirar-template-engine

A simple template engine to transform templates into HTML or TXT.

| Service             | Status                                                       |
| ------------------- | ------------------------------------------------------------ |
| Travis-CI (master)  | ![Travis-CI](https://api.travis-ci.com/albirar/albirar-template-engine.svg?branch=master) |
| Travis-CI (develop) | ![Travis-CI](https://api.travis-ci.com/albirar/albirar-template-engine.svg?branch=develop) |
| Code coverage       | ![Code Climate coverage](https://img.shields.io/codeclimate/coverage/albirar/albirar-template-engine) |
| Maintainability     | ![Code Climate maintainability](https://img.shields.io/codeclimate/maintainability-percentage/albirar/albirar-template-engine) |
| Maven Central       | ![Maven Central](https://img.shields.io/maven-central/v/cat.albirar.lib/albirar-template-engine) |

# Requirements

This library depends on spring framework MVC and thymeleaf.
Can be used with spring-framework or spring-boot.

# Usage

## Add dependency

### MVN users

Add the dependency to your project:

```xml
<dependency>
   <groupId>cat.albirar.lib</groupId>
   <artifactId>albirar-template-engine</artifactId>
   <version>2.0.0</version>
</dependency>
```

### Gradle users

```gradle
implementation 'cat.albirar.lib:albirar-template-engine:2.0.0'
```
## Configuration

This library is auto-configurable with spring boot.

If not, you should to import the configuration class `cat.albirar.template.engine.configuration.TemplateEngineConfiguration`.

## Template transformation

The templates are files that should to be placed in classpath, as a resource.

The template engine is a spring bean dependency autowired:

```java

// ...

@Autowired
private ITemplateEngine templateEngine;

// ...

```

Use a `cat.albirar.template.engine.models.TemplateDefinitionBean` to describe some invariable template aspects, like name, template resource classpath, content type and charset:

```java
TemplateDefinitionBean tDefinition;

tDefinition = TemplateDefinitionBean.builder()
               .name("Template1")
               .contentType(EContentType.HTML)
               .template("classpath:/cat/albirar/template/engine/templates/simpleTemplate.html")
               .build()
               ;
```

Then, create a `cat.albirar.template.engine.models.TemplateInstanceBean` from definition and set the specific aspects of each rendering:

```java
TemplateInstanceBean tInstance;
Map<String, Object> vars;
ResourceBundleMessageSource messages;

vars = new TreeMap<>();
vars.put("title", "The first template title");
vars.put("users", Arrays.asList(VARS_USERS));

messages = new ResourceBundleMessageSource();
messages.addBasenames("cat/albirar/template/engine/messages/template1");

tInstance = TemplateInstanceBean.buildInstance(tDefinition)
                 .locale(new Locale("ca"))
                 .variables(vars)
                 .messages(messages)
                 .build()
                 ;
```

And the you can render the template:

```java
String rendered;

rendered = templateEngine.renderTemplate(tInstance);

```
