from django.urls import path
from django.conf.urls import url
from django.conf import settings
from django.conf.urls.static import serve
from my_app import views as my_views

urlpatterns = [
    url(r'^$', my_views.index),
    url(r'^login$', my_views.login),
    url(r'^register$', my_views.register),
    url(r'^movie$', my_views.movie_list),
    url(r'^post/$', my_views.post_list),
    url(r'^post/add$', my_views.post_add),
    url(r'^movie/comment/<int:movie_id>$', my_views.movie_comment_list),
    url(r'^post/comment/<int:movie_id>$', my_views.post_comment_list),
    url(r'^movie/like/$', my_views.movie_like),
    url(r'^movie/unlike/$', my_views.movie_unlike),
    url(r'^movie/comment/like/$', my_views.movie_comment_like),
    url(r'^movie/comment/unlike/$', my_views.movie_comment_unlike),
    url(r'^movie/comment/add/$', my_views.movie_comment_add),
    url(r'^post/comment/add/$', my_views.post_comment_add),
    url(r'^static/(?P<path>.*)$', serve, {'document_root': settings.STATIC_URL}, name='media'),
    url(r'^media/(?P<path>.*)$', serve, {'document_root': settings.MEDIA_ROOT}, name='media'),
]
