FROM ubuntu
ENV LANG=C.UTF-8
WORKDIR /home/files/

COPY jars/jdk-17.0.5_linux-x64_bin.tar.gz jdk17.tar.gz
RUN tar -xvzf jdk17.tar.gz
RUN rm jdk17.tar.gz
ENV JAVA_HOME /home/files/jdk-17.0.5
RUN export JAVA_HOME
ENV PATH $PATH:$HOME/bin:$JAVA_HOME/bin
RUN export PATH
COPY target/mock-server-1.0-SNAPSHOT-jar-with-dependencies.jar mock.jar
CMD "java" "-jar" "mock.jar"