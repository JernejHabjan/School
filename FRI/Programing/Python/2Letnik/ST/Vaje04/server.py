"""An example of a simple HTTP server."""
from __future__ import print_function
import socket
import mimetypes

PORT = 8080

# Header template for a successful HTTP request
# Return this header (+ content) when the request can be
# successfully fulfilled
HEADER_RESPONSE_200 = """HTTP/1.1 200 OK
Content-Type: %s
Content-Length: %d

"""  ## content lenght je število bajtov len(response)

# Template for a 404 (Not found) error: return this when
# the requested resource is not found
RESPONSE_404 = """
HTTP/1.1 404 Not found
Content-Type: text/html;charset=utf-8
<!doctype html>
<h1>404 Page not found</h1>
<p>Page cannot be found.</p>
"""
RESPONSE_ERROR_METHOD = "ERROR -> METHOD NOT RECOGNISED"
RESPONSE_ERROR_FILENAME = "ERROR -> FILENAME IS NULL"
RESPONSE_ERROR_HTTP_VERSION = "ERROR -> HTTP VERSION NOT SUPPORTED"

METHODS = ["GET", "PUT"]


def extract_headers(file_input):
    headers = {}

    while True:
        line = file_input.readline().strip()
        if line == "":
            break
        key, value = line.split(": ")
        headers[key] = value

    return headers


def send_file(filename, connection):
    try:
        # z with se file avtomatsko zapre
        with open(filename, "rb") as handle:  # read binary
            response_body = handle.read()  # tle not mamo zdj filename
            print(response_body)
        _type, _ = mimetypes.guess_type(filename)  # da so lah css datoteke, slike in ne sm html
        response_header = HEADER_RESPONSE_200 % (_type, len(response_body))
        connection.sendall(response_header.encode("utf-8"))
        connection.sendall(response_body)  # body ni treba encodat
    except FileNotFoundError:
        connection.sendall(RESPONSE_404.encode("utf-8"))  # encodat morš ker je text


def get(filename, version, file_input, connection, address):
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
        return arguments_dict

    ### CODE ###


    if filename == "":
        print("ERROR -> FILENAME IS NULL")
        connection.sendall(RESPONSE_ERROR_FILENAME.encode("utf-8"))
        return

    filename = filename[1:]  # da se znebimo slasha

    arguments_dict = extract_arguments(filename)

    if not len(filename):  # makes default action opening index.html
        filename = "index.html"

    headers = extract_headers(file_input)
    print(address[0], address[1], arguments_dict, headers)

    send_file(filename, connection)

    file_input.close()


def put(filename, version, file_input, connection, address):
    print("lol")




    file_input.close()


def process_request(connection, address):
    """
    Process an incoming socket request.
    :param connection is a new socket object usable to send and receive data on the connection
    :param address is the address bound to the socket on the other end of the connection
    """
    # Make reading from a socket like reading from a file
    file_input = connection.makefile()  # naredi wrapper nad socketom da se začne obnašati kot datoteka

    # Read one line
    request_line = file_input.readline().strip()

    try:
        method, filename, version = request_line.split()
    except:
        print("Unknown connection... Closing.")
        return

    if version != "HTTP/1.1":
        connection.sendall(RESPONSE_ERROR_HTTP_VERSION.encode("utf-8"))
        return

    if method == "GET":
        get(filename, version, file_input, connection, address)

    elif method == "PUT":
        put(filename, version, file_input, connection, address)
    else:
        connection.sendall(RESPONSE_ERROR_METHOD.encode("utf-8"))
        file_input.close()
        return


def main():
    """Starts the server and waits for connections."""

    # Create a TCP socket
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    # To prevent "Address already in use" error,
    # set the reuse address socket option
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)  # da lahko reusamo port

    # Bind on all network addresses (interfaces)
    server_socket.bind(("", PORT))

    # Start listening and allow at most 1 queued connection
    server_socket.listen(1)

    print("[system] Listening on %d" % PORT)

    while True:
        try:
            client_socket, client_addr = server_socket.accept()

            print("[%s:%d] CONNECTED" % client_addr)

            # Process request
            process_request(client_socket, client_addr)

            # Close the socket
            client_socket.close()
            print("[%s:%d] DISCONNECTED" % client_addr)


        except KeyboardInterrupt:
            break
    print("[system] Closing server socket")
    server_socket.close()


if __name__ == "__main__":
    main()
