//
//  ProgramEntriesView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ProgramEntriesView: View {
  let talks = ["talk 1", "talk 2", "talk 3", "talk 4"]
  
  @State private var searchText = ""
  
  private var searchResults: [String] {
    if searchText.isEmpty {
      return talks
    } else {
      return talks.filter { $0.contains(searchText) }
    }
  }
  
  var body: some View {
    NavigationView {
      List {
        ForEach(searchResults, id: \.self) { talk in
          NavigationLink(destination: Text(talk)) {
            Text(talk)
          }
        }
      }
      .navigationTitle("Programm")
      .searchable(text: $searchText)
    }
  }
}
