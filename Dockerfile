FROM tomcat:latest
#RUN sed -i 's/port="8080"/port="9090"/' /usr/local/tomcat/conf/server.xml

ADD target/*.war* .
EXPOSE 9090
CMD ["catalina.sh", "run"]