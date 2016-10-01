from django.conf.urls import url, include


urlpatterns = [
    url(r'^coffee/', include('belmis.devices.urls.coffee_urls', namespace='coffee'))
]
