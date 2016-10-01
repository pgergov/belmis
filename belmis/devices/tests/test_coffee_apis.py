from test_plus.test import TestCase

from belmis.users.models import User

from ..models import Device, Raspberry


class TestCoffeeApisTestCase(TestCase):

    def setUp(self):
        self.user = User.objects.create(name='test')
        self.device = Device.objects.create(user=self.user, name='Coffee Machine')
        self.raspberry = Raspberry.objects.create(device=self.device, name='pi')

    def test_we_can_hit_make_coffee_api(self):
        url = self.reverse('api:coffee:make-coffee', device_token=self.device.uuid)
        response = self.post(url)
