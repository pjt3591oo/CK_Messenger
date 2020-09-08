from django.http import HttpResponse, JsonResponse

from rest_framework.response import Response
from rest_framework.parsers import JSONParser
from rest_framework.decorators import api_view
from rest_framework.views import APIView
from rest_framework import status

from django.views.decorators.csrf import csrf_exempt
from django.utils.decorators import method_decorator


from user.models import User

from friends.models import Friends
from friends.serializers import FriendsSerializer

@csrf_exempt
def FriendList(req):
  if req.method == 'GET':
    f = Friends.objects.all()
    print(list(f))
    serializer = FriendsSerializer(f, many=True)
    print(serializer.data)
    
    # result = {
    #   "data": serializer.data if serializer.data else []
    # }
    return JsonResponse(list(serializer.data), safe=False, status=status.HTTP_200_OK)

  elif req.method == 'POST':
    d = JSONParser().parse(req)
    d['stats'] = 1
    serializer = FriendsSerializer(data=d)
    print(d['user_id'])
    print(User.objects.get(id=d['user_id']))
    
    if serializer.is_valid():
      is_exist = Friends.objects.filter(
        user_id=User.objects.get(id=d['user_id']), 
        friend_id=User.objects.get(id=d['friend_id'])
      )
      if not len(is_exist):
        serializer.save()
        return JsonResponse(serializer.data, status=status.HTTP_201_CREATED)
      else:
        return JsonResponse({"msg": "이미 등록된 친구"}, status=status.HTTP_200_OK)
    print(serializer.errors)
    return JsonResponse(serializer.error_messages, status=status.HTTP_500_INTERNAL_SERVER_ERROR)
# @method_decorator(csrf_exempt, name="dispatch")
# class FriendList(APIView):
#   def get(self, req, format=None):
#     return ''
#     # f = Friends.objects.all()
#     # serializer = FriendsSerializer(f, many=True)
#     # result = {
#     #   "data": serializer.data if serializer.data else []
#     # }
#     # return Response(list(serializer.data))

#   def post(self, req):
#     return ''
#     # print(req)
#     # d = JSONParser().parse(req)
#     # serializer = FriendsSerializer(data=d)
#     # print(d)
#     # if serializer.is_valid():
#     #   serializer.save()
#     #   # print(serializer.data)
#     #   return Response(serializer.data, status=status.HTTP_201_CREATED)
#     # print(serializer.errors)