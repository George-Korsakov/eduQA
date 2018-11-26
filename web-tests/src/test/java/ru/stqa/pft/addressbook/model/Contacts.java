package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactShortData> {

  private Set<ContactShortData> delegate;

  public Contacts(Contacts contacts ) {
    this.delegate = new HashSet<ContactShortData>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<>();
  }
  public Contacts(Collection<ContactShortData> contacts){
    this.delegate = new HashSet<ContactShortData>(contacts);
  }

  @Override
  protected Set<ContactShortData> delegate() {
    return delegate;
  }
  // метод для добавдения группы в список
  public Contacts withAdded( ContactShortData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  // метод для удаления группы из списка
  public Contacts withOut( ContactShortData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

}
