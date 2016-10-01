#!/usr/bin/env python
#
# Runs a Tornado web server with a django project
# Make sure to edit the DJANGO_SETTINGS_MODULE to point to your settings.py
#
# http://localhost:8080/hello-tornado
# http://localhost:8080

import sys
import os

from tornado.options import options, define, parse_command_line
from tornado import httpserver
from tornado import ioloop
from tornado import web
from tornado import wsgi
from tornado import websocket

from django.core.wsgi import get_wsgi_application


define('port', type=int, default=8000)


cl = []


class HelloHandler(web.RequestHandler):
    def get(self):
        self.render('belmis/templates/belmis/index.html')


class WebSocketHandler(websocket.WebSocketHandler):
    def open(self):
        print('New connection!')
        cl.append(self)
        self.write_message("Connection successful!")

    def on_close(self):
        cl.remove(self)

    def send_message_to_listeners(self, msg):
        self.write_message(msg)


class ApiHandler(web.RequestHandler):

    @web.asynchronous
    def post(self, *args, **kwargs):
        self.finish()

        for c in cl:
            c.send_message_to_listeners('Make coffee')


def main():
    os.environ['DJANGO_SETTINGS_MODULE'] = 'config.settings.local'
    sys.path.append('./belmis')

    parse_command_line()

    wsgi_app = get_wsgi_application()
    container = wsgi.WSGIContainer(wsgi_app)

    tornado_app = web.Application(
        [
            ('/connect', HelloHandler),
            ('/ws', WebSocketHandler),
            ('/shano', ApiHandler),
            ('.*', web.FallbackHandler, dict(fallback=container)),
        ])

    server = httpserver.HTTPServer(tornado_app)
    server.listen(options.port)

    ioloop.IOLoop.instance().start()


if __name__ == '__main__':
    main()
