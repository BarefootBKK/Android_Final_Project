from django.http import HttpResponse
from django.contrib.auth import authenticate
from django.forms.models import model_to_dict
from . import models
from .models import MyUser, Movie, Post, Trailer, MovieComment, PostComment, UserLike
from .forms import LoginForm, RegisterForm
from . import json_manager as JSONManager


def index(request):
    return HttpResponse("Hello, welcome to visit ASFPServer!")


# 登录
def login(request):
    if request.method == "POST":
        form = LoginForm(request)
        if form.is_valid():
            user = authenticate(username=form.get_username(), password=form.get_password())
            if user is not None:
                current_user = MyUser.objects.get(email=form.get_username())
                user_info = {"email": current_user.email,
                             "nickname": current_user.nickname,
                             "signature": current_user.signature,
                             "head_img_url": current_user.head_image}
                return JSONManager.get_success_json(user_info)
            else:
                return JSONManager.get_error_json("密码错误！")
        else:
            return JSONManager.get_error_json(form.get_error_msg())
    else:
        return HttpResponse("This is GET in login.")


# 注册
def register(request):
    if request.method == "POST":
        form = RegisterForm(request)
        if form.is_valid():
            MyUser.objects.create_user(form.get_username(), form.get_password())
            return JSONManager.get_success_json("注册成功！")
        else:
            return JSONManager.get_error_json("注册失败！")
    else:
        return HttpResponse("This is GET in register.")


# 电影列表
def movie_list(request):
    if request.method == 'GET':
        movies = Movie.objects.all()
        repos = []
        for movie_item in movies:
            data = model_to_dict(movie_item)
            data['movie_stills_url'] = data['movie_stills_url'].split('$')
            data['movie_actor_list'] = JSONManager.get_actor_list(data['movie_actor_list'])
            data['trailer'] = model_to_dict(Trailer.objects.get(trailer_id=movie_item.trailer_id))
            repos.append(data)
        return JSONManager.get_success_json(repos)
    else:
        return JSONManager.get_error_json("movie_list: null")


# 电影-收藏
def movie_like(request):
    if request.method == 'GET':
        update_user_like(request.GET.get('id'), request.user.email, models.TYPE_LIKE_MOVIE, True)
        return JSONManager.get_success_json("Movie liked")
    else:
        return JSONManager.get_error_json("movie_like: null")


# 电影-取消收藏
def movie_unlike(request):
    if request.method == 'GET':
        update_user_like(request.GET.get('id'), request.user.email, models.TYPE_LIKE_MOVIE, False)
        return JSONManager.get_success_json("Movie unlike successfully!")
    else:
        return JSONManager.get_error_json("movie_like: null")


# 电影-评论-所有评论
def movie_comment_list(request):
    if request.method == 'GET':
        comments = MovieComment.objects.filter(movie_id=request.GET.get['movie_id'])
        repos = []
        for comment_item in comments:
            data = model_to_dict(comment_item)
            user = MyUser.objects.get(email=comment_item.user_email)
            data['head_img'] = user.head_image
            data['nickname'] = user.nickname
            repos.append(data)
        return JSONManager.get_success_json(repos)
    else:
        return JSONManager.get_error_json("comment_list: null")


# 电影-评论-喜欢
def movie_comment_like(request):
    if request.method == 'GET':
        try:
            update_user_like(request.GET.get('id'), request.user.email, models.TYPE_LIKE_MOVIE_COMMENT, True)
            comment = MovieComment.objects.get(movie_id=request.GET.get('comment_id'))
            comment.comment_like_num += 1
            comment.save()
        except MovieComment.DoesNotExist:
            return JSONManager.get_error_json("Comment not found!")
    else:
        return JSONManager.get_error_json("comment_like: null")


# 电影-评论-取消喜欢
def movie_comment_unlike(request):
    if request.method == 'GET':
        try:
            update_user_like(request.GET.get('id'), request.user.email, models.TYPE_LIKE_MOVIE_COMMENT, False)
            comment = MovieComment.objects.get(movie_id=request.GET.get('comment_id'))
            comment.comment_like_num -= 1
            comment.save()
            return JSONManager.get_success_json("Comment unlike successfully!")
        except MovieComment.DoesNotExist:
            return JSONManager.get_error_json("Comment not found!")
    else:
        return JSONManager.get_error_json("comment_unlike: null")


def movie_comment_add(request):
    if request.method == 'POST':
        data = request.POST
        MovieComment.objects.create(
            movie_id=data['movie_id'],
            user_email=request.user.email,
            comment_content=data['comment_content'],
            comment_like_num=0,
        )
        return JSONManager.get_success_json("Comment successfully!")
    else:
        return JSONManager.get_error_json("comment_add: null")


# 帖子-所有帖子
def post_list(request):
    if request.method == 'GET':
        posts = Post.objects.all()
        repos = []
        for post_item in posts:
            data = model_to_dict(post_item)
            user_email = post_item.user_email
            data['post_time'] = str(post_item.post_time)
            data['post_url'] = data['post_url']
            data['user_head_img_url'] = MyUser.objects.get(email=user_email).head_image
            data['user_nickname'] = MyUser.objects.get(email=user_email).nickname
            repos.append(data)
        return JSONManager.get_success_json(repos)
    else:
        return JSONManager.get_error_json("post_list: null")


# 帖子-发布新帖
def post_add(request):
    if request.method == 'POST':
        data = request.POST
        post_title = data['post_title']
        post_content = data['post_content']
        user_email = data['user_email']
        Post.objects.create(
            post_title=post_title,
            post_content=post_content,
            user_email=user_email,
        )
        return JSONManager.get_success_json("Post successfully!")
    else:
        return JSONManager.get_error_json("Post_add: null")


# 帖子-评论-所有评论
def post_comment_list(request):
    if request.method == 'GET':
        comment_list = PostComment.objects.filter(user_email=request.GET.get('user_email'))
        repos = []
        for comment_item in comment_list:
            data = model_to_dict(comment_item)
            user_email = comment_item.user_email
            data['head_img_url'] = MyUser.objects.get(email=user_email).head_image
            data['nickname'] = MyUser.objects.get(email=user_email).nickname
            repos.append(data)
        return JSONManager.get_success_json(repos)
    else:
        return JSONManager.get_error_json("post_comment_list: null")


#     帖子-评论-添加评论
def post_comment_add(request):
    if request.method == 'POST':
        data = request.POST
        PostComment.objects.create(
            comment_content=data['comment_content'],
            user_email=request.user.email
        )
        return JSONManager.get_success_json("Add post comment successfully!")
    else:
        return JSONManager.get_error_json("post_comment_add: null")


# 更新用户【喜欢】状态
def update_user_like(object_id, user_email, like_type, is_like):
    is_exist = UserLike.objects.filter(user_email=user_email, like_type=like_type).exists()
    if is_exist:
        like_item = UserLike.objects.filter(user_email=user_email, like_type=like_type)[0]
        like_item.is_liked = is_like
        like_item.save()
    else:
        UserLike.objects.create(
            user_email=user_email,
            like_object_id=int(object_id),
            like_type=like_type,
            is_liked=is_like,
        )

