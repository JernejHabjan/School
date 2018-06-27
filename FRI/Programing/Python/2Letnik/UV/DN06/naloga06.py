import pygit2
import gi

pygit2.require_version('Gtk', '3.0')
from pygit2.repository import Gtk


class Handler:
    def avtorClicked(self, *args):

        builder.get_object("label1").set_text("Jaz sem avtor")

    def izhodClicked(self, *args):
        Gtk.main_quit(args)

    def odpriClicked(self, *args):
        builder.get_object("filechooserdialog1").show_all()

    def prekliciClicked(self, *args):
        builder.get_object("filechooserdialog1").hide()

    def okClicked(self, *args):
        builder.get_object("label1").set_text(builder.get_object("filechooserdialog1").get_filename())
        builder.get_object("filechooserdialog1").hide()

    def pretvoriClicked(self, *args):
        tecaj = 1.0991
        vrednost = builder.get_object("spinbutton1").get_value_as_int()
        if (builder.get_object("comboboxtext1").get_active() == 0):
            vrednost *= tecaj
        else:
            vrednost = vrednost / tecaj
        if (builder.get_object("radiobutton1").get_active()): vrednost *= 0.98
        builder.get_object("entry1").set_text(str(vrednost))

    def izpisiClicked(self, *args):
        model = builder.get_object("comboboxtext1").get_model()
        size = 0
        iter = model.get_iter_first()
        curr = builder.get_object("comboboxtext1").get_active()
        while (iter):
            size = size + 1
            iter = model.iter_next(iter)
        for i in range(size):
            builder.get_object("comboboxtext1").set_active(i)
            print(i, builder.get_object("comboboxtext1").get_active_text())
        builder.get_object("comboboxtext1").set_active(curr)

    def dodajClicked(self, *args):
        builder.get_object("comboboxtext1").append_text(builder.get_object("entry1").get_text())


builder = Gtk.Builder()
builder.add_from_file("naloga06py.glade")
builder.connect_signals(Handler())
window = builder.get_object("window1")
window.show_all()

Gtk.main()
