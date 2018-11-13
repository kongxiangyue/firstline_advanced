#coding utf-8

from flask import Flask,request
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Table,Column,Integer,String,MetaData,ForeignKey,Text,DateTime,desc
from sqlalchemy.orm import sessionmaker, relationship
from datetime import datetime
import json


engine = create_engine('sqlite:///msg_board.db', echo=True)
Base = declarative_base()



class Message(Base):
    __tablename__ = 'messages'
    id     = Column(Integer, primary_key=True)
    title  = Column(Text(""), nullable=False)
    detail = Column(Text(""), nullable=False)
    author = Column(String(64), nullable=False)
    time   = Column(DateTime(), nullable=False)
    pass

    @staticmethod
    def get_stuct():
        return ['id', 'title', 'detail', 'author', 'time']

Base.metadata.create_all(engine)

Session = sessionmaker(bind=engine)
session = Session()

'''
msg = Message()
msg.id = 1
msg.time = datetime.now()
msg.author = 'aa'
msg.title = 'title'
msg.detail = 'detail.....'
session.add(msg)
session.commit()
'''

app = Flask(__name__)


@app.before_first_request
def before_first_request():
    print "before_first_request()"
    pass


@app.route('/')
def hello_world():
    return 'Hello World!'

@app.route('/get_all_msg')
def get_all_msg():
    rows = session.query(Message).order_by(desc(Message.id)).all()

    json_obj = []
    for tmp in rows:
        json_obj.append(dict(zip(Message.get_stuct()
                            , [tmp.id, tmp.title, tmp.detail, tmp.author
                                , tmp.time.strftime("%Y-%m-%d %H:%M:%S")])))
        pass




    return json.dumps(json_obj)
    pass

@app.route('/add_msg', methods=['GET', 'POST'])
def add_msg():
    detail = request.args.get('detail')
    title  = request.args.get('title')
    author = request.args.get('author')
    if 'POST' == request.method:
        detail = request.form['detail']
        title  = request.form['title']
        author = request.form['author']


    msg        = Message()
    msg.id     = 1 + session.query(Message).order_by(desc(Message.id)).all()[0].id
    msg.time   = datetime.now()
    msg.author = author
    msg.title  = title
    msg.detail = detail

    session.add(msg)
    session.commit()

    return 'success'
    pass




if __name__ == '__main__':
    app.run()