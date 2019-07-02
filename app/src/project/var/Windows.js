import aside_menu from "@/router/aside_menu";

export default {

    list: [aside_menu[0]],
    selectedName: aside_menu[0].meta.title,

    set(route) {
        let f = this.list.find(x => x.meta.title === route.meta.title);
        if (f) {
            this.selectedName = f.meta.title;
            return;
        }
        this.list = this.list.concat([route]);
        this.selectedName = route.meta.title;
    },

    remove(routeName) {
        if (!this.list.length) return;
        let index = this.list.findIndex(x => x.meta.title === routeName);
        this.list.splice(index, 1);
        if (this.list.length - 1 < index) {
            index--;
        }
        this.selectedName = this.list[index].meta.title;
    } ,

    clearWindow(){
        this.list = [aside_menu[0]];
        this.selectedName = aside_menu[0].meta.title ;
    }

}