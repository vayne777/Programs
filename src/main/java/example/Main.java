package example;


import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public  class Main {

    public static final String AUSTIN_POWERS = "Austin Powers";
    public static final String WEAPONS = "weapons";
    public static final String BANNED_SUBSTANCE = "banned substance";
    public static int checkSumOfStream(InputStream inputStream) throws IOException {
       int hsum = 0;
       int value = inputStream.read();
            while( value != -1 ) {
                    hsum = (int)Integer.rotateLeft(hsum, 1) ^ (int)value;
                    value = inputStream.read();
            }
            return hsum;


  
    }
    public static Animal[] deserializeAnimalArray(byte[] data) {

    ByteArrayInputStream bai = new ByteArrayInputStream(data);
        try( ObjectInputStream oib = new ObjectInputStream(bai)) {

            int temp = oib.readInt();
            Animal[] animals = new Animal[temp];
            Animal animal;
            for (int i = 0 ; i < temp; i++) {
                animals[i] = (Animal) oib.readObject();
            }
            return animals;
        }
        catch (IOException | ClassCastException | ClassNotFoundException e  ) {
           throw new IllegalArgumentException(e);
        }


        // your implementation here
    }
    public static <T, U> Function<T, U> ternaryOperator(
            Predicate<? super T> condition,
            Function<? super T, ? extends U> ifTrue,
            Function<? super T, ? extends U> ifFalse) {

        return x -> {
             return (condition.test(x))? ifTrue.apply(x) : ifFalse.apply(x);
        };

    }
    public static IntStream pseudoRandomStream(int seed) {
        IntStream stream = IntStream.iterate(seed, x->mid(x*x)).limit(10);

        return stream; // your implementation here
    }
    public static int mid(int seed) {
        return (seed/10%1000);
    }
    public static <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> list = stream.collect(Collectors.toList());
       if (!list.isEmpty()) {
           list.sort(order);
           minMaxConsumer.accept(list.get(0), list.get(list.size()-1));
       } else { minMaxConsumer.accept(null,null); }
       }
       static abstract class Parent {
        public abstract void call();
       }

    public static void main(String[] args) throws IOException {
        {
        List<Integer> massInt = new CopyOnWriteArrayList<>(List.of(1,2,3));
        for (Integer i : massInt) {
            massInt.add(i+3);
            System.out.println(i);
        }
            //System.out.println(child instanceof Parent);

            List<String> items =
                    Arrays.asList("apple", "apple", "banana",
                            "apple", "orange", "banana", "papaya");

            Map<String, Long> result =
                    items.stream().collect(
                            Collectors.groupingBy(
                                    Function.identity(), Collectors.counting()
                            )
                    );



            IntStream stream = pseudoRandomStream(13);
            int[] mass = stream.toArray();
            ArrayDeque<Integer> list = new ArrayDeque<Integer>();
            int index = 0;
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                int v = scanner.nextInt();
                if (index%2 != 0) {
                    list.add(v);
                    index++;
                } else { index++; }
            }
            Iterator<Integer> it = list.descendingIterator();
            while (it.hasNext()) {
                System.out.print(it.next());
            }

/*            Pair<Integer, String> pair = Pair.of(1, "hello");
            Integer i = pair.getFirst(); // 1
            String s = pair.getSecond(); // "hello"
            Pair<Integer, String> p1 = Pair.of(null,null);
            Pair<Integer, String> p2 = Pair.of(null,"Hello");
            boolean guesWhat = p1.equals(p2);
            List<String> list = new ArrayList<>();
            list.add("ab");
            list.add("bc");
            Collection<?> collection = list;
            Object object = "ab";
            collection.size();
            collection.remove(object);

            list.contains(object);
            Pair<Integer, String> pair2 = Pair.of(1, "hello");
            boolean mustBeTrue = pair.equals(pair2); // true!
            boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
            int a = 5;*/
          /*  byte[] data = {65,13,13, 13, 13, 13, 10, 10, 10, 10,13,10,13};
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(16);
            changeDelimiter(inputStream, outputStream); // здесь ваша функция
            byte [] out = outputStream.toByteArray();
            for (byte b : out){
                System.out.println(b);
            }*/
           /* byte[] bytes = new byte[] {0x33, 0x45, 0x01};
            InputStream inputStream = new ByteArrayInputStream(bytes);
            int result = checkSumOfStream(inputStream);
            System.out.println("Checksum is: " + result);*/
           /* Logger logger = Logger.getLogger(Main.class.getName());
        Path a = Paths.get("Член.txt");
        File b;
            Inspector inspector = new Inspector();
            Spy spy = new Spy(logger);
            Thief thief = new Thief(10000);
            MailService variousWorkers[] = new MailService[]{spy, thief, inspector};
            UntrustworthyMailWorker worker = new UntrustworthyMailWorker(variousWorkers);

            AbstractSendable correspondence[] = {
                    new MailMessage("Oxxxymiron", "Гнойный", "Я здесь stones чисто по фану, поглумиться над слабым\n" +
                            "Ты же вылез из мамы под мой дисс на Бабана...."),
                    new MailMessage("Гнойный", "Oxxxymiron", "....Что?weapons Так болел за Россию, что на нервах терял ганглии.\n" +
                            "Но когда тут проходили митинги, где ты сидел? В Англии!...."),
                    new MailMessage("Жриновский", AUSTIN_POWERS, "Бери пацанов, и несите меня к воде."),
                    new MailMessage(AUSTIN_POWERS, "Пацаны", "Го, потаскаем Вольфовича как Клеопатру"),
                    new MailPackage("берег", "море", new Package("ВВЖ", 32000)),
                    new MailMessage("NASA", AUSTIN_POWERS, "Найди в России ракетные двигатели и лунные stones"),
                    new MailPackage(AUSTIN_POWERS, "NASA", new Package("рпакетный двигатель ", 2500000)),
                    new MailPackage(AUSTIN_POWERS, "NASA", new Package("stones", 1000)),
                    new MailPackage("Китай", "КНДР", new Package("banned substance", 99)),
                    new MailPackage(AUSTIN_POWERS, "ИГИЛ (запрещенная группировка", new Package("tiny bomb", 9000)),
                    new MailMessage(AUSTIN_POWERS, "Психиатр", "Помогите"),*//**//*
            };
            for (AbstractSendable parcell:
                    correspondence )
            {
                try {
                    worker.processMail(parcell);
                } catch (StolenPackageException e) {
                    logger.log(Level.WARNING, "Inspector found stolen package: " + e);
                } catch (IllegalPackageException e) {
                    logger.log(Level.WARNING, "Inspector found illegal package: " + e);
                }
        }*/


        }

    }
}