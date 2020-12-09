import {Graph} from '@antv/x6'

export const GraphEdge = Graph.registerEdge('graphEdge', {
    inherit: 'edge',
    attrs: {
        line: {
            stroke: '#5F95FF',
            strokeWidth: 1,
            targetMarker: {
                name: 'classic',
                size: 8
            }
        }
    },
    router: {
        name: 'manhattan'
    }
})