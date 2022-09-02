import SwiftUI
import shared

struct MainView: View {
  var body: some View {
    TabView {
      ProgramEntriesView()
        .tabItem {
          Label("Programm", systemImage: "list.bullet")
        }
        .tag(1)
      SpeakersView()
        .tabItem {
          Label("Speaker", systemImage: "person.2")
        }.tag(2)
      RoomsView()
        .tabItem {
          Label("Räume", systemImage: "mappin.circle")
        }.tag(3)
      FavoritesView()
        .tabItem {
          Label("Favoriten", systemImage: "star")
        }.tag(4)
    }
  }
}
