package day7;
import java.util.*;
 
// Custom Exception for Invalid Age
class AgeInvalidException extends Exception {
    public AgeInvalidException(String message) {
        super(message);
    }
}
 
// Custom Exception for Invalid Email
class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}
 
// Contact Class
class Contact {
    private int number;
    private String name;
    private String email;
    private int age;
 
    public Contact(int number, String name, String email, int age) {
        this.number = number;
        this.name = name;
        this.email = email;
        this.age = age;
    }
 
    public int getNumber() {
        return number;
    }
 
    public void setNumber(int number) {
        this.number = number;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }
 
    @Override
    public String toString() {
        return "Contact[number=" + number + ", name=" + name +
                ", email=" + email + ", age=" + age + "]";
    }
}
 
// Contact Manager Class
class ContactManager {
 
    // Add Contacts
    public void addContacts(HashMap<Integer, Contact> contacts, int n) {
        Scanner sc = new Scanner(System.in);
 
        for (int i = 0; i < n; i++) {
            try {
                System.out.println("\nEnter details for Contact " + (i + 1));
 
                System.out.print("Enter Contact Number: ");
                int number = Integer.parseInt(sc.nextLine());
 
                if (contacts.containsKey(number)) {
                    System.out.print("Enter Name: ");
                    sc.nextLine();
 
                    System.out.print("Enter Email: ");
                    sc.nextLine();
 
                    System.out.print("Enter Age: ");
                    sc.nextLine();
 
                    System.out.println("Contact number already exists. Skipping contact.");
                    continue;
                }
 
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
 
                System.out.print("Enter Email: ");
                String email = sc.nextLine();
 
                System.out.print("Enter Age: ");
                int age = Integer.parseInt(sc.nextLine());
 
                if (age < 0) {
                    throw new AgeInvalidException("Age cannot be negative.");
                }
 
                if (!(email.contains("@") && email.endsWith(".com"))) {
                    throw new InvalidEmailException("Invalid email format.");
                }
 
                Contact contact = new Contact(number, name, email, age);
                contacts.put(number, contact);
 
                System.out.println("Contact added successfully");
 
            } catch (AgeInvalidException | InvalidEmailException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }
        }
    }
 
    // Remove Contacts by Name
    public void removeContactsByName(HashMap<Integer, Contact> contacts, String name) {
 
        boolean found = false;
 
        Iterator<Map.Entry<Integer, Contact>> iterator = contacts.entrySet().iterator();
 
        while (iterator.hasNext()) {
            Map.Entry<Integer, Contact> entry = iterator.next();
 
            if (entry.getValue().getName().equalsIgnoreCase(name)) {
                iterator.remove();
                found = true;
            }
        }
 
        if (found) {
            System.out.println("Contacts removed successfully for name: " + name);
        } else {
            System.out.println("No contacts found with name: " + name);
        }
    }
 
    // Edit Contact
    public void editContact(HashMap<Integer, Contact> contacts, int number, Contact contact) {
 
        if (contacts.containsKey(number)) {
            contacts.put(number, contact);
            System.out.println("Contact updated successfully");
        } else {
            System.out.println("Contact number not found. Update failed.");
        }
    }
 
    // Display by Age Range
    public void displayContactThreshold(HashMap<Integer, Contact> contacts, int startAge, int endAge) {
 
        if (contacts.isEmpty()) {
            System.out.println("No contacts available");
            return;
        }
 
        boolean found = false;
 
        for (Contact contact : contacts.values()) {
 
            if (contact.getAge() >= startAge && contact.getAge() <= endAge) {
 
                if (!found) {
                    System.out.println("Contacts with age between " + startAge + " and " + endAge + ":");
                }
 
                System.out.println(contact);
                found = true;
            }
        }
 
        if (!found) {
            System.out.println("No contacts found in the age range " + startAge + " to " + endAge);
        }
    }
 
    // Display by Name Filter
    public void displayContactThreshold(HashMap<Integer, Contact> contacts, String nameFilter) {
 
        if (contacts.isEmpty()) {
            System.out.println("No contacts available");
            return;
        }
 
        boolean found = false;
 
        for (Contact contact : contacts.values()) {
 
            if (contact.getName().startsWith(nameFilter)) {
 
                if (!found) {
                    System.out.println("Contacts starting with '" + nameFilter + "':");
                }
 
                System.out.println(contact);
                found = true;
            }
        }
 
        if (!found) {
            System.out.println("No contacts found starting with '" + nameFilter + "'");
        }
    }
}
 
// Main Class
public class act1 {
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
 
        HashMap<Integer, Contact> contacts = new HashMap<>();
 
        ContactManager manager = new ContactManager();
 
        // Add Contacts
        System.out.print("Enter number of contacts: ");
        int n = Integer.parseInt(sc.nextLine());
 
        manager.addContacts(contacts, n);
 
        // Remove Contact
        System.out.print("\nEnter name to remove: ");
        String removeName = sc.nextLine();
 
        manager.removeContactsByName(contacts, removeName);
 
        // Edit Contact
        System.out.println("\nEnter details to update a contact");
 
        System.out.print("Enter Contact Number: ");
        int number = Integer.parseInt(sc.nextLine());
 
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
 
        System.out.print("Enter Email: ");
        String email = sc.nextLine();
 
        System.out.print("Enter Age: ");
        int age = Integer.parseInt(sc.nextLine());
 
        Contact updatedContact = new Contact(number, name, email, age);
 
        manager.editContact(contacts, number, updatedContact);
 
        // Display by Age
        System.out.print("\nEnter ending age threshold: ");
        int endAge = Integer.parseInt(sc.nextLine());
 
        manager.displayContactThreshold(contacts, 20, endAge);
 
        // Display by Name Filter
        System.out.print("\nEnter name filter: ");
        String filter = sc.nextLine();
 
        manager.displayContactThreshold(contacts, filter);
    }
}