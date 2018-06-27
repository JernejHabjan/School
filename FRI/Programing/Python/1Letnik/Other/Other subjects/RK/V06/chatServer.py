import signal

signal.signal(signal.SIGINT, signal.SIG_DFL)
import socket
import struct
import threading

PORT = 8000
HEADER_LENGTH = 2

SEZNAM = ["ANA", "ANJA"]

DICT = {}
DICTREVERSE = {}
SOCKETER = {}


def receive_fixed_length_msg(sock, msglen):
    message = b''
    while len(message) < msglen:
        chunk = sock.recv(msglen - len(message))  # preberi nekaj bajtov
        if chunk == b'':
            raise RuntimeError("socket connection broken")
        message = message + chunk  # pripni prebrane bajte sporocilu

    return message


def receive_message(sock):
    header = receive_fixed_length_msg(sock,
                                      HEADER_LENGTH)  # preberi glavo sporocila (v prvih 2 bytih je dolzina sporocila)
    message_length = struct.unpack("!H", header)[0]  # pretvori dolzino sporocila v int

    message = None
    if message_length > 0:  # ce je vse OK
        message = receive_fixed_length_msg(sock, message_length)  # preberi sporocilo
        message = message.decode("utf-8")

    return message


def send_message(sock, message):
    encoded_message = message.encode("utf-8")  # pretvori sporocilo v niz bajtov, uporabi UTF-8 kodno tabelo

    # ustvari glavo v prvih 2 bytih je dolzina sporocila (HEADER_LENGTH)
    # metoda pack "!H" : !=network byte order, H=unsigned short
    header = struct.pack("!H", len(encoded_message))

    message = header + encoded_message  # najprj posljemo dolzino sporocilo, slee nato sporocilo samo
    sock.sendall(message);


# funkcija za komunikacijo z odjemalcem (tece v loceni niti za vsakega odjemalca)
def client_thread(client_sock, client_addr):
    global clients

    while True:  # neskoncna zanka

        if (client_sock not in SOCKETER.values()):
            send_message(client_sock, "prijavi se študent!")
            send_message(client_sock, "Dovoljena študenska uporabniška imena: ANA, ANJA -POLJE ZARADI TESTNIH RAZLOGOV")

        msg_received = receive_message(client_sock)

        if not msg_received:  # ce obstaja sporocilo
            break

        ##error lahko rata če se random pogovarjajo o ani... more server prašat kdo je
        if (msg_received.split(" ")[0] in SEZNAM) and (client_addr[0] + str(
                client_addr[1]) not in DICT.keys()):  # če je v seznamu študentov in ni že v slovarju

            DICT[client_addr[0] + str(client_addr[1])] = msg_received.split(" ")[0]
            DICTREVERSE[msg_received.split(" ")[0]] = client_addr[0] + str(client_addr[1])
            SOCKETER[msg_received.split(" ")[0]] = client_sock
            print("[system] connected with " + client_addr[0] + ":" + str(client_addr[1]))
            print("[system] we now have " + str(len(clients)) + " clients")

            send_message(client_sock, "Hi " + DICT[client_addr[0] + str(client_addr[1])] + "! ☻")
            send_message(client_sock,
                         "ostalim študentom privatno pišeš, da prvo navedeš njihovo ime, potem pa svoje sporočilo, drugače bodo sporočilo videli vsi.")
            continue
        if client_addr[0] + str(client_addr[1]) not in DICT.keys():  # če ga ni na listi ->error

            send_message(client_sock, "Ne moremo dostaviti" + msg_received)

        if (client_addr[0] + str(client_addr[1]) in DICT.keys()):  # ČE JE ŠTUDENT V SLOVARJU -> LAHKO POGOVARJA

            if (msg_received.split(" ")[0] in DICT.values()):  # private message -> MORTA BIT OBA ONLINE
                stringmessage = msg_received.split(" ")[1:]
                send_message(SOCKETER[msg_received.split(" ")[0]],
                             "Private message from: " + DICT[client_addr[0] + str(client_addr[1])] + ": " + " ".join(
                                 stringmessage))
            else:  # else je public
                for client in clients:
                    send_message(client, msg_received.upper())

    # prisli smo iz neskoncne zanke
    with clients_lock:
        clients.remove(client_sock)

    client_sock.close()


# kreiraj socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(("localhost", PORT))
server_socket.listen(1)

# cakaj na nove odjemalce
print("[system] listening ...")

clients = set()
clients_lock = threading.Lock()
while True:
    try:
        # pocakaj na novo povezavo - blokirajoc klic
        client_sock, client_addr = server_socket.accept()
        with clients_lock:
            clients.add(client_sock)

        thread = threading.Thread(target=client_thread, args=(client_sock, client_addr));
        thread.daemon = True
        thread.start()

    except KeyboardInterrupt:
        break

print("[system] closing server socket ...")
server_socket.close()
