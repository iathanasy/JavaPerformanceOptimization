package com.cd.chapter03.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Buffer 有3个重要的参数：位置(position)、容量(capactiy)、上限(limit) 位置(position):
 * 写：当前缓冲区的位置，将从position的下一个位置写数据。 读：当前缓冲区的读取的位置，将从此位置后，读取数据。 容量(capactiy):
 * 写：缓冲区的总容量上限。 读：缓冲区的总容量上限。 上限(limit): 写：缓冲区的实际上限，它总是小于等于容量。通常情况下，和容量相等。
 * 读：代表可读取的总容量，和上次写入的数据量相等。
 * 
 * 
 * @author cd
 * @date 2019年3月29日 上午9:05:18
 * @desc
 */
public class BufferTest {

	public static void main(String[] args) {
		ByteBuffer b = ByteBuffer.allocate(15);// 15字节大小的缓冲区
		System.out.println(b);// [pos=0 lim=15 cap=15]
		for (int i = 0; i < 10; i++) {
			b.put((byte) i);// 存入10字节数据
		}
		// pos=10 为下一个即将输入的位置
		System.out.println(b); // [pos=10 lim=15 cap=15]

		b.flip();// 重置position 通常将Buffer从写模式转换为读模式需要执行此方法
		// flip()不仅重置了当前position为0，还将limt设置到当前的position的位置，
		// 这样做的目的是防止在读模式中,读到应用程序根本没有进行操作的区域。
		System.out.println(b);// [pos=0 lim=10 cap=15]

		for (int i = 0; i < 5; i++) {
			System.out.print(b.get()); // 01234
		}
		System.out.println();
		// 执行5次读操作，和写操作一样，读操作也会将position设置到当前位置
		System.out.println(b); // [pos=5 lim=10 cap=15]

		b.flip();
		System.out.println(b);// [pos=0 lim=5 cap=15]

		// ////////////////////////////////////////////////

		/**
		 * 1.Buffer的创建
		 */
		// 从堆中分配缓冲区
		// ByteBuffer buffer = ByteBuffer.allocate(1024);
		// 从既有数组中创建
		byte[] array = new byte[1024];
		ByteBuffer buffer = ByteBuffer.wrap(array);
		System.out.println("------------------------------------");
		/**
		 * 2.重置和清空缓冲区 重置 ：指重置了Buffer各项标志位，并不是真正清空了Buffer内容。
		 * 它的作用在于为提取Buffer的有效数据做准备。
		 * 
		 * rewind() //将position置零，并清除标志位(mark)
		 * out.wirte(buf);//从Buffer读取数据到Channel buf.rewind(); //回滚Buffer
		 * buf.get(array); //将Buffer的有效数据复制到数组中。
		 * 
		 * clear() //将position置零,同时将limit设置为capacity的大小，并清除了标志mark. 由于清空了limit
		 * 便无法得知Buffer内那些数据是真实有效的，这个方法用于为重新写Buffer做准备。 buf.clear();
		 * //为读入数据到Buffer做准备 in.read(buf); //从通道读入数据
		 * 
		 * flip() //将limit设置到position所在位置，然后将position置零，并清除标志位mark,通常在读写转换时使用。
		 * buf.put(magic); //将magic数组写入Buffer in.read(buf);
		 * //从通道读入给定数据，存放到Buffer中 buf.flip(); //将Buffer从读状态转换为写状态
		 * out.write(buf); //将magic和in通道中读入数据，写到通道out中。
		 */

		/**
		 * 3.标志(mark)缓冲区 可以随时记录当前位置，然后在任意时刻回到这个位置，加快或简化数据处理流程
		 * 
		 * mark() //记录当前位置。 reset() //用于恢复到mark所在的位置。
		 */

		ByteBuffer bb = ByteBuffer.allocate(15);
		for (int i = 0; i < 10; i++) {
			bb.put((byte) i);
		}
		System.out.println(bb);
		bb.flip();// 准备读

		for (int i = 0; i < bb.limit(); i++) {
			System.out.print(bb.get());
			if (i == 4) {
				bb.mark(); // 在第四个位置做mark
				System.out.println("\nmark at " + i);
			}
		}
		bb.reset();// 回到mark位置 并处理后续数据
		System.out.println("\nreset to mark");

		while (bb.hasRemaining()) {// 后续所有数据将被处理
			System.out.print(bb.get());
		}
		System.out.println();

		System.out.println("----------------------------------");

		/**
		 * 4.复制缓冲区
		 * 
		 * duplicate() //以源缓冲区为基础，生成一个完全一样的新缓冲区，会共享相同的内存数据，任意一方数据改动都是相互可见的。
		 * 两者独立维护了各自的position、limit、mark.
		 * 
		 */
		ByteBuffer b1 = ByteBuffer.allocate(15);
		for (int i = 0; i < 10; i++) {
			b1.put((byte) i);
		}

		ByteBuffer cp = b1.duplicate();// 复制当前缓冲区
		System.out.println(b1); // [pos=10 lim=15 cap=15]
		System.out.println(cp); // [pos=10 lim=15 cap=15]

		cp.flip(); // 重置缓冲区cp

		System.out.println(b1); // [pos=10 lim=15 cap=15]
		System.out.println(cp); // [pos=0 lim=10 cap=15]

		cp.put((byte) 100); // 向缓冲区cp存入数据
		// 数据是共享的
		System.out.println("b1=" + b1.get(0)); // b1=100
		System.out.println("cp=" + cp.get(0)); // cp=100

		System.out.println("-----------------------------------");

		/**
		 * 5.缓冲区分片
		 * 
		 * slice() //在现有缓冲区中，创建新的子缓冲区，子缓冲区和父缓冲区共享数据。
		 * 缓冲区切片可以将一个大缓冲区进行分割处理，得到的子缓冲区都具有完整的缓冲区模型结构
		 */
		ByteBuffer b2 = ByteBuffer.allocate(15);
		for (int i = 0; i < 10; i++) {
			b2.put((byte) i);
		}
		System.out.println(b2);
		b2.position(2);
		b2.limit(6);
		ByteBuffer subBuf = b2.slice();// 生成子缓冲区

		System.out.println(b2); // [pos=2 lim=6 cap=15]
		System.out.println(subBuf); // [pos=0 lim=4 cap=4]

		for (int i = 0; i < subBuf.capacity(); i++) {
			byte bb2 = subBuf.get(i);
			bb2 *= 10; // 在子缓冲区中，每个元素都剩以10
			subBuf.put(i, bb2);
		}

		b2.position(0);
		b2.limit(b2.capacity()); // 重置父缓冲区， 并查看父缓冲区的数据
		System.out.println(b2);
		while (b2.hasRemaining())
			System.out.print(b2.get() + " ");
		System.out.println();
		System.out.println("--------------------------------------");

		/**
		 * 6.只读缓冲区 asReadOnlyBuffer() //得到一个与当前缓冲区一致，并且共享内存数据的只读缓冲区。
		 * 可保证核心数据安全，不被随意篡改。原始缓冲区的数据修改，只读缓冲区也是可见的。
		 * 
		 */
		ByteBuffer b3 = ByteBuffer.allocate(15);
		for (int i = 0; i < 10; i++) {
			b3.put((byte) i);
		}

		ByteBuffer readOnly = b3.asReadOnlyBuffer(); // 创建只读缓冲区
		System.out.println(readOnly);
		readOnly.flip();

		while (readOnly.hasRemaining())
			System.out.print(readOnly.get() + " ");
		System.out.println();
		b3.put(2, (byte) 20);// 修改原始缓冲区数据
		// readOnly.put(2, (byte)20); //修改只读缓冲区 会抛出异常
		// java.nio.ReadOnlyBufferException

		readOnly.flip();
		while (readOnly.hasRemaining())
			System.out.print(readOnly.get() + " "); // 新改动在只读缓冲区可见
		System.out.println();

		/**
		 * 7.文件映射到内存 FileChannel.map(); //它比常规的基于流的I/O快很多
		 */
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile("a.txt", "rw");
			FileChannel fc = raf.getChannel();
			// 将文件映射到内存
			MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0,
					raf.length());
			/*
			 * while(mbb.hasRemaining()) System.out.println(mbb.get());
			 */
			mbb.put(0, (byte) 98); // 修改文件
			raf.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		System.out
				.println("--------------------------------------------------");

		/**
		 * 8.处理结构化数据，散射(Scattering)和聚集(Gathering)
		 * 
		 * 散射(Scattering): 值将数据读入一组Buffer中，而不仅仅是一个。 聚集(Gathering):
		 * 聚集与之相反，将数据写入一组Buffer中。
		 * 
		 * 在散射读取中，通道依次填充每个缓冲区，填满一个缓冲区后，就开始填充下一个。缓冲区数组就是一个大缓冲区
		 */

		String Path = "D://a.txt";

		try {
			// 通过聚集写操作创建该文件
			ByteBuffer bookBuf = ByteBuffer
					.wrap("Java性能优化技巧".getBytes("utf-8"));
			ByteBuffer autBuf = ByteBuffer.wrap("葛一鸣".getBytes("utf-8"));

			int booklen = bookBuf.limit();// 记录长度
			int authlen = autBuf.limit();

			ByteBuffer[] bufs = new ByteBuffer[] { bookBuf, autBuf };
			File file = new File(Path);
			if (!file.exists())
				file.createNewFile();// 不存在则创建文件

			FileOutputStream fos = new FileOutputStream(file);
			FileChannel fc = fos.getChannel();
			fc.write(bufs);// 聚集写文件
			System.out.println("写入数据成功");
			fos.close();

			// 使用散射读取文件
			ByteBuffer b4 = ByteBuffer.allocate(booklen);// 根据实际信息构造Buffer
			ByteBuffer b5 = ByteBuffer.allocate(authlen);

			ByteBuffer[] bufss = new ByteBuffer[] { b4, b5 }; // Buffer数组
			File f = new File(Path);
			FileInputStream fis = new FileInputStream(f);
			FileChannel fcl = fis.getChannel();

			fcl.read(bufss);// 读入数据
			String bookName = new String(bufss[0].array(), "utf-8");// 取得数据
			String authName = new String(bufss[1].array(), "utf-8");// 取得数据
			System.out.println(bookName + authName);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
