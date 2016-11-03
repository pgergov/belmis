from django import forms
from .models import Residence


class ResidenceForm(forms.ModelForm):
    class Meta:
        model = Residence
        fields = ('name', 'users')
