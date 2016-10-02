from channels import Group


# TODO: Add generic consumer
def ws_add(message):
    "Connected to websocket.connect"
    Group("raspberry").add(message.reply_channel)


def ws_message(message):
    "Connected to websocket.receive"
    Group("raspberry").send({"text": message.content['text']})


def ws_disconnect(message):
    "Connected to websocket.disconnect"
    Group("raspberry").discard(message.reply_channel)
