FROM maven:3.9.9-amazoncorretto-17

RUN curl -sL https://archive.apache.org/dist/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz -o /opt/apache-maven.tar.gz && \
    tar -xzvf /opt/apache-maven.tar.gz -C /opt && \
    rm /opt/apache-maven.tar.gz

RUN ln -s /opt/apache-maven-3.9.9/bin/mvn /usr/bin/mvn

COPY . /app

WORKDIR /app

CMD ["mvn", "test"]