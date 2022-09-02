//
//  SpeakersView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SpeakersView: View {
  let speakers = ["speaker 1", "speaker 2", "speaker 3", "speaker 4"]
  
  @State private var searchText = ""
  
  private var searchResults: [String] {
    if searchText.isEmpty {
      return speakers
    } else {
      return speakers.filter { $0.contains(searchText) }
    }
  }
  
  var body: some View {
    NavigationView {
      List {
        ForEach(searchResults, id: \.self) { speaker in
          NavigationLink(destination: Text(speaker)) {
            Text(speaker)
          }
        }
      }
      .navigationTitle("Speaker")
      .searchable(text: $searchText)
    }
  }
}
