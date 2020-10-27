import re
import time

def test_search():
    pass

def test_match():
    pass

def test_fullmatch():
    pattern = re.compile("o[gh]")
    result = pattern.fullmatch("ogg")

    print(result)

def test_findall():
    pattern = re.compile("o")
    match = pattern.findall("000o000o")
    print(match)


def main():
    # test_fullmatch()
    test_findall()

def test():
    for i in range(1000000):
        i += 1 

if __name__ == "__main__":
    start = time.time()
    for i in range(100000000):
        i += 1 
    end = time.time()
    print(end - start)