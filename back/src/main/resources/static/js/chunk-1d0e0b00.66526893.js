(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-1d0e0b00","chunk-599f6d58"],{"14ee":function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("stock-in-log-view",{attrs:{type:2}})},r=[],c=a("d545"),n={name:"StockOutLogView",components:{StockInLogView:c["default"]},props:{},data:function(){return{}},mounted:function(){this.reload()},methods:{reload:function(){}},watch:{},computed:{}},i=n,l=(a("52b6"),a("2877")),s=Object(l["a"])(i,o,r,!1,null,"f9990ca4",null);e["default"]=s.exports},2043:function(t,e,a){},3446:function(t,e,a){"use strict";a.d(e,"a",function(){return u});var o=a("d225"),r=a("308d"),c=a("6bb5"),n=a("013f"),i=a("4e2b"),l=a("bd86"),s=a("73b5"),u=function(t){function e(){var t,a;Object(o["a"])(this,e);for(var i=arguments.length,s=new Array(i),u=0;u<i;u++)s[u]=arguments[u];return a=Object(r["a"])(this,(t=Object(c["a"])(e)).call.apply(t,[this].concat(s))),Object(l["a"])(Object(n["a"])(a),"id",0),Object(l["a"])(Object(n["a"])(a),"type",0),Object(l["a"])(Object(n["a"])(a),"inventory_id",0),Object(l["a"])(Object(n["a"])(a),"inventory_tid",0),Object(l["a"])(Object(n["a"])(a),"company_id",0),Object(l["a"])(Object(n["a"])(a),"stock_entry_id",0),Object(l["a"])(Object(n["a"])(a),"happen_date",""),Object(l["a"])(Object(n["a"])(a),"status",0),Object(l["a"])(Object(n["a"])(a),"materialId",0),Object(l["a"])(Object(n["a"])(a),"materialCode",""),Object(l["a"])(Object(n["a"])(a),"materialName",""),Object(l["a"])(Object(n["a"])(a),"materialCategoryName",""),Object(l["a"])(Object(n["a"])(a),"materialUnitName",""),Object(l["a"])(Object(n["a"])(a),"warehouseId",0),Object(l["a"])(Object(n["a"])(a),"reservoirId",0),Object(l["a"])(Object(n["a"])(a),"stockId",0),Object(l["a"])(Object(n["a"])(a),"warehouseName",""),Object(l["a"])(Object(n["a"])(a),"reservoirName",""),Object(l["a"])(Object(n["a"])(a),"stockName",""),Object(l["a"])(Object(n["a"])(a),"warehouseTid",0),Object(l["a"])(Object(n["a"])(a),"reservoirTid",0),Object(l["a"])(Object(n["a"])(a),"stockTid",0),Object(l["a"])(Object(n["a"])(a),"warehousNameTo",""),Object(l["a"])(Object(n["a"])(a),"reservoirNameTo",""),Object(l["a"])(Object(n["a"])(a),"stockNameTo",""),Object(l["a"])(Object(n["a"])(a),"planQuantity",0),Object(l["a"])(Object(n["a"])(a),"quantity",0),Object(l["a"])(Object(n["a"])(a),"deliveryId",0),Object(l["a"])(Object(n["a"])(a),"stockEntryNumber",""),Object(l["a"])(Object(n["a"])(a),"stockEntryOrderNumber",""),Object(l["a"])(Object(n["a"])(a),"stockTransNumber",""),Object(l["a"])(Object(n["a"])(a),"stockTransOrderNumber",""),a}return Object(i["a"])(e,t),e}(s["a"])},3492:function(t,e,a){},"404f":function(t,e,a){"use strict";var o=a("2043"),r=a.n(o);r.a},"52b6":function(t,e,a){"use strict";var o=a("3492"),r=a.n(o);r.a},"73b5":function(t,e,a){"use strict";a.d(e,"a",function(){return c});var o=a("d225"),r=a("bd86"),c=(a("5701"),function t(){Object(o["a"])(this,t),Object(r["a"])(this,"createUid",0),Object(r["a"])(this,"updateUid",0),Object(r["a"])(this,"createTime",""),Object(r["a"])(this,"updateTime",""),Object(r["a"])(this,"createUserName",""),Object(r["a"])(this,"updateUserName","")})},d545:function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"StockInLogView flex-box flex-column"},[a("search-card-layout",{on:{"click-search":t.refreshData}},[a("div",{staticClass:"flex-box search-card"},[a("div",{},[a("span",[t._v("入库单号")]),a("el-input",{model:{value:t.filterForm.stockEntryNumber,callback:function(e){t.$set(t.filterForm,"stockEntryNumber",e)},expression:"filterForm.stockEntryNumber"}})],1),a("div",{},[a("span",[t._v("订单号")]),a("el-input",{model:{value:t.filterForm.stockEntryOrderNumber,callback:function(e){t.$set(t.filterForm,"stockEntryOrderNumber",e)},expression:"filterForm.stockEntryOrderNumber"}})],1),a("div",{},[a("span",[t._v("物料编号")]),a("el-input",{model:{value:t.filterForm.materialCode,callback:function(e){t.$set(t.filterForm,"materialCode",e)},expression:"filterForm.materialCode"}})],1),a("div",{},[a("span",[t._v("物料名称")]),a("el-input",{model:{value:t.filterForm.materialName,callback:function(e){t.$set(t.filterForm,"materialName",e)},expression:"filterForm.materialName"}})],1)])]),a("div",{staticClass:"flex-grow"},[a("table-panel",{attrs:{loading:t.loading,data:t.data,paginate:t.paginate,"operate-width":"80",selectedRow:t.selectedRow,"create-user-show":!0,"update-user-show":!0,"operate-show":!1,"show-selection":!1,"show-all-warehouse":!0},on:{"update:selectedRow":function(e){t.selectedRow=e},"update:selected-row":function(e){t.selectedRow=e},"sort-change":t.sortChange},scopedSlots:t._u([{key:"operate",fn:function(e){var o=e.row;return[a("el-button-mini",{attrs:{type:"text",icon:"edit"},nativeOn:{click:function(e){return t.clickRowEditButton(o)}}}),a("el-button-mini",{attrs:{type:"text",icon:"delete"},nativeOn:{click:function(e){return t.clickRowDeleteButton(o)}}})]}}])},[a("el-table-column",{attrs:{prop:"id",label:"ID",sortable:"custom",width:"80"}}),1===t.type?a("el-table-column",{attrs:{prop:"stk_e.number",label:"入库单号",sortable:"custom",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[t._v(t._s(a.stockEntryNumber))]}}],null,!1,2013095176)}):t._e(),2===t.type?a("el-table-column",{attrs:{prop:"stk_ts.number",label:"出库单号",sortable:"custom",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[t._v(t._s(a.stockEntryNumber))]}}],null,!1,2013095176)}):t._e(),a("el-table-column",{attrs:{prop:"stk_e.order_number",label:"订单号",sortable:"custom",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[t._v(t._s(a.stockEntryOrderNumber))]}}])}),a("el-table-column",{attrs:{prop:"materialId",label:"物料编号",sortable:"custom",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[t._v(t._s(a.materialCode))]}}])}),a("el-table-column",{attrs:{prop:"materialId",label:"物料名称",sortable:"custom",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[t._v(t._s(a.materialName))]}}])}),a("el-table-column",{attrs:{prop:"materialId",label:"物料名称",sortable:"custom",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[t._v(t._s(a.materialName))]}}])}),a("el-table-column",{attrs:{prop:"planQuantity",label:"计划数量",sortable:"custom",width:"100"}}),a("el-table-column",{attrs:{prop:"planQuantity",label:"实际数量",sortable:"custom",width:"100"}}),a("el-table-column",{attrs:{prop:"materialUnitName",label:"单位",width:"80"}}),a("el-table-column",{attrs:{prop:"approveUid",label:"审批人",sortable:"custom",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){var a=e.row;return[t._v(t._s(a.approveUserName))]}}])}),a("el-table-column",{attrs:{prop:"approveTime",label:"审批时间",sortable:"custom",width:"140"}})],1)],1)],1)},r=[],c=a("cebc"),n=a("b4d6"),i=a("3446"),l=a("7f0d"),s=a("1812"),u=a("41a2"),d=a("9960"),b=a("461d"),m={name:"StockInLogView",components:{TablePanel:b["a"],ElButtonMini:d["a"],SearchCardLayout:n["a"]},props:{type:{default:1}},data:function(){return{loading:!1,showAddDialog:!1,filterForm:new i["a"],data:[],paginate:new l["a"](this.refreshData),selectedRow:[],sortingColumn:null,editId:0}},mounted:function(){this.reload()},methods:{reload:function(){this.refreshData()},refreshData:function(){var t=this;this.loading=!0;var e=1===this.type?s["a"].move.stockInList:s["a"].move.stockOutList;console.log(this.sortingColumn),this.$ajax.request(e,Object(c["a"])({},u["a"].getSortField(this.sortingColumn,"t"),this.paginate.getJSON(),this.filterForm)).then(function(e){t.data=e.list,t.paginate.setPaginate(e)}).finally(function(){return t.loading=!1})},sortChange:function(t){this.sortingColumn=t,this.refreshData()},clickAddButton:function(){this.editId=0,this.showAddDialog=!0},clickEditButton:function(){this.editId=this.selectedRow[0].id,this.showAddDialog=!0},clickDeleteButton:function(){this.delete(this.selectedRow)},clickRowEditButton:function(t){this.editId=t.id,this.showAddDialog=!0},clickRowDeleteButton:function(t){this.delete([t])}},watch:{},computed:{typeText:function(){return 1===this.type?"入库":"出库"}}},f=m,p=(a("404f"),a("2877")),O=Object(p["a"])(f,o,r,!1,null,"5b1ca1e1",null);e["default"]=O.exports}}]);
//# sourceMappingURL=chunk-1d0e0b00.66526893.js.map