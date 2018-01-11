package io.data2viz.hierarchy.treemap

import io.data2viz.hierarchy.Node
import io.data2viz.hierarchy.Row

/**
 * Divides the rectangular area specified by x0, y0, x1, y1 vertically according the value of each
 * of the specified node’s children.
 * The children are positioned in order, starting with the top edge (y0) of the given rectangle.
 * If the sum of the children’s values is less than the specified node’s value (i.e., if the specified node
 * has a non-zero internal value), the remaining empty space will be positioned
 * on the bottom edge (y1) of the given rectangle.
 */
fun treemapSlice(parent: Row, x0: Double, y0: Double, x1: Double, y1: Double) {
    var newY = y0
    val nodes = parent.children
    var i = 0
    val n = nodes.size
    val k = (parent.value != .0) && ((y1 - newY) / parent.value != .0)

    while (i < n) {
        val node = nodes[i]

        node.x0 = x0
        node.x1 = x1
        node.y0 = newY
        newY += if (k) node.value!! else .0
        node.y1 = newY
        i++
    }
}

/**
 * Divides the rectangular area specified by x0, y0, x1, y1 horizontally according the value of each
 * of the specified node’s children. The children are positioned in order, starting with the left edge (x0)
 * of the given rectangle.
 * If the sum of the children’s values is less than the specified node’s value (i.e., if the specified node
 * has a non-zero internal value), the remaining empty space will be positioned
 * on the right edge (x1) of the given rectangle.
 */
fun treemapDice(parent: Row, x0: Double, y0: Double, x1: Double, y1: Double) {
    var newX = x0
    val nodes = parent.children
    var i = 0
    val n = nodes.size
    val k = (parent.value != .0) && ((x1 - newX) / parent.value != .0)

    while (i < n) {
        val node = nodes[i]

        node.y0 = y0
        node.y1 = y1
        node.x0 = newX
        newX += if (k) node.value!! else .0
        node.x1 = newX
        i++
    }
}

/**
 * If the specified node has odd depth, delegates to treemapSlice; otherwise delegates to treemapDice.
 */
fun treemapSliceDice(parent: Node<*>, x0: Double, y0: Double, x1: Double, y1: Double) {
//    (if (parent.depth % 2 == 1) treemapSlice(parent, x0, y0, x1, y1) else treemapDice(parent, x0, y0, x1, y1))
}