# Rest api
#### Пользователь
Авторизация  
`POST /api/sign-in`
---

#### Блог
Получить все посты блога  
`GET  /api/blog/{blog-alias}/posts`
* посты которые не доступны пользователю особо помечаются
---
Опубликовать пост  
`POST /api/blog/{blog-alias}/posts` 
---
Получить пост  
`GET  /api/blog/{blog-alias}/posts/{post-id}`
* возвращает пост если доступен пользователю
* в ответе содержится инфа о коментариях лайках
* и md text 
---
Оставить коммент  
`POST /api/blog/{blog-alias}/posts/{post-id}/comment`
---
Поставить лайк  
`POST /api/blog/{blog-alias}/posts/{post-id}/like`
---
Получить все файлы блога  
`GET  /api/blog/{blog-alias}/files/`
---
Сохранить файл  
`GET  /api/blog/{blog-alias}/files/`
---
Получить файл  
`GET  /api/blog/{blog-alias}/files/{file-name}`
---

