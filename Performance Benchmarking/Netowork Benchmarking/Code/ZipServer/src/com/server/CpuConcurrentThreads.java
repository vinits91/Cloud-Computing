package com.server;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Logger;

import com.logging.ServerLog;

public class CpuConcurrentThreads {
	Thread thread1, thread2, thread3, thread4;
	CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
	Timer timer;

	public void runCpuConcurrent() {
		thread1 = new Thread(new Runnable() {
			boolean shut = true;
			double diff;

			public void shut() {
				shut = false;
				System.out.println("Shutting downnn");
			}

			public void run() {
				try {
					cyclicBarrier.await();
					timer = new Timer();

					timer.scheduleAtFixedRate(new TimerTask() {
						int startTime = 0;
						int endTime = 20;

						@Override
						public void run() {
							startTime++;
							ServerLog.infoCpuEvalution("CpuConcurrent_thread1", diff);
							if (startTime == endTime) {
								try {
									this.cancel();
									shut();
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}
					}, 1000, 1000);

					long s, d;
					float a, b, v, e, f, g, h, k, l, m, n, o, p, q, r, t, u;
					while (shut) {
						s = System.nanoTime();
						for (long i = 0; i < 1000000000; i++) {
							a = 10000000 + 33000000;
							b = 1000000.0005f + 6600000.87f;
							v = 100777.6f + 669999.85f;
							e = 1069999.89f - 98778888.56f;
							f = 78778889.97f - 1007778.45f;
							g = 15077788.6f + 66784556.85f;
							h = 18978213.6f - 6678123459.75f;
							k = 787895612.65f + 97782222.67f;
							l = 567851.34f - 89788912.43f;
							m = 123788112.45f + 8989412.65f;
							n = 125788122.6f + 12578822.76f;
							o = 18978412.56f - 56222214.98f;
							p = 677844562.87f - 34781222.65f;
							q = 75887995f - 347851222.56f;
							r = 23488.566f - 677788.57f;
							t = 25678.4345f - 967478855.34f;
							u = 978525.567f + 897521.56f;

						}

						d = System.nanoTime();
						diff = (d - s);
						diff = diff / 1e6;
					}

				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});

		thread2 = new Thread(new Runnable() {
			boolean shut = true;
			double diff;

			public void shut() {
				shut = false;
				System.out.println("Shutting downnn");
			}

			public void run() {
				try {
					cyclicBarrier.await();
					Timer timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						int startTime = 0;
						int endTime = 20;

						@Override
						public void run() {
							startTime++;
							ServerLog.infoCpuEvalution("CpuConcurrent_thread2", diff);

							if (startTime == endTime) {
								try {
									this.cancel();
									shut();
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}
					}, 1000, 1000);
					long s, d;
					float a, b, v, e, f, g, h, k, l, m, n, o, p, q, r, t, u;
					float numerator = 810.785f, denominator = 3.5f;
					while (shut) {
						s = System.nanoTime();
						for (long i = 0; i < 1000000000; i++) {
							a = 10000000 + 33000000;
							b = 1000000.0005f + 6600000.87f;
							v = 100777.6f + 669999.85f;
							e = 1069999.89f - 98778888.56f;
							f = 78778889.97f - 1007778.45f;
							g = 15077788.6f + 66784556.85f;
							h = 18978213.6f - 6678123459.75f;
							k = 787895612.65f + 97782222.67f;
							l = 567851.34f - 89788912.43f;
							m = 123788112.45f + 8989412.65f;
							n = 125788122.6f + 12578822.76f;
							o = 18978412.56f - 56222214.98f;
							p = 677844562.87f - 34781222.65f;
							q = 75887995f - 347851222.56f;
							r = 23488.566f - 677788.57f;
							t = 25678.4345f - 967478855.34f;
							u = 978525.567f + 897521.56f;

						}

						d = System.nanoTime();
						diff = (d - s);
						diff = diff / 1e6;
					}
				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});
		thread3 = new Thread(new Runnable() {
			boolean shut = true;
			double diff;

			public void shut() {
				shut = false;
				System.out.println("Shutting downnn");
			}

			public void run() {
				try {
					cyclicBarrier.await();
					timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						int startTime = 0;
						int endTime = 20;

						@Override
						public void run() {
							startTime++;
							ServerLog.infoCpuEvalution("CpuConcurrent_thread3", diff);
							if (startTime == endTime) {
								try {
									this.cancel();
									shut();
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}
					}, 1000, 1000);
					long s, d;
					float a, b, v, e, f, g, h, k, l, m, n, o, p, q, r, t, u;
					float numerator = 810.785f, denominator = 3.5f;
					while (shut) {
						s = System.nanoTime();
						for (long i = 0; i < 1000000000; i++) {
							a = 10000000 + 33000000;
							b = 1000000.0005f + 6600000.87f;
							v = 100777.6f + 669999.85f;
							e = 1069999.89f - 98778888.56f;
							f = 78778889.97f - 1007778.45f;
							g = 15077788.6f + 66784556.85f;
							h = 18978213.6f - 6678123459.75f;
							k = 787895612.65f + 97782222.67f;
							l = 567851.34f - 89788912.43f;
							m = 123788112.45f + 8989412.65f;
							n = 125788122.6f + 12578822.76f;
							o = 18978412.56f - 56222214.98f;
							p = 677844562.87f - 34781222.65f;
							q = 75887995f - 347851222.56f;
							r = 23488.566f - 677788.57f;
							t = 25678.4345f - 967478855.34f;
							u = 978525.567f + 897521.56f;

						}

						d = System.nanoTime();
						diff = (d - s);
						diff = diff / 1e6;
					}
				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});

		thread4 = new Thread(new Runnable() {
			boolean shut = true;
			double diff;

			public void shut() {
				shut = false;
				System.out.println("Shutting downnn");
			}

			public void run() {
				try {
					cyclicBarrier.await();
					timer = new Timer();
					timer.scheduleAtFixedRate(new TimerTask() {
						int startTime = 0;
						int endTime = 20;

						@Override
						public void run() {
							startTime++;
							ServerLog.infoCpuEvalution("CpuConcurrent_thread4", diff);
							if (startTime == endTime) {
								try {
									this.cancel();
									shut();
								} catch (Exception e) {
									e.printStackTrace();
								}

							}

						}
					}, 1000, 1000);
					long s, d;
					float a, b, v, e, f, g, h, k, l, m, n, o, p, q, r, t, u;
					float numerator = 810.785f, denominator = 3.5f;
					while (shut) {
						s = System.nanoTime();
						for (long i = 0; i < 1000000000; i++) {
							a = 10000000 + 33000000;
							b = 1000000.0005f + 6600000.87f;
							v = 100777.6f + 669999.85f;
							e = 1069999.89f - 98778888.56f;
							f = 78778889.97f - 1007778.45f;
							g = 15077788.6f + 66784556.85f;
							h = 18978213.6f - 6678123459.75f;
							k = 787895612.65f + 97782222.67f;
							l = 567851.34f - 89788912.43f;
							m = 123788112.45f + 8989412.65f;
							n = 125788122.6f + 12578822.76f;
							o = 18978412.56f - 56222214.98f;
							p = 677844562.87f - 34781222.65f;
							q = 75887995f - 347851222.56f;
							r = 23488.566f - 677788.57f;
							t = 25678.4345f - 967478855.34f;
							u = 978525.567f + 897521.56f;

						}

						d = System.nanoTime();
						diff = (d - s);
						diff = diff / 1e6;
					}
				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
	}

}
