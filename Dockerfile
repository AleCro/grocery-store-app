FROM ubuntu:latest
LABEL authors="aleco"

ENTRYPOINT ["top", "-b"]