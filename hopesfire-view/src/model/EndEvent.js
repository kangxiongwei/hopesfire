import {Graph} from '@antv/x6'

export const EndEvent = Graph.registerNode('endEvent', {
    inherit: 'ellipse',
    width: 80,
    height: 40,
    attrs: {
        body: {
            fill: '#efdbff',
            stroke: '#efdbff'
        },
        text: {
            text: '结束',
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
                        fill: '#fff'
                    },
                },
            },
            right: {
                position: 'right',
                attrs: {
                    circle: {
                        r: 3,
                        magnet: true,
                        stroke: '#5F95FF',
                        fill: '#fff'
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
                        fill: '#fff'
                    },
                },
            },
            left: {
                position: 'left',
                attrs: {
                    circle: {
                        r: 3,
                        magnet: true,
                        stroke: '#5F95FF',
                        fill: '#fff'
                    },
                },
            },
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
    }
})
