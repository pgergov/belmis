from django.db import models

from belmis.users.models import User


class Residence(models.Model):
    name = models.CharField(max_length=255)
    users = models.ManyToManyField(User, related_name='residences', blank=True, null=True)
