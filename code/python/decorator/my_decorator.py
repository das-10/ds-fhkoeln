'''
Created on 16.03.2015

@author: serenidongmo
'''

from threading import RLock
import threading
# from decorator import decorator


def tracer_function(f):
    """ Create a Tracer for function f"""
    def tracer():
        "A tracer for f"
        print "TRACE:", f.func_name
        return f()
    return tracer


def tracer_function_1arg(f):
    """ Create a Tracer for function f"""
    def tracer(thearg):
        "A tracer for f"
        print "TRACE:", f.func_name
        res = f(thearg)
        print res
    return tracer


def tracer_function_general(f):
    """ Create a Tracer for function f"""
    def tracer(*p, **kwargs):
        "A tracer for f"
        print "TRACE:", f.func_name
        res = f(*p, **kwargs)
        print res
    return tracer


def tracer_function_with_name(f):
    """ Create a Tracer for function f"""
    def tracer(*p, **kwargs):
        "A tracer for f"
        print "TRACE:", f.func_name
        res = f(*p, **kwargs)
        print res
    tracer.func_name = f.func_name
    tracer.func_doc = f.func_doc
    return tracer


def threaded(nsec):
    """ A factory of decorators which launch a function after delay"""

    def _delayed_call(f):

        def __delayed_call(*p, **kwargs):
            """Call f(*p,**kwargs) in a Thread after a delay"""
            thread = threading.Timer(nsec, f, p, kwargs)
            thread.start()
            return thread

        return __delayed_call

    return _delayed_call


@threaded(10)
def long_computation():
    count = 0
    for i in range(100000):
        count += i
    print "FINISCHED COMPUTATION:{}".format(i)


@tracer_function_general
def doubler(num):
    return num + num


if __name__ == '__main__':
    # tdbl = tracer_function_with_name(doubler)
    # print tdbl
    doubler(2)
    # long_computation()
