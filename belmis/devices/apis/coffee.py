from rest_framework.views import APIView

from belmis.devices.models import Device
from belmis.users.models import User


class MakeCoffeeAPI(APIView):

    def post(self, request, *args, **kwargs):
        # TODO: Return proper response
        return