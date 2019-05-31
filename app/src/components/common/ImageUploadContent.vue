<template>
    <div class="ImageUploadContent">
        <div class=""
             style="overflow-x: hidden;overflow-y: auto;"
             :style="containerStyle"
        >
            <div class="mr-xs pos-r disp-ib mb-xs border--silver overflow-h"
                 style="border-width: 1px;border-style: solid;"
                 :style="imageContainerStyle"
                 v-for="(item,i) in imageList"
                 :key="i"
            >
                <div class="close-button"
                     @click="removeThumb(i)"
                ><i class="iconfont icon-times"></i></div>
                <img :src="item" alt="" class="wh100p">
            </div>
            <div class="mr-xs pos-r disp-ib mb-xs border--silver overflow-h color-silver  fz-xxg cursor-p add-button "
                 style="border-width: 1px;border-style: solid;"
                 :style="imageContainerStyle"
                 @click="startCropper"

            >
                <div class="flex-box flex-center flex-center-j h100p ">
                    <i class="iconfont icon-plus "></i>
                </div>
            </div>
        </div>
        <image-cropper
                ref="imageCropper"
                :show-button="false"
                @finish="finishUpdate"
                :fixed-number="fixedNumber"
        ></image-cropper>
    </div>
</template>

<script>
    import ImageCropper from "@/components/ImageCropper";
    import ImageModel from "@/project/model/ImageModel";

    export default {
        name: "ImageUploadContent",
        components: {ImageCropper},
        props: {
            imageSize: {
                default: "120px"
            },
            containerHeight: {
                default: "300px"
            },
            fixedNumber: {
                default() {
                    return [1, 1];
                }
            },
            list : {
                default(){
                    return []
                }
            }
        },
        data() {
            return {
                imageList: [],
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
                'a'.toLowerCase()
            },
            removeThumb(index) {
                this.imageList.splice(index, 1);
            },
            startCropper() {
                this.$refs.imageCropper.start();
            },
            finishUpdate(url) {
                ImageModel.updateImage(url)
                    .then( resp => {
                        this.imageList.push(resp.full_url);
                    });
            },
        },
        watch: {
            imageList(v) {
                this.$emit('change', v);
            },
            list(v){
                this.imageList = v;
            }
        },
        computed: {
            imageContainerStyle() {
                return {
                    'width': this.imageSize,
                    'height': this.imageSize,
                }
            },
            containerStyle() {
                return {
                    'height': this.containerHeight
                }
            }
        },

    }
</script>

<style lang="less" scoped>
    @import (reference) "~style/all.less";

    .ImageUploadContent {
        .close-button {
            position: absolute;
            top: 0;
            right: 0;
            width: 15px;
            height: 15px;
            .bg-red;
            color: #fff;
            text-align: center;
            line-height: 15px;
            font-size: 10px;
            cursor: pointer;
        }

        .add-button {
            &:hover {
                border-color: @color--gray;
                color: @color--gray;
            }
        }
    }
</style>