from django.contrib.auth.models import User, Group
from rest_framework import viewsets
from rest_framework import permissions
from user.serializers import UserSerializer, GroupSerializer


class UserViewSet(viewsets.ModelViewSet):
    # queryset = User.objects.all().order_by('-date_joined')
    # serializer_class = UserSerializer
    # permission_classes = [permissions.IsAuthenticated]
    pass


class GroupViewSet(viewsets.ModelViewSet):
    # queryset = Group.objects.all()
    # serializer_class = GroupSerializer
    # permission_classes = [permissions.IsAuthenticated]
    pass