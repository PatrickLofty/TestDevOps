FROM tomcat:latest
LABEL authors="patrickloftus"
ADD target/*.war /home/ec2-user/tomcat/webapps/
EXPOSE 8181
CMD ["catalina.sh", "run"]
