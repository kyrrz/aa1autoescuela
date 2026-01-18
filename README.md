# 🚗 Autoescuela API

API REST desarrollada con **Spring Boot** para la gestión de una autoescuela.  
Permite administrar autoescuelas, alumnos, profesores, coches y matrículas mediante operaciones CRUD, filtrados, validaciones y manejo centralizado de errores.

---

## 🛠️ Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Hibernate
- Maven
- MariaDB
- Mockito
- MockMvc
- ModelMapper
- ObjectMapper

## ⚙️ Requisitos previos

- Java JDK **17** o superior
- Maven **3.8+**

### 1 Clonar el repositorio

git clone https://github.com/kyrrz/aa1autoescuela.git
cd aa1autoescuela

### 2 Configuración de base de datos
Por defecto, el proyecto una Base de Datos MariaDB en un contenedor de docker, con persistencia de datos en una carpeta local.

Configurada con el docker-compose.yaml del proyecto, **necesario crear vuestras variables de entorno**.

### 3 Arrancar la API

Para arrancar la API escribir en la terminal : 

  **mvn spring-boot:run"  
  
Para que la API esté operativa, despues desde Hoppscotch o Postman probar todos los endpoints 

Para pasar todos los tests unitarios de la capa Service y Controller escribir en la terminal:

  **mvn test** 

Y se puede ver que no hay ningun problema con los tests.

### 4 Ficheros adicionales

En este repositorio también existe el fichero OpenAPI 3.0 llamado autoescuela.yaml


