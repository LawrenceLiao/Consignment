java -jar /app/app.jar   -Dspring.datasource.url=${SPRING_DATASOURCE_URL}  \
                         -Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME}  \
                         -Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}  \
                         -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000