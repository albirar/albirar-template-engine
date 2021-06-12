## Usage

Once [dependencies has been set](dependency.html "Add to your project") , usage is easy.

### Configuration

First of all, should to configure. This library is auto-configurable with the spring boot annotation [@EnableAutoConfiguration](https://docs.spring.io/spring-boot/docs/2.3.x/api/org/springframework/boot/autoconfigure/EnableAutoConfiguration.html):

```java
@SpringBootApplication
@EnableAutoConfiguration
public class DemoApplication {
    
    
```

Also you can use the annotation [@AutoconfigureTemplateEngine](https://albirar.github.io/albirar-template-engine/apidocs/cat/albirar/template/engine/configuration/AutoconfigureTemplateEngine.html):

```java
@SpringBootApplication
@AutoconfigureTemplateEngine
public class DemoApplication {
    
    
```

If not, you can import the configuration class `cat.albirar.template.engine.configuration.TemplateEngineConfiguration`.

### Template definition

Templates are files that should to be placed in the classpath, as a resource.

A **template definition** is a general applicable template model that should to be *instantiated* in order to use them.

A `cat.albirar.template.engine.models.TemplateDefinitionBean` describe the invariable template aspects, like name, template language, template resource classpath, content type and charset:

```java
TemplateDefinitionBean tDefinition;

tDefinition = TemplateDefinitionBean.builder()
               .name("Template1")
               .templateLanguage("velocity")
               .contentType(EContentType.HTML)
               .template("classpath:/cat/albirar/template/engine/templates/simpleTemplate.html")
               .build()
               ;
```

### Template instantiation

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

// Create the instance from definition
tInstance = TemplateInstanceBean.buildInstance(tDefinition)
                 .locale(new Locale("ca"))
                 .variables(vars)
                 .messages(messages)
                 .build()
                 ;
```
### Template rendering

With the template instance, you can do the rendering simply by calling factory:

```java
String rendered;

rendered = templateFactory.renderTemplate(tInstance);

```

The factory lookup the applicable template engine based on template language indicated in `TemplateDefinitionBean`.

### Engines

Since version `3.0.0`, library is capable of render templates written in different *template languages*, as [Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html), [Velocity](https://velocity.apache.org/engine), [FreeMarker](https://freemarker.apache.org/), [Mustache](https://github.com/spullara/mustache.java), [StringTemplate](https://www.stringtemplate.org/), etc

For more information see [Engines](engines.html).

Each template definition should to inform the property `templateLanguage`, used to resolve the template engine for rendering.

The rendering is made with a unique singleton template engine factory.

Create a dependency of template factory and resolve them through an autowired annotation:

```java
// ...

@Autowired
private ITemplateEngineFactory templateEnginefactory;

// ...

```

The call the method `renderTemplate` with the template instance and use the return value as the render result.

```java
// ...
String r;
try {
    r = templateEngineFactory.renderTemplate(tInstance);    
} catch (IllegalStateException e) {
    LOGGER.error(String.format("No template engine was found for %s", tInstance.getTemplateLanguage()), e);
}
// ...
```



Internally, the factory gets the value of property `templateLanguage` at template instance and search for a template engine suitable for this language.

If a template engine was found, the factory delegates the rendering to the template engine and return the result of rendering.

If no template engine was found, a `IllegalStateException` is thrown.

