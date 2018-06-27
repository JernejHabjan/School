from __future__ import print_function

import mimetypes
import pickle
import socket
from os.path import isdir

try:
    from urllib.parse import unquote_plus
except ImportError:
    from urllib import unquote_plus

PORT = 8080
PICKLE_DB = "db.pkl"
WWW_DATA = "www-data"
VALID_METHODS = ["GET", "POST"]

TABLE_ROW = """
<tr>
    <td>%d</td>
    <td>%s</td>
    <td>%s</td>
</tr>
"""

HEADER_RESPONSE_200 = """HTTP/1.1 200 OK
content-type: %s
content-length: %d
connection: Close

"""
HEADER_RESPONSE_301 = """HTTP/1.1 301 Moved Permanently
content-type: text/html
location: %s
connection: close

<!doctype html>
<h1>Moved Permanently</h1>
<p>Site has been moved to %s</p>
"""
HEADER_RESPONSE_400 = """HTTP/1.1 400 Bad Request
content-type: text/html
connection: close

<!doctype html>
<h1>Bad request</h1>
<p>Sorry... server couldnt parse this request.</p>
"""
HEADER_RESPONSE_404 = """HTTP/1.1 404 Not found
content-type: text/html
connection: close

<!doctype html>
<h1>404 Page not found</h1>
<p>Page cannot be found.</p>
"""


def save_to_db(first, last):
    """Create a new user with given first and last name and store it into
    file-based database.

    For instance, save_to_db("Mick", "Jagger"), will create a new user
    "Mick Jagger" and also assign him a unique number.

    Do not modify this method."""

    existing = read_from_db()
    existing.append({
        "number": 1 if len(existing) == 0 else existing[-1]["number"] + 1,
        "first": first,
        "last": last
    })
    with open(PICKLE_DB, "wb") as handle:
        pickle.dump(existing, handle)


def read_from_db(criteria=None):
    """Read entries from the file-based DB subject to provided criteria

    Use this method to get users from the DB. The criteria parameters should
    either be omitted (returns all users) or be a dict that represents a query
    filter. For instance:
    - read_from_db({"number": 1}) will return a list of users with number 1
    - read_from_db({"first": "bob"}) will return a list of users whose first
    name is "bob".

    Do not modify this method."""
    if criteria is None:
        criteria = {}
    else:
        # remove empty criteria values
        for key in ("number", "first", "last"):
            if key in criteria and criteria[key] == "":
                del criteria[key]

        # cast number to int
        if "number" in criteria:
            criteria["number"] = int(criteria["number"])

    try:
        with open(PICKLE_DB, "rb") as handle:
            data = pickle.load(handle)

        filtered = []
        for entry in data:
            predicate = True

            for key, val in criteria.items():
                if val != entry[key]:
                    predicate = False

            if predicate:
                filtered.append(entry)

        return filtered
    except (IOError, EOFError):
        return []


def extract_arguments(filename):
    arguments_dict = {}  # arguments like these: /?first=k&last=k&role=Student
    if "?" in filename:
        args = filename.split("?")[1]
        filename = filename.split("?")[0]
        args_array = args.split("&")
        for arg in args_array:
            key = arg.split("=")[0]
            value = arg.split("=")[1]

            arguments_dict[key] = value
    return arguments_dict, filename


def extract_filename(filename, client_port):
    if filename == "":
        return "", 400
    if isdir(filename):  # ce je dir -> redirect , ce ne -> napaka
        try:
            open(WWW_DATA + filename + "index.html")  # prevermo ce direktorij obstaja
            return "http://localhost:" + str(client_port) + filename + "index.html", 301  # preusmermo na ta naslov
        except:
            return filename, 200

    # ce ni dir serviramo datoteko:
    try:
        open(WWW_DATA + filename)
        return filename, 200
    except:
        try:
            open(WWW_DATA + filename + "/index.html")  # ce je to ubistvu DIR brez end slasha
            return "http://localhost:" + str(client_port) + filename + "index.html", 301  # preusmermo na ta naslov
        except:

            return filename, 200


def send_file(filename, connection, headers):
    filename = WWW_DATA + filename

    try:

        with open(filename, "rb") as handle:
            response_body = handle.read()
        _type, _ = mimetypes.guess_type(filename)
        if _type is None:
            _type = "application/octet-stream"
        response_header = HEADER_RESPONSE_200 % (_type, len(response_body))
        try:
            connection.sendall(response_header.encode("utf-8"))
            connection.sendall(response_body)  # body ni treba encodat
        except ConnectionAbortedError:
            print("[system] Connection was aborted by client.")
            return 1
    except FileNotFoundError:
        connection.sendall(HEADER_RESPONSE_404.encode("utf-8"))  # encodat morš ker je text
        return 1

    try:
        if headers["Connection"] == "keep-alive":
            print("[system] Connection: Keep-Alive")
            return 0
    except:
        return 1


###############################################################################################################

def get(filename, file_input, connection):
    def set_student_text(database):
        text = ""
        for row in database:
            number, firstname, lastname = row.values()

            text += TABLE_ROW % (int(number), firstname, lastname)
            text += "\n"
        return text

    def get_students(arguments_dict):
        if len(arguments_dict) == 0:  # brez parametrov brez -> ?neki...
            database = read_from_db()
            text = set_student_text(database)
        else:
            seznam1 = []
            for key, value in arguments_dict.items():
                if key == "number" or key == "first" or key == "last":
                    seznam1.extend(read_from_db({key: value}))

            seznam2 = []
            for key, value in arguments_dict.items():
                if key == "first":
                    seznam2.extend(read_from_db({key: value}))

            seznam3 = []
            for key, value in arguments_dict.items():
                if key == "last":
                    seznam3.extend(read_from_db({key: value}))

            temp = seznam1
            if len(temp) > 0:
                if len(seznam2) > 0:
                    temp = temp if (len(temp) < len(seznam2)) else seznam2
            else:
                temp = seznam2
            if len(temp) > 0:
                if len(seznam3) > 0:
                    temp = temp if (len(temp) < len(seznam3)) else seznam3
            else:
                temp = seznam3

            seznam = temp
            text = set_student_text(seznam)
        try:
            with open(WWW_DATA + "/app_list.html") as app_list_f:
                app_list_body = app_list_f.read()
                app_list_body = app_list_body.replace("{{students}}", text)
                # POŠLJEMO
                response_header = HEADER_RESPONSE_200 % ("text/html", len(app_list_body))
                try:
                    connection.sendall(response_header.encode("utf-8"))
                    connection.sendall(app_list_body.encode())
                except ConnectionAbortedError:
                    print("[system] Connection was aborted by client.")
                    return 1
        except FileNotFoundError:
            print("[system] Could not open app_list.html. Connection closed.")
            connection.sendall(HEADER_RESPONSE_404.encode("utf-8"))
            return 1
        return 0

    def extract_headers(file_input):
        headers = {}
        while True:
            line = file_input.readline().strip()
            if line == "":
                break
            key, value = line.split(": ")
            headers[key] = value
        file_input.close()
        return headers

    ### CODE ###
    arguments_dict, filename = extract_arguments(filename)
    headers = extract_headers(file_input)
    client_port = headers["Host"].split(":")[1]

    filename, _return = extract_filename(filename, client_port)

    if filename == "/app-index":
        return get_students(arguments_dict)

    if _return == 200:
        return send_file(filename, connection, headers)
    elif _return == 301:
        # filename = filename.replace('\\', '/')
        connection.sendall((HEADER_RESPONSE_301 % (filename, filename)).encode("utf-8"))
        return 1
    elif _return == 400:
        connection.sendall(HEADER_RESPONSE_400.encode("utf-8"))
        return 1
    elif _return == 404:
        connection.sendall(HEADER_RESPONSE_404.encode("utf-8"))
        return 1

def post(file_input, connection):
    def extract_headers_POST(file_input):
        headers = {}
        request_line = ""
        byte = file_input.read(1)
        while byte != "":
            request_line += byte
            last = byte  # za exit iz while zanke -> dva zaporedna \n \n
            byte = file_input.read(1)
            if byte == last and last == "\n":  # ce sta dva zaporedna \n \n
                break
        request_line_ax = request_line.strip().split("\n")
        for line in request_line_ax:
            key, value = line.split(": ")
            headers[key] = value
        return headers

    def extract_body(file_input, content_length):
        # read
        request_line = ""
        for i in range(content_length):
            byte = file_input.read(1)
            request_line += byte
        # parse
        parameters = {}
        for tuple in request_line.split("&"):
            key, val = tuple.split("=")
            parameters[key] = val
        return parameters

    ### CODE ###
    headers = extract_headers_POST(file_input)
    headers_lower = {k.lower(): v for k, v in headers.items()}
    content_length = int(headers_lower["content-length"])
    parameters = extract_body(file_input, content_length)
    file_input.close()

    if ("first" not in parameters or "last" not in parameters):
        print("[system] Missing parameters 'first' or 'last'. Connection Closed.")
        connection.sendall(HEADER_RESPONSE_400.encode("utf-8"))
        return 1

    save_to_db(parameters["first"], parameters["last"])
    return send_file("/app_add.html", connection, headers)

def process_request(connection, address):
    file_input = connection.makefile()
    try:
        request_line = file_input.readline()

    except:
        print("[system] Request line could not be read. Connection closed.")
        connection.sendall(HEADER_RESPONSE_400.encode("utf-8"))
        return 1

    request_line_ax = request_line.strip().split(" ")

    if len(request_line_ax) < 3:  # nismo dobil niti cele prve GET / HTTP/1.1
        print("[system] Unknown request parameters. Connection closed.")
        connection.sendall(HEADER_RESPONSE_400.encode("utf-8"))
        return 1

    method, filename, version = request_line_ax[0], request_line_ax[1], request_line_ax[2]

    if method not in VALID_METHODS or version != "HTTP/1.1":
        connection.sendall(HEADER_RESPONSE_400.encode("utf-8"))
        return 1

    if method == "GET":
        return get(filename, file_input, connection)
    elif method == "POST":
        return post(file_input, connection)


def main(PORT):
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind(("", PORT))
    server_socket.listen(1)

    print("[system] Listening on port %d" % PORT)
    while True:
        try:
            client_socket, client_addr = server_socket.accept()
            print("[system] Connected to: [%s:%d]" % client_addr)
            close = process_request(client_socket, client_addr)
            if close:
                client_socket.close()
                print("[system] Disconnected: [%s:%d]" % client_addr)
        except KeyboardInterrupt:
            break
    print("[system] Closing server socket. Server terminated.")
    server_socket.close()


if __name__ == "__main__":
    main(PORT)
