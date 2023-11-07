FROM tomcat:latest
RUN sed -i 's/port="8080"/port="9090"/' /home/ec2-user/apache-tomcat-10.1.15/conf/server.xml

ADD target/*.war* .
EXPOSE 9090
CMD ["catalina.sh", "run"]