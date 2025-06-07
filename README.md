`GestorAct`
Contiene la lógica principal del sistema de tareas: estructuras de datos (pila, cola, árbol), controladores, servicios y pruebas.

- **Tecnologías:**
  - Spring Boot
  - Spring Web
  - Spring Data JPA
  - RabbitMQ (a través del módulo `mensajeria-eventos`)
  - Swagger (documentación)
  - MySQL Connector

- **Funcionalidades destacadas:**
  - Gestión de tareas en múltiples estructuras (cola, árbol, lista).
  - Operaciones deshacer mediante pila
  - Controlador REST para exponer servicios.
  - Integración con módulos externos 
