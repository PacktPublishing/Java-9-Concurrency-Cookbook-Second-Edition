package com.packtpub.java9.concurrency.cookbook.chapter08.recipe09.task;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a priority based transfer queue. It extends the
 * PriorityBlockingQueue class and implements the TransferQueue interface
 *
 * @param <E>
 *            Class of the elements to be stored in the queue
 */
public class MyPriorityTransferQueue<E> extends PriorityBlockingQueue<E> implements TransferQueue<E> {

	/**
	 * Serial Version of the class
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Number of consumers waiting
	 */
	private final AtomicInteger counter;

	/**
	 * Blocking queue to store the transfered elements
	 */
	private final LinkedBlockingQueue<E> transfered;

	/**
	 * Lock to control the acces to the operations
	 */
	private final ReentrantLock lock;

	/**
	 * Constructor of the class
	 */
	public MyPriorityTransferQueue() {
		counter = new AtomicInteger(0);
		lock = new ReentrantLock();
		transfered = new LinkedBlockingQueue<>();
	}

	/**
	 * This method tries to transfer an element to a consumer. If there is a
	 * consumer waiting, we puts the element in the queue and return the true
	 * value. Else, return the false value.
	 */
	@Override
	public boolean tryTransfer(E e) {
		boolean value = false;
		try {
			lock.lock();
			if (counter.get() == 0) {
				value = false;
			} else {
				put(e);
				value = true;
			}
		} finally {
			lock.unlock();
		}
		return value;
	}

	/**
	 * Transfer an element to the consumer. If there is a consumer waiting, puts
	 * the element on the queue and return the true value. Else, puts the value
	 * in the transfered queue and returns the false value. In this case, the
	 * thread than makes the call will be blocked until a consumer takes the
	 * transfered elements
	 */
	@Override
	public void transfer(E e) throws InterruptedException {
		lock.lock();
		if (counter.get() != 0) {
			try {
				put(e);
			} finally {
				lock.unlock();
			}
		} else {
			try {
				transfered.add(e);
			} finally {
				lock.unlock();
			}
			synchronized (e) {
				e.wait();
			}
		}
	}

	/**
	 * This method tries to transfer an element to a consumer waiting a maximum
	 * period of time. If there is a consumer waiting, puts the element in the
	 * queue. Else, puts the element in the queue of transfered elements and
	 * wait the specified period of time until that time pass or the thread is
	 * interrupted.
	 */
	@Override
	public boolean tryTransfer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		lock.lock();
		if (counter.get() != 0) {
			try {
				put(e);
			} finally {
				lock.unlock();
			}
			return true;
		} else {
			long newTimeout = 0;
			try {
				transfered.add(e);
				newTimeout = TimeUnit.MILLISECONDS.convert(timeout, unit);
			} finally {
				lock.unlock();
			}
			e.wait(newTimeout);
			lock.lock();
			boolean value;
			try {
				if (transfered.contains(e)) {
					transfered.remove(e);
					value = false;
				} else {
					value = true;
				}
			} finally {
				lock.unlock();
			}
			return value;
		}
	}

	/**
	 * Method that returns if the queue has waiting consumers
	 */
	@Override
	public boolean hasWaitingConsumer() {
		return (counter.get() != 0);
	}

	/**
	 * Method that returns the number of waiting consumers
	 */
	@Override
	public int getWaitingConsumerCount() {
		return counter.get();
	}

	/**
	 * Method that returns the first element of the queue or is blocked if the
	 * queue is empty. If there is transfered elements, takes the first
	 * transfered element and wake up the thread that is waiting for the
	 * transfer of that element. Else, takes the first element of the queue or
	 * is blocked until there is one element in the queue.
	 */
	@Override
	public E take() throws InterruptedException {
		lock.lock();
		E value = null;
		try {
			counter.incrementAndGet();
			value = transfered.poll();
			if (value == null) {
				lock.unlock();
				value = super.take();
				lock.lock();
			} else {
				synchronized (value) {
					value.notify();
				}
			}
			counter.decrementAndGet();
		} finally {
			lock.unlock();
		}
		return value;
	}
}
