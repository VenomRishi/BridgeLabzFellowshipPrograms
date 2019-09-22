package com.bridgelabz.oops;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.bridgelabz.helper.JsonUtil;
import com.bridgelabz.model.Address;
import com.bridgelabz.model.AddressBookModel;
import com.bridgelabz.model.PersonModel;

public class AddressBookManagerFunctionality {

	public static void main(String[] args)
			throws InterruptedException, JsonParseException, JsonMappingException, IOException {
		/*
		 * Address book manager
		 * 
		 * newAddressBook()
		 * 
		 * open()
		 * 
		 * close()
		 * 
		 * save()
		 * 
		 * saveAs()
		 * 
		 * quit()
		 * 
		 */
		int counter = 0;
		String path = "/home/admin1/eclipse-workspace/BridgeLabzFellowshipPrograms/src/com/bridgelabz/jsonfiles/addressbook2.json";
		String statename = "";
		AddressBookModel model = new AddressBookModel();
		ArrayList<PersonModel> persons = new ArrayList<PersonModel>();

		Scanner scanner = new Scanner(System.in);

		// getting file if exist and if it is json the reading it again
		// and getting all the objects and lists of json into program

		// checking whether it is empty or not
//		File file = new File(path);
//		if (file.exists() && file.length() != 0) {
//			model = (AddressBookModel) JsonUtil.readMapper(path, model);
//			persons.addAll(model.getPersons());
//
//		}

		String search;
		int indexOfPerson;
		boolean isExitAddressBook = false;
		while (!isExitAddressBook) {
			System.out.println("Address book manager\n\n");

			System.out.println("Select menu");
			System.out.println("----------------------------------------------");

			System.out.println("1. New Address Book\n2. Open Address Book\n" + "3. Save Address Book\n"
					+ "4. SaveAs Address Book\n" + "5. Quit");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				// new address book
				System.out.println("Enter state name: ");

				statename = scanner.next();

				boolean isFoundState = false;
				for (int i = 0; i < persons.size(); i++) {
					if (persons.get(i).getAddressObj().getState().equals(statename)) {
						isFoundState = true;
						break;
					}
				}
				if (!isFoundState) {

					System.out.println("->State is added<-");
					boolean close = false;

					while (!close) {
						System.out.println(
								"Select option: \n1.add\n2.edit\n3.delete\n4.sort by last name\n5.sort by zip\n6.print\n7.close");
						int ch = scanner.nextInt();
						switch (ch) {
						case 1:
							// add person
							System.out.println("Add person details...");
							PersonModel person = new PersonModel();

							System.out.println("Enter mobile");
							String mobile = scanner.next();
							// validating mobile is not taken by anyone
							boolean isMobileTaken = false;
							for (int i = 0; i < persons.size(); i++) {
								if (persons.get(i).getMobile().equals(mobile)) {
									isMobileTaken = true;
									break;
								}
							}
							if (isMobileTaken) {
								System.out.println("This mobile number is already taken");
							} else {
								person.setMobile(mobile);
								System.out.println("Enter person first name: ");
								person.setFirstname(scanner.next().toLowerCase());
								System.out.println("Enter person last name: ");
								person.setLastname(scanner.next().toLowerCase());
								System.out.println("Enter address Details: ");
								Address address = new Address();
								System.out.println("Enter address: ");
								address.setAddressLocal(scanner.next());
								System.out.println("Enter city: ");
								address.setCity(scanner.next());
								address.setState(statename);
								System.out.println("Enter zip: ");
								address.setZip(scanner.nextInt());
								
								person.setAddressObj(address);

								persons.add(person);

								System.out.println();
								System.out.println("Person added");
								counter++;

							}

							break;
						case 2:
							// edit person
							if (counter > 0) {
								System.out.println("Enter Persons mobile number you want to edit:");
								search = scanner.next();
								indexOfPerson = 0;
								boolean isFoundPerson = false;
								for (int i = 0; i < persons.size(); i++) {
									if (search.equals(persons.get(i).getMobile())) {
										isFoundPerson = true;
										indexOfPerson = i;
										break;
									}
								}
								if (isFoundPerson) {

									mobile = search;
									// validating mobile is not taken by anyone
									isMobileTaken = false;
									for (int i = 0; i < persons.size(); i++) {
										if (persons.get(i).getMobile().equals(mobile)) {
											isMobileTaken = true;
											break;
										}
									}
									if (isMobileTaken) {
										System.out.println("Please enter different mobile number");
									} else {
										System.out.println("enter new address");
										persons.get(indexOfPerson).getAddressObj().setAddressLocal(scanner.next());
										System.out.println("enter new city name");
										persons.get(indexOfPerson).getAddressObj().setCity(scanner.next());
										System.out.println("enter new zip");
										persons.get(indexOfPerson).getAddressObj().setZip(scanner.nextInt());

										persons.get(indexOfPerson).setMobile(mobile);
										System.out.println();
										System.out.println("Edit completed");
									}

								} else
									System.out.println("No person found with this number");
							} else
								System.out.println("There is no record to edit");
							break;
						case 3:
							// delete person
							if (counter > 0) {
								System.out.println("Enter Persons mobile number you want to delete:");
								search = scanner.next();
								indexOfPerson = 0;
								boolean isFoundPerson = false;
								for (int i = 0; i < persons.size(); i++) {
									if (search.equals(persons.get(i).getMobile())) {
										isFoundPerson = true;
										indexOfPerson = i;
										break;
									}
								}
								if (isFoundPerson) {
									persons.remove(indexOfPerson);
									counter--;
									System.out.println();
									System.out.println("Delete completed");
								} else
									System.out.println("No person found with this number");
							} else
								System.out.println("No records to delete");
							break;
						case 4:
							// sort by last name
							System.out.println("Sorting by Last name is selected");
							for (int i = 0; i < persons.size() - 1; i++) {
								for (int j = 0; j < persons.size() - i - 1; j++) {

									if (persons.get(j).getLastname().compareTo(persons.get(j + 1).getLastname()) > 0) {
										Object temp = persons.get(j);
										persons.set(j, persons.get(j + 1));
										persons.set(j + 1, (PersonModel) temp);

									}
								}
							}
							System.out.println("Please wait...");
							Thread.sleep(3000);
							System.out.println("Sorting is completed to see the result select print option");
							break;
						case 5:
							// sort by zip
							System.out.println("Sorting by zip");
							for (int i = 0; i < persons.size() - 1; i++) {
								for (int j = 0; j < persons.size() - i - 1; j++) {

									if (persons.get(j).getAddressObj().getZip() > persons.get(j + 1).getAddressObj()
											.getZip()) {
										Object temp = persons.get(j);
										persons.set(j, persons.get(j + 1));
										persons.set(j + 1, (PersonModel) temp);

									}
								}
							}
							System.out.println("Please wait...");
							Thread.sleep(3000);
							System.out.println("Sorting is completed to see the result select print option");
							break;
						case 6:
							// print
							if (counter > 0) {
								System.out.println("Printing all records...");
								System.out.println("firstname\t" + "lastname\t" + "address\t" + "city\t" + "state\t"
										+ "zip\t" + "mobile\t");
								for (int i = 0; i < persons.size(); i++) {
									System.out.print(persons.get(i).getFirstname() + "\t");
									System.out.print(persons.get(i).getLastname() + "\t");
									System.out.print(persons.get(i).getAddressObj().getAddressLocal() + "\t");
									System.out.print(persons.get(i).getAddressObj().getCity() + "\t");
									System.out.print(persons.get(i).getAddressObj().getState() + "\t");
									System.out.print(persons.get(i).getAddressObj().getZip() + "\t");
									System.out.println(persons.get(i).getMobile() + "\t");
								}
							} else
								System.out.println("There is no record to print...");

							break;
						case 7:
							// close
							close = true;
							System.out.println("Closing...");
							break;
						default:
							System.out.println("Invalid option");
						}
					}
				} else
					System.out.println("State exist please try again");
				break;
			case 2:
				// open

				System.out.println("Enter state");
				statename = scanner.next();
				isFoundState = false;
				for (int i = 0; i < persons.size(); i++) {
					if (persons.get(i).getAddressObj().getState().equals(statename)) {
						isFoundState = true;
						break;
					}
				}
				if (isFoundState) {
					System.out.println("->State is found<-");
					boolean close2 = false;

					while (!close2) {
						System.out.println(
								"Select option: \n1.add\n2.edit\n3.delete\n4.sort by last name\n5.sort by zip\n6.print\n7");
						switch (scanner.nextInt()) {
						case 1:
							// add person
							System.out.println("Add person details...");
							PersonModel person = new PersonModel();

							System.out.println("Enter mobile");
							String mobile = scanner.next();
							// validating mobile is not taken by anyone
							boolean isMobileTaken = false;
							for (int i = 0; i < persons.size(); i++) {
								if (persons.get(i).getMobile().equals(mobile)) {
									isMobileTaken = true;
									break;
								}
							}
							if (isMobileTaken) {
								System.out.println("This mobile number is already taken");
							} else {
								person.setMobile(mobile);
								System.out.println("Enter person first name: ");
								person.setFirstname(scanner.next().toLowerCase());
								System.out.println("Enter person last name: ");
								person.setLastname(scanner.next().toLowerCase());
								System.out.println("Enter address Details: ");
								Address address = new Address();
								System.out.println("Enter address: ");
								address.setAddressLocal(scanner.next());
								System.out.println("Enter city: ");
								address.setCity(scanner.next());
								address.setState(statename);
								System.out.println("Enter zip: ");
								address.setZip(scanner.nextInt());
								
								person.setAddressObj(address);

								persons.add(person);

								System.out.println();
								System.out.println("Person added");
								counter++;

							}
							break;
						case 2:
							// edit person
							if (counter > 0) {
								System.out.println("Enter Persons mobile number you want to edit:");
								search = scanner.next();
								indexOfPerson = 0;

								boolean isFoundPerson = false;
								for (int i = 0; i < persons.size(); i++) {
									if (search.equals(persons.get(i).getMobile())) {
										isFoundPerson = true;
										indexOfPerson = i;
										break;
									}
								}
								if (isFoundPerson) {

									mobile = search;
									// validating mobile is not taken by anyone
									isMobileTaken = false;
									for (int i = 0; i < persons.size(); i++) {
										if (persons.get(i).getMobile().equals(mobile)) {
											isMobileTaken = true;
											break;
										}
									}
									if (isMobileTaken) {
										System.out.println("Please enter different mobile number");
									} else {
										System.out.println("enter new address");
										persons.get(indexOfPerson).getAddressObj().setAddressLocal(scanner.next());
										System.out.println("enter new city name");
										persons.get(indexOfPerson).getAddressObj().setCity(scanner.next());
										System.out.println("enter new zip");
										persons.get(indexOfPerson).getAddressObj().setZip(scanner.nextInt());

										persons.get(indexOfPerson).setMobile(mobile);
										System.out.println();
										System.out.println("Edit completed");
									}

								} else
									System.out.println("No person found with this number");

							} else
								System.out.println("There is no record to edit");
							break;
						case 3:
							// delete person
							if (counter > 0) {
								System.out.println("Enter Persons mobile number you want to delete:");
								search = scanner.next();
								indexOfPerson = 0;
								boolean isFoundPerson = false;
								for (int i = 0; i < persons.size(); i++) {
									if (search.equals(persons.get(i).getMobile())) {
										isFoundPerson = true;
										indexOfPerson = i;
										break;
									}
								}
								if (isFoundPerson) {
									persons.remove(indexOfPerson);
									counter--;
									System.out.println();
									System.out.println("Delete completed");
								} else
									System.out.println("No person found with this number");
							} else
								System.out.println("No records to delete");
							break;
						case 4:
							// sort by last name
							System.out.println("Sorting by Last name is selected");
							for (int i = 0; i < persons.size() - 1; i++) {
								for (int j = 0; j < persons.size() - i - 1; j++) {

									if (persons.get(j).getLastname().compareTo(persons.get(j + 1).getLastname()) > 0) {
										Object temp = persons.get(j);
										persons.set(j, persons.get(j + 1));
										persons.set(j + 1, (PersonModel) temp);

									}
								}
							}
							System.out.println("Please wait...");
							Thread.sleep(3000);
							System.out.println("Sorting is completed to see the result select 6 option");
							break;
						case 5:
							// sort by zip
							System.out.println("Sorting by zip");
							for (int i = 0; i < persons.size() - 1; i++) {
								for (int j = 0; j < persons.size() - i - 1; j++) {

									if (persons.get(j).getAddressObj().getZip() > persons.get(j + 1).getAddressObj()
											.getZip()) {
										Object temp = persons.get(j);
										persons.set(j, persons.get(j + 1));
										persons.set(j + 1, (PersonModel) temp);

									}
								}
							}
							System.out.println("Please wait...");
							Thread.sleep(3000);
							System.out.println("Sorting is completed to see the result select 6 option");
							break;
						case 6:
							// print
							if (counter > 0) {
								System.out.println("Printing all records...");
								System.out.println("firstname\t" + "lastname\t" + "address\t" + "city\t" + "state\t"
										+ "zip\t" + "mobile\t");
								for (int i = 0; i < persons.size(); i++) {
									System.out.print(persons.get(i).getFirstname() + "\t");
									System.out.print(persons.get(i).getLastname() + "\t");
									System.out.print(persons.get(i).getAddressObj().getAddressLocal() + "\t");
									System.out.print(persons.get(i).getAddressObj().getCity() + "\t");
									System.out.print(persons.get(i).getAddressObj().getState() + "\t");
									System.out.print(persons.get(i).getAddressObj().getZip() + "\t");
									System.out.println(persons.get(i).getMobile() + "\t");
								}
							} else
								System.out.println("There is no record to print...");

							break;
						case 7:
							// close
							close2 = true;
							System.out.println("Closing...");
							break;
						default:
							System.out.println("Invalid option");
						}
					}

				} else
					System.out.println("Please create new state of that name\nelse try new state name");

				break;
			case 3:
				System.out.println("->Saving address book into json<-");
				model.setPersons(persons);
				JsonUtil.writeMapper(path, model);
				Thread.sleep(2000);
				System.out.println();
				System.out.println("Writing into file successful....");
				// save
				break;
			case 4:
				// saveAs
				System.out.println("->Save as<-");
				System.out.println("This option requires path where you want to store");
				System.out.println("for continue press (y/n)");
				if (scanner.next().charAt(0) == 'y') {
					System.out.println("Enter the path to store file: ");
					String pathInput = scanner.next();
					// checking whether path is valid or not
					if (new File(pathInput).exists()) {
						throw new FileNotFoundException("You cannot rewrite existing file");
					} else {
						if (pathInput.charAt(pathInput.length() - 1) == '/') {
							System.out.println("please provide file name: ");
							pathInput += scanner.next();

							// writing into file
							System.out.println("->Saving address book into file<-");
							model.setPersons(persons);
							JsonUtil.writeMapper(pathInput, model);
							Thread.sleep(2000);
							System.out.println();
							System.out.println("Writing into file successful....");
						} else {
							// writing into file
							System.out.println("->Saving address book into file<-");
							model.setPersons(persons);
							JsonUtil.writeMapper(pathInput, model);
							Thread.sleep(2000);
							System.out.println();
							System.out.println("Writing into file successful....");
						}
					}
				} else
					System.out.println("prefer to choose save option");
				break;
			case 5:
				// quit
				isExitAddressBook = true;
				System.out.println("Thank you for your time");
				break;
			default:
				System.out.println("Invalid option");
				break;

			}
		}

		scanner.close();
	}

}
