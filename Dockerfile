FROM alpine:latest


ENV JAVA_HOME="/usr/lib/jvm/default-jvm/"
RUN apk add openjdk11 wget

# Has to be set explictly to find binaries
ENV PATH=$PATH:${JAVA_HOME}/bin

# https://docs.oracle.com/javase/10/tools/jshell.htm
# https://en.wikipedia.org/wiki/JShell
# CMD ["jshell"]

RUN wget https://github.com/Redislabs-Solution-Architects/riot/releases/download/v1.10.1/riot-1.10.1.zip \
   && unzip riot-1.10.1.zip
ENV RIOT_HOME="/riot-1.10.1/"
ENV PATH=$PATH:${RIOT_HOME}/bin
CMD  ["/bin/sh", "/opt/replicate.sh]
