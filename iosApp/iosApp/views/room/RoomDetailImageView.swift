//
//  RoomLocationImageView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 05.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RoomLocationImageView: View {
  let url: String
  let maxHeight: CGFloat
  
  var body: some View {
    AsyncImage(url: URL(string: url)) { image in
      image
        .resizable()
        .aspectRatio(contentMode: .fill)
        .frame(maxHeight: maxHeight)
        .clipped()
    } placeholder: {
      ProgressView()
    }
  }
}
