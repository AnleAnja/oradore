//
//  FavoritesView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct FavoritesView: View {
  
  let favorites: [GroupProgramEntries]
  
  var body: some View {
    ProgramEntriesView(title: "Favoriten", programEntries: favorites)
  }
}
