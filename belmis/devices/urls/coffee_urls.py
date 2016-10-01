from django.conf.urls import url

from ..apis import coffee

urlpatterns = [
    url(r'^make/(?P<device_token>[-\w]+)/$',
        coffee.MakeCoffeeAPI.as_view(),
        name='make-coffee'),
]
