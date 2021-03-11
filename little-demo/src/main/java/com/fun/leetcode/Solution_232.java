package com.fun.leetcode;

import java.util.Iterator;
import java.util.Stack;

/**
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
 * <p>
 * 实现 MyQueue 类：
 * <p>
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 * <p>
 * 说明：
 * <p>
 * 你只能使用标准的栈操作 —— 也就是只有push to top, peek/pop from top,size, 和is empty操作是合法的。
 * 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
 * <p>
 * 进阶：
 * <p>
 * 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyQueue", "push", "push", "peek", "pop", "empty"]
 * [[], [1], [2], [], [], []]
 * 输出：
 * [null, null, null, 1, 1, false]
 * <p>
 * 解释：
 * MyQueue myQueue = new MyQueue();
 * myQueue.push(1); // queue is: [1]
 * myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
 * myQueue.peek(); // return 1
 * myQueue.pop(); // return 1, queue is [2]
 * myQueue.empty(); // return false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-queue-using-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution_232 {

    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        int param_2 = obj.pop();
        int param_3 = obj.peek();
        boolean param_4 = obj.empty();
    }

    public static class MyQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;
        boolean isDirectPush;


        /**
         * Initialize your data structure here.
         */
        public MyQueue() {
            isDirectPush = true;
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            Stack<Integer> emptyStack , notEmptyStack;

            if (stack1.empty()) {
                emptyStack = stack1;
                notEmptyStack = stack2;
            } else {
                emptyStack = stack2;
                notEmptyStack = stack1;
            }

            if (isDirectPush) {
                notEmptyStack.push(x);

            } else {
                Iterator<Integer> it = notEmptyStack.iterator();
                while (it.hasNext()) {
                    int e = notEmptyStack.pop();
                    emptyStack.push(e);
                }
                emptyStack.push(x);
                isDirectPush = true;
            }
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            Stack<Integer> emptyStack , notEmptyStack;

            if (stack1.empty()) {
                emptyStack = stack1;
                notEmptyStack = stack2;
            } else {
                emptyStack = stack2;
                notEmptyStack = stack1;
            }

            if (isDirectPush) {
                Iterator<Integer> it = notEmptyStack.iterator();
                while (it.hasNext()) {
                    int e = notEmptyStack.pop();
                    emptyStack.push(e);
                }
                isDirectPush = false;
                return emptyStack.pop();
            } else {
                return notEmptyStack.pop();
            }
        }

        /**
         * Get the front element.
         */
        public int peek() {
            Stack<Integer> emptyStack , notEmptyStack;

            if (stack1.empty()) {
                emptyStack = stack1;
                notEmptyStack = stack2;
            } else {
                emptyStack = stack2;
                notEmptyStack = stack1;
            }

            if (isDirectPush) {
                Iterator<Integer> it = notEmptyStack.iterator();
                while (it.hasNext()) {
                    int e = notEmptyStack.pop();
                    emptyStack.push(e);
                }
                isDirectPush = false;
                return emptyStack.peek();
            } else {
                return notEmptyStack.peek();
            }
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack1.empty() && stack2.empty();
        }
    }
}
