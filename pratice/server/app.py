from flask import Flask
from sqlalchemy import create_engine
from sqlalchemy import Table,Column,Integer,String,MetaData,ForeignKey
import os

engine = create_engine('sqlite:///msg_board.db', echo=True)
metadata = MetaData(engine)


user_table = Table('user', metadata,
        Column('id', Integer, primary_key=True),
        Column('name', String(50)),
        Column('fullname', String(100))
        )

address_table = Table('address', metadata,
        Column('id', Integer, primary_key=True),
        Column('user_id', None, ForeignKey('user.id')),
        Column('email', String(128), nullable=False)
        )

metadata.create_all()

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