package com.packtpub.java9.concurrency.cookbook.chapter07.recipe09.task;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

import com.packtpub.java9.concurrency.cookbook.chapter07.recipe09.data.Account;

public class Incrementer implements Runnable {

	private Account account;

	public Incrementer(Account account) {
		this.account = account;
	}

	@Override
	public void run() {
		VarHandle handler;
		try {
			handler = MethodHandles.lookup().in(Account.class).findVarHandle(Account.class, "amount", double.class);
			for (int i = 0; i < 10000; i++) {
				handler.getAndAdd(account, 100);
				account.unsafeAmount += 100;
			}
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}

	}

}
