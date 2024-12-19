import java.util.*;

class Room {
    private int roomNumber;
    private String type;
    private double price;
    private boolean isBooked;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void book() {
        if (!isBooked) {
            isBooked = true;
        } else {
            System.out.println("Room is already booked.");
        }
    }

    public void cancelBooking() {
        if (isBooked) {
            isBooked = false;
        } else {
            System.out.println("Room is not currently booked.");
        }
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Type: " + type + ", Price: $" + price + ", Booked: " + isBooked;
    }
}

class Guest {
    private int id;
    private String name;
    private List<Room> bookedRooms;

    public Guest(int id, String name) {
        this.id = id;
        this.name = name;
        this.bookedRooms = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Room> getBookedRooms() {
        return bookedRooms;
    }

    public void bookRoom(Room room) {
        if (!room.isBooked()) {
            bookedRooms.add(room);
            room.book();
            System.out.println(name + " booked room number " + room.getRoomNumber());
        } else {
            System.out.println("Room is already booked.");
        }
    }

    public void cancelRoom(Room room) {
        if (bookedRooms.contains(room)) {
            bookedRooms.remove(room);
            room.cancelBooking();
            System.out.println(name + " canceled booking for room number " + room.getRoomNumber());
        } else {
            System.out.println("Room not found in guest's booking list.");
        }
    }

    @Override
    public String toString() {
        return "Guest ID: " + id + ", Name: " + name + ", Booked Rooms: " + bookedRooms.size();
    }
}

class Hotel {
    private List<Room> rooms;
    private List<Guest> guests;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.guests = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
        System.out.println("Added room: " + room.getRoomNumber());
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
        System.out.println("Added guest: " + guest.getName());
    }

    public Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    public Guest findGuestById(int id) {
        for (Guest guest : guests) {
            if (guest.getId() == id) {
                return guest;
            }
        }
        return null;
    }

    public void listAvailableRooms() {
        System.out.println("Available rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println(room);
            }
        }
    }

    public void listGuests() {
        System.out.println("Guests:");
        for (Guest guest : guests) {
            System.out.println(guest);
        }
    }

    public void bookRoomForGuest(int guestId, int roomNumber) {
        Guest guest = findGuestById(guestId);
        Room room = findRoomByNumber(roomNumber);

        if (guest != null && room != null) {
            guest.bookRoom(room);
        } else {
            System.out.println("Guest or room not found.");
        }
    }

    public void cancelRoomForGuest(int guestId, int roomNumber) {
        Guest guest = findGuestById(guestId);
        Room room = findRoomByNumber(roomNumber);

        if (guest != null && room != null) {
            guest.cancelRoom(room);
        } else {
            System.out.println("Guest or room not found.");
        }
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        // Adding Rooms
        hotel.addRoom(new Room(101, "Single", 100.0));
        hotel.addRoom(new Room(102, "Double", 150.0));
        hotel.addRoom(new Room(103, "Suite", 300.0));

        // Adding Guests
        hotel.addGuest(new Guest(1, "Alice"));
        hotel.addGuest(new Guest(2, "Bob"));

        // List available rooms
        hotel.listAvailableRooms();

        // Book rooms for guests
        hotel.bookRoomForGuest(1, 101); // Alice books room 101
        hotel.bookRoomForGuest(2, 102); // Bob books room 102

        // List available rooms after booking
        hotel.listAvailableRooms();

        // Cancel booking
        hotel.cancelRoomForGuest(1, 101); // Alice cancels room 101

        // List available rooms after cancellation
        hotel.listAvailableRooms();

        // List all guests
        hotel.listGuests();
    }
}
