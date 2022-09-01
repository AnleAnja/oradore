import SwiftUI
import shared

struct MainView: View {
  var body: some View {
    TabView {
      ProgramEntriesView()
        .tabItem {
          Label("Programm", systemImage: "1.square.fill")
        }
        .tag(1)
      SpeakersView()
        .tabItem {
          Label("Speaker", systemImage: "1.square.fill")
        }.tag(2)
      RoomsView()
        .tabItem {
          Label("Räume", systemImage: "1.square.fill")
        }.tag(3)
      FavoritesView()
        .tabItem {
          Label("Favoriten", systemImage: "1.square.fill")
        }.tag(4)
    }
  }
}
