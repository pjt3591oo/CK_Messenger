from django.db import models
from django.contrib.auth.models import User, Group
from django.contrib.auth.models import AbstractUser
from django.conf import settings

class Friends(models.Model):
  created  = models.DateTimeField(auto_now_add=True)
  stats = models.IntegerField()

  friend = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete = models.CASCADE, default=None, related_name="friend")
  user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete = models.CASCADE, default=None, related_name="user")

  class Meta:
    ordering= ["created"]

# Create your models here.
