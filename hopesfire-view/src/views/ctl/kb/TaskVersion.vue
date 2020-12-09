<template>
    <div id="task_layout">
        <div id="stencil_layout"></div>
        <div id="graph_layout"></div>
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
                graphData: {
                    nodes: [],
                    edges: []
                },
                graph: null,
                stencil: null,
                nodes: []
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
                    scroller: false,
                    snapline: true,
                    grid: {
                        size: 10,      //网格大小 10px
                        visible: true  //渲染网格背景
                    },
                    keyboard: {
                        enabled: true   //键盘快捷键
                    },
                    clipboard: {
                        enabled: true,  //剪切板
                    },
                    selecting: {        //选择器
                        enabled: true,
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
             * 是否显示连接点
             * @param ports
             * @param show
             */
            showPorts(ports, show) {
                for (let i = 0, len = ports.length; i < len; i = i + 1) {
                    ports[i].style.visibility = show ? 'visible' : 'hidden'
                }
            }
        },
        mounted() {
            this.initGraph();
            this.initNodes();
            this.initStencil();
            this.initEvents();
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
        box-shadow: 0 0 10px 1px #e9e9e9;
    }


</style>