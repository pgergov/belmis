from django.views.generic.list import ListView
from django.views.generic.edit import DeleteView, UpdateView, CreateView

from .models import Residence
from .forms import ResidenceForm


class ResidenceCreateView(CreateView):
    template_name = 'residences/residence_form.html'
    form_class = ResidenceForm
    success_url = '/'


class ResidenceListView(ListView):
    model = Residence
    template_name = 'residences/resident_list.html'


class ResidenceUpdateView(UpdateView):
    model = Residence
    fields = ('name', 'users')
    slug_field = 'id'
    slug_url_kwarg = 'residence_id'
    success_url = '/'
    template_name = 'residences/residence_update.html'


class ResidenceDeleteView(DeleteView):
    model = Residence
    slug_field = 'id'
    slug_url_kwarg = 'residence_id'
    success_url = '/'
    template_name = 'residences/residence_delete.html'
