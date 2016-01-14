# coding: utf-8
__author__ = 'fripSide'

import sqlite3
import json
import sys
import os

DB = "LeetCode.db"


def get_problems():
    with open("problems1.json", "r") as fp:
        data = fp.read()
    if not data:
        print("can't find problems.json")
        sys.exit(-1)
    data = json.loads(data)
    for tag in data["tags"]:
        save_tag(tag)
    for prob in data["problems"]:
        save_problem(prob)


def save_problem(problem):
    print(problem)


def save_tag(tag):
    print(tag)


def verify_db():
    pass


get_problems()
os.system("mv LeetCode.db app/src/main/assets/")

