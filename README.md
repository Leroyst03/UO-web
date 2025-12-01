# UO-web

## Resumen

Esto es el repositorio de una página web para la facultad de letras de la Universidad de Oriente de Santiago de Cuba. Para el desarrollo de la aplicación se implementó el patrón de diseño MVC, el cual se construyó sobre Java usando el framework de Spring boot para el Back-end y para el Front-end se usaron las tecnologías base (JavaScript, HTML, CSS)

## Estructura del proyecto

```
./app, Dockerfile
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── uo
│   │   │           └── app
│   │   │               ├── AppApplication.java
│   │   │               ├── controllers # Carpeta para controladores
│   │   │               │   ├── Api
│   │   │               │   │   ├── PublicacionController.java
│   │   │               │   │   └── UsuarioController.java
│   │   │               │   ├── Auth 
│   │   │               │   │   ├── LoginController.java
│   │   │               │   │   └── SignUp.java
│   │   │               │   ├── DashboardController.java
│   │   │               │   └── PageController.java
│   │   │               ├── dto # Carpeta para los DTO (Data Transfer Object)
│   │   │               │   ├── PublicacionDTO.java
│   │   │               │   └── UsuarioDTO.java
│   │   │               ├── exceptions # Carpeta de excepciones personalizadas
│   │   │               │   ├── GeneralExceptions
│   │   │               │   │   ├── AccesoDenegadoException.java
│   │   │               │   │   ├── GlobalExceptionHandler.java
│   │   │               │   │   └── HttpException.java
│   │   │               │   ├── PublicationExceptions
│   │   │               │   │   └── PublicacionNoEncontrada.java
│   │   │               │   └── UserExceptions
│   │   │               │       ├── UsuarioEnUsoActualmente.java
│   │   │               │       └── UsuarioNoEncontrado.java
│   │   │               ├── models # Carpeta de modelos
│   │   │               │   ├── Publicacion.java
│   │   │               │   └── Usuario.java
│   │   │               ├── repository # Carpeta para los repositorios
│   │   │               │   ├── PublicacionRepository.java
│   │   │               │   └── UsuarioRepository.java
│   │   │               ├── security # Configuraciones de seguridad
│   │   │               │   ├── JwtFilter.java
│   │   │               │   ├── JwtUtil.java
│   │   │               │   ├── SecurityBeans.java
│   │   │               │   └── SecurityConfig.java
│   │   │               └── services # Carpetapara servicios
│   │   │                   ├── PublicacionService.java
│   │   │                   └── UsuarioService.java
│   │   └── resources
│   │       ├── application.properties #Archivo de configuración
│   │       ├── static # Archivos del Front-end
│   │       │   ├── css
│   │       │   │   ├── dashboard.css
│   │       │   │   ├── estilos.css
│   │       │   │   └── login.css
│   │       │   └── js
│   │       │       └── publicaciones.js
│   │       └── templates
│   │           ├── dashboard.html
│   │           ├── detalle.html
│   │           ├── index.html
│   │           ├── login.html
│   │           └── usuarios-nuevo.html
│   └── test # Directorios para tests
│       └── java
│           └── com
│               └── uo
│                   └── app
│                       └── AppApplicationTests.java
```
## Documentación sobre el funcionamiento de los endpoints proximamente...
[Puedes ver la página desplegada aquí](https://uo-qzoo.onrender.com/)

