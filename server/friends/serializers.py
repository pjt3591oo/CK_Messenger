from rest_framework import serializers
from .models import Friends
from django.conf import settings

from django.contrib.auth.models import User

from user.serializers import UserSerializer

class FriendsSerializer(serializers.Serializer):
  id = serializers.IntegerField(read_only=True)
  created = serializers.DateTimeField(read_only=True)
  stats = serializers.IntegerField()
  
  user_id = serializers.IntegerField()
  friend_id = serializers.IntegerField()
  
  friend = UserSerializer(many=False, read_only=True)
  user = UserSerializer(many=False, read_only=True)

  # friend_id = serializers.RelatedField(source=settings.AUTH_USER_MODEL, read_only=True)
  # user_id = serializers.RelatedField(source=settings.AUTH_USER_MODEL, read_only=True)

  class Meta:
    model = Friends
    fields = ['id', 'created', 'stats', 'user_id', 'friend_id', 'friend', 'user']

  def create(self, validated_data):
    print(validated_data)
    return Friends.objects.create(
      **validated_data
    )


# Friends.objects.create(
#   userId=User.objects.get(id=1), 
#   friendId=User.objects.get(id=1)
# )   