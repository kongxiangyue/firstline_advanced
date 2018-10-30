from flask import Flask
from flask import request
from xml.etree import ElementTree as ET
from xml.etree.ElementTree import Element, SubElement, ElementTree

app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


@app.route('/get_data.xml', methods=['GET', 'POST'])
def get_data():
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

if __name__ == '__main__':
    app.run(host = '0.0.0.0')
