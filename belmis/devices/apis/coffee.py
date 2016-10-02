from django.http import HttpResponse

from rest_framework.views import APIView
from channels import Group


class MakeCoffeeAPI(APIView):

    def post(self, request, *args, **kwargs):
        # TODO: Send token for verification
        msg = "Make Coffee"
        Group("raspberry").send({"text": msg})

        return HttpResponse()
