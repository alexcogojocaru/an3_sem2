import os
import sys
import time
import threading
from PyQt5.QtWidgets import QWidget, QApplication, QFileDialog, QMessageBox
from PyQt5 import QtCore
from PyQt5.uic import loadUi
from mq_communication import RabbitMq
from abc import ABCMeta


def debug_trace(ui=None):
    from pdb import set_trace
    QtCore.pyqtRemoveInputHook()
    set_trace()
    # QtCore.pyqtRestoreInputHook()


class App(QWidget):
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self, ui_file):
        super(App, self).__init__()
        ui_path = os.path.join(self.ROOT_DIR, ui_file)
        loadUi(ui_path, self)
        self.rabbit_mq = RabbitMq(self)

    def send_request(self, request):
        print('Sending')
        self.rabbit_mq.send_message(message=request)
        self.rabbit_mq.receive_message()


class AddBookPopUp(App):
    EMPTY_FIELD = ''

    def __init__(self, ui_file):
        super(AddBookPopUp, self).__init__(ui_file)
        self.submit_button.clicked.connect(self.submit)
        self.cancel_button.clicked.connect(self.cancel)

        self.field_edits = [
            self.autor_line_edit,
            self.text_line_edit,
            self.denumire_line_edit,
            self.editura_line_edit
        ]

        self.msg = QMessageBox()
        self.msg.setWindowTitle("Invalid fields")
        self.msg.setText("Please fill all the parameters")

    def submit(self):
        responses = [x.text() for x in self.field_edits]
        if self.EMPTY_FIELD in responses:
            self.msg.show()
        else:
            request = 'add:' + ':'.join(x.text() for x in self.field_edits)
            threading.Thread(target=self.send_request, args=(request,)).start()
            for x in self.field_edits:
                x.clear()
            self.close()
        
    def cancel(self):
        self.close()


class LibraryApp(App):
    def __init__(self, ui_file):
        super(LibraryApp, self).__init__(ui_file)
        self.search_btn.clicked.connect(self.search)
        self.save_as_file_btn.clicked.connect(self.save_as_file)
        self.add_book_button.clicked.connect(self.add_book)
        self.pop_up = AddBookPopUp('add_book_interface.ui')

    def set_response(self, response):
        self.result.setText(response)

    def search(self):
        search_string = self.search_bar.text()
        request = None
        if not search_string:
            if self.json_rb.isChecked():
                request = 'print:json'
            elif self.html_rb.isChecked():
                request = 'print:html'
            elif self.xml_rb.isChecked():
                request = 'print:xml'
            else:
                request = 'print:raw'
        else:
            formatString = ''

            if self.json_rb.isChecked():
                formatString = 'json'
            elif self.html_rb.isChecked():
                formatString = 'html'
            elif self.xml_rb.isChecked():
                formatString = 'xml'
            else:
                formatString = 'text'
            
            if self.author_rb.isChecked():
                request = 'find:author={}={}'.format(formatString, search_string)
            elif self.title_rb.isChecked():
                request = 'find:title={}={}'.format(formatString, search_string)
            else:
                request = 'find:publisher={}={}'.format(formatString, search_string)
        self.send_request(request)

    def save_as_file(self):
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        file_path = str(
            QFileDialog.getSaveFileName(self,
                                        'Salvare fisier',
                                        options=options))
        if file_path:
            file_path = file_path.split("'")[1]
            if not file_path.endswith('.json') and not file_path.endswith(
                    '.html') and not file_path.endswith('.txt'):
                if self.json_rb.isChecked():
                    file_path += '.json'
                elif self.html_rb.isChecked():
                    file_path += '.html'
                elif self.xml_rb.isChecked():
                    file_path += '.xml'
                else:
                    file_path += '.txt'
            try:
                with open(file_path, 'w') as fp:
                    if file_path.endswith(".html"):
                        fp.write(self.result.toHtml())
                    else:
                        fp.write(self.result.toPlainText())
            except Exception as e:
                print(e)
                QMessageBox.warning(self, 'Exemplul 2',
                                    'Nu s-a putut salva fisierul')

    def add_book(self):
        self.pop_up.show()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = LibraryApp('exemplul_2.ui')
    window.show()
    sys.exit(app.exec_())
