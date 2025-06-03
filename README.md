# vedruna-backend
TFG de Diana Pascual: MicroServicio Backend de Instadruna

## Índice

1. [Introducción](#1-Introducción)
2. [Funcionalidades del proyecto](#2-Funcionalidades-del-proyecto)
3. [Tecnologías utilizadas](#3-Tecnologías-utilizadas)
4. [Guía de Instalación](#4-Guía-de-instalación)
5. [Conclusión](#5-Conclusión)
6. [Agradecimientos](#6-Agradecimientos)
7. [Licencia](#7-Licencia)
8.  [Contacto](#8-Contacto)
9.  [Referencias](#9-Referencias-y-recursos-empleados)

## 1. Introducción

### 1.1. Descripción del proyecto

**Instadruna** es una red social móvil que permite a estudiantes, profesores y otros miembros de la comunidad Vedruna compartir publicaciones, comentar, seguir a otros usuarios, chatear en tiempo real y gestionar incidencias mediante un sistema de tickets. Inspirada en plataformas como Instagram, pero adaptada al entorno educativo, esta aplicación busca ofrecer una experiencia completa de comunicación, interacción y gestión interna desde una app intuitiva, moderna y accesible para toda la comunidad escolar.

### 1.2. Justificación

En un entorno educativo donde la comunicación entre estudiantes, docentes y otros miembros de la comunidad es clave, contar con una plataforma propia que centralice interacciones y necesidades resulta fundamental. Las redes sociales tradicionales, aunque populares, no están diseñadas para adaptarse a los contextos y dinámicas de una comunidad escolar.

**Instadruna** surge como respuesta a esa necesidad: una red social segura, funcional y adaptada a la realidad educativa del centro, que permite compartir información de forma ordenada, promover la interacción entre usuarios y facilitar la gestión de incidencias técnicas a través de un sistema de tickets.

Además, con su uso, se promueve el aprendizaje digital en todos los niveles: desde el manejo de la app hasta la participación en su mejora, convirtiendo la tecnología en una aliada para la comunicación y la organización dentro del centro educativo.

### 1.3. Objetivos

Objetivo general:

Desarrollar una aplicación móvil que funcione como red social interna del centro educativo Vedruna, permitiendo a los miembros de la comunidad compartir contenidos, comunicarse entre sí y gestionar incidencias de forma ágil, segura y adaptada al contexto escolar.

Objetivos específicos:

- Facilitar la creación y visualización de publicaciones y comentarios entre los usuarios.
- Fomentar la interacción mediante funciones de “me gusta”, seguidores y sistema de historias temporales.
- Proveer un canal de comunicación privada entre usuarios a través de chat en tiempo real.
- Permitir la gestión de incidencias técnicas mediante un sistema de tickets accesible desde la app.
- Garantizar un entorno digital seguro, con autenticación a través de Google y almacenamiento responsable de la información.
- Utilizar herramientas modernas de desarrollo para ofrecer una experiencia intuitiva y fluida.
- Favorecer el uso pedagógico de las tecnologías digitales dentro del contexto educativo.

### 1.4. Motivación

La idea de crear **Instadruna** nace de la observación de nuestros docentes, quienes detectan una necesidad real dentro del centro educativo: disponer de un espacio propio donde alumnos, profesionales y demás miembros de la comunidad puedan expresarse, compartir y comunicarse de forma efectiva.

Las plataformas actualmente empleadas, como Classroom, Alexia o el correo electrónico tradicional, aunque funcionales en determinados aspectos, no siempre cubren las necesidades comunicativas reales del día a día ni se adaptan con flexibilidad a la dinámica de una comunidad educativa moderna. A menudo resultan poco intuitivas, están pensadas más para la gestión académica que para la interacción social, y no ofrecen un entorno cercano, seguro y adaptado al contexto del centro.

Frente a esta realidad, surge la motivación de construir una red social diseñada desde dentro, con las necesidades del propio centro como eje central.

Además, el desarrollo de **Instadruna** no solo cubre una necesidad comunicativa, sino que se convierte en un proyecto formativo y enriquecedor a nivel técnico. Ha sido una oportunidad única para aplicar de forma práctica los conocimientos adquiridos en programación, diseño de interfaces, bases de datos y despliegue, permitiéndome crecer como desarrolladora y enfrentarme a los retos reales del ciclo de vida de una aplicación.

Pero, más allá del aspecto técnico, lo verdaderamente motivador ha sido la posibilidad de aplicar todo ese aprendizaje en algo tangible, útil y con impacto directo en mi entorno. **Instadruna** es, en cierto modo, una forma de devolver al centro parte de lo que me ha brindado durante estos dos años de formación. Un pequeño legado con el que contribuir al fortalecimiento de la comunidad educativa desde la tecnología.

## 2. Funcionalidades del proyecto

**Instadruna** está diseñada para ofrecer una experiencia completa de interacción, comunicación y gestión dentro de un entorno educativo. Inspirada en redes sociales populares pero adaptada a las necesidades de un centro escolar, la aplicación incorpora las siguientes funcionalidades clave:

- **Publicaciones:** Permite crear publicaciones públicas o privadas con texto y, opcionalmente, imagen, para compartir información, ideas o mensajes con la comunidad o con personas concretas.

- **Comentarios y reacciones:** Las publicaciones pueden recibir comentarios (padre e hijo) y reacciones en forma de "me gusta", fomentando el diálogo y la participación.

- **Sistema de historias (Stories):** Posibilita compartir historias efímeras visibles durante un tiempo determinado, siguiendo el estilo de otras redes sociales visuales.

- **Gestión de incidencias:** A través de un sistema de tickets, se pueden comunicar incidencias técnicas o necesidades específicas al equipo de IT del centro, haciendo seguimiento del estado de cada caso.

- **Mensajería instantánea:** Ofrece un sistema de chat para comunicación directa y en tiempo real entre usuarios que se siguen mutuamente.

- **Red de seguidores:** Permite seguir o dejar de seguir a otros miembros de la comunidad y consultar quiénes le siguen y a quién sigue cada usuario.

- **Perfil personal:** Permite visualizar las publicaciones del usuario, los contenidos que le han gustado, sus datos personales, imagen (editable), y consultar estadísticas básicas como número de publicaciones, seguidores y seguidos.

- **Autenticación con Google:** Utiliza un sistema de acceso seguro mediante cuentas de Google, lo que simplifica el inicio de sesión y garantiza un entorno protegido.

- **Notificaciones:** Mantiene a los usuarios actualizados sobre nuevos cambios en la plataforma.

## 3. Tecnologías utilizadas

**Instadruna** se ha desarrollado aplicando una arquitectura modular por capas, que combina un frontend móvil, un backend principal y un microservicio independiente para el chat. Esta separación facilita la escalabilidad, el mantenimiento independiente de cada módulo y una arquitectura más limpia y organizada. En este espacio hablaremos de las tecnologías utilizadas en el backend principal.

### Backend principal (Spring Boot + MySQL)


- **Spring Boot:** Framework base para la creación del backend, incluyendo los módulos:
  - **spring-boot-starter-web**: Para la construcción de APIs REST.
  - **spring-boot-starter-data-jpa**: Para el acceso y persistencia en la base de datos.
  - **spring-boot-starter-security**: Para la gestión de la seguridad y roles.
  - **spring-boot-starter-validation**: Para validaciones automáticas de datos.
- **MySQL:** Sistema de gestión de bases de datos utilizado para almacenar los datos principales (usuarios, publicaciones, comentarios, etc.).
- **SpringDoc OpenAPI:** Para documentar de forma automática la API mediante Swagger.
- **Lombok:** Para reducir el código repetitivo en las entidades y servicios.
- **Jakarta Persistence API:** Para el soporte de JPA actualizado.

### Despliegue y Servicios Adicionales

- **Azure:** El backend principal y el microservicio de chat se desplegarán en Azure, ambos dockerizados para una mayor portabilidad y gestión eficiente.
- **MySQL (Backend Principal):** Base de datos MySQL alojada en un contenedor Docker dentro de Azure.

## 4. Guía de Instalación

## 4.1. Requisitos previos

Antes de comenzar, asegúrate de tener instaladas las siguientes herramientas:

- **Node.js** (versión 16 o superior)
- **npm**
- **Java 17** (o versión compatible con **Spring Boot 3**)
- **Docker** (para contenerización y despliegue en MV Azure)
- **Expo CLI** (**npm install -g expo-cli**)
- **MySQL** (local o servidor flexible en Azure, accesible desde la MV Azure)
- Cuenta activa en **Turso** (para almacenamiento de bases de datos del chat en la nube).
- Cuenta activa en **Azure** (para despliegue en la MV Azure).
- Cuenta activa en **Google** (para autenticación con Google Sign-In).
- Cuenta activa en **Expo** (para notificaciones en tiempo real).  

## 4.2. Instalación del Backend principal (Spring Boot + MySQL)

### 4.2.1. Instalación local

1. Clona el repositorio del backend:

```bash
git clone https://github.com/DiMaPaGa/vedruna-backend.git
cd vedruna-backend
```
2. Instala las dependencias del backend:

```bash
mvn clean install
mvn spring-boot:run
```

3. Accede a http://51.120.11.157:8080 para ver la documentación de la API.


### 4.2.2 Arrancar proyectos backend y microservicios desplegados en Azure

1. Clona el repositorio del microservicio de chat en un nuevo directorio:

```bash
git clone https://github.com/DiMaPaGa/VedruChat.git
cd VedruChat
```

2. Crea un archivo `.env` con las variables de entorno:

```bash
cp .env.example .env
```
3. Crea un archivo `Dockerfile` para el microservicio de chat con la siguiente configuración:

```bash
# Imagen base oficial de Node.js
FROM node:20

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los archivos necesarios
COPY package*.json ./
COPY server/ ./server/
COPY client/ ./client/
COPY .env ./

# Instala las dependencias
RUN npm install

# Expone el puerto (ajusta si usas otro en .env)
EXPOSE 3000

# Comando para arrancar el servidor
CMD ["node", "server/index.js"]
```

4. Crea un directorio `backend` para el backend principal en el mismo nivel en el que esté el microservicio de chat e incorpora el .jar del proyecto principal.
   
5. Crea un archivo `Dockerfile` para el backend con la siguiente configuración: 

```bash
# Usa una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo .jar a la imagen
COPY vedruna-backend-0.0.1-SNAPSHOT.jar /app/vedruna-backend.jar

# Copia el archivo .env a la imagen
COPY .env /app/.env


# Expone el puerto en el que se ejecuta la aplicaciÃ³n
EXPOSE 8080

# Comando para ejecutar la aplicaciÃ³n Spring Boot
ENTRYPOINT ["java", "-jar", "vedruna-backend.jar"]
```

6. Crea un archivo .env con las variables de entorno. 

7. Crea un archivo `docker-compose.yml` con la siguiente configuración:

```bash
version: '3.8'

services:
  backend:
    build:
      context: ./backend
    container_name: vedruna-backend
    env_file: ./backend/.env
    ports:
      - "8080:8080"
    networks:
      - app-network

  chat:
    build:
      context: ./chat
    container_name: chat-app
    env_file: ./chat/.env
    ports:
      - "3000:3000"
    depends_on:
      - backend
    networks:
      - app-network

networks:
  app-network:
    driver: bridge
```

8. Ejecuta el comando `docker-compose up` para arrancar los proyectos en contenedores Docker.

9. Accede a http://51.120.11.157:8080/swagger-ui/index.html para ver la documentación de la API.


## 5. Conclusión

**Instadruna** ha sido concebida como una red social interna para el centro educativo que promueva en esta comunidad la comunicación fluida, la interacción entre usuarios y la gestión de incidencias de forma directa y eficaz. El desarrollo del proyecto ha seguido una arquitectura modular por capas, incorporando un frontend móvil con React Native, un backend principal robusto con Spring Boot y un microservicio de mensajería en tiempo real basado en Node.js y WebSockets.

El uso de tecnologías modernas, patrones de diseño claros y una división lógica de responsabilidades permite no solo una experiencia de usuario intuitiva y responsiva, sino también una plataforma fácilmente escalable, mantenible y abierta a futuras ampliaciones. Entre las funcionalidades destacadas se incluyen la publicación y reacción a contenido, la mensajería instantánea, la autenticación con Google, el envío de notificaciones y la comunicación de incidencias.

Este proyecto comenzó como una simple idea planteada en el segundo trimestre del curso, con el objetivo de dar respuesta a una necesidad real dentro de los entornos formativos. A lo largo del proceso, se ha transformado en algo mucho más grande: una propuesta funcional, con una arquitectura sólida, pensada para crecer.

Más allá de su estado actual, **Instadruna** representa una muestra del poder que tienen las nuevas tecnologías para conectar comunidades, facilitar la colaboración y dar forma a soluciones útiles. Ojalá esta primera piedra sirva para que otros puedan construir sobre ella: adaptándola, ampliándola y mejorándola según las nuevas necesidades que surjan. Porque, al final, esto no solo ha sido un proyecto de fin de grado. Ha sido una oportunidad para aprender haciendo, y despedirme sabiendo que, de alguna forma, también dejo algo aportado.

**Instadruna** es apenas el comienzo de una historia más grande. Que quienes vengan después sigan caminando este sendero, ya sea guiados por la luz de Eärendil… o impulsados por la Fuerza. ***Porque toda aventura merece ser continuada***.


## 6. Agradecimientos

A todos aquellos amigos y familiares que han estado apoyándome desde el primer momento en que inicié esta locura. Gracias por seguir a mi lado a pesar de las quedadas pospuestas, de las ausencias justificadas por el trabajo, y de todo el tiempo que esta etapa ha exigido. Cada palabra de aliento, cada gesto, cada comprensión, ha contado más de lo que imagináis.

A mis profesores, por la confianza que siempre han depositado en mí, incluso aquellos con los que ya no coincidí en este último curso, pero que dejaron huella con su apoyo y enseñanzas.

A mis compañeros, por hacerme sentir especial sin serlo, por esos ánimos constantes que me han acompañado en cada fase del proyecto, y por sostenerme en los momentos donde flaquear parecía más fácil que continuar.

A mi pareja, que ha hecho de la paciencia una virtud. No sabría contar cuántas veces se ha asomado al cuarto donde programaba, solo para comprobar si seguía viva o si necesitaba algo. También ha estado ahí para soportar mis momentos de duda y agotamiento, y eso vale más que cualquier línea de código.

A los nuevos compañeros, colegas y superiores que me empiezan a acompañar en esta nueva etapa profesional y personal. Me habéis demostrado que hay formas sanas y humanas de crecer en equipo, y que confiar en uno mismo es más fácil cuando otros también creen en ti.

Gracias. A todos los que estáis y estaréis. Este producto, este avance, esta evolución... han sido posibles porque me habéis hecho creer e ilusionarme con la idea de que **sí, era posible**.

## 7. Licencia

**Instadruna** está bajo la licencia **MIT**, lo que te permite utilizar, modificar y distribuir este proyecto bajo las siguientes condiciones:

- Permiso para usar, copiar, modificar, fusionar, publicar, distribuir, sublicenciar y vender copias del software.
- El software se proporciona “tal cual”, sin ninguna garantía expresa o implícita.

### Licencias de Dependencias

Este proyecto también incluye dependencias de diversas librerías y tecnologías que tienen sus propias licencias. A continuación, se detalla el tipo de licencia de cada una de las dependencias del frontend:

- **React Native**: [Licencia MIT](https://github.com/facebook/react-native/blob/main/LICENSE)
- **Expo**: [Licencia MIT](https://github.com/expo/expo/blob/main/LICENSE)

## 8. Contacto

Para cualquier consulta, sugerencia o propuesta relacionada con el proyecto **Instadruna**, no dudes en ponerte en contacto a través de los siguientes canales:

- 📧 **Correo electrónico**: dianamariapascual@gmail.com  
- 💼 **LinkedIn**: [Diana Pascual García](https://www.linkedin.com/in/diana-pascual-garc%C3%ADa-47209431) 
- 💻 **GitHub**: [https://github.com/DiMaPaGa](https://github.com/DiMaPaGa)

Estaré encantada de atenderte y colaborar en todo lo posible.  
Gracias de antemano por tu interés.

## 9. Referencias y recursos empleados:

A continuación se presenta una recopilación de las principales fuentes de información y recursos utilizados durante el desarrollo del proyecto.

### 📚 Documentación oficial
  
- [Spring Boot – Proyecto oficial](https://spring.io/projects/spring-boot)  
- [Spring Boot – Repositorio GitHub](https://github.com/spring-projects/spring-boot)  
- [MySQL – Sitio oficial](https://www.mysql.com/about/legal/)  
- [MySQL – Documentación técnica](https://dev.mysql.com/doc/) 
- [Azure – Documentación oficial](https://learn.microsoft.com/en-us/azure/?product=popular)  

### 🧰 Herramientas de desarrollo y diseño
 
- [PlantUML – Diagramas UML](https://www.plantuml.com/)   

### 🎥 Tutoriales y videos de referencia

- [YouTube – Spring Boot con WebSocket](https://www.youtube.com/watch?v=WpbBhTx5R9Q)  

