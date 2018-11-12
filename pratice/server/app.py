#coding utf-8

from flask import Flask
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Table,Column,Integer,String,MetaData,ForeignKey
from sqlalchemy.orm import sessionmaker, relationship
import os

engine = create_engine('sqlite:///msg_board.db', echo=True)
Base = declarative_base()

class User(Base):
    __tablename__ = 'users'
    id = Column(Integer, primary_key=True)
    username = Column(String(64), nullable=False, index=True)
    password = Column(String(64), nullable=False)
    email = Column(String(64), nullable=False, index=True)

    def __repr__(self):
        return '%s(%r)' % (self.__class__.__name__, self.username)

Base.metadata.create_all(engine)

Session = sessionmaker(bind=engine)
session = Session()
user = User()
user.id = 1
user.username = 'abc'
user.password = 'abc'
user.email = 'abc'

session.add(user)
session.commit()

app = Flask(__name__)



@app.before_first_request
def before_first_request():
    print "before_first_request()"
    pass


@app.route('/')
def hello_world():
    return 'Hello World!'


if __name__ == '__main__':
    app.run()