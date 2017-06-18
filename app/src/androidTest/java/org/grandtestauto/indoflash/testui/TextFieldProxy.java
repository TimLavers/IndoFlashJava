package org.grandtestauto.indoflash.testui;

class TextFieldProxy extends ViewProxy {
    TextFieldProxy(int id) {
        super(id);
    }

    void checkThatTextIsEmpty() {
        checkText("");
    }
}
