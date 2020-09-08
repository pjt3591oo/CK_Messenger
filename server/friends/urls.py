from django.urls import path
from . import views

urlpatterns = [
    path('friends/', views.FriendList),
    # path('friends/', views.FriendList.as_view()),
]