package com.server;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

import com.logging.ServerLog;

public class CpuEvaluation {
	PropertiesController propertiesController = new PropertiesController();
	int nothraeds = Integer.parseInt(propertiesController.getProperty("cpuevalutionthreads"));
	CyclicBarrier c = new CyclicBarrier(nothraeds);

	public void runCpuEvaluation() {
		if (nothraeds == 1) {
			oneThreadRun();
		} else if (nothraeds == 2) {
			twoThreadRun();
		} else if (nothraeds == 4) {
			fourThreadRun();
		}
	}

	public void writeLog(String fileName, double diff) {
		ServerLog.infoCpuEvalution(fileName, diff);
	}

	public void oneThreadRun() {
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					c.await();
					long s, d;
					float a, b, v, e, f, g, h, k, l, m, n, o, p, q, r, t, u;
					double diff;
					float numerator = 810.785f, denominator = 3.5f;
					s = System.nanoTime();
					int j = 0;
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
					writeLog("CPU_file_Thread1", diff);

				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});
		thread1.start();
	}

	public void twoThreadRun() {
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					c.await();
					long s, d;
					float a, b, v, e, f, g, h, k, l, m, n, o, p, q, r, t, u;
					double diff;
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
					writeLog("CPU_file_Thread1", diff);
				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {

					c.await();
					long s, d;
					float a, b, v, e, f, g, h, k, l, m, n, o, p, q, r, t, u;
					double diff;
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
					writeLog("CPU_file_Thread2", diff);
				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});
		thread1.start();
		thread2.start();
	}

	public void fourThreadRun() {
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				try {
					c.await();
					long s, d;
					double diff;
					float numerator = 810.785f, denominator = 3.5f;
					for (int j = 0; j < 10; j++) {
						float a = 0, b = 0, v = 0, e = 0, f = 0, g = 0, h = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0,
								q = 0, r = 0, t = 0, u = 0;
						ThreadMXBean bean = ManagementFactory.getThreadMXBean();
						s = bean.getCurrentThreadCpuTime();
						for (long i = 0; i < 1000000000; i++) {

							a = 10000000 / 33000000;
							b = 1000000.0005f + 6600000.87f;
							v = 100777.6f + 669999.85f;
							e = 1069999.89f - 98778888.56f;
							f = 78778889.97f - 1007778.45f;
							g = 15077788.6f + 66784556.85f;
							h = 18978213.6f / 6678123459.75f;
							k = 787895612.65f + 97782222.67f;
							l = 567851.34f - 89788912.43f;
							m = 123788112.45f + 8989412.65f;
							n = 125788122.6f + 12578822.76f;
							o = 18978412.56f - 56222214.98f;
							p = 677844562.87f - 34781222.65f;
							q = 75887995f / 347851222.56f;
							r = 23488.566f / 677788.57f;
							t = 25678.4345f / 967478855.34f;
							u = 978525.567f + 897521.56f;
							a = 10000000 + 33000000;
							b = 1000000.0005f + 6600000.87f;
							v = 100777.6f + 669999.85f;
							e = 1069999.89f / 98778888.56f;
							f = 78778889.97f - 1007778.45f;
							g = 15077788.6f + 66784556.85f;
							h = 18978213.6f - 6678123459.75f;
							k = 787895612.65f + 97782222.67f;
							l = 567851.34f / 89788912.43f;
							m = 123788112.45f + 8989412.65f;
							n = 125788122.6f + 12578822.76f;
							o = 18978412.56f - 56222214.98f;
							p = 677844562.87f - 34781222.65f;
							q = 75887995f - 347851222.56f;
							r = 23488.566f /677788.57f;
							t = 25678.4345f / 967478855.34f;
							u = 978525.567f / 897521.56f;
						}
						d = bean.getCurrentThreadCpuTime();
						diff = (d - s);
						diff = diff / 1e6;
						writeLog("CPU_file_Thread1", diff);
						// System.gc();
					}
				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});

		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				try {
					c.await();
					long s, d;

					double diff;
					float numerator = 810.785f, denominator = 3.5f;
					for (int j = 0; j < 10; j++) {
						float a = 0, b = 0, v = 0, e = 0, f = 0, g = 0, h = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0,
								q = 0, r = 0, t = 0, u = 0;
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
						writeLog("CPU_file_Thread2", diff);
						System.gc();
					}
				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});
		Thread thread3 = new Thread(new Runnable() {
			public void run() {
				try {
					c.await();
					long s, d;
					double diff;
					for (int j = 0; j < 10; j++) {
						float a = 0, b = 0, v = 0, e = 0, f = 0, g = 0, h = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0,
								q = 0, r = 0, t = 0, u = 0;
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
						writeLog("CPU_file_Thread3", diff);
						System.gc();
					}
				} catch (InterruptedException e) {
					ServerLog.error("Exception in CpuEvaluation Thread 1 :: runCpuEvaluation method " + e.getMessage());
				} catch (BrokenBarrierException e) {
					ServerLog.error("Exception in CpuEvaluation :: runCpuEvaluation method " + e.getMessage());
				}

			}
		});

		Thread thread4 = new Thread(new Runnable() {
			public void run() {
				try {
					c.await();
					long s, d;
					// float a, b, v, e, f, g, h, k, l, m, n, o, p, q, r, t, u;
					double diff;
					for (int j = 0; j < 10; j++) {
						float a = 0, b = 0, v = 0, e = 0, f = 0, g = 0, h = 0, k = 0, l = 0, m = 0, n = 0, o = 0, p = 0,
								q = 0, r = 0, t = 0, u = 0;
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
						writeLog("CPU_file_Thread4", diff);
						System.gc();
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