package gui;

import model.Guest;
import model.LinkedList;
import model.RoomBookingLine;

import java.util.Date;
import java.text.SimpleDateFormat;
import model.RoomBooking;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import swing2swt.layout.BorderLayout;
import swing2swt.layout.FlowLayout;

import controller.RoomBookingCtr;

public class RoomBookingGUI extends Composite {

	RoomBookingCtr roomBookingCtr;

	// Tables
	private Table table;

	// Text Fields
	private Text txt_id;
	private Text txt_ownerGuest;
	private Text txt_employee;
	private Text txt_totalPrice;
	private Text search_id;

	private Button btn_delete;
	private Button btn_save;
	private Button btn_edit;
	private Button btn_create;
	private Table table_1;
	private Text txt_RoomId;

	private Button btnAdd;

	private Button btnDel;

	private Text txt_GuestId;
	private Table table_2;
	private Text txt_EndDate;
	private Text txt_agency;
	private Text txt_StartDate;

	private Button buttonAdd;

	private Button buttonDelete;

	public RoomBookingGUI(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new BorderLayout(0, 0));

		roomBookingCtr = new RoomBookingCtr();

		Composite composite_2 = new Composite(this, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.WEST);
		composite_2.setLayout(new BorderLayout(0, 0));

		Composite composite_8 = new Composite(composite_2, SWT.NONE);
		composite_8.setLayoutData(BorderLayout.NORTH);
		RowLayout rl_composite_8 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_8.center = true;
		composite_8.setLayout(rl_composite_8);

		Label lbl_id = new Label(composite_8, SWT.NONE);
		lbl_id.setText("Customer id:");

		search_id = new Text(composite_8, SWT.BORDER);
		search_id.setLayoutData(new RowData(197, SWT.DEFAULT));

		Button btn_search = new Button(composite_8, SWT.NONE);
		btn_search.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String customerName = null;
				customerName = search_id.getText();
				showSearchedRoomBookings(customerName);
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
		tblclmnId.setWidth(100);
		tblclmnId.setText("ID");

		TableColumn tblclmnfName = new TableColumn(table, SWT.NONE);
		tblclmnfName.setWidth(100);
		tblclmnfName.setText("Customer Firstname");

		TableColumn tblclmnLname = new TableColumn(table, SWT.NONE);
		tblclmnLname.setWidth(100);
		tblclmnLname.setText("Customer Lastname");
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
				int roomBookingId = 0;
				int ownerGuestId = 0;
				int employeeId = 0;
				int agencyId = 0;
				double totalPrice = 0.0;
				try {
					roomBookingId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					create = true;
					boolean error = false;
					try {
						ownerGuestId = Integer.parseInt(txt_ownerGuest
								.getText());
						employeeId = Integer.parseInt(txt_employee.getText());
						if (txt_agency.getText().length() > 0)
							agencyId = Integer.parseInt(txt_agency.getText());
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
							roomBookingId = roomBookingCtr.insertRoomBooking(
									ownerGuestId, employeeId, agencyId);
						} catch (Exception ex1) {
							ok = false;
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
						}
						if (ok) {
							showAllRoomBookings();
							showRoomBooking(roomBookingId);
						}
					}
				}
				if (!create) {
					boolean error = false;
					try {
						ownerGuestId = Integer.parseInt(txt_ownerGuest
								.getText());
						employeeId = Integer.parseInt(txt_employee.getText());
						if (txt_agency.getText().length() > 0)
							agencyId = Integer.parseInt(txt_agency.getText());
						totalPrice = Double.parseDouble(txt_totalPrice.getText());
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
							roomBookingCtr.updateRoomBooking(roomBookingId,
									totalPrice, ownerGuestId, employeeId, agencyId);
						} catch (Exception ex1) {
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
							ok = false;
						}
						if (ok) {
							showAllRoomBookings();
							showRoomBooking(roomBookingId);
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
				int roomBookingId = 0;
				boolean error = false;
				try {
					roomBookingId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					try {
						roomBookingCtr.removeRoomBooking(roomBookingId);
					} catch (Exception ex) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
					}
					showAllRoomBookings();
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
				txt_ownerGuest.setEditable(true);
				txt_employee.setEditable(true);
				txt_totalPrice.setEditable(false);
				txt_agency.setEditable(true);
				search_id.setEditable(true);

				btnAdd.setEnabled(true);
				btnDel.setEnabled(true);
				txt_RoomId.setEditable(true);
				txt_StartDate.setEditable(true);
				txt_EndDate.setEditable(true);
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
				btnAdd.setEnabled(false);
				btnDel.setEnabled(false);
				txt_RoomId.setEditable(false);
				txt_StartDate.setEditable(false);
				txt_EndDate.setEditable(false);

				resetFields();

				txt_id.setEditable(false);
				txt_ownerGuest.setEditable(true);
				txt_employee.setEditable(true);
				txt_totalPrice.setEditable(false);
				txt_agency.setEditable(true);
				search_id.setEditable(true);
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

		Label lblOwnerGuest = new Label(composite_7, SWT.NONE);
		lblOwnerGuest.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblOwnerGuest.setText("Owner guest:");

		txt_ownerGuest = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_ownerGuest = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txt_ownerGuest.widthHint = 203;
		txt_ownerGuest.setEditable(false);
		txt_ownerGuest.setLayoutData(gd_txt_ownerGuest);

		Label lblAgency = new Label(composite_7, SWT.NONE);
		lblAgency.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblAgency.setText("Agency:");

		txt_agency = new Text(composite_7, SWT.BORDER);
		txt_agency.setEditable(false);
		txt_agency.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblEmployee = new Label(composite_7, SWT.NONE);
		lblEmployee.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblEmployee.setText("Employee:");

		txt_employee = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_employee = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txt_employee.widthHint = 203;
		txt_employee.setEditable(false);
		txt_employee.setLayoutData(gd_txt_employee);

		Label lblTotalPrice = new Label(composite_7, SWT.NONE);
		lblTotalPrice.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblTotalPrice.setText("Total Price:");

		txt_totalPrice = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_totalPrice = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txt_totalPrice.widthHint = 203;
		txt_totalPrice.setEditable(false);
		txt_totalPrice.setLayoutData(gd_txt_totalPrice);

		Composite composite = new Composite(composite_6, SWT.NONE);
		RowLayout rl_composite = new RowLayout(SWT.VERTICAL);
		rl_composite.center = true;
		composite.setLayout(rl_composite);

		Composite composite_10 = new Composite(composite, SWT.NONE);
		composite_10.setLayoutData(new RowData(SWT.DEFAULT, 130));
		composite_10.setLayout(new RowLayout(SWT.HORIZONTAL));

		ScrolledComposite scrolledComposite_1 = new ScrolledComposite(
				composite_10, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_1.setLayoutData(new RowData(SWT.DEFAULT, 106));
		scrolledComposite_1.setExpandVertical(true);
		scrolledComposite_1.setExpandHorizontal(true);

		table_1 = new Table(scrolledComposite_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setItemCount(0);
		table_1.setHeaderVisible(true);

		TableColumn tbl2clmnId = new TableColumn(table_1, SWT.NONE);
		tbl2clmnId.setWidth(51);
		tbl2clmnId.setText("ID");

		TableColumn tbl2clmnRoom = new TableColumn(table_1, SWT.NONE);
		tbl2clmnRoom.setWidth(51);
		tbl2clmnRoom.setText("Room");

		TableColumn tbl2clmnStartDate = new TableColumn(table_1, SWT.NONE);
		tbl2clmnStartDate.setWidth(91);
		tbl2clmnStartDate.setText("Start date");

		TableColumn tbl2clmnEndDate = new TableColumn(table_1, SWT.NONE);
		tbl2clmnEndDate.setWidth(91);
		tbl2clmnEndDate.setText("End date");

		TableColumn tbl2clmnSubtotal = new TableColumn(table_1, SWT.NONE);
		tbl2clmnSubtotal.setWidth(91);
		tbl2clmnSubtotal.setText("Subtotal");

		TableColumn tbl2clmnCheckinDate = new TableColumn(table_1, SWT.NONE);
		tbl2clmnCheckinDate.setWidth(91);
		tbl2clmnCheckinDate.setText("Check-in date");

		TableColumn tbl2clmnDepositStatus = new TableColumn(table_1, SWT.NONE);
		tbl2clmnDepositStatus.setWidth(91);
		tbl2clmnDepositStatus.setText("Deposit status");
		scrolledComposite_1.setContent(table_1);
		scrolledComposite_1.setMinSize(table_1.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));
		scrolledComposite_1.setMinSize(new Point(321, 45));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		RowLayout rl_composite_1 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_1.center = true;
		composite_1.setLayout(rl_composite_1);

		Label lblRoomId = new Label(composite_1, SWT.NONE);
		lblRoomId.setAlignment(SWT.CENTER);
		lblRoomId.setText("Room ID:");

		txt_RoomId = new Text(composite_1, SWT.BORDER);
		txt_RoomId.setLayoutData(new RowData(43, SWT.DEFAULT));
		txt_RoomId.setEditable(false);

		Label lblStartDate = new Label(composite_1, SWT.NONE);
		lblStartDate.setText("Start Date:");
		lblStartDate.setAlignment(SWT.CENTER);

		txt_StartDate = new Text(composite_1, SWT.BORDER);
		txt_StartDate.setEditable(false);

		Label lblEndDate = new Label(composite_1, SWT.NONE);
		lblEndDate.setText("End Date:");
		lblEndDate.setAlignment(SWT.CENTER);
		txt_EndDate = new Text(composite_1, SWT.BORDER);
		txt_EndDate.setEditable(false);

		btnAdd = new Button(composite_1, SWT.NONE);
		btnAdd.setEnabled(false);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int roomBookingId = 0;
				int roomId = 0;
				Date startDate = null;
				Date endDate = null;
				boolean error = false;
				try {
					roomBookingId = Integer.parseInt(txt_id.getText());
					roomId = Integer.parseInt(txt_RoomId.getText());
					startDate = new SimpleDateFormat("dd.MM.yyyy")
							.parse(txt_StartDate.getText());
					endDate = new SimpleDateFormat("dd.MM.yyyy")
							.parse(txt_EndDate.getText());
				} catch (Exception ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					roomBookingCtr.addRoomBookingLine(roomBookingId, roomId,
							startDate, endDate);
					showRoomBookingLines(roomBookingId);
				}
			}
		});
		btnAdd.setText("Add");
		btnDel = new Button(composite_1, SWT.NONE);
		btnDel.setText("Delete");
		btnDel.setEnabled(false);

		Composite composite_11 = new Composite(composite_6, SWT.NONE);
		RowLayout rl_composite_11 = new RowLayout(SWT.VERTICAL);
		rl_composite_11.center = true;
		composite_11.setLayout(rl_composite_11);

		Composite composite_12 = new Composite(composite_11, SWT.NONE);
		composite_12.setLayoutData(new RowData(-1, 130));
		composite_12.setLayout(new RowLayout(SWT.HORIZONTAL));

		ScrolledComposite scrolledComposite_2 = new ScrolledComposite(
				composite_12, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite_2.setLayoutData(new RowData(-1, 106));
		scrolledComposite_2.setExpandVertical(true);
		scrolledComposite_2.setExpandHorizontal(true);
		scrolledComposite_2.setMinSize(new Point(322, 45));

		table_2 = new Table(scrolledComposite_2, SWT.BORDER
				| SWT.FULL_SELECTION);
		table_2.setLinesVisible(true);
		table_2.setItemCount(0);
		table_2.setHeaderVisible(true);

		scrolledComposite_2.setContent(table_2);
		scrolledComposite_2.setMinSize(table_2.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));

		TableColumn tblclmnGuest = new TableColumn(table_2, SWT.NONE);
		tblclmnGuest.setWidth(159);
		tblclmnGuest.setText("Guest");

		Composite composite_13 = new Composite(composite_11, SWT.NONE);
		RowLayout rl_composite_13 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_13.center = true;
		composite_13.setLayout(rl_composite_13);

		Label lblGuestId = new Label(composite_13, SWT.NONE);
		lblGuestId.setText("Guest ID:");
		lblGuestId.setAlignment(SWT.CENTER);

		txt_GuestId = new Text(composite_13, SWT.BORDER);
		txt_GuestId.setLayoutData(new RowData(43, -1));
		txt_GuestId.setEditable(false);

		buttonAdd = new Button(composite_13, SWT.NONE);
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int roomBookingLineId = 0;
				int guestId = 0;
				boolean error = false;
				try {
					roomBookingLineId = Integer.parseInt(table_1.getItem(
							table_1.getSelectionIndex()).getText(0));
					guestId = Integer.parseInt(txt_GuestId.getText());
				} catch (Exception ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					roomBookingCtr.addGuest(roomBookingLineId, guestId);
					showGuests(roomBookingLineId);
				}
			}
		});
		buttonAdd.setText("Add");
		buttonAdd.setEnabled(false);

		buttonDelete = new Button(composite_13, SWT.NONE);
		buttonDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int roomBookingLineId = 0;
				int guestId = 0;
				boolean error = false;
				try {
					roomBookingLineId = Integer.parseInt(table_1.getItem(
							table_1.getSelectionIndex()).getText(0));
					guestId = Integer.parseInt(table_2.getItem(
							table_2.getSelectionIndex()).getText(0));
				} catch (NumberFormatException ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					roomBookingCtr.deleteGuest(roomBookingLineId, guestId);
					showGuests(roomBookingLineId);
				}
			}
		});
		buttonDelete.setText("Delete");
		buttonDelete.setEnabled(false);
		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int roomBookingLineId = 0;
				int roomBookingId = 0;
				boolean error = false;
				try {
					roomBookingId = Integer.parseInt(txt_id.getText());
					roomBookingLineId = Integer.parseInt(table_1.getItem(
							table_1.getSelectionIndex()).getText(0));
				} catch (NumberFormatException ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					roomBookingCtr.removeRoomBoolingLine(roomBookingLineId);
					showRoomBookingLines(roomBookingId);
				}
			}
		});

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int id = Integer.parseInt(table.getItem(
						table.getSelectionIndex()).getText(0));
				showRoomBooking(id);
			}
		});
		
		table_1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int id = Integer.parseInt(table_1.getItem(
						table_1.getSelectionIndex()).getText(0));
				showGuests(id);
			}
		});

		showAllRoomBookings();

	}

	private void showAllRoomBookings() {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<RoomBooking> roomBookings = roomBookingCtr
				.getAllRoomBookings();
		for (RoomBooking roomBooking : roomBookings) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(roomBooking.getRoomBookingId()));
			item.setText(1, roomBooking.getOwnerGuest().getFirstName());
			item.setText(2, roomBooking.getOwnerGuest().getSurName());
		}
	}

	private void showSearchedRoomBookings(String customerName) {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<RoomBooking> roomBookings = roomBookingCtr
				.searchRoomBookingByCustomerName(customerName);
		for (RoomBooking roomBooking : roomBookings) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(roomBooking.getRoomBookingId()));
			item.setText(1, roomBooking.getOwnerGuest().getFirstName());
			item.setText(2, roomBooking.getOwnerGuest().getSurName());
		}

	}

	private void showRoomBooking(int id) {
		RoomBooking roomBooking = roomBookingCtr.searchRoomBookingById(id);
		txt_id.setText(String.valueOf(roomBooking.getRoomBookingId()));
		txt_ownerGuest.setText(String.valueOf(roomBooking.getOwnerGuest()
				.getPersonId()));
		txt_employee.setText(String.valueOf(roomBooking.getEmployee()
				.getPersonId()));
		if(roomBooking.getAgency().getAgencyId() != 0) txt_agency.setText(String.valueOf(roomBooking.getAgency().getAgencyId()));
		txt_totalPrice.setText(String.valueOf(roomBooking.getTotalPrice()));

		txt_id.setEditable(false);
		txt_ownerGuest.setEditable(false);
		txt_employee.setEditable(false);
		txt_agency.setEditable(false);
		txt_totalPrice.setEditable(false);

		btnAdd.setEnabled(false);
		btnDel.setEnabled(false);
		txt_RoomId.setEditable(false);
		txt_StartDate.setEditable(false);
		txt_EndDate.setEditable(false);

		showRoomBookingLines(roomBooking.getRoomBookingId());

		btn_create.setEnabled(true);
		btn_edit.setEnabled(true);
		btn_delete.setEnabled(true);
		btn_save.setEnabled(false);
	}

	public void showRoomBookingLines(int id) {
		table_1.clearAll();
		table_1.setItemCount(0);
		txt_RoomId.setText("");
		txt_StartDate.setText("");
		txt_EndDate.setText("");
		double totalPrice = 0.0;
		LinkedList<RoomBookingLine> roomBookingLines = roomBookingCtr
				.getAllRoomBookingLines(id);
		for (RoomBookingLine roomBookingLine : roomBookingLines) {
			totalPrice += roomBookingLine.getSubtotal();
			TableItem item = new TableItem(table_1, SWT.NONE);
			item.setText(0, String.valueOf(roomBookingLine.getBookingLineId()));
			item.setText(1,
					String.valueOf(roomBookingLine.getRoom().getRoomId()));
			item.setText(2, String.valueOf(roomBookingLine.getStartDateTime()));
			item.setText(3, String.valueOf(roomBookingLine.getEndDateTime()));
			item.setText(4, String.valueOf(roomBookingLine.getSubtotal()));
			item.setText(5,
					String.valueOf(roomBookingLine.getCheckInDateTime()));
			item.setText(6, String.valueOf(roomBookingLine.getDepositStatus()));
		}
		try {
			if (totalPrice != Double.parseDouble(txt_totalPrice.getText())) {
				int roomBookingId = 0;
				int ownerGuestId = 0;
				int employeeId = 0;
				int agencyId = 0;
				try {
					roomBookingId = Integer.parseInt(txt_id.getText());
					ownerGuestId = Integer.parseInt(txt_ownerGuest
							.getText());
					employeeId = Integer.parseInt(txt_employee.getText());
					if (txt_agency.getText().length() > 0)
						agencyId = Integer.parseInt(txt_agency.getText());
					roomBookingCtr.updateRoomBooking(roomBookingId,
							totalPrice, ownerGuestId, employeeId, agencyId);
					showRoomBooking(roomBookingId);
				} catch (Exception ex1) {
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
			}
		} catch (Exception e) {
			MessageBox box = new MessageBox(getShell(), 0);
			box.setText("Error");
			box.setMessage("There was an error. Please try again");
			box.open();
		}
	}

	public void showGuests(int id) {
		table_2.clearAll();
		table_2.setItemCount(0);
		txt_GuestId.setText("");
		txt_GuestId.setEditable(true);
		buttonAdd.setEnabled(true);
		buttonDelete.setEnabled(true);
		LinkedList<Guest> guests = roomBookingCtr
				.getGuests(id);
		for (Guest guest : guests) {
			TableItem item = new TableItem(table_2, SWT.NONE);
			item.setText(0, String.valueOf(guest.getPersonId()));
		}
	}

	public void resetFields() {
		txt_id.setText("");
		txt_ownerGuest.setText("");
		txt_employee.setText("");
		txt_totalPrice.setText("");
		txt_agency.setText("");
		search_id.setText("");

		table_1.clearAll();
		table_1.setItemCount(0);
	}
}
