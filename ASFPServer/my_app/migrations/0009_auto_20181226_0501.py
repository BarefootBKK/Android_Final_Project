# Generated by Django 2.1.3 on 2018-12-26 05:01

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('my_app', '0008_post_post_url'),
    ]

    operations = [
        migrations.AlterField(
            model_name='post',
            name='post_time',
            field=models.DateField(auto_now_add=True),
        ),
    ]
