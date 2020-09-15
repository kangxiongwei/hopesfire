<template>
    <div style="width: 100%; height: 100%">
        <Row style="width: 100%; height: calc(100vh - 178px)">
            <Col span="4" style="height: 100%; border-right: 1px solid #e8eaec">
                <Row>
                    <Tree :data="bannerTree" :render="renderBannerTree" @on-select-change="selectBannerTree" @on-contextmenu="handleContextMenu">
                        <template slot="contextMenu">
                            <DropdownItem style="text-align: center; color: #19be6b" @click.native="addBanner">
                                添加
                            </DropdownItem>
                            <DropdownItem style="text-align: center; color: #ed4014" @click.native="dropBanner">
                                删除
                            </DropdownItem>
                        </template>
                    </Tree>
                </Row>
            </Col>
            <Col span="18" :offset="1">
                <Table stripe border :columns="bannerTableHeader" :data="bannerTable" style="margin-top: 10px">
                    <template slot-scope="{row}" slot="action">
                        <Button type="primary" size="small" @click="updateBanner(row)">编辑</Button>
                        <Button type="error" size="small" @click="deleteBanner(row.id)">删除</Button>
                    </template>
                </Table>
            </Col>
        </Row>
        <Drawer v-model="saveBannerModal" :title="saveBannerTitle" width="40" @on-close="resetBanner()">
            <Form ref="bannerForm" :model="bannerForm" label-position="left" :label-width="100" style="width: 500px">
                <FormItem label="栏目名称" prop='banner'>
                    <Input type="text" clearable v-model="bannerForm.banner"></Input>
                </FormItem>
                <FormItem label="栏目类型" prop="bannerType">
                    <Select v-model="bannerForm.bannerType">
                        <Option v-for="(item, index) in bannerTypes" :value="index">{{item}}</Option>
                    </Select>
                </FormItem>
                <FormItem label="父级栏目" prop="parentId">
                    <Input type="text" disabled :value="parentBanner.title"></Input>
                </FormItem>
                <FormItem label="栏目权重" prop="weight">
                    <Input type="number" v-model="bannerForm.weight" placeholder="请输入0~100的数字"></Input>
                </FormItem>
                <FormItem label="状态" prop="status">
                    <i-switch :true-value="1" :false-value="0" v-model="bannerForm.status" size="large">
                        <span slot="open">开</span>
                        <span slot="close">关</span>
                    </i-switch>
                </FormItem>
                <FormItem :label-width="0" style="text-align: center">
                    <Button @click="saveBanner()" type="primary">保存</Button>
                    <Button @click="resetBanner()" type="primary">重置</Button>
                </FormItem>
            </Form>
        </Drawer>
    </div>
</template>

<script>
    import banner from '../../../api/banner'

    export default {
        name: "banner",
        data() {
            return {
                bannerTree: [],
                bannerTable: [],
                bannerTableHeader: [
                    {
                        title: '编号',
                        key: 'id',
                        width: 60
                    },
                    {
                        title: '栏目名称',
                        key: 'banner'
                    },
                    {
                        title: '栏目类型',
                        key: 'bannerType',
                        render: (h, params) => {
                            let bannerType = params.row.bannerType;
                            return h('div', this.bannerTypes[bannerType]);
                        }
                    },
                    {
                        title: '父级栏目',
                        render: (h, params) => {
                            let parent = params.row.parent;
                            return h('div', parent == null ? '无' : parent.banner);
                        }
                    },
                    {
                        title: '权重',
                        key: 'weight'
                    },
                    {
                        title: '状态',
                        key: 'status',
                        render: (h, params) => {
                            return h('i-switch', {
                                props: {
                                    size: 'large',
                                    value: params.row.status,
                                    'true-value': 1,
                                    'false-value': 0
                                },
                                scopedSlots: {
                                    open: () => h('span', '开'),
                                    close: () => h('span', '关')
                                },
                                on: {
                                    'on-change': (value) => {
                                        this.updateBannerStatus(params.row.id, value);
                                    }
                                }
                            });
                        }
                    },
                    {
                        title: '创建时间',
                        key: 'createTime',
                        width: 150
                    },
                    {
                        title: '操作',
                        slot: 'action',
                        width: 150
                    }
                ],
                saveBannerModal: false,
                saveBannerTitle: '',
                bannerForm: {
                    id: null,
                    banner: '',
                    bannerType: 0,
                    status: 0,
                    weight: 0,
                    parent: {
                        id: 0
                    }
                },
                parentBanner: {},
                bannerTypes: []
            }
        },
        methods: {
            listBannerTree() {
                banner.doListBannerTree(this, {
                    parent: {
                        id: 0
                    },
                    sort: 'weight'
                }).then(res => {
                    this.bannerTree = res;
                })
            },
            selectBannerTree(node, banner) {
                if (banner.id > 0) {
                    this.bannerTable = [banner.banner];
                } else {
                    this.bannerTable = []
                }
            },
            listBannerTypes() {
                banner.listBannerTypes().then(res => {
                    this.bannerTypes = [];
                    res.forEach((item) => {
                        this.bannerTypes[item.key] = item.value;
                    })
                })
            },
            updateBannerStatus(id, status) {
                banner.doSaveBanner(this, {
                    id: id,
                    status: status
                })
            },
            addBanner() {
                this.saveBannerModal = true
                this.saveBannerTitle = '增加栏目'
                this.bannerForm.id = null;
            },
            updateBanner(row) {
                this.saveBannerModal = true
                this.saveBannerTitle = '更新栏目'
                this.bannerForm.id = row.id
                this.bannerForm.banner = row.banner;
                this.bannerForm.bannerType = row.bannerType;
                this.bannerForm.weight = row.weight;
                this.bannerForm.status = row.status;
                this.parentBanner.id = row.parent.id;
                this.parentBanner.title = row.parent.banner;
            },
            dropBanner() {
                this.deleteBanner(this.parentBanner.id);
            },
            deleteBanner(id) {
                this.$Modal.confirm({
                    title: '确认删除',
                    content: '该栏目以及子栏目全部会被删除，您确定要删除吗？',
                    closable: true,
                    okText: '删除',
                    onOk: () => {
                        banner.doDeleteBanner(this, {id: id}).then(() => {
                            this.listBannerTree();
                            this.parentBanner = {};
                        });
                    }
                });
            },
            saveBanner() {
                this.bannerForm.parent.id = this.parentBanner.id;
                banner.doSaveBanner(this, this.bannerForm).then(res => {
                    //刷新栏目树
                    this.listBannerTree();
                    //重置表单
                    this.parentBanner = {};
                    this.resetBanner();
                    this.saveBannerModal = false;
                })
            },
            resetBanner() {
                this.$refs['bannerForm'].resetFields();
            },
            handleContextMenu(data) {
                this.parentBanner = data;
            },
            renderBannerTree(h, {root, node, data}) {
                let isLeafNode = data.children == null || data.children.length === 0;
                return h('span', [
                    h('Icon', {
                        props: {
                            type: isLeafNode ? 'ios-paper' : 'ios-folder-open',
                            color: isLeafNode ? '#2d8cf0' : '#ff9900'
                        },
                        style: {
                            marginRight: '8px'
                        }
                    }),
                    h('span', data.title)
                ])
            }
        },
        mounted() {
            this.listBannerTree();
            this.listBannerTypes();
        }
    }
</script>

<style scoped>

</style>
