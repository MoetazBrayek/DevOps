import os
import sys
import socket
import sys,time

def sprint(str):
   for c in str + '\n':
     sys.stdout.write(c)
     sys.stdout.flush()
     time.sleep(3./90)


def init():
    sprint("Checking if All Service  ....")

def is_port_in_use(port):
    import socket
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        if s.connect_ex(('localhost', port)) == 0 :
            if port == 8080 :
                print(f"Your Jenkins Service Is Up : http://localhost:{port}")
            elif port == 8081 :
                print(f"Your Nexus Service Is Up : http://localhost:{port}")
            elif port == 9000 :
                print(f"Your Sonar Service Is Up : http://localhost:{port}")
            else:
                pass

        else:
            pass

init()
is_port_in_use(8080)
is_port_in_use(9000)
is_port_in_use(8081)
#smee --url https://smee.io/Fa1RfLxs47CnueD0 --path /github-webhook/ --port 8080
