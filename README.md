# Challenge Foro Hub Api REST(CRUD)

_Challenge del programa Oracle next education junto con alura latan para aplicar los conocimientos adquiridos en las aulas 
sobre Spring boot, API's Rest y CRUD; Creando una API simulando solicitudes de un foro._

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Instalación](#instalación)
- [Uso](#uso)
- [Contacto](#contacto)


## Características

- Respuestas para las solicitudes Http(CRUD) , para las entidades:
  usuario,curso,topico y respuestas.
- Modulo login para autorización de solicitudes.
- Creación y uso de JWT.
- Documentacion con Spring doc(Swagger).



## Tecnologías Utilizadas

- [Java](https://www.oracle.com/java/)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Hibernate](https://hibernate.org/)
- [MySQL](https://www.mysql.com)
- [Maven](https://maven.apache.org/)
- [Insomnia](https://insomnia.rest/)
- [Spring initializr](https://start.spring.io/)

  
## Instalación

Para instalar y ejecutar el proyecto localmente, sigue estos pasos:

1. Clona el repositorio:

   ```bash
   gh repo clone zVersatility/ApiForoHub
  
2. Navega al directorio del proyecto:

   ```bash
   cd ApiForoHub

3. Asegúrate de tener Mysql y Java IDEA | JDK instalados.
4. Configura la base de datos en el archivo application.properties.
5. Diagrama BD
   

   
   ![diagrama_base_de_datos_forohub](https://github.com/user-attachments/assets/9f673d9f-11e0-46a3-bf2f-0a291b812e00)

   
   
7. Ejecuta la aplicación.


 ## Uso

  Las pruebas a las solicitudes Http a las entidades se pueden hacer con una app con Postmnan o Insomnia o usar
  Swagger usando la URL ya liberada para su documentación : /swagger-ui/index.html#/

  
  
  ![image](https://github.com/user-attachments/assets/6f228bd8-9155-4503-92c2-6bba297da577)

  


  * **Autenticación** - Login , se necesita almenos un registro en la base de datos en la entidad usuario.
                        usando [Bcrypt](https://www.browserling.com/tools/bcrypt) para encriptar la contraseña y registrarla en la bd.
                        Luego usar Swagger para autenticar.






     ![image](https://github.com/user-attachments/assets/2b4e622a-2a9e-433f-a075-8cd67eb445c7)






    

  * **Uso ** - Con el token generado se puede autenticar en las demas Request y usarlas.
    



    ![image](https://github.com/user-attachments/assets/cce054c1-818f-48dd-b0b3-63c43ebf75c0)



    ![image](https://github.com/user-attachments/assets/4e05ca81-6485-4a5c-b9c2-f635dc1bba07)


    



                       
    


## Contacto
- ` Linkedin`: https://www.linkedin.com/in/pedro-huaman-mauricio/
  
## Expresiones de Gratitud 🎁

* Desafio realizado gracias a  [Alura Latam](https://www.aluracursos.com/) y su grupo de instructores.
* Instructora: [Génesys Rondón](https://github.com/genesysrm)


