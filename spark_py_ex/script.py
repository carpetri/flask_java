#!/usr/bin/python

import sys
import json

inputs = sys.stdin.readlines()
tot = 0.0
for input in inputs:
    tot = tot + json.loads(input)['value']
    print(tot)
