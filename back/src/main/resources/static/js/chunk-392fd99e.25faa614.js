(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-392fd99e"],{3353:function(t,e,i){"use strict";i.r(e);var n=function(){var t=this,e=t.$createElement,i=t._self._c||e;return i("div",{staticClass:"StockEntryManagerView flex-box flex-column"},[i("search-card-layout",{attrs:{loading:t.loading},on:{"click-search":t.refreshData}},[i("div",{staticClass:"flex-box search-card"},[i("div",{},[i("span",[t._v(t._s(t.titleText)+"单号")]),i("el-input",{model:{value:t.filterForm.number,callback:function(e){t.$set(t.filterForm,"number",e)},expression:"filterForm.number"}})],1),i("div",{},[i("span",[t._v("订单号")]),i("el-input",{model:{value:t.filterForm.orderNumber,callback:function(e){t.$set(t.filterForm,"orderNumber",e)},expression:"filterForm.orderNumber"}})],1),i("div",{},[i("span",[t._v("公司名称")]),i("el-input",{model:{value:t.filterForm.companyName,callback:function(e){t.$set(t.filterForm,"companyName",e)},expression:"filterForm.companyName"}})],1),i("div",{staticStyle:{width:"200px"}},[i("span",[t._v(t._s(t.titleText)+"单分类")]),i("category-selector",{attrs:{type:t.categoryType,"is-filter":!0},model:{value:t.filterForm.categoryId,callback:function(e){t.$set(t.filterForm,"categoryId",e)},expression:"filterForm.categoryId"}})],1),i("div",{staticStyle:{width:"200px"}},[i("span",[t._v("审批状态")]),i("approve-selector",{attrs:{"is-filter":!0,"is-stock-in":1===t.type},model:{value:t.filterForm.status,callback:function(e){t.$set(t.filterForm,"status",e)},expression:"filterForm.status"}})],1),i("div",{},[i("span",[t._v("创建时间")]),i("date-time-range-input",{attrs:{"start-time":t.filterForm.startTime,"end-time":t.filterForm.endTime},on:{"update:startTime":function(e){return t.$set(t.filterForm,"startTime",e)},"update:start-time":function(e){return t.$set(t.filterForm,"startTime",e)},"update:endTime":function(e){return t.$set(t.filterForm,"endTime",e)},"update:end-time":function(e){return t.$set(t.filterForm,"endTime",e)}}})],1)]),i("el-button-curd-group",{attrs:{slot:"buttons","delete-disabled":!t.selectedRow.length,"edit-disabled":1!==t.selectedRow.length},on:{"click-add":t.clickAddButton,"click-edit":t.clickEditButton,"click-delete":t.clickDeleteButton},slot:"buttons"},[i("el-button-group",{staticClass:"ml-xs"},[i("el-button-mini",{attrs:{icon:"iconfont icon-notes-medical",disabled:!t.submitEnable},nativeOn:{click:function(e){return t.clickSubmitButton(e)}}},[t._v("提交审核\n                ")]),i("el-button-mini",{attrs:{icon:"iconfont icon-check-circle",disabled:!t.approveEnable},nativeOn:{click:function(e){return t.clickApproveButton(e)}}},[t._v("审批通过\n                ")]),i("el-button-mini",{attrs:{icon:"iconfont icon-times-circle","icon-color-class":"color-red",disabled:!t.rejectEnable},nativeOn:{click:function(e){return t.clickRejectButton(e)}}},[t._v("驳回\n                ")]),1===t.type?i("el-button-mini",{attrs:{icon:"iconfont icon-circle","icon-color-class":"color-olive",disabled:!t.finishEnable},nativeOn:{click:function(e){return t.clickFinishButton(e)}}},[t._v("订单完成\n                ")]):t._e(),2===t.type?i("el-button-mini",{attrs:{icon:"iconfont icon-truck","icon-color-class":"color-olive",disabled:!t.finishEnable},nativeOn:{click:function(e){return t.clickDeliveryButton(e)}}},[t._v("完成发货\n                ")]):t._e()],1)],1)],1),i("div",{staticClass:"flex-grow"},[i("table-panel",{attrs:{loading:t.loading,data:t.data,paginate:t.paginate,"operate-width":"140",selectedRow:t.selectedRow,"create-user-show":!0,"update-user-show":!0},on:{"update:selectedRow":function(e){t.selectedRow=e},"update:selected-row":function(e){t.selectedRow=e},"sort-change":t.sortChange},scopedSlots:t._u([{key:"operate",fn:function(e){var n=e.row;return[i("el-button-mini",{attrs:{type:"text",icon:"edit",title:"编辑"},nativeOn:{click:function(e){return t.clickRowEditButton(n)}}}),i("el-button-mini",{attrs:{type:"text",title:"预览查看",icon:"iconfont icon-eye1"},nativeOn:{click:function(e){return t.clickRowPreviewButton(n)}}}),1===n.status?i("el-button-mini",{attrs:{type:"text",icon:"delete",title:"删除"},nativeOn:{click:function(e){return t.clickRowDeleteButton(n)}}}):t._e(),[1,4].includes(n.status)&&n.moveCount>0?i("el-button-mini",{attrs:{type:"text",icon:"iconfont icon-notes-medical",title:"提交审批"},nativeOn:{click:function(e){return t.clickRowSubmitButton(n)}}}):t._e(),2===n.status?i("el-button-mini",{attrs:{type:"text",icon:"iconfont icon-check-circle",title:"审批通过"},nativeOn:{click:function(e){return t.clickRowApproveButton(n)}}}):t._e(),2===n.status?i("el-button-mini",{attrs:{type:"text",icon:"iconfont icon-times-circle",title:"驳回"},nativeOn:{click:function(e){return t.clickRowRejectButton(n)}}}):t._e(),3===n.status&&1===t.type?i("el-button-mini",{attrs:{type:"text",icon:"iconfont icon-circle",title:"完成订单"},nativeOn:{click:function(e){return t.clickRowFinishButton(n)}}}):t._e(),3===n.status&&2===t.type?i("el-button-mini",{attrs:{type:"text",icon:"iconfont icon-truck","icon-color-class":"color-olive",title:"完成发货"},nativeOn:{click:function(e){return t.clickRowDeliveryButton(n)}}}):t._e()]}}])},[i("el-table-column",{attrs:{prop:"id",label:"ID",sortable:"custom",width:"80"}}),i("el-table-column",{attrs:{prop:"number",label:t.titleText+"单号",sortable:"custom",width:"180"}}),i("el-table-column",{attrs:{prop:"orderNumber",label:"订单号",sortable:"custom",width:"180"}}),i("el-table-column",{attrs:{prop:"moveCount",label:"明细条数",width:"100"},scopedSlots:t._u([{key:"default",fn:function(e){var n=e.row;return[i("el-button-mini",{attrs:{type:"text",title:"查看和编辑明细",icon:"iconfont icon-th-list"},nativeOn:{click:function(e){return t.clickRowDetailButton(n)}}}),[1,4].includes(n.status)?i("el-button-mini",{attrs:{type:"text",icon:"iconfont icon-plus-circle"},nativeOn:{click:function(e){return t.clickRowAddMoveButton(n)}}}):t._e(),i("span",{staticClass:"ml-xs"},[t._v(t._s(n.moveCount))])]}}])}),i("el-table-column",{attrs:{prop:"status",label:t.titleText+"状态",sortable:"custom",width:"105"},scopedSlots:t._u([{key:"default",fn:function(e){var n=e.row;return i("div",{staticClass:"flex-box flex-center"},[i("approve-tags",{attrs:{status:n.status,"is-stock-in":1===t.type}}),2===n.type&&5===n.status&&0!==n.deliveryId?i("div",{staticStyle:{"padding-top":"2px","margin-left":"2px"}},[i("el-tag",{staticClass:"cursor-p",attrs:{type:"info"}},[i("i",{staticClass:"iconfont icon-truck"})])],1):t._e()],1)}}])}),i("el-table-column",{attrs:{prop:"categoryId",label:t.titleText+"类型",sortable:"custom",width:"120"},scopedSlots:t._u([{key:"default",fn:function(e){var i=e.row;return[t._v(t._s(i.categoryName))]}}])}),i("el-table-column",{attrs:{prop:"companyId",label:"公司名称",sortable:"custom",width:"200"},scopedSlots:t._u([{key:"default",fn:function(e){var i=e.row;return[t._v(t._s(i.companyName))]}}])}),i("el-table-column",{attrs:{prop:"remark",label:"备注",width:"200","show-overflow-tooltip":!0}}),i("el-table-column",{attrs:{prop:"rejectRemark",label:"驳回理由",width:"200","show-overflow-tooltip":!0}})],1)],1),i("stock-entry-add-dialog",{attrs:{visible:t.showAddDialog,"edit-id":t.editId,type:t.type},on:{"update:visible":function(e){t.showAddDialog=e},finish:t.refreshData}}),i("move-list-dialog",{attrs:{visible:t.showMoveListDialog,"stock-entry":t.selectStockEntry,type:t.type},on:{"update:visible":function(e){t.showMoveListDialog=e},close:t.refreshData}}),i("move-add-dialog",{attrs:{visible:t.showMoveAddDialog,"edit-id":0,type:t.type,"stock-entry":t.selectStockEntry},on:{"update:visible":function(e){t.showMoveAddDialog=e},finish:t.refreshData}}),i("stock-entry-preview-dialog",{attrs:{visible:t.showPreviewDialog,"edit-id":t.editId,type:t.type},on:{"update:visible":function(e){t.showPreviewDialog=e}}}),i("stock-entry-reject-dialog",{attrs:{visible:t.showRejectDialog,"stock-entry":t.selectStockEntry,type:t.type},on:{"update:visible":function(e){t.showRejectDialog=e},finish:t.refreshData}}),i("stock-entry-delivery-finish-dialog",{attrs:{visible:t.showDeliveryFinishDialog,"stock-entry":t.selectStockEntry,type:t.type},on:{"update:visible":function(e){t.showDeliveryFinishDialog=e},finish:t.refreshData}})],1)},o=[],c=(i("6762"),i("2fdb"),i("5176")),s=i.n(c),a=i("cebc"),l=i("705a"),r=i("7480"),u=i("7f0d"),d=i("1812"),f=i("41a2"),h=i("b4d6"),p=i("9960"),m=i("461d"),v=i("558e"),b=i("15e2"),w=i("ef7c"),k=i("ee5d"),y=i("3522"),g=i("ec55"),D=i("a992"),R=i("d024"),E=i("f4c8"),x=i("a58e"),S=i("cd0d"),B=i("fc8a"),_={name:"StockEntryManagerView",components:{StockEntryDeliveryFinishDialog:B["a"],StockEntryRejectDialog:S["a"],StockEntryPreviewDialog:x["a"],ApproveTags:E["a"],ApproveSelector:D["a"],MoveAddDialog:g["a"],MoveListDialog:y["a"],StockEntryAddDialog:k["a"],CategorySelector:w["a"],DateTimeRangeInput:b["a"],ElButtonCurdGroup:v["a"],TablePanel:m["a"],ElButtonMini:p["a"],SearchCardLayout:h["a"]},props:{type:{default:1},params:Object},data:function(){return{loading:!1,showAddDialog:!1,showMoveListDialog:!1,showMoveAddDialog:!1,showPreviewDialog:!1,showRejectDialog:!1,showDeliveryFinishDialog:!1,filterForm:new r["a"](this.type),data:[],paginate:new u["a"](this.refreshData),selectedRow:[],sortingColumn:null,editId:0,selectStockEntry:new r["a"],statusEnum:R["a"]}},mounted:function(){f["a"].initParams(this),this.reload()},methods:{reload:function(){this.refreshData()},refreshData:function(){var t=this;this.loading=!0,this.$ajax.request(d["a"].stockEntry.list,Object(a["a"])({},f["a"].getSortField(this.sortingColumn),this.paginate.getJSON(),this.filterForm)).then(function(e){t.data=e.list,t.paginate.setPaginate(e)}).finally(function(){return t.loading=!1})},sortChange:function(t){this.sortingColumn=t,this.refreshData()},clickAddButton:function(){this.editId=0,this.selectStockEntry=this.selectedRow[0],this.showAddDialog=!0},clickEditButton:function(){this.editId=this.selectedRow[0].id,this.showAddDialog=!0},clickDeleteButton:function(){this.delete(this.selectedRow)},clickRowEditButton:function(t){this.editId=t.id,this.showAddDialog=!0},clickRowDeleteButton:function(t){this.delete([t])},clickRowDetailButton:function(t){this.selectStockEntry=t,this.showMoveListDialog=!0},clickRowPreviewButton:function(t){this.editId=t.id,this.showPreviewDialog=!0},clickRowAddMoveButton:function(t){this.editId=0,this.selectStockEntry=t,this.showMoveAddDialog=!0},delete:function(t){var e=this;l["a"].confirm("\n                确定删除以下入库单吗？ <br>\n                [ ".concat(t.map(function(t){return t.number}).join(",")," ]\n            ")).then(function(){return e.$ajax.request(d["a"].stockEntry.delete,{ids:t.map(function(t){return t.id}).join(",")})}).then(function(t){l["a"].toastSuccess(t),e.refreshData()})},clickApproveButton:function(){this.approve(this.selectedRow,!0,"通过审批")},clickRowApproveButton:function(t){this.approve([t],!0,"通过审批")},clickRejectButton:function(){this.selectStockEntry=this.selectedRow[0],this.showRejectDialog=!0},clickRowRejectButton:function(t){this.selectStockEntry=t,this.showRejectDialog=!0},approve:function(t){var e=this,i=!(arguments.length>1&&void 0!==arguments[1])||arguments[1],n=arguments.length>2&&void 0!==arguments[2]?arguments[2]:"通过";l["a"].confirm("\n                确定要将以下单号 ".concat(n," 吗？ </br>\n                [ ").concat(t[0].number," ]\n            ")).then(function(){var n=s()({},t[0]);return n.status=i?3:4,e.$ajax.request(d["a"].stockEntry.approve,n)}).then(function(t){l["a"].toastSuccess(t),e.refreshData()})},clickFinishButton:function(){this.finishSE(this.selectedRow)},clickRowFinishButton:function(t){this.finishSE([t])},finishSE:function(t){var e=this;l["a"].confirm("\n                确定完成一下单号？ </br>\n                [ ".concat(t[0].number," ]\n            ")).then(function(){var i=s()({},t[0]);return i.status=5,e.$ajax.request(d["a"].stockEntry.finish,i)}).then(function(t){l["a"].toastSuccess(t),e.refreshData()})},clickSubmitButton:function(){this.submit(this.selectedRow)},clickRowSubmitButton:function(t){this.submit([t])},submit:function(t){var e=this;l["a"].confirm("\n                确定要将以下订单提交审核吗？ </br>\n                [ ".concat(t[0].number," ]\n            ")).then(function(){return e.$ajax.request(d["a"].stockEntry.submit,t[0])}).then(function(t){l["a"].toastSuccess(t),e.refreshData()})},clickDeliveryButton:function(){this.selectStockEntry=this.selectedRow[0],this.showDeliveryFinishDialog=!0},clickRowDeliveryButton:function(t){this.selectStockEntry=t,this.showDeliveryFinishDialog=!0},delivery:function(t){}},watch:{},computed:{titleText:function(){return 1===this.type?"入库":"出库"},categoryType:function(){return 1===this.type?3:4},submitEnable:function(){return 1===this.selectedRow.length&&[1,4].includes(this.selectedRow[0].status)},approveEnable:function(){return 1===this.selectedRow.length&&2===this.selectedRow[0].status},rejectEnable:function(){return 1===this.selectedRow.length&&2===this.selectedRow[0].status},finishEnable:function(){return 1===this.selectedRow.length&&3===this.selectedRow[0].status}}},F=_,j=(i("fdb0"),i("2877")),A=Object(j["a"])(F,n,o,!1,null,"938e026a",null);e["default"]=A.exports},8535:function(t,e,i){},fdb0:function(t,e,i){"use strict";var n=i("8535"),o=i.n(n);o.a}}]);
//# sourceMappingURL=chunk-392fd99e.25faa614.js.map