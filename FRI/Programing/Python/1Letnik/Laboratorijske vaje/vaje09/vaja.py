# https://ucilnica.fri.uni-lj.si/mod/page/view.php?id=7772
# https://drive.google.com/drive/folders/0BwoDQsnfgapYMHg5bGpveFVzenc
def capitalize(xs):
    return [x.capitalize() for x in xs]


def icapitalize(xs):
    # xs+=4
    # xs.append(3)
    # del xs [0]
    # xs[0]=54
    # xs [1:3]=[343,3,5,6]
    xs[:] = capitalize(xs)


def count(n):
    while n > 9:
        n = sum(int(i) for i in str(n))
    return n


def postnina(n):
    deljitelji = []
    vsota = []
    for i in range(1, n + 1):
        if n % i == 0:
            deljitelji.append(i)
    for x in deljitelji:
        for y in deljitelji:
            for z in deljitelji:
                if z * x * y == n:
                    vsota.append((x, y, z))
    return min([sum(x) for x in vsota])


def cost(n, m):  # m je meja med 0  in enko
    count = 0
    for i in range(m):
        if n[i] == 1:
            count += 1  # prestel smo koliko enk je na levi strani meje
    for i in range(m, len(n)):
        if n[i] == 0:
            count += 1
    return count


def nic_ena(n):
    seznam = []
    for znj in range(len(n) + 1):
        seznam.append(cost(n, znj))
    return min(seznam)


def mask(n):
    if n == 0:
        return [[]]
    xss = []
    for xs in mask(n - 1):
        xss.append(xs + [0])
        xss.append(xs + [1])
    return xss


def knjige(xs):
    for ms in mask(len(xs)):
        razlika = 0
        for m, x in zip(ms, xs):
            if m:
                razlika += x
            else:
                razlika -= x
        if razlika == 0:
            return True
    return False


import unittest


class TestNaloge8(unittest.TestCase):
    def test_capitalize(self):
        imena = ['marko', 'Miha', 'maja', 'Monika']
        self.assertEqual(capitalize(imena), ['Marko', 'Miha', 'Maja', 'Monika'])
        self.assertEqual(imena, ['marko', 'Miha', 'maja', 'Monika'])

        imena = ['ana', 'anja', 'alen', 'aljana', 'angelika']
        self.assertEqual(capitalize(imena), ['Ana', 'Anja', 'Alen', 'Aljana', 'Angelika'])
        self.assertEqual(imena, ['ana', 'anja', 'alen', 'aljana', 'angelika'])

    def test_icapitalize(self):
        imena = ['marko', 'Miha', 'maja', 'Monika']
        self.assertIsNone(icapitalize(imena))
        self.assertEqual(imena, ['Marko', 'Miha', 'Maja', 'Monika'])

        imena = ['ana', 'anja', 'alen', 'aljana', 'angelika']
        self.assertIsNone(icapitalize(imena))
        self.assertEqual(imena, ['Ana', 'Anja', 'Alen', 'Aljana', 'Angelika'])

    def test_count(self):
        self.assertEqual(count(1), 1)
        self.assertEqual(count(23), 5)
        self.assertEqual(count(12345), 6)
        self.assertEqual(count(999999999), 9)
        self.assertEqual(count(213413512), 4)
        self.assertEqual(count(2147483647), 1)
        self.assertEqual(count(21499999997483999999964999919997), 2)

    def test_postnina(self):
        self.assertEqual(postnina(1), 3)
        self.assertEqual(postnina(6), 6)
        self.assertEqual(postnina(7), 9)
        self.assertEqual(postnina(10), 8)
        self.assertEqual(postnina(100), 14)
        self.assertEqual(postnina(200), 18)
        self.assertEqual(postnina(300), 21)

    def test_nic_ena(self):
        self.assertEqual(nic_ena([0, 0, 1, 0, 1]), 1)
        self.assertEqual(nic_ena([0, 0, 1, 1, 1]), 0)
        self.assertEqual(nic_ena([0, 1, 0, 1, 0]), 2)
        self.assertEqual(nic_ena([1, 1, 1, 1, 0, 0, 0]), 3)
        self.assertEqual(nic_ena([1, 0, 0, 0, 1, 1, 1, 0]), 2)
        self.assertEqual(nic_ena([0, 0, 1, 0, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0]), 6)
        self.assertEqual(nic_ena([0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0]), 9)
        self.assertEqual(nic_ena([1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0]), 10)

    def test_mask(self):
        self.assertCountEqual(mask(0), [[]])
        self.assertCountEqual(mask(1), [[0], [1]])
        self.assertCountEqual(mask(2), [[0, 0], [0, 1], [1, 0], [1, 1]])
        self.assertCountEqual(mask(3),
                              [[0, 0, 0], [0, 0, 1], [0, 1, 0], [0, 1, 1], [1, 0, 0], [1, 0, 1], [1, 1, 0], [1, 1, 1]])
        self.assertEqual(len(mask(10)), 1024)
        self.assertEqual(len(mask(16)), 65536)

    def test_knjige(self):
        self.assertTrue(knjige([10, 20, 30]))
        self.assertTrue(knjige([10, 20, 30, 40]))
        self.assertTrue(knjige([11, 20, 12, 42, 20, 20, 11, 20, 20, 20, 4]))
        self.assertTrue(knjige([23, 51, 51, 153, 20, 25, 51, 59, 39, 35, 91]))
        self.assertTrue(knjige([33, 9, 15, 14, 7, 35, 13, 8, 38, 10, 60, 14, 12, 56]))
        self.assertTrue(knjige([101, 42, 132, 41, 120, 301, 401, 180, 150, 11, 11]))

        self.assertFalse(knjige([10, 20, 30, 40, 50]))
        self.assertFalse(knjige([11, 20, 12, 42, 22, 20, 11, 20, 20, 20, 4]))
        self.assertFalse(knjige([23, 51, 51, 153, 25, 51, 59, 39, 35, 91]))
        self.assertFalse(knjige([101, 42, 132, 41, 120, 301, 401, 180]))


if __name__ == '__main__':
    unittest.main(verbosity=2)
