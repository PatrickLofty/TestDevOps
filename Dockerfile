FROM tomcat:10.1.15
#RUN sed -i 's/port="8080"/port="9090"/' /usr/local/tomcat/conf/server.xml

ADD ./target/project.war /usr/local/tomcat/webapps/project.war
EXPOSE 8080
CMD ["catalina.sh", "run"]


#this command creates a sql database using the schema file
#FROM mysql:latest
#ENV MYSQL_DATABASE=<petition> \
#    MYSQL_ROOT_PASSWORD=<pass>
#ADD petition_application_schema.sql /docker-entrypoint-initdb.d
#EXPOSE 3306