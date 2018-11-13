# -*- coding: utf-8 -*-
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy import Column,Integer,String,Text,DateTime,desc
from sqlalchemy.orm import sessionmaker
from datetime import datetime
import json


engine = create_engine('sqlite:///msg_board.db', echo=True)
Base   = declarative_base()
Session = sessionmaker(bind=engine)

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

class DBManager():

    def __name___(self):
        pass


    def get_max_id(self):
        id = 1
        session = Session()
        #todo 这里取最大值的方法应该不是最好的方式
        query_res = session.query(Message).order_by(desc(Message.id)).all()
        if len(query_res) != 0:
            id = query_res[0].id
        session.close()
        return id
        pass

    def add_msg(self, title, author, detail):
        ret = False
        while True:

            if None == title \
                or None == author \
                or None == detail:
                break

            new_id = 1 + self.get_max_id()

            msg    = Message()
            msg.id = new_id
            msg.time   = datetime.now()
            msg.author = author
            msg.title  = title
            msg.detail = detail

            session = Session()
            session.add(msg)
            session.commit()
            session.close()

            ret = True
            break
            pass

        return ret
        pass


    def get_all_msg_json_obj(self, list):
        json_obj = []
        for tmp in list:#todo 这里太耦合，应想个办法改掉
            json_obj.append(dict(zip(Message.get_stuct()
                    , [tmp.id, tmp.title, tmp.detail, tmp.author
                        , tmp.time.strftime("%Y-%m-%d %H:%M:%S")])))
        pass
        return json_obj


    def get_all_msg_json(self):
        session = Session()
        rows = session.query(Message).order_by(desc(Message.id)).all()
        session.close()
        ret = ''

        while True:
            if 0 == len(rows):
                break

            json_obj = self.get_all_msg_json_obj(rows)
            if None == json_obj:
                break

            ret = json.dumps(json_obj)
            break
            pass

        return ret
        pass

db_manager = DBManager()

if __name__ == "__main__":
    print '我在手动执行这个程序...'