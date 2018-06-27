import unittest
import time
from multiprocessing import Process
from server import *

PORT = 8000
HOST = ""


class ServerTest(unittest.TestCase):
    def setUp(self):
        with open("DB.txt", "w") as f: pass # empty file
        self.p = Process(target=run, args=(HOST, PORT))
        self.p.start()

    def tearDown(self):
        self.p.terminate()

    def _client(self, data):
        time.sleep(1)
        client_socket = socket.socket()
        client_socket.connect((HOST, PORT))
        client_socket.sendall(data.encode("UTF-8"))
        response = client_socket.recv(4096).decode()
        client_socket.close()
        return response

    def test_parse_request_get_index(self):
        data = ('GET / HTTP/1.1\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive\n')
        method, path, protocol, params = parse_request(data)
        self.assertEqual(method, "GET")
        self.assertEqual(path, "index.html")
        self.assertEqual(protocol, "HTTP/1.1")
        self.assertEqual(params, None)


    def test_parse_request_get_params(self):
        data = ('GET /?first=John&last= HTTP/1.1\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive')
        method, path, protocol, params = parse_request(data)
        self.assertEqual(method, "GET")
        self.assertEqual(path, "index.html")
        self.assertEqual(protocol, "HTTP/1.1")
        self.assertEqual(params["first"], "John")
        self.assertEqual(params["last"], "")

    def test_parse_request_get_style(self):
        data = ('GET /style.css HTTP/1.1\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive')
        method, path, protocol, params = parse_request(data)
        self.assertEqual(method, "GET")
        self.assertEqual(path, "style.css")
        self.assertEqual(protocol, "HTTP/1.1")
        self.assertEqual(params, None)

    def test_parse_request_get_dir(self):
        data = ('GET /test/ HTTP/1.1<\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive')
        method, path, protocol, params = parse_request(data)
        self.assertEqual(method, "GET")
        self.assertEqual(path, "test/index.html")
        self.assertEqual(protocol, "HTTP/1.1")
        self.assertEqual(params, None)

    def test_parse_request_get_file(self):
        data = ('GET /test HTTP/1.1\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive')
        method, path, protocol, params = parse_request(data)
        self.assertEqual(method, "GET")
        self.assertEqual(path, "test")
        self.assertEqual(protocol, "HTTP/1.1")
        self.assertEqual(params, None)

    def test_parse_request_get_dir_file(self):
        data = ('GET /test/hello.html HTTP/1.1\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive')
        method, path, protocol, params = parse_request(data)
        self.assertEqual(method, "GET")
        self.assertEqual(path, "test/hello.html")
        self.assertEqual(protocol, "HTTP/1.1")
        self.assertEqual(params, None)

    def test_handle_request_get(self):
        response = handle_request("GET", "index.html", "HTTP/1.1", None)
        self.assertTrue(response.startswith("HTTP/1.1 200 OK"))

    def test_handle_request_get_style(self):
        response = handle_request("GET", "style.css", "HTTP/1.1", None)
        self.assertTrue(response.startswith("HTTP/1.1 200 OK"))

    def test_handle_request_get_not_found(self):
        response = handle_request("GET", "test/test.html", "HTTP/1.1", None)
        self.assertTrue(response.startswith("HTTP/1.1 404 Not Found"))

    def test_parse_request_post(self):
        data = ('POST /add.html HTTP/1.1\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive\n'
                'Content-Length: 23\n\n'
                'number=1&first=John&last=Smith')
        method, path, protocol, params = parse_request(data)
        self.assertEqual(method, "POST")
        self.assertEqual(path, "add.html")
        self.assertEqual(protocol, "HTTP/1.1")
        self.assertEqual(params["first"], "John")
        self.assertEqual(params["last"], "Smith")

    def test_save_read(self):
        save({"number": "1", "first": "John", "last": "Smith"})
        with open("DB.txt", "r") as f:
            self.assertEqual(len(f.readlines()), 1)
        save({"number": "2", "first": "Jack", "last": "Black"})
        self.assertEqual(read({"first": "John", "last": ""}),
                         read({"first": "", "last": "Smith"}))
        self.assertNotEqual(read({"first": "John", "last": ""}),
                            read({"first": "", "last": "Black"}))
        self.assertEqual(read(None), "")

    def test_server_get(self):
        save({"number": "1", "first": "John", "last": "Smith"})
        data = ('GET /?first=John&last= HTTP/1.1\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive')
        response = self._client(data)
        self.assertTrue("HTTP/1.1 200 OK" in response)
        self.assertTrue("<td>John</td>" in response)
        self.assertTrue("%students%" not in response)

    def test_server_post(self):
        data = ('POST /add.html HTTP/1.1\n'
                'Host: localhost:8000\n'
                'Connection: keep-alive\n\n'
                'number=1&first=John&last=Smith')
        response = self._client(data)
        self.assertTrue("HTTP/1.1 200 OK" in response)
        self.assertTrue("<p>A new student has been added.</p>" in response)
        with open("DB.txt", "r") as f:
            self.assertEqual(len(f.readlines()), 1)

