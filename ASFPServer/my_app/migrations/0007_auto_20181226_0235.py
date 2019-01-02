# Generated by Django 2.1.3 on 2018-12-26 02:35

from django.db import migrations
import django_thumbs.fields
import my_app.models


class Migration(migrations.Migration):

    dependencies = [
        ('my_app', '0006_auto_20181226_0215'),
    ]

    operations = [
        migrations.AlterField(
            model_name='myuser',
            name='head_image',
            field=django_thumbs.fields.ImageThumbsField(default='media/images/user/default-head_img.jpg', sizes=({'code': 'avatar', 'resize': 'crop', 'wxh': '125x125'}, {'code': 'm', 'resize': 'scale', 'wxh': '640x480'}, {'code': '150', 'wxh': '150x150'}), upload_to=my_app.models.generate_user_filename),
        ),
    ]
