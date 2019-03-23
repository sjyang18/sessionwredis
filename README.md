# sessionwredis

Springboot app with HTTPSession with Redis to test HTTPSession behind load balancer.


## 1. how to run in your dev box

### 1.1 Pre-requsites

In my dev box, I created .env file which consumes by docker-compose and inject the environment variable. This format of .env file is:
```
REDIS_HOST=${MY_AZURE_REDIS_INSTANCE_HOST}
REDIS_PORT=6380
REDIS_PASSWORD=${ACCESSKEY_FROM_AZURE_REDIS}
```
If you don't have a AZURE account, set up your own redis instance locally and point to your instance.

### 1.2 Command to start docker-compose up
> docker-compose up --scale webapp=3 -d

* Note: wait for a min or so to get ngnix properly connect. If I start to run my REST api call to early, I got 'Bad Gateway' error, due to the fact that backend springboot apps are not up yet. The 'Bad Gateway' error is the miss-leading message. Going thru the nginx container logs, i found that actually the nginx container tried to connect all three my backend webapps.

```
$ docker logs bbc
2019/03/23 01:05:32 [error] 6#6: *2 connect() failed (111: Connection refused) while connecting to upstream, client: 192.168.96.1, server: , request: "PUT /session/message HTTP/1.1", upstream: "http://192.168.96.4:8080/session/message", host: "localhost"
2019/03/23 01:05:32 [warn] 6#6: *2 upstream server temporarily disabled while connecting to upstream, client: 192.168.96.1, server: , request: "PUT /session/message HTTP/1.1", upstream: "http://192.168.96.4:8080/session/message", host: "localhost"
2019/03/23 01:05:32 [error] 6#6: *2 connect() failed (111: Connection refused) while connecting to upstream, client: 192.168.96.1, server: , request: "PUT /session/message HTTP/1.1", upstream: "http://192.168.96.2:8080/session/message", host: "localhost"
2019/03/23 01:05:32 [warn] 6#6: *2 upstream server temporarily disabled while connecting to upstream, client: 192.168.96.1, server: , request: "PUT /session/message HTTP/1.1", upstream: "http://192.168.96.2:8080/session/message", host: "localhost"
2019/03/23 01:05:32 [error] 6#6: *2 connect() failed (111: Connection refused) while connecting to upstream, client: 192.168.96.1, server: , request: "PUT /session/message HTTP/1.1", upstream: "http://192.168.96.3:8080/session/message", host: "localhost"
2019/03/23 01:05:32 [warn] 6#6: *2 upstream server temporarily disabled while connecting to upstream, client: 192.168.96.1, server: , request: "PUT /session/message HTTP/1.1", upstream: "http://192.168.96.3:8080/session/message", host: "localhost"
2019/03/23 01:05:37 [error] 6#6: *2 no live upstreams while connecting to upstream, client: 192.168.96.1, server: , request: "PUT /session/message HTTP/1.1", upstream: "http://webapp/session/message", host: "localhost"
```
