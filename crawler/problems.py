# coding: utf-8
"""
python 3
python 2: 请把59行改成content.encode("utf-8")
抓到257道题
"""
__author__ = 'fripSide'

import requests
import logging
import json
import re
import os

logging.basicConfig(filename="download.log", level=logging.INFO, format="%(asctime)s | %(levelname)s | %(message)s")

BASE = "https://leetcode.com"
DIR = "problems/"
PROBLEM_URL = re.compile('<a href="([^\"]+)">([^<]+)</a>')
PROBLEM_TITLE = re.compile('<h3 style="display:inline-block;margin-top:0px;">([^<]+)</h3>')
PROBLEM_ACCEPT = re.compile('Total Accepted: <strong>([^<]+)</strong>')
PROBLEM_SUBMISSION = re.compile('Total Submissions: <strong>([^<]+)</strong>')
PROBLEM_DIFFICULTY = re.compile('Difficulty: <strong>([^<]+)</strong>')
PROBLEM_TAG = re.compile('<a class="btn btn-xs btn-primary" href="([^\"]+)">([^<]+)</a>')

if not os.path.exists(DIR):
    os.mkdir(DIR)

one = "https://leetcode.com/problems/container-with-most-water/"

PROBLEMS = {
    "Tags": [],
    "Problems": []
}

def parser_problem_set():
    url = "https://leetcode.com/problemset/algorithms/"
    r = requests.get(url)
    items = PROBLEM_URL.findall(r.text)
    problems = []
    for item in items:
        link = item[0]
        title = item[1]
        if "problems/" in link:
            problems.append({
                "Url": BASE + link,
                "Title": title
            })

            print(BASE + link)

    return problems


def save_problem(content, info):
    name = DIR + str(info["No"]) + ".html"
    with open(name, "w") as fp:
        fp.write(content)


def get_problem(url):
    r = requests.get(url)
    # print(r.text)
    h3 = PROBLEM_TITLE.search(r.text)
    if not h3:  # 无法抓取的private 题目
        return
    h3 = h3.group(1).split(".")
    no = h3[0].strip()
    title = h3[1].strip()
    print(no + "|" + title)

    accept = PROBLEM_ACCEPT.search(r.text).group(1)
    submission = PROBLEM_SUBMISSION.search(r.text).group(1)
    difficulty = PROBLEM_DIFFICULTY.search(r.text).group(1)

    p1 = r.text.find('<div class="question-content">')
    p2 = r.text.find('<p><a href="/subscribe/">Subscribe</a>')
    # print(p1, p2)
    content = r.text[p1 + 30:p2 - 25].strip()
    print(p1, p2, content)

    similar = []
    tags = []
    for tag in PROBLEM_TAG.findall(r.text):
        print(tag)
        if "problems" in tag[0]:
            name = tag[1][4:]
            similar.append({
                "Url": BASE + tag[0],
                "Title": name.strip()
            })
        if "tag" in tag[0]:
            tt = {
                "Url": BASE + tag[0],
                "Content": tag[1]
            }
            tags.append(tt)
            add = True
            for t in PROBLEMS["Tags"]:
                if t["Content"] == tag[1]:
                    add = False
            if add:
                PROBLEMS["Tags"].append(tt)

    info = {
        "No": int(no),
        "Title": title,
        "Accept": int(accept),
        "Submission": int(submission),
        "Difficulty": difficulty,
        "Tags": tags,
        "Url": url,
        "Similar": similar
    }
    print(info)
    PROBLEMS["Problems"].append(info)
    save_problem(content, info)
    logging.info("Download: " + url)


def pipeline():
    # get_problem(one)
    # with open("problems1.json", "w") as fp:
    #     fp.write(json.dumps(PROBLEMS))
    # return
    problems = parser_problem_set()
    # print(problems)
    for p in problems:
        get_problem(p["Url"])
    with open("problems.json", "w") as fp:
        fp.write(json.dumps(PROBLEMS))

pipeline()