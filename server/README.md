# 프로젝트 생성

```sh
$ django-admin startproject [프로젝트 이름]
```

```sh
$ tree .
프로젝트 이름/
    manage.py
    프로젝트 이름/
        __init__.py
        settings.py
        urls.py
        wsgi.py
```

# 서버실행

```sh
$ python manage.py runserver [포트]

$ python manage.py runserver 8080
```

# 앱 추가

``sh
$ python manage.py startapp [앱이름]
```


```
$ docker run -p 3306:3306 --name clone_kakao -e MYSQL_ROOT_PASSWORD=password -d mysql:5.7 mysqld

$ docker exec -it clone_kakao /bin/bash

$ docker start clone_kakao
```

Friends.objects.create({"userId": User.objects.get(id=1), "friendId": User.objects.get(id=1)}) 