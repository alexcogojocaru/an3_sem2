import pika

config = {
        'host': '0.0.0.0',
        'port': 5678,
        'username': 'student',
        'password': 'student',
        'exchange': 'libraryapp.direct',
        'routing_key': 'libraryapp.routingkey1',
        'queue': 'libraryapp.queue'
    }

credentials = pika.PlainCredentials(config['username'], config['password'])
# parameters = (pika.ConnectionParameters(host=config['host']),
#               pika.ConnectionParameters(port=config['port']),
#               pika.ConnectionParameters(credentials=credentials))

parameters = pika.ConnectionParameters(config['host'], config['port'], '/', credentials=credentials)

def on_open_callback():
    print('Connection opened')

def on_close_callback(connection, exception):
    print('Connection closed')

connection = pika.adapters.select_connection.SelectConnection(parameters=parameters, on_open_callback=on_open_callback, on_close_callback=on_close_callback)
connection.ioloop.start()