package gui;

import model.LinkedList;
import model.SportFacilityBookingLine;

import java.util.Date;
import java.text.SimpleDateFormat;
import model.SportFacilityBooking;

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

import controller.SportFacilityBookingCtr;

public class SportFacilityBookingGUI extends Composite {

	SportFacilityBookingCtr sportFacilityBookingCtr;

	// Tables
	private Table table;

	// Text Fields
	private Text txt_id;
	private Text txt_ownerGuest;
	private Text txt_totalPrice;
	private Text search_id;

	private Button btn_delete;
	private Button btn_save;
	private Button btn_edit;
	private Button btn_create;
	private Table table_1;
	private Text txt_sportFacilityId;

	private Button btnAdd;

	private Button btnDel;
	private Text txt_EndDate;
	private Text txt_StartDate;

	public SportFacilityBookingGUI(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new BorderLayout(0, 0));

		sportFacilityBookingCtr = new SportFacilityBookingCtr();

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
				showSearchedSportFacilityBookings(customerName);
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
				int sportFacilityBookingId = 0;
				int ownerGuestId = 0;
				double totalPrice = 0.0;
				try {
					sportFacilityBookingId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					create = true;
					boolean error = false;
					try {
						ownerGuestId = Integer.parseInt(txt_ownerGuest
								.getText());
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
							sportFacilityBookingId = sportFacilityBookingCtr
									.insertSportFacilityBooking(ownerGuestId);
						} catch (Exception ex1) {
							ok = false;
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
						}
						if (ok) {
							showAllSportFacilityBookings();
							showSportFacilityBooking(sportFacilityBookingId);
						}
					}
				}
				if (!create) {
					boolean error = false;
					try {
						ownerGuestId = Integer.parseInt(txt_ownerGuest
								.getText());
						totalPrice = Double.parseDouble(txt_totalPrice
								.getText());
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
							sportFacilityBookingCtr.updateSportFacilityBooking(
									sportFacilityBookingId, totalPrice,
									ownerGuestId);
						} catch (Exception ex1) {
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
							ok = false;
						}
						if (ok) {
							showAllSportFacilityBookings();
							showSportFacilityBooking(sportFacilityBookingId);
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
				int sportFacilityBookingId = 0;
				boolean error = false;
				try {
					sportFacilityBookingId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					try {
						sportFacilityBookingCtr
								.removeSportFacilityBooking(sportFacilityBookingId);
					} catch (Exception ex) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
					}
					showAllSportFacilityBookings();
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
				txt_totalPrice.setEditable(false);
				search_id.setEditable(true);

				btnAdd.setEnabled(true);
				btnDel.setEnabled(true);
				txt_sportFacilityId.setEditable(true);
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
				txt_sportFacilityId.setEditable(false);
				txt_StartDate.setEditable(false);
				txt_EndDate.setEditable(false);

				resetFields();

				txt_id.setEditable(false);
				txt_ownerGuest.setEditable(true);
				txt_totalPrice.setEditable(false);
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

		TableColumn tbl2clmnSportFacility = new TableColumn(table_1, SWT.NONE);
		tbl2clmnSportFacility.setWidth(51);
		tbl2clmnSportFacility.setText("Sport Facility");

		TableColumn tbl2clmnStartDate = new TableColumn(table_1, SWT.NONE);
		tbl2clmnStartDate.setWidth(91);
		tbl2clmnStartDate.setText("Start date");

		TableColumn tbl2clmnEndDate = new TableColumn(table_1, SWT.NONE);
		tbl2clmnEndDate.setWidth(91);
		tbl2clmnEndDate.setText("End date");

		TableColumn tbl2clmnSubtotal = new TableColumn(table_1, SWT.NONE);
		tbl2clmnSubtotal.setWidth(91);
		tbl2clmnSubtotal.setText("Subtotal");
		scrolledComposite_1.setContent(table_1);
		scrolledComposite_1.setMinSize(table_1.computeSize(SWT.DEFAULT,
				SWT.DEFAULT));
		scrolledComposite_1.setMinSize(new Point(321, 45));

		Composite composite_1 = new Composite(composite, SWT.NONE);
		RowLayout rl_composite_1 = new RowLayout(SWT.HORIZONTAL);
		rl_composite_1.center = true;
		composite_1.setLayout(rl_composite_1);

		Label lblSportFacilityId = new Label(composite_1, SWT.NONE);
		lblSportFacilityId.setAlignment(SWT.CENTER);
		lblSportFacilityId.setText("Sport Facility ID:");

		txt_sportFacilityId = new Text(composite_1, SWT.BORDER);
		txt_sportFacilityId.setLayoutData(new RowData(43, SWT.DEFAULT));
		txt_sportFacilityId.setEditable(false);

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
				int sportFacilityBookingId = 0;
				int sportFacilityId = 0;
				Date startDate = null;
				Date endDate = null;
				boolean error = false;
				try {
					sportFacilityBookingId = Integer.parseInt(txt_id.getText());
					sportFacilityId = Integer.parseInt(txt_sportFacilityId
							.getText());
					startDate = new SimpleDateFormat("dd.MM.yyyy H:m")
							.parse(txt_StartDate.getText());
					endDate = new SimpleDateFormat("dd.MM.yyyy H:m")
							.parse(txt_EndDate.getText());
				} catch (Exception ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					sportFacilityBookingCtr.addSportFacilityBookingLine(
							sportFacilityBookingId, sportFacilityId, startDate,
							endDate);
					showSportFacilityBookingLines(sportFacilityBookingId);
				}
			}
		});
		btnAdd.setText("Add");
		btnDel = new Button(composite_1, SWT.NONE);
		btnDel.setText("Delete");
		btnDel.setEnabled(false);
		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int sportFacilityBookingLineId = 0;
				int sportFacilityBookingId = 0;
				boolean error = false;
				try {
					sportFacilityBookingId = Integer.parseInt(txt_id.getText());
					sportFacilityBookingLineId = Integer.parseInt(table_1
							.getItem(table_1.getSelectionIndex()).getText(0));
				} catch (NumberFormatException ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					sportFacilityBookingCtr
							.removeSportFacilityBookingLine(sportFacilityBookingLineId);
					showSportFacilityBookingLines(sportFacilityBookingId);
				}
			}
		});

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int id = Integer.parseInt(table.getItem(
						table.getSelectionIndex()).getText(0));
				showSportFacilityBooking(id);
			}
		});
		showAllSportFacilityBookings();

	}

	private void showAllSportFacilityBookings() {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<SportFacilityBooking> sportFacilityBookings = sportFacilityBookingCtr
				.getAllSportFacilityBooking();
		for (SportFacilityBooking sportFacilityBooking : sportFacilityBookings) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(sportFacilityBooking
					.getSportFacilityBookingId()));
			item.setText(1, sportFacilityBooking.getOwnerGuest().getFirstName());
			item.setText(2, sportFacilityBooking.getOwnerGuest().getSurName());
		}
	}

	private void showSearchedSportFacilityBookings(String customerName) {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<SportFacilityBooking> sportFacilityBookings = sportFacilityBookingCtr
				.searchSportFacilityBookingByCustomerName(customerName);
		for (SportFacilityBooking sportFacilityBooking : sportFacilityBookings) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(sportFacilityBooking
					.getSportFacilityBookingId()));
			item.setText(1, sportFacilityBooking.getOwnerGuest().getFirstName());
			item.setText(2, sportFacilityBooking.getOwnerGuest().getSurName());
		}

	}

	private void showSportFacilityBooking(int id) {
		SportFacilityBooking sportFacilityBooking = sportFacilityBookingCtr
				.searchSportFacilityBookingById(id);
		txt_id.setText(String.valueOf(sportFacilityBooking
				.getSportFacilityBookingId()));
		txt_totalPrice.setText(String.valueOf(sportFacilityBooking
				.getTotalPrice()));
		txt_ownerGuest.setText(String.valueOf(sportFacilityBooking
				.getOwnerGuest().getPersonId()));

		txt_id.setEditable(false);
		txt_totalPrice.setEditable(false);
		txt_ownerGuest.setEditable(false);

		btnAdd.setEnabled(false);
		btnDel.setEnabled(false);
		txt_sportFacilityId.setEditable(false);
		txt_StartDate.setEditable(false);
		txt_EndDate.setEditable(false);

		showSportFacilityBookingLines(sportFacilityBooking
				.getSportFacilityBookingId());

		btn_create.setEnabled(true);
		btn_edit.setEnabled(true);
		btn_delete.setEnabled(true);
		btn_save.setEnabled(false);
	}

	public void showSportFacilityBookingLines(int id) {
		table_1.clearAll();
		table_1.setItemCount(0);
		txt_sportFacilityId.setText("");
		txt_StartDate.setText("");
		txt_EndDate.setText("");
		double totalPrice = 0.0;
		LinkedList<SportFacilityBookingLine> sportFacilityBookingLines = sportFacilityBookingCtr
				.getAllSportFacilityBookingLines(id);
		for (SportFacilityBookingLine sportFacilityBookingLine : sportFacilityBookingLines) {
			totalPrice += sportFacilityBookingLine.getSubtotal();
			TableItem item = new TableItem(table_1, SWT.NONE);
			item.setText(0,
					String.valueOf(sportFacilityBookingLine.getBookingLineId()));
			item.setText(1, String.valueOf(sportFacilityBookingLine
					.getSportFacility().getSportFacilityId()));
			item.setText(2,
					String.valueOf(sportFacilityBookingLine.getStartDateTime()));
			item.setText(3,
					String.valueOf(sportFacilityBookingLine.getEndDateTime()));
			item.setText(4,
					String.valueOf(sportFacilityBookingLine.getSubtotal()));
		}
		try {
			if (totalPrice != Double.parseDouble(txt_totalPrice.getText())) {
				int sportFacilityBookingId = 0;
				int ownerGuestId = 0;
				try {
					sportFacilityBookingId = Integer.parseInt(txt_id.getText());
					ownerGuestId = Integer.parseInt(txt_ownerGuest.getText());
					sportFacilityBookingCtr.updateSportFacilityBooking(
							sportFacilityBookingId, totalPrice, ownerGuestId);
					showSportFacilityBooking(sportFacilityBookingId);
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

	public void resetFields() {
		txt_id.setText("");
		txt_ownerGuest.setText("");
		txt_totalPrice.setText("");
		search_id.setText("");

		table_1.clearAll();
		table_1.setItemCount(0);
	}
}
