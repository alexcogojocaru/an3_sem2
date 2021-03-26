from flask import Flask
from flask import render_template, url_for, request, redirect
import requests

app = Flask(__name__)

@app.route('/')
def start():
    url = 'http://localhost:8080/persons'
    resp = requests.get(url=url)
    return render_template('index.html', data=resp.text)

@app.route('/get-persons')
def get_persons():
    url = 'http://localhost:8080/persons'
    resp = requests.get(url=url)
    return resp.text

@app.route('/add-person', methods=['POST', 'GET'])
def add_person():
    if request.method == 'POST':
        id = request.form['id']
        firstName = request.form['firstName']
        lastName = request.form['lastName']

        data = {
            "id": id,
            "firstName": firstName,
            "lastName": lastName
        }

        requests.post('http://localhost:8080/person', json=data)

        return redirect(url_for('start'))
    elif request.method == 'GET':
        return render_template('add-person.html')

@app.route('/get-person', methods=['POST', 'GET'])
def get_person():
    if request.method == 'POST':
        id = request.form['id']
        resp = requests.get(f'http://localhost:8080/person/{id}')
        return render_template('print-person.html', data=resp.text)
    else:
        return render_template('get-person.html')

@app.route('/update-person', methods=['POST', 'GET'])
def update_person():
    if request.method == 'POST':
        id = request.form['id']
        firstName = request.form['firstName']
        lastName = request.form['lastName']

        data = {
            "id": id,
            "firstName": firstName,
            "lastName": lastName
        }

        requests.put(f'http://localhost:8080/person/{id}', json=data)

        return redirect(url_for('start'))
    elif request.method == 'GET':
        return render_template('update-person.html')

@app.route('/delete-person', methods=['GET', 'POST'])
def delete_person():
    if request.method == 'POST':
        id = request.form['id']

        requests.delete(f'http://localhost:8080/person/{id}')
        return redirect(url_for('start'))
    else:
        return render_template('delete-person.html')

@app.route('/add-payment', methods=['GET', 'POST'])
def add_payment():
    if request.method == 'POST':
        id = request.form['id']
        sum = request.form['sum']

        requests.post(f'http://localhost:8080/pay/{id}/{sum}')
        return redirect(url_for('start'))
    else:
        return render_template('add-payment.html')

@app.route('/subtract-payment', methods=['GET', 'POST'])
def subtract_payment():
    if request.method == 'POST':
        id = request.form['id']
        sum = request.form['sum']

        requests.delete(f'http://localhost:8080/pay/{id}/{sum}')
        return redirect(url_for('start'))
    else:
        return render_template('subtract-payment.html')

@app.route('/update-payment', methods=['GET', 'POST'])
def update_payment():
    if request.method == 'POST':
        id = request.form['id']
        sum = request.form['sum']

        requests.put(f'http://localhost:8080/pay/{id}/{sum}')
        return redirect(url_for('start'))
    else:
        return render_template('update-payment.html')