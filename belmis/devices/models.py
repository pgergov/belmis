import uuid

from django.db import models

from belmis.users.models import User


class Device(models.Model):
    user = models.ForeignKey(User)
    name = models.CharField(max_length=255)
    uuid = models.UUIDField(default=uuid.uuid4, editable=False, unique=True)


class Raspberry(models.Model):
    device = models.ForeignKey(Device)
    name = models.CharField(max_length=255)
