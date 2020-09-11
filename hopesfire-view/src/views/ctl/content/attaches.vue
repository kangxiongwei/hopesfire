<template>
    <div>
        <Row type="flex" align="middle">
            <Col :span="22">
                <Form inline style="height: 34px; line-height: 34px">
                    <FormItem></FormItem>
                </Form>
            </Col>
            <Col :span="2">
                <Button type="primary" @click="addAttach">添加附件</Button>
            </Col>
        </Row>
        <Table stripe border size="small" :columns="attachTableHeader" :data="attachTable">
            <template slot-scope="{row}" slot="action">
                <Button type="primary" size="small" @click="">编辑</Button>
                <Button type="error" size="small" @click="">删除</Button>
            </template>
        </Table>
        <Drawer title="上传附件" :closable="true" :width="40" v-model="saveAttachDrawer">
            <Form ref="attachForm" :model="attachAttributes" :label-width="70" label-position="left">
                <FormItem label="附件类型" prop="attachType">
                    <Select v-model="attachAttributes.attachType" style="width: 50%">
                        <Option v-for="item in attachTypes" :value="item.value" :label="item.label"></Option>
                    </Select>
                </FormItem>
                <FormItem label="附件内容">
                    <Upload ref="uploadAttach"
                            style="height: 34px; line-height: 34px;"
                            action="/ctl/attach/upload"
                            name="files"
                            :with-credentials="true"
                            :data="attachAttributes"
                            :show-upload-list="false"
                            :before-upload="beforeUploadAttach"
                            :on-success="uploadAttachSuccess"
                            :on-error="uploadAttachError"
                            :on-remove="deleteUploadAttach">
                        <Button icon="ios-cloud-upload" type="primary">上传附件</Button>
                    </Upload>
                </FormItem>
            </Form>
        </Drawer>



        <!--<Upload paste name="files" action="" :before-upload="beforeUploadAttaches">
            <Button icon="ios-cloud-upload" type="primary">上传附件</Button>
        </Upload>-->
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
                cropperImg: null,
                attachTableHeader: [

                ],
                attachTable: [

                ],
                attachAttributes: {
                    attachType: 1
                },
                saveAttachDrawer: false,
                attachTypes: [
                    {
                        value: 1,
                        label: '首页轮播图'
                    },
                    {
                        value: 2,
                        label: '用户头像图'
                    }
                ]
            }
        },
        methods: {
            beforeUploadAttaches(files) {
                let data = window.URL.createObjectURL(new Blob([files]));
                this.cropperImg = data;
                this.cropperModel = true;
                return false
            },
            addAttach() {
                this.saveAttachDrawer = true
            },
            beforeUploadAttach(files) {

            },
            uploadAttachSuccess(response) {
                if (response.code === 200) {
                    this.$Message.info("上传成功！");
                } else {
                    this.$Message.error("上传失败，请联系管理员！");
                }
            },
            uploadAttachError(error) {

            },
            deleteUploadAttach(files) {
                console.log("删除文件" + JSON.stringify(files))
            }
        }
    }
</script>

<style scoped>

</style>
