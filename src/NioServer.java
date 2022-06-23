import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio服务端
 */
public class NioServer {
    public void start() throws IOException {
        /**
         * 1：创建 Selector
         */
        Selector selector = Selector.open();
        /**
         * 2:创建 channel 通道
         */
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        /**
         * 3：为 channel 通道绑定监听端口
         */
        serverSocketChannel.bind(new InetSocketAddress(8000));
        /**
         * 4：设置channel 为非阻塞模式
         */
        serverSocketChannel.configureBlocking(false);
        /**
         * 5 :将channel注册到监听器selector上，监听连接事件(接受连接)
         */
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动成功");
        // 6：循环等待接入的链接
        for (; ; ) {
            // todo-获取channel的数量
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            // 获取可用的channel集合
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = (SelectionKey) iterator.next();
                iterator.remove();
                // 根据就绪状态，分别处理
                // 读
                // 写
            }
        }
    }
}
