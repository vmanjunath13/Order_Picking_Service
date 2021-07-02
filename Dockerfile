FROM openjdk:8-jdk-alpine
ENV SPRING_DATASOURCE_URL = jdbc:mysql://localhost:3306/order_picking_service
ENV SPRING_DATASOURCE_USERNAME = root
ENV SPRING_DATASOURCE_PASSWORD = root
EXPOSE 8080
COPY ./target/Order_Picking_Service-1.0.0.jar order-picking-service.jar
CMD ["java","-jar","order-picking-service.jar"]