# coding: utf-8
__author__ = 'fripSide'

import sqlite3
import json
import sys
import os

"""
TODO:
1. 题目content在存入sqlite时候的转义问题
"""

DB = "LeetCode.db"
CON = sqlite3.connect(DB)

TABLE_PROBLEM = {
    "No": 2323,
    "Title": "Problem Title",
    "Accept": 100,
    "Submission": 23,
    "Difficulty": "Hard",
    "IsStar": 0,
    "IsSolve": 0,
    "Content": "content",
    "Answer": "",
    "Url": "url"
}

TABLE_TAG = {
    "Content": "content",
    "Url": "url"
}

TABLE_PIVOT = {
    "Problem": 3,
    "Tag": 4
}

TABLE_SIMILAR = {
    "ProblemOrigin": 3,
    "ProblemSimilar": 5
}


class Table(object):
    PROBLEM = "Problems"
    TAG = "Tags"
    PIVOT = "Pivots"
    SIMILAR = "Similarities"


def get_problems():
    with open("problems.json", "r") as fp:
        data = fp.read()
    if not data:
        print("can't find problems.json")
        sys.exit(-1)
    data = json.loads(data)

    print(len(data["Problems"]))
    for tag in data["Tags"]:
        save_tag(tag)
    for prob in data["Problems"]:
        save_problem(prob)
        # return
    for prob in data["Problems"]:
        save_similar(prob)


def save_problem(problem):
    # print(problem)
    no = problem["No"]
    with open("problems/%d.html" % int(no), "r") as fp:
        content = fp.read()
    if "Tags" in problem:
        tags = problem["Tags"]
        del problem["Tags"]
    if "Similar" in problem:
        similar = problem["Similar"]
        del problem["Similar"]
    # print(problem)
    # 不然转义会出错误
    content = content.replace('"', "'")
    TABLE_PROBLEM.update(dict(Content=content), **problem)
    # print(TABLE_PROBLEM)
    save_to_table(Table.PROBLEM, TABLE_PROBLEM)


def save_similar(problem):
    pass


def save_tag(tag):
    # print(tag)
    pass


def save_to_table(table, data):
    value_name = ", ".join(data.keys())
    values = []
    for val in data.values():
        val = json.dumps(val)
        values.append(val)
    values = ", ".join(values)
    sql = 'INSERT INTO %s (%s) VALUES (%s)' % (table, value_name, values)
    print(sql)
    res = CON.execute(sql)
    CON.commit()
    print(CON.execute("SELECT * FROM %s" % table).fetchall())
    print(res.lastrowid)
    return res.lastrowid


def find_item(table, key, val):
    res = CON.execute("SELECT * FROM %s WHERE %s = %s" % (table, key, val))
    return res.fetchone()


def empty_db():
    CON.execute("DELETE FROM " + Table.PROBLEM)
    CON.execute("DELETE FROM " + Table.TAG)
    CON.execute("DELETE FROM " + Table.PIVOT)
    CON.execute("DELETE FROM " + Table.SIMILAR)
    CON.commit()


def test_db():
    save_to_table(Table.TAG, TABLE_TAG)
    save_to_table(Table.PROBLEM, TABLE_PROBLEM)
    save_to_table(Table.PIVOT, TABLE_PIVOT)
    save_to_table(Table.SIMILAR, TABLE_SIMILAR)


def verify_db():
    pass


test_db()
empty_db()
get_problems()

CON.close()
os.system("mv LeetCode.db app/src/main/assets/")

