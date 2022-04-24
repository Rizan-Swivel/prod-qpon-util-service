FROM openjdk:11-jre-slim
COPY /target/qpon-util-service-1.0.1-SNAPSHOT.jar /home/qpon-util-service-1.0.1-SNAPSHOT.jar
ENV GOOGLE_APPLICATION_CREDENTIAL /usr/local/bin/qpon-google-secret.json
ARG GOOGLE_APPLICATION_CREDENTIAL /usr/local/bin/qpon-google-secret.json
ENV AWS_EMAIL_SENDER_ID app@qpon.lk
ENV WEBSMS_API_KEY JxLvHXlP3Sw9w4tig7KRsWJnJSnJtBTH
ENV WEBSMS_API_TOKEN kJC31631611953
ENV WEBSMS_SENDER_ID qpon
WORKDIR /home
EXPOSE 8085
CMD ["java", "-jar", "qpon-util-service-1.0.1-SNAPSHOT.jar"]
