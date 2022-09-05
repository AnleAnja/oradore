//
//  RoomsView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

extension Room: Identifiable { }

struct RoomsView: View {
  
  let rooms = DummyData.shared.Rooms()
  
  @State private var searchText = ""
  
  private var searchResults: [Room] {
    if searchText.isEmpty {
      return rooms
    } else {
      return rooms.filter {
        $0.name.contains(searchText) ||
        $0.desc.contains(searchText)
      }
    }
  }
  
  var body: some View {
    NavigationView {
      List {
        ForEach(searchResults) { room in
          NavigationLink(destination: RoomDetailView(room: room)) {
            VStack(alignment: .leading) {
              Text(room.name)
              if !room.desc.isEmpty {
                Text(room.desc)
                  .font(.caption)
                  .foregroundColor(.secondary)
              }
            }
          }
        }
      }
      .navigationTitle("Räume")
      .searchable(text: $searchText)
    }
  }
}
