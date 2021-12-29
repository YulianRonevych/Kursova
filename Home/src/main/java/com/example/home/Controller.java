package com.example.home;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import  java.sql.*;
import java.io.IOException;
import java.sql.Connection;

public class Controller {

    public Button AdButton;
    public Button FindButton;
    public Button ListButton;
    public Button AverageButton;
    public Button DeleteButton;
    public Button TurnButton;
    public Button ExitButton;
    public Button ReturnButton1;
    public Button SpeakerB;
    public Button HairDryerB;
    public Button PCB;
    public Button VacuumB;
    public  Button ReturnButton2;
    public Button AddNewSpeaker;
    public TextField InputName;
    public TextField InputPowerCons;
    public TextField InputOnOff;
    public TextField InputVolume;
    public Label SuccessSpeakerLabel;
    static Connection MyCon;
    public Button SpeakersListShow;
    public Button FullListShow;
    public Button PCsListShow;
    public Button HairdryersListShow;
    public Button VacuumListShow;
    public Button return3;
    public Button AddNewPC;
    public Button AddNewHairDryer;
    public Button AddNewVacuum;
    public TextField InputPCName;
    public TextField InputProcName;
    public TextField InputRAM;
    public TextField InputFlowS;
    public TextField InputFlowT;
    public TextField InputIsWet;
    public Label SuccessPCLabel;
    public Label SuccessHDLabel;
    public Label SuccessVLabel;
    public Button test;
    public AnchorPane VacuumlistWnd;
    public AnchorPane FulllistWnd;
    public  AnchorPane SpeakerListWnd;
    public AnchorPane HairDryerListWnd;
    public AnchorPane PCListWnd;
    public Button fullB;
    public Button Calculate;
    public Label AvgLabel;
    public Button FindOn;
    public Button FindName;
    public Button FindCons;
   public Button ShowFindName;
   public Button ShowFindOn;
   public Button ShowFindPower;
   public TextField FindOnInput;
   public TextField FindNameInput;
   public TextField FindPowerInput;
   public AnchorPane FindNameWnd;
   public AnchorPane FindOnWnd;
   public AnchorPane FindPowerWnd;
   public Button returnToFind;
   public Button DeleteButton1;
   public Button SetButton;
   public TextField InputSetOn;
   public TextField InputDelete;
    public TextField InputSetOn1;



    static {
        try {
            MyCon = DBCon.GetCon();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void AddButtonClick() throws IOException {
        Stage stage = (Stage) AdButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddnewDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    public void FindButtonClick() throws SQLException, IOException {
        Stage stage = (Stage) ListButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("FindMenu.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    public void ListButtonClick() throws IOException {
        Stage stage = (Stage) ListButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ShowAllDevices.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    public void AverageButtonClick() throws IOException {
        Stage stage = (Stage) AverageButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AVG.fxml"));
        stage.setScene(new Scene(newRoot, 600,400));
    }

    public  void Calculate() throws SQLException {
        Statement stat = MyCon.createStatement();
        ResultSet rs = stat.executeQuery("SELECT SUM(PowerConsumption) as s FROM deviceDefault as dd\n" +
                "WHERE dd.TurnedOn = 1;");
        rs.next();
        double result = rs.getDouble("s");
        AvgLabel.setText("Power consumption: "+ result);
    }

    public void DeleteButtonClick() throws IOException {
        Stage stage = (Stage) DeleteButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("DeleteDevice.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    public void TurnButtonClick() throws IOException {
        Stage stage = (Stage) TurnButton.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("SetOn.fxml"));
        stage.getScene().setRoot(newRoot);
    }


    public void DeleteButton1() throws SQLException {
        Statement mystate = MyCon.createStatement();
        int inputed = Integer.parseInt(InputDelete.getText());
        mystate.executeUpdate("DELETE FROM deviceDefault WHERE Id = "+inputed+";");
        ResultSet rs; 
        String Type = null;
        rs = mystate.executeQuery("SELECT * FROM Speakers  WHERE Id = "+inputed+";");
        if(rs.next()){
            Type = rs.getString("DType");
        }else{
            rs = mystate.executeQuery("SELECT * FROM HairDryer  WHERE Id = "+inputed+";");
            if(rs.next()){
                Type = rs.getString("DType");
            }else{
                rs = mystate.executeQuery("SELECT * FROM PCs  WHERE Id = "+inputed+";");
                if(rs.next()){
                    Type = rs.getString("DType");
                }else{
                    rs = mystate.executeQuery("SELECT * FROM Vacuums  WHERE Id = "+inputed+";");
                    if(rs.next()){
                        Type = rs.getString("DType");
                    }
                }
            }    
        }
        
        if(Type == "Speaker"){
            mystate.executeUpdate("DELETE FROM Speakers WHERE Id = "+inputed+";");
        }
        if(Type == "PC"){
            mystate.executeUpdate("DELETE FROM PCs WHERE Id = "+inputed+";");
        }
        if(Type == "Vacuum"){
            mystate.executeUpdate("DELETE FROM Vacuums WHERE Id = "+inputed+";");
        }
        if(Type == "HairDryer"){
            mystate.executeUpdate("DELETE FROM HairDryer WHERE Id = "+inputed+";");
        }
    }

    public void SetButton() throws SQLException {
        Statement mystate = MyCon.createStatement();
        int inputed = Integer.parseInt(InputSetOn.getText());
        boolean inputed1 = Integer.parseInt(InputSetOn1.getText()) == 1;
        mystate.executeUpdate("UPDATE deviceDefault SET TurnedOn = "+inputed1+" WHERE Id = "+inputed+";");
    }



    public void ExitButtonClick(){
        Stage stage = (Stage) ExitButton.getScene().getWindow();
        stage.close();
    }

    public void ReturnButton1Click() throws IOException {
        Stage stage = (Stage) ReturnButton1.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.setScene(new Scene(newRoot, 376,503));
    }

    public void SpeakerButtonClick() throws IOException {
        Stage stage = (Stage) SpeakerB.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddSpeaker.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    public void HairDryerButtonClick() throws IOException {
        Stage stage = (Stage) HairDryerB.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddHD.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    public void PCButtonClick() throws IOException {
        Stage stage = (Stage) PCB.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddPC.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    public void VacuumButtonClick() throws IOException {
        Stage stage = (Stage) VacuumB.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddVacuum.fxml"));
        stage.getScene().setRoot(newRoot);
    }

    public void AddNewSpeaker() throws SQLException {
        SuccessSpeakerLabel.setVisible(false);
        String Name = InputName.getText();
        Double PowerCons = Double.parseDouble(InputPowerCons.getText());
        boolean OnOff = Integer.parseInt(InputOnOff.getText()) == 1;
        Double Volume = Double.parseDouble(InputVolume.getText());
        Statement mystate = MyCon.createStatement();
        int random = getRandomNumber(1,1000);
        String sql = "insert into deviceDefault VALUES("+random+",\""+Name+"\","
                +PowerCons+","+OnOff+");";
        String sql1 = "insert into Speakers(Id,Volume) VALUES("+random+"," + Volume+");";
        mystate.executeUpdate(sql);
        mystate.executeUpdate(sql1);

        InputVolume.clear();
        InputPowerCons.clear();
        InputOnOff.clear();
        InputName.clear();
        SuccessSpeakerLabel.setVisible(true);
    }

    public void ReturnButton2Click() throws IOException {
        Stage stage = (Stage) ReturnButton2.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("AddnewDevice.fxml"));
        stage.setScene(new Scene(newRoot, 376,503));
    }

    public void VacuumListShow() throws IOException, SQLException {
        Stage stage = (Stage) VacuumListShow.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("VacuumList.fxml"));
        stage.setScene(new Scene(newRoot, 500,500));
    }


    public void HairDryerB() throws IOException, SQLException {
        HairDryer[] Allhd = {};
        TableView<HairDryer> HDList;
        Statement stat = MyCon.createStatement();
        ResultSet rs = stat.executeQuery("Select * FROM deviceDefault\n" +
                "JOIN HairDryer on deviceDefault.Id = HairDryer.Id;");
        while(rs.next()){
            int id = rs.getInt("Id");
            double pwcs = rs.getDouble("PowerConsumption");
            String name = rs.getString("DName");
            boolean OnOff = rs.getBoolean("TurnedOn");
            double fs = rs.getDouble("FlowSpeed");
            double ft = rs.getDouble("FlowTemp");
            Allhd = HairDryer.addHairDryer(Allhd.length,Allhd,new HairDryer(pwcs,name,OnOff,fs,ft,id));
        }
        TableColumn<HairDryer, Integer> idcolumn = new TableColumn<HairDryer ,Integer>("ID");
        idcolumn.setMinWidth(83);
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<HairDryer, Double> pccolumn = new TableColumn<HairDryer, Double>("Power Consumption");
        pccolumn.setMinWidth(83);
        pccolumn.setCellValueFactory(new PropertyValueFactory<>("Power"));

        TableColumn<HairDryer, String> namecolumn = new TableColumn<HairDryer, String>("DName");
        namecolumn.setMinWidth(83);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<HairDryer, Boolean> onoffcolumn = new TableColumn<HairDryer, Boolean>("On");
        onoffcolumn.setMinWidth(83);
        onoffcolumn.setCellValueFactory(new PropertyValueFactory<>("IsOn"));

        TableColumn<HairDryer, Double> flows = new TableColumn<HairDryer,Double>("FlowSpeed");
        flows.setMinWidth(83);
        flows.setCellValueFactory(new PropertyValueFactory<>("FlowSpeed"));

        TableColumn<HairDryer, Double> flowt = new TableColumn<HairDryer,Double>("FlowTemp");
        flowt.setMinWidth(83);
        flowt.setCellValueFactory(new PropertyValueFactory<>("FlowTemperature"));

        HDList = new TableView<HairDryer>();
        HDList.setItems(getAllHairDryers(Allhd));
        HDList.getColumns().addAll(idcolumn,pccolumn,namecolumn,onoffcolumn,flows,flowt);

        HairDryerListWnd.getChildren().add(HDList);

    }

    public void fullB() throws IOException, SQLException {
        Device[] Alld = {};
        TableView<Device> DList;
        Statement stat = MyCon.createStatement();
        ResultSet rs = stat.executeQuery("Select * FROM deviceDefault;");
        while(rs.next()){
            int id = rs.getInt("Id");
            double pwcs = rs.getDouble("PowerConsumption");
            String name = rs.getString("DName");
            boolean OnOff = rs.getBoolean("TurnedOn");
            Alld = Device.addDevice(Alld.length,Alld,new Device(pwcs,name,OnOff,id));
        }
        TableColumn<Device, Integer> idcolumn = new TableColumn<Device,Integer>("ID");
        idcolumn.setMinWidth(124);
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Device, Double> pccolumn = new TableColumn<Device, Double>("Power Consumption");
        pccolumn.setMinWidth(124);
        pccolumn.setCellValueFactory(new PropertyValueFactory<>("Power"));

        TableColumn<Device, String> namecolumn = new TableColumn<Device, String>("DName");
        namecolumn.setMinWidth(124);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Device, Boolean> onoffcolumn = new TableColumn<Device, Boolean>("On");
        onoffcolumn.setMinWidth(124);
        onoffcolumn.setCellValueFactory(new PropertyValueFactory<>("IsOn"));

        DList = new TableView<Device>();
        DList.setItems(getAllDevices(Alld));
        DList.getColumns().addAll(idcolumn,pccolumn,namecolumn,onoffcolumn);

        FulllistWnd.getChildren().add(DList);

    }

    public void SpeakerB() throws IOException, SQLException {
        Speaker[] Alls = {};
        TableView< Speaker> SList;
        Statement stat = MyCon.createStatement();
        ResultSet rs = stat.executeQuery("Select * FROM deviceDefault\n" +
                "JOIN Speakers ON deviceDefault.Id = Speakers.Id;");
        while(rs.next()){
            int id = rs.getInt("Id");
            double pwcs = rs.getDouble("PowerConsumption");
            String name = rs.getString("DName");
            boolean OnOff = rs.getBoolean("TurnedOn");
            double volume = rs.getDouble("Volume");
            Alls = Speaker.addSpeaker(Alls.length,Alls,new  Speaker(pwcs,name,OnOff,volume,id));
        }
        TableColumn<Speaker, Integer> idcolumn = new TableColumn<Speaker,Integer>("ID");
        idcolumn.setMinWidth(99);
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Speaker, Double> pccolumn = new TableColumn<Speaker, Double>("Power Consumption");
        pccolumn.setMinWidth(99);
        pccolumn.setCellValueFactory(new PropertyValueFactory<>("Power"));

        TableColumn<Speaker, String> namecolumn = new TableColumn<Speaker, String>("DName");
        namecolumn.setMinWidth(99);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Speaker, Boolean> onoffcolumn = new TableColumn<Speaker, Boolean>("On");
        onoffcolumn.setMinWidth(99);
        onoffcolumn.setCellValueFactory(new PropertyValueFactory<>("IsOn"));

        TableColumn<Speaker, Double> volume = new TableColumn<Speaker, Double>("Volume");
        volume.setMinWidth(99);
        volume.setCellValueFactory(new PropertyValueFactory<>("VolumeDz"));

        SList = new TableView<Speaker>();
        SList.setItems(getAllSpeakers(Alls));
        SList.getColumns().addAll(idcolumn,pccolumn,namecolumn,onoffcolumn,volume);

        SpeakerListWnd.getChildren().add(SList);

    }

    public void PCB() throws IOException, SQLException {
        PC[] Allp = {};
        TableView<PC> PList;
        Statement stat = MyCon.createStatement();
        ResultSet rs = stat.executeQuery("Select * FROM deviceDefault\n" +
                "JOIN PCs on deviceDefault.Id = PCs.Id;");
        while(rs.next()){
            int id = rs.getInt("Id");
            double pwcs = rs.getDouble("PowerConsumption");
            double RAM = rs.getDouble("RAM");
            String name = rs.getString("DName");
            String pcname = rs.getString("PCName");
            String pname = rs.getString("ProcName");
            boolean OnOff = rs.getBoolean("TurnedOn");
            Allp = PC.addPC(Allp.length,Allp,new  PC(pwcs,name,OnOff,pcname,RAM,pname,id));
        }
        TableColumn<PC, Integer> idcolumn = new TableColumn<PC,Integer>("ID");
        idcolumn.setMinWidth(71);
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<PC, Double> pccolumn = new TableColumn<PC, Double>("Power Consumption");
        pccolumn.setMaxWidth(71);
        pccolumn.setCellValueFactory(new PropertyValueFactory<>("Power"));

        TableColumn<PC, String> namecolumn = new TableColumn<PC, String>("DName");
        namecolumn.setMinWidth(71);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<PC, Boolean> onoffcolumn = new TableColumn<PC, Boolean>("On");
        onoffcolumn.setMinWidth(71);
        onoffcolumn.setCellValueFactory(new PropertyValueFactory<>("IsOn"));

        TableColumn<PC, Double> ram = new TableColumn<PC, Double>("RAM");
        ram.setMinWidth(71);
        ram.setCellValueFactory(new PropertyValueFactory<>("RAM"));

        TableColumn<PC, String> pcname = new TableColumn<PC, String>("PCname");
        pcname.setMinWidth(71);
        pcname.setCellValueFactory(new PropertyValueFactory<>("PCName"));

        TableColumn<PC, String> pname = new TableColumn<PC, String>("ProcName");
        pname.setMinWidth(71);
        pname.setCellValueFactory(new PropertyValueFactory<>("ProccesorName"));

        PList = new TableView<PC>();
        PList.setItems(getAllPCs(Allp));
        PList.getColumns().addAll(idcolumn,pccolumn,namecolumn,onoffcolumn,pname,ram,pcname);

        PCListWnd.getChildren().add(PList);

    }

    public void test() throws SQLException, IOException {
        Vacuum[] AllVac = {};
        TableView<Vacuum> VacuumList;
        Statement stat = MyCon.createStatement();
        ResultSet rs = stat.executeQuery("Select * FROM deviceDefault\n" +
                "JOIN Vacuums on deviceDefault.Id = Vacuums.Id;");
        while(rs.next()){
            int id = rs.getInt("Id");
            double pwcs = rs.getDouble("PowerConsumption");
            String name = rs.getString("DName");
            boolean OnOff = rs.getBoolean("TurnedOn");
            boolean iswet  = rs.getBoolean("IsWet");
            AllVac = Vacuum.addVacuum(AllVac.length,AllVac,new Vacuum(pwcs,name,OnOff,iswet,id));
        }
        TableColumn<Vacuum, Integer> idcolumn = new TableColumn<Vacuum ,Integer>("ID");
        idcolumn.setMinWidth(99);
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Vacuum, Double> pccolumn = new TableColumn<Vacuum, Double>("Power Consumption");
        pccolumn.setMinWidth(99);
        pccolumn.setCellValueFactory(new PropertyValueFactory<>("Power"));

        TableColumn<Vacuum, String> namecolumn = new TableColumn<Vacuum, String>("DName");
        namecolumn.setMinWidth(99);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Vacuum, Boolean> onoffcolumn = new TableColumn<Vacuum, Boolean>("On/Off");
        onoffcolumn.setMinWidth(99);
        onoffcolumn.setCellValueFactory(new PropertyValueFactory<>("IsOn"));

        TableColumn<Vacuum, Boolean> iswetcolumn = new TableColumn<Vacuum,Boolean>("IsWet");
        iswetcolumn.setMinWidth(99);
        iswetcolumn.setCellValueFactory(new PropertyValueFactory<>("IsDry"));

        VacuumList = new TableView<Vacuum>();
        VacuumList.setItems(getAllVacuums(AllVac));
        VacuumList.getColumns().addAll(idcolumn,pccolumn,namecolumn,onoffcolumn,iswetcolumn);

        VacuumlistWnd.getChildren().add(VacuumList);

    }

    public void SpeakersListShow() throws IOException {
        Stage stage = (Stage) SpeakersListShow.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("SpeakerList.fxml"));
        stage.setScene(new Scene(newRoot, 500,500));
    }

    public void PCsListShow() throws IOException {
        Stage stage = (Stage) PCsListShow.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("PCList.fxml"));
        stage.setScene(new Scene(newRoot, 500,500));
    }

    public void HairdryersListShow() throws IOException {
        Stage stage = (Stage) HairdryersListShow.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("HairDryerList.fxml"));
        stage.setScene(new Scene(newRoot, 500,500));
    }

    public void FullListShow() throws IOException {
        Stage stage = (Stage) FullListShow.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("FullList.fxml"));
        stage.setScene(new Scene(newRoot, 500,500));
    }

    public void return3click() throws IOException {
        Stage stage = (Stage) return3.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("ShowAllDevices.fxml"));
        stage.setScene(new Scene(newRoot, 376,503));
    }

    public ObservableList<Device> getAllDevices(Device[] AllDevices){
        ObservableList<Device> list = FXCollections.observableArrayList();
        for(int i = 0; i<AllDevices.length;i++){
            list.add(AllDevices[i]);
        }
        return list;
    }

    public ObservableList<Speaker> getAllSpeakers(Speaker[] AllSpeakers){
        ObservableList<Speaker> list = FXCollections.observableArrayList();
        for(int i = 0; i<AllSpeakers.length;i++){
            list.add(AllSpeakers[i]);
        }
        return list;
    }

    public ObservableList<HairDryer> getAllHairDryers(HairDryer[] AllHairDryers){
        ObservableList<HairDryer> list = FXCollections.observableArrayList();
        for(int i = 0; i<AllHairDryers.length;i++){
            list.add(AllHairDryers[i]);
        }
        return list;
    }

    public ObservableList<PC> getAllPCs(PC[] AllPCs){
        ObservableList<PC> list = FXCollections.observableArrayList();
        for(int i = 0; i<AllPCs.length;i++){
            list.add(AllPCs[i]);
        }
        return list;
    }

    public ObservableList<Vacuum> getAllVacuums(Vacuum[] AllVacuums){
        ObservableList<Vacuum> list = FXCollections.observableArrayList();
        for(int i = 0; i<AllVacuums.length;i++){
            list.add(AllVacuums[i]);
        }
        return list;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public void AddNewVacuum() throws SQLException {
        SuccessVLabel.setVisible(false);
        String Name = InputName.getText();
        Double PowerCons = Double.parseDouble(InputPowerCons.getText());
        boolean OnOff = Integer.parseInt(InputOnOff.getText()) == 1;
        boolean Wet = Integer.parseInt(InputIsWet.getText()) == 1;
        Statement mystate = MyCon.createStatement();
        int random = getRandomNumber(1,1000);
        String sql = "insert into deviceDefault VALUES("+random+",\""+Name+"\","
                +PowerCons+","+OnOff+");";
        String sql1 = "insert into Vacuums(Id,IsWet) VALUES("+random+"," +Wet+");";
        mystate.executeUpdate(sql);
        mystate.executeUpdate(sql1);

        InputIsWet.clear();
        InputPowerCons.clear();
        InputOnOff.clear();
        InputName.clear();
        SuccessVLabel.setVisible(true);
    }

    public void AddNewPC() throws SQLException {
        SuccessPCLabel.setVisible(false);
        String Name = InputName.getText();
        Double PowerCons = Double.parseDouble(InputPowerCons.getText());
        boolean OnOff = Integer.parseInt(InputOnOff.getText()) == 1;
        String Pcname = InputPCName.getText();
        String ProcName = InputProcName.getText();
        Double RAM = Double.parseDouble(InputRAM.getText());
        Statement mystate = MyCon.createStatement();
        int random = getRandomNumber(1,1000);
        String sql = "insert into deviceDefault VALUES("+random+",\""+Name+"\","
                +PowerCons+","+OnOff+");";
        String sql1 = "insert into PCs(Id,PCName,RAM,ProcName) VALUES("+random+",\""+Pcname+"\","+RAM+",\""+ProcName+"\" );";
        mystate.executeUpdate(sql);
        mystate.executeUpdate(sql1);

        InputRAM.clear();
        InputPowerCons.clear();
        InputOnOff.clear();
        InputName.clear();
        InputProcName.clear();
        InputPCName.clear();
        SuccessPCLabel.setVisible(true);
    }

    public void AddNewHairDryer() throws SQLException {
        SuccessHDLabel.setVisible(false);
        String Name = InputName.getText();
        Double PowerCons = Double.parseDouble(InputPowerCons.getText());
        boolean OnOff = Integer.parseInt(InputOnOff.getText()) == 1;
        Double FlowS = Double.parseDouble(InputFlowS.getText());
        Double FlowT = Double.parseDouble(InputFlowT.getText());
        Statement mystate = MyCon.createStatement();
        int random = getRandomNumber(1,1000);
        String sql = "insert into deviceDefault VALUES("+random+",\""+Name+"\","
                +PowerCons+","+OnOff+");";
        String sql1 = "insert into HairDryer(Id,FlowSpeed,FlowTemp) VALUES("+random+","+FlowS+","+FlowT+");";
        mystate.executeUpdate(sql);
        mystate.executeUpdate(sql1);

        InputFlowT.clear();
        InputPowerCons.clear();
        InputOnOff.clear();
        InputName.clear();
        InputFlowS.clear();
        SuccessHDLabel.setVisible(true);
    }

    public void FindName() throws IOException {
        Stage stage = (Stage) FindName.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("FindByNameMenu.fxml"));
        stage.setScene(new Scene(newRoot, 500,500));
    }

    public void FindCons() throws IOException {
        Stage stage = (Stage) FindCons.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("FindByPowerMenu.fxml"));
        stage.setScene(new Scene(newRoot, 500,500));
    }

    public void FindOn() throws IOException {
        Stage stage = (Stage) FindOn.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("FindByOnMenu.fxml"));
        stage.setScene(new Scene(newRoot, 500,500));
    }

    public void ShowFindName() throws SQLException {
        Device[] Alld = {};
        TableView<Device> DList;
        String inputed = FindNameInput.getText();
        Statement stat = MyCon.createStatement();
        ResultSet rs = stat.executeQuery("Select * FROM deviceDefault\n" +
                "WHERE DName LIKE '%"+inputed+"%';");
        while(rs.next()){
            int id = rs.getInt("Id");
            double pwcs = rs.getDouble("PowerConsumption");
            String name = rs.getString("DName");
            boolean OnOff = rs.getBoolean("TurnedOn");
            Alld = Device.addDevice(Alld.length,Alld,new Device(pwcs,name,OnOff,id));
        }
        TableColumn<Device, Integer> idcolumn = new TableColumn<Device,Integer>("ID");
        idcolumn.setMinWidth(124);
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Device, Double> pccolumn = new TableColumn<Device, Double>("Power Consumption");
        pccolumn.setMinWidth(124);
        pccolumn.setCellValueFactory(new PropertyValueFactory<>("Power"));

        TableColumn<Device, String> namecolumn = new TableColumn<Device, String>("DName");
        namecolumn.setMinWidth(124);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Device, Boolean> onoffcolumn = new TableColumn<Device, Boolean>("On");
        onoffcolumn.setMinWidth(124);
        onoffcolumn.setCellValueFactory(new PropertyValueFactory<>("IsOn"));

        DList = new TableView<Device>();
        DList.setItems(getAllDevices(Alld));
        DList.getColumns().addAll(idcolumn,pccolumn,namecolumn,onoffcolumn);

        FindNameWnd.getChildren().add(DList);

    }

    public void ShowFindPower() throws SQLException {
        Device[] Alld = {};
        TableView<Device> DList;
        double inputed = Double.parseDouble(FindPowerInput.getText());
        Statement stat = MyCon.createStatement();
        ResultSet rs = stat.executeQuery("Select * FROM deviceDefault " +
                "WHERE  PowerConsumption >=" + inputed+";");
        while(rs.next()){
            int id = rs.getInt("Id");
            double pwcs = rs.getDouble("PowerConsumption");
            String name = rs.getString("DName");
            boolean OnOff = rs.getBoolean("TurnedOn");
            Alld = Device.addDevice(Alld.length,Alld,new Device(pwcs,name,OnOff,id));
        }
        TableColumn<Device, Integer> idcolumn = new TableColumn<Device,Integer>("ID");
        idcolumn.setMinWidth(124);
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Device, Double> pccolumn = new TableColumn<Device, Double>("Power Consumption");
        pccolumn.setMinWidth(124);
        pccolumn.setCellValueFactory(new PropertyValueFactory<>("Power"));

        TableColumn<Device, String> namecolumn = new TableColumn<Device, String>("DName");
        namecolumn.setMinWidth(124);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Device, Boolean> onoffcolumn = new TableColumn<Device, Boolean>("On");
        onoffcolumn.setMinWidth(124);
        onoffcolumn.setCellValueFactory(new PropertyValueFactory<>("IsOn"));

        DList = new TableView<Device>();
        DList.setItems(getAllDevices(Alld));
        DList.getColumns().addAll(idcolumn,pccolumn,namecolumn,onoffcolumn);

        FindPowerWnd.getChildren().add(DList);
    }

    public void ShowFindOn() throws SQLException {
        Device[] Alld = {};
        TableView<Device> DList;
        boolean inputed = Integer.parseInt(FindOnInput.getText()) == 1;
        Statement stat = MyCon.createStatement();
        ResultSet rs = null;
        rs = stat.executeQuery("Select * FROM deviceDefault " +
                    "WHERE TurnedOn = " + inputed + ";");
        while(rs.next()){
            int id = rs.getInt("Id");
            double pwcs = rs.getDouble("PowerConsumption");
            String name = rs.getString("DName");
            boolean OnOff = rs.getBoolean("TurnedOn");
            Alld = Device.addDevice(Alld.length,Alld,new Device(pwcs,name,OnOff,id));
        }
        TableColumn<Device, Integer> idcolumn = new TableColumn<Device,Integer>("ID");
        idcolumn.setMinWidth(124);
        idcolumn.setCellValueFactory(new PropertyValueFactory<>("Id"));

        TableColumn<Device, Double> pccolumn = new TableColumn<Device, Double>("Power Consumption");
        pccolumn.setMinWidth(124);
        pccolumn.setCellValueFactory(new PropertyValueFactory<>("Power"));

        TableColumn<Device, String> namecolumn = new TableColumn<Device, String>("DName");
        namecolumn.setMinWidth(124);
        namecolumn.setCellValueFactory(new PropertyValueFactory<>("Name"));

        TableColumn<Device, Boolean> onoffcolumn = new TableColumn<Device, Boolean>("On");
        onoffcolumn.setMinWidth(124);
        onoffcolumn.setCellValueFactory(new PropertyValueFactory<>("IsOn"));

        DList = new TableView<Device>();
        DList.setItems(getAllDevices(Alld));
        DList.getColumns().addAll(idcolumn,pccolumn,namecolumn,onoffcolumn);

        FindOnWnd.getChildren().add(DList);
    }

    public void returnToFind() throws IOException {
        Stage stage = (Stage) returnToFind.getScene().getWindow();
        Parent newRoot = FXMLLoader.load(getClass().getResource("FindMenu.fxml"));
        stage.setScene(new Scene(newRoot, 376,503));
    }
}
