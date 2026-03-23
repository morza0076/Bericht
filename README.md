# Brugger Bericht App

Diese Version ist fuer GitHub Pages, iPad, iPhone, Android, Handy und Tablet vorbereitet.

## GitHub hochladen

1. Auf GitHub ein neues Repository anlegen.
2. Den kompletten Inhalt dieses Ordners in das Repository hochladen.
3. Als Branch `main` oder `master` verwenden.
4. Nach dem Push unter `Actions` pruefen, ob der Workflow `Deploy GitHub Pages` erfolgreich war.
5. Unter `Settings > Pages` sicherstellen, dass GitHub Pages aktiv ist.

## App auf dem iPad installieren

1. Die GitHub-Pages-URL in Safari oeffnen.
2. Auf `Teilen` tippen.
3. `Zum Home-Bildschirm` waehlen.
4. Die App danach direkt vom Home-Bildschirm starten.

## Offline-Nutzung

- Die App speichert den Web-App-Kern ueber den Service Worker lokal.
- Der Ordner `vendor/` ist fuer lokale PDF-Bibliotheken vorgesehen und wird ebenfalls offline gecacht.
- Fuer den ersten Offline-Einsatz die App einmal mit Internetverbindung oeffnen, damit alle Dateien in den Cache geladen werden.
- Falls keine echten lokalen PDF-Bibliotheken vorhanden sind, schaltet die App offline automatisch auf den Druckdialog um. Dort kann die Datei als PDF gesichert werden.

## PDF-Export

- Standard: Die App verwendet lokale Dateien aus `vendor/`.
- Fallback: Falls die lokalen Dateien fehlen, werden CDN-Dateien verwendet.
- Offline ohne Bibliotheken: Die App nutzt den Druckdialog als PDF-Alternative.
- Fuer direkten Datei-PDF-Export komplett ohne Internet muessen echte `html2canvas`- und `jsPDF`-Dateien lokal im `vendor/`-Ordner liegen.

## Wichtige Dateien

- `index.html`: Hauptanwendung
- `manifest.webmanifest`: Installationsdaten fuer PWA
- `sw.js`: Offline-Cache
- `.github/workflows/deploy-pages.yml`: automatisches GitHub-Pages-Deployment
- `vendor/html2canvas.min.js`: lokale PDF-Hilfsbibliothek
- `vendor/jspdf.umd.min.js`: lokale PDF-Hilfsbibliothek