import socket
import struct
from _multiprocessing import recv

import datetime

s = socket.socket()
s.connect(("ntp1.arnes.si",37))
buff = s.recv(4, 0)

t=struct.unpack(buff, ">I")[0]
print(t)
d = datetime.datetime.fromtimestamp(t);
s.close()
