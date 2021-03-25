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
    # return render_template('add-person.html')
    if request.method == 'POST':
        id = request.form['id']
        firstName = request.form['firstName']
        lastName = request.form['lastName']

        data = {
            "id": id,
            "firstName": firstName,
            "lastName": lastName
        }

        print(id)
        print(firstName)
        print(lastName)

        requests.post('http://localhost:8080/person', json=data)

        return redirect(url_for('start'))
    elif request.method == 'GET':
        return render_template('add-person.html')