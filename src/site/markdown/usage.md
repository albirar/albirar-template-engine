# Usage

Once [dependencies has been set](dependency.html "Add to your project") , usage is easy.

## Configuration

First of all, should to configure. This library is auto-configurable with spring boot.

If not, you should to import the configuration class `cat.albirar.template.engine.configuration.TemplateEngineConfiguration`.

## Engine

The templates are files that should to be placed in the classpath, as a resource.

The template engine is a Spring Bean dependency, resolved through autowired annotation:

```java
// ...

@Autowired
private ITemplateEngine templateEngine;

// ...

```

By default, a template engine implementation is provided. This engine works with [Thymeleaf language with Spring Beans extension](https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html).

Remember to use the interface, so concrete implementations can be selected through configuration.


## Template definition

A template definition is a general applicable template that should to be *instantiated* in order to use them.

A `cat.albirar.template.engine.models.TemplateDefinitionBean` describe the invariable template aspects, like name, template resource classpath, content type and charset:

```java
TemplateDefinitionBean tDefinition;

tDefinition = TemplateDefinitionBean.builder()
               .name("Template1")
               .contentType(EContentType.HTML)
               .template("classpath:/cat/albirar/template/engine/templates/simpleTemplate.html")
               .build()
               ;
```

## Template instantiation

Once the definition is created, a `cat.albirar.template.engine.models.TemplateInstanceBean` can be created from definition and set the specific aspects of each rendering, like variables and messages.

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
## Template rendering

With the template instance, you can do the rendering:

```java
String rendered;

rendered = templateEngine.renderTemplate(tInstance);

```
