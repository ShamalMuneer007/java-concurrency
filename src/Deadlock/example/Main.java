package Deadlock.example;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Intersection intersection =  new Intersection();
        Thread trainA = new Thread(new TrainA(intersection));
        Thread trainB = new Thread(new TrainB(intersection));
        trainA.start();
        trainB.start();

    }

    private static class TrainA implements Runnable {
        private Intersection intersection;
        private Random random = new Random();

        public TrainA(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true) {
                long randomSleepTime = random.nextInt(5);
                try {
                    Thread.sleep(randomSleepTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    intersection.takeRoadA();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

        private static class TrainB implements Runnable {
            private Intersection intersection;
            private Random random = new Random();

            public TrainB(Intersection intersection) {
                this.intersection = intersection;
            }

            @Override
            public void run() {
                while (true) {
                    long randomSleepTime = random.nextInt(5);
                    try {
                        Thread.sleep(randomSleepTime);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        intersection.takeRoadB();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        private static class Intersection {
            private final Object roadA = new Object();
            private final Object roadB = new Object();

            public void takeRoadA() throws InterruptedException {
                synchronized (roadA) {
                    System.out.println("Road A is locked by : " + Thread.currentThread().getName());

                    synchronized (roadB) {
                        System.out.println("Train is passing through road A");
                        Thread.sleep(1);
                    }
                }
            }

            public void takeRoadB() throws InterruptedException {
                synchronized (roadB) {
                    System.out.println("Road B is locked by : " + Thread.currentThread().getName());

                    synchronized (roadA) {
                        System.out.println("Train is passing through road B");
                        Thread.sleep(1);
                    }
                }
            }
        }
    }

