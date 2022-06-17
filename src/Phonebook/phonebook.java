package Phonebook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class phonebook {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        menu();


/*
        ArrayList<Entries> read = new ArrayList<>();
        read.addAll(getObjects());

        // so now i want add every entry to some arraylist

       // read.addAll(getObjects());getObjects()
        read.add(new Entries("13vds","vakav","13fav","helos","32"));

        addAllEntry(read);


        ArrayList<Entries> abcd=getObjects();
        for (int i = 0; i < read.size(); i++) {
            System.out.println(abcd.get(i).toString());
        }*/
    }


    public static void menu() throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        ArrayList<Entries> read = new ArrayList<>();
            boolean it = true;


        while(it) {
            System.out.println("########## --> MENU <-- ##########");
            System.out.println(" 1) Add an Entry");     // this is done
            System.out.println(" 2) Save to file");        // also done
            System.out.println(" 3) List Phone Data");  // this also done
            System.out.println(" 4) Remove an entry");          //
            System.out.println(" 5) Edit an existing entry");   //
            System.out.println(" 6) Sort the Address book"); // done
            System.out.println(" 7) Search for a specific entry");  // done
            System.out.println(" 8) Pattern Matching ");
            System.out.println(" 9) Quit");
            System.out.println();
            System.out.println(" Please choose what you like to do with the phone database :");
            int key =sc.nextInt();

            switch (key)
            {
                case 1 :{

                    read.add(addEntry());
                    System.out.println("Successfully Added press any key");
                    sc.nextLine();
                    break;
                }
                case 2 :{
                    addAllEntry(read);
                    System.out.println("Save Successfully");
                    break;
                }

                case 3 :{
                    if(read.size()==0){
                    read.addAll(getObjects());
                    }
                    for (int i = 0; i < read.size(); i++) {
                        System.out.println(i+" "+read.get(i).toString());
                    }
                    break;
                }
                case 4 :{
                    remove(read);
                      break;
                }
                case 5 : edit(read); break;
                case 6 :{ sort(read);
                    for (int i = 0; i < read.size(); i++) {
                        System.out.println(i+" "+read.get(i).toString());
                    }break;}
                case 7 : search(read); break;
                case 8 : PatternMatching(read); break;
                case 9: {
                    System.out.println("Whatever Change are done you want to save it  Y/N");
                    String s =sc.next();
                    if(s.equals("Y") || s.equals("y"))
                    {
                        addAllEntry(read);
                        System.out.println("Save Successfully");
                    }
                    // else exit the program
                    it=false;
                break;
                }

                default: System.out.println("You Enter Wrong input. Try again :) ");

            }


        }
    }

    static Entries addEntry()
    {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter your FirstName");
        String firstname = sc.nextLine();
        System.out.println("Enter you LastName");
        String lastname = sc.nextLine();
        System.out.println("Enter you address");
        String address = sc.nextLine();
        System.out.println("Enter you Phonenumber");
        String phonenumber = sc.nextLine();
        System.out.println("Enter you Email");
        String email= sc.nextLine();
        Entries es = new Entries(firstname,lastname,phonenumber,address,email);
        return es;
    }

// write the Entry to the file
    public static void addAllEntry(ArrayList<Entries> entries)
    {

        try {

            FileOutputStream f = new FileOutputStream(new File("/home/kapture/Desktop/myObjects.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            // Write objects to file
            for (int i = 0; i < entries.size(); i++) {
                o.writeObject(entries.get(i));
            }
            o.close();
            f.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Get the object from file and return those object inthe Arraylist of Entries  //  load all file object to ArrayList
    // get it from StackOver flow
    public static ArrayList<Entries> getObjects() throws IOException, ClassNotFoundException {

        ArrayList<Entries> objects = new ArrayList<Entries>();
        FileInputStream fis = new FileInputStream("/home/kapture/Desktop/myObjects.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Entries obj =null;

        boolean isExist = true;

        while(isExist){
            if(fis.available() != 0){
                obj = (Entries) ois.readObject();
                objects.add(obj);
            }
            else{
                isExist =false;
            }
        }
        return objects;
    }


    // searching is adv level done
    static void search(ArrayList<Entries> read) throws IOException, ClassNotFoundException {

        if(read.isEmpty())
        {
            read.addAll(getObjects());
        }
        Scanner sc = new Scanner(System.in);
        String searchkey;
        System.out.println(" enter  Searching value");
        searchkey = sc.nextLine();
        boolean loop=true;

/*
       for(Entries e : read)
        {
            if(e.getFirstName().contains(searchkey))

            {
                System.out.println("Object exist \n"+e);
            }
        }
*/

            while (loop) {

                System.out.println(" $  Select Searching Parameter  $");
                System.out.println(" 1. FirstName");
                System.out.println(" 2. LastName ");
                System.out.println(" 3. PhoneNumber");
                System.out.println(" 4. Address");
                System.out.println(" 5. Email");
                System.out.println(" 6. Change Search Value");
                System.out.println(" 7. you would to like see search value");
                System.out.println(" 8. Return to Main Menu ");
                System.out.println("Choose parameter for Searching");
                int key = sc.nextInt();


                switch (key) {

                    case 1: {
                        int check=0;
                        for (Entries e : read) {
                            if (e.getFirstName().contains(searchkey))
                            {  System.out.println("OBJECT EXIST \n" + e+"\n\n\n");
                                check=1;}

                        }

                        if(check==0)
                        { // not found
                            System.out.println(" Value NOT matched  'FirstName : "+searchkey+ "'\n\n\n");
                        }
                        break;
                    }

                    case 2: {
                        int check=0;
                        for (Entries e : read) {
                            if (e.getLastName().contains(searchkey)) {
                                System.out.println("OBJECT EXIST \n" + e+"\n\n\n");
                                check = 1;
                            }
                        }
                        if(check==0)
                        { // not found
                            System.out.println(" Value NOT matched  'LastName : "+searchkey+ "'\n\n\n");
                        }
                        break;
                    }

                    case 3: {
                        int check=0;
                        for (Entries e : read) {
                            if (e.getPhoneNumber().contains(searchkey)) {
                                System.out.println("OBJECT EXIST \n" + e+"\n\n\n");
                                check = 1;
                            }
                        }
                        if(check==0)
                        { // not found
                            System.out.println(" Value NOT matched  'PhoneNumber : "+searchkey+ "'\n\n\n");
                        }
                        break;
                    }

                    case 4: {
                        int check=0;
                        for (Entries e : read) {
                            if (e.getAddress().contains(searchkey)) {
                                System.out.println("OBJECT EXIST \n" + e+"\n\n\n");
                                check = 1;
                            }
                        }
                        if(check==0)
                        { // not found
                            System.out.println(" Value NOT matched  'Address : "+searchkey+ "'\n\n\n");
                        }
                        break;
                    }
                    case 5: {
                        int check=0;
                        for (Entries e : read) {
                            if (e.getEmailAddress().contains(searchkey)) {
                                System.out.println("OBJECT EXIST \n" + e+"\n\n\n");
                                check = 1;
                            }
                        } if(check==0)
                        { // not found
                            System.out.println(" Value NOT matched  'Email : "+searchkey+ "'\n\n\n");
                        }break;
                    }

                    case 6: // change search value
                        System.out.println("Enter New Searching Value");
                            searchkey = sc.next();
                            break;
                    case 7: System.out.println("your Searching value is "+searchkey); break;

                    case 8:
                        loop = false;
                        break;


                }
            }






    }


    // Sorting in Advance level is Done
    static void sort(ArrayList<Entries> read)
    {
        System.out.println("Which parameter do you want to sort plz select it");

        Scanner sc = new Scanner(System.in);
        System.out.println("       1.FirstName ");
        System.out.println("       2.LastName  ");
        System.out.println("       3.PhoneNumber ");
        System.out.println("       4.Address ");
        System.out.println("       5.Email Address ");
        System.out.println("       6.Return to main function");
        int key =sc.nextInt();
        switch (key) {
            case 1 -> {
                read.sort((o1, o2) -> o1.getFirstName().compareTo(o2.getFirstName()));
                break;
            }
            case 2 -> {
                read.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
                break;
            }
            case 3 -> {
                read.sort((o1, o2) -> o1.getPhoneNumber().compareTo(o2.getPhoneNumber()));
                break;
            }
            case 4 -> {
                read.sort((o1, o2) -> o1.getAddress().compareTo(o2.getAddress()));
                break;
            }
            case 5 -> {
                read.sort((o1, o2) -> o1.getEmailAddress().compareTo(o2.getEmailAddress()));
                break;
            }
            case 6-> System.out.println("Out from Sorting Menu ");
        }


/*        read.sort((o1,o2)
            ->o1.getFirstName().compareTo(o2.getFirstName()));*/


    }

    // Done as much as can possible
    // Almost Done with fix minor bugs and everything works now.....
    static void remove(ArrayList<Entries> read) throws IOException, ClassNotFoundException {

        if(read.isEmpty())
        {
            read.addAll(getObjects());
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Remove Value: ");
        String str = sc.nextLine();




/*
        for(Iterator<Entries> itr = read.iterator();
         itr.hasNext();){

            Entries e = itr.next();
            System.out.println(e.toString());
            if(e.getFirstName().startsWith(str))
            {
                itr.remove();
                System.out.println(e);
                System.out.println("remove successfully");
            }

        }*/

        boolean loop=true;
        while(loop)
        {
            System.out.println(" !!!!!!   Select Remove Parameter  !!!!!");
            System.out.println(" 1. FirstName");
            System.out.println(" 2. LastName ");
            System.out.println(" 3. PhoneNumber");
            System.out.println(" 4. Address");
            System.out.println(" 5. Email");
            System.out.println(" 6. Change Remove Value ");
            System.out.println(" 7. Check Remove Value");
            System.out.println(" 8. Return to Main Menu");
            System.out.println("\n\n\n Select option :    ");
            int op = sc.nextInt();
            switch (op){
                case 1:  // firstname
                {   int check=0;
                    for(Iterator<Entries> itr = read.iterator(); itr.hasNext();){

                    Entries e = itr.next();

                        if(e.getFirstName().startsWith(str))
                        {   itr.remove();
                        System.out.println(e);
                        System.out.println(" Remove Successfully \n\n\n");
                        check=1;
                        }
                    }

                    if(check==0) {
                        System.out.println(" Value NOT Matched  'FirstName : "+str+ "'");
                    }
                    break;

                }

                case 2:{ // lastname
                    int check=0;
                    for(Iterator<Entries> itr = read.iterator(); itr.hasNext();){

                        Entries e = itr.next();

                        if(e.getLastName().startsWith(str))
                        {   itr.remove();
                            System.out.println(e);
                            System.out.println(" Remove Successfully \n\n\n");
                            check=1;
                        }                                                       }

                    if(check==0) {
                        System.out.println(" Value NOT Matched  'LastName : "+str+ "'");
                    }
                    break;
                     }

                case 3: {// PhoneNumber
                    int check=0;
                    for(Iterator<Entries> itr = read.iterator(); itr.hasNext();){

                        Entries e = itr.next();

                        if(e.getPhoneNumber().startsWith(str))
                        {   itr.remove();
                            System.out.println(e);
                            System.out.println(" Remove Successfully \n\n\n");
                            check=1;
                        }                                                       }

                    if(check==0) {
                        System.out.println(" Value NOT Matched  'PhoneNumber : "+str+ "'");
                    }
                    break;
                }

                case 4: { // Address
                    int check=0;
                    for(Iterator<Entries> itr = read.iterator(); itr.hasNext();){

                        Entries e = itr.next();

                        if(e.getAddress().startsWith(str))
                        {   itr.remove();
                            System.out.println(e);
                            System.out.println(" Remove Successfully \n\n\n");
                            check=1;
                        }                                                       }

                    if( check==0) {
                        System.out.println(" Value NOT Matched  'Address : "+str+ "'");
                    }
                    break;
                }


                case 5: {// Email
                    int check=0;
                    for(Iterator<Entries> itr = read.iterator(); itr.hasNext();){

                        Entries e = itr.next();

                        if(e.getEmailAddress().startsWith(str))
                        {   itr.remove();
                            System.out.println(e);
                            System.out.println(" Remove Successfully \n\n\n");
                            check=1;
                        }                                                       }

                    if(check==0) {
                        System.out.println(" Value NOT Matched  'Email : "+str+ "'");
                    }

                     break;
                    }


                case 6: {// change remove value

                    System.out.println("Enter New Remove input: ");
                            str= sc.next();
                            break;
                        }

                case 7: System.out.println("Your Currently Remove input is : "+str);break;
                case 8: loop=false; break;

                default: System.out.println("You Press Wrong Input,  PLZ Try Again :) "); break;
                }
        }





    }



    static void edit(ArrayList<Entries> read) throws IOException, ClassNotFoundException {
        if (read.isEmpty()) {
            read.addAll(getObjects());
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Edit Value: ");
        String str = sc.nextLine();

        for (Iterator<Entries> itr = read.iterator();
             itr.hasNext(); ) {
            Entries e = itr.next();
            if (e.getFirstName().startsWith(str)) {
                setEdit(e);
            }
            if (e.getLastName().startsWith(str)) {
                setEdit(e);
            }
            if (e.getPhoneNumber().startsWith(str)) {
                setEdit(e);
            }
            if (e.getAddress().startsWith(str)) {
                setEdit(e);
            }
            if (e.getEmailAddress().startsWith(str)) {
                setEdit(e);
            }

        }






    }


    //  String firstName; String lastName;String phoneNumber;String address;String emailAddress;

    static void setEdit(Entries e)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(e);
        System.out.println(" Do you want to edit  this entry  Y/N");
        String ch = sc.next();

        if(ch.equals("Y") || ch.equals("y"))
        { // edit them
            System.out.println("Enter FirstName : ");
            e.setFirstName(sc.next());
            System.out.println("Enter LastName : ");
            e.setLastName(sc.next());
            System.out.println("Enter PhoneNumber : ");
            e.setPhoneNumber(sc.next());
            System.out.println("Enter Address : ");
            e.setAddress(sc.next());
            System.out.println("Enter Email: ");
            e.setEmailAddress(sc.next());
        }



    }



    static void PatternMatching(ArrayList<Entries> read) throws IOException, ClassNotFoundException {
        if (read.isEmpty()) {
            read.addAll(getObjects());
        }
        System.out.println("Enter LastName with beginning letter");
        Scanner sc = new Scanner(System.in);
        String pm=sc.next();
        int i=0;
        for(Entries e : read)
        {
            if(e.getLastName().charAt(0)==pm.charAt(0))
            {  i++;
                System.out.println("Object exist \n"+e);
            }
        }
        System.out.println("\n Total Number of Matching is  "+i+" With Letter "+pm.charAt(0)+"\n\n\n");


    }


}





//  thing to do
/*
    1) Load from file                      (Done)
    2) Save to file                        (Done)
    3) Add an entry                        (Done)
    4) Remove an entry                     (Done)
    5) Edit an existing entry              (Done)       //
    6) Sort the address book               (Done)
    7) Search for a specific entry         (Done)  // modified
    8) Search with pattern                 (Done)       // don't know anything but learn
    9) Exit                                (Done)

  */