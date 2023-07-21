import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {

    @Test
    public void shouldCompareFirstPriceToSecondPrice() {
        Ticket ticket1 = new Ticket("Москва", "Лондон", 100_000, 17, 21);
        Ticket ticket2 = new Ticket("Москва", "Нью-Йорк", 120_000, 10, 19);

        int expected = -1;
        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSortSearchResultsByPrice() {
        Ticket ticket1 = new Ticket("Москва", "Лондон", 100_000, 17, 21);
        Ticket ticket2 = new Ticket("Москва", "Нью-Йорк", 120_000, 10, 19);
        Ticket ticket3 = new Ticket("Москва", "Лондон", 90_000, 20, 24);
        Ticket ticket4 = new Ticket("Москва", "Лондон", 50_000, 15, 19);
        Ticket ticket5 = new Ticket("Москва", "Лондон", 60_000, 12, 16);
        Ticket ticket6 = new Ticket("Москва", "Лондон", 75_000, 8, 12);

        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Ticket [] expected = {ticket4, ticket5, ticket6, ticket3, ticket1};
        Ticket [] actual = aviaSouls.search("Москва", "Лондон");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFind1Result() {
        Ticket ticket1 = new Ticket("Москва", "Лондон", 100_000, 17, 21);
        Ticket ticket2 = new Ticket("Москва", "Нью-Йорк", 120_000, 10, 19);
        Ticket ticket3 = new Ticket("Москва", "Лондон", 90_000, 20, 24);
        Ticket ticket4 = new Ticket("Москва", "Лондон", 50_000, 15, 19);
        Ticket ticket5 = new Ticket("Москва", "Лондон", 60_000, 12, 16);
        Ticket ticket6 = new Ticket("Москва", "Лондон", 75_000, 8, 12);

        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Ticket [] expected = {ticket2};
        Ticket [] actual = aviaSouls.search("Москва", "Нью-Йорк");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFind0Results() {
        Ticket ticket1 = new Ticket("Москва", "Лондон", 100_000, 17, 21);
        Ticket ticket2 = new Ticket("Москва", "Нью-Йорк", 120_000, 10, 19);
        Ticket ticket3 = new Ticket("Москва", "Лондон", 90_000, 20, 24);
        Ticket ticket4 = new Ticket("Москва", "Лондон", 50_000, 15, 19);
        Ticket ticket5 = new Ticket("Москва", "Лондон", 60_000, 12, 16);
        Ticket ticket6 = new Ticket("Москва", "Лондон", 75_000, 8, 12);

        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Ticket [] expected = {};
        Ticket [] actual = aviaSouls.search("Москва", "Стамбул");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortFlightsWithComparator() {
        Ticket ticket1 = new Ticket("Москва", "Лондон", 100_000, 17, 21); //3
        Ticket ticket2 = new Ticket("Москва", "Нью-Йорк", 120_000, 10, 19);
        Ticket ticket3 = new Ticket("Москва", "Лондон", 90_000, 17, 18); //1
        Ticket ticket4 = new Ticket("Москва", "Лондон", 50_000, 17, 19); //2
        Ticket ticket5 = new Ticket("Москва", "Лондон", 60_000, 17, 22); //4
        Ticket ticket6 = new Ticket("Москва", "Лондон", 75_000, 17, 24); //5

        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket [] expected = {ticket3, ticket4, ticket1, ticket5, ticket6};
        Ticket [] actual = aviaSouls.searchAndSortBy("Москва", "Лондон", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFind1FlightWithComparator() {
        Ticket ticket1 = new Ticket("Москва", "Лондон", 100_000, 17, 21); //3
        Ticket ticket2 = new Ticket("Москва", "Нью-Йорк", 120_000, 10, 19);
        Ticket ticket3 = new Ticket("Москва", "Лондон", 90_000, 17, 18); //1
        Ticket ticket4 = new Ticket("Москва", "Лондон", 50_000, 17, 19); //2
        Ticket ticket5 = new Ticket("Москва", "Лондон", 60_000, 17, 22); //4
        Ticket ticket6 = new Ticket("Москва", "Лондон", 75_000, 17, 24); //5

        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket [] expected = {ticket2};
        Ticket [] actual = aviaSouls.searchAndSortBy("Москва", "Нью-Йорк", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFind0FlightWithComparator() {
        Ticket ticket1 = new Ticket("Москва", "Лондон", 100_000, 17, 21); //3
        Ticket ticket2 = new Ticket("Москва", "Нью-Йорк", 120_000, 10, 19);
        Ticket ticket3 = new Ticket("Москва", "Лондон", 90_000, 17, 18); //1
        Ticket ticket4 = new Ticket("Москва", "Лондон", 50_000, 17, 19); //2
        Ticket ticket5 = new Ticket("Москва", "Лондон", 60_000, 17, 22); //4
        Ticket ticket6 = new Ticket("Москва", "Лондон", 75_000, 17, 24); //5

        AviaSouls aviaSouls = new AviaSouls();

        aviaSouls.add(ticket1);
        aviaSouls.add(ticket2);
        aviaSouls.add(ticket3);
        aviaSouls.add(ticket4);
        aviaSouls.add(ticket5);
        aviaSouls.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket [] expected = {};
        Ticket [] actual = aviaSouls.searchAndSortBy("Москва", "Стамбул", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
