
import time


class TimerIterator(object):
    interval = None

    def __init__(self, interval):
        self.interval = interval

    def next(self):
        time.sleep(self.interval)
        return int(time.time())

ti = TimerIterator(2)
# import pdb;pdb.set_trace()


class SortedDictIterable(object):

    def __init__(self, thedict={}):
        self.thedict = thedict

    def __iter__(self):
        self.thekeys = self.thedict.keys()
        self.thekeys.sort()
        self.index = -1
        return self

    def next(self):
        self.index = self.index + 1
        if self.index >= len(self.thekeys):
            raise StopIteration
        return self.thekeys[self.index]


def sorted_dict_generator(adict):
    thekeys = adict.keys()
    thekeys.sort()

    for key in thekeys:
        yield key