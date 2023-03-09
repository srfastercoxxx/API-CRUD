Desarrolle una API REST utilizando Spring Boot y JPA que realice las siguientes funciones

• Cree el mantenimiento (listar, crear, actualizar, eliminar) para la tabla Producto con
sus respectivas validaciones.

• El servicio Rest de listar Productos deberá retornar el resultado de forma paginada


Datos proyecto
<spring-boot-version> 2.7.4
<java.version> 11

--------------------------------------
  
GENERAR DIAGRAMA PARA ENDPOINT
ruta: docs/diagrams
1 - Crear archivo extension .puml para agregar estructura de endpoint y generacion de imagen

2 - Generar imagen de diagramas de archivos con extension .puml(Ejecutar comando en la raiz del proyecto):
mvn clean com.github.funthomas424242:plantuml-maven-plugin:generate

ruta generacion imagen: docs/descriptions
3 - Crear archivo extension .md para documentacion de endpoint, se agrega ruta de imagen aqui
