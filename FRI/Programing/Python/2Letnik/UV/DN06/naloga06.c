#include <stdio.h>
#include <stdlib.h>
#include <gtk/gtk.h>

GtkBuilder *builder;
GtkWidget *window, *fc, *status, *comboB, *entry, *spinB, *rb;

//gboolean ime (GObject *obj GDKEvent event, gpointer userdata)

void avtorClicked(GObject *object, gpointer userdata){
  gtk_label_set_text(GTK_LABEL(status), "Jaz sem avtor!!!");
}
void odpriClicked(GObject *object, gpointer userdata){
  gtk_widget_show(fc);
}

void prekliciClicked(GObject *object, gpointer userdata){
  gtk_widget_hide(fc);
}

void okClicked(GObject *object, gpointer userdata){
  gtk_label_set_text(GTK_LABEL(status), 
    gtk_file_chooser_get_filename(GTK_FILE_CHOOSER(fc)));
  gtk_widget_hide(fc);
}

void pretvoriClicked(GObject *object, gpointer userdata){
  double tecaj=1.0991;
  double vrednost = gtk_spin_button_get_value_as_int(GTK_SPIN_BUTTON(spinB));
  
  if (gtk_combo_box_get_active(GTK_COMBO_BOX(comboB))==0) vrednost*=tecaj;
  else vrednost/=tecaj;
  
  if (gtk_toggle_button_get_active(GTK_TOGGLE_BUTTON(rb))) vrednost*=0.98;
  
  gchar out[30];
  sprintf(out,"%10.3lf", vrednost);
  gtk_entry_set_text(GTK_ENTRY(entry),out);
}

gint elCnt(GtkTreeModel *model){
  gint size=0;
  GtkTreeIter iter;
  gboolean valid;
  valid=gtk_tree_model_get_iter_first(model, &iter);
  while (valid){
    size++;
    valid=gtk_tree_model_iter_next(model, &iter);
  }
  return size;
}

void izpisiClicked(GObject *object, gpointer userdata){
  int i,crt;
  int size = elCnt(gtk_combo_box_get_model(GTK_COMBO_BOX(comboB)));
  crt = gtk_combo_box_get_active(GTK_COMBO_BOX(comboB));
  for (i=0;i<size;i++){
    gtk_combo_box_set_active(GTK_COMBO_BOX(comboB), i);
    printf("%d %s\n", i, gtk_combo_box_text_get_active_text(GTK_COMBO_BOX_TEXT(comboB)));
  }
  gtk_combo_box_set_active(GTK_COMBO_BOX(comboB), crt);
}

void dodajClicked(GObject *object, gpointer userdata){
  gtk_combo_box_text_append_text(GTK_COMBO_BOX_TEXT(comboB), 
    gtk_entry_get_text(GTK_ENTRY(entry)));
}

int main(int argc, char *argv[]){
  gtk_init(&argc, &argv);
  builder=gtk_builder_new();
  gtk_builder_add_from_file(builder,"naloga06.glade", NULL);
  window = GTK_WIDGET(gtk_builder_get_object(builder,"window1"));
  fc = GTK_WIDGET(gtk_builder_get_object(builder,"filechooserdialog1"));
  status = GTK_WIDGET(gtk_builder_get_object(builder,"label1"));
  comboB = GTK_WIDGET(gtk_builder_get_object(builder,"comboboxtext1"));
  entry = GTK_WIDGET(gtk_builder_get_object(builder,"entry1"));
  spinB = GTK_WIDGET(gtk_builder_get_object(builder,"spinbutton1"));
  rb = GTK_WIDGET(gtk_builder_get_object(builder,"radiobutton1"));
  gtk_builder_connect_signals(builder,NULL);
  gtk_window_resize(GTK_WINDOW(window), 400, 250);
  gtk_widget_show(window);
  gtk_main();
  return 0;
}

