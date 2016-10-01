from django.conf.urls import url

from ..apis import coffee

urlpatterns = [
    url(r'^coffee/(?P<token>[-\w]+)/$',
        coffee.MakeCoffeeAPI.as_view(),
        name='make-coffee'),
]
