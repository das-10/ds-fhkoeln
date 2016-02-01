'''
Created on 09.07.2015
@author: sereni dongmo
Emulate a Bankserver for 
GeldAutomat
'''

# std
import socket

# logging
import logging
logging.basicConfig(level=logging.DEBUG)

logger = logging.getLogger(__name__)

CHUNK_SIZE = 1024


class BankServer(object):

    def __init__(self, port, host=""):
        self.port = port
        self.host = host
        self.sock = None  # raw socket
        self.sum = 1000

    def create_socket(self):
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.sock.bind((self.host, self.port))
        self.sock.listen(2)
        logger.info("Server Started On Port {}".format(self.port))
        return self

    def run_forever(self):
        try:
            while True:
                sock_comm, (raddr, rport) = self.sock.accept()
                self.welcome(sock_comm, raddr, rport)
                while(True):
                    self.show_menu(sock_comm)
                    try:
                        choose = int(sock_comm.recv(1024))
                    except ValueError as e:
                        logger.debug(e.message)
                        sys.exit(0)
                    logger.debug("CHOOSE:{}".format(choose))

                    if choose == 1:
                        self.out(sock_comm)

                    if choose == 2:
                        self.put_money(sock_comm)

                    if choose == 3:
                        self.show_account_status(sock_comm)

                    if choose == 4:
                        sock_comm.close()
                        break

        except KeyboardInterrupt:
            self.sock.close()
            logger.info("Server Stoped On Port {}".format(self.port))

    def welcome(self, sock_comm, raddr=None, port=None):
        assert sock_comm is not None, "sock_comm may not be None"
        sock_comm.sendall("Welcome to X Bank {}-{}\r\n".format(raddr, port))

    def show_menu(self, sock_comm):
        menu = ["Take Money(1)", "Put Money(2)", "Account Status(3)", "Close(4)", "Please Choose\n"]
        sock_comm.sendall("\n".join(menu))

    def out(self, sock_comm):
        ask = "How much Money yout Want\r\n?"
        sock_comm.sendall(ask)
        amount = sock_comm.recv(CHUNK_SIZE)
        self.sum -= int(amount)

    def put_money(self, sock_comm):
        ask = "Please choose a Amount\r\n?"
        sock_comm.sendall(ask)
        amount = sock_comm.recv(CHUNK_SIZE)
        self.sum = int(amount)

    def show_account_status(self, sock_comm):
        sock_comm.sendall(str(self.sum) + "\r\n")


def start(port, host=""):
    BankServer(port, host).create_socket().run_forever()

if __name__ == '__main__':
    port = 4003
    host = "127.0.0.1"
    import sys
    import getopt

    try:
        opts, args = getopt.getopt(sys.argv[1:], "phost", ["port", "host"])
    except getopt.GetoptError:
        logger.debug("Usage {} [-p] [-host | ]".format(sys.argv[0]))
        sys.exit(0)

    logger.debug("OPTS:{},ARGS:{}".format(opts, args))

    for o, a in opts:
        if o in ("-p", "--port"):
            port = a
        if o in ("-host", "--host"):
            host = a

    # start the server
    start(port, host)