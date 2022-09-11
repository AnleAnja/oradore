//
//  SpeakerImageView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 04.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SpeakerImageView: View {
  let url: String
  
  var body: some View {
    AsyncImage(url: URL(string: url)) { image in
      image.resizable()
    } placeholder: {
      ProgressView()
    }
    .clipShape(Circle())
    .frame(width: 55, height: 55)
  }
}
