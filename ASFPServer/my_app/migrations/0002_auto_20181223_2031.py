# Generated by Django 2.1.2 on 2018-12-23 20:31

from django.db import migrations, models
import django_thumbs.fields
import my_app.models


class Migration(migrations.Migration):

    dependencies = [
        ('my_app', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='UserLike',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user_email', models.EmailField(default='', max_length=255)),
                ('like_object_id', models.IntegerField()),
                ('is_liked', models.BooleanField(default=False)),
                ('like_type', models.CharField(default='', max_length=255)),
            ],
        ),
        migrations.AddField(
            model_name='movie',
            name='movie_stills_url',
            field=models.CharField(default='', max_length=1000),
        ),
        migrations.AlterField(
            model_name='comment',
            name='create_date',
            field=models.DateField(auto_now_add=True),
        ),
        migrations.AlterField(
            model_name='myuser',
            name='head_image',
            field=django_thumbs.fields.ImageThumbsField(default='images/user/default-head_img.jpg', sizes=({'code': 'avatar', 'resize': 'crop', 'wxh': '125x125'}, {'code': 'm', 'resize': 'scale', 'wxh': '640x480'}, {'code': '150', 'wxh': '150x150'}), upload_to=my_app.models.generate_user_filename),
        ),
    ]
