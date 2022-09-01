//
//  RoomsView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct RoomsView: View {
  let rooms = ["room 1", "room 2", "room 3", "room 4"]
  
  @State private var searchText = ""
  
  private var searchResults: [String] {
    if searchText.isEmpty {
      return rooms
    } else {
      return rooms.filter { $0.contains(searchText) }
    }
  }
  
  var body: some View {
    NavigationView {
      List {
        ForEach(searchResults, id: \.self) { room in
          NavigationLink(destination: Text(room)) {
            Text(room)
          }
        }
      }
      .navigationTitle("Räume")
      .searchable(text: $searchText)
    }
  }
}
