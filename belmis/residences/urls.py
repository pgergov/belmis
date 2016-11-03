from django.conf.urls import url

from . import views

urlpatterns = [
    # Residence CRUD
    url(r'^create/$',
        views.ResidenceCreateView.as_view(),
        name='create'),
    url(r'^list/$',
        views.ResidenceListView.as_view(),
        name='list'),
    url(
        regex=r'^update/(?P<residence_id>[0-9]+)/$',
        view=views.ResidenceUpdateView.as_view(),
        name='update'
    ),
    url(
        regex=r'^delete/(?P<residence_id>[0-9]+)/$',
        view=views.ResidenceDeleteView.as_view(),
        name='delete'
    ),
]
