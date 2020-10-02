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
                <Button type="error" size="small" @click="deleteAttach(row)">删除</Button>
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
                            multiple
                            action="/ctl/attach/upload"
                            name="files"
                            :with-credentials="true"
                            :data="attachAttributes"
                            :show-upload-list="false"
                            :before-upload="beforeUploadAttach"
                            :on-success="uploadAttachSuccess"
                            :on-error="uploadAttachError">
                        <Button icon="ios-cloud-upload" type="primary">添加附件</Button>
                    </Upload>
                    <Tag v-for="(item, index) in attachAttributes.files"
                         :name="index"
                         closable @on-close="closeAttachTag">
                        {{item.name}}
                    </Tag>
                </FormItem>
                <FormItem :label-width="0" style="text-align: center">
                    <Button @click="saveAttach()" type="primary">保存</Button>
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

    import attach from '../../../api/attach'

    export default {
        name: "attaches",
        data() {
            return {
                cropperModel: false,
                cropperImg: null,
                attachTableHeader: [
                    {
                        title: "编号",
                        key: 'id'
                    },
                    {
                        title: '附件名称',
                        key: 'originName'
                    },
                    {
                        title: '附件类型',
                        key: 'attachType'
                    },
                    {
                        title: '附件URL',
                        key: 'fileUrl'
                    },
                    {
                        title: '附件路径',
                        key: 'filePath'
                    },
                    {
                        title: '缩略图URL',
                        key: 'thumbnailUrl'
                    },
                    {
                        title: '创建时间',
                        key: 'createTime'
                    },
                    {
                        title: '更新时间',
                        key: 'updateTime'
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        fixed: 'right',
                        align: 'center'
                    }
                ],
                attachTable: [],
                attachAttributes: {
                    attachType: 1,
                    files: []
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
                    },
                    {
                        value: 3,
                        label: '文章标题图'
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
            saveAttach() {
                for (let i = 0; i < this.attachAttributes.files.length; i++) {
                    let item = this.attachAttributes.files[i];
                    this.$refs.uploadAttach.post(item);
                }
                this.attachAttributes.files = []
            },
            closeAttachTag(event, name) {
                let files = this.attachAttributes.files;
                const index = files.indexOf(name);
                files.splice(index, 1);
            },
            beforeUploadAttach(files) {
                this.attachAttributes.files.push(files);
                return false;
            },
            uploadAttachSuccess(response) {
                if (response.code === 200) {
                    this.$Message.info("上传成功！");
                } else {
                    this.$Message.error("上传失败，请联系管理员！");
                }
            },
            uploadAttachError(error) {
                this.$Message.error("上传失败，请联系管理员！");
            },
            findAttaches() {
                attach.doFindAttaches(this, {}).then(res => {
                    this.attachTable = res.records;
                })
            },
            deleteAttach(row) {
                this.$Modal.confirm({
                    title: '确认删除',
                    content: '请问您确认要删除这条记录吗？',
                    closable: true,
                    okText: '删除',
                    onOk: () => {
                        attach.doDeleteAttach(this, {
                            id: row.id
                        }).then(() => {
                            this.findAttaches();
                        });
                    }
                });
            }
        },
        mounted() {
            this.findAttaches();
        }
    }
</script>

<style scoped>

</style>
