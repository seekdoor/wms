export default {

    /**  @type Element | null */
    _iframe: null,

    _createIframe() {
        if (this._iframe !== null) return;
        let iframe = document.createElement("iframe");
        document.body.appendChild(iframe);
        this._iframe = iframe;
    },

    setDoc(doc, iframe = null) {
        iframe = iframe || this._iframe;
        let d = iframe.contentWindow.document;
        d.write(doc);
        d.close();
    },

    setIframe(iframe) {
        this._iframe = iframe;
    },

    print(iframe = null) {
        iframe = iframe || this._iframe;
        iframe.contentWindow.focus();
        iframe.contentWindow.print();
    }


}