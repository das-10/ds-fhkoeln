#!/usr/bin/env python

# std lib
import time
import logging


timer = time.time
logger = logging.getLogger(__name__)
logger.setLevel(logging.DEBUG)
console_handler = logging.StreamHandler()
logger.addHandler(console_handler)

# show how to build a String efficiently

def ul_inefficient(list_of_items):
	"Create and return a <ul> list of <li> items as string"
	start_time = timer()
	s = '<ul>'

	for item in list_of_items:
		s = s + "\n<li>{}</li>".format(escape_html(item))
	s = s + '\n<ul>'
	final_time = timer()
	total_time = final_time - start_time
	logger.info("UL INEFFICIENT TAKE [{}]".format(total_time))
	logger.debug( "HTML:[{}]".format(s))
	print "-----------------------------------------"
	return s

def ul_efficient(list_of_items):
  "Create and return a <ul> list of <li> items as string"
  start_time = timer()
  slist = ['<ul>']
  for item in list_of_items:
  	slist.append('<li>{}</li>'.format(escape_html(item)))
  s = '\n'.join(slist)

  final_time = timer()
  total_time = final_time - start_time
  logger.debug("UL EFFICIENT TAKE [{}]".format(total_time))
  logger.debug( "HTML:[{}]".format(s))
  print "-----------------------------------------"
  return s


def escape_html(s):
 	''' Escape HTML special Characters

 	Given a string s, escaped the HTML specials characters
 	"&", "<" and ">". Return the escaped string '''

 	return s.replace('&', '&amp;').replace('<','&lt;').replace('>','&gt;')

def is_efficient():
	slist = ["Python", "Java", "C"]
	print "IS_EFFICIENT:[{}]".format(ul_efficient(slist) < ul_inefficient(slist))


if __name__ == '__main__':
	is_efficient()


