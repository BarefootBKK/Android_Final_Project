# Generated by Django 2.1.2 on 2018-12-25 14:06

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('my_app', '0004_auto_20181224_1115'),
    ]

    operations = [
        migrations.AddField(
            model_name='movie',
            name='movie_length',
            field=models.CharField(default='', max_length=255),
        ),
    ]