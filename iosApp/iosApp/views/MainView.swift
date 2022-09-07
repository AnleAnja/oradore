import SwiftUI
import shared

struct MainView: View {
  @EnvironmentObject var viewModel: AppViewModel
  
  var body: some View {
    TabView {
      ProgramEntriesView(title: "Programm", programEntries: viewModel.programEntries)
        .tabItem {
          Label("Programm", systemImage: "list.bullet")
        }
        .tag(1)
        .onAppear {
          viewModel.fetchProgramEntries()
        }
      SpeakersView(speakers: viewModel.speakers)
        .tabItem {
          Label("Speaker", systemImage: "person.2")
        }
        .tag(2)
        .onAppear {
          viewModel.fetchSpeakers()
        }
      RoomsView(rooms: viewModel.rooms)
        .tabItem {
          Label("Räume", systemImage: "location")
        }
        .tag(3)
        .onAppear {
          viewModel.fetchRooms()
        }
      FavoritesView(favorites: viewModel.favorites())
        .tabItem {
          Label("Favoriten", systemImage: "star")
        }
        .tag(4)
    }
  }
}
