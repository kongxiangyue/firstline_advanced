# -*- coding: utf-8 -*-

from flask import Flask,request
from db_manager import db_manager
import json

app = Flask(__name__)

@app.before_first_request
def before_first_request():
    print("before_first_request()")
    pass


@app.route('/')
def hello_world():
    return('Hello World!')

@app.route('/get_all_msg')
def get_all_msg():
    return db_manager.get_all_msg_json()
    pass

@app.route('/add_msg', methods=['GET', 'POST'])
def add_msg():
    detail = request.args.get('detail')
    title  = request.args.get('title')
    author = request.args.get('author')
    avatar = request.args.get('avatar')
    if 'POST' == request.method:
        detail = request.form['detail']
        title  = request.form['title']
        author = request.form['author']
        avatar = request.form['avatar']
    ret = "failed"
    if True == db_manager.add_msg(title, author, detail, avatar):
        ret = 'success'

    return ret
    pass

@app.route('/login', methods=['GET', 'POST'])
def login():
    res = {}
    res["login"] = False
    res["error_code"] = 1
    res["msg"]   = "username not exit"
    username = request.args.get('username')
    password  = request.args.get('password')
    if 'POST' == request.method:
        username  = request.form('username')
        password  = request.form('password')
    
    info = db_manager.get_user_info(username)

    if info["username"] == None :
        res["error_code"] = 1
        res["msg"]   = "username not exit"
    
    if password != info['password'] :
        res["error_code"] = 2
        res["msg"]   = "wrong password"
    
    if password == info['password'] and username == info["username"] :
        res["login"] = False
        res["error_code"] = 0
        res["msg"]   = "success"

    return json.dumps(res)

    pass


if __name__ == '__main__':
    app.run(host = '0.0.0.0')