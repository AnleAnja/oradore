//
//  FavoritesView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct FavoritesView: View {
  @EnvironmentObject var viewModel: AppViewModel
  
  var body: some View {
    let favorites = viewModel.favorites()
    ProgramEntriesView(title: "Favoriten", entries: favorites)
  }
}
