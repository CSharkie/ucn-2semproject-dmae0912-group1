package gui;

import controller.PersonCtr;
import model.Guest;
import model.LinkedList;

import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class GuestGUI extends Composite {

	private PersonCtr persCtr;

	// Tables
	private Table table;

	// Text Fields
	private Text txt_id;
	private Text txt_firstName;
	private Text txt_surName;
	private Text txt_email;
	private Text txt_address;
	private Text txt_phoneNo;
	private Text search_name;

	private Button btn_delete;
	private Button btn_save;
	private Button btn_edit;
	private Button btn_create;
	private Text txt_passportNo;
	private Text txt_password;
	private Text txt_discount;

	public GuestGUI(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new BorderLayout(0, 0));

		persCtr = new PersonCtr();

		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.WEST);
		composite_2.setLayout(new BorderLayout(0, 0));

		Composite composite_8 = new Composite(composite_2, SWT.NONE);
		composite_8.setLayoutData(BorderLayout.NORTH);
		RowLayout rl_composite_8 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_8.center = true;
		composite_8.setLayout(rl_composite_8);

		Label lbl_name = new Label(composite_8, SWT.NONE);
		lbl_name.setText("Name:");

		search_name = new Text(composite_8, SWT.BORDER);
		search_name.setLayoutData(new RowData(116, SWT.DEFAULT));

		Button btn_search = new Button(composite_8, SWT.NONE);
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String name = search_name.getText();
				showSearchedGuests(name);
			}
		});
		btn_search.setText("Search");

		Composite composite_9 = new Composite(composite_2, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));

		ScrolledComposite scrolledComposite = new ScrolledComposite(
				composite_9, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);

		table = new Table(scrolledComposite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tblclmnId = new TableColumn(table, SWT.NONE);
		tblclmnId.setWidth(40);
		tblclmnId.setText("ID");

		TableColumn tblclmnFirstName = new TableColumn(table, SWT.NONE);
		tblclmnFirstName.setWidth(100);
		tblclmnFirstName.setText("FirstName");

		TableColumn tblclmnSurName = new TableColumn(table, SWT.NONE);
		tblclmnSurName.setWidth(100);
		tblclmnSurName.setText("SurName");
		scrolledComposite.setContent(table);
		scrolledComposite.setMinSize(table
				.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		Composite composite_3 = new Composite(this, SWT.NONE);
		composite_3.setLayout(new BorderLayout(0, 0));

		Composite composite_4 = new Composite(composite_3, SWT.NONE);
		composite_4.setLayoutData(BorderLayout.SOUTH);
		composite_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		Composite composite_5 = new Composite(composite_4, SWT.NONE);
		composite_5.setLayout(new RowLayout(SWT.HORIZONTAL));

		btn_delete = new Button(composite_5, SWT.CENTER);
		btn_save = new Button(composite_5, SWT.CENTER);
		btn_save.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				boolean create = false;
				int guestId = 0;
				String firstName = null;
				String surName = null;
				String address = null;
				String email = null;
				String phoneNo = null;
				int passportNo = 0;
				String password = null;
				double discount = 0.0;
				try {
					guestId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					create = true;
					boolean error = false;
					try {
						firstName = txt_firstName.getText();
						surName = txt_surName.getText();
						address = txt_address.getText();
						email = txt_email.getText();
						phoneNo = txt_phoneNo.getText();
						passportNo = Integer.parseInt(txt_passportNo.getText());
						password = txt_password.getText();
						discount = Double.parseDouble(txt_discount.getText());
					} catch (Exception exc) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
						error = true;
					}
					if (!error) {
						boolean ok = true;
						try {
							guestId = persCtr.insertGuest(firstName, surName,
									address, phoneNo, email, passportNo,
									password, discount);
						} catch (Exception ex1) {
							ok = false;
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
						}
						if (ok) {
							showAllGuests();
							showGuest(guestId);
						}
					}
				}
				if (!create) {
					boolean error = false;
					try {
						firstName = txt_firstName.getText();
						surName = txt_surName.getText();
						address = txt_address.getText();
						email = txt_email.getText();
						phoneNo = txt_phoneNo.getText();
						passportNo = Integer.parseInt(txt_passportNo.getText());
						password = txt_password.getText();
						discount = Double.parseDouble(txt_discount.getText());
					} catch (Exception exc) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
						error = true;
					}
					if (!error) {
						boolean ok = true;
						try {
							persCtr.updateGuest(guestId, firstName, surName,
									address, phoneNo, email, passportNo,
									password, discount);
						} catch (Exception ex1) {
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
							ok = false;
						}
						if (ok) {
							showAllGuests();
							showGuest(guestId);
						}
					}

				}
			}
		});
		btn_edit = new Button(composite_5, SWT.CENTER);
		btn_create = new Button(composite_5, SWT.CENTER);

		btn_delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int guestId = 0;
				boolean error = false;
				try {
					guestId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					try {
						persCtr.removeGuest(guestId);
					} catch (Exception ex) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
					}
					showAllGuests();
					resetFields();
					btn_delete.setEnabled(false);
					btn_edit.setEnabled(false);
					btn_save.setEnabled(false);
					btn_create.setEnabled(true);
				}
			}
		});
		btn_delete.setLayoutData(new RowData(75, 50));
		btn_delete.setText("DELETE");
		btn_delete.setEnabled(false);

		btn_save.setLayoutData(new RowData(75, 50));
		btn_save.setText("SAVE");
		btn_save.setEnabled(false);

		btn_edit.setLayoutData(new RowData(75, 50));
		btn_edit.setEnabled(false);
		btn_edit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btn_delete.setEnabled(false);
				btn_edit.setEnabled(false);
				btn_save.setEnabled(true);
				btn_create.setEnabled(false);

				txt_id.setEditable(false);
				txt_firstName.setEditable(true);
				txt_surName.setEditable(true);
				txt_address.setEditable(true);
				txt_email.setEditable(true);
				txt_phoneNo.setEditable(true);
				txt_passportNo.setEditable(true);
				txt_password.setEditable(true);
				txt_discount.setEditable(true);
				search_name.setEditable(true);
			}
		});
		btn_edit.setText("EDIT");

		btn_create.setLayoutData(new RowData(75, 50));
		btn_create.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btn_delete.setEnabled(false);
				btn_edit.setEnabled(false);
				btn_save.setEnabled(true);
				btn_create.setEnabled(false);

				resetFields();

				txt_id.setEditable(false);
				txt_firstName.setEditable(true);
				txt_surName.setEditable(true);
				txt_address.setEditable(true);
				txt_email.setEditable(true);
				txt_phoneNo.setEditable(true);
				txt_passportNo.setEditable(true);
				txt_password.setEditable(true);
				txt_discount.setEditable(true);
				search_name.setEditable(true);
			}
		});
		btn_create.setText("CREATE");

		Composite composite_6 = new Composite(composite_3, SWT.NONE);
		composite_6.setLayoutData(BorderLayout.CENTER);
		composite_6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 40));

		Composite composite_7 = new Composite(composite_6, SWT.NONE);
		composite_7.setLayout(new GridLayout(2, false));

		Label lblId = new Label(composite_7, SWT.NONE);
		lblId.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false,
				1, 1));
		lblId.setBounds(0, 0, 55, 15);
		lblId.setText("ID:");

		txt_id = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_id = new GridData(SWT.LEFT, SWT.CENTER, true, false, 1,
				1);
		gd_txt_id.widthHint = 203;
		txt_id.setEditable(false);
		txt_id.setLayoutData(gd_txt_id);

		Label lblFirstName = new Label(composite_7, SWT.NONE);
		lblFirstName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblFirstName.setText("First Name:");

		txt_firstName = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_fname = new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1);
		gd_txt_fname.widthHint = 203;
		txt_firstName.setEditable(false);
		txt_firstName.setLayoutData(gd_txt_fname);

		Label lblSurName = new Label(composite_7, SWT.NONE);
		lblSurName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblSurName.setText("Last Name:");

		txt_surName = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_sname = new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1);
		gd_txt_sname.widthHint = 203;
		txt_surName.setEditable(false);
		txt_surName.setLayoutData(gd_txt_sname);

		Label lblAddress = new Label(composite_7, SWT.NONE);
		lblAddress.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblAddress.setText("Address:");

		txt_address = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_address = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txt_address.widthHint = 203;
		txt_address.setEditable(false);
		txt_address.setLayoutData(gd_txt_address);

		Label lblEmail = new Label(composite_7, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblEmail.setText("Email:");

		txt_email = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_email = new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1);
		gd_txt_email.widthHint = 203;
		txt_email.setEditable(false);
		txt_email.setLayoutData(gd_txt_email);

		Label lblPhoneNo = new Label(composite_7, SWT.NONE);
		lblPhoneNo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblPhoneNo.setText("Phone No:");

		txt_phoneNo = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_phoneNo = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txt_phoneNo.widthHint = 203;
		txt_phoneNo.setEditable(false);
		txt_phoneNo.setLayoutData(gd_txt_phoneNo);

		Label lblPassportNo = new Label(composite_7, SWT.NONE);
		lblPassportNo.setText("Passport No:");
		lblPassportNo.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));

		txt_passportNo = new Text(composite_7, SWT.BORDER);
		txt_passportNo.setEditable(false);
		txt_passportNo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblPassword = new Label(composite_7, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblPassword.setText("Password:");

		txt_password = new Text(composite_7, SWT.BORDER);
		txt_password.setEditable(false);
		txt_password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblDiscount = new Label(composite_7, SWT.NONE);
		lblDiscount.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblDiscount.setText("Discount:");

		txt_discount = new Text(composite_7, SWT.BORDER);
		txt_discount.setEditable(false);
		txt_discount.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int id = Integer.parseInt(table.getItem(
						table.getSelectionIndex()).getText(0));
				showGuest(id);
			}
		});
		showAllGuests();
	}

	private void showAllGuests() {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<Guest> guests = persCtr.getAllGuests();
		for (Guest guest : guests) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(guest.getPersonId()));
			item.setText(1, guest.getFirstName());
			item.setText(2, guest.getSurName());
		}

	}

	private void showSearchedGuests(String name) {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<Guest> guests = persCtr.searchGuestByName(name);
		for (Guest guest : guests) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(guest.getPersonId()));
			item.setText(1, guest.getFirstName());
			item.setText(2, guest.getSurName());
		}

	}

	private void showGuest(int id) {
		Guest guest = persCtr.searchGuestById(id);
		txt_id.setText(String.valueOf(guest.getPersonId()));
		txt_firstName.setText(guest.getFirstName());
		txt_surName.setText(guest.getSurName());
		txt_address.setText(guest.getAddress());
		txt_email.setText(guest.getEmail());
		txt_phoneNo.setText(guest.getPhoneNo());
		txt_passportNo.setText(String.valueOf(guest.getPassportNo()));
		txt_password.setText(guest.getPassword());
		txt_discount.setText(String.valueOf(guest.getDiscount()));

		txt_id.setEditable(false);
		txt_firstName.setEditable(false);
		txt_surName.setEditable(false);
		txt_address.setEditable(false);
		txt_email.setEditable(false);
		txt_phoneNo.setEditable(false);
		txt_passportNo.setEditable(false);
		txt_password.setEditable(false);
		txt_discount.setEditable(false);

		btn_create.setEnabled(true);
		btn_edit.setEnabled(true);
		btn_delete.setEnabled(true);
		btn_save.setEnabled(false);
	}

	public void resetFields() {
		txt_id.setText("");
		txt_firstName.setText("");
		txt_surName.setText("");
		txt_address.setText("");
		txt_email.setText("");
		txt_phoneNo.setText("");
		txt_passportNo.setText("");
		txt_password.setText("");
		txt_discount.setText("");
		search_name.setText("");
	}
}