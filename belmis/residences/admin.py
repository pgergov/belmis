from django.contrib import admin

from .models import Residence


@admin.register(Residence)
class ResidenceAdmin(admin.ModelAdmin):
    list_display = ('id', 'name')
