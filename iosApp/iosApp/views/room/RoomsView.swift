//
//  RoomsView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

let rooms = DummyData.shared.ProgramEntriesPreview().map { $0.room }

extension Room: Identifiable { }

struct RoomsView: View {
  
  @State private var searchText = ""
  
  private var searchResults: [Room] {
    if searchText.isEmpty {
      return rooms
    } else {
      return rooms.filter {
        $0.name.contains(searchText)
      }
    }
  }
  
  var body: some View {
    NavigationView {
      List {
        ForEach(searchResults) { room in
          NavigationLink(destination: Text(room.id)) {
            Text(room.name)
          }
        }
      }
      .navigationTitle("Räume")
      .searchable(text: $searchText)
    }
  }
}
