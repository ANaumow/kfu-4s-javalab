# Rest api
#### Пользователь
Авторизация  
`POST /api/sign-in`
---

#### Блог
Получить все посты блога  
`GET  /api/blog/{blog-name}/posts`
* посты которые не доступны пользователю особо помечаются
---
Опубликовать пост  
`POST /api/blog/{blog-name}/posts` 
---
Получить пост  
`GET  /api/blog/{blog-name}/posts/{post-id}`
* возвращает пост если доступен пользователю
* в ответе содержится инфа о коментариях лайках
* и md text 
---
Оставить коммент  
`POST /api/blog/{blog-name}/posts/{post-id}/comment`
---
Поставить лайк  
`POST /api/blog/{blog-name}/posts/{post-id}/like`
---
Получить все файлы блога  
`GET  /api/blog/{blog-name}/files/`
---
Сохранить файл  
`GET  /api/blog/{blog-name}/files/`
---
Получить файл  
`GET  /api/blog/{blog-name}/files/{file-name}`
---

