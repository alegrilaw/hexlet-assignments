package exercise;

// BEGIN
class ListThread implements Runnable {

    private final SafetyList list;

    ListThread(SafetyList list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1L);
                int number = (int) (Integer.MAX_VALUE * Math.random());
                list.add(number);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
    }
}
// END
