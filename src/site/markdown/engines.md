## Engines

A engine is an implementation class of the `cat.albirar.template.engine.service.ITemplateEngine` interface. This class is capable of render a specific template language resource, with the properties and message resources provided to the template instance.



Library ships a [Thymeleaf Spring Dialect](https://www.thymeleaf.org/doc/tutorials/3.0/thymeleafspring.html) template engine.

You can add more template engines by adding the corresponding jar to the classpath and register them to factory registry.

Each template engine is associated to

