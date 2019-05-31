<template>
    <div class="ImageCropper disp-ib">

        <input type="file" ref="file" v-show="false" @change="fileChange"></input>
        <el-button
                type="primary"
                @click="start"
                v-if="showButton"
                :icon="buttonIcon"
                :disabled="buttonDisabled"
        >{{ buttonText }}
        </el-button>
        <el-dialog
                :visible.sync="visible"
                width="540px"
                :modal-append-to-body="true"
                :append-to-body="true"
                :close-on-click-modal="false"
        >
            <div class="mt-sm" style="height: 500px; ">
                <vue-cropper
                        ref="cropper"
                        :img="image"
                        :max-img-size="600"
                        output-type="png"
                        :auto-crop="autoCrop"
                        :fixed="fixed"
                        :fixed-number="fixedNumber"
                ></vue-cropper>
            </div>
            <div class="text-center mt-md">
                <el-button-submit
                        class="disp-ib"
                        :is-block="false"
                        @click.native="clickFinishCropper"
                >完成截图选择
                </el-button-submit>
            </div>

        </el-dialog>
    </div>
</template>

<script>
    import ElButtonSubmit from "@/components/common/button/ElButtonSubmit";
    import ImageUtil from "@/util/ImageUtil";
    import DialogUtil from "@/util/DialogUtil";

    export default {
        name: "ImageCropper",
        components: {ElButtonSubmit},
        props: {
            showButton: {
                default: true
            },
            buttonText: {
                default: '选择图片'
            },
            buttonIcon: {
                default: 'iconfont icon-plus'
            },
            buttonDisabled: {
                default: false
            },
            fixed: {
                default: true,
            },
            fixedNumber: {
                default() {
                    return [1, 1]
                }
            },
            autoCrop: {
                default : true
            }

        },
        data() {
            return {
                visible: false,
                image: this.img,
            }
        },
        mounted() {
            this.reload();
        },
        methods: {
            reload() {
            },
            start() {
                this.$refs.file.click()
            },
            fileChange(event) {
                ImageUtil.fileInputChange(event)
                    .then(img => {
                        this.visible = true;
                        this.image = img;
                    }).catch(err => DialogUtil.toastWarning(err))
                    .finally(() => this.$refs.file.value = '');
            },
            clickFinishCropper() {
                this.$refs.cropper.getCropData(data => {
                    this.visible = false;
                    this.$emit('finish', data);
                })
            },
        },
        watch: {},
        computed: {},

    }
</script>

<style lang="less" scoped>
    @import "~style/var.less";

    .ImageCropper {
    }
</style>