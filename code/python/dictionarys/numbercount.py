#!/usr/bin/env python

# std lib
from random import random
import logging
import sys

logger = logging.getLogger(__name__)
logger.setLevel(logging.DEBUG)
console_handler = logging.StreamHandler()
logger.addHandler(console_handler)

def count_number(list_of_number):
	'''count_number(list_of_number) -> ('number1': count1, 'number2':count2)

	Count the number of times a number occurs in the number list list_of_number.
	Return a dictionary mapping each word to its count.'''

	number_count = {}
	for number in list_of_number:
		number_count[number] = number_count.get(number, 0) + 1
	return number_count


def display_number_count(number_count):
	sorted_by_number = number_count.keys()
	sorted_by_number.sort()

	outlist = []

	for key in sorted_by_number:
		outlist.append("{}:{}".format(key,number_count[key]))
	print ' '.join(outlist)


def create_number_list(size=10):
	return [int(random()*10) for index in xrange(size)]

if __name__ == '__main__':

	if (len(sys.argv) > 1):
		size = int(sys.argv[1])
	
	else:
		logger.info("Usage:{} random_size".format(sys.argv[0]))
		sys.exit(0)

	nlist = create_number_list(size)
	nc =count_number(nlist)
	display_number_count(nc)


