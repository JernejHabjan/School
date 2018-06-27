#include <stdlib.h>
#include <string.h>
#include <gtk/gtk.h>

GtkBuilder *builder;
GtkWidget *window, *fc, *status, *comboB, *spinB, *entry, *rb;

void avtor(GObject *obj, gpointer udata) {
    gtk_label_set_text(GTK_LABEL(status), "Jaz sem avtor");
}

void odpri(GObject *obj, gpointer udata) {
    gtk_widget_show(fc);
}

void preklici(GObject *obj, gpointer udata) {
    gtk_widget_hide(fc);
}

void potrdi(GObject *obj, gpointer udata) {
    gtk_label_set_text(GTK_LABEL(status),
                       gtk_file_chooser_get_filename(GTK_FILE_CHOOSER(fc)));
    gtk_widget_hide(fc);
}

int getComboCnt(GtkTreeModel *model) {
    int size = 0;
    GtkTreeIter iter;
    gboolean vld;
    vld = gtk_tree_model_get_iter_first(model, &iter);
    while (vld) {
        size++;
        vld = gtk_tree_model_iter_next(model, &iter);
    }
    return size;
}

void izpisi(GObject *obj, gpointer udata) {
    int i, crt;
    int size = getComboCnt(gtk_combo_box_get_model(GTK_COMBO_BOX(comboB)));
    crt = gtk_combo_box_get_active(GTK_COMBO_BOX(comboB));
    for (i = 0; i < size; i++) {
        gtk_combo_box_set_active(GTK_COMBO_BOX(comboB), i);
        gchar *out = gtk_combo_box_text_get_active_text(GTK_COMBO_BOX_TEXT(comboB));
        printf("%d  %s\n", i, out);
    }
    gtk_combo_box_set_active(GTK_COMBO_BOX(comboB), crt);
}

void dodaj(GObject *obj, gpointer udata) {
    gtk_combo_box_text_append_text(GTK_COMBO_BOX_TEXT(comboB),
                                   gtk_entry_get_text(GTK_ENTRY(entry)));
}

void pretvori(GObject *obj, gpointer udata) {
    double tecaj = 1.289;
    double vred = gtk_spin_button_get_value(GTK_SPIN_BUTTON(spinB));
    if (gtk_combo_box_get_active(GTK_COMBO_BOX(comboB)) == 0) vred *= tecaj;
    else vred /= tecaj;
    if (gtk_toggle_button_get_active(GTK_TOGGLE_BUTTON(rb))) vred *= 0.98;
    char out[100];
    sprintf(out, "%6.2lf", vred);
    gtk_entry_set_text(GTK_ENTRY(entry), out);
}


int naloga06_UV(int argc, char *argv[]) {
    gtk_init(&argc, &argv);

    builder = gtk_builder_new();
    gtk_builder_add_from_file(builder, "naloga06.glade", NULL);
    window = GTK_WIDGET(gtk_builder_get_object(builder, "window1"));
    fc = GTK_WIDGET(gtk_builder_get_object(builder, "filechooserdialog1"));
    status = GTK_WIDGET(gtk_builder_get_object(builder, "label1"));
    comboB = GTK_WIDGET(gtk_builder_get_object(builder, "comboboxtext1"));
    spinB = GTK_WIDGET(gtk_builder_get_object(builder, "spinbutton1"));
    entry = GTK_WIDGET(gtk_builder_get_object(builder, "entry1"));
    rb = GTK_WIDGET(gtk_builder_get_object(builder, "radiobutton1"));
    gtk_builder_connect_signals(builder, NULL);
    gtk_widget_show(window);
    gtk_main();
    return 0;
}

