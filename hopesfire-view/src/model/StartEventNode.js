import {Graph} from '@antv/x6'

export const StartEvent = Graph.registerNode('startEvent', {
    inherit: 'ellipse',
    width: 80,
    height: 40,
    attrs: {
        body: {
            fill: '#efdbff',
            stroke: '#efdbff'
        },
        text: {
            text: '开始',
            fill: '#000000'
        }
    },
    markup: [
        {
            tagName: 'ellipse',
            selector: 'body'           //对应attrs中的body属性
        },
        {
            tagName: 'text',
            selector: 'text'          //对应attrs中的text属性
        }
    ],
    ports: {
        groups: {
            top: {
                position: 'top',
                attrs: {
                    circle: {
                        r: 3,
                        magnet: true,
                        stroke: '#5F95FF',
                        fill: '#fff',
                        style: {
                            visibility: 'hidden'
                        }
                    }
                },
            },
            right: {
                position: 'right',
                attrs: {
                    circle: {
                        r: 3,
                        magnet: true,
                        stroke: '#5F95FF',
                        fill: '#fff',
                        style: {
                            visibility: 'hidden'
                        }
                    },
                },
            },
            bottom: {
                position: 'bottom',
                attrs: {
                    circle: {
                        r: 3,
                        magnet: true,
                        stroke: '#5F95FF',
                        fill: '#fff',
                        style: {
                            visibility: 'hidden'
                        }
                    }
                }
            },
            left: {
                position: 'left',
                attrs: {
                    circle: {
                        r: 3,
                        magnet: true,
                        stroke: '#5F95FF',
                        fill: '#fff',
                        style: {
                            visibility: 'hidden'
                        }
                    }
                }
            }
        },
        items: [
            {
                group: 'top',
            },
            {
                group: 'right',
            },
            {
                group: 'bottom',
            },
            {
                group: 'left',
            },
        ]
    },
    data: {
        nodeId: null,
        nodeName: null,
        editable: true
    }
})

