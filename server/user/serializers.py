from rest_framework import serializers

from .models import User


class UserSerializer(serializers.HyperlinkedModelSerializer):
  class Meta:
    model = User
    fields = ['email', 'birth', 'gender', 'profile_img']


class GroupSerializer(serializers.HyperlinkedModelSerializer):
  class Meta:
    model = User
    fields = ['url', 'name']