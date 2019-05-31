import Vue from 'vue'
import Element from 'element-ui'
import '../element-variables.scss'
import VueCropper from 'vue-cropper'
import VueQuillEditor from 'vue-quill-editor'

/**
 * ElementUI 组件安装
 */
Vue.use(Element, {
    size: 'mini'
});
/**
 * VueCropper 组件安装
 */
Vue.use(VueCropper);

/**
 * quill 编辑器安装
 */
Vue.use(VueQuillEditor, {
    modules: {
        toolbar: [
            ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
            ['blockquote', 'code-block'],

            [{'header': 1}, {'header': 2}],               // custom button values
            [{'list': 'ordered'}, {'list': 'bullet'}],
            [{'script': 'sub'}, {'script': 'super'}],      // superscript/subscript
            [{'indent': '-1'}, {'indent': '+1'}],          // outdent/indent
            [{'direction': 'rtl'}],                         // text direction

            [{'size': ['small', false, 'large', 'huge']}],  // custom dropdown
            [{'header': [1, 2, 3, 4, 5, 6, false]}],

            [{'color': []}, {'background': []}],          // dropdown with defaults from theme
            [{'font': []}],
            [{'align': []}],

            ['clean'],                                         // remove formatting button
            ['image']
        ]
    },
});