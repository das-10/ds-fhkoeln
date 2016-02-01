'''
Created on 15.07.2015

@author: serenidongmo
'''

BUFSIZ = 1024


def chunkwise_copy(source, destination):
    """ copy source file to destination"""
    with open(source, 'r') as f_src:
        with open(destination, 'w+') as f_dest:
            buf = f_src.read(BUFSIZ)
            while len(buf) > 0:
                f_dest.write(buf)
                buf = buf = f_src.read(BUFSIZ)

if __name__ == '__main__':
    import sys
    if len(sys.argv) != 3:
        print >> sys.stderr, "Usage:", sys.argv[0], "source destination"
        sys.exit(1)
    source, destination = sys.argv[1], sys.argv[2]
    chunkwise_copy(source, destination)