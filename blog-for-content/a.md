
#Dto
# BlogDto:
-Long id;

-String alias;
-String title;
-String subTitle;

-List<PostDto> posts;

-int subCount;
-int postCount;

# PostDto
-Long id;

-int level;
-String contentUrl;
-LocalDateTime cratedAt;

-List<CommentDto> comments;
-List<UserDto> likes;

-int likeCount;
-int commentCount;

# CommentDto
-Long id;
-UserDto user;
-String text;
-LocalDateTime cratedAt;

# UserDto
-Long       id;
-String     blogAlias;
-String     avatarUrl;
-String     name;
-String     surname;
-String     vocation;

#Entity


