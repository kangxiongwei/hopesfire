<template>
    <div id="task_layout" style="width: 100%">
        <Row style="position: absolute; left: 130px; top: 0; z-index: 100">
            <Button type="warning" size="small" icon="md-undo" @click="undoGraph">撤销</Button>
            <Button type="warning" size="small" icon="md-redo" @click="redoGraph">恢复</Button>
            <Button type="warning" size="small" icon="md-grid" @click="zoomFitGraph">自适应</Button>
            <Button type="warning" size="small" icon="md-expand" @click="zoomOutGraph">放大</Button>
            <Button type="warning" size="small" icon="md-contract" @click="zoomInGraph">缩小</Button>
        </Row>
        <Row style="position: absolute; right: 10px; top: 0; z-index: 100">
            <Button type="primary" size="small">检测</Button>
            <Button type="primary" size="small" @click="addTaskVersion">保存</Button>
            <Button type="primary" size="small">取消</Button>
        </Row>
        <div id="stencil_layout"></div>
        <div id="graph_layout"></div>
        <Drawer v-model="nodeData.editable"
                :title="nodeData.nodeName + '[' + nodeData.nodeId + ']'"
                :closable="true" :width="30"
                class-name="graph_drawer">
            <Form ref="nodeForm" :model="nodeData" :label-width="80" label-position="right">
                <FormItem label="节点名称" prop="nodeName">
                    <Input type="text" v-model="nodeData.nodeName" placeholder="请输入节点名称"/>
                </FormItem>
            </Form>
        </Drawer>
    </div>
</template>

<script>
    import {Addon, Graph} from '@antv/x6'
    import {StartEvent} from "../../../model/StartEventNode";
    import {FillSlot} from "../../../model/FillSlotNode";
    import {InvokeApi} from "../../../model/InvokeApiNode";
    import {ReplyAnswer} from "../../../model/ReplyAnswerNode";
    import {EndEvent} from "../../../model/EndEvent";
    import {GraphEdge} from "../../../model/GraphEdge";

    export default {
        name: "TaskVersion",
        data() {
            return {
                graphData: {},
                graph: null,
                stencil: null,
                nodes: [],
                nodeData: {}
            }
        },
        methods: {
            /**
             * 创建画布
             */
            initGraph() {
                this.graph = new Graph({
                    container: document.getElementById('graph_layout'),
                    width: 1024,
                    height: 740,
                    scroller: {
                        enabled: false,
                        pannable: true,
                        width: 1300,
                        height: 740
                    },
                    snapline: true,
                    background: {
                        color: '#fffbe6', // 设置画布背景颜色
                    },
                    grid: {
                        size: 10,      //网格大小 10px
                        visible: true  //渲染网格背景
                    },
                    keyboard: true,     //键盘快捷键
                    clipboard: true,    //剪切板
                    history: true,      //撤销、重做功能
                    selecting: {                     //选择器
                        enabled: true,               //启用选择器
                        rubberband: true,            // 启用框选
                        showNodeSelectionBox: true,  //选中时边框
                        anchor: 'center',            //锚点
                        connectionPoint: 'anchor'    //连接点
                    },
                    interacting: true,              //开启可交互
                    connecting: {
                        snap: true,                 //自动吸附
                        dangling: false,            //边的起点和终点只能是节点或连接桩
                        highlight: true,            //拖动边是高亮显示可用的节点或连接桩
                        createEdge() {
                            return new GraphEdge(); //创建边
                        }
                    }
                })
            },
            initTools() {
            },
            /**
             * 初始化节点
             */
            initNodes() {
                this.nodes = [];
                const startEvent = new StartEvent();
                const fillSlot = new FillSlot();
                const invokeApi = new InvokeApi();
                const replyAnswer = new ReplyAnswer();
                const endEvent = new EndEvent();
                this.nodes.push(startEvent, fillSlot, invokeApi, replyAnswer, endEvent);
            },
            /**
             * 初始化组件库
             */
            initStencil() {
                let stencilLayout = document.getElementById("stencil_layout");
                this.stencil = new Addon.Stencil({
                    title: '组件库',
                    target: this.graph,
                    stencilGraphWidth: 200,
                    stencilGraphHeight: 600,
                    layoutOptions: {
                        columns: 1
                    },
                    groups: [
                        {
                            name: 'taskFlow',
                            title: '组件库',
                            collapsable: false
                        }
                    ]
                });
                //画布拖拽
                stencilLayout.appendChild(this.stencil.container);
                this.stencil.load(this.nodes, 'taskFlow')
            },
            /**
             * 绑定事件
             */
            initEvents() {
                const graphLayout = document.getElementById("graph_layout");
                this.graph.bindKey("meta + c", () => {
                    const cells = this.graph.getSelectedCells();
                    if (cells.length) {
                        this.graph.copy(cells)
                    }
                    return false
                })
                this.graph.bindKey("meta + v", () => {
                    if (!this.graph.isClipboardEmpty()) {
                        const cells = this.graph.paste({offset: 32})
                        this.graph.cleanSelection()
                        this.graph.select(cells)
                    }
                    return false
                })
                this.graph.bindKey('backspace', () => {
                    const cells = this.graph.getSelectedCells()
                    if (cells.length) {
                        this.graph.removeCells(cells)
                    }
                    return false
                })
                this.graph.on('node:mouseenter', () => {
                    const ports = graphLayout.querySelectorAll('.x6-port-body');
                    this.showPorts(ports, true)
                })
                this.graph.on('node:mouseleave', () => {
                    const ports = graphLayout.querySelectorAll('.x6-port-body');
                    this.showPorts(ports, false)
                })
            },

            /**
             * 初始化选中节点时的操作
             */
            initSelectNode() {
                this.graph.on('node:selected', (e) => {
                    const node = e.node;
                    this.nodeData = node.data;
                    this.nodeData.nodeId = node.id;
                    this.nodeData.nodeName = node.attr('text/text');
                })
                this.graph.on('node:unselected', (e) => {
                    const node = e.node;
                    node.setData(this.nodeData);
                    node.data.editable = true;
                    node.attr('text/text', this.nodeData.nodeName)
                    this.nodeData = {}
                })
            },

            /**
             * 是否显示连接点
             * @param ports
             * @param show
             */
            showPorts(ports, show) {
                for (let i = 0, len = ports.length; i < len; i = i + 1) {
                    ports[i].style.visibility = show ? 'visible' : 'hidden'
                }
            },

            undoGraph() {
                this.graph.history.undo();
            },

            redoGraph() {
                this.graph.history.redo();
            },

            zoomInGraph() {
                this.graph.zoom(-0.1)
            },

            zoomOutGraph() {
                this.graph.zoom(0.1)
            },

            zoomFitGraph() {
                this.graph.zoomToFit();
            },

            /**
             * 添加版本
             */
            addTaskVersion() {
                let graphData = this.graph.toJSON();
                console.log(JSON.stringify(graphData));
            }


        },
        mounted() {
            this.initGraph();
            this.initTools();
            this.initNodes();
            this.initStencil();
            this.initEvents();
            this.initSelectNode();
        }
    }
</script>

<style scoped>
    #task_layout {
        width: 100%;
        height: 100%;
        display: flex;
        padding: 0;
    }

    #stencil_layout {
        width: 112px;
        height: 740px;
        position: relative;
    }

    #graph_layout {
        flex: 1;
        margin-left: 8px;
        margin-right: 8px;
    }

</style>