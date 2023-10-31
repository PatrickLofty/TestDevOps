FROM tomcat:9.0
LABEL authors="patrickloftus"
COPY ${env.JOB_NAME}.war /home/ec2-user/tomcat/webapps/
