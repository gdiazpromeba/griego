; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
AppName=Kal�s
AppVersion=4.16
DefaultDirName={pf}\Kalos


[Files]
Source: "kalos.exe"; DestDir: "{app}";
Source: "lib\*"; DestDir: "{app}\lib";
Source: "armado_reportes\*"; DestDir: "{app}\armado_reportes";
Source: "db.conf"; DestDir: "{app}\db.conf";
Source: "data\*"; DestDir: "{app}\data";
Source: "kalos.properties"; DestDir: "{%HOMEPATH}";

[Languages]
Name: "en"; MessagesFile: "compiler:Default.isl"
