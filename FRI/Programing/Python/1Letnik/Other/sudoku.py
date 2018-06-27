from functools import partial
from tkinter import *

undefinedTextColor = "#ffffff"
constantTextColor = "#0000aa"
correctTextColor = "#00ff00"
wrongTextColor = "#ff0000"
backgroundColor = "#101010"

arrayUsed = 1

sudokuArray = ([
    -1, 5, 9, -1, 2, -1, 4, 6, -1,
    1, -1, -1, 4, -1, 3, -1, -1, 8,
    3, -1, -1, -1, 7, -1, -1, -1, 2,
    -1, 3, -1, 8, -1, 9, -1, 2, -1,
    6, -1, 5, -1, -1, -1, 3, -1, 7,
    -1, 1, -1, 7, -1, 6, -1, 4, -1,
    2, -1, -1, -1, 1, -1, -1, -1, 4,
    9, -1, -1, 3, -1, 2, -1, -1, 5,
    -1, 7, 8, -1, 6, -1, 2, 3, -1
]) if arrayUsed == 0 else ([
    7, 5, 9, 1, 2, 8, 4, 6, -1,
    1, 2, 6, 4, 9, 3, -1, -1, 8,
    3, 8, 4, 6, 7, 5, -1, 1, 2,
    4, 3, 7, 8, 5, 9, 1, 2, 6,
    6, 9, 5, 2, 4, 1, 3, 8, 7,
    8, 1, 2, 7, 3, 6, 5, 4, 9,
    2, 6, 3, 5, 1, 7, 8, 9, 4,
    9, 4, 1, 3, 8, 2, 6, 7, 5,
    5, 7, 8, 9, 6, 4, 2, 3, 1
])


class Sudoku(Frame):
    buttons = []
    values = []
    found = [-1, -1]

    def __init__(self, parent):
        Frame.__init__(self, parent)
        self.grid()
        self.createInterface()

    def addButton(self, text, row, column):
        self.values.append(int(text))
        self.buttons.append(Button(self))
        self.buttons[-1]["text"] = str(self.values[-1]) if self.values[-1] != 0 else "-"
        self.buttons[-1].grid(row=row, column=column)
        command = partial(self.updateButton, len(self.buttons) - 1)
        self.buttons[-1]["command"] = command
        self.buttons[-1]["width"] = 3
        self.buttons[-1]["height"] = 1
        self.buttons[-1]["bg"] = backgroundColor
        self.buttons[-1]["fg"] = constantTextColor if self.values[-1] != 0 else undefinedTextColor
        self.buttons[-1]["font"] = ("Canvas", 20, "bold")

    def updateButton(self, index):
        if (sudokuArray[index] != -1): return
        self.values[index] += 1
        if self.values[index] > 9: self.values[index] = 0

        if (self.checkField(int((index / 9)), (index % 9))):
            self.buttons[index]["fg"] = correctTextColor if self.values[index] != 0 else undefinedTextColor
            if (self.checkWin()):
                raise SystemExit
        else:
            self.buttons[index]["fg"] = wrongTextColor
        self.buttons[index]["text"] = str(self.values[index]) if self.values[index] != 0 else "-"

    def checkColumn(self, row, valueRow, valueColumn):
        if (row > 8):
            return [-1, -1]
        if (self.values[row * 9 + valueColumn] == self.values[valueRow * 9 + valueColumn]
            and row != valueRow and self.values[row * 9 + valueColumn] > 0):
            return [row, valueColumn]
        return self.checkColumn(row + 1, valueRow, valueColumn)

    def checkRowAndColumn(self, column, row, valueRow, valueColumn):
        if (column > 8):
            return self.checkColumn(row, valueRow, valueColumn)
        if (self.values[valueRow * 9 + column] == self.values[valueRow * 9 + valueColumn]
            and column != valueColumn and self.values[valueRow * 9 + column] > 0):
            return [valueRow, column]
        return self.checkRowAndColumn(column + 1, row, valueRow, valueColumn)

    def checkGrid(self, column, row, valueRow, valueColumn):
        rowCell = int(valueRow / 3) * 3
        columnCell = int(valueColumn / 3) * 3

        for i in range(rowCell, rowCell + 3):
            for j in range(columnCell, columnCell + 3):
                if (self.values[i * 9 + j] == self.values[valueRow * 9 + valueColumn]
                    and i != valueRow and j != valueColumn and self.values[i * 9 + j] > 0):
                    return [i, j]
        return self.checkRowAndColumn(column, row, valueRow, valueColumn)

    def checkWin(self):
        for i in range(0, 9):
            for j in range(0, 9):
                color = self.buttons[(i * 9) + j]["fg"]
                if color != correctTextColor and color != constantTextColor:
                    return False
        return True

    def checkField(self, x, y):
        if (self.found[0] != -1 and self.found[1] != -1):
            self.buttons[self.found[0] * 9 + self.found[1]]["bg"] = backgroundColor

        self.found = self.checkGrid(0, 0, x, y)
        # print(self.found[0], self.found[1])

        if (self.found[0] != -1 and self.found[1] != -1):
            self.buttons[self.found[0] * 9 + self.found[1]]["bg"] = wrongTextColor
            return False
        else:
            return True

    def createInterface(self):
        for i in range(1, 10):
            for j in range(1, 10):
                v = sudokuArray[(i - 1) * 9 + (j - 1)]
                if (v == -1): v = 0
                self.addButton(v, i - 1, j - 1)


window = Tk()
window.title("Sudoku")
window.geometry("549x504")
sudoku = Sudoku(window)
window.mainloop()
