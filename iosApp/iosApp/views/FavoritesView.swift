//
//  FavoritesView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct FavoritesView: View {
  let favorites = ["fav 1", "fav 2", "fav 3", "fav 4"]
  
  @State private var searchText = ""
  
  private var searchResults: [String] {
    if searchText.isEmpty {
      return favorites
    } else {
      return favorites.filter { $0.contains(searchText) }
    }
  }
  
  var body: some View {
    NavigationView {
      List {
        ForEach(searchResults, id: \.self) { fav in
          NavigationLink(destination: Text(fav)) {
            Text(fav)
          }
        }
      }
      .navigationTitle("Favoriten")
      .searchable(text: $searchText)
    }
  }
}
