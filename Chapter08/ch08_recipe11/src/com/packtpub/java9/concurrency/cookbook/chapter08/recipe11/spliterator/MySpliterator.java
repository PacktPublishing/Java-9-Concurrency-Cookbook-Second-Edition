package com.packtpub.java9.concurrency.cookbook.chapter08.recipe11.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class MySpliterator implements Spliterator<Item> {
	
	private Item[][] items;
	private int start, end, current;
	
	public MySpliterator(Item[][] items, int start, int end) {
		this.items=items;
		this.start=start;
		this.end=end;
		this.current=start;
	}

	@Override
	public int characteristics() {
		return ORDERED | SIZED | SUBSIZED;
	}

	@Override
	public long estimateSize() {
		return end - current;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Item> consumer) {
		System.out.printf("MySpliterator.tryAdvance.start: %d, %d, %d\n",start,end,current);
		if (current < end) {
			for (int i=0; i<items[current].length; i++) {
				consumer.accept(items[current][i]);
			}
			current++;
			System.out.printf("MySpliterator.tryAdvance.end:true\n");
			return true;
		}
		System.out.printf("MySpliterator.tryAdvance.end:false\n");
		return false;
	}
	
	@Override
	public void forEachRemaining(Consumer<? super Item> consumer) {
		System.out.printf("MySpliterator.forEachRemaining.start\n");
		boolean ret;
		do {
			ret=tryAdvance(consumer);
		} while (ret);
		System.out.printf("MySpliterator.forEachRemaining.end\n");
	}

	
	@Override
	public Spliterator<Item> trySplit() {
		System.out.printf("MySpliterator.trySplit.start\n");

		if (end-start<=2) {
			System.out.printf("MySpliterator.trySplit.end\n");
			return null;
		}
		int mid=start+((end-start)/2);
		int newStart=mid;
		int newEnd=end;
		end=mid;
		System.out.printf("MySpliterator.trySplit.end: %d, %d, %d, %d, %d, %d\n",start, mid, end, newStart, newEnd, current);

		return new MySpliterator(items, newStart, newEnd);
	}

}
