from flask import Flask
from flask import request
from xml.etree import ElementTree as ET
from xml.etree.ElementTree import Element, SubElement, ElementTree
import json
import datetime

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/get_data.xml', methods=['GET', 'POST'])
def get_data_xml():
    apps = Element('apps')

    app     = SubElement(apps, 'app')
    id      = SubElement(app, 'id')
    name    = SubElement(app, 'name')
    version = SubElement(app, 'version')
    id.text   = '1'
    name.text = 'Google Maps'
    version.text = '1.0'

    app = SubElement(apps, 'app')
    id = SubElement(app, 'id')
    name = SubElement(app, 'name')
    version = SubElement(app, 'version')
    id.text = '2'
    name.text = 'Chrome'
    version.text = '2.1'

    app = SubElement(apps, 'app')
    id = SubElement(app, 'id')
    name = SubElement(app, 'name')
    version = SubElement(app, 'version')
    id.text = '3'
    name.text = 'Google Play'
    version.text = '2.3'

    return ET.tostring(apps)


@app.route('/get_data.json', methods=['GET', 'POST'])
def get_data_json():
    apps = []
    app_0  = { 'id' : '5'
        , 'version' : '5.5'
        , 'name'    : 'Clash of Clans' }

    app_1 = { 'id' : '6'
        , 'version' : '7.0'
        , 'name'    : 'Boom Beach' }

    app_2 = {'id': '7'
        , 'version': '3.5'
        , 'name': 'Clash Royale'}


    apps.append(app_0)
    apps.append(app_1)
    apps.append(app_2)


    return json.dumps(apps)
    pass


@app.route('/login', methods=['GET', 'POST'])
def login():

    username = request.args.get('username')
    password = request.args.get('password')
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']

    msg     = 'Login faileded'
    logined = False
    while True:
        if None == username \
                or None == password:
            break

        if 'admin' != username \
                or '123456' != password:
            break
        msg     = 'Login succeeded!'
        logined = True
        break

    ret = {'logined'  : logined
        , 'loginName' : username
        , 'loginTime' : datetime.datetime.now().strftime('%Y-%m-%d')
        , 'msg'       : msg}

    return json.dumps(ret)


@app.route('/test_for_post', methods=['GET', 'POST'])
def test_for_post(): 
    name = request.args.get('name')
    url = request.args.get('url')
    if request.method == 'POST':
        name = request.form['name']
        url = request.form['url']

    return_str = "你的输入:" + name + "==" + url;
    
    return return_str


if __name__ == '__main__':
    app.run(host = '0.0.0.0')