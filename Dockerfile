FROM tomcat:10.1.15
#RUN sed -i 's/port="8080"/port="9090"/' /usr/local/tomcat/conf/server.xml

ADD ./target/*.war /usr/local/tomcat/webapps/project.war
EXPOSE 9091
CMD ["catalina.sh", "run"]