from channels.routing import route, include

from belmis.devices.consumers import ws_message, ws_add, ws_disconnect


raspberry_routing = [
    route("websocket.connect", ws_add),
    route("websocket.receive", ws_message),
    route("websocket.disconnect", ws_disconnect),
]

routing = [
    include(raspberry_routing, path=r"^/connect/"),
]
