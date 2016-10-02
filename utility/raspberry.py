import websocket
import thread
import time
import RPi.GPIO as GPIO

GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(18, GPIO.OUT)
GPIO.output(18, False)

def on_message(ws, message):
        current_state = GPIO.input(18)
        if message == 'Make Coffee':
                if current_state != True:
                        print 'Making Coffee'
                        GPIO.output(18, True)
                        time.sleep(1)
                        GPIO.output(18, False)

def on_error(ws, error):
        print error

def on_close(ws):
        print "### closed ###"

if __name__ == "__main__":
    websocket.enableTrace(True)
    ws = websocket.WebSocketApp("ws:http://192.168.1.100:8000/connect/",
                                on_message = on_message,
                                on_error = on_error,
                                on_close = on_close)

    ws.run_forever()
