package gui;

import controller.TravelAgencyCtr;
import model.TravelAgency;
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

public class TravelAgencyGUI extends Composite {

	private TravelAgencyCtr travelAgencyCtr;

	// Tables
	private Table table;

	// Text Fields
	private Text txt_id;
	private Text txt_name;
	private Text txt_discount;
	private Text txt_phoneNo;
	private Text txt_address;
	private Text txt_email;
	private Text search_name;

	private Button btn_delete;
	private Button btn_save;
	private Button btn_edit;
	private Button btn_create;

	public TravelAgencyGUI(Composite parent, int style) {
		super(parent, style);
		this.setLayout(new BorderLayout(0, 0));

		travelAgencyCtr = new TravelAgencyCtr();

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
				String name = null;
				name = search_name.getText();
				showSearchedTravelAgencies(name);
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

		TableColumn tblclmnName = new TableColumn(table, SWT.NONE);
		tblclmnName.setWidth(100);
		tblclmnName.setText("Name");
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
				int travelAgencyId = 0;
				String name = null;
				double discount = 0.0;
				String address = null;
				String phoneNo = null;
				String email = null;
				try {
					travelAgencyId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					create = true;
					boolean error = false;
					try {
						name = txt_name.getText();
						discount = Double.parseDouble(txt_discount.getText());
						address = txt_address.getText();
						phoneNo = txt_phoneNo.getText();
						email = txt_email.getText();
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
							travelAgencyId = travelAgencyCtr
									.insertTravelAgency(name, discount,
											address, phoneNo, email);
						} catch (Exception ex1) {
							ok = false;
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
						}
						if (ok) {
							showAllTravelAgencies();
							showTravelAgency(travelAgencyId);
						}
					}
				}
				if (!create) {
					boolean error = false;
					try {
						name = txt_name.getText();
						discount = Double.parseDouble(txt_discount.getText());
						address = txt_address.getText();
						phoneNo = txt_phoneNo.getText();
						email = txt_email.getText();
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
							travelAgencyCtr.updateTravelAgency(travelAgencyId,
									name, discount, address, phoneNo, email);
						} catch (Exception ex1) {
							MessageBox box = new MessageBox(getShell(), 0);
							box.setText("Error");
							box.setMessage("There was an error. Please try again");
							box.open();
							ok = false;
						}
						if (ok) {
							showAllTravelAgencies();
							showTravelAgency(travelAgencyId);
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
				int travelAgencyId = 0;
				boolean error = false;
				try {
					travelAgencyId = Integer.parseInt(txt_id.getText());
				} catch (NumberFormatException ex) {
					error = true;
					MessageBox box = new MessageBox(getShell(), 0);
					box.setText("Error");
					box.setMessage("There was an error. Please try again");
					box.open();
				}
				if (!error) {
					try {
						travelAgencyCtr.removeTravelAgency(travelAgencyId);
					} catch (Exception ex) {
						MessageBox box = new MessageBox(getShell(), 0);
						box.setText("Error");
						box.setMessage("There was an error. Please try again");
						box.open();
					}
					showAllTravelAgencies();
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
				txt_name.setEditable(true);
				txt_discount.setEditable(true);
				txt_address.setEditable(true);
				txt_phoneNo.setEditable(true);
				txt_name.setEditable(true);
				txt_email.setEditable(true);
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
				txt_name.setEditable(true);
				txt_discount.setEditable(true);
				txt_address.setEditable(true);
				txt_phoneNo.setEditable(true);
				txt_name.setEditable(true);
				txt_email.setEditable(true);
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

		Label lblName = new Label(composite_7, SWT.NONE);
		lblName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblName.setText("Name:");

		txt_name = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_name = new GridData(SWT.LEFT, SWT.CENTER, true, false,
				1, 1);
		gd_txt_name.widthHint = 203;
		txt_name.setEditable(false);
		txt_name.setLayoutData(gd_txt_name);

		Label lblDiscount = new Label(composite_7, SWT.NONE);
		lblDiscount.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblDiscount.setText("Discount:");

		txt_discount = new Text(composite_7, SWT.BORDER);
		GridData gd_txt_discount = new GridData(SWT.LEFT, SWT.CENTER, true,
				false, 1, 1);
		gd_txt_discount.widthHint = 203;
		txt_discount.setEditable(false);
		txt_discount.setLayoutData(gd_txt_discount);

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

		Label lblEmail = new Label(composite_7, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		lblEmail.setText("Email:");

		txt_email = new Text(composite_7, SWT.BORDER);
		txt_email.setEditable(false);
		txt_email.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		table.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				int id = Integer.parseInt(table.getItem(
						table.getSelectionIndex()).getText(0));
				showTravelAgency(id);
			}
		});
		showAllTravelAgencies();
	}

	private void showAllTravelAgencies() {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<TravelAgency> travelAgencies = travelAgencyCtr
				.getAllTravelAgencies();
		for (TravelAgency travelAgency : travelAgencies) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(travelAgency.getAgencyId()));
			item.setText(1, travelAgency.getName());
		}

	}

	private void showSearchedTravelAgencies(String name) {
		table.clearAll();
		table.setItemCount(0);
		LinkedList<TravelAgency> travelAgencies = travelAgencyCtr
				.searchTravelAgencyByName(name);
		for (TravelAgency travelAgency : travelAgencies) {
			TableItem item = new TableItem(table, SWT.NONE);
			item.setText(0, String.valueOf(travelAgency.getAgencyId()));
			item.setText(1, travelAgency.getName());
		}

	}

	private void showTravelAgency(int id) {
		TravelAgency travelAgency = travelAgencyCtr.searchTravelAgencyById(id);
		txt_id.setText(String.valueOf(travelAgency.getAgencyId()));
		txt_name.setText(travelAgency.getName());
		txt_discount.setText(String.valueOf(travelAgency.getDiscount()));
		txt_address.setText(travelAgency.getAddress());
		txt_phoneNo.setText(travelAgency.getPhoneNo());
		txt_email.setText(travelAgency.getEmail());

		txt_id.setEditable(false);
		txt_name.setEditable(false);
		txt_discount.setEditable(false);
		txt_address.setEditable(false);
		txt_phoneNo.setEditable(false);
		txt_email.setEditable(false);

		btn_create.setEnabled(true);
		btn_edit.setEnabled(true);
		btn_delete.setEnabled(true);
		btn_save.setEnabled(false);
	}

	public void resetFields() {
		txt_id.setText("");
		txt_name.setText("");
		txt_discount.setText("");
		txt_address.setText("");
		txt_phoneNo.setText("");
		txt_email.setText("");
		search_name.setText("");
	}
}