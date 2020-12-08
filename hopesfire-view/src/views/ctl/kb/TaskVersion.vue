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

    export default {
        name: "TaskVersion",
        data() {
            return {
                graphData: {
                    nodes: [],
                    edges: []
                }
            }
        },
        methods: {},
        mounted() {
            //创建画布
            const graph = new Graph({
                container: document.getElementById('graph_layout'),
                width: 1024,
                height: 722,
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
                    showNodeSelectionBox: true  //选中时边框
                },
                interacting: true,              //开启可交互
                connecting: {
                    snap: true,                 //自动吸附
                    dangling: false,            //边的起点和终点只能是节点或连接桩
                    highlight: true            //拖动边是高亮显示可用的节点或连接桩
                }
            })
            //画布拖拽
            const stencil = new Addon.Stencil({
                title: '组件库',
                target: graph,
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
            document.getElementById("stencil_layout").appendChild(stencil.container)
            //自定义节点
            const startEvent = new StartEvent();
            const fillSlot = new FillSlot();
            const invokeApi = new InvokeApi();
            const replyAnswer = new ReplyAnswer();
            const endEvent = new EndEvent();
            //挂在模板节点
            stencil.load([
                startEvent, fillSlot, invokeApi, replyAnswer, endEvent
            ], 'taskFlow')
            //绑定快捷键
            graph.bindKey("meta + c", () => {
                const cells = graph.getSelectedCells();
                if (cells.length) {
                    graph.copy(cells)
                }
                return false
            })
            graph.bindKey("meta + v", () => {
                if (!graph.isClipboardEmpty()) {
                    const cells = graph.paste({offset: 32})
                    graph.cleanSelection()
                    graph.select(cells)
                }
                return false
            })
            graph.bindKey('backspace', () => {
                const cells = graph.getSelectedCells()
                if (cells.length) {
                    graph.removeCells(cells)
                }
                return false
            })
            //加载数据
            graph.fromJSON(this.graphData)
        }
    }
</script>

<style scoped>
    #task_layout {
        border: 1px solid red;
        width: 100%;
        height: 100%;
        display: flex;
        padding: 0;
    }

    #stencil_layout {
        width: 200px;
        height: 722px;
        position: relative;
    }

    #graph_layout {
        flex: 1;
        margin-left: 8px;
        margin-right: 8px;
        box-shadow: 0 0 10px 1px #e9e9e9;
    }


</style>