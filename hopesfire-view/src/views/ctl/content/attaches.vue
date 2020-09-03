<template>
    <div>
        <Upload paste name="files" action="" :before-upload="beforeUploadAttaches">
            <Button icon="ios-cloud-upload" type="primary">上传附件</Button>
        </Upload>
        <Modal v-model="cropperModel" title="图片裁剪">
            <div style="height: 225px; width: 360px">
                <VueCropper ref="cropper"
                            :img="cropperImg"
                            :outputSize="1"
                            :autoCrop="true"
                            :autoCropWidth="200"
                            :autoCropHeight="200"
                            outputType="png">

                </VueCropper>
            </div>
            <div slot="footer">
                <Button type="success">更换图片</Button>
                <Button type="warning"><span style="font-weight: 600;">↺</span></Button>
                <Button type="warning"><span style="font-weight: 600;">↻</span></Button>
                <Button type="primary">确定</Button>
                <Button type="error">取消</Button>
            </div>
        </Modal>
    </div>
</template>

<script>
    export default {
        name: "attaches",
        data() {
            return {
                cropperModel: false,
                cropperImg: null
            }
        },
        methods: {
            beforeUploadAttaches(files) {
                let data = window.URL.createObjectURL(new Blob([files]));
                this.cropperImg = data;
                this.cropperModel = true;
                return false
            }


        }
    }
</script>

<style scoped>

</style>
