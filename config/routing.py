from channels.routing import route

from belmis.devices.consumers import ws_message


channel_routing = [
    route("websocket.receive", ws_message, path=r"^/shano"),
]
