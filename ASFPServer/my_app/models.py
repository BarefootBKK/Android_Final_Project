from django.db import models
from django_thumbs.fields import ImageThumbsField
from django.contrib.auth.models import (
    BaseUserManager, AbstractBaseUser
)
import os
import datetime
import uuid

user_filename = "default_user"
# 类型
TYPE_LIKE_MOVIE_COMMENT = "like-movie-comment"
TYPE_LIKE_MOVIE = "like-movie"
TYPE_LIKE_POST = "like-post"


# 生成用户头像文件名
def generate_user_filename(instance, filename):
    directory_name = 'images/user'
    new_filename = user_filename
    return os.path.join(directory_name, new_filename)


# 生成通用图像文件名
def generate_normal_filename(instance, filename):
    directory_name = 'images/normal'
    new_filename = datetime.datetime.now().strftime('%Y%m%d') + uuid.uuid4().hex + os.path.splitext(filename)[-1]
    return os.path.join(directory_name, new_filename)


# 获取图像大小
def get_size():
    SIZES = (
        {'code': 'avatar', 'wxh': '125x125', 'resize': 'crop'},
        {'code': 'm', 'wxh': '640x480', 'resize': 'scale'},
        {'code': '150', 'wxh': '150x150'},  # 'resize' defaults to 'scale'
    )
    return SIZES


# 重载BaseUserManager
class MyUserManager(BaseUserManager):
    def create_user(self, email, password):
        user = self.model(
            email=self.normalize_email(email),
            nickname='用户_' + email
        )
        user.set_password(password)
        user.save(self._db)
        return user

    def set_nickname(self, nickname=None):
        user = self.model(
            nickname=nickname,
        )
        user.save(self._db)
        return user

    def set_signature(self, signature=None):
        user = self.model(
            signature=signature,
        )
        user.save(self._db)
        return user


# User model
class MyUser(AbstractBaseUser):
    email = models.EmailField(
        '邮箱地址',
        max_length=255,
        unique=True,
    )
    nickname = models.CharField('昵称', max_length=100, default="")
    signature = models.CharField('个性签名', max_length=300, default="")
    head_image = models.CharField('头像', max_length=300, default="static/images/user/default-head_img.jpg")
    creation_date = models.DateTimeField('注册时间', auto_now_add=True)

    objects = MyUserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []


# 电影
class Movie(models.Model):
    movie_id = models.IntegerField(primary_key=True, auto_created=True, unique=True)
    movie_name = models.CharField(max_length=255, default="")
    movie_release_date = models.CharField(max_length=20, default="")
    movie_release_area = models.CharField(max_length=255, default="")
    movie_length = models.CharField(max_length=255, default="")
    movie_score = models.IntegerField(default=0)
    movie_actor_list = models.CharField(max_length=2000, default="")
    trailer_id = models.IntegerField()
    movie_channel = models.CharField(max_length=255, default="")
    movie_intro = models.CharField(max_length=500, default="")
    movie_rating_num = models.IntegerField(default=0)
    movie_poster_url = models.CharField(max_length=255, default="")
    movie_stills_url = models.CharField(max_length=1000, default="")


# 电影评论
class MovieComment(models.Model):
    comment_id = models.IntegerField(auto_created=True, unique=True, primary_key=True, default=1)
    object_id = models.IntegerField(null=False)
    user_email = models.EmailField(max_length=255, default="")
    create_date = models.DateField(auto_now_add=True)
    comment_content = models.CharField(max_length=300, default="")
    comment_like_num = models.IntegerField(default=0)


# 帖子
class Post(models.Model):
    post_id = models.IntegerField(primary_key=True, auto_created=True, unique=True, null=False)
    post_title = models.CharField(max_length=30, default="")
    post_content = models.CharField(max_length=10000, default="")
    post_time = models.DateField(auto_now_add=True)
    user_email = models.EmailField(max_length=255, default="")
    post_url = models.CharField(max_length=1000, default="static/images/post/00.png")


# 帖子评论
class PostComment(models.Model):
    comment_id = models.IntegerField(auto_created=True, unique=True, primary_key=True, default=1)
    post_id = models.IntegerField(null=False)
    user_email = models.EmailField(max_length=255, default="")
    create_date = models.DateField(auto_now_add=True)
    comment_content = models.CharField(max_length=300, default="")


# 预告片
class Trailer(models.Model):
    trailer_id = models.IntegerField(primary_key=True, auto_created=True, unique=True)
    trailer_name = models.CharField(max_length=255, default="")
    trailer_video_url = models.CharField(max_length=255, default="")
    trailer_cover_url = models.CharField(max_length=255, default="")


# 用户【喜欢】，即点赞
class UserLike(models.Model):
    user_email = models.EmailField(max_length=255, default="")
    like_object_id = models.IntegerField(null=False)
    is_liked = models.BooleanField(default=False, null=False)
    like_type = models.CharField(null=False, default="", max_length=255)
