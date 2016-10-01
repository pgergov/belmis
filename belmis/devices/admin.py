from django.contrib import admin

from .models import Device, Raspberry


@admin.register(Device)
class DeviceAdmin(admin.ModelAdmin):
    list_display = ('id', 'name', 'uuid', 'user', 'raspberry')


@admin.register(Raspberry)
class RaspberryAdmin(admin.ModelAdmin):
    list_display = ('id', 'name', 'device')
